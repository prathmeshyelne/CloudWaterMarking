 
<%@page import="java.util.List"%>

<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cryptouch</title>
 
 <script type="text/javascript">
    function Validate() {
        var password = document.getElementById("newpass").value;
        var confirmPassword = document.getElementById("rnewpass").value;
        document.frmChangePass.rnewPass.value='';
        if (password != confirmPassword) {
            alert("Passwords do not match.");
            return false;
        }
        return true;
    }
</script>
</head>
<body>
<jsp:include page="Top.jsp"></jsp:include>
<% try{ response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("index.jsp");
}
%>
<div class="container">
  <div class="jumbotron"> 

     
<div class="row">

<div class="col-md-6"> <h2>Change Password</h2>
   <div class="form-group"> 
    <form method="post" name="frmChangePass" action="/ChangePassService">
<table class="tblform">
<tr>
<td>Current Password</td>
<td>
<input type="password"  name="currentpass" required class="form-control"></input>
</td>
</tr>
<tr>
<td>New Password</td>
<td>
<input type="password"  class="form-control"  name="newpass" required  >
</td>
</tr>
<tr>
<td>Retype New Password</td>
<td>
<input type="password"  class="form-control"  name="rnewpass" onchange="Validate();" required >
</td>
</tr>			
               
 <tr>
 <td colspan="2">
 <input type="submit" value="Submit" class="btn btn-primary"/>
 </td>
 </tr>
</table></form>
 
</div></div>
 <div class="col-md-6">
 <img src="images/changePass.png" width="90%"/>
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