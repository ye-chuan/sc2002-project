package scs3grp5.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date implements Comparable<Date>, Serializable {
    public static final Date MAX = new Date(LocalDate.MAX.getYear(), LocalDate.MAX.getMonthValue(), LocalDate.MAX.getDayOfMonth());
    public static final Date MIN = new Date(LocalDate.MIN.getYear(), LocalDate.MIN.getMonthValue(), LocalDate.MIN.getDayOfMonth());

    private static DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    private static DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern("dd/MM/[uuuu][uu]");
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

    public static Date today() {
        LocalDate tdyLocalDate = LocalDate.now();
        return new Date(tdyLocalDate.getYear(), tdyLocalDate.getMonthValue(), tdyLocalDate.getDayOfMonth());
    }

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
     * Parse Date from format D/M/YY or YYYY
     * @return Parsed date
     * @throws java.time.format.DateTimeParseException When the given string is invalid for parsing
     */
    public static Date fromString(String datestring) throws DateTimeParseException {
        LocalDate parsedLocalDate = LocalDate.parse(datestring, inFormatter);
        return new Date(parsedLocalDate.getYear(), parsedLocalDate.getMonthValue(), parsedLocalDate.getDayOfMonth());
    }

	public int getDayOfMonth() {
        return localDate.getDayOfMonth();
	}

	public int getMonth() {
        return localDate.getMonthValue();
	}

	public int getYear() {
        return localDate.getYear();
	}

	/**
	 * Gives in the Local DD/MM/YYYY format
	 */
	public String toString() {
        return localDate.format(outFormatter);
	}

	/**
	 * 
	 * @param other
	 */
	public boolean isAfter(Date other) {
        return localDate.isAfter(other.localDate);
	}

	/**
	 * 
	 * @param other
	 */
	public boolean isBefore(Date other) {
        return localDate.isBefore(other.localDate);
	}

	/**
	 * 
	 * @param other
	 */
	public boolean equals(Date other) {
        return localDate.equals(other.localDate);
	}
    
    @Override
    public int compareTo(Date other) {
        return localDate.compareTo(other.localDate);
    }


    public static void main(String[] args) {
        Date a = new Date(2023,11,20);
        Date b = new Date(2023,11,21);

        Date test = Date.fromString("26/11/2023");
        System.out.println(test);

        System.out.println(a.isAfter(b));
        System.out.println(a.isBefore(b));
        System.out.println(a);
    }
}
