
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Tools {

	
	private static Encryption AES_Security;
	
	public static void writeFile(String path, String message, String key) throws Exception
	{
		AES_Security = new Encryption(key);
		
		String enrypted_data = AES_Security.encrypt(message);
		
		BufferedWriter messageOut = new BufferedWriter(new FileWriter(path));
		
		messageOut.write(enrypted_data);
		
		messageOut.close();
		
	}
	
	
	public static String readFile(String path, String key) throws Exception
	{
		
		AES_Security = new Encryption(key);
		
		BufferedReader messagein = new BufferedReader(new FileReader(path));
		
		String encrypted_data = messagein.readLine();
		
		messagein.close();
		
		String decrypted_data = AES_Security.decrypt(encrypted_data);
		
		return decrypted_data;
		
	}
	
	
	public static void Write(String path, String value) throws IOException 
	{
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			
			bw.write(value);
			
			bw.close();
		
	}
	
	
	public static String Read(String path) throws IOException 
	{
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(path));
		
		String finalVal = br.readLine();
		
		return finalVal;
	}
	
}
