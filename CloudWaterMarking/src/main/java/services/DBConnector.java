/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


 
 
import java.sql.*;
 
 
 
public class DBConnector {
    
 public Connection con;
    public DBConnector() 
    {
    }
    
    public Connection connect()
    {
    
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
         
           con=DriverManager.getConnection("jdbc:mysql://localhost/cloudwatermarkdb?user=root&&password=1234");         
            System.out.println("Connected..");
        }
        catch(Exception e)
        {
            System.out.println("Error in connection : "+e.getMessage());
        }
        
    return con;
    }
    
    
    
}

