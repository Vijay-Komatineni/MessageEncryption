<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Dashboard</title>
<link rel="stylesheet" href="external.css"/>
</head>
<header><h1><span class="name_header">Welcome <%=session.getAttribute("name") %></span></h1><h3><a class="logout_header" href="Logout">Logout</a></h3></header>
<hr class="hr">
<nav><h1><a href="GetMessages.jsp">Inbox</a>|<span class="db-header-label" onclick="loadCompose()"><u>Compose</u></span></h1></nav>
<span id="hidden-emailid" hidden><%=session.getAttribute("userid") %></span> 

<div id="dashboard-body">


<div class="comp-body-content"> 
<form id="comp-form" class="comp-form" action="StoreMessage" method="post">
<h3 class="comp-label">To</h3>
<select class="comp-select" name="to" id="to" onfocus="populateList()">
</select>
<h3 class="comp-label">Subject</h3>
<input class="comp-text-box" type="text" name="subject" id="subject" placeholder="Subject">
<h3 class="comp-label">Message</h3>
<textarea class="comp-txt-area" name="message" id="message" placeholder="Message" required></textarea><br/><br/>
<input type="submit" class="comp-btn" value="Send"/>
</form>
</div>

</div>

<body>
</body>

<script src="external.js"></script>
</html>