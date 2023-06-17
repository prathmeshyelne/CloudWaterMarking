/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import java.util.Vector;


 
public class ServiceSummary
{
 
    java.util.Vector usage=new java.util.Vector();
         java.util.Vector rate=new java.util.Vector();
         java.util.Vector rent=new java.util.Vector();
         java.util.Vector service=new java.util.Vector();
   CloudFuns sf=new CloudFuns();
   public ServiceSummary() 
    {
   
    }
   
     public void getcloudrent(String tenantid,int month,int year) 
     {
        usage.clear();
        service.clear();
        rent.clear();
        rate.clear();
         service=sf.getValue("select service from services",1);
         rate=sf.getValue("select rate from services",1);
         
             for(int j=0;j<service.size();j++)
             {
                 int ind=0,ind1=0;
                  String typ="",str="NA";
                 try{
                 ind=service.elementAt(j).toString().trim().length()-5;
                   ind1=service.elementAt(j).toString().trim().length();
                 typ=service.elementAt(j).toString().trim().substring(0,ind);
                 System.out.println("lg="+service.elementAt(j).toString().trim().substring(ind,ind1)+" typ="+typ);
                 str=service.elementAt(j).toString().trim().substring(ind,ind1);
                 }catch(Exception ex){System.out.println("errservice="+ex.getMessage());}
                   if(str.equals("login"))
                 {
                     try{
                      LoginSummary obj=new LoginSummary(tenantid.toString(), month, year,typ);
             usage.addElement(obj.getTotcustLogs());
             rent.addElement(Double.parseDouble(String.valueOf(obj.getTotcustLogs()))*Double.parseDouble(String.valueOf(rate.elementAt(j))));
                     }
                     catch(Exception ex){}
                 }
                 else
                 {
             String qr1="SELECT count(*) from usagelog where actiondt Like '%/"+month+"/%' and actiondt Like '%/"+year+"%' and action='"+service.elementAt(j).toString().trim()+"' and userid='"+tenantid.toString()+"'";  
             java.util.Vector vu=sf.getValue(qr1, 1);
              usage.addElement(vu.elementAt(0).toString().trim());
                rent.addElement(Double.parseDouble(String.valueOf(vu.elementAt(0).toString().trim()))*Double.parseDouble(String.valueOf(rate.elementAt(j))));
             } 
              
         }
     }

    
    public Vector getUsage() {
        return usage;
    }

    public Vector getRate() {
        return rate;
    }

    public Vector getRent() {
        return rent;
    }

    public Vector getService() {
        return service;
    }
      
   
  
}
