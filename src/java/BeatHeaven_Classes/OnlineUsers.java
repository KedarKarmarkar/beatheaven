
package BeatHeaven_Classes;
import java.util.Date;
import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OnlineUsers {
    ArrayList<Integer> olfriend_list = new ArrayList<Integer>();
    
    Connection conn;
    Statement stmt;
    String sql="";
    ResultSet rs;
    public OnlineUsers(){
        
    }
    public void updateKalakarOnlineStatus(int kalakarId,int status){
    
        try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                String sql = "update kalakar set OnlineStatus=? where Id=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, kalakarId);
                pst.setInt(2, status);
                pst.executeUpdate();
                conn.close();            
                
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Problem while updating data into kalakar");
           
        }
    }
    public ArrayList<Integer> getOnlineFriendDetails( int userid){
        try{        
        Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
                stmt = conn.createStatement();               
                sql = "select fl.Friend_Id from friendlist fl,Kalakar k where fl.Kalakar_Id="+userid+" and k.Id=fl.Friend_Id and k.OnlineStatus=1";
                rs = stmt.executeQuery(sql);                
                while (rs.next()) {
                  olfriend_list.add(rs.getInt("Friend_Id")) ;                   
                }
                rs.close();
                conn.close();
                 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
                stmt = conn.createStatement();               
                sql = "select fl.Kalakar_Id from friendlist fl,Kalakar k where fl.Friend_Id="+userid+" and k.Id=fl.Kalakar_Id and k.OnlineStatus=1";
                rs = stmt.executeQuery(sql);                
                while (rs.next()) {
                  olfriend_list.add(rs.getInt("Friend_Id")) ;                   
                }
                rs.close();
                conn.close();
        }catch(Exception e){
            System.out.println("Exception found in getOnlineFriendDetails of class-OnlineUsers");
        }
        return olfriend_list;
    }
    
    public ArrayList<Integer> getOnlineInstituteUsers(int instituteid){
        try{        
        Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
                stmt = conn.createStatement();               
                sql = "select kir.KalakarId from Kalakar_institute_relation kir,Kalakar k where kir.InstituteId="+instituteid+" and k.Id=kir.KalakarId and k.OnlineStatus=1";
                rs = stmt.executeQuery(sql);                
                while (rs.next()) {
                  olfriend_list.add(rs.getInt("Kalakar_Id")) ; 
                }
                rs.close();
                conn.close();
        }catch(Exception e){
            System.out.println("Exception found in getOnlineInstituteUsers of class-OnlineUsers");
        }
        return olfriend_list;
    }
}
