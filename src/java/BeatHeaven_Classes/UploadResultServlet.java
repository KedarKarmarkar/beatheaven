/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BeatHeaven_Classes;

import com.oreilly.servlet.MultipartRequest;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GlobeOp
 */
public class UploadResultServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fname="";
        String exam_name="";
        int conducted_by;
        Exam exam=new Exam();
        exam.getExamInfo(Integer.parseInt(request.getParameter("exam_id")));
        exam_name=exam.getExam_name();
        conducted_by=exam.getConductedby();
        Maifal maifal=new Maifal();
        maifal.getMaifalDetails(conducted_by);
        String institute_name=maifal.getName();
        fname="d:/new/exam_results/"+institute_name+"/";
        fname+=Integer.parseInt(request.getParameter("exam_id"))+"_"+exam_name+"_Result.pdf";
        String msg="";
        HttpSession session=request.getSession(true);  
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Result r = new Result();    
        //out.println("Exam Id:"+request.getParameter("exam_id"));
        //out.println("Kalakar_id:"+request.getParameter("kalakar_id"));
        //out.println("Result_Desc:"+request.getParameter("result_desc"));  
        int j=r.getResultAvailability(Integer.parseInt(request.getParameter("exam_id")));
        if(j==1){
            int i=r.updateResultDetails(Integer.parseInt(request.getParameter("exam_id")), Integer.parseInt(request.getParameter("institute_id")), request.getParameter("result_desc"), fname);
            if (i == 1) {
                  out.println("<br><br><center>Result Details added successfully:" + i + "</center>");
                  msg="Result updated successfully..";
                  session.setAttribute("Message",msg);  
                  session.setAttribute("ExamId",request.getParameter("exam_id"));
                  response.sendRedirect("FileUploader.jsp");
              } else {
                  out.println("<br><br><center>Error while updating result details:" + i + "</center>");
                  out.println("<br><br><center><a href='Institute_Profile.jsp'>Go Back</a></center>");
                  msg="Error while updating result details:"+i;
                  session.setAttribute("Message",msg);  
                  // response.sendRedirect("Institute_Profile.jsp");
              }
        }else if(j==0){
            int i = r.addResultDetails(Integer.parseInt(request.getParameter("exam_id")), Integer.parseInt(request.getParameter("institute_id")), request.getParameter("result_desc"), fname);
            if (i == 1) {
                  out.println("<br><br><center>Result Details added successfully:" + i + "</center>");
                  msg="Result Details added successfully..choose a file with name:"+request.getParameter("exam_id")+"_Result.pdf";
                  session.setAttribute("Message",msg);  
                  session.setAttribute("ExamId",request.getParameter("exam_id"));
                  response.sendRedirect("FileUploader.jsp");
              } else {
                  out.println("<br><br><center>Error while adding details:" + i + "</center>");
                   out.println("<br><br><center><a href='Institute_Profile.jsp'>Go Back</a></center>");
                    msg="Error while uploading file:";
                   session.setAttribute("Message",msg);  
                  // response.sendRedirect("Institute_Profile.jsp");
              }
        }else{
            msg="Error while checking result availability:"+j;
            session.setAttribute("Message",msg);  
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
