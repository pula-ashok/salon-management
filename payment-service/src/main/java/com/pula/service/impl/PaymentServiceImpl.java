package com.pula.service.impl;

import com.pula.domain.PaymentMethod;
import com.pula.domain.PaymentOrderStatus;
import com.pula.model.PaymentOrder;
import com.pula.payload.dto.BookingDTO;
import com.pula.payload.dto.UserDTO;
import com.pula.payload.response.PaymentLinkResponse;
import com.pula.repository.PaymentRepository;
import com.pula.service.PaymentService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Value("${razorpay.api.key}")
    private String razorpayApiKey;

    @Value("${razorpay.api.secret}")
    private String razorpayApiSecret;

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentLinkResponse createOrder(UserDTO userDTO, BookingDTO bookingDTO, PaymentMethod paymentMethod) throws RazorpayException, StripeException {
        Long amount = (long) bookingDTO.getTotalPrice();
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setAmount(amount);
        paymentOrder.setPaymentMethod(paymentMethod);
        paymentOrder.setBookingId(bookingDTO.getId());
        paymentOrder.setSalonId(bookingDTO.getSalonId());
        paymentOrder.setUserId(userDTO.getId());
        paymentRepository.save(paymentOrder);
        PaymentLinkResponse paymentLinkResponse = new PaymentLinkResponse();
        if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
            PaymentLink paymentLink = createRazorpayPaymentLink(userDTO,amount,paymentOrder.getId());
            String paymentUrl = paymentLink.get("short_url");
            String paymentUrlId = paymentLink.get("id");
            paymentLinkResponse.setPayment_link_url(paymentUrl);
            paymentLinkResponse.setPayment_link_id(paymentUrlId);
            paymentOrder.setPaymentLinkId(paymentUrl);
            paymentRepository.save(paymentOrder);
        } else{
            String paymentLink = createStripePaymentLink(userDTO,amount,paymentOrder.getId());
            paymentLinkResponse.setPayment_link_url(paymentLink);
            paymentOrder.setPaymentLinkId(paymentLink);
            paymentRepository.save(paymentOrder);
        }

        return paymentLinkResponse;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception {
        Optional<PaymentOrder> paymentOrder = paymentRepository.findById(id);
        if(paymentOrder.isEmpty()){
            throw new Exception("Order is not found with given id "+id);
        }
        return paymentOrder.get();
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
        PaymentOrder paymentOrder = paymentRepository.findByPaymentLinkId(paymentId);
        return paymentOrder;
    }

    @Override
    public PaymentLink createRazorpayPaymentLink(UserDTO userDTO, Long amount, Long orderId) throws RazorpayException {
        Long amountPay = amount*100;
        RazorpayClient razorpayClient = new RazorpayClient(razorpayApiKey,razorpayApiSecret);
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amountPay);
        paymentLinkRequest.put("currency","INR");
        JSONObject customer = new JSONObject();
        customer.put("name",userDTO.getFullName());
        customer.put("email",userDTO.getFullName());
        JSONObject notify = new JSONObject();
        notify.put("email",true);
        paymentLinkRequest.put("customer",customer);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        paymentLinkRequest.put("callback_url","http://localhost:3000/payment-success/"+orderId);
        paymentLinkRequest.put("callback_method","get");

        PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);
        return paymentLink;
    }

    @Override
    public String createStripePaymentLink(UserDTO userDTO, Long amount, Long orderId) throws StripeException {
        Stripe.apiKey = stripeApiKey;
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment-success/"+orderId)
                .setCancelUrl("http://localhost:3000/payment/cancel")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount(amount*100)
                                .setProductData(
                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                .setName("Salon appointment")
                                                .build()
                                ).build()
                        ).build()
                ).build();
        Session session = Session.create(params);
        return session.getUrl();
    }

    @Override
    public Boolean proceedPayment(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException {
        if(paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)){
            if(paymentOrder.getPaymentMethod().equals(PaymentMethod.RAZORPAY)){
                RazorpayClient razorpayClient = new RazorpayClient(razorpayApiKey,razorpayApiSecret);
                Payment payment = razorpayClient.payments.fetch(paymentId);
                Integer amount = payment.get("amount");
                String status = payment.get("status");
                if(status.equals("captured")){
                    paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                    paymentRepository.save(paymentOrder);
                    return  true;
                }
            } else {
                paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                paymentRepository.save(paymentOrder);
                return  true;
            }
        }
        return false;
    }
}
