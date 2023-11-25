package scs3grp5.controller;

import scs3grp5.entity.*;

public class DateController {

    /**
	 * 
	 * 
	 */
	public Date toDate(int YYMMDD) {
		int dd = YYMMDD % 100;
		int mm = (YYMMDD % 10000 - dd)/100;
		int yy = (YYMMDD - YYMMDD % 10000)/10000;
		
		
		Date d = new Date(yy,mm,dd);

		return d;
	}

    /**
	 * 
	 * 
	 */
	public int getYY(int YYMMDD) {
		int yy = (YYMMDD - YYMMDD % 10000)/10000;
		
		return yy;
	}

    /**
	 * 
	 * 
	 */
	public int getMM(int YYMMDD) {
		int dd = YYMMDD % 100;
		int mm = (YYMMDD % 10000 - dd)/100;
	
		return mm;
	}

    /**
	 * 
	 * 
	 */
	public int getDD(int YYMMDD) {
		int dd = YYMMDD % 100;

		return dd;
	}
    
}
