package cloud.watermarking;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;
 
import models.CloudServices;
import models.Documents;
import models.GroupDoc;
import models.GroupMembers;
import models.Groups;
import models.GroupsMsg;
 
import models.Login;
import models.ShareDoc;
import models.UserMsg;
import models.UserReg;
import services.Base64Decoder;
import services.Base64Encoder;
import services.CloudFuns;
import services.GetURL;
import services.RC6;

@Controller
public class WaterMarkingController implements ErrorController {
	@RequestMapping("/home")
	public String home()
	{
		return "index.jsp";
	}
	@RequestMapping("/error")
    public String handleError() {
        //do something like logging
		return "home";
    }
	@SessionScope
	@RequestMapping("/sendOTP")
	public ModelAndView sendOTP(Documents eobj,HttpServletRequest request,HttpSession ses)
	{
	 ModelAndView mv=new ModelAndView();
	 
		 try {
			 eobj.setDocid( (request.getParameter("docId").trim()));
			 eobj.setFilePath(request.getParameter("path").trim());
			 eobj.setSeckey(request.getParameter("seckey").trim());
			 eobj.setUserid(ses.getAttribute("userid").toString().trim());
			 String otp=services.RandomString.getAlphaNumericString(4);
			    mv.setViewName("OTPVerification.jsp");
			    System.out.println("path="+eobj.getFilePath()+" skey="+eobj.getSeckey());
				mv.addObject("path",eobj.getFilePath());
				mv.addObject("docId",eobj.getDocid());
				mv.addObject("seckey",eobj.getSeckey());
				mv.addObject("otp",otp);
				services.Mail mail=new services.Mail();
				System.out.println("otp="+otp);
				CloudFuns jf=new CloudFuns();
				String qr="select email,userName from userdetails where userid='"+ses.getAttribute("userid").toString().trim()+"'";
				Vector v=jf.getValue(qr, 2);
				ses.setAttribute("username", v.elementAt(1).toString().trim());
				ses.setAttribute("email", v.elementAt(0).toString().trim());
				String msg="Dear "+ses.getAttribute("username").toString().trim()+", your one time password is "+otp;
				
				try
				{
				if(mail.sendMail(msg, ses.getAttribute("email").toString().trim(),"One time Password"))
				{
					
				}
				}
				catch (Exception e) {
					// TODO: handle exception
					System.out.println("err in mail="+e.getMessage());
				}
			  
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 mv.setViewName("Failure.jsp?type=sendOTP");
		}
		 return mv;
	}
	@SessionScope
	@RequestMapping("/OTPAuth")
	public ModelAndView OTPAuth(Documents eobj,HttpServletRequest request,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
	 
		 try {
			 
			 eobj.setUserid(ses.getAttribute("userid").toString().trim());
			 if(eobj.getOtp().equals(eobj.getUotp()))
			 {
				 System.out.println("otp verified");
				 Base64Encoder encoder=new  Base64Encoder();
				 mv.setViewName("gotoPython.jsp");
				 String seckey=request.getParameter("seckey").toString();
				 RC6 rc6=new RC6();
				 String fnm=request.getParameter("filePath").toString().trim().split("\\.")[0].trim();
				 String filepath=request.getServletContext().getRealPath("/")+"/Uploads/";
				 Path path= Paths.get(filepath+"/"+fnm+".txt");
				 byte[] b1=Files.readAllBytes(path);
				 byte[] b=rc6.decrypt(b1, request.getParameter("seckey").toString().trim().getBytes());
				 Base64Decoder decode=new Base64Decoder();
				 String str=new String(decode.decode(new String(b)));
				 System.out.println("str="+str);
				 String[] arr=str.split("\\,");
				 String param= arr[2].trim()+"|"+arr[3].trim()+"|"+arr[4].trim()+"|"+arr[5].trim()+"|"+request.getParameter("filePath").toString().trim() ;
				 System.out.println("param="+param);
				 param=encoder.encode(param.getBytes());
				 mv.addObject("path", GetURL.getPythonServerURL()+"DecryptDoc.py?param="+param);
				 
				 //mv.setViewName("Download.jsp");
				// mv.addObject("path","Uploads/temp/"+eobj.getDocpath());
			 }
			 else
			 { 
				mv.setViewName("Failure.jsp?type=OTPAuth"); 
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 mv.setViewName("Failure.jsp?type=OTPAuth"); 
		}
		 return mv;
	}
	@RequestMapping("/FromPythonDec")
   	public ModelAndView FromPythonDec(ServletRequest request)
   	{
   		ModelAndView mv=new ModelAndView();  
   		 try {
   			  
   			 
   			 String st=request.getParameter("sts").toString().trim();
   				 
   					mv.setViewName("download.jsp");
   				 mv.addObject("sts",st);
   		 }
   		 catch (Exception e) {
   			// TODO: handle exception
   			 mv.setViewName("Failure.jsp");
   		} 
   		
   		 return mv;
   	}
	@RequestMapping("/viewGroupMsg")
	public ModelAndView viewGroupMsg(HttpSession ses,HttpServletRequest request)
	{
		List<GroupsMsg> lst = new ArrayList<GroupsMsg>();
		GroupsMsg obj=new GroupsMsg();
		obj.setGroupId(Integer.parseInt(request.getParameter("groupId").toString().trim()));
		 obj.getGroupMessages();
		 lst=obj.getLstgroups();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewGroupMsg.jsp");
		mv.addObject("lst", lst);
		mv.addObject("groupName",request.getParameter("groupName").trim());
		return mv;
		 
	}
	@RequestMapping("/datasetInsrtPython")
   	public ModelAndView datasetInsrtPython(ServletRequest request)
   	{
   		ModelAndView mv=new ModelAndView();  
   		 try {
   			  
   			 
   			 String st=request.getParameter("sts").toString().trim();
   				if(st.equals("success"))
   				{String filepath=request.getServletContext().getRealPath("/")+"/Uploads/";
   			 
   					CloudFuns cf=new CloudFuns();
   					cf.recordusage(request.getParameter("userid").toString().trim(), "encryption");
   					System.out.println("path="+filepath);
   					File f=new File(filepath);
   					f.mkdir();
   					String metaData=request.getParameter("meta").toString().trim();
   					RC6 rc6=new RC6();
   					byte[] b=rc6.encrypt(metaData.getBytes(), request.getParameter("s").toString().trim().getBytes());
   					String docid=request.getParameter("docid").toString().trim();
   					try (FileOutputStream stream = new FileOutputStream(filepath+"/"+docid+".txt")) {
   					    stream.write(b);
   					}
   					catch(Exception ex){}
   					mv.setViewName("Success.jsp?type=DocUpload");
   				}
   				else
   					mv.setViewName("Failure.jsp");
   		 }
   		 catch (Exception e) {
   			// TODO: handle exception
   			 mv.setViewName("Failure.jsp");
   		} 
   		 mv.addObject("activity","DocUpload");
   		 return mv;
   		
   	}
	@RequestMapping("/shareWithGroups")
	public ModelAndView shareWithGroups(HttpSession ses,HttpServletRequest request)
	{
		List<Groups> lst = new ArrayList<Groups>();
		Groups obj=new Groups();
		 obj.getGroups2(ses.getAttribute("userid").toString().trim(),request.getParameter("docid").toString().trim());
		 lst=obj.getLstgroups();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("ShareDoc.jsp");
		mv.addObject("docid",request.getParameter("docid").trim());
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/ShareImgWithGroups")
	public ModelAndView SubmitReqDocs(HttpServletRequest request,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
		//System.out.println("docs="+request.getParameter("chkdoc").toString().trim());
		try {
		    Enumeration  e=request.getParameterNames();
		    String groupName="",docid="";
		    Vector v=new Vector();
		    while(e.hasMoreElements())
		    {
		    String Chknm=(String)e.nextElement();
		      if(Chknm.trim().equals("docid"))
		      {
		    	  docid=request.getParameter("docid").toString().trim();
		      }
		      else
		      {
		    	  v.addElement(Chknm.trim());
		      }
		      for(int i=0;i<v.size();i++)
		      {
		    	ShareDoc obj=new ShareDoc();
		    	obj.setGroupName(v.elementAt(i).toString().trim());
		    	obj.setUserid(ses.getAttribute("userid").toString().trim());
		    	obj.setMsgid(docid);
		    	obj.shareDocsWithGroups();
		      }
		    }
		        
		    mv.setViewName("Success.jsp?type=ShareDoc");
		    mv.addObject("activity","ShareDoc");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err="+e.getMessage());
		}
		return mv;
	}
	@RequestMapping("/CloudPayment")
	public String CloudPayment(HttpServletRequest request)
	{
		CloudFuns cf=new CloudFuns();
		if(cf.execute("update cloudpayments set status='paid' where id="+request.getParameter("id").trim()))
		{
			
		}
		return "Success.jsp?type=CloudPayment";
	}
	@RequestMapping("/uploadDocs")
	public String uploadDocs()
	{
		return "UploadDoc.jsp";
	}
	@RequestMapping("/viewMyDocs")
	@SessionScope
	public ModelAndView viewMyDocs(HttpSession ses) {
		ModelAndView mv=new ModelAndView();
		try
		{
		List<models.Documents> lst = new ArrayList<Documents>();
		//CallMinnerAPI vs = new CallMinnerAPI();
		Documents obj=new Documents();
		 
		  obj.getDocs(ses.getAttribute("userid").toString().trim());
		
		mv.addObject("std",obj.getLst());
		mv.setViewName("ViewMyDocs.jsp");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err="+e.getMessage());
		}
		return mv;
	}
	@RequestMapping("/CheckDoc")
	public ModelAndView CheckDoc(HttpServletRequest request,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
		try {
		if(request.getParameter("encsts").equals("on"))
		{
			mv.setViewName("sendOTP?dpath="+request.getParameter("path").trim()+"&docId="+request.getParameter("docId").trim()+"&type="+request.getParameter("type").trim());
		}
		else
		{
			mv.setViewName("Uploads/"+request.getParameter("path").trim());
		}
		CloudFuns cloud=new CloudFuns();
		cloud.recordusage(ses.getAttribute("userid").toString().trim(), "download");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err="+e.getMessage());
			mv.setViewName("Failure.jsp?type=ChkDoc");
		}
		return mv;
	}
	@RequestMapping("/viewGroupMsg1")
	public ModelAndView viewGroupMsg1(HttpSession ses,HttpServletRequest request)
	{
		List<GroupsMsg> lst = new ArrayList<GroupsMsg>();
		GroupsMsg obj=new GroupsMsg();
		obj.setGroupName(request.getParameter("groupName").toString().trim());
		 obj.getGroupMessages1();
		 lst=obj.getLstgroups();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewGroupMsg.jsp");
		mv.addObject("lst", lst);
		mv.addObject("groupName",request.getParameter("groupName").trim());
		return mv;
		 
	}
	@RequestMapping("/RegGroupMsg")
	public ModelAndView RegGroupMsg(GroupsMsg msg, HttpServletRequest request,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView();
		msg.setEncsts(ses.getAttribute("dataEncryption").toString().trim()); 
		String skey="";
		if(msg.getGroupMsg().length()>4)
			skey=services.RandomString.getAlphaNumericString1(4);
		else
			skey=services.RandomString.getAlphaNumericString1(msg.getGroupMsg().length());
		msg.setSeckey(skey.toLowerCase());
		if(msg.registration())
		{
			if(ses.getAttribute("dataEncryption").toString().trim().equals("on"))
			 {
				CloudFuns cf=new CloudFuns();
				 cf.recordusage(ses.getAttribute("userid").toString().trim(),"encryption");
				Base64Encoder encoder=new Base64Encoder();
				 mv.setViewName("gotoPython.jsp");
				 String param=String.valueOf(msg.getMsgId()+"|"+skey.toLowerCase());
				 System.out.println("param="+param);
				 param=encoder.encode(param.getBytes());
				 mv.addObject("path",GetURL.getPythonServerURL()+"EncryptMsg.py?param="+param);
			 }
			else
			{
			mv.setViewName("Success.jsp?type=GroupMsgReg");
			}
		}
		else
			mv.setViewName("Failure.jsp?type=GroupMsgReg");
		
		return mv;
	}
	@RequestMapping("/SendGroupMsg")
	public ModelAndView SendGroupMsg(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("SendGroupMsg.jsp");
		mv.addObject("groupName",request.getParameter("groupName").trim());
		mv.addObject("groupId",request.getParameter("groupId").trim());
		return mv;
	}
	@SessionScope
	@RequestMapping("/RegGroup")
	public String RegGroup1(Groups obj)
	{
		 try
		 { 
			 if(obj.registration())
			 {
				 
				 
				 return "Success.jsp?type=GroupReg";
			 }
			 else
			 { 
				 return "Failure.jsp?type=GroupReg";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=UserReg");
		}
	}
	@RequestMapping("/newGroup")
	public ModelAndView RegGroup(HttpSession ses)
	{
		List<Groups> lst = new ArrayList<Groups>();
		Groups obj=new Groups();
		 obj.getGroups(ses.getAttribute("userid").toString().trim());
		 lst=obj.getLstgroups();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("RegGroup.jsp");
		mv.addObject("lst", lst);

		return mv;
		 
	}
	@SessionScope
	@RequestMapping("/RegGroupMember")
	public ModelAndView RegGroupMember(GroupMembers obj)
	{ ModelAndView mv=new ModelAndView();
		 try
		 {  
			 if(obj.registration())
			 { 
				 mv.setViewName("Success.jsp?type=GroupMemReg");
				 mv.addObject("groupName",obj.getGroupName());
			 }
			 else
			 {  
				 mv.setViewName("Failure.jsp?type=GroupMemReg");		 
				 
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 mv.setViewName("Failure.jsp?type=GroupMemReg");
		}
		 return mv;
	}
	@SessionScope
	@RequestMapping("/DelGroupMember")
	public ModelAndView DelGroupMember(GroupMembers obj)
	{ ModelAndView mv=new ModelAndView();
		 try
		 {   
			 if(obj.delete())
			 { 
				 mv.setViewName("Success.jsp?type=GroupMemDel");
				 mv.addObject("groupName",obj.getGroupName());
			 }
			 else
			 {  
				 mv.setViewName("Failure.jsp?type=GroupMemDel");		 
				 
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 mv.setViewName("Failure.jsp?type=GroupMemDel");
		}
		 return mv;
	}
	 
	@RequestMapping("/GetUsers")
	public ModelAndView GetUsers(HttpServletRequest request)
	{
		 
		ModelAndView mv = new ModelAndView();
		UserReg obj=new UserReg();
		 javax.servlet.http.HttpSession ses=request.getSession(true);
		 obj.getUsers( request.getParameter("searchText").toString().trim(), ses.getAttribute("userid").toString().trim(), request.getParameter("groupName").toString().trim());
		List<UserReg> lst=obj.getLstuser();
		mv.setViewName("GetUser.jsp");
		mv.addObject("lst",lst);
		mv.addObject("groupName", request.getParameter("groupName").toString().trim());
		return mv;
	 
	}
	@RequestMapping("/sendMessage")
	public ModelAndView sendMessage(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("SendMsg.jsp");
		mv.addObject("userid",request.getParameter("userid").trim());
		mv.addObject("username",request.getParameter("username").trim());
		return mv;
	}
	@RequestMapping("/RegMsg")
	public ModelAndView RegMsg(UserMsg msg,HttpSession ses, HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		msg.setEncsts(ses.getAttribute("dataEncryption").toString().trim()); 
		String skey="";
		if(msg.getGroupMsg().length()>4)
			skey=services.RandomString.getAlphaNumericString1(4);
		else
			skey=services.RandomString.getAlphaNumericString1(msg.getGroupMsg().length());
		msg.setSeckey(skey.toLowerCase());
		System.out.println("resuserid="+msg.getResuserid()+" "+msg.getResuserName());
		if(msg.registration())
		{
			if(ses.getAttribute("dataEncryption").toString().trim().equals("on"))
			 {
				CloudFuns cf=new CloudFuns();
				 cf.recordusage(ses.getAttribute("userid").toString().trim(),"encryption");
				Base64Encoder encoder=new Base64Encoder();
				 mv.setViewName("gotoPython.jsp");
				 String param=String.valueOf(msg.getMsgId()+"|"+skey.toLowerCase());
				 System.out.println("param="+param);
				 param=encoder.encode(param.getBytes());
				 mv.addObject("path",GetURL.getPythonServerURL()+"EncryptUserMsg.py?param="+param);
			 }
			else
			{
			mv.setViewName("Success.jsp?type=MsgReg");
			}
		}
		else
			mv.setViewName("Failure.jsp?type=MsgReg");
		
		return mv;
	}
	@RequestMapping("/viewMessages")
	public ModelAndView viewMessages(HttpSession ses,HttpServletRequest request)
	{
		List<UserMsg> lst = new ArrayList<UserMsg>();
		UserMsg obj=new UserMsg();
		obj.setUserid(ses.getAttribute("userid").toString().trim());
		 obj.getGroupMessages();
		 lst=obj.getLstgroups();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewMsg.jsp");
		mv.addObject("lst", lst);
		System.out.println("size="+lst.size());
		mv.addObject("username",ses.getAttribute("username").toString().trim());
		return mv;
		 
	}
	@RequestMapping("/searchUsers")
	public ModelAndView searchUsers(HttpServletRequest request)
	{
		  
		ModelAndView mv = new ModelAndView();
		try {
		UserReg obj=new UserReg();
		mv.setViewName("searchUsers.jsp");
		 javax.servlet.http.HttpSession ses=request.getSession(true);
		 System.out.println("userid="+ses.getAttribute("userid").toString().trim());
		// System.out.println("group="+request.getParameter("groupName").toString().trim());
		 obj.getUsers1("NA", ses.getAttribute("userid").toString().trim(),"NA");
		List<UserReg> lst=new ArrayList<UserReg>();
		lst=obj.getLstuser();
		System.out.println("lst size="+lst.size());
		//System.out.println("group="+request.getParameter("groupName").toString().trim());
		//mv.setViewName("AddGroupMembers1.jsp");
		mv.addObject("lst",lst);
		mv.addObject("groupName", "NA");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err in controller="+e.getMessage());
		}
		return mv;
		
	 
	}
	@RequestMapping("/AddMember")
	public ModelAndView AddMember(HttpServletRequest request)
	{
		  
		ModelAndView mv = new ModelAndView();
		try {
		UserReg obj=new UserReg();
		mv.setViewName("AddGroupMembers.jsp");
		 javax.servlet.http.HttpSession ses=request.getSession(true);
		 System.out.println("userid="+ses.getAttribute("userid").toString().trim());
		 System.out.println("group="+request.getParameter("groupName").toString().trim());
		 obj.getUsers("NA", ses.getAttribute("userid").toString().trim(), request.getParameter("groupName").toString().trim());
		List<UserReg> lst=new ArrayList<UserReg>();
		lst=obj.getLstuser();
		System.out.println("lst size="+lst.size());
		System.out.println("group="+request.getParameter("groupName").toString().trim());
		//mv.setViewName("AddGroupMembers1.jsp");
		mv.addObject("lst",lst);
		mv.addObject("groupName", request.getParameter("groupName").toString().trim());
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err in controller="+e.getMessage());
		}
		return mv;
		
	 
	}
	@RequestMapping("/ExistingMembers")
	public ModelAndView ExistingMembers(HttpServletRequest request)
	{
		  
		ModelAndView mv = new ModelAndView();
		try {
		GroupMembers obj=new GroupMembers();
		mv.setViewName("ExistingGroupMembers.jsp");
		 javax.servlet.http.HttpSession ses=request.getSession(true);
		 obj.getMembers(ses.getAttribute("userid").toString().trim(), request.getParameter("groupName").toString().trim());
		List<GroupMembers> lst=new ArrayList<GroupMembers>();
		lst=obj.getLstmem();
		mv.addObject("lst",lst);
		mv.addObject("groupName", request.getParameter("groupName").toString().trim());
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err in controller="+e.getMessage());
		}
		return mv;
		
	 
	}
	@RequestMapping("/mygroups")
	public ModelAndView mygroups(HttpSession ses)
	{
		List<Groups> lst = new ArrayList<Groups>();
		Groups obj=new Groups();
		 obj.getGroups1(ses.getAttribute("userid").toString().trim());
		 lst=obj.getLstgroups();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("OtherGroups.jsp");
		mv.addObject("lst", lst);

		return mv;
		 
	}
	@RequestMapping("/viewGroupDocs")
	public ModelAndView viewGroupDocs(GroupDoc obj,HttpServletRequest request)
	{
		List<Documents> lst = new ArrayList<Documents>();
		obj.setGroupName(request.getParameter("groupName").trim());
		lst= obj.getsharedDocGroup();
		 System.out.println("size="+lst.size());

		ModelAndView mv = new ModelAndView();

		mv.setViewName("ViewGroupDocs.jsp");
		mv.addObject("std", lst);
		 System.out.println("size="+lst.size());
		return mv;
		 
	}
	 
	@RequestMapping("/admin")
	public String admin()
	{
		return "admin.jsp";
	}
	@RequestMapping("/user")
	public ModelAndView user(HttpSession ses)
	{
		List<Groups> lst = new ArrayList<Groups>();
		Groups obj=new Groups();
		 obj.getGroups1(ses.getAttribute("userid").toString().trim());
		 lst=obj.getLstgroups();
		 System.out.println("list="+lst.size());
		ModelAndView mv = new ModelAndView();

		mv.setViewName("user.jsp");
		mv.addObject("lst", lst);

		return mv;
		 
	}
	@RequestMapping("/")
	public String home1()
	{
		return "index.jsp";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
        session.invalidate();
		return "index.jsp";
	}
	@RequestMapping("/registration")
	public String registration()
	{
		return "Registration.jsp";
	}
	@RequestMapping("/adminHome")
	public String adminHome()
	{
		return "admin.jsp";
	}
	@RequestMapping("userHome")
	public ModelAndView userHome(HttpSession ses)
	{
		List<Groups> lst = new ArrayList<Groups>();
		Groups obj=new Groups();
		 obj.getGroups1(ses.getAttribute("userid").toString().trim());
		 lst=obj.getLstgroups();
		 System.out.println("list="+lst.size());
		ModelAndView mv = new ModelAndView();

		mv.setViewName("user.jsp");
		mv.addObject("lst", lst);

		return mv;
	}
	@RequestMapping("/Cities")
	public String cities()
	{
		return "Cities.jsp";
	}
	@RequestMapping("/viewUsers")
	public ModelAndView viewUsers(HttpSession ses)
	{
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		 obj.getAllUsersAdmin();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewUsers.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("RegUser")
	public String RegUser(UserReg obj)
	{
		 try
		 {
			 if(obj.registration() )
			 {
				 
				 return "Success.jsp?type=RegUser";
			 }
			 else
			 { 
				 return "Failure.jsp?type=RegUser";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=RegUser");
		}
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request)
	{
		 Login obj=new Login();
		 try
		 {
			 javax.servlet.http.HttpSession ses=request.getSession(true);
			 
			 if(obj.chkAuthentication(request.getParameter("txtuserid").trim(), request.getParameter("txtpass").trim()))
			 {
				 ses.setAttribute("userid", obj.getUserid());
				 System.out.println("userid="+obj.getUserid());
				 System.out.println("userid="+obj.getuType());
				 System.out.println("userid="+obj.getUserName());
				 ses.setAttribute("utype", obj.getuType());
				 ses.setAttribute("email", obj.getEmail());
				 ses.setAttribute("username", obj.getUserName());
				 return obj.getuType();
			 }
			 else
			 { 
				 return "Failure.jsp?type=Auth";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=Auth");
		}
		 
	}
}
