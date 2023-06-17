  
package services ;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.*;
import javax.crypto.spec.*;
 

import java.util.*;


public class Decryption {

    private String skey;
    private double size, encsize;
   

    public double getSize() {
        return size;
    }

    public Decryption() {
    
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getEncsize() {
        return encsize;
    }

    public void setEncsize(double encsize) {
        this.encsize = encsize;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    
 public void decrypt(String inFile,String outpath) {
        int K = 0;
        int index111=1;
        long div=2;
        FileOutputStream fos;
        FileInputStream fis;
        DataInputStream dis;
         DataOutputStream dos;
        try {
             
             File file=new File(outpath);
            fos=new FileOutputStream(file);
             dos = new DataOutputStream(fos);
            System.out.println("inputfile=" + inFile);
            fis = new FileInputStream(inFile);
            // create new data input stream
             dis = new DataInputStream(fis);
             byte[] b = Files.readAllBytes(Paths.get(inFile));
            size =  (float) (b.length / 1024.0);
           // generateKey();
            byte[] out = new byte[b.length];
            //long[] keys=generateKey();
            int cnt = 0,cnt1=1;
            long k2=0;
            long key=dis.readLong();
            System.out.println("dkey="+key);
             // available stream to be read
        while(dis.available()>0) {
        	//System.out.println("cnt1="+index111);
                
            // read four bytes from data input, return int
           
            long k1=key;
           //  long key =23232;
           //long key = (long) k1;
          //   System.out.println("key="+key);
              long d;
              
              try{
                  
                  
                   if(dis.available()<8)
                  {System.out.println("under 8="+dis.available());
                    /*    byte d1=dis.readByte();
                   System.out.println("under 8="+d1);
                int tem1 = (int) d1;
                int key1 = (int) k1;
                int res = tem1 ^ (key1);
              byte encbyte = (byte) (0xff & res);*/
             //  dos.writeByte((int)dis.readByte());
               dos.write(dis.read());
                  }
                  else
                  {
                //  System.out.println("udd="+dis.available());
              d=dis.readLong();
            //  System.out.println("inp="+d);
         //      long res = d ^ (k1);
            //   System.out.println("res="+res);
          //     dos.writeLong(res);
            //  System.out.println("cnt111111111111="+cnt1);
                	  if(index111%div==0)
                      {  //System.out.println("k2="+k2+" cnt1="+cnt1);
                    	  long res = d ^ (k2);
                    	  
                             dos.writeLong(res);
                      }
                      else
                      {
                    	  k2= d;
                    	 // System.out.println("d"+d);
                       long res = d ^ (k1);
                       
                    //   System.out.println("res="+res);
                       dos.writeLong(res);
                      }   
                	 
               
            //     System.out.println("cnt="+cnt);
                  }
              }
              catch(Exception ex1)
              {
                 System.out.println("err="+ex1.getMessage());
              }
              
            // print int
            cnt++;
            index111++;
         }
        
          // force data to the underlying file output stream
         dos.flush();
         //   encsize=out.length;
           // encsize = (float)(encsize / 1024.0);
        } catch (Exception ex) {
            System.out.println("err in dec=" + ex.getMessage());
        }
       
    }
  

   

   /*( public long[] generateKey() {
    	long[] lArr=null;
        try{
         
			byte[] bytes = skey.getBytes();
			LongBuffer tmpBuf = ByteBuffer.wrap(bytes).asLongBuffer();
			
			 lArr = new long[tmpBuf.remaining()];
			for (int i = 0; i < lArr.length; i++)
			    lArr[i] = tmpBuf.get();
			
			System.out.println(skey);
			System.out.println(Arrays.toString(lArr));
        }
        catch(Exception ex)
        {
            System.out.println("err in key="+ex.getMessage());
        }
        return lArr;
    } */
}
