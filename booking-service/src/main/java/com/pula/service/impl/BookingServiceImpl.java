package com.pula.service.impl;

import com.pula.domain.BookingStatus;
import com.pula.dto.BookingRequest;
import com.pula.dto.SalonDTO;
import com.pula.dto.ServiceOfferingDTO;
import com.pula.dto.UserDTO;
import com.pula.model.Booking;
import com.pula.model.SalonReport;
import com.pula.repository.BookingRepository;
import com.pula.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl  implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest booking, UserDTO userDTO, SalonDTO salonDTO, Set<ServiceOfferingDTO> serviceDTOSet) throws Exception {
        int totalDuration = serviceDTOSet.stream()
                .mapToInt(ServiceOfferingDTO::getDuration)
                .sum();
        LocalDateTime bookingStartTime = booking.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);
        Boolean isSlotAvailable = isTimeSlotAvailable(salonDTO,bookingStartTime,bookingEndTime);
        int totalPrice = serviceDTOSet.stream().mapToInt(ServiceOfferingDTO::getPrice).sum();
        Set<Long> idList = serviceDTOSet.stream().map(ServiceOfferingDTO::getId).collect(Collectors.toSet());
        Booking newBooking = new Booking();
        newBooking.setCustomerId(userDTO.getId());
        newBooking.setServiceIds(idList);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartDate(bookingStartTime);
        newBooking.setEndDate(bookingEndTime);
        newBooking.setTotalPrice(totalPrice);
        return bookingRepository.save(newBooking);
    }

    public Boolean isTimeSlotAvailable(SalonDTO salonDTO,LocalDateTime bookingStartTime,LocalDateTime bookingEndTime) throws Exception {
        List<Booking> existingBookings = getBookingsBySalonId(salonDTO.getId());
        LocalDateTime salonOpenTime =  salonDTO.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime = salonDTO.getCloseTime().atDate(bookingStartTime.toLocalDate());
        if(salonOpenTime.isBefore(bookingStartTime) || salonCloseTime.isAfter(bookingEndTime)){
            throw new Exception("Booking must be within salon's working hours");
        }
        for(Booking booking :existingBookings){
            LocalDateTime existingBookingStartTime = booking.getStartDate();
            LocalDateTime existingBookingEndTime =  booking.getEndDate();
            if(bookingStartTime.isBefore(existingBookingEndTime) && bookingEndTime.isAfter(existingBookingStartTime)){
                throw new Exception("Slot is not available ,choose different time");
            }
            if(bookingStartTime.isEqual(existingBookingStartTime) || bookingEndTime.isEqual(existingBookingEndTime)){
                throw new Exception("Slot is not available , choose different time");
            }
        }
        return true;
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
