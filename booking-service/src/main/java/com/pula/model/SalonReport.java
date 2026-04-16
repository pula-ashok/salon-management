package com.pula.model;

import lombok.Data;

@Data
public class SalonReport {

    private Long salonId;

    private  double totalEarnings;

    private Integer totalBookings;

    private Integer cancelledBookings;

    private double totalRefunds;
}
