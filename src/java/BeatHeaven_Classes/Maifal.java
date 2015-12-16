
package BeatHeaven_Classes;
import java.util.Date;
import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Maifal {
    private int maifal_id,status;
    private String name="",address="",city="",est_date="",founder="",activities="",contact="",email="",regid="",user_name="",password="",randomCode="";
   // long random_code;
    Connection conn;
    Statement stmt;
    String sql="";
    PreparedStatement pstmt;
    ResultSet rs;

    public int getMaifal_id() {
        return maifal_id;
    }

    public void setMaifal_id(int maifal_id) {
        this.maifal_id = maifal_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEst_date() {
        return est_date;
    }

    public void setEst_date(String est_date) {
        this.est_date = est_date;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
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

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
    
    public Maifal(){
        
    }
    public Maifal(String name,String address,String city,String esta_date,String founder,String activities,String email,String contact,String regid,long random_code){
        this.name=name;
        this.address=address;
        this.city=city;
        this.est_date=esta_date;
        this.founder=founder;
        this.activities=activities;
        this.contact=contact;
        this.email=email;
        this.regid=regid;
        this.randomCode=Long.toString(random_code);
    }
    public void getMaifalDetails(){
        //to retrive institute(maifal) details
    }
    public int addMaifalDetails(){
        //to add maifal details into db
        int j=-1;
        try {           
            Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            sql = "insert into Institute(Name,Address,City,EstablishDate,Founder,Activities,Contact,Email,RegId,RandomNo,Status) values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, city);                 
            pstmt.setDate(4,java.sql.Date.valueOf(est_date));
            pstmt.setString(5, founder);
            pstmt.setString(6, activities); 
            pstmt.setString(7, contact);
            pstmt.setString(8, email); 
            pstmt.setString(9,regid ); 
            pstmt.setString(10,randomCode ); 
            pstmt.setInt(11,0);            
            j = pstmt.executeUpdate();
            conn.close();  
            return j;
        } catch (Exception e) {
            e.printStackTrace();
            return j;
        }        
    }
    
    public int updateMaifalInfo(String email,String user_name,String password){
        //to update profile 
        int i=-1;
        try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                String sql = "update institute set UserName=?,Password=?,Status=2 where Email='"+email+"'";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, user_name);
                pst.setString(2, password);
                pst.executeUpdate();
                conn.close();
                i=1;
                return i;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Problem while updating data into maifal");
            return i;
        }
    }
    
     public int updateMaifalDetails(String Name,String Address,String City,String EstablishDate,String Founder,String Activities,String Contact,String Email,String RegId,String Password,int InstituteId){
        //to update profile 
        int i=-1;
        try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                String sql = "update institute set Name=?,Address=?,City=?,EstablishDate=?,Founder=?,Activities=?,Contact=?,Email=?,RegId=?,Password=?,Status=2 where Id="+InstituteId+"";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, Name);
                pst.setString(2, Address);
                pst.setString(3, City);
                pst.setDate(4, java.sql.Date.valueOf(EstablishDate));
                pst.setString(5, Founder);
                pst.setString(6, Activities);
                pst.setString(7, Contact);
                pst.setString(8, Email);
                pst.setString(9, RegId);
                pst.setString(10, Password);
                i=pst.executeUpdate();
                conn.close();              
                return i;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Problem while updating data into maifal");
            return i;
        }
    }
    public int recoverLoginDetails(String email){
         int count=0;
         String uname="",pwd="";
        try{
            Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                stmt = conn.createStatement();
                sql="select UserName,Password from institute where Email='"+email+"'";
                rs=stmt.executeQuery(sql);
                while(rs.next()){
                    count++;
                    uname=rs.getString("UserName");
                    pwd=rs.getString("Password");
                }
                conn.close();
                EmailSessionBean eb = new EmailSessionBean();
            String emailBody= "<html><body><h3>BeatHeaven Password Recovery</h3><br><h5>Plz do not reply to this Mail..<br> UserName: " + uname + ", Password: "+pwd+" </h5></body></html>";
            eb.sendEmail(email, "BH Institute Details Recovery..", emailBody); 
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
                sql="select * from institute where Email='"+email+"'";
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
    
     public int getUserNameAvailability(String userName){
        int count=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                stmt = conn.createStatement();
                sql="select * from institute where UserName='"+userName+"'";
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
    
     public int deleteMaifal(String email){
         int j=-1;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            String sql="delete from institute where Email='"+email+"' and Status=0";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            j=pstmt.executeUpdate();
            conn.close();
            return j;
        }catch(Exception e){
            e.printStackTrace();
            return j;
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
    public String getRandomCode(String emailId){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");         
            stmt = conn.createStatement();
            String sql;
            sql = "select RandomNo from institute where Email='"+emailId+"'";
            ResultSet rs = stmt.executeQuery(sql);            
            while (rs.next()) {
                randomCode = rs.getString("RandomNo");               
            }
            rs.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return randomCode;
    }
    public void getMaifalDetails(int instituteId){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");         
            stmt = conn.createStatement();
            String sql;
            sql = "select * from institute where Id="+instituteId+"";
            ResultSet rs = stmt.executeQuery(sql);            
            while (rs.next()) {
                this.name=rs.getString("Name");
                this.address=rs.getString("Address");
                this.city=rs.getString("City");
                this.est_date=rs.getDate("EstablishDate").toString();
                this.founder=rs.getString("Founder");;
                this.activities=rs.getString("Activities");;
                this.contact=rs.getString("Contact");;
                this.email=rs.getString("Email");;
                this.regid=rs.getString("RegId");
                this.user_name=rs.getString("UserName");
                this.password=rs.getString("Password");        
                this.randomCode=rs.getString("RandomNo");  
                this.status=rs.getInt("Status");
            }
            rs.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
