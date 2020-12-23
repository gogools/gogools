package com.gogools.enums;

import com.gogools.utils.EnumTool.EnumToolInterface;

public enum RoleEnum implements EnumToolInterface {
	
	SUPER_ROLE("SUPER_ROLE"),
	NORMAL_ROLE("NORMAL_ROLE");
	
	private final String option;
	
    private RoleEnum( String option ) {
    	
        this.option = option;
    }

	@Override
	public String getValue() {

		return option;
	}

	@Override
	public Integer getOrdinal() {
		
		return ordinal();
	}

}
