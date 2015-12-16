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

public class Kalakar_Registration extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();        
        try {  
            RandomNoGenerator rng=new RandomNoGenerator();
            long random_code=rng.getRandomCode();  
            String email=request.getParameter("email");            
            Kalakar k=new Kalakar(request.getParameter("kname"),request.getParameter("address"),request.getParameter("city"),request.getParameter("dob"),email,request.getParameter("contact"),request.getParameter("interest"),request.getParameter("instruments"),request.getParameter("occupation"),request.getParameter("gender"),random_code);            
            int count=k.getEmailAvailability(email);
            if(count==0){
                int j=k.addKalakrDetails();
                out.println("j:"+j);
               // int i=k.sendRegCodeMail(random_code);
                //out.println("i:"+i);
                String newURL=response.encodeURL("EmailValidator.jsp?count=0");
                response.sendRedirect(newURL);
            }else{
                out.print("<br><br><center><h3>Email already exists..<a href='index.jsp'>Click to Try Again</a></h3></center>");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            out.close();
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
