  
<%@page import="services.CloudFuns"%>
<%@page import="services.ServiceSummary"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,java.io.*"%>
 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css"/>
        <title>JSP Page</title>
    </head>
    <body>
       
     <%
     try{
        String userid=String.valueOf(session.getAttribute("userid"));
        System.out.println(String.valueOf(session.getAttribute("utype")));
        String typ=String.valueOf(session.getAttribute("utype")); 

        String home="";                     
        if((session.getAttribute("userid")==null))             
        {
                response.sendRedirect("Invalidate.jsp");
        }
        if(typ.equals("cloudadmin"))
            home="Admin.jsp";
        else
            home="user.jsp";
           CloudFuns sf=new CloudFuns();
             java.util.Vector vmon=new java.util.Vector();
        vmon.addElement("Jan");vmon.addElement("Feb");vmon.addElement("March");vmon.addElement("April");
        vmon.addElement("May");vmon.addElement("June");vmon.addElement("Jully");vmon.addElement("Aug");vmon.addElement("Sep");
        vmon.addElement("Oct");vmon.addElement("Nov");vmon.addElement("Dec");
        %>
       
    <br/>
    <center>
        <%
        if((request.getParameter("cmbyr").equals("") || request.getParameter("cmbyr")==null)&& (request.getParameter("uid").equals("") || request.getParameter("uid")==null))
        {%>
            <br/>
            <center><h3>Please Select Month and Year to Find Rent</h3></center>
                
        <%}
        else{
             Vector vunm=sf.getValue("select username from users where userid='"+request.getParameter("uid").toString()+"'", 1);
            %>
        <h2>Payment History Customer Name :  <%=vunm.elementAt(0).toString()%> for <%=request.getParameter("cmbyr")%> </h2></center>
        
    <table width="950" cellspacing="0" border="1" cellpadding="7" align="center" class="table table-bordered tblform">
            <tr bgcolor="#b5c7f8"> 
            
               
            <%
        String uid=request.getParameter("uid");
          String cyr=request.getParameter("cmbyr");
          ServiceSummary obj=new ServiceSummary();
       
          Vector vt=new Vector();
         
          vt=sf.getValue("select services from cloudpayments where tenantid='"+uid+"' and year="+cyr, 1);
          if(vt.size()==0)
          {%><th>No Record Found!!</th><%}else{%>   <th>Months</th> <%}
          String s[]=vt.elementAt(0).toString().split(",");
          for(int i=0;i<s.length;i++)
          {
                %>  <th><%=s[i] %> </th><%}%>
                <th>Total Rent</th>
                <th>Status</th>
                <th>Payment Date</th>
                
            </tr>
                <%
        Vector   vr=sf.getValue("select rents,total,status,id,month,paydt from cloudpayments where tenantid='"+request.getParameter("uid").toString()+"' and year="+cyr, 6);
         for(int i=0;i<vr.size();i=i+6)
          {%><tr><%
              String str[]=vr.elementAt(i).toString().split(",");
             
              %>
              <td><%=vmon.elementAt(Integer.parseInt(vr.elementAt(i+4).toString())-1)%></td>
                <%
              for(int k=0;k<str.length;k++){
        %>
          
                    <td><%=str[k] %></td>
                    <%}
                  
                    %>
                    <td><%=vr.elementAt(i+1).toString()%></td>
                      <td><%=vr.elementAt(i+2).toString()%></td>
                   
                 <td><%=vr.elementAt(i+5).toString()%></td>
                <%
          }%></tr><%
        }
                }
                catch(Exception ex)
                {
                    System.out.println("errr="+ex.getMessage());
                }
            
            
            %>
            
      </table>
             
    </body>
</html>
