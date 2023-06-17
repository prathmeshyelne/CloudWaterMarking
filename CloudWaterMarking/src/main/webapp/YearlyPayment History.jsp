 
<%@page import="services.CloudFuns"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <title>Monthly Cloud Rent</title>
       
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

function makeGetRequest() 
{       id=document.frm.cmbcust.value;
    id1=document.frm.cmbyr.value;
   
    http.open('get', 'PaymentsReport.jsp?cmbyr='+id1+'&uid='+id);
    http.onreadystatechange = processResponse;
    http.send(null);
}

function processResponse() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('report').innerHTML = response;
    }
}
-->
</script>
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
    <center>    <jsp:include page="Top.jsp"/>
        <div class="container-fluid">
        <%
        String userid=String.valueOf(session.getAttribute("userid"));
        System.out.println(String.valueOf(session.getAttribute("utype")));
        String typ=String.valueOf(session.getAttribute("utype"));  
        String home="";             
        if((session.getAttribute("userid")==null))             
        {
                response.sendRedirect("Invalidate.jsp");
        }
      CloudFuns ff=new CloudFuns();
       java.util.Vector vt=ff.getValue("select userid,username from users where utype='user' ", 2);
       
        %>
<br/>
           
        
        
        <br/>
        <form name="frm">
        <table align="center" class="tblform">
            <tr>
                <td>
                             <select name="cmbcust" class="form-control">
                                 <option value="<--Select-->"><--Select--></option>
                             <%
                              
                             for(int i=0;i<vt.size();i=i+2)
                             {%>
                                 <option value="<%=String.valueOf(vt.elementAt(i))%>"><%=String.valueOf(vt.elementAt(i+1))%></option>
                             <%}
                             %>
                             </select></td>
                
                
                <td>
                             <select name="cmbyr" class="form-control" style="width:80px">
                            <option value="YYYY">YYYY</option>
                             <%
                             java.util.Date dt=new java.util.Date();
                             int y=dt.getYear()+1900;
                              
                             for(int i=2014;i<=y;i++)
                             {%>
                             <option value="<%=String.valueOf(i)%>"><%=String.valueOf(i)%></option>
                             <%}
                             %>
                             </select></td><td style="width:80px"> 
                <input type="button" onclick="javascript:makeGetRequest()" value="Get Report" class="btn btn-primary"/></td>                    
            </tr>
        </table>
        </form>
        <br/> 
        <div id="report">
        </div>
        <br/> <br/></div>   <% }
       catch(Exception ex)
       {
           
       }%>
        
    </body>
</html>
