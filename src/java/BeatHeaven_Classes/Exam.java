package BeatHeaven_Classes;
import java.util.Date;
import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Exam {
    int exam_id,status,course_id,conductedby;
    private String exam_name="",date="",location="",exam_type="",egligibility="";

    public String getTime(){
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
     ArrayList<Integer> examid_list = new ArrayList<Integer>();
     String[][]exam_details;
     Connection conn;
    Statement stmt;
    String sql="";
    private String time;

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public int getConductedby() {
        return conductedby;
    }

    public void setConductedby(int conductedby) {
        this.conductedby = conductedby;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExam_type() {
        return exam_type;
    }

    public void setExam_type(String exam_type) {
        this.exam_type = exam_type;
    }

    public String getEgligibility() {
        return egligibility;
    }

    public void setEgligibility(String egligibility) {
        this.egligibility = egligibility;
    }

    public String[][] getExam_details() {
        return exam_details;
    }

    public void setExam_details(String[][] exam_details) {
        this.exam_details = exam_details;
    }
    ResultSet rs;
    public Exam(){
    
    }
    public int addExamDetails(String name,String conductedBy,int courseId,String d,String location,String type,String eligibility,String time){
        int j=-1;
        try {           
             Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
             String sql="insert into exam(Name,ConductedBy,CourseId,ExamDate,Location,Type,Eligibility,Status,Time) values(?,?,?,?,?,?,?,?,?)";
             PreparedStatement pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, name);
             pstmt.setInt(2,Integer.parseInt(conductedBy));
             pstmt.setInt(3, courseId);
             pstmt.setDate(4, java.sql.Date.valueOf(d));
             pstmt.setString(5,location);
             pstmt.setString(6,type);
             pstmt.setString(7,eligibility);
             pstmt.setInt(8,1);
             pstmt.setString(9,time);
             j = pstmt.executeUpdate();
             conn.close();             
             return j;
        }catch(Exception e){
            return j;
        }
    }
    public int updateExamDetails(String name,int conductedBy,int courseId,String d,String location,String type,String eligibility,int examId,String time){
        int j=-1;
        int count=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            String sql="update Exam set Name='"+name+"',ConductedBy="+conductedBy+",CourseId="+courseId+",ExamDate='"+java.sql.Date.valueOf(d)+"',Location='"+location+"',Type='"+type+"',Eligibility='"+eligibility+"',Status=2,Time='"+time+"' where Id="+examId+"";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            j=pstmt.executeUpdate();
            conn.close();
            return j;
        }catch(Exception e){
            e.printStackTrace();
            return j;
        }
        
    }
    public int deleteExam(int examId){
        int j=-1;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            String sql="delete from Exam where Id="+examId+"";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            j=pstmt.executeUpdate();
            conn.close();
            return j;
        }catch(Exception e){
            e.printStackTrace();
            return j;
        }
    }
    public void getExamDetails(int institute_id){
        int row=0;
        int col=0;
        try{        
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
                stmt = conn.createStatement();            
                sql="select * from exam where ConductedBy="+institute_id+"";
                rs = stmt.executeQuery(sql); 
                while(rs.next()){
                    System.out.println("Resultset:"+rs.getInt(1));
                    for(int i=0;i<1;i++)
                    exam_details[row][i]=Integer.toString(rs.getInt(i));
                    row++;
                    
                }
                conn.close();
                for(int i=0;i<exam_details.length;i++){
                    for(int j=0;j<1;i++)
                    System.out.print("welcome:"+exam_details[i][j]+"\t");
                    System.out.println("");
                }
                
        }catch(Exception e){
            
        }
    }
    public ArrayList<Integer> getExamId(int institute_id){
        try{        
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
                stmt = conn.createStatement();               
               sql="select Id from Exam where ConductedBy='"+institute_id+"' and Status=1 ";
                rs = stmt.executeQuery(sql);                
                while (rs.next()) {
                  examid_list.add(rs.getInt("Id")) ; 
                }
                rs.close();
                conn.close();
        }catch(Exception e){
            System.out.println("Exception found in getOnlineFriendDetails of class-OnlineUsers");
        }
        return examid_list;
    }
    public void getExamInfo(int exam_id){
        //code to retrive from the db
     
     try{
         Class.forName("com.mysql.jdbc.Driver");     
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
         stmt = conn.createStatement();            
         sql="select * from exam where Id="+exam_id+"";
         rs = stmt.executeQuery(sql); 
         while(rs.next()){
             this.exam_id = exam_id;             
             this.exam_name = rs.getString("Name");             
             this.conductedby=rs.getInt("ConductedBy");
             this.course_id=rs.getInt("CourseId");
             this.date=rs.getDate("ExamDate").toString();
             this.location=rs.getString("Location");
             this.exam_type=rs.getString("Type");
             this.egligibility=rs.getString("Eligibility");
             this.time=rs.getString("Time");
         }
         conn.close();
     }catch(Exception e){
         e.printStackTrace();
     }
    }
    public static void main(String[] args) {
        Exam e=new Exam();
        e.getExamDetails(1);
    }
    
}
