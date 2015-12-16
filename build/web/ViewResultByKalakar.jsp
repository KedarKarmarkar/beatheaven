<%@page import="BeatHeaven_Classes.Maifal"%>
<%@page import="java.io.File"%>
<%@page import="BeatHeaven_Classes.Exam"%>
<%@page import="BeatHeaven_Classes.Kalakar"%>
<%@page import="BeatHeaven_Classes.Course"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en"><head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>ViewResult</title>
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

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>
        <%! HttpSession session;%>
        <%
            session = request.getSession();
            int institute_id = 1;
            int kalakar_id = 1;
            String msg = "welcome", kalakar_name = "Admin";
            ArrayList<String> coursename_list = new ArrayList<String>();
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
                    kalakar_id = Integer.parseInt((session.getAttribute("Kalakar_Id").toString()));
                    msg = session.getAttribute("Message").toString();
                }
                    //Kalakar k=new Kalakar();
                // kalakar_name=k.getKalakarName(institute_id);
            }
        } catch (Exception e) {%>
        <h5> Error while session loading..<%=e.getMessage()%></h5>          
        <% }
            Course c = new Course();
            coursename_list = c.getCourseName(institute_id);
            ArrayList<Integer> examid_list = new ArrayList<Integer>();
            Exam e = new Exam();
            examid_list = e.getExamId(institute_id);
        %>
        <div class="row">
            <nav class="navbar navbar-default navbar-fixed-top" style="background-color:#DFDACB">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">beatHeaven</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="Institute_Profile.jsp">Admin Profile</a></li>
                            <li><a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-plus"></i> Post</a></li>
                            <li><a href="#"><span class="badge">Notifiactions</span></a></li>
                            <li><a href="#menu-toggle" class="btn btn-default btn-sm" id="menu-toggle">Toggle Menu</a></li>
                            <li><a href="#"><span class="badge"><%=msg%></span></a></li>

                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><p class="navbar-text">Logged in as <a><%= institute_id%></a><i class="glyphicon glyphicon-user"></i></p></li>
                            <li id="topbar-chat" class="hidden-xs"><a href="javascript:void(0)" data-step="4" data-intro="&lt;b&gt;Form chat&lt;/b&gt; keep you connecting with other coworker" data-position="left" class="btn-chat">Chat<i class="fa fa-comments"></i><span class="badge badge-info">3</span></a></li>
                            <li><a href="LogOutServlet"><i class="glyphicon glyphicon-off"></i>Logout</a></li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div>
            </nav>
            <div class="" id="wrapper">
                <!-- Sidebar -->
                <div id="postModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
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
                <div id="sidebar-wrapper">
                    <ul class="sidebar-nav">
                        <li class="sidebar-brand">
                            <a href="#">
                                Admin
                            </a>
                        </li>
                        <li>
                            <div class="well">
                                <figure>
                                    <center>
                                        <img src="http://www.localcrimenews.com/wp-content/uploads/2013/07/default-user-icon-profile.png" alt="" class="img-circle img-responsive">
                                    </center>
                                </figure>
                            </div>
                        </li>
                        <li>
                            <a href="#">Edit Profile</a>
                        </li>
                        <li>
                            <a href="#">Account Settings</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Event Management<b class="caret"></b></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#" data-toggle="modal" data-target="#AddEventModal">Add Event</a></li>
                                <li class="divider"></li>
                                <li><a href="ViewEvent.jsp" >View Event</a></li>                    
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Exam Management<b class="caret"></b></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#" data-toggle="modal" data-target="#AddExamModal">Add Exam</a></li>
                                <li class="divider"></li>
                                <li><a href="ViewExam.jsp">View Exam</a></li>                    
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Course Management<b class="caret"></b></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#" data-toggle="modal" data-target="#AddCourseModal">Add Course</a></li>
                                <li class="divider"></li>
                                <li><a href="ViewCourse.jsp">View Course</a></li>                    
                            </ul>
                        </li>
                        <li>
                            <a href="#">Manage Access & Users</a>
                        </li>
                        <li>
                            <a href="#">Add Notifications</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Uploads<b class="caret"></b></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#" data-toggle="modal" data-target="#UploadResultModal">Upload Result</a></li>
                                <li class="divider"></li>
                                <li><a href="ViewResult.jsp">View Result</a></li>
                                <li class="divider"></li>
                                <li><a href="#" data-toggle="modal" data-target="#AddPhotoesModal">Add Photos</a></li>  
                                <li class="divider"></li>
                                <li><a href="#" data-toggle="modal" data-target="#ViewGalleryModal">View Gallery</a></li>  
                                <li class="divider"></li>
                                <li><a href="#" data-toggle="modal" data-target="#AddMp3Modal">Add Mp3</a></li> 
                                <li class="divider"></li>
                                <li><a href="#" data-toggle="modal" data-target="#ViewMediaModal">View Media</a></li>  
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- /#sidebar-wrapper -->
                <div class="modal info" id="AddEventModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title">Add Event</h4>
                            </div>

                            <div class="modal-body">
                                <!-- The form is placed inside the body of modal -->
                                <form class="form-horizontal" id="addEventForm" action="AddEventServlet" method="post" >
                                    <div class="form-group">
                                        <label for="name" class="col-sm-4 control-label">Event Title:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="ename" name="event_name" placeholder="Event Name" required autofocus>
                                        </div>
                                    </div>                          
                                    <div class="form-group">
                                        <label for="city" class="col-sm-4 control-label">Enter Location:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="location" name="location" placeholder="Enter Location" required autofocus>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="date" class="col-sm-4 control-label">Starting Date:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="sdate" name="start_date" placeholder="YYYY/MM/DD" required autofocus>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="date" class="col-sm-4 control-label">End Date:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="edate" name="end_date" placeholder="YYYY/MM/DD" required autofocus>
                                        </div>
                                    </div>    
                                    <div class="form-group">
                                        <label for="time" class="col-sm-4 control-label">Time:</label>
                                        <div class="col-sm-8">
                                            <input type="time" class="form-control" id="time" name="time" required autofocus>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="city" class="col-sm-4 control-label">Event Details:</label>
                                        <div class="col-sm-8">
                                            <textarea name="event_info" required autofocus></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="gender" class="col-sm-4 control-label">Select Visibility Level:</label>
                                        <div class="col-sm-8">
                                            <div class="btn-group" data-toggle="buttons">
                                                <label class="btn btn-primary">
                                                    <input type="radio" name="visible_level" value="Public" > Public
                                                </label>
                                                <label class="btn btn-primary">
                                                    <input type="radio" name="visible_level" value="Institute"> Institute
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <hr class="style-two">
                                    <div class="form-group">
                                        <div class="col-sm-offset-4 col-sm-8">
                                            <button type="submit" class="btn btn-info">Submit</button>
                                            <button type="reset" class="btn btn-info">Cancel</button>
                                        </div>
                                    </div>
                                    <div class="form-group">                            
                                        <div class="col-sm-8">
                                            <input type="hidden" class="form-control" id="createdBy" name="created_by" value="1">
                                        </div>
                                    </div> 
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="AddExamModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title">Add Event</h4>
                            </div>

                            <div class="modal-body">
                                <!-- The form is placed inside the body of modal -->
                                <form class="form-horizontal" id="addEventForm" action="AddExamServlet" method="post" >
                                    <div class="form-group">
                                        <label for="name" class="col-sm-4 control-label">Select Course:</label>
                                        <div class="col-sm-8">
                                            <select name="course_id"><% for (int i = 0; i < coursename_list.size(); i++) {%><option value="<%=coursename_list.get(i)%>"><%=coursename_list.get(i)%></option><%}%></select>
                                        </div>
                                    </div>    
                                    <div class="form-group">
                                        <label for="name" class="col-sm-4 control-label">Exam Title:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="ename" name="exam_name" placeholder="Exam Name" required autofocus>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="name" class="col-sm-4 control-label">Exam Date:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="exam_date" name="exam_date" placeholder="Exam Date" required autofocus>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="name" class="col-sm-4 control-label">Time</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="time" name="time" placeholder="time" required autofocus>
                                        </div>
                                    </div>  
                                    <div class="form-group">
                                        <label for="name" class="col-sm-4 control-label">Location:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="location" name="location" placeholder="Location" required autofocus>
                                        </div>
                                    </div>  
                                    <div class="form-group">
                                        <label for="name" class="col-sm-4 control-label">Type:</label>
                                        <div class="col-sm-8">
                                            <select name="exam_type"><option value="Multichoice">MultiChoice</option><option value="Subjective">Subjective</option></select>
                                        </div>
                                    </div> 
                                    <div class="form-group">
                                        <label for="name" class="col-sm-4 control-label">Eligibility:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="eligibility" name="eligibility" placeholder="Eligibility" required autofocus>
                                        </div>
                                    </div>
                                    <hr class="style-two">
                                    <div class="form-group">
                                        <div class="col-sm-offset-4 col-sm-8">
                                            <button type="submit" class="btn btn-info">Submit</button>
                                            <button type="reset" class="btn btn-info">Cancel</button>
                                        </div>
                                    </div>
                                    <div class="form-group">                            
                                        <div class="col-sm-8">
                                            <input type="hidden" class="form-control" id="institute_id" name="institute_id" value="1">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- add course model -->
                <div class="modal fade" id="AddCourseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title">Add Course</h4>
                            </div>

                            <div class="modal-body">
                                <!-- The form is placed inside the body of modal -->
                                <form class="form-horizontal" id="uploadResultForm" action="AddCourseServlet" method="post" >
                                    <div class="form-group">
                                        <label for="name" class="col-sm-4 control-label">Course Name:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="cname" name="course_name" placeholder="Course Name" required autofocus>
                                        </div>
                                    </div>                          
                                    <div class="form-group">
                                        <label for="city" class="col-sm-4 control-label">Enter Duration:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="duration" name="duration" placeholder="In  Months" required autofocus>
                                        </div>
                                    </div>                          
                                    <div class="form-group">
                                        <label for="time" class="col-sm-4 control-label">Fee:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="fee" name="fee" placeholder="(enter per month amount:in digits)" required autofocus>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="city" class="col-sm-4 control-label">Eligibility:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="eligibility" name="eligibility" placeholder="eligibility" required autofocus>
                                        </div>
                                    </div> 
                                    <div class="form-group">
                                        <label for="city" class="col-sm-4 control-label">Event Specification(Note):</label>
                                        <div class="col-sm-8">
                                            <textarea name="specification" required autofocus></textarea>
                                        </div>
                                    </div>    

                                    <hr class="style-two">
                                    <div class="form-group">
                                        <div class="col-sm-offset-4 col-sm-8">
                                            <button type="submit" class="btn btn-info">Submit</button>
                                            <button type="reset" class="btn btn-info">Cancel</button>
                                        </div>
                                    </div>
                                    <div class="form-group">                            
                                        <div class="col-sm-8">
                                            <input type="hidden" class="form-control" id="createdBy" name="institute_id" value="1">
                                        </div>
                                    </div> 
                                </form>
                            </div>
                        </div>
                    </div>
                </div>   
                <!-- Upload Result Model -->
                <div class="modal fade" id="UploadResultModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title">Upload Result</h4>
                            </div>

                            <div class="modal-body">
                                <!-- The form is placed inside the body of modal -->
                                <form class="form-horizontal" id="uploadResult" action="UploadResultServlet" method="post" >
                                    <div class="form-group">
                                        <label for="name" class="col-sm-4 control-label">Select ExamId:</label>
                                        <div class="col-sm-8">
                                            <select name="exam_id"><% for (int i = 0; i < examid_list.size(); i++) {%>
                                                <option value="<%=examid_list.get(i)%>"><%=examid_list.get(i)%></option><%}%>
                                            </select>
                                        </div>
                                    </div>                          
                                    <div class="form-group">
                                        <label for="city" class="col-sm-4 control-label">Comments:</label>
                                        <div class="col-sm-8">
                                            <textarea name="result_desc" cols="20" rows="5" required autofocus></textarea>
                                        </div>
                                    </div>                          
                                    <div class="form-group">
                                        <label for="city" class="col-sm-4 control-label">Note:</label>
                                        <div class="col-sm-8">
                                            <label for="note" class="col-sm-8 control-label">File Name should be in the format:(ExamId_Result.pdf)</label>
                                        </div>
                                    </div> 
                                    <hr class="style-two">
                                    <div class="form-group">
                                        <div class="col-sm-offset-4 col-sm-8">
                                            <button type="submit" class="btn btn-info"  >AttachFile</button>
                                            <button type="reset" class="btn btn-info">Cancel</button>
                                        </div>
                                    </div>
                                    <div class="form-group">                            
                                        <div class="col-sm-8">
                                            <input type="hidden" class="form-control" id="createdBy" name="institute_id" value="1">
                                        </div>
                                    </div> 
                                </form>
                            </div>
                        </div>
                    </div>
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
                             <div class="col-md-12" id="mainDiv" ><hr style="border-color: blueviolet">                        
                        <form>
                            <b>Available Results:</b>
                            <center>
                            <table class="table table-bordered">
                                <thead>
                          <tr class="active">
                              <th>click to download the file</th>
                          </tr> 
                          </thead>
                      <tbody>
                                            <%
                                                String fileName = "";
                                                String cfile = "/BeatHeaven/Filedownloader.jsp";
                                                String locationFolder = "D:\\new\\exam_results\\";
                                                // locationFolder="D:\\Data\\TYMCA\\SEM VI\\Beatheaven-Final Year Project\\BeatHeaven\\web\\img1";
                                                int s = 0;
                                                Maifal maifal=new Maifal();
                                                maifal.getMaifalDetails(institute_id);
                                                String institute_name=maifal.getName();
                                                locationFolder= locationFolder+institute_name+"\\";
                                                File folder = new File(locationFolder);
                                                File[] listOfFiles = folder.listFiles();
                                                for (int i = 0; i < listOfFiles.length; i++) {
                                                    if (listOfFiles[i].isFile()) {
                                                        fileName = listOfFiles[i].getName();%>
                      <td><h5> <a href=" /BeatHeaven/DownloadExamResult.jsp?filename=<%=fileName%>&filepath=<%=locationFolder%>"> <%=fileName%> </a></h5></td>
                                        <%         }
                                            }

                                        %>
                            </tbody>
                            </table>
                            </center>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- /#page-content-wrapper -->

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
            $('.btn-chat').click(function() {
                if ($('#chat-box').is(':visible')) {
                    $('#chat-form').toggle('slide', {
                        direction: 'right'
                    }, 500);
                    $('#chat-box').hide();
                } else {
                    $('#chat-form').toggle('slide', {
                        direction: 'right'
                    }, 500);
                }
            });
            $('.chat-box-close').click(function() {
                $('#chat-box').hide();
                $('#chat-form .chat-group a').removeClass('active');
            });
            $('.chat-form-close').click(function() {
                $('#chat-form').toggle('slide', {
                    direction: 'right'
                }, 500);
                $('#chat-box').hide();
            });

            $('#chat-form .chat-group a').unbind('*').click(function() {
                $('#chat-box').hide();
                $('#chat-form .chat-group a').removeClass('active');
                $(this).addClass('active');
                var strUserName = $('> small', this).text();
                $('.display-name', '#chat-box').html(strUserName);
                var userStatus = $(this).find('span.user-status').attr('class');
                $('#chat-box > .chat-box-header > span.user-status').removeClass().addClass(userStatus);
                var chatBoxStatus = $('span.user-status', '#chat-box');
                var chatBoxStatusShow = $('#chat-box > .chat-box-header > small');
                if (chatBoxStatus.hasClass('is-online')) {
                    chatBoxStatusShow.html('Online');
                } else if (chatBoxStatus.hasClass('is-offline')) {
                    chatBoxStatusShow.html('Offline');
                } else if (chatBoxStatus.hasClass('is-busy')) {
                    chatBoxStatusShow.html('Busy');
                } else if (chatBoxStatus.hasClass('is-idle')) {
                    chatBoxStatusShow.html('Idle');
                }


                var offset = $(this).offset();
                var h_main = $('#chat-form').height();
                var h_title = $("#chat-box > .chat-box-header").height();
                var top = ($('#chat-box').is(':visible') ? (offset.top - h_title - 40) : (offset.top + h_title - 20));

                if ((top + $('#chat-box').height()) > h_main) {
                    top = h_main - $('#chat-box').height();
                }

                $('#chat-box').css({'top': top});

                if (!$('#chat-box').is(':visible')) {
                    $('#chat-box').toggle('slide', {
                        direction: 'right'
                    }, 500);
                }
                // FOCUS INPUT TExT WHEN CLICK
                $('ul.chat-box-body').scrollTop(500);
                $("#chat-box .chat-textarea input").focus();
            });
            // Add content to form
            $('.chat-textarea input').on("keypress", function(e) {

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
                        if (m < 10)
                            m = "0" + m;
                        $obj.val(""); // CLEAR TEXT ON TEXTAREA

                        var $element = "";
                        $element += "<li>";
                        $element += "<p>";
                        $element += "<img class='avt' src='" + $my_avt + "'>";
                        $element += "<span class='user'>John Doe</span>";
                        $element += "<span class='time'>" + h + ":" + m + "</span>";
                        $element += "</p>";
                        $element = $element + "<p>" + $content + "</p>";
                        $element += "</li>";
                        $me.append($element);
                        var height = 0;
                        $me.find('li').each(function(i, value) {
                            height += parseInt($(this).height());
                        });
                        height += '';
                        //alert(height);
                        $me.scrollTop(height);  // add more 400px for #chat-box position     

                        // RANDOM RESPOND CHAT

                        var $res = "";
                        $res += "<li class='odd'>";
                        $res += "<p>";
                        $res += "<img class='avt' src='" + $your_avt + "'>";
                        $res += "<span class='user'>Swlabs</span>";
                        $res += "<span class='time'>" + h + ":" + m + "</span>";
                        $res += "</p>";
                        $res = $res + "<p>" + "Yep! It's so funny :)" + "</p>";
                        $res += "</li>";
                        setTimeout(function() {
                            $me.append($res);
                            $me.scrollTop(height + 100); // add more 500px for #chat-box position             
                        }, 1000);
                    }
                }
            });
            // END FORM CHAT

        </script>




    </body></html>