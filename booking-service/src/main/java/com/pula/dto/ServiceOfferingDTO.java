package com.pula.dto;

import lombok.Data;

@Data
public class ServiceOfferingDTO {

    private Long id;

    private String name;

    private String description;

    private int price;

    private int duration;

    private long salonId;

    private long categoryId;

    private String image;

}
