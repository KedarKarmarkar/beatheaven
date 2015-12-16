package BeatHeaven_Classes;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UploadBlogImage extends HttpServlet {
    int maxpostsize=5008490;
    int institute_id=0,kalakar_id=0;
    String sql = "",submit_param="",postTitle="",postSubject="",references="",postBody="",visibility="",logintypeforpost="",fname="",path="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession(true);            
        PrintWriter out = response.getWriter();
        try {
            
            Connection conn;
            Statement stmt;
            
            PreparedStatement pstmt;
            ResultSet rs; 
            
             session = request.getSession(true);
                if (session == null || session.isNew()) {
                    out.print("You are not logged in");
                } else {
                    Enumeration names = session.getAttributeNames();
                    while (names.hasMoreElements()) {
                        String name = (String) names.nextElement();
                        Object value = session.getAttribute(name);                 
                        
                        postTitle=session.getAttribute("PostTitle").toString();
                        postSubject=session.getAttribute("PostSubject").toString();
                        references=session.getAttribute("References").toString();
                        postBody=session.getAttribute("PostBody").toString();
                        visibility=session.getAttribute("Visibility").toString();
                        logintypeforpost=session.getAttribute("LoginTypeForPost").toString();
                        if(logintypeforpost.equals("Institute")){
                            institute_id = Integer.parseInt((session.getAttribute("PostUnderInstitute").toString()));
                            kalakar_id=0;
                        }else if(logintypeforpost.equals("Kalakar")){
                            kalakar_id=Integer.parseInt((session.getAttribute("Kalakar_Id").toString()));
                            institute_id = Integer.parseInt((session.getAttribute("PostUnderInstitute").toString()));
                        }
                    }  
                    //institute_id = Integer.parseInt((session.getAttribute("Institute_Id").toString()));
                }
                Maifal maifal=new Maifal();
                maifal.getMaifalDetails(institute_id);
                String institute_name=maifal.getName();
                path="D:/Data/TYMCA/SEM VI/Beatheaven-Final Year Project/BeatHeaven/web/img1/InstituteBlogImages/"+institute_name;
                String createdir="D:\\Data\\TYMCA\\SEM VI\\Beatheaven-Final Year Project\\BeatHeaven\\web\\img1\\InstituteBlogImages\\"+institute_name;
                GetCurrentDateTime gcdt=new GetCurrentDateTime();
                String cur_date=gcdt.getCurDate();
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
                            
                            fname=path + "/" + institute_id +"_"+kalakar_id+"_"+postTitle+"."+extension;                            
                            File newFile = new File(fname);
                            return newFile;
                        }
                    }
            );
                Class.forName("com.mysql.jdbc.Driver");            
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");
                sql = "INSERT INTO instituteblog(Title,Subject,Body,Privacy,ImageLocation,Reference,UploadBy,InstituteId,UploadDate,Status) VALUES(?,?,?,?,?,?,?,?,?,?)";
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1, postTitle);
                pstmt.setString(2, postSubject);
                pstmt.setString(3, postBody);
                pstmt.setString(4, visibility);
                pstmt.setString(5,fname);
                pstmt.setString(6, references);
                pstmt.setInt(7,kalakar_id);
                pstmt.setInt(8,institute_id);
                pstmt.setDate(9,Date.valueOf(cur_date));
                pstmt.setInt(10, 1);
                int  j = pstmt.executeUpdate();
                session.setAttribute("Message", "Blog added successfully..");
                response.sendRedirect("Institute_Profile.jsp");
                
        } catch(Exception ex) {
           session.setAttribute("Message", "Error while adding blog");
           ex.printStackTrace();
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
