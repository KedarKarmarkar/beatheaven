package BeatHeaven_Classes;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
     
        getFileName(request, response);
    }
    public int downloadFile(HttpServletRequest request,HttpServletResponse response,String filepath,String filename){
        int  k=-1;
        try{
            response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "");

        java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath + filename);

        int i;
        while ((i = fileInputStream.read()) != -1) {
            out.write(i);
        }
        fileInputStream.close();
        out.close();
        k=1;
        return k;
        }catch(Exception e){
            e.printStackTrace();
            return k;
        }
    }
    
    public void getFileName(HttpServletRequest request,HttpServletResponse response) {
      try{  
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String fileName="";
        String cfile="/BeatHeaven/filedownloader.jsp";
        String locationFolder="D:\\new\\";
        int s=0;
        File folder = new File("D:\\new\\");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                
                fileName=listOfFiles[i].getName();
                out.println("Time:"+(i+1));
                out.println("i:"+(i+1)+" FileName:"+fileName);
               out.println("<a href='/BeatHeaven/Filedownloader.jsp?folder="+folder+"&filename="+fileName+"'>"+fileName+"</a>");
                //out.println("<a href="+cfile+"?folder="+folder+"&filename="+fileName+">"+fileName+"</a>");
               out.println("<br>");
            } else if (listOfFiles[i].isDirectory()) {
                out.println("<h4>FolderName:"+listOfFiles[i].getName());
              //  System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        //return s;
      }catch(Exception e){
          e.printStackTrace();
      }
    }
      
}
