package scs3grp5.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date implements Comparable<Date>, Serializable {
    public static final Date MAX = new Date(LocalDate.MAX.getYear(), LocalDate.MAX.getMonthValue(), LocalDate.MAX.getDayOfMonth());
    public static final Date MIN = new Date(LocalDate.MIN.getYear(), LocalDate.MIN.getMonthValue(), LocalDate.MIN.getDayOfMonth());

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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
	 * Gives in the Local dd-mm-yyyy format
	 */
	public String toString() {
        return localDate.format(formatter);
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

        System.out.println(a.isAfter(b));
        System.out.println(a.isBefore(b));
        System.out.println(a);
    }
}
