/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

  

import java.io.File;
import java.io.IOException;
  
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import entities.Uploads;
import entities.Users;
 

 

/**
 *
 * @author megha
 */
public class CloudFuns {
        Connection con;
     PreparedStatement pst;
     CallableStatement cst;
     ResultSet rs;
   
    public int FetchMax(String fldnm,String tblnm)
    {
       int maxid=1000;
    try
    {
       DBConnector obj=new DBConnector();
        con=obj.connect();
        String qr="select max("+fldnm+") as mxid from "+tblnm;
        pst=con.prepareStatement(qr);
        rs=pst.executeQuery();
        while(rs.next())
        maxid=rs.getInt("mxid");
        if(maxid==0)
            maxid=1000;
    }
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
    maxid=1000;
    }
    return (maxid+1);
    }
    

    public boolean sendLoginCredentials(String userid,String pass,String user,String email)
    {
        try{
             Mail obj=new Mail();
            String sub="Login Credentials";
            String msg="Dear "+user+",\nYour account has been created on clouddeduplication.com.\nYour userid is "+userid+" and password is "+pass+"\n Thanks.\n Cloud Admininstrator";
            
            if(obj.sendMail(msg,email,sub))
            {
                return true;
            }
            else
                return false;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
    
     public List getClients()
     {
        String id="";
        System.out.println("in get clients");
          List<Users> lst=new ArrayList<Users>();
         try
         { System.out.println("in get clients");
             
               // lst=new ArrayList<Clients>(); 
               System.out.println("in get clients");
		lst = (List)getResultSet("select * from users where utype='user'");
                         
  System.out.println("in get clients"+lst.size());
                for(int i=0; i<lst.size(); i++){
	                   Users user = (Users)lst.get(i);
                           id=user.getClientId();
                           System.out.println("id"+id);
                }
                 
         }
         catch(Exception ex)
         {
             System.out.println("error cl="+ex.getMessage());
         }
         return lst;
     }
       
       public String getclientName(String clientid)
     {
        String id="";
         try
         {
             
                List<Users> lst=new ArrayList<Users>();
                lst=(List)getResultSet("select username from users where userid='"+clientid+"'");
                
                for(int i=0; i<lst.size(); i++){
	                   Users user = (Users)lst.get(i);
                           id=user.getUserName();
                }
                 
         }
         catch(Exception ex)
         {
             
         }
         return id;
     }
         public Vector getValue(String qr,int nocol)
    {    
        Vector v=new Vector();
        Connection con=null;
        Statement st;
        ResultSet rs;
        v.clear();
         try{ 
              DBConnector obj=new  DBConnector();
             con=obj.connect();
           st=con.createStatement();
           System.out.println("query="+qr);
           rs=st.executeQuery(qr);
          
           while(rs.next())
           {
               for(int i=1;i<=nocol;i++)
               {
               v.addElement(rs.getString(i));
               System.out.println(rs.getString(i));
               }              
           }
         }
         catch(Exception e)
        {
            System.out.println("Error in processing due to "+e.getMessage());
        }   
          finally
         {
             try{
             con.close();}catch(Exception ex){}
         }
        return(v);        
    }
       public ResultSet getResultSet(String qr)
    {    
        Vector v=new Vector();
     
        Connection con=null;
        Statement st;
        ResultSet rs=null;
        v.clear();
         try{ 
              DBConnector obj=new  DBConnector();
             con=obj.connect();
           st=con.createStatement();
           System.out.println("query="+qr);
           rs=st.executeQuery(qr);
           
         }
         catch(Exception e)
        {
            System.out.println("Error in processing due to "+e.getMessage());
        }   
          finally
         {
            // try{
            // con.close();}catch(Exception ex){}
         }
        return(rs);        
    }
     
    public boolean execute(String qr)
    {
         Boolean sts=false;
         try
        {
            DBConnector obj=new  DBConnector();
            con=obj.connect();
            Statement st=con.createStatement();
            st.executeUpdate(qr);
            sts=true;
        }
        catch(Exception ex)
        {
        sts=false;
        }
         finally
         {
             try{
             con.close();}catch(Exception ex){}
         }
        return sts;  
        
    }
        public void recordLogin(String userid,String logtyp)    
{
     String logdt;
     int logmon;
     int logyr;
     Connection con;
    PreparedStatement pst;
    try
    {
     DBConnector dbcon=new DBConnector();
     con=dbcon.connect();
       Vector v=getValue("select companyid from users where userid='"+userid.trim()+"'", 1);
     String qr="insert into loginlog values(?,?,?,?,?,?);";
        Calendar cal=Calendar.getInstance();
    logdt=String.valueOf(cal.getTime());
    logmon=cal.get(Calendar.MONTH)+1;
    logyr=cal.get(Calendar.YEAR);
    pst=con.prepareStatement(qr);
    int maxid=FetchMax( "logid","loginlog");
    pst.setInt(1, maxid);
    pst.setString(2,logdt);
    pst.setInt(3,logmon);
    pst.setInt(4,logyr);
    pst.setString(5,v.elementAt(0).toString().trim());
    pst.setString(6,logtyp);
    pst.executeUpdate();
    con.close();
}
catch(Exception e)
{
    System.out.println("Error:"+e.getMessage());
}

}
        
 public void recordusage(String userid,String action)    
{
     String logdt;
     int logmon;
     int logyr;
        
    
     Connection con;
    PreparedStatement pst;
    try
    {
  DBConnector dbcon=new DBConnector();
     con=dbcon.connect();
     //Vector v=getValue("select userid from users where userid='"+userid.trim()+"'", 1);
     String qr="insert into usagelog values(?,?,?,?);";
    Calendar cal=Calendar.getInstance();
    logdt=String.valueOf(cal.getTime());
    logmon=cal.get(Calendar.MONTH)+1;
    logyr=cal.get(Calendar.YEAR);
    pst=con.prepareStatement(qr);
    int maxid=FetchMax("logid","usagelog");
    pst.setInt(1,maxid);
      pst.setString(2,userid);
    pst.setString(3,action);
    pst.setString(4,logdt+"/"+logmon+"/"+logyr);
   
    int n=pst.executeUpdate();
    if(n>0)
    {
        System.out.println("Usage traked successfully...");
    }
    con.close();
}
catch(Exception e)
{
    System.out.println("Err="+e.getMessage());
}

}
 public boolean sendSecKeyDetails(String title,String user,String email,String key)
    {
        try{
           Mail obj=new Mail();
            String sub="Document Details";
            String msg="Dear "+user+",\n Secrete key to download the Document Having title "+title+" is  "+key+" .\n\n Thanks.\n  Admininstrator";
            
            if(obj.sendMail(msg,email,sub))
            {
                return true;
            }
            else
                return false;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
 public boolean sendOTPDetails(String title,String user,String email,String key)
    {
        try{
            Mail obj=new Mail();
            String sub="Document Details";
            String msg="Dear "+user+",\n OTP to download the Document Having title "+title+" is  "+key+" .\n\n Thanks.\n  Admininstrator";
            
            if(obj.sendMail(msg,email,sub))
            {
                return true;
            }
            else
                return false;
        }
        catch(Exception ex)
        {
            return false;
        }
    }

     
    
  
public static double monthsBetween(Calendar date1, Calendar date2){
        double monthsBetween = 0;
        //difference in month for years
        monthsBetween = (date1.get(Calendar.YEAR)-date2.get(Calendar.YEAR))*12;
        //difference in month for months
        monthsBetween += date1.get(Calendar.MONTH)-date2.get(Calendar.MONTH);
        //difference in month for days
        if(date1.get(Calendar.DAY_OF_MONTH)!=date1.getActualMaximum(Calendar.DAY_OF_MONTH)
                && date1.get(Calendar.DAY_OF_MONTH)!=date1.getActualMaximum(Calendar.DAY_OF_MONTH) ){
            monthsBetween += ((date1.get(Calendar.DAY_OF_MONTH)-date2.get(Calendar.DAY_OF_MONTH))/31d);
        }
        return Math.round(monthsBetween);
    }


  
  
       public static void fdelete(File file)
    	throws IOException{
 
    	if(file.isDirectory()){
 
    		//directory is empty, then delete it
    		if(file.list().length==0){
 
    		   file.delete();
    		   System.out.println("Directory is deleted : " 
                                                 + file.getAbsolutePath());
 
    		}else{
 
    		   //list all the directory contents
        	   String files[] = file.list();
 
        	   for (String temp : files) {
        	      //construct the file structure
        	              File fileDelete = new File(file, temp);
 
        	      //recursive delete
        	     fdelete(fileDelete);
        	   }
 
        	   //check the directory again, if empty then delete it
        	   if(file.list().length==0){
           	     file.delete();
        	     System.out.println("Directory is deleted : " 
                                                  + file.getAbsolutePath());
        	   }
    		}
 
    	}else{
    		//if file, then delete it
            String[] str=file.getName().split("\\.");
            if(!str[1].equals("des"))
    		file.delete();
    		System.out.println("File is deleted : " + file.getAbsolutePath());
    	}
    }
 

      public List getMyDocs(String userid,String clientid,String sts)
     {
        List<Uploads> lstdoc=new ArrayList<Uploads>();
        int crid=0;
         try
         { 
               //  session.beginTransaction();
             String qr="select * from uploads where userid='"+userid+"'  and status='"+sts+"'";
             System.out.println("qr="+qr);
		 Vector v = getValue(qr,18);
                  for(int i=0;i<v.size();i=i+18)
                  {
                      Uploads obj=new Uploads();
                      obj.setUploadId(Integer.parseInt(v.elementAt(i).toString()));
                      obj.setTitle((v.elementAt(i+1).toString()));
                      obj.setUserid((v.elementAt(i+2).toString()));
                      obj.setUserName((v.elementAt(i+3).toString()));
                      obj.setDt((v.elementAt(i+4).toString()));
                      obj.setTm((v.elementAt(i+5).toString()));
                      obj.setIntro((v.elementAt(i+6).toString()));
                      obj.setPath((v.elementAt(i+7).toString()));
                      obj.setFilesize((v.elementAt(i+9).toString()));
                      obj.setExt(v.elementAt(i+10).toString());
                      obj.setStatus((v.elementAt(i+11).toString()));
                      obj.setClientId((v.elementAt(i+12).toString()));
                      obj.setBranch((v.elementAt(i+13).toString()));
                      obj.setHashval((v.elementAt(i+14).toString()));
                      lstdoc.add(obj);
                  }
                 
         }
         catch(Exception ex)
         {
             System.out.println("errr="+ex.getMessage());
         }
         return lstdoc;
     }
      
        public List getOtherDocs(String clientid,String branch)
     {
        List<Uploads> lstdoc=new ArrayList<Uploads>();
        int crid=0;
         try
         {
             String qr="select * from uploads where  clientId='"+clientid+"' and status='active' branch='"+branch+"'";
                   Vector v = getValue(qr,18);
                  for(int i=0;i<v.size();i=i+18)
                  {
                      Uploads obj=new Uploads();
                      obj.setUploadId(Integer.parseInt(v.elementAt(i).toString()));
                      obj.setTitle((v.elementAt(i+1).toString()));
                      obj.setUserid((v.elementAt(i+2).toString()));
                      obj.setUserName((v.elementAt(i+3).toString()));
                      obj.setDt((v.elementAt(i+4).toString()));
                      obj.setTm((v.elementAt(i+5).toString()));
                      obj.setIntro((v.elementAt(i+6).toString()));
                      obj.setPath((v.elementAt(i+7).toString()));
                      obj.setFilesize((v.elementAt(i+9).toString()));
                      obj.setExt(v.elementAt(i+10).toString());
                      obj.setStatus((v.elementAt(i+11).toString()));
                      obj.setClientId((v.elementAt(i+12).toString()));
                      obj.setBranch((v.elementAt(i+13).toString()));
                      obj.setHashval((v.elementAt(i+14).toString()));
                      lstdoc.add(obj);
                  }
                 
         }
         catch(Exception ex)
         {
             
         }
         return lstdoc;
     }
      
    public List getAllDocs(String clientid)
     {
        List<Uploads> lstdoc=new ArrayList<Uploads>();
        int crid=0;
         try
         {
                String qr="select * from uploads where  clientId='"+clientid+"' and status='active' ";
                  Vector v = getValue(qr,18);
                  for(int i=0;i<v.size();i=i+18)
                  {
                      Uploads obj=new Uploads();
                      obj.setUploadId(Integer.parseInt(v.elementAt(i).toString()));
                      obj.setTitle((v.elementAt(i+1).toString()));
                      obj.setUserid((v.elementAt(i+2).toString()));
                      obj.setUserName((v.elementAt(i+3).toString()));
                      obj.setDt((v.elementAt(i+4).toString()));
                      obj.setTm((v.elementAt(i+5).toString()));
                      obj.setIntro((v.elementAt(i+6).toString()));
                      obj.setPath((v.elementAt(i+7).toString()));
                      obj.setFilesize((v.elementAt(i+9).toString()));
                      obj.setExt(v.elementAt(i+10).toString());
                      obj.setStatus((v.elementAt(i+11).toString()));
                      obj.setClientId((v.elementAt(i+12).toString()));
                      obj.setBranch((v.elementAt(i+13).toString()));
                      obj.setHashval((v.elementAt(i+14).toString()));
                      lstdoc.add(obj);
                  }
         }
         catch(Exception ex)
         {
             
         }
         return lstdoc;
     }

}
