package com.gogools.util;

public class Util {

	public static String getPassword( String email ) {
		
		return email.substring(0, email.lastIndexOf("@"))
					  .replace("s", "5")
					  .replace("S", "5")
					  .replace("g", "9")
					  .replace("G", "9")
					  .replace("a", "4")
					  .replace("A", "4")
					  .replace("e", "3")
					  .replace("E", "e")
					  .replace("i", "1")
					  .replace("I", "1")
					  .replace("o", "0")
					  .replace("O", "0")
					  .replace("b", "8")
					  .replace("B", "8");
	}
}
