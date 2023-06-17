
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
<title>Insert title here</title>
</head>
<body>
 <jsp:include page="Top.jsp"></jsp:include>
<% try{ response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
 
%>
<div class="container">
  <div class="jumbotron"> 

     
<div class="row">

<div class="col-md-12"> <h2>OTP Authentication</h2>
   <div class="form-group"> 
    <form method="post" name="frm" action="OTPAuth">
<table class="tblform">
<tr><td colspan="2">One time password is sent on your registered email id!!</td></tr>
<tr>
<td>Enter OTP</td>
<td>
<input type="text"  name="uotp" required class="form-control"></input>
</td>
</tr>
 
              
 <tr>
 <td colspan="2"> 
<input type="hidden" value="<%=request.getAttribute("path").toString().trim() %>" name="filePath"/>
  <input type="hidden" value="<%=request.getAttribute("seckey").toString().trim() %>" name="seckey"/>
  
 <input type="hidden" value="<%=request.getAttribute("docId").toString().trim() %>" name="docId"/>
  <input type="submit" value="Submit" class="buttonStyle"/>
 </td>
 
 </tr>
 <tr><td colspan="2"><input type="text" value="<%=request.getAttribute("otp").toString().trim() %>" name="otp"/></td></tr>
</table></form>
 <form method="post" action="sendOTP">
			 	<input type="hidden" name="userid" value="<%=session.getAttribute("userid").toString().trim() %>"/>
			<input type="hidden" value="<%=request.getAttribute("path").toString().trim() %>" name="filePath"/>
  <input type="hidden" value="<%=request.getAttribute("seckey").toString().trim() %>" name="seckey"/>
  <input type="hidden" value="<%=request.getAttribute("otp").toString().trim() %>" name="otp"/>
 <input type="hidden" value="<%=request.getAttribute("docId").toString().trim() %>" name="docId"/>
  
			<input type="submit" class="btn-primary" value="Get OTP on Email" class="buttonStyle"/>
			</form> 
</div></div>
 
</div>
</div>
<%}
catch(Exception ex)
{
	
} %>

</div>
</body>
</html>