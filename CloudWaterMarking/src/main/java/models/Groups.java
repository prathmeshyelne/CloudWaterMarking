package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import services.DBConnector;

public class Groups {
private String groupName,groupDesc,userid;
private int groupId;
private List<Groups> lstgroups = new ArrayList<Groups>();
Connection con;
CallableStatement csmt;
ResultSet rs;

public int getGroupId() {
	return groupId;
}
public void setGroupId(int groupId) {
	this.groupId = groupId;
}
public String getGroupName() {
	return groupName;
}
public void setGroupName(String groupName) {
	this.groupName = groupName;
}
public String getGroupDesc() {
	return groupDesc;
}
public void setGroupDesc(String groupDesc) {
	this.groupDesc = groupDesc;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public List<Groups> getLstgroups() {
	return lstgroups;
}
public void setLstgroups(List<Groups> lstgroups) {
	this.lstgroups = lstgroups;
}
public Groups()
{
	
}
public Groups(ResultSet rs)
{
	try
	{
		
	groupName=rs.getString("groupName").toString().trim();
	groupId=rs.getInt("gid");
	groupDesc=rs.getString("groupDesc").toString().trim();
	
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err in group="+e.getMessage());
	}
}
public void getGroups(String userid1)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getGroups(?)}");
       csmt.setString(1, userid1);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstgroups.add(new Groups(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public void getGroups2(String userid1)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getGroups2(?)}");
       csmt.setString(1, userid1);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstgroups.add(new Groups(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public void getGroups1(String userid1)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getGroups1(?)}");
       csmt.setString(1, userid1);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstgroups.add(new Groups(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public void getGroups2(String userid1,String docid)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getGroups2(?,?)}");
       csmt.setString(1, userid1);
       csmt.setInt(2, Integer.parseInt(docid));
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstgroups.add(new Groups(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public boolean registration()
{
    try
    {
    	 
    	String bodycond="NA";
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call insertGroups(?,?,?)}");
        csmt.setString(1, userid);
        csmt.setString(2, groupName);
        csmt.setString(3, groupDesc);
         
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
}
