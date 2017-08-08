<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User dashboard</title>
<link rel="stylesheet" href="external.css"/>
</head>
<body>
<jsp:include page="dashboard.jsp"></jsp:include>
<%
String result = request.getParameter("result");
if(result.equals("@message@sending_failed"))
{	
%>
<h3 class="comp-label1">Sending failed. Please try again</h3>
<%
}
else
{
%>
<h3 class="comp-label1">User doesn't exist</h3>
<%
}
%>
</body>
<script>
setTimeout(scrollToBottom,1000);
function scrollToBottom(){
	window.scrollTo(0,document.body.scrollHeight);
}

</script>
</html>