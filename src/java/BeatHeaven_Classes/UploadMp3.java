/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BeatHeaven_Classes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UploadMp3 extends HttpServlet {
String file_location="";
  int institute_id=1;
  String msg="",path="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(true);    
        
        int maxpostsize=25008490;
        
        try {
           /*if (session == null || session.isNew()) { 
                    
                } else {
                    Enumeration names = session.getAttributeNames();
                    while (names.hasMoreElements()) {
                        String name = (String) names.nextElement();
                        Object value = session.getAttribute(name);                                        
                        institute_id=Integer.parseInt(session.getAttribute("Institute_Id").toString());
                        event_id=Integer.parseInt(session.getAttribute("EventId").toString());
                        file_location=session.getAttribute(Mp3Location).toString();   
                    }
        }*/
            Maifal m3=new Maifal();
            m3.getMaifalDetails(institute_id);
            String institute_name=m3.getName();
            path="d:/new/songs/"+institute_name+"/";
           
            String createdir="d:\\new\\songs\\"+institute_name;
            File newdir2=new File(createdir); 
            boolean dirCreated= newdir2.mkdir();    
            
             MultipartRequest m = new MultipartRequest(
                    request, 
                    path,
                    maxpostsize,
                    new FileRenamePolicy() {
                 private Exception SQLException;
                        @Override
                        public File rename(File file) {
                            EventInfo ei=new EventInfo();
                            String extension="";
                            String file_name=file.getName();
                            int i=file_name.lastIndexOf(".");
                            if(i>=0){
                                extension=file_name.substring(i+1);
                            }
                            String fname=path + "/" +file_name; 
                            i=ei.updateSongDetails(file_location, fname,institute_id);
                            if(i!=1) try {
                                throw SQLException;
                            } catch (Exception ex) {
                                Logger.getLogger(UploadMp3.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            File newFile = new File(fname);
                            return newFile;
                        }
                    }
            );
            out.print("<br><center><h5>File successfully uploaded..<a href='Institute_Profile.jsp'>Click to Go Back</a></h5></center>");           
            msg="Mp3 File  uploaded successfully";
            session.setAttribute("Message",msg);               
           // response.sendRedirect("Institute_Profile.jsp");
        } catch(Exception ex){
            ex.printStackTrace();
            out.print("Sorry..Error while uploading file, <a href='Institute_Profile.jsp'>Click Go Back</a> to try again");
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
