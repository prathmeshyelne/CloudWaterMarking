package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;
 
import services.DBConnector;

public class UserMsg {
private String resuserName,resuserid,encsts,groupMsg,userid,seckey,dt,tm,username;
private int groupId,msgId;
private List<UserMsg> lstgroups = new ArrayList<UserMsg>();
Connection con;
CallableStatement csmt;
ResultSet rs;
 
public String getResuserName() {
	return resuserName;
}
public void setResuserName(String resuserName) {
	this.resuserName = resuserName;
}
public String getResuserid() {
	return resuserid;
}
public void setResuserid(String resuserid) {
	this.resuserid = resuserid;
}
public int getMsgId() {
	return msgId;
}
public void setMsgId(int msgId) {
	this.msgId = msgId;
}
public String getEncsts() {
	return encsts;
}
public void setEncsts(String encsts) {
	this.encsts = encsts;
}
public String getGroupMsg() {
	return groupMsg;
}
public void setGroupMsg(String groupMsg) {
	this.groupMsg = groupMsg;
}
public String getSeckey() {
	return seckey;
}
public void setSeckey(String seckey) {
	this.seckey = seckey;
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
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public List<UserMsg> getLstgroups() {
	return lstgroups;
}
public void setLstgroups(List<UserMsg> lstgroups) {
	this.lstgroups = lstgroups;
}
public int getGroupId() {
	return groupId;
}
public void setGroupId(int groupId) {
	this.groupId = groupId;
}
 
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
} 
public UserMsg()
{
	
}
public UserMsg(ResultSet rs)
{
	try
	{
	msgId=rs.getInt("gcId");
	resuserName=rs.getString("resName").toString().trim();
	resuserid=rs.getString("resUserid").toString().trim();
	
	//groupMsg=CaesarCipher.decrypt(rs.getString("msg").toString().trim(),Integer.parseInt(rs.getString("skey").toString().trim()));
	groupMsg= (rs.getString("msg").toString().trim() );
	dt=rs.getString("dt").toString().trim();
	tm=rs.getString("tm").toString().trim();
	userid=rs.getString("userid").toString().trim();
	username=rs.getString("userName").toString().trim();
	encsts=rs.getString("encSts").toString().trim();
	seckey=rs.getString("skey").toString().trim();
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err in gpmsg="+e.getMessage());
	}
}
public void getGroupMessages()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getSharedMessages(?)}");
       csmt.setString(1, userid);
         csmt.execute();
         rs=csmt.getResultSet();
         lstgroups=new ArrayList<UserMsg>();       
        while(rs.next())
        { System.out.println("true");
        lstgroups.add(new UserMsg(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public void getGroupMessages1()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getGroupMsg1(?)}");
       csmt.setString(1, resuserid);
         csmt.execute();
         rs=csmt.getResultSet();
         lstgroups=new ArrayList<UserMsg>();       
        while(rs.next())
        { System.out.println("true");
        lstgroups.add(new UserMsg(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
 
public boolean registration()
{
	boolean flag=false;
    try
    { 
    	Date dt1=new Date();
    	dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear());
        tm=dt1.getHours()+":"+dt1.getMinutes(); 
    	DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call insertCommunication(?,?,?,?,?,?,?,?,?,?)}");
        csmt.setString(1, userid);
        csmt.setString(2, username);
        csmt.setString(3, resuserid);
        csmt.setString(4, resuserName);
       // Random rnd=new Random();
       // int key=rnd.nextInt(20);
        //csmt.setString(5, CaesarCipher.encrypt(groupMsg, key));
        csmt.setString(5, groupMsg);
        
        csmt.setString(6, seckey.toLowerCase());
        csmt.setString(7, "encrypted");
        csmt.setString(8, dt);
        csmt.setString(9, tm);
        csmt.setString(10,encsts);
         csmt.execute(); 
        rs=csmt.getResultSet();
        while(rs.next())
        {
            msgId=rs.getInt("mxid");
            System.out.println("true");
             flag=true;
        }
        try{con.close();}catch(Exception ex){}
        

        }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
    return flag;
}
}
