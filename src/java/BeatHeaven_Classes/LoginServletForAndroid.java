package BeatHeaven_Classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class LoginServletForAndroid extends HttpServlet {

    private static final long serialVersionUID = 1L;
    String uid, ss, fname, lname, hcid, fcid;
    boolean status = false;
    int count = 0,id=0;
    String query="";
   String name="";
    public LoginServletForAndroid() {
        super();
       
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             
        String user_name = request.getParameter("un");
        String password = request.getParameter("ps");
        String user_type = request.getParameter("ut");
        PrintWriter out = response.getWriter();
        
        /*System.out.println("hai");
         String name=request.getParameter("name");
         String pwd=request.getParameter("pwd");
         System.out.println("the name is "+name);
         System.out.println("password is "+pwd);
         Statement stmt=null;
         ResultSet rs=null;
         boolean val = false;
         Connection con=DBConnection.getConnection();
         stmt=con.createStatement();
         PreparedStatement pst=con.prepareStatement("select ul.user_id,u.fname,u.lname,u.h_city_id,u.f_city_id from eventdb.userlogin ul,eventdb.endusers u where ul.uname=? and ul.password=? and u.user_id=ul.user_id");
         pst.setString(1,name);
         pst.setString(2,pwd);
		
         rs = pst.executeQuery();
	
         while(rs.next())
         {
         val = true;
         uid=rs.getString(1);
         fname=rs.getString(2);
         lname=rs.getString(3);
         fcid=rs.getString(4);
         hcid=rs.getString(5);
         } */
        try {
             if(user_name.equals("BeatHeaven") && password.equals("BH@3189")){
                query="select * from admin where UserName=? and Password=? and Status=?";
                //encodeURL="BH_Profile.jsp";
            }
            else if(user_type.equals("kalakar")){
                query="select * from kalakar where UserName=? and Password=? and Status=?";
                //encodeURL="Kalakar_Profile.jsp";
            }else if(user_type.equals("maifal")){
                query="select * from institute where UserName=? and Password=? and Status=?";
                //encodeURL="Institute_Profile.jsp";
            }
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user_name);
            pstmt.setString(2, password);
            pstmt.setInt(3, 1);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                
                status=true;
                count++;
                id = rs.getInt("Id");
                name=rs.getString("Name");
            }

            if (status) {
                System.out.println("Record found");
                ss = "Success";
               System.out.println(fcid + "  " + hcid);

            } else {
                System.out.println("Record not found");
                ss = "Invalid";
            }
            out.print(ss + "~" + id + "~" +name );

        } catch (Exception e) {
            // TODO Auto-generated cat~ch block
            e.printStackTrace();
        }

    }
}
