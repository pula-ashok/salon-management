package com.pula.controller;

import com.pula.domain.BookingStatus;
import com.pula.dto.*;
import com.pula.mapper.BookingMapper;
import com.pula.model.Booking;
import com.pula.model.SalonReport;
import com.pula.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/msapi/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestParam long salonId, @RequestBody BookingRequest bookingRequest) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(salonId);
        salonDTO.setOpenTime(LocalTime.now());
        salonDTO.setCloseTime(LocalTime.now().plusHours(4));
        Set<ServiceOfferingDTO> serviceOfferingDTOS = new HashSet<>();
        ServiceOfferingDTO serviceOfferingDTO = new ServiceOfferingDTO();
        serviceOfferingDTO.setId(1L);
        serviceOfferingDTO.setPrice(399);
        serviceOfferingDTO.setDuration(45);
        serviceOfferingDTO.setName("Hair cut for men");
        serviceOfferingDTOS.add(serviceOfferingDTO);
        Booking booking = bookingService.createBooking(bookingRequest,userDTO,salonDTO,serviceOfferingDTOS);

        return ResponseEntity.ok().body(booking);

    }

    @GetMapping("/customer")
    public ResponseEntity<List<BookingDTO>> getBookingByCustomerId(){
        List<Booking> bookings = bookingService.getBookingsByCustomerId(1L);
        List<BookingDTO> bookingDTOS = BookingMapper.getBookingDTOs(bookings);
        return ResponseEntity.ok(bookingDTOS);
    }

    @GetMapping("/salon")
    public ResponseEntity<List<BookingDTO>> getBookingBySalonId(){
        List<Booking> bookings = bookingService.getBookingsBySalonId(1L);
        return ResponseEntity.ok().body(BookingMapper.getBookingDTOs(bookings));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) throws Exception {
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok().body(BookingMapper.toDTO(booking));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long bookingId, @RequestParam BookingStatus status) throws Exception {
        Booking booking = bookingService.updateBooking(bookingId,status);
        return ResponseEntity.ok().body(BookingMapper.toDTO(booking));
    }

    @GetMapping("/slots/salon/{salonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDTO>> getBookingSlots(@PathVariable Long salonId, @RequestParam(required = false)LocalDate date){
        List<Booking> bookings = bookingService.getBookingsByDate(date,salonId);
        List<BookingSlotDTO> bookingSlotDTOS = bookings.stream()
                .map(booking -> {
                    BookingSlotDTO bookingSlotDTO = new BookingSlotDTO();
                    bookingSlotDTO.setStartTime(booking.getStartDate());
                    bookingSlotDTO.setEndTime(booking.getEndDate());
                    return bookingSlotDTO;
                }).collect(Collectors.toList());
        return ResponseEntity.ok(bookingSlotDTOS);
    }

    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport(){
        SalonReport salonReport = bookingService.getSalonReport(1L);
        return ResponseEntity.ok().body(salonReport);
    }
}
