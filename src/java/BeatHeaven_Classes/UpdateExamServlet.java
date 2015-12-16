/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BeatHeaven_Classes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GlobeOp
 */
public class UpdateExamServlet extends HttpServlet {

    String msg="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           String info="";
           HttpSession session=request.getSession(true);             
           msg=request.getParameter("Message");  
           String institute_id="0";
           institute_id=request.getParameter("institute_id");
           Exam e=new Exam();
           int j=e.updateExamDetails(request.getParameter("exam_name"),Integer.parseInt(request.getParameter("institute_id")),Integer.parseInt(request.getParameter("course_id")),request.getParameter("exam_date"),request.getParameter("location"),request.getParameter("exam_type"),request.getParameter("eligibility"),Integer.parseInt(request.getParameter("exam_id")),request.getParameter("time"));
           if(j==1){
             out.print("Exam Updated successfully:"+j);
             msg="Exam Updated successfully:"+j;
             session.setAttribute("Message",msg);
          }else{
              out.print("Error while Updating Exam:"+j);
              msg="Error while Updating Exam:"+j;
              session.setAttribute("Message",msg);
             // out.print("Error while adding event:"+j+" ==>vlevel:"+visibility+" createdBy:"+createdBy+" curr_date:"+curr_date+" eventinfo:"+EventInfo);
          }
         // out.print("Updating event:"+j);
          response.sendRedirect("Institute_Profile.jsp");
        } catch(Exception e){
            e.printStackTrace();
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
