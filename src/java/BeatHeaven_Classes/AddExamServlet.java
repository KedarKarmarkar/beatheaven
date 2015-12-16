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

public class AddExamServlet extends HttpServlet {
    private Exception SQLException;
    String msg="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Exception ex;
        int i=0;
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(true);             
        try {
            String institute_id="0";
            institute_id=request.getParameter("institute_id");
            msg=request.getParameter("Message");  
            Exam e=new Exam();
            Course c=new Course();
            int c_id=c.getCourseId(request.getParameter("course_id"));
            out.println("C_id:"+c_id);
            i=e.addExamDetails(request.getParameter("exam_name"),request.getParameter("institute_id"),c_id,request.getParameter("exam_date"),request.getParameter("location"),request.getParameter("exam_type"),request.getParameter("eligibility"),request.getParameter("examTime"));
            if(i!=1) throw SQLException;
            else{
                out.println("Exam details added successfully:"+i);
                 msg="Exam details added successfully:"+i;
                 session.setAttribute("Message",msg);                
            }
        } catch(Exception e) {
             out.print("Error while adding Exam:"+i);
              msg="Error while adding event:"+i;
              session.setAttribute("Message",msg);
        }
  response.sendRedirect("Institute_Profile.jsp");
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
