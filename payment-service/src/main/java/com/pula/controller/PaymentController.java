package com.pula.controller;

import com.pula.domain.PaymentMethod;
import com.pula.model.PaymentOrder;
import com.pula.payload.dto.BookingDTO;
import com.pula.payload.dto.UserDTO;
import com.pula.payload.response.PaymentLinkResponse;
import com.pula.service.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/msapi/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@RequestBody BookingDTO bookingDTO, @RequestParam PaymentMethod paymentMethod) throws StripeException, RazorpayException {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFullName("ashok");
        userDTO.setEmail("ashok@gmail.com");
        PaymentLinkResponse paymentLinkResponse = paymentService.createOrder(userDTO,bookingDTO,paymentMethod);
        return ResponseEntity.ok(paymentLinkResponse);
    }

    @GetMapping("/{paymentOrderId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(@PathVariable Long paymentOrderId) throws Exception {
        return ResponseEntity.ok().body(paymentService.getPaymentOrderById(paymentOrderId));
    }

    @PatchMapping("/proceed")
    public ResponseEntity<Boolean> proceedPayment(
            @RequestParam String paymentId,
            @RequestParam String paymentLinkId
    ) throws RazorpayException {
        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkId);
        Boolean res = paymentService.proceedPayment(paymentOrder,paymentId,paymentLinkId);
        return ResponseEntity.ok(res);
    }
}
