package com.pula.service;

import com.pula.dto.CategoryDTO;
import com.pula.dto.SalonDTO;
import com.pula.dto.ServiceOfferingDTO;
import com.pula.model.ServiceOffering;

import java.util.Set;

public interface ServiceOfferingService {

    ServiceOffering createService(ServiceOfferingDTO serviceOfferingDTO, SalonDTO salonDTO, CategoryDTO categoryDTO);
    ServiceOffering updateService(Long serviceId,ServiceOffering serviceOffering) throws Exception;
    Set<ServiceOffering> getAllServiceBySalonId(Long salonId,Long categoryId);
    Set<ServiceOffering> getServicesByIds(Set<Long> ids);
    ServiceOffering getServiceById(Long serviceId) throws Exception;
}
