/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BeatHeaven_Classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateCourseServlet extends HttpServlet {
    private Exception SQLException;    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String msg="";
        int i=0;
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(true);             
        try {            
           msg=request.getParameter("Message"); 
           String institute_id="0";
           String info=request.getParameter("specification");
           String details=request.getParameter("courseInfo");
           institute_id=request.getParameter("institute_id");
           String courseDetails="";           
            if(info==""|| info.trim().length()==0|| info==null)
                courseDetails=details;
            else
                courseDetails=info;
           Course c=new Course(request.getParameter("course_name"),request.getParameter("duration"),Integer.parseInt(institute_id),Integer.parseInt(request.getParameter("fee")),request.getParameter("eligibility"),request.getParameter("specification"));
           i=c.addCourseDetails();
           i=c.updateCourseDetails(request.getParameter("course_name"),request.getParameter("duration"),Integer.parseInt(request.getParameter("fee")),request.getParameter("eligibility"),courseDetails,Integer.parseInt(request.getParameter("course_id")));
           if(i!=1) throw SQLException;
            else{
                out.println("Course updated successfully:"+i);
                 msg="Course updated successfully:"+i;
                 session.setAttribute("Message",msg);  
                 response.sendRedirect("ViewCourse.jsp");
            }           
        } catch(Exception e) {
             out.print("Error while updating Course:"+i);
              msg="Error while updating Course:"+i;
              session.setAttribute("Message",msg);
              response.sendRedirect("ViewCourse.jsp");
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
