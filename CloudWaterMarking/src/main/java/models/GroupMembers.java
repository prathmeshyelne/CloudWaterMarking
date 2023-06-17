package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import services.DBConnector;

public class GroupMembers {
private String groupName,userid,memUserid,memUserName,memId,dob,mobile,email,addr;
private List<GroupMembers> lstmem;
Connection con;
CallableStatement csmt;
ResultSet rs;
public String getGroupName() {
	return groupName;
}

public String getDob() {
	return dob;
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

public void setGroupName(String groupName) {
	this.groupName = groupName;
}

public String getUserid() {
	return userid;
}

public List<GroupMembers> getLstmem() {
	return lstmem;
}

public String getMemId() {
	return memId;
}

public void setMemId(String memId) {
	this.memId = memId;
}

public void setLstmem(List<GroupMembers> lstmem) {
	this.lstmem = lstmem;
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
        csmt=con.prepareCall("{call insertGroupMem(?,?,?,?)}");
        csmt.setString(1, userid);
        csmt.setString(2, groupName);
        csmt.setString(3, memUserid);
        csmt.setString(4, memUserName);
         
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
        csmt=con.prepareCall("{call deleteGroupMem(?)}");
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
public void getMembers( String userid1,String groupName)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        
        csmt=con.prepareCall("{call getGroupMembers(?,?)}");
        System.out.println("group="+groupName);
        System.out.println("userid="+userid1);
        csmt.setString(1, userid1);
	    csmt.setString(2, groupName);
         csmt.execute();
         rs=csmt.getResultSet();
        
     
         lstmem=new ArrayList<GroupMembers>();
        while(rs.next())
        {  
        	GroupMembers uobj=new GroupMembers();
	        uobj.setUserid(rs.getString("userid").toString().trim());
	        uobj.setMemUserid(rs.getString("memUserid").toString().trim());
	        uobj.setMemId(rs.getString("memId").toString().trim());
	        uobj.setGroupName(rs.getString("groupName").toString().trim());
	        uobj.setMemUserName(rs.getString("memberName").toString().trim());
	        uobj.setDob(rs.getString("dob").toString().trim());
	        uobj.setMobile(rs.getString("mobile").toString().trim());
	        uobj.setEmail(rs.getString("email").toString().trim());
	        uobj.setAddr(rs.getString("addr").toString().trim());
	         
	        lstmem.add(uobj);
              
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err in getuser="+ex.getMessage());
         
    }
}
}
