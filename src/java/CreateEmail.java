/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Denny Desktop
 */
@ManagedBean
@SessionScoped
public class CreateEmail implements Serializable {
   
   private String From;
   private String To;
   private String Subject;
   private String Body;
   
   public String getFrom(String id) { 
       setFrom(id);
       return id;
    }
   
    public void setFrom(String From) {
        this.From = From;
    }
   public String getTo() {
        return To;
    }

    public void setTo(String To) {
        this.To = To;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String Body) {
        this.Body = Body;
    }
   
    public String sendemail()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");    
        }
        catch (Exception e)
        {
            return ("Internal Error! Please try again later.");
        }
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                return ("Internal Server Error for class forname");
            }
            Connection con = null;
            Statement st = null;
            
            try {
                final String url = "jdbc:mysql://mis-sql.uhcl.edu/shahp0589";
                con = DriverManager.getConnection(url, "shahp0589", "1307421");
                st = con.createStatement();

               String r1 =" INSERT INTO email ( Subject, Body, Fromid, Toid, reademail ) VALUES (  '"+Subject+"'   , '"+Body+"'  ,  '"+From+"' , '"+To+"' ,\"new\" )";
                st.execute(r1);
                
                return("***Your email has been sent succesfully to " + To +" at " + DateAndTime.DateTime()+"***");
                
                
            } catch (SQLException e) {
                e.printStackTrace();
                return ("Internal Error! Please try again later.SQL Error");
            } finally {
                try {
                    To="";
                    Body="";
                    Subject="";
                    con.close();
                    st.close();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    return ("Internal Error! Please try again later.Exception in second catcth block");
                }

            }

    }
    
         
    
     
     
}
