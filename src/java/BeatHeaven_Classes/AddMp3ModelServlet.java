package BeatHeaven_Classes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddMp3ModelServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(true);  
        response.setContentType("text/html");  
        int institute_id=1,i;
        String msg="";
        try {           
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
            String title=request.getParameter("title");
            String songby=request.getParameter("songby");
            String singer=request.getParameter("singer");
            String album=request.getParameter("album");
            String songtype=request.getParameter("songtype");
            EventInfo eventinfo=new EventInfo();
            Maifal m2=new Maifal();
            m2.getMaifalDetails(institute_id);
            String institute_name=m2.getName();
            RandomNoGenerator rng=new RandomNoGenerator();
            long random_code=rng.getRandomCode(); 
            String file_location="d:/new/songs/"+institute_name+"/"+random_code;  
            i=eventinfo.addSongUploadDetails(title, songby, singer, album, songtype, institute_id, "Institute", file_location);
            if (i == 1) {
                  out.println("<br><br><center>Songs Details added successfully:" + i + "</center>");
                  msg="Songs Details added successfully..";
                  session.setAttribute("Message",msg);      
                  session.setAttribute("Mp3Location", file_location);
                  response.sendRedirect("Mp3FileUploader.jsp");
              } else {
                  out.println("<br><br><center>Error while addings song details:" + i + "</center>");
                  out.println("<br><br><center><a href='Institute_Profile.jsp'>Go Back</a></center>");
                  msg="Error while addings song details:"+i;
                  session.setAttribute("Message",msg);  
                  //response.sendRedirect("Institute_Profile.jsp");
              }
        } catch(Exception ex) {
            ex.printStackTrace();
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
