package BeatHeaven_Classes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InstituteAccessibilityAction extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
         int institute_id=Integer.parseInt(request.getParameter("InstituteId"));  
            String email=request.getParameter("Email");
            String status=request.getParameter("Status"); 
            String sql="";
            String msg="";
            int i=0;
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                if(status.equalsIgnoreCase("block")){
                 sql = "update institute set Status=3 where Id="+institute_id+"";
                 msg="You have been blocked from beatheaven site..";
                }
                else if(status.equalsIgnoreCase("unblock")){
                 sql = "update institute set Status=1 where Id="+institute_id+""; 
                  msg="You have been unblocked from beatheaven site..";
                }
                else if(status.equalsIgnoreCase("approve")){
                   sql = "update institute set Status=1 where Id="+institute_id+""; 
                    msg="Your registration request has been approved..please visit to site for login..Thanks!!";
                }
                PreparedStatement pst = conn.prepareStatement(sql);               
                pst.executeUpdate();
                conn.close();
                //EmailSessionBean esb=new EmailSessionBean();
                //esb.sendEmail(email, "BeatHeaven accessibility & support", msg);
                
                response.sendRedirect("InstituteAccessibility.jsp");
        } catch(Exception ex) {
            out.close();
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
