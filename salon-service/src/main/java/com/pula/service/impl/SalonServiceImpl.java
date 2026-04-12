package com.pula.service.impl;

import com.pula.model.Salon;
import com.pula.payload.dto.SalonDTO;
import com.pula.payload.dto.UserDTO;
import com.pula.service.SalonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalonServiceImpl implements SalonService {
    @Override
    public Salon creatSalon(SalonDTO salonDTO, UserDTO userDTO) {
        return null;
    }

    @Override
    public Salon updateSalon(SalonDTO salonDTO, UserDTO userDTO, Long id) {
        return null;
    }

    @Override
    public List<Salon> getAllSalons() {
        return List.of();
    }

    @Override
    public Salon getSalonById(Long salonId) {
        return null;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return null;
    }

    @Override
    public List<Salon> searchSalonByCityName(String city) {
        return List.of();
    }
}
