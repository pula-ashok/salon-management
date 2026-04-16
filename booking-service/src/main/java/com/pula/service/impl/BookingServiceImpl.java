package com.pula.service.impl;

import com.pula.domain.BookingStatus;
import com.pula.dto.BookingRequest;
import com.pula.dto.SalonDTO;
import com.pula.dto.ServiceOfferingDTO;
import com.pula.dto.UserDTO;
import com.pula.model.Booking;
import com.pula.model.SalonReport;
import com.pula.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class BookingServiceImpl  implements BookingService {
    @Override
    public Booking createBooking(BookingRequest booking, UserDTO userDTO, SalonDTO salonDTO, Set<ServiceOfferingDTO> serviceDTOSet) {
        return null;
    }

    @Override
    public List<Booking> getBookingsByCustomerId(Long customerId) {
        return List.of();
    }

    @Override
    public List<Booking> getBookingsBySalonId(Long salonId) {
        return List.of();
    }

    @Override
    public Booking getBookingById(Long id) {
        return null;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) {
        return null;
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, Long salonId) {
        return List.of();
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {
        return null;
    }
}
