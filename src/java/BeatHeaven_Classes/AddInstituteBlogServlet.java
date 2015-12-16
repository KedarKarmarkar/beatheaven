
package BeatHeaven_Classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
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
public class AddInstituteBlogServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();        
        try {
            HttpSession session=request.getSession(true);            
            Connection conn;
            Statement stmt;
            String sql = "",submit_param="";
            PreparedStatement pstmt;
            ResultSet rs;            
            //int postedBy =4;
            GetCurrentDateTime gcdt1=new GetCurrentDateTime();
            String cur_date=gcdt1.getCurDate();
            int instituteId=Integer.parseInt(request.getParameter("InstituteId"));
            int uploadBy=Integer.parseInt(request.getParameter("UploadBy"));
            submit_param=request.getParameter("post");
            String postTitle = request.getParameter("txtPostTitle");
            String postSubject = request.getParameter("txtSubject");
            String references = request.getParameter("txtRefrences");
            String postBody = request.getParameter("txtaPostBody");
            String visibility = request.getParameter("txtPrivacy");
            if(submit_param.equals("AttachPic")){
                session.setAttribute("PostTitle", postTitle);
                session.setAttribute("PostSubject", postSubject);
                session.setAttribute("References", references);
                session.setAttribute("PostBody", postBody);
                session.setAttribute("Visibility", visibility);
                session.setAttribute("PostUnderInstitute", instituteId);
                if(uploadBy==0){
                 session.setAttribute("LoginTypeForPost", "Institute");   
                }else{
                 session.setAttribute("LoginTypeForPost", "Kalakar");      
                }
                response.sendRedirect("AttachPostImages.jsp");
            }else if(submit_param.equals("Post")){
                Class.forName("com.mysql.jdbc.Driver");            
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                sql = "INSERT INTO instituteblog(Title,Subject,Body,Privacy,ImageLocation,Reference,UploadBy,InstituteId,UploadDate,Status) VALUES(?,?,?,?,?,?,?,?,?,?)";
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1, postTitle);
                pstmt.setString(2, postSubject);
                pstmt.setString(3, postBody);
                pstmt.setString(4, visibility);
                pstmt.setString(5,"Unavailable");
                pstmt.setString(6, references);
                pstmt.setInt(7,uploadBy);
                pstmt.setInt(8,instituteId);
                pstmt.setDate(9,Date.valueOf(cur_date));
                pstmt.setInt(10, 1);
                int  j = pstmt.executeUpdate();
                session.setAttribute("Message", "Blog added successfully..");
                response.sendRedirect("Institute_Profile.jsp");
            }
            out.println("<html> <body><div class=alert alert-success role=alert> Blog inserted succesfully</div></body></html>");
            //response.sendRedirect("Institute_Profile.jsp");
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
