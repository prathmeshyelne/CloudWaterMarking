package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import services.DBConnector;

public class CloudServices {
private String dataEncryption,documentEncryption,userid;
private List<CloudServices> lstservices = new ArrayList<CloudServices>();
Connection con;
CallableStatement csmt;
ResultSet rs;
  
public String getDataEncryption() {
	return dataEncryption;
}
public void setDataEncryption(String dataEncryption) {
	this.dataEncryption = dataEncryption;
}
public String getDocumentEncryption() {
	return documentEncryption;
}
public void setDocumentEncryption(String documentEncryption) {
	this.documentEncryption = documentEncryption;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public List<CloudServices> getLstservices() {
	return lstservices;
}
public void setLstservices(List<CloudServices> lstservices) {
	this.lstservices = lstservices;
}
public CloudServices()
{
	
}
public CloudServices(ResultSet rs)
{
	try
	{ 
		if(rs.getString("service").equals("Data_Encryption"))
		{
			dataEncryption=rs.getString("sts").toString().trim();
		}
		else
		{ 
			documentEncryption=rs.getString("sts").toString().trim();
		}
	
	
	}
	catch (Exception e) {
		// TODO: handle exception
	}
}
public void getSubscribedServices(String userid1)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getSubscribed(?)}");
       csmt.setString(1, userid1);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstservices.add(new CloudServices(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public void getDataEncryptionServiceSts(String userid1)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getDataEncSts(?)}");
       csmt.setString(1, userid1);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
         dataEncryption=rs.getString("dataEncryption").toString().trim(); 
         documentEncryption=rs.getString("documentEncryption").toString().trim();
         System.out.println("doc="+documentEncryption);
         System.out.println("doc="+dataEncryption);
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
 
public boolean subscribe()
{
    try
    {
    	 
    	String dt="";
    	Date dt1=new Date();
    	dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        if(dataEncryption==null)
        	dataEncryption="off";
        else
        	dataEncryption="on";
        if(documentEncryption==null)
        	documentEncryption="off";
        else
        	documentEncryption="on";
        csmt=con.prepareCall("{call insertSubscribedServices(?,?,?,?)}");
        csmt.setString(1, userid);
        csmt.setString(2, dataEncryption);
        csmt.setString(3, documentEncryption);
        csmt.setString(4, dt);
         
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
