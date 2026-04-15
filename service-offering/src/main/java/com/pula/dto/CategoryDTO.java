package com.pula.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class CategoryDTO {

    private Long id;

    private String name;

    private String image;

    private Long salonId;

}
