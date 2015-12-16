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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import sun.util.calendar.LocalGregorianCalendar;
/**
 *
 * @author GlobeOp
 */
public class BirthDayCalculator {
    private int day=0;
    Connection conn,conn2;
    Statement stmt,stmt2;
    String sql="";
    PreparedStatement pstmt;
    ResultSet rs;
    Boolean flag=false;
    public ArrayList<Integer> kalakarIdList=new ArrayList<Integer>();
    public ArrayList<Integer> bdayIdList=new ArrayList<Integer>();
    public ArrayList<Integer> eventIdList=new ArrayList<Integer>();
    int instituteNotificationCount=0;
    int instituteBdayCount=0;
    int eventCount=0;
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
    public String getLastSeen(int id,String loginType){
        String last_seen="";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");         
            stmt = conn.createStatement();
            String sql;
            sql="select Last_Seen from lastseenstatus where UserId="+id+" and LoginType='"+loginType+"'";
            rs = stmt.executeQuery(sql);            
            while (rs.next()) {
                        last_seen=rs.getDate("Last_Seen").toString();
            }
            conn.close();
            return last_seen;
        }catch(Exception e){
            e.printStackTrace();
            return "-1";
        }
    }
    public int getInstituteNotificationCount(int instituteId){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");         
            stmt = conn.createStatement();
            String sql;
            sql="select KalakarId from kalakar_institute_relation where InstituteId="+instituteId+" and (Status=1 or Status=2)";
            rs = stmt.executeQuery(sql);            
            while (rs.next()) {
                        kalakarIdList.add(rs.getInt("KalakarId"));
            }
            conn.close();
            for(int i=0;i<kalakarIdList.size();i++){
                flag=getBirthDay(kalakarIdList.get(i));
                if(flag==true){
                    instituteBdayCount++;
                    bdayIdList.add(kalakarIdList.get(i));
                }
            }
            //////////////////////////////////////////////////
            Class.forName("com.mysql.jdbc.Driver");
            conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");         
            stmt2 = conn2.createStatement(); 
            String cur_date="1900-01-01";
            String last_seen=getLastSeen(instituteId, "Institute");
            GetCurrentDateTime gcdt=new GetCurrentDateTime();
            cur_date=gcdt.getCurDate();
            sql="select Id from eventinfo where CreatedBy="+instituteId+" and PublishFrom>='"+last_seen+"' and EndDate>='"+ cur_date+"' and Status != 0";                    
            rs = stmt2.executeQuery(sql);            
            while (rs.next()) {
                eventIdList.add(rs.getInt("Id"));
                eventCount++;
            }
            conn2.close();        
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        instituteNotificationCount=instituteBdayCount+eventCount;
        return instituteNotificationCount;
    }
    
    public int getKalakarNotificationCount(int kalakarId){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");         
            stmt = conn.createStatement();
            String sql;
            sql="select Friend_Id from friendlist where Kalakar_Id="+kalakarId+" and Status=1";
            rs = stmt.executeQuery(sql);            
            while (rs.next()) {
                        kalakarIdList.add(rs.getInt("Friend_Id"));
            }
            conn.close();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");         
            stmt = conn.createStatement();
            
            sql="select Kalakar_Id from friendlist where Friend_Id="+kalakarId+" and Status=1";
            rs = stmt.executeQuery(sql);            
            while (rs.next()) {
                        kalakarIdList.add(rs.getInt("Kalakar_Id"));
            }
            conn.close();
            for(int i=0;i<kalakarIdList.size();i++){
                flag=getBirthDay(kalakarIdList.get(i));
                if(flag==true){
                    instituteBdayCount++;
                    bdayIdList.add(kalakarIdList.get(i));
                }
            }
            //////////////////////////////////////////////////
            Class.forName("com.mysql.jdbc.Driver");
            conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");         
            stmt2 = conn2.createStatement(); 
            String cur_date="1900-01-01";
            String last_seen=getLastSeen(kalakarId, "Kalakar");
            GetCurrentDateTime gcdt=new GetCurrentDateTime();
            cur_date=gcdt.getCurDate();
            sql="select ei.Id from eventinfo ei,kalakar_institute_relation kir where kir.InstituteId IN(select InstituteId from kalakar_institute_relation where KalakarId="+kalakarId+") and ei.PublishFrom>='"+last_seen+"' and ei.EndDate>='"+cur_date+"' and (ei.Status=1 or ei.Status=2)";
            rs = stmt2.executeQuery(sql);            
            while (rs.next()) {
                eventIdList.add(rs.getInt("Id"));
                eventCount++;
            }
            conn2.close();        
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        instituteNotificationCount=instituteBdayCount+eventCount;
        return instituteNotificationCount;
    }
    
    public boolean getBirthDay(int kalakarId){  
        boolean isBirthday=false;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");         
            stmt = conn.createStatement();
            String sql;
            String dob="";
            String cur_date="";
            sql = "select DOB from kalakar where Id="+kalakarId+"";
            ResultSet rs = stmt.executeQuery(sql);            
            while (rs.next()) {
                        dob=rs.getDate("DOB").toString();
            }       
            rs.close();
            conn.close();           
                dob=dob.substring(5).trim(); 
                GetCurrentDateTime gcdt=new GetCurrentDateTime();
                cur_date=gcdt.getCurDate();
                cur_date=cur_date.substring(5).trim();
                System.out.println("cur_date:"+cur_date+" DOB:"+dob);
                if(dob.equals(cur_date)){                    
                    isBirthday=true;
                    return isBirthday;
                }else
                    return isBirthday;       
                
        }catch(Exception e){
            e.printStackTrace();
            return isBirthday;
        }
        
    }
     public int updateLast_Seen(int userId,String loginType){
         int i=-1;
      try{   
            int count=0;
            GetCurrentDateTime gcdt=new GetCurrentDateTime();
            String cur_datetime=gcdt.getCurDateTime();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
             stmt = conn.createStatement();       
            sql="select * from lastseenstatus where UserId="+userId+" and loginType='"+loginType+"'";
            rs = stmt.executeQuery(sql); 
            while(rs.next())
                count++;
            conn.close();
            if(count>0){
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                sql="update lastseenstatus set Last_Seen='"+gcdt.getCurDateTime()+"',Status=2 where UserId="+userId+" and loginType='"+loginType+"'";
                PreparedStatement pstmt = conn.prepareStatement(sql);  
                i=pstmt.executeUpdate();
                conn.close();
            }else{
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                sql="insert into lastseenstatus(UserId,Last_Seen,Status,LoginType) values("+userId+",'"+cur_datetime+"',1,'"+loginType+"')";
                PreparedStatement pstmt = conn.prepareStatement(sql);  
                /*pstmt.setInt(1, userId);
                pstmt.setDate(2, java.sql.Date.valueOf(cur_datetime));
                pstmt.setInt(3, 1);
                pstmt.setString(4, loginType);*/
                i=pstmt.executeUpdate();
                conn.close();
            }
            return i;
         }catch(Exception ex){
             ex.printStackTrace();
             return i;
         }
     }
     public String insertIntoLastSeenStatus(int userId,String loginType){
         int i=-1;
         try{   
            int count=0;
            GetCurrentDateTime gcdt=new GetCurrentDateTime();
            String cur_datetime=gcdt.getCurDateTime();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            stmt = conn.createStatement();       
            sql="select * from lastseenstatus where UserId="+userId+" and loginType='"+loginType+"'";
            rs = stmt.executeQuery(sql); 
            while(rs.next()){
                count++;}
            conn.close();
            if(count==0){
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                //sql="insert into lastseenstatus(UserId,Last_Seen,Status,LoginType) values(?,?,?,?)";
                sql="insert into lastseenstatus(UserId,Last_Seen,Status,LoginType) values("+userId+",'"+cur_datetime+"',1,'"+loginType+"')";
                PreparedStatement pstmt = conn.prepareStatement(sql);  
               /* pstmt.setInt(1, userId);
                pstmt.setDate(2, java.sql.Date.valueOf(cur_datetime));
                pstmt.setInt(3, 1);
                pstmt.setString(4, loginType);*/
                i=pstmt.executeUpdate();
                conn.close();
            }
            return "1";
          }catch(Exception ex){
              ex.printStackTrace();
              return ""+i;
          }
     }
     
     public String getBirthDayWishDate(int bdayId,int postedById,String postedByType){
        String bday_wishdate="-1";
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            stmt = conn.createStatement();       
            sql="select PostedDate from birthdaywish where BdayId="+bdayId+" and PostBy='"+postedById+"' and PostedbyType='"+postedByType+"'";
            rs = stmt.executeQuery(sql); 
            while(rs.next()){
                bday_wishdate=rs.getDate("PostedDate").toString();
            }            
            conn.close();  
            bday_wishdate=bday_wishdate.substring(0,10);
            return bday_wishdate;
        }catch(Exception ex){
            return bday_wishdate;
        }        
     }     
     public int addToBirthdayWish(int bdayId,int postedById,String postedByType,String bdayMsg){
         int j=-11;
         try{
                GetCurrentDateTime gcdt=new GetCurrentDateTime();
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                sql="insert into birthdaywish(BdayId,BdayMsg,PostBy,PostedbyType,PostedDate,Status) values("+bdayId+",'"+bdayMsg+"',"+postedById+",'"+postedByType+"','"+gcdt.getCurDateTime()+"',1)";
                PreparedStatement pstmt = conn.prepareStatement(sql);  
                /*pstmt.setInt(1, userId);
                pstmt.setDate(2, java.sql.Date.valueOf(cur_datetime));
                pstmt.setInt(3, 1);
                pstmt.setString(4, loginType);*/
                j=pstmt.executeUpdate();
                conn.close();
                return j;
         }catch(Exception ex){
                ex.printStackTrace();
                return j;
         }
     }
    public static void main(String[] args) {
        BirthDayCalculator bdc=new BirthDayCalculator();
       // System.out.println(""+bdc.getInstituteNotificationCount(1));
        //System.out.println("falg:"+bdc.getBirthDay(3));
        bdc.addToBirthdayWish(1, 1, "Institute", "happy bday");
    }
}
