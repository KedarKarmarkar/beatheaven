
package BeatHeaven_Classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddInstitutFeedBack extends HttpServlet {
    
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        session = request.getSession();
        int institute_id=1;
        int kalakar_id=3;
        String msg="";
        String category="",subject="",description="";
        try {
           /* if (session == null || session.isNew()) {
                out.println("You are not logged in");
            }else{
                    Enumeration names = session.getAttributeNames();
                    while (names.hasMoreElements()) {
                        String name = (String) names.nextElement();
                        Object value = session.getAttribute(name);
                        institute_id = Integer.parseInt((session.getAttribute("Institute_Id").toString()));
                        kalakar_id=Integer.parseInt((session.getAttribute("Kalakar_Id").toString()));
                        msg=session.getAttribute("Message").toString();
                    }
            }*/
            category=request.getParameter("category");
            subject=request.getParameter("subject");
            description=request.getParameter("discription");
            Feedback f=new Feedback(institute_id, kalakar_id, "", "", subject, category, description, 0);
            int j=f.addFeedback();
            if(j==1){
             out.print("FeedBack Added successfully:"+j);
             msg="FeedBack Updated successfully:"+j;
             session.setAttribute("Message",msg);
          }else{
              out.print("Error while Adding FeedBack:"+j);
              msg="Error while Adding FeedBack:"+j;
              session.setAttribute("Message",msg);             
          }
            
        } catch(Exception ex) {
            out.close();
        }
        //response.sendRedirect("Institute_Profile.jsp");
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
