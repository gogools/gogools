package com.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.app.enums.HoraEnum;
import com.gogools.utils.EnumTool;
import com.gogools.utils.EnumTool.EnumToolInterface;

public class APPTests {
	
	private class Aux implements EnumToolInterface {

		@Override
		public String getValue() {

			return null;
		}

		@Override
		public Integer getOrdinal() {
			
			return null;
		}
		
	}
	
	interface Spacer {
		
		String ln( String msg );
	}
	
	static Spacer s = x -> "========== " + x + " ==========";

	@Test
	public void lab() {
		
		assertEquals(HoraEnum.CERO, EnumTool.getEnumFromValue( HoraEnum.class, "00:00"));
		assertEquals(null, EnumTool.getEnumFromValue( HoraEnum.class, ""));
		assertEquals(null, EnumTool.getEnumFromValue( EnumTool.EnumToolInterface.class, ""));
		assertEquals(null, EnumTool.getEnumFromValue( Aux.class, "01:00"));
		assertEquals(HoraEnum.values().length, EnumTool.getListEnumValues(HoraEnum.class).size());
		
		System.out.println( s.ln(HoraEnum.class.toString() + " -> Map") );
		EnumTool.getMapEnumValues(HoraEnum.class)
				.entrySet().stream()
				.map(x -> x.getKey() + " - " + x.getValue())
				.forEach(System.out::println);
		
		System.out.println( s.ln(HoraEnum.class.toString() + " -> List") );
		EnumTool.getListEnumValues(HoraEnum.class)
				.stream()
				.forEach(System.out::println);
	}

}
