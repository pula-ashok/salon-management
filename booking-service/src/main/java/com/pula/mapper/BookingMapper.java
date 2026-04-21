package com.pula.mapper;

import com.pula.dto.BookingDTO;
import com.pula.model.Booking;

import java.util.List;
import java.util.stream.Collectors;

public class BookingMapper {
    public static List<BookingDTO> getBookingDTOs(List<Booking> bookings) {
        return bookings.stream().map(
                BookingMapper::toDTO
        ).collect(Collectors.toList());
    }

    public static BookingDTO toDTO(Booking booking) {
        BookingDTO  bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setCustomerId(booking.getCustomerId());
        bookingDTO.setSalonId(booking.getSalonId());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setServiceIds(booking.getServiceIds());
        bookingDTO.setStartDate(booking.getStartDate());
        bookingDTO.setEndDate(booking.getEndDate());
        bookingDTO.setTotalPrice(booking.getTotalPrice());
        return bookingDTO;
    }
}
