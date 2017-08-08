<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="org.vijay.dao.Dao,org.vijay.model.Message,java.util.*,org.vijay.controllers.EncryptMessage"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inbox</title>
<link rel="stylesheet" href="external.css"/>
</head>
<header><h1><span class="name_header">Welcome <%=session.getAttribute("name") %></span></h1><h3><a class="logout_header" href="Logout">Logout</a></h3></header>
<hr class="hr">
<nav><h1><a href="dashboard.jsp">Compose</a></h1></nav>

<div id="dashboard-body">

<% String email = (String)session.getAttribute("userid");

//get messages from the database
Dao dao = new Dao();
Map<Message,String> encryptedMessages = dao.getMessageDetails(email);

if(encryptedMessages.size()!=0){
//Obtain list of decrypted messages
EncryptMessage em = new EncryptMessage();
List<Message> decryptedMessages = em.decrypt(encryptedMessages);

%>
<h3><a href="GetMessages.jsp">Refresh</a></h3>

<!-- Inbox table -->

<table class="Itable">

<tr>
<th class="Ith"  style="font-size:20px;">From</th>
<th class="Ith" style="font-size:20px;">Subject</th>
<th class="Ith" style="font-size:20px;">Message</th>
<th class="Ith" style="font-size:20px;">Received On</th>
<th class="Ith" style="font-size:20px;">Delete</th>
</tr>
<%
for(Message m:decryptedMessages)
{
%>
	<tr>
	<td align="center" class="Itd"><%=m.getSource() %></td>
	<td align="center" class="Itd"><%=m.getSubject() %></td>
	<td align="center" class="Itd"><%=m.getMessage() %></td>
	<td align="center" class="Itd"><%=m.getDateTime() %></td>
	<td align="center" class="Itd"><a href="DeleteMessage?id=<%=m.getId()%>">Delete</a></td>
	</tr>
<% } %>
	
	</table>
<% 
}//endIF

else{
	%>
	<h2 class="no-message">No messages at this time</h2>
<% }%>
	
	</div>
	<body>
</body>
<script src="external.js"></script>
</html>
