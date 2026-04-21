package com.pula.model;

import com.pula.domain.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long salonId;

    private Long CustomerId;

    @ElementCollection
    private Set<Long> serviceIds;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private  int totalPrice;

    private BookingStatus status = BookingStatus.PENDING;

}
