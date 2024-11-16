package com.fwp.study.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class DateUtils {

    public static Date localDateToDate(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static void main(String[] args) {
        System.out.println("LocaleDateToDate = " + localDateToDate(LocalDate.of(2020, 1, 1)));
        System.out.println("localDateTimeToDate = " + localDateTimeToDate(LocalDateTime.of(2020, 1, 1, 1, 1, 1)));
        System.out.println("dateToLocalDate = " + dateToLocalDate(new Date()));
        System.out.println("dateToLocalDateTime = " + dateToLocalDateTime(new Date()));
    }
}
