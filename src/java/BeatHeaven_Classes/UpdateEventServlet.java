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

public class UpdateEventServlet extends HttpServlet {
    String msg="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        try {
            String info="";
           HttpSession session=request.getSession(true);             
           msg=request.getParameter("Message");  
          int createdBy=Integer.parseInt(request.getParameter("created_by") );
          String eventTitle=request.getParameter("event_name");
          String location=request.getParameter("location");
          String startDate=request.getParameter("start_date");
          String endDate=request.getParameter("end_date");
          String time=request.getParameter("time");
          info=request.getParameter("event_details");
          String visibility=request.getParameter("visible_level");
          String ed=request.getParameter("sendEventDetails");
          EventInfo ei=new EventInfo();
          GetCurrentDateTime gcdt=new GetCurrentDateTime();
            String curr_date=gcdt.getCurDate();
            //out.print("I am here :"+info+" ::"+ed);
          int j=ei.updateEvent(createdBy,eventTitle,ed,info,location,startDate,endDate,time,visibility);
          if(j==1){
             out.print("Event Updated successfully:"+j);
             msg="Event Updated successfully:"+j;
             session.setAttribute("Message",msg);
          }else{
              out.print("Error while Updating event:"+j);
              msg="Error while Updating event:"+j;
              session.setAttribute("Message",msg);
             // out.print("Error while adding event:"+j+" ==>vlevel:"+visibility+" createdBy:"+createdBy+" curr_date:"+curr_date+" eventinfo:"+EventInfo);
          }
         // out.print("Updating event:"+j);
          response.sendRedirect("Institute_Profile.jsp");
        }catch(Exception e){
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
