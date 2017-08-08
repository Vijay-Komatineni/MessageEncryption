<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Passwords</title>
<link rel="stylesheet" href="external.css"/>
</head>
<header><h1><a href="login.html">Login</a></header>

<body>
<%
String id;
if(request.getParameter("hidden-emailid")==null){
	id = (String)request.getAttribute("hidden-emailid");
}
else{
	id = request.getParameter("hidden-emailid");
};
String actionUrl = "RegisterUser?email="+id; %>

<div class="reg_pwd-body_content">

<label class="reg_pwd-info">*Please fill the details</label>

<form id="registerPwd" action="<%= actionUrl%>">

<%out.print("<input type=\"hidden\" name=\"email\" value=\""+id+"\">"); %>

<h3 class="reg_pwd-label"> First Name </h3>    
<input class="reg_pwd-text-box" type="text" name="fname" id="fname" placeholder="First Name" required autofocus>

<h3 class="reg_pwd-label"> Last Name </h3>    
<input class="reg_pwd-text-box" type="text" name="lname" id="lname" placeholder="Last Name" required>

<h3 class="reg_pwd-label"> Password </h3>    
<input class="reg_pwd-text-box" type="password" name="uPwd" id="uPwd" placeholder="Password" required>
    
<h3 class="reg_pwd-label">Confirm Password </h3>

<input class="reg_pwd-text-box" type="password" id="cnfrmPwd" name="cnfrmPwd" placeholder="Re-enter Password" required>   

<br/>
<div id="pwdMisMatch"></div> 
<br/>
 <input type="submit" class="reg_pwd-Btn" value="Register" /><!-- onclick="return checkPasswords()" -->
  </form>
 
</div>
</body>
<script src="external.js"></script>
</html>