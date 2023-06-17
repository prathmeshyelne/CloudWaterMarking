package models;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import services.DBConnector;
 

public class GroupDoc {
private String groupName,path,userid,infilepath,outfilepath,qrfilePath,key,clkey,title,dt,tm,gid,otp,sentotp;
private List<GroupDoc> lstgroups = new ArrayList<GroupDoc>();
Connection con;
CallableStatement csmt;
ResultSet rs;
private MultipartFile file;
public String getOtp() {
	return otp;
}


public void setOtp(String otp) {
	this.otp = otp;
}


public MultipartFile getFile() {
	return file;
}


public void setFile(MultipartFile file) {
	this.file = file;
}


public String getSentotp() {
	return sentotp;
}


public void setSentotp(String sentotp) {
	this.sentotp = sentotp;
}


public String getQrfilePath() {
	return qrfilePath;
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


public String getGid() {
	return gid;
}


public void setGid(String gid) {
	this.gid = gid;
}


public void setQrfilePath(String qrfilePath) {
	this.qrfilePath = qrfilePath;
}
public String getInfilepath() {
	return infilepath;
}
public void setInfilepath(String infilepath) {
	this.infilepath = infilepath;
}
public String getOutfilepath() {
	return outfilepath;
}
public void setOutfilepath(String outfilepath) {
	this.outfilepath = outfilepath;
}
public String getGroupName() {
	return groupName;
}
public void setGroupName(String groupName) {
	this.groupName = groupName;
}
 
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
 

public List<GroupDoc> getLstgroups() {
	return lstgroups;
}
public void setLstgroups(List<GroupDoc> lstgroups) {
	this.lstgroups = lstgroups;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}
public String getClkey() {
	return clkey;
}
public void setClkey(String clkey) {
	this.clkey = clkey;
}
 
public String getPath() {
	return path;
}


public void setPath(String path) {
	this.path = path;
}


public GroupDoc()
{
	
}
public boolean delete()
{
    try
    {
    	  
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call deleteGroupDoc(?,?)}");
        csmt.setString(1, gid);
        csmt.setString(2, userid);
         
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
public GroupDoc(ResultSet rs)
{
	try
	{
	groupName=rs.getString("groupName").toString().trim();
	dt=rs.getString("dt").toString().trim();
	userid=rs.getString("userid").toString().trim();
	tm=rs.getString("tm").toString().trim();
	title=rs.getString("title").toString().trim();
	gid=rs.getString("gid").toString().trim();
	path=rs.getString("path").toString().trim();
	}
	catch (Exception e) {
		// TODO: handle exception
	}
}
public GroupDoc(ResultSet rs,String gl)
{
	try
	{
	groupName=rs.getString("groupName").toString().trim();
	dt=rs.getString("dt").toString().trim();
	userid=rs.getString("userid").toString().trim();
	tm=rs.getString("tm").toString().trim();
	title=rs.getString("title").toString().trim();
	gid=rs.getString("gid").toString().trim();
	clkey=rs.getString("clientKey").toString().trim();
	key=rs.getString("serverKey").toString().trim();
	path=rs.getString("path").toString().trim();
	}
	catch (Exception e) {
		// TODO: handle exception
	}
}
public void getGroupMsg(String userid1)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getGroupDoc(?)}");
       csmt.setString(1, userid1);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstgroups.add(new GroupDoc(rs));
              
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public String decryptMsg()
{
	String decryptedMsg="";
    try
    {
    	// Base64Decoder decoder=new Base64Decoder();
     //	byte[] encbytes= decoder.decodeBytesFromBase64String(key);
     	//DecryptionLong decobj=new DecryptionLong();
     //	decobj.setSkey(key);
     //	Path path = Paths.get(infilepath);
  	//  Files.write(path, encbytes);
  	  //decobj.decrypt(infilepath, outfilepath);
  	 
  //	 Base64Encoder encoderobj=new Base64Encoder();
 	//  decryptedMsg= encoderobj.encodeFileToBase64Binary(b);
     //	decobj.decrypt(inFile, outpath);
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
    return outfilepath;
}
public List<Documents> getsharedDocGroup()
{
	List<Documents> lst=new ArrayList<Documents>();
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getsharedDocGroup(?)}");
       csmt.setString(1, groupName);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true in group");
        lst.add(new Documents(rs));
              
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
    return lst;
}
public void getGroupMessages()
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getGroupDoc1(?)}");
       csmt.setString(1, groupName);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstgroups.add(new GroupDoc(rs,"group"));
              
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
    	// System.out.println("msg="+encmsg+" msg="+msg);  
    	//EncryptionLong encobj=new EncryptionLong();
    	//String skey=RandomString.getAlphaNumericString(8);
    //	encobj.setSkey(skey);
    	//Path path = Paths.get(infilepath);
    	//  Files.write(path, skey.getBytes());
    	
    	//encobj.encrypt(infilepath, outfilepath);
    	// byte[] b= skey.getBytes();
    //	 Base64Encoder encoderobj=new Base64Encoder();
    //	String keyqr= encoderobj.encodeFileToBase64Binary(b);
    	 
    	
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        String dt11,tm11="";
        Date dt1=new Date();
        dt11=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
        tm11=dt1.getHours()+":"+dt1.getMinutes();
        csmt=con.prepareCall("{call insertGroupDoc(?,?,?,?,?,?,?,?)}");
        csmt.setString(1, userid);
        csmt.setString(2, groupName);
        csmt.setString(3, path);
        csmt.setString(4, dt11);
        csmt.setString(5,tm11);
        csmt.setString(6,key);
        csmt.setString(7,"NA");
        csmt.setString(8,title);
         ResultSet rs=csmt.executeQuery();
         String msgid="";
         int n=0;
         while(rs.next())
         {
        	 n++;
        	 msgid=rs.getString("msgid").toString().trim();
         }
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
/*public boolean registration()
{
    try
    {
    	 System.out.println("msg="+encmsg+" msg="+msg);  
    	EncryptionLong encobj=new EncryptionLong();
    	String skey=RandomString.getAlphaNumericString(8);
    	encobj.setSkey(skey);
    	Path path = Paths.get(infilepath);
    	  Files.write(path, encmsg.getBytes());
    	
    	encobj.encrypt(infilepath, outfilepath);
    	 byte[] b= Files.readAllBytes(Paths.get(outfilepath));
    	 Base64Encoder encoderobj=new Base64Encoder();
    	String encryptedMsg= encoderobj.encodeFileToBase64Binary(b);
    	 
    	
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        String dt11,tm11="";
        Date dt1=new Date();
        dt11=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
        tm11=dt1.getHours()+":"+dt1.getMinutes();
        csmt=con.prepareCall("{call insertGroupMsg(?,?,?,?,?,?,?,?)}");
        csmt.setString(1, userid);
        csmt.setString(2, groupName);
        csmt.setString(3, encryptedMsg);
        csmt.setString(4, dt11);
        csmt.setString(5,tm11);
        csmt.setString(6,key);
        csmt.setString(7,skey);
        csmt.setString(8,title);
         ResultSet rs=csmt.executeQuery();
         String msgid="";
         int n=0;
         while(rs.next())
         {
        	 n++;
        	 msgid=rs.getString("msgid").toString().trim();
         }
                    
         GenerateQRCode.QRgenerater("http:\\192.168.43.253:8080/enterOTP?msg="+encryptedMsg+"&mid="+msgid, qrfilePath+"/"+msgid+".png");
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
}*/
}
