/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Denny Desktop
 */
import java.sql.*;
import java.util.ArrayList;
import java.text.*;
import java.util.StringTokenizer;
import java.util.List;

/**
 *
 * @author LinJian
 */
public class EmailAccount {
    
    private String id;
    private String subject;
    private String body;
    private String fromid;
    private String toid;
    private String timestamp;
    private String reademail;
    
   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getReademail() {
        return reademail;
    }

    public void setReademail(String reademail) {
        this.reademail = reademail;
    }
    
    
   


    
    

   
    
    
    
    
            
    
}