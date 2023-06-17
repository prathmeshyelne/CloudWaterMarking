
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
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("home");
}
%> 
  <div class="jumbotron"> 

     
<div class="row">

<div class="col-md-6"> <h2>Group Registration</h2>
   <div class="form-group"> 
    <form method="post" name="frm" action="RegGroup">
<table class="tblform">
<tr>
<td>Group Name</td>
<td>
<input type="text"  name="groupName" required class="form-control"></input>
</td>
</tr>
 
                <tr><td>Group Description</td>
                    <td><textarea  name="groupDesc" required class="form-control"></textarea></td>
                </tr>
 <tr>
 <td colspan="2">
 <input type="hidden" value="<%=session.getAttribute("userid").toString().trim() %>" name="userid"/>
 <input type="submit" value="Submit" class="buttonStyle"/>
 </td>
 </tr>
</table></form>
 
</div></div>
 <div class="col-md-6">
 <center><h2>My Groups</h2></center>
 <table class="table table-bordered">
 <tr>
 <th> Group Name</th>
 <th>Group Description</th>
  <th colspan="2"></th> 
 </tr>
 <c:forEach var="userdsc" items="${lst}">
		<tr><td>${userdsc.getGroupName() }</td>  
			<td>${userdsc.getGroupDesc()}</td>
		 	<!--  <td>
			<a href="SendGroupMsg?groupId=${userdsc.groupId }&groupName=${userdsc.getGroupName() }">Send Group Message</a>
			</td>
			 <td>
			<a href="viewGroupMsg?groupId=${userdsc.groupId }&groupName=${userdsc.getGroupName() }">View Group Message</a>
			</td> -->
			<td>
			<a href="AddMember?groupName=${userdsc.getGroupName() }">Add New Members</a>
			</td>
			<td>
			<a href="ExistingMembers?groupName=${userdsc.getGroupName() }">Existing Members</a></td>
			
			 	</tr>
		</c:forEach></table>
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