/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author GlobeOp
 */
public class InstituteUserReqApproval extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int kalakar_id=Integer.parseInt(request.getParameter("KalakarId"));        
        String status=request.getParameter("Status");
        String email=request.getParameter("Email");
        int institute_id=Integer.parseInt(request.getParameter("InstituteId"));
        Connection conn;
        Maifal m=new Maifal();
        
        int j=-1;
        try {
            m.getMaifalDetails(institute_id);
           if(status.equalsIgnoreCase("approve")){               
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            String sql="update kalakar_institute_relation set Status=1 where KalakarId="+kalakar_id+" and InstituteId="+institute_id+"";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            j=pstmt.executeUpdate();
            conn.close();
            EmailSessionBean eb = new EmailSessionBean();
            String emailBody= "<html><body><h3>BeatHeaven Institute Reg confirmation Mail</h3><br><h5>Plz do not reply to this Mail..<br> Your Registration Request has been approved by "+m.getName() +"<br>Thanks for the registration,login to the site to get the latest update of the institute</h5></body></html>";
            eb.sendEmail(email, "BeatHeaven Institute Reg confirmation Mail", emailBody);
           }
           else if(status.equalsIgnoreCase("delete")){
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
            String sql="delete from kalakar_institute_relation where KalakarId="+kalakar_id+" and InstituteId="+institute_id+"";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            j=pstmt.executeUpdate();
            conn.close();
            EmailSessionBean eb = new EmailSessionBean();
            String emailBody= "<html><body><h3>Your Registration request for Institute:"+m.getName() +" has been rejected</h3><br><h5>Plz do not reply to this Mail..<br> </h5></body></html>";
            eb.sendEmail(email, "BeatHeaven Institute Reg Mail", emailBody);
           }  
           
        }catch(Exception ex){
            ex.printStackTrace();
        }
         response.sendRedirect("UserApproval.jsp");
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
