<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="DefaultTop.jsp"></jsp:include>
<div class="container"><br/><br/><center> 
<%
if(request.getParameter("type").toString().trim().equals("Reg"))
{
	%><h2 class="h2">Your Registration Done Successfully....</h2>
	<br/>
	<a href="home">Home</a>
<%}
if(request.getParameter("type").toString().trim().equals("RegUser"))
{
	%><h2 class="h2">Your Registration Done Successfully....</h2>
	<br/>
	<a href="home">Home</a>
<%}
else if(request.getParameter("type").toString().trim().equals("DocUpload"))
{
	%><h2 class="h2">Document Uploaded Successfully....</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("GroupMsgReg"))
{
	%><h2 class="h2">Group Message Sent Successfully....</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}else if(request.getParameter("type").toString().trim().equals("CloudPayment"))
{
	%><h2 class="h2">Payment Updated Successfully....</h2>
	<br/>
	<a href="MonthlyPayment.jsp">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("MsgReg"))
{
	%><h2 class="h2"> Message Sent Successfully....</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("DocumentReg"))
{
	%><h2 class="h2">Document Registered Successfully....</h2>
	<br/>
	<a href="/userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ShareDoc"))
{
	%><h2 class="h2">Document Shared Successfully....</h2>
	<br/>
	<a href="/userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("GroupReg"))
{
	%><h2 class="h2">Group Registered Successfully....</h2>
	<br/>
	<a href="/userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("GroupMsgDel"))
{
	%><h2 class="h2">Group Message Deleted Successfully....</h2>
	<br/>
	<a href="/groupMsgHistory">continue...</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("UserMsgDel"))
{
	%><h2 class="h2"> Message Deleted Successfully....</h2>
	<br/>
	<a href="/MsgHistory">continue...</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("Doc"))
{
	%><h2 class="h2">Document Uploaded Successfully....</h2>
	<br/>
	<a href="userHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("GroupMemReg"))
{
	%><h2 class="h2">Group Member Added to Selected Group Successfully....</h2>
	<br/>
	<a href="AddMember?groupName=<%=request.getAttribute("groupName").toString() %>">continue...</a>
	
<%
}
else if(request.getParameter("type").toString().trim().equals("ShareMsg"))
{
	%><h2 class="h2">Message Shared with selected user Successfully....</h2>
	<br/>
	<a href="MsgHistory">continue...</a>
	
<%
}
else if(request.getParameter("type").toString().trim().equals("GroupMemDel"))
{
	%><h2 class="h2">Group Member Removed from Selected Group Successfully....</h2>
	<br/>
	<a href="ExistingMembers?groupName=<%=request.getAttribute("groupName").toString() %>">continue...</a>
	
<%
}else if(request.getParameter("type").toString().trim().equals("Subscribe"))
{
	%><h2 class="h2">Cloud Services Subscribed Successfully....</h2>
	<br/>
	<a href="user">continue...</a>
	
<%
}
%>
</div>
</body>
</html>