import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PasswordValidator {

	private static Pattern pattern;
	private static Matcher matcher;

	private static final String Password_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$#^%!]).{6,20})";
	
	protected static int checkPassword(String pass) 
	{
		
		pattern = Pattern.compile(Password_REGEX);
		
		matcher = pattern.matcher(pass);

		if(matcher.matches()) 
		{
			return 0;
		}
		else 
		{
			return 1;
		}
		
	}
	
	
	
	
}
