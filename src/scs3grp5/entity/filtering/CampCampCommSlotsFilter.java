package scs3grp5.entity.filtering;

import scs3grp5.entity.*;
import scs3grp5.Main;

/**
 * Camp filter based on number of participant slots (or free slots)
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CampCampCommSlotsFilter extends CampFilter {
    /** The membershipDB witht he membership information of this camp */
    private CampMembershipDatabase membershipDB;
    /** Min. number of Camp Committee slots to include */
    private int freeCampCommSlotsMin = 0;
    /** Max. number of Camp Committee slots to include */
    private int freeCampCommSlotsMax = Integer.MAX_VALUE;

    /** 
     * Effectively include only Camps with free Camp Committee Slots
     * @param membershipDB The membershipDB witht he membership information of this camp
     * @return The {@code CampCampCommSlotsFilter} with this filter option
     */
    public static CampCampCommSlotsFilter excludeFull(CampMembershipDatabase membershipDB) {
        CampCampCommSlotsFilter filter = new CampCampCommSlotsFilter();
        filter.membershipDB = membershipDB;
        filter.freeCampCommSlotsMin = 1;
        return filter;
    }

    /** {@inheritDoc} */
    @Override
    public boolean pass(Camp camp) {
        int freeCampCommSlots = camp.getCampCommSlots() - membershipDB.getCampCommSize(camp);
        assert freeCampCommSlots >= 0;
        if (freeCampCommSlots < freeCampCommSlotsMin || freeCampCommSlots > freeCampCommSlotsMax)
            return false;
        return true;
    }

}
