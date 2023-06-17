 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="soham"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.min.css">
 <link rel="stylesheet" href="css/cust.css">

<title> </title>
</head>
<body><jsp:include page="Top.jsp"></jsp:include>
<%

String userid=String.valueOf(session.getAttribute("userid"));
String usertype=String.valueOf(session.getAttribute("usertype"));
if(!userid.equalsIgnoreCase("null")){	
	
session.setMaxInactiveInterval(10*60);
 
%>
<h2>My Documents</h2>
<hr>

<table class="table table-bordered">
<tr style="background-color:seashell">
<th>Title</th>
<th>Description</th>
<th>Date</th>
<th>Time</th>
<th></th>  
 
</tr>


<soham:forEach items="${std}" var="rec">

<tr>
<td>${rec.title}</td>
<td>${rec.docdesc}</td>
<td>${rec.dt}</td>
<td>${rec.tm}</td>
<td> 
<form method="post" action="sendOTP">
<input type="hidden" name="path" value=" ${rec.filePath}"/>
<input type="hidden" name="docId" value=" ${rec.docid}"/>
<input type="hidden" name="seckey" value=" ${rec.seckey}"/>
<input type="submit" value="Send OTP" class="btn btn-submit"/>
</form>
</td>  
  <td> 
<form method="post" action="shareWithGroups">
 
<input type="hidden" name="docid" value=" ${rec.docid}"/>
 <input type="submit" value="Share With Groups" class="btn btn-submit"/>
</form>
</td> 
</tr>

</soham:forEach>
</table>

<%
if(usertype.equals("admin")){
%> 
<%
}
else{	%>
	 
<%
	}
	
}
else{
	%>
	<h2>Invalid Session...Login again</h2>
	<br>
	<a href="index.jsp">Login</a>
	
	<%
}

%>
</body>
</html>