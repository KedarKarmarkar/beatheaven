package BeatHeaven_Classes;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UploadEventImage extends HttpServlet {
    String msg="",path="";
    int maxpostsize=5008490;
    int event_id=1,count=0,image_count=2;
    int institute_id=1;
    boolean dirCreated=false;
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
        HttpSession session=request.getSession(true);              
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       
         try{          
           /*  session = request.getSession();
                if (session == null || session.isNew()) { 
                    
                } else {
                    Enumeration names = session.getAttributeNames();
                    while (names.hasMoreElements()) {
                        String name = (String) names.nextElement();
                        Object value = session.getAttribute(name);
                        institute_id = session.getAttribute("Institute_Id").toString();                        
                        msg=session.getAttribute("Message").toString();
                        event_id=Integer.parseInt(session.getAttribute("EventId").toString());
                        image_count=Integer.parseInt(session.getAttribute("ImageCount").toString());  
                    }
                   
              } */           
            msg=request.getParameter("Message"); 
            Exam exam=new Exam();           
            Maifal maifal=new Maifal();  
            
            maifal.getMaifalDetails(institute_id);
            String institute_name=maifal.getName();
            path="D:/Data/TYMCA/SEM VI/Beatheaven-Final Year Project/BeatHeaven/web/img1/event_images/"+institute_name+"/";  
            String createdir="D:\\Data\\TYMCA\\SEM VI\\Beatheaven-Final Year Project\\BeatHeaven\\web\\img1\\event_images\\"+institute_name;
            File newdir2=new File(createdir); 
            dirCreated= newdir2.mkdir();            
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
                            String fname=path + "/" + event_id +"_"+(image_count+1)+"."+extension;
                            i=ei.updateEventImageCount(event_id, image_count);
                            if(i!=1) try {
                                throw SQLException;
                            } catch (Exception ex) {
                                Logger.getLogger(UploadEventImage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            File newFile = new File(fname);
                            return newFile;
                        }
                    }
            );
            out.print("<br><center><h5>File successfully uploaded..<a href='Institute_Profile.jsp'>Click to Go Back</a></h5></center>");
            out.print("<br><center><h5><a href='EventImageFileUploader.jsp'>Click to add more files</a></h5></center>");
            msg="File uploaded successfully";
            session.setAttribute("Message",msg);   
            session.setAttribute("EventId",event_id);
            session.setAttribute("ImageCount", image_count);
           // response.sendRedirect("Institute_Profile.jsp");
         }catch(Exception e){
             out.println("Error while uploading file:"+dirCreated+"==>"+e.getMessage());
             msg="Error while uploading file:"+e.getMessage();
            session.setAttribute("Message",msg);   
            //response.sendRedirect("Institute_Profile.jsp");
         }
          
    }

    
}
