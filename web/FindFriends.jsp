<%@page import="BeatHeaven_Classes.Kalakar"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@page import="java.sql.ResultSet"%>
    <%@page import="java.sql.Statement"%>
    <%@page import="java.sql.DriverManager"%>
    <%@page import="java.sql.Connection"%>
    <%@page import="java.util.Enumeration"%>
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
                    <form id="form1" name="form1" method="post" action="FindFriends.jsp">
                        <input name="search_friend" type="text" size="15" id="search_friend" />
                        <input class="button" type="submit" name="Submit" value="Search Friend" />
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
                    <li><a href="UploadResult.jsp">Upload Results</a></li>
                    <li><a href="AddCourse.jsp">Add Course</a></li>
                    <li><a href="AddExam.jsp">Add Exam</a></li>
                </div>
            </div>
            <div id="templatemo_right_column"><form><table>
                <%! HttpSession session; %>
<%  String kalakar_id="3";
    int count=0;
    int status=0;
    Kalakar k= new Kalakar();
     ArrayList<Integer> KalakarId_List = new ArrayList<Integer>();
    try {
                session = request.getSession();
                if (session == null || session.isNew()) { %>
        <h5> You are not logged in..</h5>
<%   } else {
                    Enumeration names = session.getAttributeNames();
                    while (names.hasMoreElements()) {
                        kalakar_id=session.getAttribute("Kalakar_Id").toString();
                    }
                }
    }catch(Exception e){%>
      <h5> Error while session loading..<%=e.getMessage()%></h5>          
   <%        
    }
    String search_friend=request.getParameter("search_friend");
    try{    int id=0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");   
            Statement stmt = conn.createStatement();    
            String sql="select Id,Name from kalakar where Name='"+search_friend+"'";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()) {
               KalakarId_List.add(rs.getInt("Id"));
                %>
               
           <% }
            conn.close();
     }catch(Exception e){%>
      <h5> Database connection problem..<%=e.getMessage()%></h5>          
   <%        
    }
    if(KalakarId_List.size()!=0) {
    for(int i=0;i<KalakarId_List.size();i++) {
        count=0;
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");   
        Statement stmt = conn.createStatement(); 
        String sql="select Status from friendlist where Kalakar_Id="+Integer.parseInt(kalakar_id)+" and Friend_Id="+KalakarId_List.get(i)+" OR (Kalakar_Id="+KalakarId_List.get(i)+" and Friend_Id="+Integer.parseInt(kalakar_id)+")";
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next()){
            count++;
            status=rs.getInt("Status");
        }
        conn.close();
        if(count>0){
            if(status==2) {%>
            <tr><td><a href="ViewKalakar.jsp?Id=<%=KalakarId_List.get(i)%>"><%= k.getKalakarName(KalakarId_List.get(i))%></td><td>Request Already Send</td></tr>
            <%
        }else if(status==1) {%>
            <tr><td><a href="ViewKalakar.jsp?Id=<%=KalakarId_List.get(i)%>" title="View Profile"><%=k.getKalakarName(KalakarId_List.get(i))%></a></td><td>&nbsp;&nbsp;&nbsp;You are already friend</td></tr>
        <%}
      }else{%>
            <tr><td><a href="ViewKalakar.jsp?Id=<%=KalakarId_List.get(i)%>"><%= k.getKalakarName(KalakarId_List.get(i))%></td><td><a href="SendFriendReq.jsp?Id=<%=KalakarId_List.get(i)%>">Add Friend</a></td></tr>
       <% }       
    }  
    }else{%>
            <tr><td colspan="2">No friend found</td></tr>
    <%}
    
%>
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