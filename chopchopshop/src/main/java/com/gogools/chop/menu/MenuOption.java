package com.gogools.chop.menu;

import java.util.List;

import lombok.Data;

@Data
public class MenuOption {

	private String				label;
	private String				url;
	private String				icon;
	private String				params;
	private List<MenuOption>	dropdown;
}
