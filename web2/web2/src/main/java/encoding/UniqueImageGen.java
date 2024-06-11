package encoding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class UniqueImageGen {
	
	public static String generate(String originImage) {
		String uniName = genUniName();
		String ext = getExt(originImage);
		String time = getTime();
		return uniName+"_"+time+"." +ext;
	}
	public static String genUniName() {
		UUID u = UUID.randomUUID();
		return u.toString();
	}
	public static String getExt(String str) {
		int dotInd = str.lastIndexOf(".");
		if(dotInd > 0 && dotInd < str.length() - 1 ) {
			//ajsdfas.gif.jpg.
			return str.substring(dotInd + 1);
		}
		return "jpg";
	}
	public static String getTime() {
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(formatter);
	}
	
}
