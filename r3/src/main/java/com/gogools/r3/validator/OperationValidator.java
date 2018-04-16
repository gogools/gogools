package com.gogools.r3.validator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gogools.r3.constant.Constants;
import com.gogools.r3.constant.OperationType;
import com.gogools.r3.domain.Operation;
import com.gogools.utils.ConsolePrinter;
import com.gogools.utils.Regex;

public class OperationValidator {
	
	public static final Logger logger = LoggerFactory.getLogger(OperationValidator.class);
	
	
	public static void validateEmptyData(String data) {
		
		if (data == null || data.isEmpty()) {

			throw new IllegalArgumentException("The 'data' parameter must not be null or empty");
		}
	}
	

	public static void validateMessageSize(List<String> data, OperationType opt) {

		if (data.size() != opt.getMessageSize()) {

			IllegalArgumentException excep =  new IllegalArgumentException("Operation [" + opt + "] incomplete");
			logger.debug(excep.getMessage());
			
			throw excep;
		}
	}

	public static void validateMessageFormat(Operation op, OperationType opt) {
		
		StringBuilder sb = new StringBuilder();
		
		if ((opt == OperationType.OPERATION_START || opt == OperationType.CASH_CLOSING || opt == OperationType.USER_ACCESS) && !Regex.match(op.getTubos(), Constants.coinRegex)) {

			sb.append(String.format("::%s::\n", " Data [TUBOS] incorrect format "));			
			ConsolePrinter.prettyJson("enum", sb);
		}

		if (opt == OperationType.CASH_CLOSING || opt == OperationType.USER_ACCESS) {

			if (!Regex.match(op.getCofre(), 			Constants.coinRegex)) sb.append(String.format("::%s::\n", " Data [COFRE] incorrect format "));
			if (!Regex.match(op.getMonedasEntrada(), Constants.coinRegex)) sb.append(String.format("::%s::\n", " Data [$-IN] incorrect format "));
			if (!Regex.match(op.getMonedasSalida(), 	Constants.coinRegex)) sb.append(String.format("::%s::\n", " Data [$-OUT] incorrect format "));
		}
		
		if( sb.length() != 0 ) {
			
			throw new IllegalArgumentException(sb.toString());
		}
	}
}
