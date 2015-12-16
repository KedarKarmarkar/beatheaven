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

/**
 *
 * @author GlobeOp
 */
public class AddCourseServlet extends HttpServlet {
    private Exception SQLException;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String msg="";
        int i=0;
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(true);             
        try {
            
           msg=request.getParameter("Message"); 
           String institute_id="0";
           institute_id=request.getParameter("institute_id");
           Course c=new Course(request.getParameter("course_name"),request.getParameter("duration"),Integer.parseInt(institute_id),Integer.parseInt(request.getParameter("fee")),request.getParameter("eligibility"),request.getParameter("specification"));
           i=c.addCourseDetails();
           
           if(i!=1) throw SQLException;
            else{
                out.println("Course added successfully:"+i);
                 msg="Course added successfully:"+i;
                 session.setAttribute("Message",msg);  
                 response.sendRedirect("Institute_Profile.jsp");
            }
           
        } catch(Exception e) {
             out.print("Error while adding Course:"+i);
              msg="Error while adding Course:"+i;
              session.setAttribute("Message",msg);
              response.sendRedirect("Institute_Profile.jsp");
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
