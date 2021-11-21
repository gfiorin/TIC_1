package com.example.AppPrototipo.business.managers;

import com.example.AppPrototipo.business.entities.Booking;
import com.example.AppPrototipo.persistence.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingMgr {
    private final BookingRepository bookingRepository;

    public BookingMgr(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> findAll(){
        return (List<Booking>) bookingRepository.findAll();
    }
}
