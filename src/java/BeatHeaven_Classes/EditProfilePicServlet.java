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
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GlobeOp
 */
public class EditProfilePicServlet extends HttpServlet {
    private Exception SQLException;
    String msg="",login_type="Institute",path="",dir="";
    int i,id=1,maxpostsize=108490,count=0;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(true);     
       try {
                session = request.getSession();
                if (session == null || session.isNew()) { 
                } else {
                    Enumeration names = session.getAttributeNames();
                    while (names.hasMoreElements()) {
                        String name = (String) names.nextElement();
                        Object value = session.getAttribute(name);
                        login_type=session.getAttribute("LoginType").toString();
                        if(login_type.equalsIgnoreCase("Institute"))
                         id = Integer.parseInt((session.getAttribute("Institute_Id").toString()));
                        else if(login_type.equalsIgnoreCase("Kalakar"))
                         id=Integer.parseInt((session.getAttribute("Kalakar_Id").toString()));
                        msg=session.getAttribute("Message").toString();
                    }                  
                } 
                
                dir=id+"_"+login_type;
                path="D:/Data/TYMCA/SEM VI/Beatheaven-Final Year Project/BeatHeaven/web/img1/Profile_Pic/";
                String createdir="D:\\Data\\TYMCA\\SEM VI\\Beatheaven-Final Year Project\\BeatHeaven\\web\\img1\\Profile_Pic";
                File newdir=new File(createdir);
                boolean dirCreated=newdir.mkdir();
                MultipartRequest m = new MultipartRequest(
                    request, 
                    path,
                    maxpostsize,
                    new FileRenamePolicy() {
                 private Exception SQLException;
                        @Override
                        public File rename(File file) {
                            ProfilePicDetails ppd=new ProfilePicDetails();
                            count=ppd.getProfilePicDetails(id, login_type);
                            String extension="";
                            String file_name=file.getName();
                            int i=file_name.lastIndexOf(".");
                            if(i>=0){
                                extension=file_name.substring(i+1);
                            }                            
                            String fname=path +dir+ "."+extension;
                            if(count>0)
                                i=ppd.updateProfilePicDetails(id, login_type,fname);
                            else
                                i=ppd.addProfilePicDetails(id, login_type, fname);
                            if(i!=1) try {
                                throw SQLException;
                            } catch (Exception ex) {
                                Logger.getLogger(EditProfilePicServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            File newFile = new File(fname);
                            return newFile;
                        }
                    }
            );session.setAttribute("Message", "Profile pic updated successfully");
            if(login_type.equalsIgnoreCase("Institute"))
                response.sendRedirect("Institute_Profile.jsp");
            else if(login_type.equalsIgnoreCase("Kalakar"))
                response.sendRedirect("Kalakar_Profile.jsp");
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
