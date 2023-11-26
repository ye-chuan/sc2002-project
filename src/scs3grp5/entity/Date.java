package scs3grp5.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A Date object used to represent a date.
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class Date implements Comparable<Date>, Serializable {
    /** The latest date supported by this class */
    public static final Date MAX = new Date(LocalDate.MAX.getYear(), LocalDate.MAX.getMonthValue(), LocalDate.MAX.getDayOfMonth());
    /** The earliest date supported by this class */
    public static final Date MIN = new Date(LocalDate.MIN.getYear(), LocalDate.MIN.getMonthValue(), LocalDate.MIN.getDayOfMonth());

    /** The format string used to format output from this class */
    private static DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    /** The format string used to format input from this class */
    private static DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern("d/M/[uuuu][uu]");
    /** The current implementation is a wrapper around LocalDate */
    private LocalDate localDate;

	/**
	 * 
	 * @param year
	 * @param month
	 * @param dayOfMonth
     *
     * @throws java.time.DateTimeException When given arguments cannot form a valid date
	 */
	public Date(int year, int month, int dayOfMonth) {
        this.localDate = LocalDate.of(year, month, dayOfMonth);
	}

    /**
     * @return Today's date based on system's time
     */
    public static Date today() {
        LocalDate tdyLocalDate = LocalDate.now();
        return new Date(tdyLocalDate.getYear(), tdyLocalDate.getMonthValue(), tdyLocalDate.getDayOfMonth());
    }

    /**
     * @param year The year
     * @param month The month
     * @param dayOfMonth The day
     * @return If the given data is valid
     */
    public static boolean isValidDate(int year, int month, int dayOfMonth) {
        try {
            new Date(year, month, dayOfMonth);    
        }   
        catch(java.time.DateTimeException e) {
            return false;
        }
        return true;
    }

    /**
     * @param dateString The formatted string dd/mm/yyyy
     * @return If the given data is valid
     */
    public static boolean isValidDate(String datestring) {
        try {
            Date.fromString(datestring);    
        }   
        catch(DateTimeParseException e) {
            return false;
        }
        return true;
    }


    /** 
     * Parse Date from format D/M/YY or YYYY
     * @return Parsed date
     * @throws java.time.format.DateTimeParseException When the given string is invalid for parsing
     */
    public static Date fromString(String datestring) throws DateTimeParseException {
        LocalDate parsedLocalDate = LocalDate.parse(datestring, inFormatter);
        return new Date(parsedLocalDate.getYear(), parsedLocalDate.getMonthValue(), parsedLocalDate.getDayOfMonth());
    }

    /**
     * @return The calendar day of this date
     */
	public int getDayOfMonth() {
        return localDate.getDayOfMonth();
	}

    /**
     * @return The calendar month of this date
     */
	public int getMonth() {
        return localDate.getMonthValue();
	}

    /**
     * @return The calendar year of this date
     */
	public int getYear() {
        return localDate.getYear();
	}

	/**
	 * Gives in the Local DD/MM/YYYY format
     * @return This date in the Local DD/MM/YYYY format
	 */
	public String toString() {
        return localDate.format(outFormatter);
	}

	/**
     * Check if this date is after the other date
	 * @param other The other date
	 */
	public boolean isAfter(Date other) {
        return localDate.isAfter(other.localDate);
	}

	/**
     * Check if this date is before the other date
	 * @param other The other date
	 */
	public boolean isBefore(Date other) {
        return localDate.isBefore(other.localDate);
	}

	/**
     * Check if this date is on the same day as the other date
	 * @param other The other date
	 */
	public boolean equals(Date other) {
        return localDate.equals(other.localDate);
	}
    
    /** {@inheritDocs} */
    @Override
    public int compareTo(Date other) {
        return localDate.compareTo(other.localDate);
    }
}
