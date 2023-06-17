/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import java.util.Vector;
 
public class LoginSummary 
{
private int totcomplogs;
private int totbranchlogs;
private int totemplogs;
private int totcustlogs;

    public LoginSummary(String custid,int month,int year,String typ) 
    {
    String qr1="select count(*) from loginlog where  userid='"+custid.trim()+"'  and loginmonth="+month+" and loginyear="+year;    
      System.out.println("typ="+typ);
    CloudFuns sf=new CloudFuns();
                try
               {
                    Vector vct=new Vector();
                    
                    vct.clear();
                    vct=sf.getValue(qr1,1);
                    totcustlogs=Integer.parseInt(vct.elementAt(0).toString());                   
                }
                catch(Exception e)
                {                    
                }
    }
   
    public int getTotcustLogs()
    {
        return totcustlogs;
    }
}
