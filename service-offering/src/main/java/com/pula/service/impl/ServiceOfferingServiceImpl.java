package com.pula.service.impl;

import com.pula.dto.CategoryDTO;
import com.pula.dto.SalonDTO;
import com.pula.dto.ServiceOfferingDTO;
import com.pula.model.ServiceOffering;
import com.pula.repository.ServiceOfferingRepository;
import com.pula.service.ServiceOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    @Autowired
    private ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createService(ServiceOfferingDTO serviceOfferingDTO, SalonDTO salonDTO, CategoryDTO categoryDTO) {
        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setName(serviceOfferingDTO.getName());
        serviceOffering.setDescription(serviceOfferingDTO.getDescription());
        serviceOffering.setPrice(serviceOfferingDTO.getPrice());
        serviceOffering.setDuration(serviceOfferingDTO.getDuration());
        serviceOffering.setSalonId(salonDTO.getId());
        serviceOffering.setCategoryId(categoryDTO.getId());
        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering serviceOffering) throws Exception {
        Optional<ServiceOffering> serviceOffering1 = serviceOfferingRepository.findById(serviceId);
        if(serviceOffering1.isEmpty()){
            throw new Exception("Service is not found with given id "+serviceId);
        }
        ServiceOffering serviceOffering2 = serviceOffering1.get();
        serviceOffering2.setName(serviceOffering.getName());
        serviceOffering2.setDescription(serviceOffering.getDescription());
        serviceOffering2.setPrice(serviceOffering.getPrice());
        serviceOffering2.setDuration(serviceOffering.getDuration());
        return serviceOfferingRepository.save(serviceOffering2);
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId) {
        Set<ServiceOffering> serviceOfferings = serviceOfferingRepository.findBySalonId(salonId);
        if(categoryId != null){
            serviceOfferings = serviceOfferings.stream().
                    filter(service->service.getCategoryId() == categoryId).collect(Collectors.toSet());
        }
        return serviceOfferings;
    }

    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> ids) {
        List<ServiceOffering> serviceOfferings = serviceOfferingRepository.findAllById(ids);
        return new HashSet<>(serviceOfferings);
    }

    @Override
    public ServiceOffering getServiceById(Long serviceId) throws Exception {
       Optional<ServiceOffering> serviceOffering = serviceOfferingRepository.findById(serviceId);
       if(serviceOffering.isPresent()){
           return serviceOffering.get();
       }
       throw new Exception("Service not found with given service id "+serviceId);
    }
}
