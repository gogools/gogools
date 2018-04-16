package com.gogools.r3.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogools.r3.constant.Constants;
import com.gogools.r3.constant.OperationType;
import com.gogools.r3.domain.Operation;
import com.gogools.r3.repository.OperationRepository;
import com.gogools.r3.validator.OperationValidator;
import com.gogools.utils.ConsolePrinter;
import com.gogools.utils.DateUtil;

@Service
public class OperationService {
	
	@Autowired
	private OperationRepository opRepo;
	

	public void saveOperation(String data) {
		
		OperationValidator.validateEmptyData(data);
		
		Operation op = null;
		List<String> dataSplit = Arrays.asList(data.split(Constants.operationSeparator));
		
		OperationType opt = OperationType.findEnumByValue(dataSplit.get(1));
		switch ( opt ) {
		
        		case OPERATION_START:
        			
        			OperationValidator.validateMessageSize(dataSplit, OperationType.OPERATION_START);
        			op = createOperationStart(dataSplit);
        			break;
        			
        		case CASH_CLOSING:
        			
        			OperationValidator.validateMessageSize(dataSplit, OperationType.CASH_CLOSING);
        			op = createCashClosing(dataSplit);
        			break;
        			
        		case TOKEN_ACCESS:
        			
        			OperationValidator.validateMessageSize(dataSplit, OperationType.TOKEN_ACCESS);
        			op = createTokenAccess(dataSplit);
        			break;
        			
        		case USER_ACCESS:
        			
        			OperationValidator.validateMessageSize(dataSplit, OperationType.USER_ACCESS);
        			op = createUserAccess(dataSplit);
        			break;
        			
        		case PHYSICAL_TOKEN_ACCESS:
        			
        			OperationValidator.validateMessageSize(dataSplit, OperationType.PHYSICAL_TOKEN_ACCESS);
        			op = createPhysicalTokenAccess(dataSplit);
        			break;
        			
        		case SENSOR_STATE:
        			
        			OperationValidator.validateMessageSize(dataSplit, OperationType.SENSOR_STATE);
        			op = createSensorState(dataSplit);
        			break;
        			
        		case DEBUGEO:
        			
        			OperationValidator.validateMessageSize(dataSplit, OperationType.DEBUGEO);
        			op = createDebug(dataSplit);
        			break;
		}
		
		OperationValidator.validateMessageFormat(op, opt);

		ConsolePrinter.prettyJson("Just before save operation [" + DateUtil.getStringFromDate(op.getSysDate(), DateUtil.DEFAULT_DATE_TIME_FORMAT) + "]", op);
		opRepo.save(op);
	}
	
	
	public Stream<Operation> getOperationsByClient(String idClient) {
		
		return opRepo.findByIdClientOrderBySysDate(idClient).stream();
	}
	
	
	public Stream<Operation> getOperationsByClientAndSysDate(String idClient, Date sysDate, Date windowSysDate) {
		
		return opRepo.findByIdClientAndSysDate(idClient, sysDate, windowSysDate);
	}

	
	public Stream<Operation> getOperationsByClientCardAndSysDate(String idClient, String idCard, Date sysDate, Date windowSysDate) {
		
		return opRepo.findByIdClientCardAndSysDate(idClient, idCard, sysDate, windowSysDate);
	}
	
	
	public Stream<Operation> getOperationsByClientCardOpAndSysDate(String idClient, String idCard, String op, Date sysDate, Date windowSysDate) {
		
		return opRepo.findByIdClientCardOpAndSysDate(idClient, idCard, op, sysDate, windowSysDate);
	}

	/*
	 * Create operation block
	 */
	
	private Operation createOperationStart(List<String> data) {

		Operation op =  new Operation(data.get(0), data.get(1), data.get(2));
		
		List<String> msg = getMessage(data, Arrays.asList(0, 1, 2));

		op.setIdMonedero(msg.get(0));
		op.setTubos(msg.get(1));
		op.setTipoGabinete(msg.get(2));
		op.setCardDate(msg.get(3));
		op.setMetodoReporte(msg.get(4));
		
		return op;
	}

	
	private Operation createCashClosing(List<String> data) {

		Operation op = new Operation(data.get(0), data.get(1), data.get(3));

		List<String> msg = getMessage(data, Arrays.asList(0, 1, 3));

		op.setCodigoTeclado(msg.get(0));
		op.setTubos(msg.get(1));
		op.setCofre(msg.get(2));
		op.setMonedasEntrada(msg.get(3));
		op.setMonedasSalida(msg.get(4));
		op.setTipoGabinete(msg.get(5));
		op.setCardDate(msg.get(6));
		op.setMetodoReporte(msg.get(7));
		
		return op;
	}

	
	private Operation createTokenAccess(List<String> data) {

		Operation op =  new Operation(data.get(0), data.get(1), data.get(2));
		
		List<String> msg = getMessage(data, Arrays.asList(0, 1, 2));

		op.setUid(msg.get(0));
		op.setTipoGabinete(msg.get(1));
		op.setCardDate(msg.get(2));
		op.setMetodoReporte(msg.get(3));
		
		return op;
	}

	
	private Operation createUserAccess(List<String> data) {

		Operation op =  new Operation(data.get(0), data.get(1), data.get(2));
		
		List<String> msg = getMessage(data, Arrays.asList(0, 1, 2));

		op.setTubos(msg.get(0));
		op.setCofre(msg.get(1));
		op.setMonedasEntrada(msg.get(2));
		op.setMonedasSalida(msg.get(3));
		op.setTipoGabinete(msg.get(4));
		op.setCardDate(msg.get(5));
		op.setMetodoReporte(msg.get(6));
		
		return op;
	}

	
	private Operation createPhysicalTokenAccess(List<String> data) {

		Operation op =  new Operation(data.get(0), data.get(1), data.get(2));
		
		List<String> msg = getMessage(data, Arrays.asList(0, 1, 2));

		op.setUid(msg.get(0));
		op.setTipoGabinete(msg.get(1));
		op.setCardDate(msg.get(2));
		op.setMetodoReporte(msg.get(3));
		
		return op;
	}

	
	private Operation createSensorState(List<String> data) {

		Operation op =  new Operation(data.get(0), data.get(1), data.get(2));
		
		List<String> msg = getMessage(data, Arrays.asList(0, 1, 2));

		op.setAdicionales(msg.get(0));
		op.setTipoGabinete(msg.get(1));
		op.setCardDate(msg.get(2));
		op.setMetodoReporte(msg.get(3));
		
		return op;
	}
	
	
	private Operation createDebug(List<String> data) {

		Operation op =  new Operation(data.get(0), data.get(1), data.get(2));
		
		List<String> msg = getMessage(data, Arrays.asList(0, 1, 2));

		op.setDebugData(msg.get(0));
		op.setTipoGabinete(msg.get(1));
		op.setCardDate(msg.get(2));
		op.setMetodoReporte(msg.get(3));
		
		return op;
	}
	
	
	private List<String> getMessage(List<String> data, List<Integer> filter) {

		return IntStream.range(0, data.size())
						.filter(index -> !filter.contains(index))
						.mapToObj(data::get)
						.collect(Collectors.toList());
	}	
}
