   
<%@page import="services.CloudFuns"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%> 
<!DOCTYPE html>
<html>
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

function makeGetRequest() {
    id=document.frm.company.value;
    mon=document.frm.mon.value;
    yr=document.frm.yr.value;    
    http.open('get', 'PaymentStats.jsp?collegecd=' + id+'&month='+mon+'&year='+yr);
    http.onreadystatechange = processResponse;
    http.send(null);
}

function processResponse() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('stats').innerHTML = response;
    }
}

-->
</script>
    <head>
        <link rel="stylesheet" href="hrmstyles.css"></link>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Summary</title>
    </head>
    <body>
          <%
         try{
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", -1);
  if((session.getAttribute("userid")==null))             
        {
                response.sendRedirect("Invalidate.jsp");
        }
%>
    <jsp:include page="Top.jsp"></jsp:include>     
    <center>
           <div id="main">
           
     <%
        java.util.Date dt=new java.util.Date();
        String userid=String.valueOf(session.getAttribute("userid"));
        System.out.println(String.valueOf(session.getAttribute("utype")));
        String typ=String.valueOf(session.getAttribute("utype"));        
        if((session.getAttribute("userid")==null))             
        {
                response.sendRedirect("Invalidate.jsp");
        }
        else if(typ.equals("cloudadmin")==false)
        {
                response.sendRedirect("Invalidate.jsp");
        }
     CloudFuns ff=new CloudFuns();
        java.util.Vector v=ff.getValue("select userid,usernm from users where utype='company' or utype='supplier';", 2);
        %>
       
    <br/>
    <center>
        <h2>Payment Summary</h2>
        
      <table>
    <tr>
        <td>
<form name="frm">
Hospital ID <select name="compnay" class="combo">
    <option value="select">Select</option>
   <%
 
                try
               {
                    for(int i=0;i<v.size();i=i+2)
                                               {
                        %>
                        <option value="<%=v.elementAt(i).toString()%>"><%=v.elementAt(i+1)%></option>
                        <%
                    
                                           }
                  }
catch(Exception e)
{
}                           
%>  
</select>

Month <select name="mon" class="combo">
    <option value="january">January</option>
    <option value="february">February</option>
    <option value="march">March</option>
    <option value="april">April</option>
    <option value="may">May</option>
    <option value="june">June</option>
    <option value="july">July</option>
    <option value="august">August</option>
    <option value="september">September</option>
    <option value="october">October</option>
    <option value="november">November</option>
    <option value="december">December</option>

    
</select>

Year <input type="text" name="yr" value="<%=dt.getYear()+1900%>" class="textbox"></input>

<input type="button" value="Generate Report" onclick="javascript:makeGetRequest()" class="buttonStyle"></input> 


</form>
</td>
</tr>
</table>


<div id="stats"></div>



</br>

        
       </center>
           </div></center>
    <jsp:include page="Bottom.jsp"></jsp:include>

        <%} catch(Exception ex){}%>
    </body>
</html>
