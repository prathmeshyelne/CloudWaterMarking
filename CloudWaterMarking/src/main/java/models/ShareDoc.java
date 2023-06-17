package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import services.DBConnector;

public class ShareDoc {
private String userid,memUserid,groupName,memUserName,memId,dob,mobile,email,addr,msgid;
 
Connection con;
CallableStatement csmt;
ResultSet rs;
 
public String getDob() {
	return dob;
}

public String getGroupName() {
	return groupName;
}

public void setGroupName(String groupName) {
	this.groupName = groupName;
}

public String getMsgid() {
	return msgid;
}

public void setMsgid(String msgid) {
	this.msgid = msgid;
}

public void setDob(String dob) {
	this.dob = dob;
}

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getAddr() {
	return addr;
}

public void setAddr(String addr) {
	this.addr = addr;
}

 

public String getUserid() {
	return userid;
}

 

public String getMemId() {
	return memId;
}

public void setMemId(String memId) {
	this.memId = memId;
}

 

public void setUserid(String userid) {
	this.userid = userid;
}

public String getMemUserid() {
	return memUserid;
}

public void setMemUserid(String memUserid) {
	this.memUserid = memUserid;
}

public String getMemUserName() {
	return memUserName;
}

public void setMemUserName(String memUserName) {
	this.memUserName = memUserName;
}
public boolean registration()
{
    try
    {
    	  
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call insertAccesPerDoc(?,?,?)}");
        csmt.setString(1, userid);
        csmt.setString(2, memUserid);
        csmt.setString(3, msgid); 
         
         int n=csmt.executeUpdate();
         
                    
        
        if(n>0)
        {
            try{con.close();}catch(Exception ex){}
            System.out.println("true");
            return true;
        }
        else
            return false;

        }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
        return false;
    }
}
public boolean shareDocsWithGroups()
{
    try
    {
    	  
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call insertAccesPerDocGroup(?,?,?)}");
        csmt.setString(1, userid);
        csmt.setString(2, groupName);
        csmt.setString(3, msgid); 
         
         int n=csmt.executeUpdate();
         
                    
        
        if(n>0)
        {
            try{con.close();}catch(Exception ex){}
            System.out.println("true");
            return true;
        }
        else
            return false;

        }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
        return false;
    }
}
public boolean delete()
{
    try
    {
    	  
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call deleteAccessPerDoc(?)}");
        csmt.setString(1, memId);
         
         int n=csmt.executeUpdate();
         
                    
        
        if(n>0)
        {
            try{con.close();}catch(Exception ex){}
            System.out.println("true");
            return true;
        }
        else
            return false;

        }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
        return false;
    }
}
 public String  getSharedMsg( String userid1)
{
	String msgids="";
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        
        csmt=con.prepareCall("{call getsharedDoc(?)}");
     
        System.out.println("userid="+userid1);
        csmt.setString(1, userid1);
	   
         csmt.execute();
         rs=csmt.getResultSet();
         
        while(rs.next())
        {  
        	 if(msgids.equals(""))
        	 {
        		 msgids=rs.getString("msgid");
        	 }
        	 else
        	 {
        		 msgids+=","+rs.getString("msgid");
        	 }
              
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err in getuser="+ex.getMessage());
         
    }
    return msgids;
} 
}
