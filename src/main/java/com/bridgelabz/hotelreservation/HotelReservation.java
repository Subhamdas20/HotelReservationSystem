package com.bridgelabz.hotelreservation;

import java.util.ArrayList;
import java.util.List;

public class HotelReservation {
    private List<Hotels> hotelList;

    public HotelReservation()
    {
        this.hotelList = new ArrayList<Hotels>();
    }

    public void addHotel(Hotels hotel)
    {
        this.hotelList.add(hotel);
    }
    public List<Hotels> getHotels()
    {
        return this.hotelList;
    }
}
