package org.vijay.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vijay.dao.Dao;
import org.vijay.model.User;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String pwd = request.getParameter("uPwd");
		String cnfrmPwd = request.getParameter("cnfrmPwd");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		if(pwd.equals(cnfrmPwd))
		{
		User u = new User();

		u.setEmail(email);
		u.setPassword(pwd);
		u.setLname(lname);
		u.setFname(fname);
		Dao dao = new Dao();
		int result = dao.saveUser(u);
		if(result==1){
			response.getWriter().write("<html><head><link rel=\"stylesheet\" href=\"external.css\"/></head>"
										+"<div class=\"up_Details-info\">Successfully registered!\nRedirecting to login page</div><body></body>"
										+"<script>setTimeout(function(){location.href=\"login.html\";},1000);</script></html>");
			
		}
		else{
			response.getWriter().write("<html><head><link rel=\"stylesheet\" href=\"external.css\"/></head>"
					+"<div class=\"up_Details-info\">Oops! something gone wrong.\n Try again</div><body></body>"
					+"<script>setTimeout(function(){location.href=\"register_id.html\";},1000);</script></html>");
		}
		}
		else{
			response.sendRedirect("PasswordMatch_Error.jsp?email="+email);
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
