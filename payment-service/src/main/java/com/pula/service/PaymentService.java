package com.pula.service;

import com.pula.domain.PaymentMethod;
import com.pula.model.PaymentOrder;
import com.pula.payload.dto.UserDTO;
import com.pula.payload.response.PaymentLinkResponse;
import com.pula.payload.dto.BookingDTO;
import com.razorpay.PaymentLink;
import org.apache.catalina.User;

public interface PaymentService {

    PaymentLinkResponse createOrder(UserDTO userDTO, BookingDTO bookingDTO, PaymentMethod paymentMethod);

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    PaymentLink createRazorpayPaymentLink(UserDTO userDTO,Long amount,Long orderId);

    String createStripePaymentLink(UserDTO userDTO,Long amount, Long orderId);
}
