 
 
<%@page import="services.*"%>
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
            home="cloudadmin.jsp";
        else
            home="user.jsp";
        %>
       
    <br/>
    <center>
        <%
        if((request.getParameter("cmbmon").equals("") || request.getParameter("cmbmon")==null)&& (request.getParameter("cmbyr").equals("") || request.getParameter("cmbyr")==null))
        {%>
            <br/>
            <center><h3>Please Select Month and Year to Find Rent</h3></center>
                
        <%}
        else{
            %>
        <h2>Cloud Payment Report for <%=request.getParameter("cmbmon")%>/<%=request.getParameter("cmbyr")%> </h2></center>
        
    <table width="950" cellspacing="0" border="1" cellpadding="7" align="center" class="table table-bordered">
            <tr bgcolor="#b5c7f8"> 
            
               
            <%
        String cmonth=request.getParameter("cmbmon");
          String cyr=request.getParameter("cmbyr");
          ServiceSummary obj=new ServiceSummary();
        CloudFuns sf=new CloudFuns();
          Vector vt=new Vector();
         
          vt=sf.getValue("select services from cloudpayments where month="+cmonth+" and year="+cyr, 1);
          if(vt.size()==0)
          {%><th>No Record Found!!</th><%}else{%>   <th> Name</th> <%}
          String s[]=vt.elementAt(0).toString().split(",");
          for(int i=0;i<s.length;i++)
          {
                %>  <th><%=s[i] %> </th><%}%>
                <th>Total Rent</th>
                <th>Status</th>
                   <th>Payment Date</th>
                <th>Action</th>
                
            </tr>
                <%
        Vector   vr=sf.getValue("select rents,total,status,id,tenantid,paydt from cloudpayments where month="+cmonth+" and year="+cyr, 6);
         for(int i=0;i<vr.size();i=i+6)
          {%><tr><%
              String str[]=vr.elementAt(i).toString().split(",");
              Vector vunm=sf.getValue("select username from users where userid='"+vr.elementAt(i+4).toString()+"'", 1);
              %>
              <td><%=vunm.elementAt(0).toString()%></td>
                <%
              for(int k=0;k<str.length;k++){
        %>
          
                    <td><%=str[k] %></td>
                    <%}
                  
                    %>
                    <td><%=vr.elementAt(i+1).toString()%></td>
                      <td><%=vr.elementAt(i+2).toString()%></td>
                      <td><%=vr.elementAt(i+5).toString()%></td>
                      <% if(vr.elementAt(i+2).toString().equals("pending"))
                      {
                          java.util.Date dt=new java.util.Date();
                          if(Integer.parseInt(cmonth)<(dt.getMonth()+1))
                          {%>
                          <td><a href="CloudPayment?id=<%=vr.elementAt(i+3).toString()%>">Mark as Paid</a></td>
                          <%}else{%><td>Current Month</td>
                          <%
                      }}
                      else{%><td>Paid</td><%} 
                          
                     %>
                 
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
