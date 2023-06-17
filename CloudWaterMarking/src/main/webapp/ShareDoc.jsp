 
<%@page import="java.util.ArrayList"%>
<%@page import="models.Groups"%>
<%@page import="java.util.Vector"%>
<%@page import="services.CloudFuns"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
    
    http.open('get', 'GetUsers?searchText=' + st+'&groupName=NA');
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
<style>
.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
}

.switch input { 
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

input:checked + .slider {
  background-color: #2196F3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196F3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}
</style>
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
CloudFuns jf=new CloudFuns();
Vector v=jf.getValue("select groupName from accessperdocgroup where docid="+request.getAttribute("docid").toString().trim(), 1);

%>
<div class="container">
<div class="jumbotron">

    
<div class="row">

<div class="col-md-6">
<h2>Available Groups</h2><br/>
<%
List<Groups> lst=(ArrayList<Groups>)request.getAttribute("lst");
if(lst.size()>0)
{
%>
<form  method="post" action="ShareImgWithGroups">
<table class="table table-bordered">

<c:forEach var="userdsc" items="${lst}">
		<tr>  	<th colspan="2">
<input type="checkbox" name="${userdsc.getGroupName()}" value="${userdsc.getGroupName()}"/>
 <input type="hidden" name="docid" value="<%=request.getAttribute("docid").toString().trim() %>"/>
		${userdsc.getGroupName() }</th>
			</tr> 
		</c:forEach></table>
		<input type="submit" value="Submit" class="btn btn-primary"/>
</form>
<%}else{ %>
<h7>No Record Found!!</h7>
<%} %>
</div>
<div class="col-md-6">
<h2>Selected Documents already Shared with following Groups</h2>
<table class="table table-bordered">
<tr><th>Group Name</th>
</tr>
<%for(int i=0;i<v.size();i++){ %>

<tr><td><%=v.elementAt(i).toString().trim() %></td></tr>
<%} %>
</table>


</div>
<%
}
catch(Exception ex)
{
	System.out.println("err iin sharedoc="+ex.getMessage());
} 
%>  
 
</div>



</div>
</body>
</html>