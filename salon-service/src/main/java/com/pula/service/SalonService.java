package com.pula.service;

import com.pula.model.Salon;
import com.pula.payload.dto.SalonDTO;
import com.pula.payload.dto.UserDTO;

import java.util.List;

public interface SalonService {

    Salon creatSalon(SalonDTO salonDTO, UserDTO userDTO);
    Salon updateSalon(SalonDTO salonDTO,UserDTO userDTO,Long id);
    List<Salon> getAllSalons();
    Salon getSalonById(Long salonId);
    Salon getSalonByOwnerId(Long ownerId);
    List<Salon> searchSalonByCityName(String city);
}
