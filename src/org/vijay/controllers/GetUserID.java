package org.vijay.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.vijay.dao.Dao;
import org.vijay.model.User;

/**
 * Servlet implementation class GetUserID
 */
@WebServlet("/GetUserID")
public class GetUserID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserID() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("emailid");
		Dao dao = new Dao();
		List<User> list = dao.getAllUsers();
		PrintWriter pw = response.getWriter();
		 response.setContentType("application/json; charset=UTF-8");
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
	    for (User u : list)
	    {
	    	if(!(email.equals(u.getEmail())))
	            array.put(u.getEmail());
	    }
	    json.put("userIDs", array);
	    pw.print(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
