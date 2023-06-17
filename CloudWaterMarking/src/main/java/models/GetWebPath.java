package models;

import java.util.Arrays;
import java.util.List;

public class GetWebPath {
public  static String getPath(String path)
{
	String finalpath="";
try
{
	// String path = getServletContext().getRealPath("/");
	 System.out.println("path="+path);
	 finalpath=path;
	/* String[] str=path.split("\\\\");
	List<String> lst=Arrays.asList(path.split("\\\\"));
	int ind=lst.indexOf("JavaEEProjects");
	
	 for(int i=0;i<=ind;i++)
	 {
		 if(i==0)
		finalpath+=lst.get(i);	 
		 else
			finalpath+="/"+lst.get(i);	 
			 
	 }
	finalpath+="/secureKYC\\secureKYC\\WebContent\\";*/
	  System.out.println("p="+finalpath);
}
catch (Exception e) {
	// TODO: handle exception
	 System.out.println("err="+e.getMessage());
}
	return finalpath ;
}
}
