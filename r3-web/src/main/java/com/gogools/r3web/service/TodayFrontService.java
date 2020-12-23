package com.gogools.r3web.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogools.r3web.constant.OperationType;
import com.gogools.r3web.domain.Client;
import com.gogools.r3web.repository.CardRepository;
import com.gogools.r3web.rest.response.R3RestOperation;
import com.gogools.r3web.session.SessionData;
import com.gogools.r3web.view.CardSalesView;
import com.gogools.r3web.view.TodayCardView;
import com.gogools.r3web.vo.CardVO;
import com.gogools.utils.GDT;

@Service
public class TodayFrontService implements IFrontService<TodayCardView>{
	
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SessionData		sessionData;

	@Autowired
	private CardRepository	cardRepo;
	
	@Autowired
	private R3RestApiService restService;

	//data
	private List<CardVO> cards 								= new ArrayList<CardVO>();
//	private Map<CardVO, List<R3RestOperation>> todayCardData 	= new HashMap<CardVO, List<R3RestOperation>>();
//	private Map<CardVO, List<R3RestOperation>> weekCardData 	= new HashMap<CardVO, List<R3RestOperation>>();
	private Map<CardVO, List<R3RestOperation>> monthCardData 	= new HashMap<CardVO, List<R3RestOperation>>();

	@Override
	public void initialize() {
		
		Client client = new Client();
		client.setIdclient(sessionData.getIdClient());
		
		cards = cardRepo.findByClient(client).stream()
											.map(i -> new CardVO(i))
											.collect(Collectors.toList());
		
		for (CardVO cardVO : cards) {
			
			//todayCardData.put(cardVO, restService.getTodaysCardData(sessionData.getIdClient().toString(), cardVO.getSerial().toString(), OperationType.USER_ACCESS.getOperation()));
			//weekCardData.put(cardVO,  restService.getWeeklyCardData(sessionData.getIdClient().toString(), cardVO.getSerial().toString(), OperationType.USER_ACCESS.getOperation()));
			monthCardData.put(cardVO, restService.getMonthlyCardData(sessionData.getIdClient().toString(), cardVO.getSerial().toString(), OperationType.USER_ACCESS.getOperation()));
			
			//logger.info("initialize: card[" + cardVO.getName() + "], TODAY size[" + todayCardData.get(cardVO).size() + "]");
			//logger.info("initialize: card[" + cardVO.getName() + "], WEEK  size[" + weekCardData.get(cardVO).size()  + "]");
			logger.info("initialize: card[" + cardVO.getName() + "], MONTH size[" + monthCardData.get(cardVO).size() + "]");
		}
	}
	
	
	@Override
	public TodayCardView getViewData() {			
		
		initialize();
		
		TodayCardView result = new TodayCardView();
		
		result.setSalesView(getSalesData());
		
		return result;
	}
	
	private CardSalesView getSalesData() {
		
		CardSalesView result = new CardSalesView();
		
		//list of cards
		result.setCardList(cards);
		
		Long totalToday  	= 0L;
		Long totalWeekly 	= 0L;
		Long totalMonthly  	= 0L;
		
		//stats of every card
		Map<CardVO, String> salesStats = new HashMap<CardVO, String>();
		for (CardVO cardVO : cards) {
			
			Map<String, Long> sortedMap  = new LinkedHashMap<String, Long>();
					
			monthCardData.get(cardVO).stream()
										.filter(mc -> mc.getSysDate().before(GDT.getDateFromString(GDT.getLastDayOfTheWeekOnString()))
													&& mc.getSysDate().after(GDT.getDateFromString(GDT.getFirstDayOfTheWeekOnString()))
												)
										.collect(Collectors
												.groupingBy( m -> GDT.date2String(m.getSysDate()), 
															Collectors.counting()
														   )
												)
									.entrySet()
									.stream()
										.sorted(Map.Entry.<String, Long>comparingByKey())
										.forEachOrdered(m -> {																
																Calendar c = Calendar.getInstance();
																c.setTime(GDT.getDateFromString(m.getKey()));
																sortedMap.put(GDT.getDayOfWeek(c.get(Calendar.DAY_OF_WEEK)) + " " + m.getKey().substring(0, m.getKey().lastIndexOf("-")), 
																		      m.getValue()); //TODO change for DB >> session  * Constants.userCost
															 }
													   );
			
			totalToday  	+= monthCardData.get(cardVO).stream()
													.filter(mc -> GDT.date2String(mc.getSysDate()).equals(GDT.getCurrentMexDate())
														).count();			
			
			totalWeekly 	+= sortedMap.entrySet().stream()
												.map(m -> m.getValue())
												.collect(Collectors.summingLong(Long::longValue));
					
			totalMonthly	+= monthCardData.get(cardVO).stream().count();
			
			salesStats.put(cardVO, sortedMap.entrySet().stream()
														.map(m -> "[\"" + m.getKey() + "\", " + m.getValue() + "]")
														.collect(Collectors.joining(",", "[{data:[", "],color: \"#0088cc\"}]"))); //TODO make color scale in constant class
		}
		
		result.setSalesStats(salesStats);
		result.setTodayTotal(totalToday);
		result.setWeekTotal(totalWeekly);
		result.setMonthTotal(totalMonthly);
		
		return result;
	}
}
