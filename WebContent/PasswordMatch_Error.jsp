<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<% String email = request.getParameter("email");
request.setAttribute("hidden-emailid",email);%>
	<jsp:include page="register_passwords.jsp"></jsp:include>
	<br/><p class="reg_pwd-info">* Passwords do not match</p>
</body>
</html>