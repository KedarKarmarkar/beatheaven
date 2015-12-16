package BeatHeaven_Classes;
import java.io.PrintWriter;
import java.util.Date;
import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Kalakar {
    public int kalakar_id,status; 
    long random_code;
    public String kalakar_name="",address="",city="",dob="",email="",contact="",interest="",playing_instrument="",occupation="",gender="",userName="",password="",randomno="??";
    Connection conn;
    Statement stmt;
    String sql="";
    PreparedStatement pstmt;
    ResultSet rs;
     public ArrayList<Integer> friendList=new ArrayList<Integer>();
    public Kalakar(){
        
    }
    public Kalakar(String kalakar_name,String address,String city,String dob,String email,String contact,String interest,String playing_instrument,String occupation,String gender,long random_code){
        this.kalakar_name=kalakar_name;
        this.address=address;
        this.city=city;
        this.dob=dob;
        this.email=email;
        this.contact=contact;
        this.interest=interest;
        this.playing_instrument=playing_instrument;
        this.occupation=occupation;
        this.gender=gender;  
        this.randomno=Long.toString(random_code);
    }    
    
    public int getYourFriend(int kid){
        String sql1="",sql2="";
        int i=-1;
        try{        
        Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
                stmt = conn.createStatement();               
               sql1 = "SELECT Friend_Id FROM friendlist WHERE Kalakar_Id="+kid;
               sql2 = "SELECT Kalakar_Id FROM friendlist WHERE Friend_Id="+kid;
                rs = stmt.executeQuery(sql1);                
                while (rs.next()) {
                  friendList.add(rs.getInt(1)); 
                }
                i=1;
                rs.close();
                conn.close();
                 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
                stmt = conn.createStatement();   
                rs = stmt.executeQuery(sql2);                
                while (rs.next()) {
                  friendList.add(rs.getInt(1)); 
                }
                i=1;
                rs.close();
                conn.close();
        }catch(Exception e){
            System.out.println("Exception found in gettYourFriend of class-OnlineUsers");
        }
        return i;
    }

    public void getkalakarDetails(int id){
        try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                stmt = conn.createStatement();
                sql="select * from kalakar where Id="+id+"";
                rs=stmt.executeQuery(sql);
                while(rs.next()){
                                        kalakar_name=rs.getString("Name");
                                        address=rs.getString("Address");
                                        city=rs.getString("City");
                                        dob=rs.getString("DOB");
                                        email=rs.getString("Email");
                                        contact=rs.getString("Contact");
                                        interest=rs.getString("Interest");
                                        playing_instrument=rs.getString("PlayingInstrument");
                                        occupation=rs.getString("Occupation");
                                        gender=rs.getString("Gender");
                                        password=rs.getString("Password");
                }
                conn.close();
        }catch(Exception ex){
            
        }
    }
    
    public int getUserNameAvailability(String userName){
        int count=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                stmt = conn.createStatement();
                sql="select * from kalakar where UserName='"+userName+"'";
                rs=stmt.executeQuery(sql);
                while(rs.next()){
                    count++;
                }
                conn.close();
                return count;
        }catch(Exception ex){
            ex.printStackTrace();
            return count;
        }
    }
    
    public int getEmailAvailability(String email){
        int count=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                stmt = conn.createStatement();
                sql="select * from kalakar where Email='"+email+"'";
                rs=stmt.executeQuery(sql);
                while(rs.next()){
                    count++;
                }
                conn.close();
                return count;
        }catch(Exception ex){
            ex.printStackTrace();
            return count;
        }
    }
    public int recoverLoginDetails(String email){
         int count=0;
         String uname="",pwd="";
        try{
            Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                stmt = conn.createStatement();
                sql="select UserName,Password from kalakar where Email='"+email+"'";
                rs=stmt.executeQuery(sql);
                while(rs.next()){
                    count++;
                    uname=rs.getString("UserName");
                    pwd=rs.getString("Password");
                }
                conn.close();
                EmailSessionBean eb = new EmailSessionBean();
            String emailBody= "<html><body><h3>BeatHeaven Password Recovery</h3><br><h5>Plz do not reply to this Mail..<br> UserName: " + uname + ", Password: "+pwd+" </h5></body></html>";
            eb.sendEmail(email, "BH Details", emailBody); 
                return count;
        }catch(Exception ex){
            ex.printStackTrace();
            return count;
        }
    }
    public int deleteKalakar(String email){
         int j=-1;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            String sql="delete from kalakar where Email='"+email+"' and Status=0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            j=pstmt.executeUpdate();
            conn.close();
            return j;
        }catch(Exception e){
            e.printStackTrace();
            return j;
        }
     }
    
    public int addKalakrDetails(){
        //code to add details in the db
        int j=-122;
        try {           
            Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            sql = "insert into kalakar(Name,Address,City,DOB,Email,Contact,Interest,PlayingInstrument,Occupation,Gender,Status,OnlineStatus,RandomNo) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kalakar_name);
            pstmt.setString(2, address);
            pstmt.setString(3, city);                 
            pstmt.setDate(4,java.sql.Date.valueOf(dob));
            pstmt.setString(5, email);
            pstmt.setString(6, contact); 
            pstmt.setString(7, interest);
            pstmt.setString(8, playing_instrument); 
            pstmt.setString(9,occupation ); 
            pstmt.setString(10,gender ); 
            pstmt.setInt(11,0);
            pstmt.setInt(12,0);
            pstmt.setString(13,randomno ); 
            j = pstmt.executeUpdate();            
            return j;
        } catch (Exception e) {
            e.printStackTrace();
            return j;
        }        
    }  
    public int updateKalakarInfo(String email,String user_name,String password){
        //to update profile 
        int i=0;
        try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                String sql = "update kalakar set UserName=?,Password=?,Status=1 where Email='"+email+"'";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, user_name);
                pst.setString(2, password);
                pst.executeUpdate();
                conn.close();
                i=1;
                return i;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Problem while updating data into kalakar");
            return i;
        }
    }
    
     public int updateKalakar(int kalakarId){
        //to update profile 
        int i=0;
        try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                String sql = "update kalakar set Name=?,Address=?,City=?,DOB=?,Email=?,Contact=?,Interest=?,PlayingInstrument=?"
                        + "Occupation=?,Gender=?,Password=? where Id="+kalakarId+"";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, this.kalakar_name);
                pst.setString(2, this.address);
                pst.setString(3, this.city);
                pst.setDate(4, java.sql.Date.valueOf(this.dob));
                pst.setString(5, this.email);
                pst.setString(6, this.contact);
                pst.setString(7, this.interest);
                pst.setString(8, this.playing_instrument);
                pst.setString(9, this.occupation);
                pst.setString(10, this.gender);
                pst.setString(11, this.password);
                pst.executeUpdate();
                conn.close();
                i=1;
                return i;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Problem while updating data into kalakar");
            return i;
        }
    }
    public int sendRegCodeMail(long random_code) {
        int i=-1;
        try {
            //RandomNoGenerator rng=new RandomNoGenerator();
           // random_code=rng.getRandomCode();
            EmailSessionBean eb = new EmailSessionBean();
            String emailBody= "<html><body><h3>BeatHeaven Reg. Mail</h3><br><h5>Plz do not reply to this Mail..<br> The code is:" + random_code + "</h5></body></html>";
            eb.sendEmail(email, "Reg. Code", emailBody); 
            i=1;
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Sending Mail(Reg Code Error)");
            return i;
        }
    }
    
    public String getRandomCode(String emailID){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");         
            stmt = conn.createStatement();
            String sql;
            sql = "select RandomNo from kalakar where Email='"+emailID+"'";
            ResultSet rs = stmt.executeQuery(sql);            
            while (rs.next()) {
                randomno = rs.getString("RandomNo");               
            }
            rs.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return randomno;
    }
    
    public String getKalakarName(int id){
        String k_name="";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");         
            stmt = conn.createStatement();
            String sql;
            sql = "select Name from kalakar where Id="+id+" and Status=1";
            ResultSet rs = stmt.executeQuery(sql);            
            while (rs.next()) {
                k_name = rs.getString("Name");               
            }
            rs.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return k_name;
    }
    
    public static void main(String[] args) {
       // Kalakar  k= new Kalakar();
        //System.out.println(""+k.getRandomCode("nikhilranade3189@gmail.com"));
    }
}
