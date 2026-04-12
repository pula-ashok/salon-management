package com.pula.controller;

import com.pula.mapper.SalonMapper;
import com.pula.model.Salon;
import com.pula.payload.dto.SalonDTO;
import com.pula.payload.dto.UserDTO;
import com.pula.service.SalonService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/msapi/salons")
public class SalonController {

    @Autowired
    private SalonService salonService;

    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.creatSalon(salonDTO,userDTO);
        SalonDTO salonDTO1 = SalonMapper.mapToDTO(salon);
        return  ResponseEntity.ok().body(salonDTO1);
    }

    @PatchMapping("/{salonId}")
    public ResponseEntity<SalonDTO> updateSalon(@PathVariable Long salonId,@RequestBody SalonDTO salonDTO) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.updateSalon(salonDTO,userDTO,salonId);
        SalonDTO salonDTO1 = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok().body(salonDTO1);
    }

    @GetMapping
    public ResponseEntity<List<SalonDTO>> getAllSalons(){
        List<Salon> salons = salonService.getAllSalons();
        List<SalonDTO> salonDTOS = salons.stream().map(
                salon ->{
                    SalonDTO salonDTO = SalonMapper.mapToDTO(salon);
                    return salonDTO;
                }
        ).toList();
        return ResponseEntity.ok().body(salonDTOS);
    }

    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable Long salonId) throws Exception {
        Salon salon = salonService.getSalonById(salonId);
        SalonDTO salonDTO = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> getSalonsByCity(@RequestParam String city){
        List<Salon> salons = salonService.searchSalonByCityName(city);
        List<SalonDTO> salonDTOS = salons.stream().map(
                salon -> {
                    SalonDTO salonDTO = SalonMapper.mapToDTO(salon);
                    return salonDTO;
                }
        ).toList();
        return ResponseEntity.ok().body(salonDTOS);
    }

    @GetMapping("/owner")
    public ResponseEntity<SalonDTO> getSalonByOwnerId(){
        UserDTO userDTO  = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.getSalonByOwnerId(userDTO.getId());
        SalonDTO salonDTO = SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO);
    }
}
