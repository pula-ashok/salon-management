package com.pula.payload.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDTO {

    private Long id;

    private Long salonId;

    private Long CustomerId;

    private Set<Long> serviceIds;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private  int totalPrice;

//    private BookingStatus status = BookingStatus.PENDING;
}