
<%@page import="java.util.List"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>QR based Secure Data Transfer Application</title>
 

</head>
<body> 
<% try{ response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("home");
}
%> 
 <table class="table table-bordered">
 <tr>
 <th> User Name</th>
 <th>Email id</th>
 <th></th>
 </tr>
 <c:forEach var="userdsc" items="${lst}">
		<tr><td>${userdsc.getName() }</td>  
			<td>${userdsc.getEmail()}</td>
			<td><a href="RegGroupMember?userid=${userdsc.getUserid() }&groupName=<%=request.getAttribute("groupName").toString().trim() %>">Add as Member</a></td>
			</tr>
		</c:forEach></table>
		 
<%}
catch(Exception ex)
{
	
} %>
 
</body>
</html>