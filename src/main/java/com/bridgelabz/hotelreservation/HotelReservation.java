package com.bridgelabz.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class HotelReservation {
    private List<Hotels> hotelList;

    public HotelReservation() {
        this.hotelList = new ArrayList<Hotels>();
    }

    /**
     * @param hotel add  used to add hotels to list
     */
    public void addHotel(Hotels hotel) {
        this.hotelList.add(hotel);
    }

    /*
     * get list of hotels
     * */
    public List<Hotels> getHotels() {
        return this.hotelList;
    }

    public Map<Hotels, Integer> searchFor(String date1, String date2) {
        int totalDays = totalNumberOfDays(date1, date2);
        int weekDays = totalNumberOfWeekDays(date1, date2);
        int weekendDays = totalDays - weekDays;
        return getCheapestHotels(weekDays, weekendDays);
    }

    /**
     * get cheapest hotel
     */
    public Map<Hotels, Integer> getCheapestHotels(int weekDays, int weekendDays) {
        Map<Hotels, Integer> hotelCosts = new HashMap<>();
        Map<Hotels, Integer> cheapestHotel = new HashMap<>();
        if (hotelList.size() == 0)
            return null;
        this.hotelList.stream().forEach(
                n -> hotelCosts.put(n, (n.getRegularWeekdayRate() * weekDays + n.getRegularWeekendRate() * weekendDays)));
        Integer cheap = hotelCosts.values().stream().min(Integer::compare).get();
        hotelCosts.forEach((k, v) -> {
            if (v == cheap)
                cheapestHotel.put(k, v);
        });

        return cheapestHotel;
    }


    /**
     * count total number of days
     *
     * @param date1 is start date
     * @param date2 is end date
     */
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

    public Map<Hotels, Integer> getCheapestAndBestRatedHotels(String date1, String date2) {
        Map<Hotels, Integer> bestHotels = new HashMap<Hotels, Integer>();
        Map<Hotels, Integer> cheapestHotels = searchFor(date1, date2);
        int highestRating = (cheapestHotels.keySet().stream().max(Comparator.comparingInt(Hotels::getRating)).get())
                .getRating();
        cheapestHotels.forEach((k, v) -> {
            if (k.getRating() == highestRating)
                bestHotels.put(k, k.getRating());
        });
        return bestHotels;
    }


}
