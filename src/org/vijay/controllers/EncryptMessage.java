package org.vijay.controllers;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.vijay.model.Message;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptMessage {
	
	private static SecretKey generateKey() throws NoSuchAlgorithmException{
		//Generate Key
		KeyGenerator KeyGen = KeyGenerator.getInstance("AES");
	    KeyGen.init(128);
	    SecretKey secKey = KeyGen.generateKey();
		return secKey;
	}

	public Map<String,String> encrypt(String message) throws NoSuchAlgorithmException, NoSuchPaddingException, 
	InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
		
			SecretKey secKey = new EncryptMessage().generateKey();		    
		    
		    //Create Cipher object
		    Cipher AesCipher = Cipher.getInstance("AES");
		    
		    byte[] byteText = message.getBytes();
		    
		    //Encrypt the data and convert it to base 64 encoding format
		    AesCipher.init(Cipher.ENCRYPT_MODE, secKey);
		    byte[] cipherText = AesCipher.doFinal(byteText);
		    String encryptedMessage = new BASE64Encoder().encode(cipherText);
		    String secretKey = new BASE64Encoder().encode(secKey.getEncoded()); 
		    

		Map<String,String> map = new HashMap<String,String>();
		map.put(secretKey, encryptedMessage);
		return map;
		    
	}
	
	
	//Decrypt Messages
	public List<Message> decrypt(Map<Message,String> map) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		List<Message> list = new ArrayList<Message>();
		
		for(Map.Entry<Message, String> entry:map.entrySet()){
		System.out.println(entry.getKey().getId());
		}
		
		for(Map.Entry<Message, String> entry:map.entrySet()){
			//Decode the String the representation of secret key to get byte array
			byte[] byte_key = new BASE64Decoder().decodeBuffer(entry.getValue());
			
			//Using the byte array, reconstruct the SecretKey
			SecretKey secKey = new SecretKeySpec(byte_key,0,byte_key.length,"AES");
			
			//Now decryption of the actual message present in the message object
			
			Message m = entry.getKey();
			String encryptedMessage = m.getMessage();
		
			//Get the byte array representation of encrypted message
			byte[] byte_message = new BASE64Decoder().decodeBuffer(encryptedMessage);
			Cipher AesCipher = Cipher.getInstance("AES");
			AesCipher.init(Cipher.DECRYPT_MODE, secKey);
			
			//Decrypt the message
			byte[] bytePlainText = AesCipher.doFinal(byte_message);
			String message = new String(bytePlainText);
			m.setMessage(message);
			list.add(m);
		}//endFor
	
		return list;
	}

}
