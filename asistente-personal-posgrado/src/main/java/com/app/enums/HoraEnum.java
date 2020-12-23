package com.app.enums;

import com.gogools.utils.EnumTool.EnumToolInterface;

public enum HoraEnum implements EnumToolInterface {

	CERO		("00:00"),
	ONE 		("01:00"),
	TWO 		("02:00"),
	THREE 		("03:00"),
	FOUR 		("04:00"),
	FIVE 		("05:00"),
	SIX			("06:00"),
	SEVEN 		("07:00"),
	EIGHT 		("08:00"),
	NINE 		("09:00"),
	TEN 		("10:00"),
	ELEVEN 		("11:00"),
	TWELVE 		("12:00"),
	THIRTEEN 	("13:00"),
	FOURTEEN 	("14:00"),
	FIFTEEN 	("15:00"),
	SIXTEEN 	("16:00"),
	SEVENTEEN 	("17:00"),
	EIGHTEEN 	("18:00"),
	NINETEEN 	("19:00"),
	TWENTY 		("20:00"),
	TWENTYONE 	("21:00"),
	TWENTYTWO 	("22:00"),
	TWENTYTHREE	("23:00"),
	TWENTYFOUR	("24:00");
	
	private final String option;
	
    private HoraEnum(String option) {
    	
        this.option = option;
    }

    @Override
    public String getValue() {
    	
    	return this.option;
    }

	@Override
	public Integer getOrdinal() {
	
		return ordinal();
	}
	
}
