package com.pula.service;

import com.pula.domain.BookingStatus;
import com.pula.dto.BookingRequest;
import com.pula.dto.SalonDTO;
import com.pula.dto.ServiceOfferingDTO;
import com.pula.dto.UserDTO;
import com.pula.model.Booking;
import com.pula.model.SalonReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {

    Booking createBooking(BookingRequest booking, UserDTO userDTO, SalonDTO salonDTO, Set<ServiceOfferingDTO> serviceDTOSet) throws Exception;
    List<Booking> getBookingsByCustomerId(Long customerId);
    List<Booking> getBookingsBySalonId(Long salonId);
    Booking getBookingById(Long id) throws Exception;
    Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;
    List<Booking> getBookingsByDate(LocalDate date,Long salonId);
    SalonReport getSalonReport(Long salonId);
}
