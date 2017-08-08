<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="org.vijay.dao.Dao,java.util.List, org.vijay.model.User"
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register EmailID</title>
</head>
<body>
<%
String email = request.getParameter("uEmail");
request.setAttribute("reg_emailID",email);
boolean available = true;
List<User> list = new Dao().getAllUsers();
if(list!=null){
for(User u:list){
	
	if(email.equals(u.getEmail())){
		available=false;
		break;
	}
}
}
if(available)
{
	out.write("*Email id is available");
}
else{
	out.write("!Sorry this email id is not available");
}
%>

</body>
</html>