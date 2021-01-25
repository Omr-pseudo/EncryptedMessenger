import java.io.Serializable;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

@SuppressWarnings("serial")
public class Encryption implements Serializable{

private final String ALGORITHM = "AES";
private byte [] key_value;

public Encryption (String key)
{
    key_value = key.getBytes();

}

public String encrypt(String data) throws Exception
{
    Key key = keyGenerator();
    Cipher ciphertext = Cipher.getInstance(ALGORITHM);
    ciphertext.init(Cipher.ENCRYPT_MODE,key);
    byte [] encVal = ciphertext.doFinal(data.getBytes());
    String finalVal = Base64.getEncoder().encodeToString(encVal);
   
    return finalVal;

}

public String decrypt(String encrypteddata) throws Exception 
{
	Key key = keyGenerator();
	
	Cipher ciphertext = Cipher.getInstance(ALGORITHM);
	ciphertext.init(Cipher.DECRYPT_MODE, key);
	byte [] decodedVal = Base64.getDecoder().decode(encrypteddata);
	byte [] decryptVal = ciphertext.doFinal(decodedVal);
	
	String finalVal = new String(decryptVal);
	
	return finalVal;
}


private Key keyGenerator() throws Exception
{
    Key key = new SecretKeySpec(key_value,ALGORITHM);
    
    return key;

}
    
}