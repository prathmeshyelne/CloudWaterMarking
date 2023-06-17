
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
<title>Social Networking</title>
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

function makeGetRequestDec(id,k) 
{   
    http.open('get', 'decryptMsg?msgId='+id+'&k='+k);
    http.onreadystatechange = processResponseDec;
    http.send(null);
}

function processResponseDec() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('decmsg').innerHTML = response;
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
<div class="container-fluid">
  <div class="jumbotron"> 

     
<div class="row">

<div class="col-md-12"> <h2>Group Messages (<%=request.getAttribute("groupName").toString().trim() %>)</h2>
   <div class="form-group"> 
    
  <div id="users">
   <table class="table table-bordered">
 <tr>
 <th>Message</th>
 <th>Message Sent By</th>
 <th>Date</th>
 <th>Time</th>   
 </tr>
  
 <c:forEach var="userdsc" items="${lst}">
		 <tr>
		<td>${userdsc.groupMsg } 
		<c:choose>
    <c:when test="${userdsc.encsts=='on'}">
        <a href="decryptMsg?msgId=${userdsc.msgId }&k=${userdsc.seckey }" target="_blank">Decrypt</a>
		 
    </c:when>    
    
</c:choose>

		
		 
		<div id="decmsg" name="decmsg"></div>
		 </td>
		<td>${userdsc.username }</td>
		<td>${userdsc.dt }</td>
		<td>${userdsc.tm }</td>
 
	    
		 </tr>
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