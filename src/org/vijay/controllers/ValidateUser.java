package org.vijay.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.vijay.dao.Dao;
import org.vijay.model.User;

/**
 * Servlet implementation class ValidateLogin
 */
@WebServlet("/ValidateUser")
public class ValidateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String email = request.getParameter("email").toLowerCase();
	String pwd = request.getParameter("pwd");
	
	boolean valid= false;
	Dao dao = new Dao();
	List<User> user_list = dao.getAllUsers();
	
	for(User u:user_list){
		if(email.equals(u.getEmail())&&pwd.equals(u.getPassword())){
			valid = true;
			HttpSession session = request.getSession();
			session.setAttribute("name", u.getFname());
			session.setAttribute("userid", u.getEmail());
			break;
		}
	}
	
	if(valid){
		/*RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
		rd.forward(request, response);*/
		response.sendRedirect("dashboard.jsp");
	}
	else{
		/*RequestDispatcher rd = request.getRequestDispatcher("Error_LoginAgain.jsp");
		rd.forward(request, response);*/
		response.sendRedirect("Error_LoginAgain.jsp");
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
