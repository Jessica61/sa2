package sa2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmValidation {

	public static boolean emailValidation(String email) {
		
 boolean status= false;
		 
		 String email_pattern = "^\\w{4,30}[@]+(gmail|hotmail|yahoo|outlook).[\\w]{2,3}$";
		 Pattern pattern = Pattern.compile(email_pattern);
		 Matcher matcher =pattern.matcher(email);
	       if(matcher.matches()){
	    	   status = true;
	       }else{
	    	  
	    	   status = false;
	       }
	       
	       
		return status;
	}

}
