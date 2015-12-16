
package BeatHeaven_Classes;
import java.util.Date;
import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int course_id,status,fee;
    private String course_name="",duration="",eligibility="",specification="";

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getInstitute_id() {
        return institute_id;
    }

    public void setInstitute_id(int institute_id) {
        this.institute_id = institute_id;
    }
    int institute_id=0;
    ArrayList<String> coursename_list = new ArrayList<String>();
    Connection conn;
    Statement stmt;
    String sql="";
    ResultSet rs;     
    public Course(){
        
    }            
            
    public Course(String cname,String duration,int institute_id,int fee,String eligibility,String specification){
      course_name=cname;
      this.duration=duration;
      this.institute_id=institute_id;
      this.fee=fee;
      this.eligibility=eligibility;
      this.specification=specification;
    }
    public void getCourseDetails(int courseId){
        try{
         Class.forName("com.mysql.jdbc.Driver");     
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
         stmt = conn.createStatement();            
         sql="select * from course where Id="+courseId+"";
         rs = stmt.executeQuery(sql); 
         while(rs.next()){
             this.course_id = courseId;             
             this.course_name = rs.getString("CourseName");             
             this.institute_id=rs.getInt("InstituteId");
             this.fee=rs.getInt("Fee");             
             this.eligibility=rs.getString("Eligibility");
             this.specification=rs.getString("Specification");
         }
         conn.close();
     }catch(Exception e){
         e.printStackTrace();
     }
    }
    public int addCourseDetails(){
        int j=-1;
        try {           
            Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
             sql="insert into course(CourseName,Duration,InstituteId,Fee,Eligibility,Specification,Status) values(?,?,?,?,?,?,?)";
             PreparedStatement pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, course_name);
             pstmt.setString(2,duration);
             pstmt.setInt(3,institute_id);
             pstmt.setInt(4,fee);
             pstmt.setString(5, eligibility);
             pstmt.setString(6, specification);
             pstmt.setInt(7,1);
             j = pstmt.executeUpdate();
             conn.close();  
             return j;
        }catch(Exception e){
            e.printStackTrace();
            return j;
        }
        
    }
    public ArrayList<String> getCourseName(int instituteId){
        try{        
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
                stmt = conn.createStatement();               
               sql="select CourseName from Course where InstituteId="+instituteId+" and Status=1 ";
                rs = stmt.executeQuery(sql);                
                while (rs.next()) {
                  coursename_list.add(rs.getString("CourseName")) ; 
                }
                rs.close();
                conn.close();
        }catch(Exception e){
            System.out.println("Exception found in getOnlineFriendDetails of class-OnlineUsers");
        }
        return coursename_list; 
    }
    public int updateCourseDetails(String cname,String d,int fee,String el,String specif,int courseId){
        int j=-1;
        int count=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            String sql="update Course set CourseName='"+cname+"',Duration='"+d+"',Fee="+fee+",Eligibility='"+el+"',Specification='"+specif+"' where Id="+courseId+"";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            j=pstmt.executeUpdate();
            conn.close();
            return j;
        }catch(Exception e){
            e.printStackTrace();
            return j;
        }
    }
    
    public int getCourseId(String courseName){
        int cid=0;
        try{        
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
                stmt = conn.createStatement();               
               sql="select Id from Course where CourseName='"+courseName+"'";
                rs = stmt.executeQuery(sql);                
                while (rs.next()) {
                  cid=rs.getInt("Id");
                }
                rs.close();
                conn.close();
        }catch(Exception e){
            System.out.println("Exception found in getOnlineFriendDetails of class-OnlineUsers");
        }
        return cid;
    }
    
    public int deleteCourse(int courseId){
        int j=-1;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            String sql="delete from course where Id="+courseId+"";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            j=pstmt.executeUpdate();
            conn.close();
            return j;
        }catch(Exception e){
            e.printStackTrace();
            return j;
        }
    }
    public static void main(String[] args) {
      // Course c=new Course();
    }
}
