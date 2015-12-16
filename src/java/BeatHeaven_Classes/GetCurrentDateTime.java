/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BeatHeaven_Classes;


import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.lang.String.*;
import java.lang.StringBuffer.*;
 
 
public class GetCurrentDateTime {
  public static void main(String[] args) {
 
	  GetCurrentDateTime gcdt=new GetCurrentDateTime();
          //String str=gcdt.getTime();
          //System.out.println("Current Time"+str);
         // String s=gcdt.getCurDate();
	   //     System.out.println("Current Date"+s);
 
  }
  public String getTime(){
           DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   //get current date time with Date()
	   Date date = new Date();
	  // System.out.println(dateFormat.format(date)); 
            Calendar cal = Calendar.getInstance();
	   //System.out.println(dateFormat.format(cal.getTime()));           
           String str=(String)dateFormat.format(cal.getTime());
           String subtime=str.substring(11);
          
           String time=subtime.substring(0, 5);
           return time;
           //System.out.println("Time:"+time); 
  }  
 
  public String getCurDate(){
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date date = new Date();
      Calendar cal = Calendar.getInstance();
      String str=(String)dateFormat.format(date);
      return str;
  }

  
 public String getCurDateTime(){
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = new Date();
      Calendar cal = Calendar.getInstance();
      String str=(String)dateFormat.format(date);
      return str;
  }
}