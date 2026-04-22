package com.pula.service.impl;

import com.pula.domain.PaymentMethod;
import com.pula.model.PaymentOrder;
import com.pula.payload.dto.BookingDTO;
import com.pula.payload.dto.UserDTO;
import com.pula.payload.response.PaymentLinkResponse;
import com.pula.service.PaymentService;
import com.razorpay.PaymentLink;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Override
    public PaymentLinkResponse createOrder(UserDTO userDTO, BookingDTO bookingDTO, PaymentMethod paymentMethod) {
        return null;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) {
        return null;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(Long paymentId) {
        return null;
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
