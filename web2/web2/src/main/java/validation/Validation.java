package validation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Validation {
	
	public static boolean isBlank(String str) { // chuỗi trống => true 
		return str.isBlank();
	}
	
	public static boolean isNumber(String str) { // số dương => true
		return Pattern.compile("\\d+").matcher(str).matches();
	}
	
	public static boolean isEmail(String str) { // là email => true
		String reg = "^[A-Za-z0-9._%+-]+\\@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
		Pattern pattern = Pattern.compile(reg);
		return pattern.matcher(str).matches();
	}
	
	public static boolean isDate(String str) { // is date -> true
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(str);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public static boolean isReleaseDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			Date releaseDate = sdf.parse(str);
			Date currentDate = new Date();
			int diff = currentDate.compareTo(releaseDate);
			if(diff >= 0) {
				return true;
			}else {
				return false;	
			}
		}catch(Exception e) {
			return false;
		}
	}
	
	public static boolean isUsername(String str) {
		// username có : chữ hoa hoặc thường, hoặc có số, dấu ., _, -. ít nhất 3 ký tự
		return Pattern.compile("^[a-zA-Z0-9._-]{3,}$").matcher(str).matches();
	}
	
	public static boolean isPhoneNumber(String str) {
		// số điện thoại là 10 số liên tiếp ko cách
		return Pattern.compile("^\\d{10}$").matcher(str).matches();
	}
	
	public static boolean isZipcode(String str) {
		return Pattern.compile("^\\d{6}$").matcher(str).matches();
	}
	
}
