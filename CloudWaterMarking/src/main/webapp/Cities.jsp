 
  
<%@page import="models.Cities"%>
<%@page import="models.GetStateNCities"%>
<%@page import="java.util.List"%>
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<title>Register</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
 <select required name="city" class="form-control">

<%
String st=request.getParameter("state");

//cities to be generated from the DB later
GetStateNCities obj=new GetStateNCities();
obj.getCities(st);
List<Cities> lst=obj.getLstcity();
  
 for(int i=0;i<lst.size();i++)
{%>
<option value="<%=lst.get(i).getCity() %>"><%=lst.get(i).getCity() %></option>											
<%}%>
 

</select>
</body>
</html>