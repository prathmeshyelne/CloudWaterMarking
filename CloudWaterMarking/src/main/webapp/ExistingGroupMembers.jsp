
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
<script language="Javascript" type="text/javascript">
<!--

function createRequestObject() {
    var tmpXmlHttpObject;
    if (window.XMLHttpRequest) {
            tmpXmlHttpObject = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        tmpXmlHttpObject = new ActiveXObject("Microsoft.XMLHTTP");
    }

    return tmpXmlHttpObject;
}


var http = createRequestObject();

function makeGetRequest(st) {
     groupName=document.frmSearch.groupName.value;
    http.open('get', 'GetUsers?searchText=' + st+'&groupName='+groupName);
    http.onreadystatechange = processResponse;
    http.send(null);
}

function processResponse() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('users').innerHTML = response;
    }
}
-->
</script>

</head>
<body>
<jsp:include page="Top.jsp"></jsp:include>
<% try{ response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("home");
}
%>
<div class="container">
  <div class="jumbotron"> 

     
<div class="row">

<div class="col-md-12"> <h2>Existing Group Members</h2>
   <div class="form-group"> 
    
  <div id="users">
   <table class="table table-bordered">
 <tr>
 <th> User Name</th>
 <th>Mobile No</th>
 <th>DOB</th>
 <th>Address</th>
 <th>Email id</th>
 <th></th>
 </tr>
 <c:forEach var="userdsc" items="${lst}">
		 <tr>
		<td>${userdsc.getMemUserName() }</td>
		<td>${userdsc.getMobile() }</td>
		<td>${userdsc.getDob() }</td>
		<td>${userdsc.getAddr() }</td>
		<td>${userdsc.getEmail() }</td> 
		<td>
			<form method="post" action="DelGroupMember">
			<input type="hidden" name="groupName" value="<%=request.getAttribute("groupName").toString().trim() %>"/>
			<input type="hidden" name="userid" value="<%=session.getAttribute("userid").toString().trim() %>"/>
			
			<input type="hidden" value="${userdsc.getMemId() }" name="memId"/>
			 
			<input type="submit" value="Remove" class="buttonStyle"/>
			</form> 
		 </td></tr>
		</c:forEach> 
		 
		
		</table>
		</div>
 </div>
</div>
</div>
<%}
catch(Exception ex)
{
	
} %>

</div>
</body>
</html>