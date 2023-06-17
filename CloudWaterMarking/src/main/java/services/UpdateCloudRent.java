/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services; 
import java.util.*;
 
public class UpdateCloudRent {
    
  public void updcloudrent(int month,int year)
  {
    try{
        CloudFuns ff=new CloudFuns();
       
     Vector vt=ff.getValue("select userid from users where utype='user'",1);
     for(int m=0;m<vt.size();m++)
     {
          String ratestr="",rentstr="",usagestr="",servicestr="";
        double total=0.0;
     ServiceSummary obj=new ServiceSummary();
        String tenantid=vt.elementAt(m).toString();
     obj.getcloudrent(vt.elementAt(m).toString(), month, year);
     Vector usage=obj.getUsage();
     Vector service=obj.getService();
     Vector rates=obj.getRate();
     Vector rent=obj.getRent();
     for(int i=0;i<usage.size();i++)
     {
          if(usagestr.equals(""))
         usagestr+=usage.elementAt(i).toString().trim();
          else
              usagestr+=","+usage.elementAt(i).toString().trim();
     }
     for(int i=0;i<rates.size();i++)
     {
           if(ratestr.equals(""))
         ratestr=rates.elementAt(i).toString().trim();
           else
               ratestr+=","+rates.elementAt(i).toString().trim();
     }
      for(int i=0;i<service.size();i++)
      {
          if(servicestr.equals(""))
           servicestr=service.elementAt(i).toString().trim();   
          else
         servicestr+=","+service.elementAt(i).toString().trim();
      }
     for(int i=0;i<rent.size();i++)
     {
         if(rentstr.equals(""))
             rentstr+=rent.elementAt(i).toString().trim();
         else
             rentstr+=","+rent.elementAt(i).toString().trim();
         total+=Double.parseDouble(rent.elementAt(i).toString().trim());
     }
     Vector vpay=ff.getValue("select tenantid from cloudpayments where month="+month+" and year="+year+" and tenantid='"+tenantid+"'", 1);
     if(vpay.size()>0)
     {
         String qr="update cloudpayments set rates='"+ratestr+"',susage='"+usagestr+"',rents='"+rentstr+"',total="+total+" where month="+month+" and year="+year+" and tenantid='"+tenantid+"'";
         System.out.println("qrr="+qr);
         if(ff.execute(qr))
         {
             
         }
     }
     else
     {
         int max=ff.FetchMax("id", "cloudpayments");
         String qr="insert into cloudpayments values("+max+",'"+tenantid+"','NA',"+month+","+year+",'"+servicestr+"','"+ratestr+"','"+usagestr+"','"+rentstr+"',"+total+",'pending')";
       
         if(ff.execute(qr))
         {
             
         }
     }
     }
    }
    catch(Exception ex)
    {
        System.out.println("errrr="+ex.getMessage());
    }
}
}
 
