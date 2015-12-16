package BeatHeaven_Classes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();        
        try {
           checkStudLoginInfo(request, response);
        } finally {
            out.close();
        }
        
    }
    
    public void checkStudLoginInfo(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            PrintWriter out = response.getWriter();        
            HttpSession session=request.getSession(true);
            String user_name=request.getParameter("un");
            String password=request.getParameter("ps");
            String user_type=request.getParameter("ut");
            String query="",encodeURL="";
            int count=0,id=0;
            if(user_name.equals("beatHEAVEN") && password.equals("BH@3189") && user_type.equals("maifal")){
                query="select * from siteadmin where UserName=? and Password=? and Status=?";
                encodeURL="BH_Profile.jsp";
            }
            else if(user_type.equals("kalakar")){
                query="select * from kalakar where UserName=? and Password=? and Status=?";
                encodeURL="Kalakar_Profile.jsp";
            }else if(user_type.equals("maifal")){
                query="select * from institute where UserName=? and Password=? and Status=?";
                encodeURL="Institute_Profile.jsp";
            }
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");            
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user_name);
            pstmt.setString(2, password);
            pstmt.setInt(3,1);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                count++;
                id=rs.getInt("Id");
            }
            conn.close();
            if(count>0) {  
                   if(user_name.equals("beatHEAVEN") && password.equals("BH@3189") && user_type.equals("maifal")){
                         session.setAttribute("SiteAdmin_Id", id);
                    }else if(user_type.equals("maifal")){
                         session.setAttribute("Institute_Id", id);                         
                     }else if(user_type.equals("kalakar")){
                         session.setAttribute("Kalakar_Id", id);
                         OnlineUsers ou=new OnlineUsers();
                         ou.updateKalakarOnlineStatus(id, 1);
                     }
                     session.setAttribute("Message","Login successful");
                     out.print("<h4> Login Successful..</h4>");
                     response.sendRedirect(encodeURL);
               // }
            }else{
                out.print("<h4>Invalid Login..</h4>");
                out.println("<h5><a href='index.jsp'>Click</a> to try again</h5>");
                //session.invalidate();
                request.getSession().invalidate();
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
