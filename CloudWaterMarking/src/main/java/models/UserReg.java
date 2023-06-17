package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
import services.DBConnector;
public class UserReg {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private String name,mobile,email,city,addr, gender,state,userid,pass,dob,otp;
    private List<UserReg> lstuser;
    
    public UserReg() {}
    
     
 

	public String getOtp() {
		return otp;
	}



	public void setOtp(String otp) {
		this.otp = otp;
	}



	public List<UserReg> getLstuser() {
		return lstuser;
	}



	public void setLstuser(List<UserReg> lstuser) {
		this.lstuser = lstuser;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getAddr() {
		return addr;
	}



	public void setAddr(String addr) {
		this.addr = addr;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getPass() {
		return pass;
	}



	public void setPass(String pass) {
		this.pass = pass;
	}



	public String getDob() {
		return dob;
	}



	public void setDob(String dob) {
		this.dob = dob;
	}



	/*public void getId()
	    {
	        try
	        {
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call getMaxIdUsers()}");
	           
	             csmt.execute();
	             rs=csmt.getResultSet();
	                        
	            boolean auth=false;
	            while(rs.next())
	            { System.out.println("true");
	                auth=true;
	                
	                mxid=rs.getInt("mxid");
	                  
	            }
	        }
	           
	         
	        catch(Exception ex)
	        {
	            System.out.println("err="+ex.getMessage());
	             
	        }
	    }*/
	public boolean registration()
	    {
	        try
	        {
	        	String bodycond="NA";
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call insertUser(?,?,?,?,?,?,?,?,?,?)}");
	            csmt.setString(1, userid);
	            csmt.setString(2, pass);
	            csmt.setString(3, name);
	            csmt.setString(4, mobile);
	            csmt.setString(5, email);
	        
	          
	            csmt.setString(6, city);
	            csmt.setString(7, state);
	        
	            csmt.setString(8, gender);
	            csmt.setString(9, addr);
	            csmt.setString(10, dob);
	          
	            
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
	public void getUsers(String searchText,String userid1,String groupName)
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	       if(searchText.trim().equals("NA"))
	        {
	        csmt=con.prepareCall("{call getAllUsers(?,?)}");
	        System.out.println("group="+groupName);
	        System.out.println("userid="+userid1);
	        csmt.setString(1, groupName);
  	        csmt.setString(2,userid1 );
	         csmt.execute();
	         rs=csmt.getResultSet();
	        }
	        else
	        {
	        	  csmt=con.prepareCall("{call getUsers(?,?,?)}");
	        	  csmt.setString(1, searchText);
	        	  csmt.setString(2, groupName);
	  	        csmt.setString(3,userid1 );
	 	         csmt.execute();
	 	         rs=csmt.getResultSet();
	        } 
	     
	                     lstuser=new ArrayList<UserReg>();
	        while(rs.next())
	        { System.out.println("true");
	        System.out.println("in getall users");
	        UserReg uobj=new UserReg();
	        uobj.setUserid(rs.getString("userid").toString().trim());
	        uobj.setName(rs.getString("userName").toString().trim());
	        uobj.setEmail(rs.getString("email").toString().trim());
	        System.out.println("userid="+rs.getString("userid").toString().trim()+" "+rs.getString("userName").toString().trim()+" "+rs.getString("email").toString().trim());
	        lstuser.add(uobj);
	              
	        }
	    } 
	    catch(Exception ex)
	    {
	        System.out.println("err in getuser="+ex.getMessage());
	         
	    }
	}
	public void getUsers1(String searchText,String userid1,String groupName)
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	       if(searchText.trim().equals("NA"))
	        {
	        csmt=con.prepareCall("{call getAllUsers1(?)}");
	       // System.out.println("group="+groupName);
	        System.out.println("userid="+userid1);
	        csmt.setString(1, userid1);
  	        
	         csmt.execute();
	         rs=csmt.getResultSet();
	        }
	        else
	        {
	        	  csmt=con.prepareCall("{call getUsers(?,?,?)}");
	        	  csmt.setString(1, searchText);
	        	  csmt.setString(2, groupName);
	  	        csmt.setString(3,userid1 );
	 	         csmt.execute();
	 	         rs=csmt.getResultSet();
	        } 
	     
	                     lstuser=new ArrayList<UserReg>();
	        while(rs.next())
	        { System.out.println("true");
	        System.out.println("in getall users");
	        UserReg uobj=new UserReg();
	        uobj.setUserid(rs.getString("userid").toString().trim());
	        uobj.setName(rs.getString("userName").toString().trim());
	        uobj.setEmail(rs.getString("email").toString().trim());
	        System.out.println("userid="+rs.getString("userid").toString().trim()+" "+rs.getString("userName").toString().trim()+" "+rs.getString("email").toString().trim());
	        lstuser.add(uobj);
	              
	        }
	    } 
	    catch(Exception ex)
	    {
	        System.out.println("err in getuser="+ex.getMessage());
	         
	    }
	}
	public void getUsersSearch(String searchText,String userid1)
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	       
	        csmt=con.prepareCall("{call getAllUsersSearch(?,?)}");
	     
	        System.out.println("userid="+userid1+"search="+searchText);
	        csmt.setString(1, searchText);
	        csmt.setString(2, userid1);
	         csmt.execute();
	         rs=csmt.getResultSet();
	        
	     
	                     lstuser=new ArrayList<UserReg>();
	        while(rs.next())
	        { System.out.println("true");
	        System.out.println("in getall users");
	        UserReg uobj=new UserReg();
	        uobj.setUserid(rs.getString("userid").toString().trim());
	        uobj.setName(rs.getString("userName").toString().trim());
	        uobj.setEmail(rs.getString("email").toString().trim());
	        System.out.println("userid="+rs.getString("userid").toString().trim()+" "+rs.getString("userName").toString().trim()+" "+rs.getString("email").toString().trim());
	        lstuser.add(uobj);
	              
	        }
	    } 
	    catch(Exception ex)
	    {
	        System.out.println("err in getuser="+ex.getMessage());
	         
	    }
	}
	public UserReg(ResultSet rs)
	{
		try
		{
		addr=rs.getString("addr").toString().trim();
		userid=rs.getString("userid").toString().trim();
		city=rs.getString("city").toString().trim();
		state=rs.getString("state").toString().trim();
		name=rs.getString("userName").toString().trim();
		email=rs.getString("email").toString().trim();
		mobile=rs.getString("mobile").toString().trim();
		gender=rs.getString("gender").toString().trim();
		dob=rs.getString("dob").toString().trim();
	 	}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err="+e.getMessage());
		}
	}
	public void getAllUsers(String userid1)
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        
	        	  csmt=con.prepareCall("{call getAllUsers1(?)}");
	        	  csmt.setString(1, userid1);
	        	 
	 	         csmt.execute();
	 	         rs=csmt.getResultSet();
	          
	     
	                     lstuser=new ArrayList<UserReg>();
	        while(rs.next())
	        { System.out.println("true");
	        System.out.println("in getall users");
	        UserReg uobj=new UserReg();
	        uobj.setUserid(rs.getString("userid").toString().trim());
	        uobj.setName(rs.getString("userName").toString().trim());
	        uobj.setEmail(rs.getString("email").toString().trim());
	        System.out.println("userid="+rs.getString("userid").toString().trim()+" "+rs.getString("userName").toString().trim()+" "+rs.getString("email").toString().trim());
	        lstuser.add(uobj);
	              
	        }
	    } 
	    catch(Exception ex)
	    {
	        System.out.println("err in getuser="+ex.getMessage());
	         
	    }
	}
	public void getUsers1(String userid1)
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getUsers1(?)}");
	        lstuser=new ArrayList<UserReg>();
	         csmt.setString(1, userid1);
	         csmt.execute();
	         rs=csmt.getResultSet();
	                     
	        while(rs.next())
	        { System.out.println("true");
	        lstuser.add(new UserReg(rs));
	              
	        }
	    }
	       
	     
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}
	public void getAllUsersAdmin()
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        
	        	  csmt=con.prepareCall("{call getUsersAll()}");
	        	  
	        	 
	 	         csmt.execute();
	 	         rs=csmt.getResultSet();
	          
	     
	                     lstuser=new ArrayList<UserReg>();
	        while(rs.next())
	        { System.out.println("true");
	        System.out.println("in getall users");
	        UserReg uobj=new UserReg();
	        uobj.setUserid(rs.getString("userid").toString().trim());
	        uobj.setName(rs.getString("userName").toString().trim());
	        uobj.setEmail(rs.getString("email").toString().trim());
	        uobj.setMobile(rs.getString("mobile").toString().trim());
	        uobj.setAddr(rs.getString("addr").toString().trim());
	        uobj.setCity(rs.getString("city").toString().trim());
	        uobj.setState(rs.getString("state").toString().trim());
	        uobj.setDob(rs.getString("dob").toString().trim());
	        uobj.setGender(rs.getString("gender").toString().trim());
	        System.out.println("userid="+rs.getString("userid").toString().trim()+" "+rs.getString("userName").toString().trim()+" "+rs.getString("email").toString().trim());
	        lstuser.add(uobj);
	              
	        }
	    } 
	    catch(Exception ex)
	    {
	        System.out.println("err in getuser="+ex.getMessage());
	         
	    }
	}
	 
}
