package com.pula.service.impl;

import com.pula.model.Salon;
import com.pula.payload.dto.SalonDTO;
import com.pula.payload.dto.UserDTO;
import com.pula.repository.SalonRepository;
import com.pula.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public Salon creatSalon(SalonDTO salonDTO, UserDTO userDTO) {
        Salon salon = new Salon();
        salon.setName(salonDTO.getName());
        salon.setAddress(salonDTO.getAddress());
        salon.setEmail(salonDTO.getEmail());
        salon.setCity(salonDTO.getCity());
        salon.setImages(salonDTO.getImages());
        salon.setOwnerId(userDTO.getId());
        salon.setOpenTime(salonDTO.getOpenTime());
        salon.setCloseTime(salonDTO.getCloseTime());
        salon.setPhoneNumber(salonDTO.getPhoneNumber());
        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO salonDTO, UserDTO userDTO, Long id) throws Exception {
        Salon salon = salonRepository.findById(id).orElse(null);
        if(salon !=null && salon.getOwnerId().equals(userDTO.getId())) {
            salon.setName(salonDTO.getName());
            salon.setAddress(salonDTO.getAddress());
            salon.setEmail(salonDTO.getEmail());
            salon.setCity(salonDTO.getCity());
            salon.setImages(salonDTO.getImages());
            salon.setOwnerId(userDTO.getId());
            salon.setOpenTime(salonDTO.getOpenTime());
            salon.setCloseTime(salonDTO.getCloseTime());
            salon.setPhoneNumber(salonDTO.getPhoneNumber());
            return salonRepository.save(salon);
        }
        throw new Exception("Salon not found");
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) throws Exception {
        Optional<Salon> salon = salonRepository.findById(salonId);
        if(salon.isPresent()){
            return  salon.get();
        }
        throw new Exception("Salon not found with given salon id "+salonId);
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCityName(String city) {
        return salonRepository.searchSalons(city);
    }
}
