package scs3grp5.entity.filtering;

import scs3grp5.entity.*;

/**
 * Camp filter based on number of participant slots (or free slots)
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CampCampCommSlotsFilter extends CampFilter {

    /** Min. number of Camp Committee slots to include */
    private int freeCampCommSlotsMin = 0;
    /** Max. number of Camp Committee slots to include */
    private int freeCampCommSlotsMax = Integer.MAX_VALUE;

    /** Effectively include only Camps with free Camp Committee Slots */
    public static CampCampCommSlotsFilter excludeFull() {
        CampCampCommSlotsFilter filter = new CampCampCommSlotsFilter();
        filter.freeCampCommSlotsMin = 1;
        return filter;
    }

    /** {@inheritDoc} */
    @Override
    public boolean pass(Camp camp) {
        int freeCampCommSlots = camp.getCampCommSlots() - camp.getNumCampComm();
        assert freeCampCommSlots >= 0;
        if (freeCampCommSlots < freeCampCommSlotsMin || freeCampCommSlots > freeCampCommSlotsMax)
            return false;
        return true;
    }

}
