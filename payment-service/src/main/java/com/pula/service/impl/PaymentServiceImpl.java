package com.pula.service.impl;

import com.pula.domain.PaymentMethod;
import com.pula.model.PaymentOrder;
import com.pula.payload.dto.BookingDTO;
import com.pula.payload.dto.UserDTO;
import com.pula.payload.response.PaymentLinkResponse;
import com.pula.repository.PaymentRepository;
import com.pula.service.PaymentService;
import com.razorpay.PaymentLink;
import lombok.RequiredArgsConstructor;
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

    private PaymentRepository paymentRepository;

    @Override
    public PaymentLinkResponse createOrder(UserDTO userDTO, BookingDTO bookingDTO, PaymentMethod paymentMethod) {
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
    public PaymentLink createRazorpayPaymentLink(UserDTO userDTO, Long amount, Long orderId) {
        return null;
    }

    @Override
    public String createStripePaymentLink(UserDTO userDTO, Long amount, Long orderId) {
        return "";
    }
}
