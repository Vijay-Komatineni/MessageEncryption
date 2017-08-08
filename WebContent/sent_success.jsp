<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User dashboard</title>

</head>
<body>
<jsp:include page="dashboard.jsp"></jsp:include>
<h3 class="comp-label1">Message sent successfully</h3>
</body>
<script>
setTimeout(scrollToBottom,1000);
function scrollToBottom(){
	window.scrollTo(0,document.body.scrollHeight);
}

</script>
</html>