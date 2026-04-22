package com.pula.service;

import com.pula.domain.PaymentMethod;
import com.pula.model.PaymentOrder;
import com.pula.payload.dto.UserDTO;
import com.pula.payload.response.PaymentLinkResponse;
import com.pula.payload.dto.BookingDTO;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {

    PaymentLinkResponse createOrder(UserDTO userDTO, BookingDTO bookingDTO, PaymentMethod paymentMethod) throws RazorpayException, StripeException;

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    PaymentLink createRazorpayPaymentLink(UserDTO userDTO,Long amount,Long orderId) throws RazorpayException;

    String createStripePaymentLink(UserDTO userDTO,Long amount, Long orderId) throws StripeException;
    
    Boolean proceedPayment(PaymentOrder paymentOrder,String paymentId,String paymentLinkId) throws RazorpayException;
}
