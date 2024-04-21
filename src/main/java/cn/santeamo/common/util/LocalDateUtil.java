package cn.santeamo.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * LocalDate LocalDateTime 工具类
 *
 * @author shenle
 */
public class LocalDateUtil {

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");
    public static final DateTimeFormatter DATE_NO_LINE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter DATETIME_NO_LINE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final ZoneOffset DEFAULT_ZONE_OFFSET = OffsetTime.now().getOffset();

    public static String formatDateTime(TemporalAccessor temporal) {
        return DATETIME_FORMATTER.format(temporal);
    }

    public static String formatDate(TemporalAccessor temporal) {
        return DATE_FORMATTER.format(temporal);
    }

    public static String formatDateNoLine(TemporalAccessor temporal) {
        return DATE_NO_LINE_FORMATTER.format(temporal);
    }

    public static String formatDateTimeNoLine(TemporalAccessor temporal) {
        return DATETIME_NO_LINE_FORMATTER.format(temporal);
    }

    public static String formatTime(TemporalAccessor temporal) {
        return TIME_FORMATTER.format(temporal);
    }

    public static String formatMonth(TemporalAccessor temporal) {
        return MONTH_FORMATTER.format(temporal);
    }

    public static String format(TemporalAccessor temporal, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(temporal);
    }

    public static LocalDateTime parseDateTime(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return parseDateTime(dateStr, formatter);
    }

    public static LocalDateTime parseDateTime(String dateStr, DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateStr, formatter);
    }

    public static LocalDateTime parseDateTime(String dateStr) {
        return parseDateTime(dateStr, DATETIME_FORMATTER);
    }

    public static LocalDate parseDate(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return parseDate(dateStr, formatter);
    }

    public static LocalDate parseDate(String dateStr, DateTimeFormatter formatter) {
        return LocalDate.parse(dateStr, formatter);
    }

    public static LocalDate parseDate(String dateStr) {
        return parseDate(dateStr, DATE_FORMATTER);
    }

    public static LocalTime parseTime(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return parseTime(dateStr, formatter);
    }

    public static LocalTime parseTime(String dateStr, DateTimeFormatter formatter) {
        return LocalTime.parse(dateStr, formatter);
    }

    public static LocalTime parseTime(String dateStr) {
        return parseTime(dateStr, TIME_FORMATTER);
    }

    public static long toEpochSecond(LocalDateTime time) {
        return time.toEpochSecond(DEFAULT_ZONE_OFFSET);
    }

    public static LocalDateTime epochSecondToLocalDateTime(long epochSecond) {
        return epochSecondToLocalDateTime(epochSecond, 0);
    }

    public static LocalDateTime epochSecondToLocalDateTime(long epochSecond, int nanoOfSecond) {
        return LocalDateTime.ofEpochSecond(epochSecond, nanoOfSecond, DEFAULT_ZONE_OFFSET);
    }

    public static LocalDate epochSecondToLocalDate(long epochSecond) {
        return epochSecondToLocalDate(epochSecond, 0);
    }

    public static LocalDate epochSecondToLocalDate(long epochSecond, int nanoOfSecond) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(epochSecond, nanoOfSecond, DEFAULT_ZONE_OFFSET);
        return localDateTime.toLocalDate();
    }

    public static Instant toInstant(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    public static LocalDateTime toDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static Date toDate(LocalDateTime dateTime) {
        return Date.from(toInstant(dateTime));
    }

    public static Duration between(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive);
    }

    public static Period between(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate);
    }

    public static boolean checkDateIntersect(LocalDate checkStartDate, LocalDate checkEndDate,
                                             LocalDate startDate, LocalDate endDate) {
        return checkDateIntersect(checkStartDate, checkEndDate, startDate, endDate, true);
    }

    public static boolean checkDateIntersect(LocalDate checkStartDate, LocalDate checkEndDate,
                                             LocalDate startDate, LocalDate endDate, boolean allowEqual) {
        if (allowEqual) {
            if (!checkEndDate.isAfter(startDate)) {
                // 检测结束时间比给定起始时间早
                return false;
            } else if (!checkStartDate.isBefore(endDate)) {
                // 检测起始时间比给定结束时间晚，
                return false;
            }
            return true;
        } else {
            if (checkEndDate.isBefore(startDate)) {
                // 检测结束时间比给定起始时间早
                return false;
            } else if (checkStartDate.isAfter(endDate)) {
                // 检测起始时间比给定结束时间晚，
                return false;
            }
            return true;
        }
    }

}
