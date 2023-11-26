package scs3grp5.entity.filtering;

import scs3grp5.entity.*;

/**
 * Camp filter based on number of participant slots (or free slots)
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CampParticipantSlotsFilter extends CampFilter {
    /** The membershipDB witht he membership information of this camp */
    private CampMembershipDatabase membershipDB;
    /** Min. number of free participants slots to include */
    private int freeParticipantSlotsMin = 0;
    /** Max. number of free participants slots to include */
    private int freeParticipantSlotsMax = Integer.MAX_VALUE;

    /**
     * Effectively include only Camps with free Participant Slots
     * @param membershipDB The membershipDB witht he membership information of this camp
     * @return The {@code CampParticipantSlotsFilter} with this filter option
     */
    public static CampParticipantSlotsFilter excludeFull(CampMembershipDatabase membershipDB) {
        CampParticipantSlotsFilter filter = new CampParticipantSlotsFilter();
        filter.membershipDB = membershipDB;
        filter.freeParticipantSlotsMin = 1;
        return filter;
    }

    /** {@inheritDoc} */
    @Override
    public boolean pass(Camp camp) {
        int freeParticipantSlots = camp.getParticipantSlots() - membershipDB.getParticipantSize(camp);
        assert freeParticipantSlots >= 0;
        if (freeParticipantSlots < freeParticipantSlotsMin || freeParticipantSlots > freeParticipantSlotsMax)
            return false;
        return true;
    }
}
