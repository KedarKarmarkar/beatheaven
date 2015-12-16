
package BeatHeaven_Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SiteAdmin {
    private int id;
    private String user_name="",password="",email="",contact="";
    Connection conn;
    Statement stmt;
    String sql="";
    PreparedStatement pstmt;
    ResultSet rs;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public void getSiteAdminDetails(int id){
        try{
            Class.forName("com.mysql.jdbc.Driver");     
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
            stmt = conn.createStatement();            
            sql="select * from siteadmin where Id="+id+"";
            rs = stmt.executeQuery(sql); 
            while(rs.next()){
                id=rs.getInt("Id");
                email=rs.getString("Email");
                contact=rs.getString("Contact");
                user_name=rs.getString("UserName");
                password=rs.getString("Password");
            }
            conn.close();
           
        }catch(Exception ex){
          ex.printStackTrace();
        }
    }
    
    public int updateSiteAdminDetails(int id,String email,String pwd,String contact){
        int j=-1;
        try{
            GetCurrentDateTime gcdt=new GetCurrentDateTime();
            String cur_date=gcdt.getCurDate();            
            Class.forName("com.mysql.jdbc.Driver");        
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            sql="update siteadmin set Email=?,Contact=?,Password=? where Id="+id+"";
            PreparedStatement pstmt = conn.prepareStatement(sql);            
            pstmt.setString(1, email);    
            pstmt.setString(2, contact);   
            pstmt.setString(3, pwd);     
            j = pstmt.executeUpdate();
            conn.close();            
            return j;
        }catch(Exception e){
            e.printStackTrace();
            return j;
        }
    }
}
