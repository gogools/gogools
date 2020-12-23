package com.gogools.r3web.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gogools.r3web.constant.Constants;
import com.gogools.r3web.vo.CardVO;

import lombok.Data;

@Data
public class CardSalesView {

	private List<CardVO>			cardList;
	private Map<CardVO, String> 	salesStats;
	private Long					todayTotal;
	private Long					weekTotal;
	private Long					monthTotal;
	
	
	public CardSalesView() {
		
		cardList 	= new ArrayList<CardVO>();
		salesStats 	= new HashMap<CardVO, String>();
	}
	
	
	public String getCardStats(CardVO card) {
		
		return salesStats.get(card);
	}
	
	
	public String getTodayTotalIncome() { //TODO change for DB >> session  
		
		return "$" + (todayTotal * Constants.userCost);
	}
	
	
	public String getMonthTotalIncome() { //TODO session info from DB
		
		return "$" + (monthTotal * Constants.userCost);
	}
}
