/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Denny Desktop
 */
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.*;
import java.util.List;
import javax.ejb.SessionBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
/**
 *
 * @author LinJian
 */

@ManagedBean(name = "testBean")
@SessionScoped

public class OnlineAccount {
    
    private String id;   //account ID
    private String password;   //saved password
    private ArrayList<EmailAccount> managedAccounts; //inbox
    private ArrayList<EmailAccount> sentItems; //outbox
    private ArrayList<EmailAccount> InboxItems;

   
  
    public ArrayList<EmailAccount> getSentItems() {
        return sentItems;
    }

    public void setSentItems(ArrayList<EmailAccount> sentItems) {
        this.sentItems = sentItems;
    }

//the accounts under this ID;

    public ArrayList<EmailAccount> getManagedAccounts() {
        OnlineAccount oa = new OnlineAccount();
        return managedAccounts;
    }

    public void setManagedAccounts(ArrayList<EmailAccount> managedAccounts) {
        this.managedAccounts = managedAccounts;
    }
    
    public OnlineAccount(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String uId = session.getAttribute("userId").toString();
        System.out.println("USER ID = "+uId);
        String password1 = session.getAttribute("password").toString();
        System.out.println("USER ID = "+password1);
        testConstruct(uId, password1);
    }
    //constructor
    public OnlineAccount(String selectedID, String selectedPassword)
    {
        
        testConstruct(selectedID, selectedPassword);
    }
    public void testConstruct(String selectedID, String selectedPassword)
    { 
        System.out.println("Method has been called");
        id = selectedID;
        password = selectedPassword;
        sentemails();
        
        managedAccounts = new ArrayList<EmailAccount> ();
     
        
       
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/shahp0589";
            
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL, "shahp0589", "1307421");   
            statement = connection.createStatement();
            
         
            resultSet = statement.executeQuery(" SELECT * FROM email WHERE Toid =  '"+id+"'  ORDER BY ID DESC    ");
            while(resultSet.next())
            {
                EmailAccount en = new EmailAccount();
                System.out.println("fegrgrg" + resultSet.getString(1) );
                en.setId(resultSet.getString(1));
                en.setSubject(resultSet.getString(2));
                en.setBody(resultSet.getString(3));
                en.setFromid(resultSet.getString(4));
                en.setToid(resultSet.getString(5));
                en.setTimestamp(resultSet.getString(6));
                en.setReademail(resultSet.getString(7));
                managedAccounts.add(en);
           }
            System.out.println("method is running" +  managedAccounts.size() + id );
            System.out.println( id );
        }
        catch (SQLException e)
        {           
            e.printStackTrace();
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
    public String signOut()
    {
        FacesContext.getCurrentInstance()
                .getExternalContext().invalidateSession();
        System.out.println("fff");
        return "logout.xhtml"; 
    }
    
     public List<EmailAccount>  inboxemails()
    {
     InboxItems = new ArrayList<EmailAccount> ();
     
        
       
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/shahp0589";
            
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL, "shahp0589", "1307421");   
            statement = connection.createStatement();
            
         
            resultSet = statement.executeQuery(" SELECT * FROM email WHERE Toid =  '"+id+"'  ORDER BY ID DESC    ");
            while(resultSet.next())
            {
                EmailAccount en = new EmailAccount();
                System.out.println("fegrgrg" + resultSet.getString(1) );
                en.setId(resultSet.getString(1));
                en.setSubject(resultSet.getString(2));
                en.setBody(resultSet.getString(3));
                en.setFromid(resultSet.getString(4));
                en.setToid(resultSet.getString(5));
                en.setTimestamp(resultSet.getString(6));
                en.setReademail(resultSet.getString(7));
                InboxItems.add(en);
           }
            System.out.println("method is running" +  sentItems.size() + id );
            System.out.println( id );
        }
        catch (SQLException e)
        {           
            e.printStackTrace();
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
        return InboxItems;
    }
    public List<EmailAccount>  sentemails()
    {
     sentItems = new ArrayList<EmailAccount> ();
     
        
       
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/shahp0589";
            
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL, "shahp0589", "1307421");   
            statement = connection.createStatement();
            
         
            resultSet = statement.executeQuery(" SELECT * FROM email WHERE Fromid =  '"+id+"'  ORDER BY ID DESC    ");
            while(resultSet.next())
            {
                EmailAccount en = new EmailAccount();
                System.out.println("fegrgrg" + resultSet.getString(1) );
                en.setId(resultSet.getString(1));
                en.setSubject(resultSet.getString(2));
                en.setBody(resultSet.getString(3));
                en.setFromid(resultSet.getString(4));
                en.setToid(resultSet.getString(5));
                en.setTimestamp(resultSet.getString(6));
                en.setReademail(resultSet.getString(7));
                sentItems.add(en);
           }
            System.out.println("method is running" +  sentItems.size() + id );
            System.out.println( id );
        }
        catch (SQLException e)
        {           
            e.printStackTrace();
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
        return sentItems;
    }
    
    public String viewEmail(EmailAccount ab){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("emailID",ab);
        Connection connection =null;
        Statement statement = null;
        final String DATABASE_URL ="jdbc:mysql://mis-sql.uhcl.edu/shahp0589";
        try {
            //create connection    
            connection = DriverManager.getConnection(DATABASE_URL, "shahp0589", "1307421");
            //create statement
            statement = connection.createStatement();
            String s = "UPDATE email SET reademail =\"\" WHERE ID = '" + ab.getId() + "' ";
            statement.execute(s);
            System.out.println("update is working");
        //search for email id we use in view

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       // ab.setReademail("");
        return "viewinbox";
    }
    public String viewSentEmail(EmailAccount ab){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("emailID",ab);
        return "viewsentbox";
    }
    
    public List<EmailAccount> readThisEmail(){
        List<EmailAccount> eList = new ArrayList<EmailAccount>();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        EmailAccount e = (EmailAccount) session.getAttribute("emailID");
        //System.out.println("Value of ID = "+e);
        eList.add(e);
        return eList;
    }
    
    public List<EmailAccount> replyEmail()
    {
        List<EmailAccount> eList = new ArrayList<EmailAccount>();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        EmailAccount e = (EmailAccount) session.getAttribute("emailID");
        //System.out.println("Value of ID = "+e);
        eList.add(e);
        String subject= "RE: "+eList.get(0).getSubject();
        String Body=("\n"+ "\n ********************************************************"  +"\n From: "+ eList.get(0).getFromid()+ "\n To: "+ eList.get(0).getToid()+"\n Time: " +eList.get(0).getTimestamp()+ "\n Subject: "+ eList.get(0).getSubject()+"\n Body: " +eList.get(0).getBody());
        String From=eList.get(0).getFromid(); //587
        String To = eList.get(0).getToid(); //589
        eList.get(0).setSubject(subject);
        eList.get(0).setBody(Body);
        eList.get(0).setFromid(To); //589
        eList.get(0).setToid(From); //587
        return eList;
    }
    public void sendreplyemail(EmailAccount ab)
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("emailID1",ab);
    }
    public String sendingemail()
    {
         List<EmailAccount> eList = new ArrayList<EmailAccount>();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        EmailAccount e = (EmailAccount) session.getAttribute("emailID1");
         eList.add(e);
         System.out.println("From:"   + eList.get(0).getFromid()+ "to" + eList.get(0).getToid());
    Connection con = null;
            Statement st = null;
            
            try {
                final String url = "jdbc:mysql://mis-sql.uhcl.edu/shahp0589";
                con = DriverManager.getConnection(url, "shahp0589", "1307421");
                st = con.createStatement();

               String r1 =" INSERT INTO email ( Subject, Body, Fromid, Toid, reademail ) VALUES (  '"+eList.get(0).getSubject()+"'   , '"+eList.get(0).getBody()+"'  ,  '"+eList.get(0).getFromid()+"' , '"+eList.get(0).getToid()+"' ,\"new\" )";
                st.execute(r1);
                
                return("***Your reply has been sent succesfully to " + eList.get(0).getToid() +" at " + DateAndTime.DateTime()+"***");
                
                
            } catch (SQLException e1) {
                e1.printStackTrace();
                return ("Internal Error! Please try again later.SQL Error");
            } finally {
                try {
                    
                    con.close();
                    st.close();
                    
                } catch (Exception e1) {
                    e1.printStackTrace();
                    return ("Internal Error! Please try again later.Exception in second catcth block");
                }

            }
    }
    
    public void deleteemail(EmailAccount appObj) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/shahp0589";
            connection = DriverManager.getConnection(DATABASE_URL, "shahp0589", "1307421");
            statement = connection.createStatement();
             String r1 =" DELETE FROM `email` WHERE ID= '"+appObj.getId()+"'";
                statement.execute(r1);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
              

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     
    }
      public String inboxemailssize()
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;  
         int i=0;
         String s="";
        try
        {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/shahp0589";
            
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL, "shahp0589", "1307421");   
            statement = connection.createStatement();
           
            resultSet = statement.executeQuery(" SELECT count(*) FROM email WHERE Toid =  '"+id+"'  AND reademail= \"new\"   ");
            while(resultSet.next())
            {
               i=resultSet.getInt(1);
            }
            if(i>0)
            {
            s=( "("+  i +") " );
            }
           
            else
            {
            s="";
            }
             System.out.println( s + "   " + i);
        }
        catch (SQLException e)
        {           
            e.printStackTrace();
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
       return s;
    }
    
}


