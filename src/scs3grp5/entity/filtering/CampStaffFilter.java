package scs3grp5.entity.filtering;

import scs3grp5.entity.*;

/**
 * Camp filter based the staff-in-charge of the camp
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CampStaffFilter extends CampFilter {

    /** The only staff to include */
    private String onlyStaffID;

    /** Include only Camp with a specific Staff-in-Charge */
    public static CampStaffFilter onlyBy(Staff staffInCharge) {
        return onlyBy(staffInCharge.getID());
    }

    /** Include only Camp with a specific Staff-in-Charge */
    public static CampStaffFilter onlyBy(String staffInChargeID) {
        CampStaffFilter filter = new CampStaffFilter();
        filter.onlyStaffID = staffInChargeID;
        return filter;
    }

    /** {@inheritDoc} */
    @Override
    public boolean pass(Camp camp) {
        // Staff-in-Charge Filtering
        if (!camp.getStaffInCharge().getID().equals(onlyStaffID))
            return false;
        return true;
    }
}
