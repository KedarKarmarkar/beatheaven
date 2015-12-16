/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BeatHeaven_Classes;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageFile {
    
    public int downloadFile(String fname,String locationFolder) throws Exception {
   String location_Folder="D:\\new\\";    
    String url=location_Folder+fname;
   // String url = "http://www.uni-koblenz-landau.de/images/starts-c-ko.jpg";
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    // optional default is GET
    con.setRequestMethod("GET");
    //add request header
    con.setRequestProperty("User-Agent", "Mozilla/5.0");
    int responseCode = con.getResponseCode();
    System.out.println("\nSending 'GET' request to URL : " + url);
    System.out.println("Response Code : " + responseCode);
    InputStream in = con.getInputStream();
    OutputStream out = new FileOutputStream("D:\\"+fname);
    try {
      byte[] bytes = new byte[233475];
      int length;

      while ((length = in.read(bytes)) != -1) {
        out.write(bytes, 0, length);
      }
      return 1;
    }catch(Exception e){
        System.out.println("Error while file download: "+ e.getMessage());
        return 0;
    }finally {
      in.close();
      out.close();
    }
  }
}
