<%@page import="java.util.ArrayList"%>
<%@page import="BeatHeaven_Classes.Exam"%>
<%@page import="java.util.Enumeration"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Upload Result</title>
    <meta name="keywords" content="music note, free template, website template, CSS, XHTML" />
    <meta name="description" content="Music Note - free CSS template provided by templatemo.com" />    
    <link href="templatemo_style.css" rel="stylesheet" type="text/css" />
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">        
    <link href="css/style.css" type="text/css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">  
    <script src="js/ie-emulation-modes-warning.js"></script>
</head>
<body>
<%! HttpSession session; %>
        <%
            int institute_id=1;
            int kalakar_id=3;
            ArrayList<Integer> examid_list = new ArrayList<Integer>();
            try {
                session = request.getSession();
                if (session == null || session.isNew()) { %>
        <h5> You are not logged in..</h5>
        <%   } else {
                    Enumeration names = session.getAttributeNames();
                    while (names.hasMoreElements()) {
                        String name = (String) names.nextElement();
                        Object value = session.getAttribute(name);
                        institute_id = Integer.parseInt((session.getAttribute("Institute_Id").toString()));
                        kalakar_id=Integer.parseInt((session.getAttribute("Kalakar_Id").toString()));
                    }                    
                    Exam e=new Exam();
                    examid_list=e.getExamId(institute_id);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>     
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
                    <li><a href="FileUploader.html">Upload Music</a></li>
                </div>
            </div>
            <div id="templatemo_right_column">
                   <form  action="UploadResultServlet" method="post">
                       <input type="hidden" name="kalakar_id" value="<%=kalakar_id%>"/><br/>
                       <table id="table1">
                           <tr><td>Select ExamId:</td>
                               <td><select name="exam_id"><option value="1">1</option><% for(int i=0;i<examid_list.size();i++){ %>
                                       <option value="<%=examid_list.get(i)%>"><%=examid_list.get(i)%></option><%}%>
                                   </select></td></tr>
                                   <tr><td>Comments:</td><td><textarea name="result_desc" cols="5" rows="5"></textarea></td></tr>
                                   <tr><td colspan="2"><input type="submit" value="Click to Attached File" name="AttachFile"></td></tr> 
                                   <tr><td colspan="2"><b>(Note:File Name should be in the format:(ExamId_Result.pdf))</b></td></tr> 
                       </table>                                   
                </form>
            </div>
        </div>
           <div id="templatemo_footer">
            <a href="#">Home</a> | <a href="#">About Us</a> | <a href="#">FeedBack</a> | <a href="#">Member</a> | <a href="#">Contact Us</a><br />
        </div>
    </div>
</div>
</body>
</html>