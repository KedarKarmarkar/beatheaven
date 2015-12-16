
package BeatHeaven_Classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DeleteCourse extends HttpServlet {

    String msg="";
    int i=0;
    private Exception SQLException;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(true); 
        int course_id=0;
        try {
            course_id=Integer.parseInt(request.getParameter("CourseId"));
            Course c=new Course();
            i=c.deleteCourse(course_id);
            if(i!=1) throw SQLException;
            else{
                out.println("Course deleted successfully:"+i);
                 msg="Course deleted successfully";
                 session.setAttribute("Message",msg);                
            }
        } catch(Exception ex) {
             out.print("Course while deleting exam:"+i);
              msg="Error while deleting Course Id:"+course_id;
              session.setAttribute("Message",msg);
              
        }
        response.sendRedirect("ViewCourse.jsp");
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
