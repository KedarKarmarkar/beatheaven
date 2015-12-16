<%@page import="BeatHeaven_Classes.ProfilePicDetails"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="BeatHeaven_Classes.Maifal"%>
<%@page import="BeatHeaven_Classes.Feedback"%>
<%@page import="BeatHeaven_Classes.GetCurrentDateTime"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="BeatHeaven_Classes.EventInfo"%>
<%@page import="BeatHeaven_Classes.BirthDayCalculator"%>
<%@page import="BeatHeaven_Classes.OnlineUsers"%>
<%@page import="BeatHeaven_Classes.Exam"%>
<%@page import="BeatHeaven_Classes.Kalakar"%>
<%@page import="BeatHeaven_Classes.Course"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.DriverManager"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Manage Kalakar Accessibility </title>
    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/simple-sidebar.css" rel="stylesheet">
    <link href="css/chatstyle.css" rel="stylesheet">
    <link href="css/bootstrap-datetimepicker.css" rel="stylesheet">
    <style>
@import url(http://fonts.googleapis.com/css?family=Lato:400,700);
                    body {
            padding-bottom: 40px;
            padding-top: 45px;
            font-family: 'Lato', 'sans-serif';
        }
        .sidebar-nav-fixed {
                width: inherit;      
        }
        .figure{
                   
        }
    </style>
</head>
<body>
<%! HttpSession session; %>
<% // Set refresh, autoload time as 5 seconds
   response.setIntHeader("Refresh", 180);
   // Get current time
   Calendar calendar = new GregorianCalendar();
   String am_pm;
   int hour = calendar.get(Calendar.HOUR);
   int minute = calendar.get(Calendar.MINUTE);
   int second = calendar.get(Calendar.SECOND);
   if(calendar.get(Calendar.AM_PM) == 0)
      am_pm = "AM";
   else
      am_pm = "PM";
   String CT = hour+":"+ minute+" "+ am_pm;
   Connection conn;
   Statement stmt;
   String sql="";
   ResultSet rs;    
    int siteAdminId=0;    
    String msg="welcome",kalakar_name="SiteAdmin"; 
    ArrayList<String> coursename_list = new ArrayList<String>();   
    try {
                session = request.getSession(true);
                if (session == null || session.isNew()) { %>
        <h5> You are not logged in..</h5>
        <%   } else {
                    Enumeration names = session.getAttributeNames();
                    while (names.hasMoreElements()) {
                        String name = (String) names.nextElement();
                        Object value = session.getAttribute(name);
                        siteAdminId = Integer.parseInt((session.getAttribute("SiteAdmin_Id").toString()));                        
                        msg=session.getAttribute("Message").toString();
                    } 
                    
        }
    }catch(Exception e){%>
      <h5> Error while session loading..<%=e.getMessage()%></h5>          
   <% }    
    
     String  profilepic_path="img1/profile_pic.png";   
%>
<script>
     $("button1").click(function(){
    var div = $("div1");
    div.animate({left: '100px'}, "slow");
    div.animate({fontSize: '3em'}, "slow");
}); 

</script>    
<div class="row">
	<nav class="navbar navbar-default navbar-fixed-top" style="background-color:#DFDACB" >
      <div class="container">
        <div class="navbar-header" id="div1">
          <button type="button" id="button1" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="BH_Profile.jsp" ><b>BEAT</b><span style="font-weight:bold;color:#c32929;">HEAVEN</span> </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">              
            <li><a href="#postModal1" role="button" data-toggle="modal"><i class="glyphicon glyphicon-plus"></i> Post</a></li>
            <li><a href="" data-toggle="modal" data-target="#ViewNotificationModel1"><span class="badge">Notifications<span class="badge badge-info"></span></span></a></li>
            <li><a href="#menu-toggle" class="btn btn-default btn-sm" id="menu-toggle">Toggle Menu</a></li>
            <li><a href="#"><span class="badge"><%=msg%></span></a></li>
            
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><p class="navbar-text">Logged in as <a href="EditInstituteProfile.jsp">BeatHeaven</a></p></li>
         <!--   <li id="topbar-chat" class="hidden-xs"><a href="javascript:void(0)" data-step="4" data-intro="&lt;b&gt;Form chat&lt;/b&gt; keep you connecting with other coworker" data-position="left" class="btn-chat">Chat<i class="fa fa-comments"></i><span class="badge badge-info"><% //=olfriend_list.size() %></span></a></li> -->
            <li><a href="LogOutServlet"><i class="glyphicon glyphicon-off"></i>Logout</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    <div class="" id="wrapper">
        <!-- Sidebar -->
        <div id="posttoModal1" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        Update Status
                    </div>
                    <div class="modal-body">
                        <form class="form center-block">
                            <div class="form-group">
                                <textarea class="form-control input-lg" autofocus="" placeholder="What do you want to share?"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div>
                            <button class="btn btn-primary btn-sm" data-dismiss="modal" aria-hidden="true">Post</button>
                            <ul class="pull-left list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li><li><a href=""><i class="glyphicon glyphicon-camera"></i></a></li><li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul>
                        </div>  
                    </div>
                </div>
            </div>
        </div>
        <div id="postModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                Update Status
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal" action="AddInstituteBlogServlet" method="post">
                                    <div class="form-group">
                                        <label for="txtPostTitle" class="col-sm-3 control-label">Title</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="txtPostTitle" id="txtPostTitle" placeholder="Post Title" required autofocus>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="txtSubject" class="col-sm-3 control-label">Subject</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="txtSubject" id="txtSubject" placeholder="Post Subject" required autofocus>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="txtRefrences" class="col-sm-3 control-label">References</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="txtRefrences" id="txtRefrences" placeholder="For eg. friends,family,newspaper,TV channel" required autofocus>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="txtPrivacy" class="col-sm-3 control-label">visibility Level</label>
                                        <div class="col-sm-9">
                                            <div class="btn-group" role="group" >
                                                <div class="btn-group" data-toggle="buttons">
                                                        <label class="btn btn-primary active">
                                                            <input type="radio" name="txtPrivacy" id="option1" value="Friends"  checked>Friends 
                                                        </label>
                                                        <label class="btn btn-primary">
                                                          <input type="radio" name="txtPrivacy" id="option2" value="Public"  >Public 
                                                        </label>
                                                        <label class="btn btn-primary">
                                                          <input type="radio" name="txtPrivacy" id="option3"  value="Institute" >Institute 
                                                        </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="txtaPostBody" class="col-sm-3 control-label">What's in Your Mind ?</label>
                                        <div class="col-sm-9">
                                            <textarea class="form-control input-lg" name="txtaPostBody" id="txtaPostBody"  placeholder="What do you want to share?" required autofocus ></textarea>
                                        </div>
                                    </div>
                                    <hr class="style-two">
                                    <div>
                                    <button type="submit" class="btn btn-primary btn-sm" name="post" value="Post" >Post</button>
                                    <button type="submit" class="btn btn-primary btn-sm" name="post" value="AttachPic" >Add Image</button>
                                <!--    <ul class="pull-left list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li><li><a href=""><i class="glyphicon glyphicon-camera"></i></a></li><li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul> -->
                                </div> 
                                     <div class="form-group">
                                        
                                        <div class="col-sm-9">
                                            <input type="hidden" class="form-control input-lg" name="InstituteId" id="InstituteId" value="<%= siteAdminId%>">
                                            <input type="hidden" class="form-control input-lg" name="UploadBy" id="InstituteId" value="0">
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                
                            </div>
                        </div>
                    </div>
                </div>
        <div id="sidebar-wrapper" style="font-weight:bold;color:#c32929;" > 
            <ul class="sidebar-nav" >
                <li class="sidebar-brand">   </li>           
                <li >               
                    <figure>
                        <center>
                            <img src="<%=profilepic_path%>" alt="" class="img-circle img-responsive" style="width:150px;height:125px"> 
                        </center>
                    </figure>                    
                </li>               
                <li><hr></li>
                <li class="dropdown" style="font-weight:bold;color:#c32929;">
                    <a href="" class="dropdown-toggle" data-toggle="dropdown">Uploads<b class="caret"></b></a>                  
                </li>               
                <li class="dropdown">
                  <a href="" class="dropdown-toggle" data-toggle="dropdown">Manage Access & Users<b class="caret"></b></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="InstituteAccessibility.jsp" style="font-weight:bold;color:#c32929;">Institute </a></li>
                    <li class="divider"></li>
                    <li><a href="ManageKalakarAccessibility.jsp" style="font-weight:bold;color:#c32929;">Kalakar </a></li>                    
                  </ul>
                </li>
                <li  class="dropdown">
                    <a href="" class="dropdown-toggle" data-toggle="dropdown">FeedBack & Queries<b class="caret"></b></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="" data-toggle="modal" data-target="#AddFeedBackModel" style="font-weight:bold;color:#c32929;">Add FeedBack & Queries</a></li>
                       <li class="divider"></li>                    
                        <li><a href="" data-toggle="modal" data-target="#ViewFeedBackModel" style="font-weight:bold;color:#c32929;">View FeedBack</a></li>                       
                    </ul>
                </li>
            </ul>
        </div>
       
        <!--BEGIN CHAT FORM-->
            <div id="chat-form" class="fixed">
                <div class="chat-inner">
                    <h2 class="chat-header">
                        <a href="javascript:;" class="chat-form-close pull-right"><i class="glyphicon glyphicon-remove">
                        </i></a><i class="fa fa-user"></i>&nbsp; Chat &nbsp;<span class="badge badge-info">3</span></h2>
                    <div id="group-1" class="chat-group">
                        <strong>Favorites</strong><a href="#"><span class="user-status is-online"></span> <small>
                            Verna Morton</small> <span class="badge badge-info">2</span></a><a href="#"><span
                                class="user-status is-online"></span> <small>Delores Blake</small> <span class="badge badge-info is-hidden">
                                    0</span></a><a href="#"><span class="user-status is-busy"></span> <small>Nathaniel Morris</small>
                                        <span class="badge badge-info is-hidden">0</span></a><a href="#"><span class="user-status is-idle"></span>
                                            <small>Boyd Bridges</small> <span class="badge badge-info is-hidden">0</span></a><a
                                                href="#"><span class="user-status is-offline"></span> <small>Meredith Houston</small>
                                                <span class="badge badge-info is-hidden">0</span></a></div>
                    <div id="group-2" class="chat-group">
                        <strong>Office</strong><a href="#"><span class="user-status is-busy"></span> <small>
                            Ann Scott</small> <span class="badge badge-info is-hidden">0</span></a><a href="#"><span
                                class="user-status is-offline"></span> <small>Sherman Stokes</small> <span class="badge badge-info is-hidden">
                                    0</span></a><a href="#"><span class="user-status is-offline"></span> <small>Florence
                                        Pierce</small> <span class="badge badge-info">1</span></a></div>
                    <div id="group-3" class="chat-group">
                        <strong>Friends</strong><a href="#"><span class="user-status is-online"></span> <small>
                            Willard Mckenzie</small> <span class="badge badge-info is-hidden">0</span></a><a
                                href="#"><span class="user-status is-busy"></span> <small>Jenny Frazier</small>
                                <span class="badge badge-info is-hidden">0</span></a><a href="#"><span class="user-status is-offline"></span>
                                    <small>Chris Stewart</small> <span class="badge badge-info is-hidden">0</span></a><a
                                        href="#"><span class="user-status is-offline"></span> <small>Olivia Green</small>
                                        <span class="badge badge-info is-hidden">0</span></a></div>
                </div>
                <div id="chat-box" style="top: 400px">
                    <div class="chat-box-header">
                        <a href="#" class="chat-box-close pull-right"><i class="glyphicon glyphicon-remove">
                        </i></a><span class="user-status is-online"></span><span class="display-name">Willard
                            Mckenzie</span> <small>Online</small>
                    </div>
                    <div class="chat-content">
                        <ul class="chat-box-body">
                            <li>
                                <p>
                                    <img src="images/avatar/128.jpg" class="avt" /><span class="user">John Doe</span><span
                                        class="time">09:33</span></p>
                                <p>
                                    Hi Swlabs, we have some comments for you.</p>
                            </li>
                            <li class="odd">
                                <p>
                                    <img src="images/avatar/48.jpg" class="avt" /><span class="user">Swlabs</span><span
                                        class="time">09:33</span></p>
                                <p>
                                    Hi, we're listening you...</p>
                            </li>
                        </ul>
                    </div>
                    <div class="chat-textarea">
                        <input placeholder="Type your message" class="form-control" /></div>
                </div>
            </div>
        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
              <div class="row">
                     <div class="col-md-6 ">
                         <form action="ManageKalakarAccessibility.jsp" method="post">
                         <% try{ %>    
                                    <center>
                                        <b>Search By:</b>&nbsp;
                                        <select name="criteria">                                           
                                            <option value="city">City</option>
                                            <option value="name">Name</option>
                                            <option value="email">Email</option>
                                            <option value="profession">Profession</option>
                                            <option value="all">Select All</option>
                                        </select>&nbsp;
                                        
                                        <input type="submit" name="submit" value="Filter"/>
                                    </center>
                                      
                               <% }catch(Exception ex){} %>         
                                </form>
                      </div>
                      <div class="col-md-6 ">
                         <form action="ManageKalakarAccessibility.jsp" method="post">
                                    <center>
                                        <%
                                        try{
                                            if(request.getParameter("submit").equals("Filter")){
                                                if(request.getParameter("criteria").equals("city")){%>
                                                Enter City:&nbsp;<input type="text" name="search_value" required autofocus>&nbsp;<input type="submit" name="submit" value="Search">
                                                <input type="hidden" name="criteria" value="<%=request.getParameter("criteria")%>"/>
                                                <%} else if(request.getParameter("criteria").equals("name")){%>
                                                    Enter Name:&nbsp;<input type="text" name="search_value" required autofocus>&nbsp;<input type="submit" name="submit" value="Search">
                                                    <input type="hidden" name="criteria" value="<%=request.getParameter("criteria")%>"/>
                                                <%} else if(request.getParameter("criteria").equals("email")){%>
                                                    Enter Email:&nbsp;<input type="text" name="search_value" required autofocus>&nbsp;<input type="submit" name="submit" value="Search">                                               
                                                    <input type="hidden" name="criteria" value="<%=request.getParameter("criteria")%>"/>
                                                <%} else if(request.getParameter("criteria").equals("profession")){%>
                                                    Enter Profession:&nbsp<input type="text" name="search_value" required autofocus>&nbsp;<input type="submit" name="submit" value="Search">;
                                                    <input type="hidden" name="criteria" value="<%=request.getParameter("criteria")%>"/>
                                                <%}
                                            }
                                        }catch(Exception ex){}
                                       %>
                                    </center>    
                                </form>
                      </div>
                    <div class="col-md-12" >
                        <hr style="border-color: blueviolet">                        
                        <form>
                            <b>User Approvals:</b>
                            <table class="table table-bordered">
                                <thead>
                          <tr class="active">
                              <th>Name</th>
                              <th>Address</th>
                              <th>City</th>
                              <th>DOB</th>
                              <th>Email</th>
                              <th>Contact</th>
                              <th>Interest</th>
                              <th>Playing Instrument</th>
                              <th>Occupation</th>
                              <th>Gender</th>
                              <th></th>
                              <th></th>
                          </tr>
                      </thead>
                      <tbody>
                        <%                               
                               String name="",address="",gender="", city="",dob="",email="",contact="",interest="",playing_instruments="",occupation="";
                               try{                                    
                                  int  count=0,kalakar_id=0, status=0;
                                   GetCurrentDateTime gcdt=new GetCurrentDateTime();
                                   String cur_date=gcdt.getCurDate();
                                    Class.forName("com.mysql.jdbc.Driver");
                                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
                                    stmt = conn.createStatement();
                                    if(request.getParameter("submit").equals("Filter") && request.getParameter("criteria").equalsIgnoreCase("All")){
                                            sql="select * from kalakar";
                                    }else if(request.getParameter("submit").equals("Search")){
                                        sql="select * from kalakar where "+request.getParameter("criteria")+" ='"+request.getParameter("search_value")+"'";
                                    }
                                    rs = stmt.executeQuery(sql); 
                                    while(rs.next()){
                                        kalakar_id=rs.getInt("Id");
                                        count++;
                                        name=rs.getString("Name");
                                        address=rs.getString("Address");
                                        city=rs.getString("City");
                                        dob=rs.getString("DOB");
                                        email=rs.getString("Email");
                                        contact=rs.getString("Contact");
                                        interest=rs.getString("Interest");
                                        playing_instruments=rs.getString("PlayingInstrument");
                                        occupation=rs.getString("Occupation");
                                        gender=rs.getString("Gender");
                                        status=rs.getInt("Status");
                                        %>
                                        <tr class="success">                              
                                            <td><%=name%></td>
                                            <td><%=address%></td>
                                            <td><%=city%></td>
                                            <td><%=dob%></td>
                                            <td><%=email%></td>
                                            <td><%=contact%></td>
                                            <td><%=interest%></td>
                                            <td><%=playing_instruments%></td>
                                            <td><%=occupation%></td>
                                            <td><%=gender%></td>
                                            <td><% if(status==1){%><a href="KalakarAccessibilityAction?KalakarId=<%=kalakar_id%>&Status=block&Email=<%=email%>">Block</a>
                                                <%}else if(status==3){%><a href="KalakarAccessibilityAction?KalakarId=<%=kalakar_id%>&Status=unblock&Email=<%=email%>">Unblock</a>
                                                <%}else if(status==2){%><a href="KalakarAccessibilityAction?KalakarId=<%=kalakar_id%>&Status=approve&Email=<%=email%>">Approve</a>
                                                <%}%>
                                            </td>                                            
                                       </tr>
                                    <% }
                                    conn.close();
                                    if(count==0){%>
                                    <tr class="success"><td colspan="12"><center><b>No matching records found</b></center></td></tr>
                              <%} } catch(Exception ex){}
                         %>  
                      </tbody>
                    </table>
                 </form>                      
            </div>
            </div>
        </div>       
    </div>
    <!-- /#wrapper -->
    </div>
    <!-- jQuery -->
    <script src="js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/bootstrap.min.js"></script>    
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>
    <script>
    // BEGIN FORM CHAT
    $('.btn-chat').click(function () {
        if($('#chat-box').is(':visible')){
            $('#chat-form').toggle('slide', {
                direction: 'right'
            }, 500);
            $('#chat-box').hide();
        } else{
            $('#chat-form').toggle('slide', {
                direction: 'right'
            }, 500);
        }
    });
    $('.chat-box-close').click(function(){
        $('#chat-box').hide();
        $('#chat-form .chat-group a').removeClass('active');
    });
    $('.chat-form-close').click(function(){
        $('#chat-form').toggle('slide', {
            direction: 'right'
        }, 500);
        $('#chat-box').hide();
    });

    $('#chat-form .chat-group a').unbind('*').click(function(){
        $('#chat-box').hide();
        $('#chat-form .chat-group a').removeClass('active');
        $(this).addClass('active');
        var strUserName = $('> small', this).text();
        $('.display-name', '#chat-box').html(strUserName);
        var userStatus = $(this).find('span.user-status').attr('class');
        $('#chat-box > .chat-box-header > span.user-status').removeClass().addClass(userStatus);
        var chatBoxStatus = $('span.user-status', '#chat-box');
        var chatBoxStatusShow = $('#chat-box > .chat-box-header > small');
        if(chatBoxStatus.hasClass('is-online')){
            chatBoxStatusShow.html('Online');
        } else if(chatBoxStatus.hasClass('is-offline')){
            chatBoxStatusShow.html('Offline');
        } else if(chatBoxStatus.hasClass('is-busy')){
            chatBoxStatusShow.html('Busy');
        } else if(chatBoxStatus.hasClass('is-idle')){
            chatBoxStatusShow.html('Idle');
        }

        var offset = $(this).offset();
        var h_main = $('#chat-form').height();
        var h_title = $("#chat-box > .chat-box-header").height();
        var top = ($('#chat-box').is(':visible') ? (offset.top - h_title - 40) : (offset.top + h_title - 20));

        if((top + $('#chat-box').height()) > h_main){
            top = h_main - 	$('#chat-box').height();
        }

        $('#chat-box').css({'top': top});

        if(!$('#chat-box').is(':visible')){
            $('#chat-box').toggle('slide',{
                direction: 'right'
            }, 500);
        }
        // FOCUS INPUT TExT WHEN CLICK
        $('ul.chat-box-body').scrollTop(500);
        $("#chat-box .chat-textarea input").focus();
    });
    // Add content to form
    $('.chat-textarea input').on("keypress", function(e){

        var $obj = $(this);
        var $me = $obj.parent().parent().find('ul.chat-box-body');
        var $my_avt = 'https://s3.amazonaws.com/uifaces/faces/twitter/kolage/128.jpg';
        var $your_avt = 'https://s3.amazonaws.com/uifaces/faces/twitter/alagoon/48.jpg';
        if (e.which == 13) {
            var $content = $obj.val();
            if ($content !== "") {
                var d = new Date();
                var h = d.getHours();
                var m = d.getMinutes();
                if (m < 10) m = "0" + m;
                $obj.val(""); // CLEAR TEXT ON TEXTAREA
                var $element = ""; 
                $element += "<li>";
                $element += "<p>";
                $element += "<img class='avt' src='"+$my_avt+"'>";
                $element += "<span class='user'>John Doe</span>";
                $element += "<span class='time'>" + h + ":" + m + "</span>";
                $element += "</p>";
                $element = $element + "<p>" + $content +  "</p>";
                $element += "</li>";                
                $me.append($element);
                var height = 0;
                $me.find('li').each(function(i, value){
                    height += parseInt($(this).height());
                });
                height += '';
                //alert(height);
                $me.scrollTop(height);  // add more 400px for #chat-box position   
                // RANDOM RESPOND CHAT
                var $res = "";
                $res += "<li class='odd'>";
                $res += "<p>";
                $res += "<img class='avt' src='"+$your_avt+"'>";
                $res += "<span class='user'>Swlabs</span>";
                $res += "<span class='time'>" + h + ":" + m + "</span>";
                $res += "</p>";
                $res = $res + "<p>" + "Yep! It's so funny :)" + "</p>";
                $res += "</li>";
                setTimeout(function(){
                    $me.append($res);
                    $me.scrollTop(height+100); // add more 500px for #chat-box position             
                }, 1000);
            }
        }
    });
    // END FORM CHAT
        </script>
</body>
</html>