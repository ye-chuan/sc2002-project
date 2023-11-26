package scs3grp5.entity;

import java.io.Serializable;

/**
 * A representation for a range of dates
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class DateRange implements Serializable {
    /** The start date of this range */
    private Date start;
    /** The end date of this range */
    private Date end;

    /**
     * Creates a DateRange object that represents a continuous range of dates
     *
     * @param start The starting date of this range (inclusive)
     * @param end The ending date of this range (inclusive)
     */
    public DateRange(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @return The starting date of this range (inclusive)
     */
    public Date getStart() {
		return this.start;
	}

    /**
     * @return The ending date of this range (inclusive)
     */
    public Date getEnd() {
		return this.end;
	}

    
    /**
     * Checks if 2 date ranges overlaps each other at all.
     *
     * @param other The other date to check against
     * @return Whether the 2 dates overlap
     */
    public boolean overlaps(DateRange other) {
        // start1 --------------------- end1
        //          start2 ----------------- end2
        // The only time overlap won't happen is if the one of the ranges starts after the other ends
        // So !(start1 > end2 || start2 > end1)
        
        return !(this.start.isAfter(other.end) || other.start.isAfter(this.end));
    }

    @Override
    public String toString() {
        return "DateRange(" + this.start + ", " + this.end + ")";
    }

}
