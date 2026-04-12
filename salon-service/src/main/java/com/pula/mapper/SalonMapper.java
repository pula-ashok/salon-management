package com.pula.mapper;

import com.pula.model.Salon;
import com.pula.payload.dto.SalonDTO;

import java.time.LocalTime;
import java.util.List;

public class SalonMapper {
    public static SalonDTO mapToDTO(Salon salon) {
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(salon.getId());
        salonDTO.setName(salon.getName());
        salonDTO.setImages(salon.getImages());
        salonDTO.setAddress(salon.getAddress());
        salonDTO.setPhoneNumber(salon.getPhoneNumber());
        salonDTO.setEmail(salon.getEmail());
        salonDTO.setCity(salon.getCity());
        salonDTO.setOwnerId(salon.getOwnerId());
        salonDTO.setOpenTime(salon.getOpenTime());
        salonDTO.setCloseTime(salon.getCloseTime());
        return  salonDTO;
    }
}
