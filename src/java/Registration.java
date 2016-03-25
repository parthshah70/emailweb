/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Denny Desktop
 */
@ManagedBean
@RequestScoped
public class Registration {

   private String firstname;
    private String lastname;
    private String id;
    private String password;
    private String password1;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }
   

   
    public String register()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");    
        }
        catch (Exception e)
        {
            return ("Internal Error! Please try again later.");
        }
         
         
       
        boolean hasid = false;
        String s;
        s = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{3,10}$";
        if (id.matches(s)) 
        {
            hasid = true;
        

        if (getPassword().equals(getPassword1())) {

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                return ("Internal Server Error for class forname");
            }
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                final String url = "jdbc:mysql://mis-sql.uhcl.edu/shahp0589";
                con = DriverManager.getConnection(url, "shahp0589", "1307421");
                st = con.createStatement();

                rs = st.executeQuery("select * from samplemail where accountID ='" + id + "'  ");
                if (rs.next()) {
                    return "This email id is taken please use another id. Go back To Registration!!";
                    
                   
                } else {
                    String r = "insert into samplemail values ('" + firstname + "' ,'" + lastname + "','" + id + "', '" + password + "','" + id + "@uhcl.edu') ";
                    st.execute(r);
                    return "Congratulations you are registred with " + id + "@uhcl.edu created at " + DateAndTime.DateTime();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return ("Internal Error! Please try again later.SQL Error");
            } finally {
                try {
                    con.close();
                    st.close();
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return ("Internal Error! Please try again later.Exception in second catcth block");
                }

            }

        } else {
            return ("Please retype your password correctly. Go back To Registration!!");
        }
        }else
        {
         return ("Please Enter Id between 3 to 10 character including 0-9, A-Z, a-z. Go back To Registration!!");
        }
    
         
    }
     
     
}
