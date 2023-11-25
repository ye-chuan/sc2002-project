package scs3grp5.entity.filtering;

import scs3grp5.entity.*;

public class CampParticipantSlotsFilter extends CampFilter {

    private int freeParticipantSlotsMin = 0;
    private int freeParticipantSlotsMax = Integer.MAX_VALUE;

    /** Effectively include only Camps with free Participant Slots */
    public static CampParticipantSlotsFilter excludeFull() {
        CampParticipantSlotsFilter filter = new CampParticipantSlotsFilter();
        filter.freeParticipantSlotsMin = 1;
        return filter;
    }

    public boolean pass(Camp camp) {
        int freeParticipantSlots = camp.getParticipantSlots() - camp.getNumParticipants();
        assert freeParticipantSlots >= 0;
        if (freeParticipantSlots < freeParticipantSlotsMin || freeParticipantSlots > freeParticipantSlotsMax)
            return false;
        return true;
    }
}
