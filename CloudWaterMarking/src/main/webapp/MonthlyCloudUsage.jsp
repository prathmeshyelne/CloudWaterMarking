 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <title>Monthly Cloud Usage</title>
       
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
{     
    id=document.frmcloud.cmbmon.value;
    id1=document.frmcloud.cmbyr.value;
   //alert(id);
    http.open('get', 'CloudUsageStats.jsp?cmbmon='+ id+'&cmbyr='+id1);
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
      java.util.Vector vmon=new java.util.Vector();
        vmon.addElement("Jan");vmon.addElement("Feb");vmon.addElement("March");vmon.addElement("April");
        vmon.addElement("May");vmon.addElement("June");vmon.addElement("Jully");vmon.addElement("Aug");vmon.addElement("Sep");
        vmon.addElement("Oct");vmon.addElement("Nov");vmon.addElement("Dec");
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
              
        %>
<br/>
           
        
        
        <br/>
        <form name="frmcloud">
        <table align="center" class="tblform">
            <tr><td>
                 <select name="cmbmon"   class="form-control" style="width:80px">
                            <option value="MM">MM</option>
                             <%
 
                             for(int i=1;i<=12;i++)
                             {%>
                             <option value="<%=String.valueOf(i)%>"><%=vmon.elementAt(i-1)%></option>
                             <%}
                             %>
                 </select> </td><td>
                             <select name="cmbyr" class="form-control" >
                            <option value="YYYY">YYYY</option>
                             <%
                             java.util.Date dt=new java.util.Date();
                             int y=dt.getYear()+1900;
                              
                             for(int i=2014;i<=y;i++)
                             {%>
                             <option value="<%=String.valueOf(i)%>"><%=String.valueOf(i)%></option>
                             <%}
                             %>
                             </select></td><td  > 
                <input type="button" onclick="javascript:makeGetRequest()" value="Get Report" class="btn btn-primary"/></td>                    
            </tr>
        </table>
        </form>
        <br/>
        <div id="report">
        </div>
        <br/></div>   <% }
       catch(Exception ex)
       {
           
       }%> 
    </body>
</html>
