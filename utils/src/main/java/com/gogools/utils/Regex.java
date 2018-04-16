package com.gogools.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	/*	REGEX TOOL TIP
	 * 	 . = any char
	 *  \. = the actual dot character
	 *  .? = .{0,1} = match any char zero or one times
	 *  .* = .{0,} = match any char zero or more times
	 *  .+ = .{1,} = match any char one or more times
	 */
	
	/*	PASSWORD DETAIL
	 * 	^                 = start-of-string
	 *	(?=.*[0-9])       = a digit must occur at least once
	 *  (?=.*[a-z])       = a lower case letter must occur at least once
     *  (?=.*[A-Z])       = an upper case letter must occur at least once
     *  (?=.*[@#$%^&+=\.,;])  = a special character must occur at least once
	 *  (?=\S+$)          = no whitespace allowed in the entire string
     *  .{8,}             = anything, at least eight places
	 *  $                 = end-of-string
	 */

	public static final String	EMAIL_PATTERN		= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String	PASSWORD_PATTERN		= "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%\\.,])(?=\\S+$).{8,20}$";
	public static final String	IMAGE_PATTERN		= "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
	public static final String	NIP_PATTERN			= "^[0-9]{4}$";

	private static Pattern		thePattern;
	private static Matcher		matcher;

	public static Boolean match(String data, String pattern) {

		thePattern = Pattern.compile(pattern);

		matcher = thePattern.matcher(data);

		return matcher.find();
	}
	
	
	public static Boolean matchEmail(String email) {
		
		return match(email, EMAIL_PATTERN);
	}
	
	
	public static Boolean matchPassword(String password) {
		
		return match(password, PASSWORD_PATTERN);
	}
	
	
	public static Boolean matchNip(String nip) {
		
		return match(nip, NIP_PATTERN);
	}
}
