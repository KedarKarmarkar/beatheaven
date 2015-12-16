package BeatHeaven_Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Feedback {
    private int institute_id,user_id,status,id;
    private  String subject,category="",description="",user_name="",email="",post_date="",comments="",comment_date="";
    public int count=0;
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    Connection conn;
    Statement stmt;
    String sql="";
    ResultSet rs;
    public Feedback(){
        
    }
    public Feedback(int instituteId,int userId,String user_name,String email,String sub,String category,String desc,int status){
        this.institute_id=instituteId;
        this.user_id=userId;
        this.category=category;
        this.subject=sub;
        this.description=desc;
        this.status=status;
        if(!(email.equals(""))){
            this.email=email;
        }
        if(!(user_name.equals(""))) {
            this.user_name=user_name;
        }   
        GetCurrentDateTime gcdt=new GetCurrentDateTime();
        this.post_date=gcdt.getCurDate();
    }
    public int getInstitute_id() {
        return institute_id;
    }

    public void setInstitute_id(int institute_id) {
        this.institute_id = institute_id;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int addFeedback(){
        int j=-1;
        try {      
            if(user_name=="" && email==""){
             Kalakar k=new Kalakar();
             k.getkalakarDetails(user_id);
             this.user_name=k.kalakar_name;
             this.email=k.email;
            }
             Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
             sql="insert into feedback(UserId,InstituteId,UserName,Email,Category,Subject,Discription,PostDate,Status) values(?,?,?,?,?,?,?,?,?)";
             PreparedStatement pstmt = conn.prepareStatement(sql);
             pstmt.setInt(1, this.user_id);
             pstmt.setInt(2,this.institute_id);
             pstmt.setString(3, this.user_name);
             pstmt.setString(4, this.email);
             pstmt.setString(5,this.category);
             pstmt.setString(6,this.subject);
             pstmt.setString(7,this.description);
             pstmt.setDate(8, java.sql.Date.valueOf(post_date));
             pstmt.setInt(9,this.status);             
             j = pstmt.executeUpdate();
             conn.close();             
             return j;
        }catch(Exception e){
            return j;
        }
    }
    
    public int updateFeedBack(int id,String comments,String status,String prevcomments,String commentBy){
        int j=-1;
        String sql="";
        String cmts="";
        int sts=0;
        try{
            if(status.equalsIgnoreCase("pending")){
                sts=0;
            }else if(status.equalsIgnoreCase("working")){
                sts=2;
            }else if(status.equalsIgnoreCase("resolved")){
                sts=1;
            }
            if(comments=="" || comments.length()==0){
                sql="update feedback set Status="+sts+" where Id="+id+"";
            }else{
                
                 GetCurrentDateTime gcdt=new GetCurrentDateTime();
                 post_date=gcdt.getCurDate();
                 cmts=prevcomments+"<br><b>"+commentBy+":"+post_date+"<b><br>"+comments;
                sql="update feedback set Comments='"+cmts+"',CommentDate='"+post_date+"',Status="+sts+" where Id="+id+"";
            }
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            j=pstmt.executeUpdate();
            conn.close();
            return j;
        }catch(Exception e){
            e.printStackTrace();
            return j;
        }
        
    }
    
    public int getAllFeedBack(int instituteId){
        int i=-1;    
        try{        
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
                stmt = conn.createStatement();            
                sql="select * from feedback where InstituteId="+instituteId+" and Status=1";
                rs = stmt.executeQuery(sql); 
                while(rs.next()){
                    id=rs.getInt("Id");
                    user_id=rs.getInt("UserId");
                    institute_id=instituteId;
                    user_name=rs.getString("UserName");
                    email=rs.getString("Email");
                    category=rs.getString("Category");
                    subject=rs.getString("Subject");
                    description=rs.getString("Discription");
                    post_date=rs.getDate("PostDate").toString();
                    comments=rs.getString("Comments");
                    comment_date=rs.getDate("CommentDate").toString();
                    status=rs.getInt("Status");
                    count++;
                }
                conn.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return i;
    }
    
    public int getFeedBackByID(int id){
        int i=-1;    
        try{        
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
                stmt = conn.createStatement();            
                sql="select * from feedback where Id="+id+"";
                rs = stmt.executeQuery(sql); 
                while(rs.next()){
                    id=rs.getInt("Id");
                    user_id=rs.getInt("UserId");
                    institute_id=rs.getInt("InstituteId");
                    user_name=rs.getString("UserName");
                    email=rs.getString("Email");
                    category=rs.getString("Category");
                    subject=rs.getString("Subject");
                    description=rs.getString("Discription");
                    post_date=rs.getDate("PostDate").toString();
                    comments=rs.getString("Comments");
                    comment_date=rs.getDate("CommentDate").toString();
                    status=rs.getInt("Status");
                     count++;
                }
                conn.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return i;
    }
    
    public int getFeedBackByID(int userId,int instituteId){
        int i=-1;    
        try{        
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
                stmt = conn.createStatement();            
                sql="select * from feedback where UserId="+userId+" and InstituteId="+instituteId+"";
                rs = stmt.executeQuery(sql); 
                while(rs.next()){
                    id=rs.getInt("Id");
                    user_id=rs.getInt("UserId");
                    institute_id=rs.getInt("InstituteId");
                    user_name=rs.getString("UserName");
                    email=rs.getString("Email");
                    category=rs.getString("Category");
                    subject=rs.getString("Subject");
                    description=rs.getString("Discription");
                    post_date=rs.getDate("PostDate").toString();
                    comments=rs.getString("Comments");
                    comment_date=rs.getDate("CommentDate").toString();
                    status=rs.getInt("Status");
                     count++;
                }
                conn.close();
                i=1;
                return i;
        }catch(Exception ex){
            ex.printStackTrace();
            return i;
        }
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }
    
}
