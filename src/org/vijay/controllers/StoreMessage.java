package org.vijay.controllers;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vijay.dao.Dao;
import org.vijay.model.Message;

/**
 * Servlet implementation class StoreMessage
 */
@WebServlet("/StoreMessage")
public class StoreMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get values
		String source = (String) request.getSession().getAttribute("userid");
		String recepient = request.getParameter("to");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		Dao dao = new Dao();
		if(dao.checkUser(recepient))  //Check if recepient exists
		{
		//Get Time
		Calendar calendar = Calendar.getInstance();
		DateConversion dc = new DateConversion();
		String dateTime = dc.convertDate(calendar);
		
		//Encrypt the message
		EncryptMessage encrypt  =new EncryptMessage();
		Map<String,String> map = null;
		try {
		 map = encrypt.encrypt(message);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String key = null;
		for(Entry<String, String> entry:map.entrySet()){
			key = entry.getKey();
			message = entry.getValue();
		}
		
		//create message object
		Message m = new Message();
		m.setSource(source);
		m.setRecepient(recepient);
		m.setSubject(subject);
		m.setMessage(message);
		m.setDateTime(dateTime);
		
		//Store message object
		
		int result = dao.saveMessage(m,key);
		if(result==1){
			
			request.getRequestDispatcher("sent_success.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("sent_fail.jsp?result=@message@sending_failed").forward(request, response);
		}
		}//endOuterIF
		else{
			request.getRequestDispatcher("sent_fail.jsp?result=@recepient@not_available").forward(request, response);
		}
	}//enddoGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
