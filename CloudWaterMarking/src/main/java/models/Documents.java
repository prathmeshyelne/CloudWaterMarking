package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import services.*;

public class Documents {
private String docid,userid,username,seckey,otp,uotp,title,docdesc,dt,tm,filePath;
private List<Documents> lst;
Connection con;
CallableStatement csmt;
ResultSet rs;
 
public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public List<Documents> getLst() {
	return lst;
}

public void setLst(List<Documents> lst) {
	this.lst = lst;
}

public String getOtp() {
	return otp;
}

public void setOtp(String otp) {
	this.otp = otp;
}

public String getUotp() {
	return uotp;
}

public void setUotp(String uotp) {
	this.uotp = uotp;
}

public String getSeckey() {
	return seckey;
}

public void setSeckey(String seckey) {
	this.seckey = seckey;
}

public String getDocid() {
	return docid;
}

public void setDocid(String docid) {
	this.docid = docid;
}

public String getUserid() {
	return userid;
}

public void setUserid(String userid) {
	this.userid = userid;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getDocdesc() {
	return docdesc;
}

public void setDocdesc(String docdesc) {
	this.docdesc = docdesc;
}

public String getDt() {
	return dt;
}

public void setDt(String dt) {
	this.dt = dt;
}

public String getTm() {
	return tm;
}

public void setTm(String tm) {
	this.tm = tm;
}

public String getFilePath() {
	return filePath;
}

public void setFilePath(String filePath) {
	this.filePath = filePath;
}
public Documents()
{
	
}
public Documents(ResultSet rs)
{
	try
	{docid=rs.getString("docid").trim();
		title=rs.getString("title").trim();
		seckey=rs.getString("skey").trim();
   	 dt=rs.getString("dt").trim();
   	 tm=rs.getString("tm").trim();
   	 docdesc=rs.getString("docdesc").trim();
   	 
   	 filePath=rs.getString("filePath").trim();
 	userid=rs.getString("userid").trim();
   	username=rs.getString("userName").trim();
   
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
public void getDocs(String userid)
{
	boolean flag=false;
	  lst=new ArrayList<Documents>();
    try
    {
         DBConnector obj=new  DBConnector();
         
        con=obj.connect();
        csmt=con.prepareCall("{call getDocuments1(?)}");
         
        csmt.setString(1, userid);
        
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        {  
        	lst.add(new Documents(rs));
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err in api="+ex.getMessage());
         
    }
    
}
public void getUserDocs(String docids)
{
	boolean flag=false;
  lst=new ArrayList<Documents>();
    try
    {
         DBConnector obj=new  DBConnector();
         
        con=obj.connect();
        Statement st=con.createStatement();
        System.out.println("qr="+"select * from getdocuments where  docid in("+docids+")");
		rs=st.executeQuery("select * from getdocuments where  docid in("+docids+")");
                     
        while(rs.next())
        {  
        	lst.add(new Documents(rs));
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err in api="+ex.getMessage());
         
    }
     
}
}
