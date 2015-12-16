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
public class BirthdayWishServlet extends HttpServlet {
    private Exception SQLException;

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
        HttpSession session=request.getSession(true);             
        String msg="";
        int i=-1;
           int bdayid=Integer.parseInt(request.getParameter("bdayid"));
            String comment=request.getParameter("comment");
            String postedType=request.getParameter("postedType");
            int postedBy=Integer.parseInt(request.getParameter("postedBy"));
            out.println(bdayid+" "+comment+" "+postedBy+" "+postedType);
        //msg=session.getAttribute("msg").toString();
        try {
            
            BirthDayCalculator bdc=new BirthDayCalculator();
            i= bdc.addToBirthdayWish(bdayid, postedBy, postedType, comment);
            if(i!=1) throw SQLException;
            else{
                out.println("BirthdayWishes added successfully:"+i);
                 msg="BirthdayWishes added successfully:"+i;
                 session.setAttribute("Message",msg);  
                 response.sendRedirect("Institute_Profile.jsp");
            }
        }catch(Exception ex){
             out.print("Error while adding BirthdayWishes:"+i);
              msg="Error while adding BirthdayWishes:"+i;
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
