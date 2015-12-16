/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BeatHeaven_Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProfilePicDetails {
    private int id,user_id,status;
    private String user_type="",file_location="";
    Connection conn;
    Statement stmt;
    String sql="";
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getFile_location() {
        return file_location;
    }

    public void setFile_location(String file_location) {
        this.file_location = file_location;
    }

    public ProfilePicDetails() {
    }
    
    public int addProfilePicDetails(int UserId,String UserType,String FileLocation){
        int j=-1;
        try {           
             Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
             String sql="insert into profilepicdetails(UserId,UserType,FileLocation,Status) values(?,?,?,?)";
             PreparedStatement pstmt = conn.prepareStatement(sql);
             pstmt.setInt(1, UserId);
             pstmt.setString(2, UserType);
             pstmt.setString(3, FileLocation);
             pstmt.setInt(4, 2);
             j=pstmt.executeUpdate();
             return j;
        }catch(Exception ex){
            return j;
        }
    }
    
    public int getProfilePicDetails(int UserId,String UserType){
        int count=0;
        try{
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
                stmt = conn.createStatement();            
                sql="select * from profilepicdetails where UserId="+UserId+" and UserType='"+UserType+"'";
                ResultSet rs = stmt.executeQuery(sql); 
                while(rs.next()){
                    user_id=UserId;
                    user_type=UserType;
                    file_location=rs.getString("FileLocation");
                    status=rs.getInt("Status");
                    count++;
                }
                conn.close();
            return count;
        }catch(Exception ex){
            ex.printStackTrace();
            return count;
        }
    }
    
      public int updateProfilePicDetails(int UserId,String UserType,String FileLocation){
        int j=-1;        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            String sql="update profilepicdetails set FileLocation='"+FileLocation+"' where UserId="+UserId+" and UserType='"+UserType+"'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            j=pstmt.executeUpdate();
            conn.close();
            return j;
        }catch(Exception e){
            e.printStackTrace();
            return j;
        }
        
    }
}
