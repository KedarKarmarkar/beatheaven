package BeatHeaven_Classes;

import java.util.Date;
import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class EventInfo {
    private int event_id;
    private String event_name;
    private String location;
    private String eventDate;
    private String startDate;
    private String endDate;
    private String time;
    public ArrayList<Integer> eventid_list=new ArrayList<Integer>();
    public ArrayList<String> eventname_list=new ArrayList<String>();
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    private String visibility_level;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String StartDate) {
        this.startDate = StartDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String EndDate) {
        this.endDate = EndDate;
    }

    public String getVisibility_level() {
        return visibility_level;
    }

    public void setVisibility_level(String visibility_level) {
        this.visibility_level = visibility_level;
    }
    private String eventDetails;
    Connection conn;
    Statement stmt;
    String sql="";
    PreparedStatement pstmt;
    ResultSet rs;

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String evant_name) {
        this.event_name = evant_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }
    
    public EventInfo(int event_id, String evant_name, String location, String eventDate, String eventDetails) {
        this.event_id = event_id;
        this.event_name = evant_name;
        this.location = location;
        this.eventDate = eventDate;
        this.eventDetails = eventDetails;
        
    }
    public EventInfo(){
        
    }
    public void getEventInfo(int event_id){
        //code to retrive from the db
     
     try{
         Class.forName("com.mysql.jdbc.Driver");     
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
         stmt = conn.createStatement();            
         sql="select * from eventinfo where Id="+event_id+"";
         rs = stmt.executeQuery(sql); 
         while(rs.next()){
             this.event_id = event_id;
             this.eventDate = rs.getDate("PublishFrom").toString();
             this.event_name = rs.getString("Title");
             this.eventDetails=rs.getString("Info");
             this.location=rs.getString("Location");
             this.startDate=rs.getDate("StartDate").toString();
             this.endDate=rs.getDate("EndDate").toString();
             this.time=rs.getString("Time");
             this.visibility_level=rs.getString("VisibilityLevel");         
         }
         conn.close();
     }catch(Exception e){
         e.printStackTrace();
     }
    }
    
    public void getEventIdList(int instituteId){
     try{
         Class.forName("com.mysql.jdbc.Driver");     
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
         stmt = conn.createStatement();            
         sql="select Id,Title from eventinfo where CreatedBy="+instituteId+" and Status!=0";
         rs = stmt.executeQuery(sql); 
         while(rs.next()){
            eventid_list.add(rs.getInt("Id"));
            eventname_list.add(rs.getString("Title"));
         }
         conn.close();
     }catch(Exception e){
         e.printStackTrace();
     }        
    }
    
    public int getEventImageCount(int eventId){
        int imageCount=0;
        try{
         Class.forName("com.mysql.jdbc.Driver");     
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
         stmt = conn.createStatement();            
         sql="select ImageCount from gallery where EventId="+eventId+"";
         rs = stmt.executeQuery(sql); 
         while(rs.next()){
            imageCount=rs.getInt("ImageCount");
         }
         conn.close();
     }catch(Exception e){
         e.printStackTrace();
     }  
        return imageCount;
    }
    
    public int updateEventImageCount(int eventId,int imageCount){
        int i=-1;
        try{         
            Class.forName("com.mysql.jdbc.Driver");
            int imagCnt=imageCount+1;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            sql="update gallery set ImageCount="+imagCnt+" where EventId="+eventId+"";
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            i=pstmt.executeUpdate();
            conn.close();
            return i;
         }catch(Exception ex){
                ex.printStackTrace();
            return i;
         }
    }
    
    public int updateEventGallery(int evenId,int instituteId,int imageCount,String imgName,String location){
        int i=-1;
        try{
         if(imageCount>0){   
            Class.forName("com.mysql.jdbc.Driver");
           
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            sql="update gallery set ImageCount="+imageCount+" where EventId="+evenId+" and InstituteId="+instituteId+"";
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            i=pstmt.executeUpdate();
            conn.close();
            return i;
         }else{ 
            GetCurrentDateTime gcdt=new GetCurrentDateTime();
            String curr_date=gcdt.getCurDate();             
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            sql="insert into gallery(ImageName,ImageLocation,EventId,InstituteId,PostDate,ImageCount,Status) value(?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, imgName);
            pstmt.setString(2, location);
            pstmt.setInt(3, evenId);
            pstmt.setInt(4, instituteId);
            pstmt.setDate(5,java.sql.Date.valueOf(curr_date));
            pstmt.setInt(6, imageCount);
            pstmt.setInt(7, 1);
            i=pstmt.executeUpdate();
            conn.close();
            return i;
         }
        }catch(Exception ex){
            ex.printStackTrace();
            return i;
        }
    }
    
    public int addSongUploadDetails(String title,String songby,String singer,String album,String type,int uploadBy,String userType,String fileLocation){
        int i=-1; 
        try {           
            GetCurrentDateTime gcdt=new GetCurrentDateTime();
            String curr_date=gcdt.getCurDate();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            sql="insert into songcollection(Name,SongBy,Singer,Album,SongType,UploadBy,UserType,FileLocation,PostDate,Status) values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, songby);
            pstmt.setString(3,singer);
            pstmt.setString(4, album);
            pstmt.setString(5, type);
            pstmt.setInt(6,uploadBy);
            pstmt.setString(7, userType);
            pstmt.setString(8, fileLocation);
            pstmt.setDate(9,java.sql.Date.valueOf(curr_date));
            pstmt.setInt(10,1);
            i=pstmt.executeUpdate();
            conn.close();
             return i;
         }catch(Exception ex){
             ex.printStackTrace();
              return i;
         }
       
    }
    
    public int updateSongDetails(String oldLocation,String newLocation,int instituteId ){
        int i=-1;
        try{
        
            Class.forName("com.mysql.jdbc.Driver");
           
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            sql="update songcollection set FileLocation='"+newLocation+"' where UploadBy="+instituteId+" and FileLocation='"+oldLocation+"'";
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            i=pstmt.executeUpdate();
            conn.close();
            return i;
         }catch(Exception ex){
             ex.printStackTrace();
             return i;
         }
    }
    public String addEventDetails(int eid,String ename,String info,String loc,String esdate,String eedate,String time,String visibility){
        String j="";
        try {           
            GetCurrentDateTime gcdt=new GetCurrentDateTime();
            String curr_date=gcdt.getCurDate();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            sql="insert into eventinfo(CreatedBy,PublishFrom,Title,Info,Location,StartDate,EndDate,Time,Status,VisibilityLevel) values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,eid);
            pstmt.setDate(2,java.sql.Date.valueOf(curr_date));
            pstmt.setString(3, ename);
            pstmt.setString(4, info);
            pstmt.setString(5, loc);
            pstmt.setDate(6, java.sql.Date.valueOf(esdate));
            pstmt.setDate(7, java.sql.Date.valueOf(eedate));
            pstmt.setString(8, time);
            pstmt.setInt(9,1);
            pstmt.setString(10,visibility);
            pstmt.executeUpdate();
            conn.close();
            return j;
        }catch(Exception e){
           e.printStackTrace();
           j=e.getMessage();
            return j;
        }
    }    
    public int deleteEvent(int eventId){
        int j=-1;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            String sql="delete from eventinfo where Id="+eventId+"";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            j=pstmt.executeUpdate();
            conn.close();
            return j;
        }catch(Exception e){
            e.printStackTrace();
            return j;
        }
    }
    
    public int updateEvent(int eid,String ename,String ed,String info,String loc,String esdate,String eedate,String time,String visibility) {
        int j=-1;
        String eventDetails="";
        if(info==""|| info.trim().length()==0)
            eventDetails=ed;
        else
            eventDetails=info;
        try {           
            GetCurrentDateTime gcdt=new GetCurrentDateTime();
            String curr_date=gcdt.getCurDate();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            sql="update eventinfo set Title=?,Info=?,Location=?,StartDate=?,EndDate=?,Time=?,Status=?,VisibilityLevel=? where Id="+eid+"";
            PreparedStatement pstmt = conn.prepareStatement(sql);           
            pstmt.setString(1, ename);
            pstmt.setString(2, eventDetails);
            pstmt.setString(3, loc);
            pstmt.setDate(4, java.sql.Date.valueOf(esdate));
            pstmt.setDate(5, java.sql.Date.valueOf(eedate));
            pstmt.setString(6, time);
            pstmt.setInt(7,2);
            pstmt.setString(8,visibility);
            j=pstmt.executeUpdate();
            conn.close();
            return j;
        }catch(Exception e){
           e.printStackTrace();           
            return j;
        }
    }
    
    public ArrayList<String> getAllCategoryForMedia(String category,int id,String userType){
        ArrayList<String> category_list=new ArrayList<String>();
        try{
         Class.forName("com.mysql.jdbc.Driver");     
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
         stmt = conn.createStatement();         
         if(category.equalsIgnoreCase("All")){
             sql="select  * from songcollection where UploadBy="+id+" and UserType='"+category+"'";
         }else{
            sql="select distinct("+category+") from songcollection where UploadBy="+id+" and UserType='"+userType+"'";
         }
         rs = stmt.executeQuery(sql); 
         while(rs.next()){
             category_list.add(rs.getString(1));
         }
         conn.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return category_list;
    }
    public void notifyUser(){
        //to send notification
    }
}
