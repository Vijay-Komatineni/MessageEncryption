<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compose Message</title>

</head>
<body>

<% 
out.print("<div class=\"comp-body-content\">"+ 
"<form id=\"comp-form\" class=\"comp-form\" action=\"StoreMessage\" method=\"post\">"
+"<h3 class=\"comp-label\">To</h3>"
+"<input type=\"email\" class=\"comp-select\" name=\"to\" id=\"to\"  required/>"
+"<h3 class=\"comp-label\">Subject</h3>"    
+"<input class=\"comp-text-box\" type=\"text\" name=\"subject\" id=\"subject\" placeholder=\"Subject\">"
+"<h3 class=\"comp-label\">Message</h3>"
+"<textarea class=\"comp-txt-area\" name=\"message\" id=\"message\" placeholder=\"Message\" required></textarea><br/><br/>"
+"<input type=\"submit\" class=\"comp-btn\" value=\"Send\"/></form></div>");
%>
</body>

<!-- onfocus=\"populateList()\" -->

</html>