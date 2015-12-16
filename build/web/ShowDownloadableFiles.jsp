<%@page import="java.io.File"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
   
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Institute_Profile</title>
    <meta name="keywords" content="music note, free template, website template, CSS, XHTML" />
    <meta name="description" content="Music Note - free CSS template provided by templatemo.com" />    
    <link href="templatemo_style.css" rel="stylesheet" type="text/css" />
    <!-- Bootstrap core CSS -->
   <link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">        
     <link href="css/style.css" type="text/css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/ie-emulation-modes-warning.js"></script>
</head>

<body>
   
<div id="templatemo_container_wrapper">
    <div id="templatemo_container">
        <div id="templatemo_top">
         <a href="index.jsp">BeatHeaven Home</a> | <a href="Login.html"> Contact Us</a>
        </div>

        <div id="templatemo_header">
            <div id="templatemo_site_title">Institute Name</div>
            <div id="templatemo_site_slogan">Subtitle(if any)</div>
        </div>

        <div id="templatemo_menuleft"></div>
        <div id="templatemo_menu">
            <ul>
                <li><a href="Institute_Profile.jsp" class="current">Home</a></li>
                <li><a href="#">About Us</a></li>
                <li><a href="#">Activities</a></li>
                <li><a href="#">Events</a></li>
                <li><a href="#">Gallery</a></li>
                <li><a href="#">Kalakars</a></li>                
            </ul>
        </div>
        <div id="templatemo_content">
            <div id="templatemo_left_column">
                <div class="templatemo_section2">
                    <h4>Search</h4>
                    <form id="form1" name="form1" method="post" action="#">
                        <input name="search_text" type="text" size="15" id="search_text" />
                        <input class="button" type="submit" name="Submit" value="Search" />
                    </form>
                </div>
                <div class="templatemo_section2">
                    <h4>Notifications:</h4>
                    <hr>
                    <h4>Extras:</h4>
                    <li><a href="postInfo.jsp">Post Info</a></li>
                    <li><a href="chat.jsp">Available Chat</a></li>
                    <li><a href="ShowDownloadableFiles.jsp">Download Music</a></li>
                </div>
            </div>
            <div id="templatemo_right_column">
                <p> <h5><b><u>Available Files to download..(click to download)</u></b></h5></p>
                 <%           
                                                
                                                String fileName = "";
                                                String cfile = "/BeatHeaven/Filedownloader.jsp";
                                                String locationFolder = "D:\\new\\";
                                               // locationFolder="D:\\Data\\TYMCA\\SEM VI\\Beatheaven-Final Year Project\\BeatHeaven\\web\\img1";
                                                int s = 0;
                                                File folder = new File(locationFolder);
                                                File[] listOfFiles = folder.listFiles();
                                                for (int i = 0; i < listOfFiles.length; i++) {
                                                    if (listOfFiles[i].isFile()) {
                                                      fileName = listOfFiles[i].getName(); %>
                                                      <li><h5> <a href=" /BeatHeaven/test.jsp?filename=<%=fileName%>"> <%=fileName%> </a><br></h5></li>
                                             <%               }
                                                        }
                                                    
                                                %>
            </div>
        </div>
           <div id="templatemo_footer">
            <a href="#">Home</a> | <a href="#">About Us</a> | <a href="#">FeedBack</a> | <a href="#">Member</a> | <a href="#">Contact Us</a><br />
        </div>
    </div>
</div>

</body>
</html>