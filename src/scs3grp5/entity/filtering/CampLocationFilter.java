package scs3grp5.entity.filtering;

import scs3grp5.entity.*;

/**
 * Camp filter for the Location that Camp held at
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CampLocationFilter extends CampFilter {
    /** The only camp location to include */
    private String onlyLocation;

    /**
     * Include only Camp with at a specific location
     * @param location The location that the camp has to be held at
     * @return The {@code CampCampCommSlotsFilter} with this filter option
     */
    public static CampLocationFilter onlyAt(String location) {
        CampLocationFilter filter = new CampLocationFilter();
        filter.onlyLocation = location;
        return filter;
    }

    /** {@inheritDoc} */
    @Override
    public boolean pass(Camp camp) {
        // Staff-in-Charge Filtering
        if (!camp.getLocation().toLowerCase().equals(onlyLocation.toLowerCase()))
            return false;
        return true;
    }

}
