package BeatHeaven_Classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateInstituteFeedBack extends HttpServlet {
    private Exception SQLException;
    String msg="";
    int i;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession(true);     
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int id=Integer.parseInt(request.getParameter("Id"));
        String addCommentArea=request.getParameter("addCommentArea");
        String status=request.getParameter("status");
        String prevcomments=request.getParameter("prevcomments");
        String commentby=request.getParameter("commentby");
        try {
           Feedback fb=new Feedback();
           i=fb.updateFeedBack(id, addCommentArea, status, prevcomments,commentby);
           if(i!=1) throw SQLException;
           else{
               out.println("Feedback updated successfully:"+i);
                 msg="Feedback updated successfully:"+i;
                 session.setAttribute("Message",msg);
                
           }
               
        } catch(Exception ex){
            ex.printStackTrace();
            out.print("Error while updating feedback:"+i);
              msg="Error while updating feedback:"+i;
              session.setAttribute("Message",msg);
              response.sendRedirect("Institute_Profile.jsp");
        }
         if(commentby.equalsIgnoreCase("Institute"))
                 response.sendRedirect("Institute_Profile.jsp");
                 else
                 response.sendRedirect("User_Institute_Profile.jsp");
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
