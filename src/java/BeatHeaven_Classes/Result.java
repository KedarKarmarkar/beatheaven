package BeatHeaven_Classes;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Result {
    Connection conn;
    Statement stmt;
    String sql="";
    PreparedStatement pstmt;
    ResultSet rs;
    public Result(){
        
    }
    public int addResultDetails(int exam_id,int upload_by,String result_desc,String file_loc ){
        int j=-1;
        try{
            GetCurrentDateTime gcdt=new GetCurrentDateTime();
            String cur_date=gcdt.getCurDate();            
            Class.forName("com.mysql.jdbc.Driver");        
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            sql="insert into result(ExamId,PublishDate,UploadBy,ResultDesc,FileLocation,Status) values(?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, exam_id);            
            pstmt.setDate(2, Date.valueOf(cur_date));
            pstmt.setInt(3, upload_by);
            pstmt.setString(4, result_desc);
            pstmt.setString(5, file_loc);
            pstmt.setInt(6,1);
            j = pstmt.executeUpdate();
            conn.close();            
            return j;
        }catch(Exception e){
            e.printStackTrace();
            return j;
        }
    }
    public int updateResultDetails(int exam_id,int upload_by,String result_desc,String file_loc ){
        int j=-1;
        try{
            GetCurrentDateTime gcdt=new GetCurrentDateTime();
            String cur_date=gcdt.getCurDate();            
            Class.forName("com.mysql.jdbc.Driver");        
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            sql="update result set ExamId=?,PublishDate=?,UploadBy=?,ResultDesc=?,FileLocation=?,Status=? where ExamId="+exam_id+"";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, exam_id);            
            pstmt.setDate(2, Date.valueOf(cur_date));
            pstmt.setInt(3, upload_by);
            pstmt.setString(4, result_desc);
            pstmt.setString(5, file_loc);
            pstmt.setInt(6,2);
            j = pstmt.executeUpdate();
            conn.close();            
            return j;
        }catch(Exception e){
            e.printStackTrace();
            return j;
        }
    }
    public int updateResultFileName(String fname,int examId){
        int j=-1;
        try{
            GetCurrentDateTime gcdt=new GetCurrentDateTime();
            String cur_date=gcdt.getCurDate();            
            Class.forName("com.mysql.jdbc.Driver");        
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            sql="update result set FileLocation=? where ExamId="+examId+"";
            PreparedStatement pstmt = conn.prepareStatement(sql);            
            pstmt.setString(1, fname);            
            j = pstmt.executeUpdate();
            conn.close();            
            return j;
        }catch(Exception e){
            e.printStackTrace();
            return j;
        }
    }
    
    public int getResultAvailability(int exam_id){
        int j=-1;
        int count=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");     
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
            stmt = conn.createStatement();            
            sql="select * from result where ExamId="+exam_id+"";
            rs = stmt.executeQuery(sql); 
            while(rs.next()){
                count++;
            }
            conn.close();
            if(count>0)
                return 1;
            else
                return 0;
        }catch(Exception ex){
          return j;
        }
    }
}
