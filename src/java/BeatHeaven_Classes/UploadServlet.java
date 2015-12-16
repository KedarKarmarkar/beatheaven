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

public class UploadServlet extends HttpServlet {
    String msg="";
    int maxpostsize=5008490;
    String path="d:/new/exam_results";
    //String path="D:/Data/TYMCA/SEM VI/Beatheaven-Final Year Project/BeatHeaven/web/img1";
    int examID=1;
    String exam_name="";
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String institute_id="0";    
        int conducted_by;
        
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
                        examID=Integer.parseInt(session.getAttribute("ExamId").toString());
                    }
                    //Kalakar k=new Kalakar();
                   // kalakar_name=k.getKalakarName(institute_id);
              } */
            institute_id=request.getParameter("institute_id");
            msg=request.getParameter("Message"); 
            Exam exam=new Exam();
            exam.getExamInfo(examID);
            exam_name=exam.getExam_name();
            conducted_by=exam.getConductedby();
            Maifal maifal=new Maifal();
            maifal.getMaifalDetails(conducted_by);
            String institute_name=maifal.getName();
            path="d:/new/exam_results/"+institute_name+"/";
            String createdir="d:\\new\\exam_results\\"+institute_name;
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
                            String extension="";
                            String file_name=file.getName();
                            int i=file_name.lastIndexOf(".");
                            if(i>=0){
                                extension=file_name.substring(i+1);
                            }
                            Result r=new Result();
                            String fname=path + "/" + examID +"_"+exam_name+"_Result."+extension;
                            i=r.updateResultFileName(fname, examID);
                            if(i!=1) try {
                                throw SQLException;
                            } catch (Exception ex) {
                                Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            File newFile = new File(fname);
                            return newFile;
                        }
                    }
            );
            out.print("<br><center><h5>File successfully uploaded</h5></center>");
            out.print("<br><center><h5><a href='Institute_Profile.jsp'>Back</a></h5></center>");
            msg="File uploaded successfully";
            session.setAttribute("Message",msg);   
            response.sendRedirect("Institute_Profile.jsp");
         }catch(Exception e){
             out.println("Error while uploading file:"+e.getMessage());
             msg="Error while uploading file:"+e.getMessage();
            session.setAttribute("Message",msg);   
         }
          
    }

    
}
