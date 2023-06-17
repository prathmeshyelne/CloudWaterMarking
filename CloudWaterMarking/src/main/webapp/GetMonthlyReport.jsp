 
 
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
        if(typ.equals("admin"))
            home="Admin.jsp";
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
        <h2>Cloud Rent Report for <%=request.getParameter("cmbmon")%>/<%=request.getParameter("cmbyr")%> </h2></center>
        
    <table width="950" cellspacing="0" border="1" cellpadding="3" align="center" class="table table-bordered tblform">
            <tr bgcolor="#b5c7f8"> 
                <th>Client Name</th> <th></th> 
              <th>Encryption</th> 
                 <th>Decryption</th> 
                   
                       <th>Email</th>     
                           
          
                <th>
                    Login</th>
        
               
                <th>Total Rent</th>
            </tr>
            <%
        String cmonth=request.getParameter("cmbmon");
          String cyr=request.getParameter("cmbyr");
          ServiceSummary obj=new ServiceSummary();
         CloudFuns sf=new  CloudFuns();
          Vector vt=new Vector();
           if(typ.equals("user"))
               vt.addElement(session.getAttribute("userid").toString());
           else
          vt=sf.getValue("select userid from users where usertype='user'", 1);
          for(int i=0;i<vt.size();i++)
          {
                
          obj.getcloudrent(vt.elementAt(i).toString(), Integer.parseInt(cmonth), Integer.parseInt(cyr));
          java.util.Vector usage=new java.util.Vector();
          usage.clear();
                  usage=obj.getUsage();
          java.util.Vector service= new java.util.Vector();
          service.clear();
          service=obj.getService();
          java.util.Vector rate=new java.util.Vector();
          rate.clear();
          rate=obj.getRate();
          java.util.Vector rent=new java.util.Vector();
          rent.clear();
          rent=obj.getRent();
          Vector vnm=sf.getValue("select username from users where userid='"+vt.elementAt(i).toString()+"'", 1);
        %>
        
            
       
                <tr>
                    <td rowspan="3"><%=vnm.elementAt(0).toString() %></td>
                    <td>Usage</td>
                    <%
                    int totusage=0;
                    int s=usage.size();
                    System.out.println("size="+s);
                    for(int j=0;j<s;j++)
                    {
                        totusage+=Double.parseDouble(usage.elementAt(j).toString());
                    %>
                    
                    <td><%=usage.elementAt(j).toString() %></td>
                    <%}%>  <td><%=totusage %></td></tr><tr>
                      <td>Rates</td>
                    <%
                      double totrate=0.0;
                    for(int j=0;j<rate.size();j++)
                    {
                         totrate+=Double.parseDouble(rate.elementAt(j).toString());
                    %>
                  
                    <td>Rs. <%=rate.elementAt(j).toString() %></td>
                    <%}%><td>Rs. <%=totrate %></td></tr><tr style="background-color: #00C6F0;color: white;font-weight: bold"> <td>Rents</td>
                    <%
                    double totrent=0.0;
                    System.out.println("rsize"+rent.size());
                    for(int j=0;j<rent.size();j++)
                    {
                        totrent+=Double.parseDouble(rent.elementAt(j).toString());
                    %>
                   
                    <td>Rs. <%=rent.elementAt(j).toString() %></td>
                    <%}%><td>Rs. <%=totrent %></td>
                </tr>
                
                <%
                  }
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
