package com.bridgelabz.testhotelreservation;

import com.bridgelabz.hotelreservation.HotelReservation;
import com.bridgelabz.hotelreservation.Hotels;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestHotelReservation {

    @Test
    public void givenListOfHotelsAndPriceListWhenAddedToListShouldReturnListOfHotels(){
        Hotels hotel1 = new Hotels("Lakeewood", 110, 90, 80, 80, 3);
        Hotels hotel2 = new Hotels("Bridgewood", 160, 60, 110, 50, 4);
        Hotels hotel3 = new Hotels("Ridgewood", 220, 150, 100, 40, 5);
        HotelReservation reserve = new HotelReservation();
        reserve.addHotel(hotel1);
        reserve.addHotel(hotel2);
        reserve.addHotel(hotel3);
        List<Hotels> hotels = Arrays.asList(hotel1,hotel2,hotel3);
        List<Hotels> result = reserve.getHotels();
        Assert.assertEquals(result,hotels);
    }


    @Test
    public void givenListOfHotelsAndPriceListWhenDatesGivenShouldreturnCheapestHotel(){
        Hotels hotel1 = new Hotels("Lakeewood", 110, 90, 80, 80, 3);
        Hotels hotel2 = new Hotels("Bridgewood", 160, 60, 110, 50, 4);
        Hotels hotel3 = new Hotels("Ridgewood", 220, 150, 100, 40, 5);
        HotelReservation reserve = new HotelReservation();
        reserve.addHotel(hotel1);
        reserve.addHotel(hotel2);
        reserve.addHotel(hotel3);
        Map<String, Integer> result = reserve.searchFor("10Sep2020", "11Sep2020");
        result.forEach((k, v) -> System.out.println(k+ " " + v));


    }
}
