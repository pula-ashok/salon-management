package com.pula.controller;

import com.pula.dto.CategoryDTO;
import com.pula.dto.SalonDTO;
import com.pula.dto.ServiceOfferingDTO;
import com.pula.model.ServiceOffering;
import com.pula.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/msapi/service-offering/salon-owner")
@RequiredArgsConstructor
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @PostMapping
    public ResponseEntity<ServiceOffering> createService(@RequestBody ServiceOfferingDTO serviceOfferingDTO){
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        CategoryDTO  categoryDTO = new CategoryDTO();
        categoryDTO.setId(serviceOfferingDTO.getCategoryId());
        ServiceOffering serviceOffering = serviceOfferingService.createService(serviceOfferingDTO,salonDTO,categoryDTO);
        return ResponseEntity.ok().body(serviceOffering);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateService(@PathVariable Long id,@RequestBody ServiceOffering serviceOffering) throws Exception {
            ServiceOffering serviceOffering1 = serviceOfferingService.updateService(id,serviceOffering);
            return ResponseEntity.ok().body(serviceOffering1);
    }
}
