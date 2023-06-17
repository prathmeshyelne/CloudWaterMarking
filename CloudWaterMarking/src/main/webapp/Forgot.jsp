<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cryptouch</title>
</head>
<body>
<jsp:include page="DefaultTop.jsp"></jsp:include>
<% try{ response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
 
  %>
  <div class="container jumbotron">
  <center><h2>Password Recovery</h2></center>
  <div class="col-md-6">
  <img src="images/forgot.png" width="100%"/>
  </div>
  <div class="col-md-6">
  <form action="passRecovery" method="post">
  <table>
  <tr>
  <td>Userid</td>
  <td>
  <input type="text" name="userid" class="form-control" required/>
    
  </td>
  </tr>
    
   <tr>
  <td colspan="2"><input type="submit" class="btn btn-primary" value="Submit"/>  </td>   
  </tr>
  </table>
  </form></div>
  </div>
  <%
}
catch(Exception ex)
{
	
}
%>
</body>
</html>