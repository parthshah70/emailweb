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
import java.util.Scanner;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Denny Desktop
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable{

    private String id;
    private String password;
    private OnlineAccount theLoginAccount;
   

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public OnlineAccount getTheLoginAccount() {
        return theLoginAccount;
    }
    

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    public  String login()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");    
        }
        catch (Exception e)
        {
            return ("Internal Error! Please try again later.");
        }
        //database URL and three important variable
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/shahp0589";
        Connection connection = null;  //a connection to the database
        Statement statement = null;    //execution of a statement
        ResultSet resultSet = null;   //set of results
        try
        {      
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL, 
                    "shahp0589", "1307421");   
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select * from samplemail "
                    + "where accountID= '" + 
                    id + "'" );
            if(resultSet.next())
            {
                //the id is found, check password
                if(password.equals(resultSet.getString(4)))
                {
                    //create the login account
                    theLoginAccount = new OnlineAccount(id+"@uhcl.edu",  password);
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                    session.setAttribute("userId",id+"@uhcl.edu");
                    
                    session.setAttribute("password", password);
                    
                    return "welcome_pagination";
                }
                else
                {
                    id="";
                    password="";                    //password is not good
                    return "loginNotOK";
                }
            }
            else
            {
                // the id is not fine, and it will got mto the errror page
                id="";
                password="";  
                return "loginNotOK_1";
            }
             
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return ("internalError");
        }
        finally
        {
            try
            {
                resultSet.close();
                statement.close();
                connection.close();
                 
            }
            catch (Exception e)
            {
                e.printStackTrace();    
            }
        }
    
    }
        
         
        
} 
    

