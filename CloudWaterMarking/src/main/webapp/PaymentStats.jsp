 
  
<%@page import="services.CloudFuns"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%> 
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="hrmstyles.css"></link>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <center>
<br>
        
  <%
         try{
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", -1);
  if((session.getAttribute("user")==null))             
        {
                response.sendRedirect("Invalidate.jsp");
        }
%>
<%
String cid=request.getParameter("userid");
String mon=request.getParameter("month");
int yr=Integer.parseInt(request.getParameter("year"));
int cnt=0;
      
                try
               {
                     CloudFuns ff=new CloudFuns();
                    java.util.Vector vct=ff.getValue("select tenantid,id,month,year,rents from cloudpayments where tenantid='"+cid+"' and month='"+mon+"' and year='"+yr+"'",5);
              
                out.println("<br><table width='800' border='1' cellspacing='0' cellpadding='5'>");
                %>
                <tr bgcolor='seashell'>
                   <th>Company
                   <th>Payment Date
                   <th>Rent Month
                   <th>Rent Year
                   <th>Amount
                    <th>Mode
                    
                                    </tr>
                <%
          for(int i=0;i<vct.size();i=i+5)
                {
                   cnt++;      

                out.println("<tr>");
                out.println("<td>"+vct.elementAt(i));
                out.println("<td>"+vct.elementAt(i+1));
                out.println("<td>"+vct.elementAt(i+2));
                out.println("<td>"+vct.elementAt(i+3));
                out.println("<td>"+vct.elementAt(i+4));
               out.println("</tr>");
            }
                                
                if(cnt==0)
                {
                    out.println("<tr>");
                    out.println("<td colspan='6' align='center'>No records available</td>");
                    out.println("</tr>");
                }
                
                out.println("</table>");
                }
                 catch(Exception e)
                        {
                     //out.println(e);
                }


%>


<%} catch(Exception ex){}%>

        
    </body>
</html>
