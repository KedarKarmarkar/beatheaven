
package BeatHeaven_Classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GlobeOp
 */
public class AddEventImagesServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(true);  
        response.setContentType("text/html");      
        int event_id=1,count=0;
        int institute_id=1;
        /*if (session == null || session.isNew()) { 
                    
                } else {
                    Enumeration names = session.getAttributeNames();
                    while (names.hasMoreElements()) {
                        String name = (String) names.nextElement();
                        Object value = session.getAttribute(name);                                        
                        institute_id=Integer.parseInt(session.getAttribute("Institute_Id").toString());
                        event_id=Integer.parseInt(session.getAttribute("EventId").toString());
                      
                    }
        }*/
        event_id=Integer.parseInt(request.getParameter("eventId"));      
        String image_title=request.getParameter("title");
        Maifal m=new Maifal();
        m.getMaifalDetails(institute_id);
        String institute_name=m.getName();
        int image_count=0;
        EventInfo einfo=new EventInfo();
        image_count=einfo.getEventImageCount(event_id);
        String fname="D:/Data/TYMCA/SEM VI/Beatheaven-Final Year Project/BeatHeaven/web/img1/event_images/"+institute_name+"/";       
        String msg="";    
        if(image_count>0){
            int i=einfo.updateEventGallery(event_id, institute_id,image_count, image_title, fname);
            if (i == 1) {
                  out.println("<br><br><center>Image Details added successfully:" + i + "</center>");
                  msg="Image Details added successfully..";
                  session.setAttribute("Message",msg);  
                  session.setAttribute("EventId",event_id);
                  session.setAttribute("ImageCount", image_count);
                  response.sendRedirect("EventImageFileUploader.jsp");
              } else {
                  out.println("<br><br><center>Error while updating result details:" + i + "</center>");
                  out.println("<br><br><center><a href='Institute_Profile.jsp'>Go Back</a></center>");
                  msg="Error while updating result details:"+i;
                  session.setAttribute("Message",msg);  
                  // response.sendRedirect("Institute_Profile.jsp");
              }
        }else if(image_count==0){
         int i=einfo.updateEventGallery(event_id, institute_id,image_count, image_title, fname);
            if (i == 1) {
                 out.println("<br><br><center>Image Details added successfully:" + i + "</center>");
                  msg="Image Details added successfully..";
                  session.setAttribute("Message",msg);  
                  session.setAttribute("EventId",event_id);
                  session.setAttribute("ImageCount", image_count);
                  response.sendRedirect("EventImageFileUploader.jsp");
              } else {
                  out.println("<br><br><center>Error while updating result details:" + i + "</center>");
                  out.println("<br><br><center><a href='Institute_Profile.jsp'>Go Back</a></center>");
                  msg="Error while updating result details:"+i;
                  session.setAttribute("Message",msg);  
                   // response.sendRedirect("Institute_Profile.jsp");
              }
        }else{
            msg="Error in page AddEventImagesServlet..";
            session.setAttribute("Message",msg);  
            // response.sendRedirect("Institute_Profile.jsp");
            
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
