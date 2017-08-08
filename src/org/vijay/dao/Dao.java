package org.vijay.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.vijay.model.Message;
import org.vijay.model.User;

public class Dao {

	private Connection con;
	
	public static void main(String[] args){
		Dao dao = new Dao();
		Map<Message,String> map = dao.getMessageDetails("rohit@gmail.com");
		for(Map.Entry<Message, String> entry:map.entrySet()){
			Message m=entry.getKey();
		System.out.println(m.getRecepient()); 
		}
	}
	public Dao(){
		
	}
	
	//Open Connection
	private void openConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","Vijay@123");
		}
		 catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		 catch(SQLException e){
			 System.out.println(e.getStackTrace());
		 }
	}
	
	
	//Close Connection
	private void closeConnection(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getStackTrace());
		}
	}
	
	//Check if User is present
	@SuppressWarnings("finally")
	public boolean checkUser(String email){
		ResultSet rs;
		boolean result = false;
		 try {
			 openConnection();
	            PreparedStatement ps = con.prepareStatement("select email from users");
	            rs=ps.executeQuery();
	            while(rs.next()){
	            	
	            	if(email.toLowerCase().equals(rs.getString("email"))){
	            		result = true;
	            		break;
	            	}

	            }//endWhile
	            rs.close();	
		 }//endTry
		 catch(SQLException e){
			 System.out.println(e.getStackTrace());
		 }
		 finally{
			 closeConnection();
			 return result;
		 }
		 
	}
	
	
	//Get all the User IDs from database
	@SuppressWarnings("finally")
	public List<User> getAllUsers(){
		ResultSet rs;
		User u = null;
		List<User> userlist = new ArrayList<User>();
		
		 try {
			 openConnection();
	            PreparedStatement ps = con.prepareStatement("select * from users");
	            rs=ps.executeQuery();
	            while(rs.next()){
	            	 u = new User();
	            	u.setEmail(rs.getString(2));
	            	u.setPassword(rs.getString(3));
	            	u.setFname(rs.getString(4));
	            	u.setLname(rs.getString(5));
	            	userlist.add(u);
	            }
	            rs.close();	
	            
		 }//endTry
		 
		 catch(SQLException e){
			 System.out.println(e.getStackTrace());
		 } 
		 finally{
			 closeConnection();
			 return userlist;
		 }

		 
	}//endMethod
	
	
	//Get User
	@SuppressWarnings("finally")
	public User getUser(String email){
		ResultSet rs;
		User u = null;
		try{
			openConnection();
			PreparedStatement ps = con.prepareStatement("select * from users where email=?");
			ps.setString(1, email);
            rs=ps.executeQuery();
            while(rs.next()){
            	 u = new User();
            	u.setEmail(rs.getString(2));
            	u.setPassword(rs.getString(3));
            	u.setFname(rs.getString(4));
            	u.setLname(rs.getString(5));
            }
            rs.close();	
            
		}
		 catch(SQLException e){
			 System.out.println(e.getStackTrace());
		 }
		finally{
		closeConnection();
		return u;
		}
	}
	
	
	//Save User in the database
	@SuppressWarnings("finally")
	public int saveUser(User u){
		
		int result = 0;
		try {
			openConnection();
            PreparedStatement ps = con.prepareStatement("insert into users(email, password, fname, lname) values(?,?,?,?)");
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFname());
            ps.setString(4, u.getLname());
           result = ps.executeUpdate();
                     
	 }//endTry
	 catch(SQLException e){
		 System.out.println(e.getStackTrace());
	 } 
		finally{
		closeConnection(); 
		return result;
	}
	}//endMethod
	
	
	//Save Message in the database	
	@SuppressWarnings("finally")
	public int saveMessage(Message m, String key){
		int result = 0;
		try{
			openConnection();
			PreparedStatement ps = con.prepareStatement("insert into messages(source, time, recepient, subject, message) values(?,?,?,?,?)");
			ps.setString(1, m.getSource());
			ps.setString(2, m.getDateTime());;
			ps.setString(3, m.getRecepient());
			if(m.getSubject().equals("")){
				ps.setString(4, "(no subject)");
			}
			else{
			ps.setString(4, m.getSubject());
			}
			ps.setString(5,m.getMessage());
			result = ps.executeUpdate();
			if(result==1)
			{
				storeKey(key);
			}
			
		}
		 catch(SQLException e){
			 System.out.println(e.getStackTrace());
		 } 
	finally{
			closeConnection();
			return result;
		}
	}//endMethod
	
	@SuppressWarnings("finally")
	public void storeKey(String key){
		int result = 0;
		try{
				int m_id = getRecentMessageID();
				openConnection();
				PreparedStatement ps = con.prepareStatement("insert into encryptionkeys (encrypted_key, mid) values(?,?)");
				ps.setString(1, key);
				ps.setInt(2, m_id);
				
				result = ps.executeUpdate();
				
			}
			 catch(SQLException e){
				 e.printStackTrace();
			 }
			finally{
				closeConnection();
			}
	}
	
	@SuppressWarnings("finally")
	private int getRecentMessageID(){
		ResultSet rs;
		int m_id=0;
		try{
			openConnection();
			PreparedStatement ps = con.prepareStatement("SELECT mid FROM messages ORDER BY mid DESC LIMIT 1");
			rs = ps.executeQuery();
			
			while(rs.next()){
				m_id = rs.getInt("mid");
			}
			rs.close();
			
		} catch (SQLException e) {
			
			System.out.println(e.getStackTrace());
		}
		finally{
			closeConnection();
			return m_id;
		}
	}
	
	
	
	
	//Get All incoming messages of corresponding Email id
	@SuppressWarnings("finally")
	public Map<Message, String> getMessageDetails(String email){
		ResultSet rs;
		
		Map<Message,String> map = new LinkedHashMap<Message,String>();
		List<Message> mList = new ArrayList<Message>();
		
		try{
			openConnection();
			PreparedStatement ps = con.prepareStatement("select messages.mid,source,subject,time,message,encrypted_key from messages inner join encryptionkeys on messages.mid = encryptionkeys.mid where recepient=?");
			//encryptionkeys.encrypted_key "
			ps.setString(1, email);
            rs=ps.executeQuery();
            while(rs.next()){
            	Message m = new Message();
            	m.setId(rs.getInt(1));
            	m.setSource(rs.getString(2));
            	m.setSubject(rs.getString(3));
            	m.setDateTime(rs.getString(4));
            	m.setMessage(rs.getString(5));
            	map.put(m,rs.getString(6));
            	//mList.add(m);
            }
            rs.close();
           // map = getKeys(mList, email);
       	
		}
		 catch(SQLException e){
			 e.printStackTrace();
		 } 
		finally{
			
			closeConnection();
			return map;
		}
	}

	
	
	//Delete message
	public void deleteMessage(int mid){
		try{
			openConnection();
			PreparedStatement ps1 = con.prepareStatement("delete from encryptionkeys where mid = ?");
			PreparedStatement ps = con.prepareStatement("delete from messages where mid = ?");
			ps1.setInt(1, mid);
			ps.setInt(1, mid);
			ps1.executeUpdate();
			ps.executeUpdate();
		}
		catch(SQLException e){
			System.out.println(e.getStackTrace());
		}
		finally{
			closeConnection();
		}
	}
	
}
