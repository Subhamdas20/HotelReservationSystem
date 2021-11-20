package com.bridgelabz.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelReservation {
    private List<Hotels> hotelList;

    public HotelReservation() {
        this.hotelList = new ArrayList<Hotels>();
    }
/**
 * @param hotel add  used to add hotels to list
 * */
    public void addHotel(Hotels hotel) {
        this.hotelList.add(hotel);
    }
/*
* get list of hotels
* */
    public List<Hotels> getHotels() {
        return this.hotelList;
    }

    public Map<String, Integer> searchFor(String date1, String date2) {
        int totalDays = totalNumberOfDays(date1, date2);
        int weekDays = totalNumberOfWeekDays(date1, date2);
        int weekendDays = totalDays - weekDays;
        return getCheapestHotels(weekDays, weekendDays);
    }
/**
 * get cheapest hotel
 * */
    public Map<String, Integer> getCheapestHotels(int weekDays, int weekendDays)
    {
        Map<String, Integer> hotelCosts = new HashMap<>();
        Map<String, Integer> cheapestHotel = new HashMap<>();
        if (hotelList.size() == 0)
            return null;
        this.hotelList.stream().forEach(
                n -> hotelCosts.put(n.getName(),(n.getRegularWeekdayRate() * weekDays + n.getRegularWeekendRate() * weekendDays)));
        Integer cheap = hotelCosts.values().stream().min(Integer::compare).get();
        hotelCosts.forEach((k, v) -> {
            if (v == cheap)
                cheapestHotel.put(k, v);
        });

        return cheapestHotel;
    }




/**
 * count total number of days
 * @param date1 is start date
 * @param date2 is end date
 * */
    public int totalNumberOfDays(String date1, String date2) {
        LocalDate startDate = toLocalDate(date1);
        LocalDate endDate = toLocalDate(date2);
        int totalDays = Period.between(startDate, endDate).getDays() + 1;
        return totalDays;
    }
/**
 * use to get total number of week days in booking dates
 */
    public int totalNumberOfWeekDays(String date1, String date2) {
        LocalDate startDate = toLocalDate(date1);
        LocalDate endDate = toLocalDate(date2);
        DayOfWeek startDay = startDate.getDayOfWeek();
        DayOfWeek endDay = endDate.getDayOfWeek();
        int days = (int) (ChronoUnit.DAYS.between(startDate, endDate) + 1);
        int daysWithoutWeekends = days - 2 * ((days + startDay.getValue()) / 7);
        int totalWeekDays = (int) daysWithoutWeekends + (startDay == DayOfWeek.SUNDAY ? 1 : 0)
                + (endDay == DayOfWeek.SUNDAY ? 1 : 0);
        return totalWeekDays;
    }

    public LocalDate toLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}
