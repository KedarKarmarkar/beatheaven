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
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author OM DATTA
 */
public class AddPersonalBlogServlet extends HttpServlet {

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
        try {
            Connection conn;
            Statement stmt;
            String sql = "";
            PreparedStatement pstmt;
            ResultSet rs;
            HttpSession session = request.getSession(true);
            //int postedBy =4;
            int postedBy=Integer.parseInt(session.getAttribute("Kalakar_Id").toString());
            String postTitle = request.getParameter("txtPostTitle");
            String postSubject = request.getParameter("txtSubject");
            String references = request.getParameter("txtRefrences");
            String postBody = request.getParameter("txtaPostBody");
            String visibility = request.getParameter("txtPrivacy");
            String submit_param=request.getParameter("post");
            if(submit_param.equals("AttachPic")){
                session.setAttribute("PostTitle", postTitle);
                session.setAttribute("PostSubject", postSubject);
                session.setAttribute("References", references);
                session.setAttribute("PostBody", postBody);
                session.setAttribute("Visibility", visibility); 
                response.sendRedirect("AttachPersonalBlogImages.jsp");
            }else if(submit_param.equals("Post")){
                GetCurrentDateTime gcdt=new GetCurrentDateTime();
                String cur_date=gcdt.getCurDate();
                cur_date=gcdt.getCurDateTime();
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                sql = "INSERT INTO personalblog(Title,Subject,Body,ImageLocation,Reference,UploadBy,UploadDate,Likes,Dislikes,VisibilityLevel) VALUES(?,?,?,?,?,?,'"+cur_date+"',0,0,?)";
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1, postTitle);
                pstmt.setString(2, postSubject);
                pstmt.setString(3, postBody);
                pstmt.setString(4, "Unavailable");
                pstmt.setString(5, references );
                pstmt.setInt(6,postedBy);
                pstmt.setString(7, visibility);
                int  j = pstmt.executeUpdate();
                out.println("<html> <body><div class=alert alert-success role=alert> inserted succesfully</div></body></html>");
                session.setAttribute("Message","Added Blog Successfully");
                response.sendRedirect("Kalakar_Profile.jsp");
            }
        } catch (Exception e) {
            out.println(e);

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
