package BeatHeaven_Classes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateInstituteProfile extends HttpServlet {
    private Exception SQLException;
    String msg="";
    int i;   
    String Name="", Address="", City="", EstablishDate="", Founder="", Activities="", Contact="", Email="", RegId="", Password="";
    int InstituteId;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(true); 
        try {
          Name=request.getParameter("name");
          Address=request.getParameter("address");
          City=request.getParameter("city");
          EstablishDate=request.getParameter("est_date");
          Founder=request.getParameter("founder");
          Activities=request.getParameter("activities");
          Contact=request.getParameter("contact");
          Email=request.getParameter("email");
          RegId=request.getParameter("regid");
          Password=request.getParameter("password");
          InstituteId=Integer.parseInt(request.getParameter("institute_id"));
          Maifal maifal1=new Maifal();
          i=maifal1.updateMaifalDetails(Name, Address, City, EstablishDate, Founder, Activities, Contact, Email, RegId, Password, InstituteId);
           if(i!=1) throw SQLException;
           else{
               out.println("Maifal details updated successfully:"+i);
                 msg="Maifal details updated successfully:"+i;
                 session.setAttribute("Message",msg);
                
           }
        } catch(Exception ex) {
            
            out.print("Error while updating Maifal:"+i);
            msg="Error while updating Maifal:"+i;
            session.setAttribute("Message",msg);
            out.close();
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
