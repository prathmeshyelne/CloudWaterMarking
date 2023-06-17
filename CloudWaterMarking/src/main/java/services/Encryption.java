 
package services ;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random; 

public class Encryption {

	 private String skey;
    private float size, encsize;
     
    public Encryption() {
        
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getEncsize() {
        return encsize;
    }

    public void setEncsize(float encsize) {
        this.encsize = encsize;
    }

     
    public void encrypt(String inFile,String outpath) {
        int K = 0;
        long div=2;
        int index111=1;
        FileOutputStream fos;
        FileInputStream fis;
        DataInputStream dis;
         DataOutputStream dos;
        try {

            byte[] b = Files.readAllBytes(Paths.get(inFile));
            size =  (float) (b.length / 1024.0);
            int len1=0,len2=0;
            Random rnd=new Random();
            len1=rnd.nextInt(b.length/2);
            len2=len1+8;
            System.out.println("len1="+len1);
            System.out.println("len2="+len2);
            byte[] randomKey=Arrays.copyOfRange(b,len1,len2);
            System.out.println("key="+new String(randomKey));

            long key=generateKey(randomKey);
            System.out.println("key len="+key);
             File file=new File(outpath);
            fos=new FileOutputStream(file);
             dos = new DataOutputStream(fos);
            System.out.println("inputfile=" + inFile);
            fis = new FileInputStream(inFile);
            // create new data input stream
             dis = new DataInputStream(fis);
             
            size =  (float) (b.length / 1024.0);
           
            byte[] out = new byte[b.length];
  System.out.println("len="+b.length);
     
            int cnt = 0,cnt1=0;
            long k2=0;
            System.out.println("cnt1="+index111);
             // available stream to be read
            dos.writeLong(key);
         while(dis.available()>0) {
        	// System.out.println("cnt1="+index111);
            // System.out.println("cnt1="+dis.available());
          //    System.out.println("cnt11="+dis.available());
            // cnt1++;
            if(index111<10)
            	System.out.println("cnt1="+index111);
                
            // read four bytes from data input, return int
           
            long k1=key;
           //  long key =23232;
            // System.out.println("key="+key);
              long d ;
              
              try{
                  if(dis.available()<8)
                  {    System.out.println("under 8="+dis.available());
                       /*  byte d1=dis.readByte();
                   System.out.println("under 8="+d1);
                int tem1 = (int) d1;
                int key1 = (int) k1;
                int res = tem1 ^ (key1);
              byte encbyte = (byte) (0xff & res); */
               dos.write(dis.read());
                  }
                  else
                  {
                   //   System.out.println("dis="+dis.available());
              d=dis.readLong();
             // System.out.println("inp="+d);
              
              if(index111%div==0)
              {
            	   
            	  long res = d ^ (k2);
                  
                     dos.writeLong(res);
              }
              else
              {
            	  
               long res = d ^ (k1);
               k2=res;
              
             //  System.out.println("k2="+k2+" cnt1="+cnt1);
               
            //   System.out.println("res="+res);
               dos.writeLong(res);
              }
             
              
              
               //long res = d ^ (k1);
            //   System.out.println("res="+res);
              // dos.writeLong(res);
           //      System.out.println("cnt="+cnt);
                  }
              }
              catch(Exception ex1)
              { System.out.println("cnt1="+dis.available());
              
              System.out.println("err="+ex1.getMessage());
            //     System.out.println("cnt="+cnt);*/
              }
              
            // print int
            cnt++;
            index111++;
         }
      
           
        //    fos.write(out);
          // force data to the underlying file output stream
         dos.flush();
      //   DecryptionLong dobj=new DecryptionLong();
       ///   System.out.println("encrypted key="+skey); 
        
       //  dobj.setSkey(skey);
      //   dobj.decrypt(outpath,"E:\\orfile.mp4");
         //   encsize=out.length;
           // encsize = (float)(encsize / 1024.0);
        } catch (Exception ex) {
            System.out.println("err in ecyption=" + ex.getMessage());
        }
       
    }

    
    public long generateKey(byte[] bytes) {
    	long[] lArr=null;
    	long key=0;
        try{
         
			 
			LongBuffer tmpBuf = ByteBuffer.wrap(bytes).asLongBuffer();
			
			 lArr = new long[tmpBuf.remaining()];
			for (int i = 0; i < lArr.length; i++)
			    lArr[i] = tmpBuf.get();
			
			System.out.println(skey);
			System.out.println(Arrays.toString(lArr));
			key=lArr[0];
        }
        catch(Exception ex)
        {
            System.out.println("err in key="+ex.getMessage());
        }
        return key;
    } 
}