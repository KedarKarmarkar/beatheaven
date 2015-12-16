<%@page import="java.io.File"%>
<%@page import="BeatHeaven_Classes.ProfilePicDetails"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.DriverManager"%>
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
<!DOCTYPE html>
<html lang="en"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>User Approval</title>
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
<%       // Set refresh, autoload time as 5 seconds
   response.setIntHeader("Refresh", 60);
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
 
    session = request.getSession();
    int institute_id=1;
    int kalakar_id=1,notificationCount=0;
    String msg="welcome",kalakar_name="Admin"; 
    ArrayList<String> coursename_list = new ArrayList<String>();  
    EventInfo ei=new EventInfo();
    Kalakar k=new Kalakar();
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
                        //kalakar_id=Integer.parseInt((session.getAttribute("Kalakar_Id").toString()));
                        msg=session.getAttribute("Message").toString();
                    }
                    //Kalakar k=new Kalakar();
                   // kalakar_name=k.getKalakarName(institute_id);
        }
    }catch(Exception e){%>
      <h5> Error while session loading..<%=e.getMessage()%></h5>          
   <% }
    
    Course c=new Course();
    coursename_list=c.getCourseName(institute_id);
    Maifal m=new Maifal();
    m.getMaifalDetails(institute_id);
    ArrayList<Integer> examid_list = new ArrayList<Integer>();
    Exam e=new Exam();
    examid_list=e.getExamId(institute_id);
    ArrayList<Integer> olfriend_list = new ArrayList<Integer>();
    OnlineUsers ou=new OnlineUsers();
    olfriend_list=ou.getOnlineInstituteUsers(institute_id);
    BirthDayCalculator bdc=new BirthDayCalculator();
    String isStatus= bdc.insertIntoLastSeenStatus(institute_id, "Institute");
    notificationCount=bdc.getInstituteNotificationCount(institute_id);
    ProfilePicDetails ppd=new ProfilePicDetails();
    int count=ppd.getProfilePicDetails(institute_id, "Institute");
    String dir=institute_id+"_Institute";
    String profilepic_path="";
    String profilepic_name="";
    if(count>0){
        profilepic_path=ppd.getFile_location();
        int index=profilepic_path.lastIndexOf("/");
        profilepic_name= profilepic_path.substring(index+1);
        profilepic_path="img1/Profile_Pic/"+profilepic_name;
    }
    else
        profilepic_path="img1/profile_pic.png";   
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
          <a class="navbar-brand" href="Institute_Profile.jsp" ><b>BEAT</b>
                   <span style="font-weight:bold;color:#c32929;">HEAVEN</span> </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
              <li class="active"><a href="Institute_Profile.jsp"><span style="font-weight:bold;color:#28a4c9;" ><b><%= m.getName()%></b></span></a></li>
            <li><a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-plus"></i> Post</a></li>
            <li><a href="" data-toggle="modal" data-target="#ViewNotificationModel"><span class="badge">Notifications<span class="badge badge-info"><%=notificationCount%></span></span></a></li>
            <li><a href="#menu-toggle" class="btn btn-default btn-sm" id="menu-toggle">Toggle Menu</a></li>
            <li><a href="#"><span class="badge"><%=CT%></span></a></li>
            
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><p class="navbar-text">Logged in as <a href="EditInstituteProfile.jsp"><%= m.getName()%></a></p></li>
         <!--   <li id="topbar-chat" class="hidden-xs"><a href="javascript:void(0)" data-step="4" data-intro="&lt;b&gt;Form chat&lt;/b&gt; keep you connecting with other coworker" data-position="left" class="btn-chat">Chat<i class="fa fa-comments"></i><span class="badge badge-info"><% //=olfriend_list.size() %></span></a></li> -->
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
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="" data-toggle="modal" data-target="#UploadResultModal" style="font-weight:bold;color:#c32929;">Upload Result</a></li>
                    <li class="divider"></li>
                    <li><a href="ViewResult.jsp" style="font-weight:bold;color:#c32929;">View Result</a></li>
                    <li class="divider"></li>
                    <li><a href="#" data-toggle="modal" data-target="#AddPhotoesModal" style="font-weight:bold;color:#c32929;">Add Photos</a></li>  
                    <li class="divider"></li>
                    <li><a href="#" data-toggle="modal" data-target="#ViewGalleryModal" style="font-weight:bold;color:#c32929;">View Gallery</a></li>  
                    <li class="divider"></li>
                    <li><a href="#" data-toggle="modal" data-target="#AddMp3Modal" style="font-weight:bold;color:#c32929;">Add Mp3</a></li> 
                    <li class="divider"></li>
                    <li><a href="ViewMedia.jsp"  style="font-weight:bold;color:#c32929;">View Media</a></li>  
                  </ul>
                </li>
               <li class="dropdown" style="font-weight:bold;color:#c32929;">
                  <a href="" class="dropdown-toggle" data-toggle="dropdown" >Event Management<b class="caret"></b></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="" data-toggle="modal" data-target="#AddEventModal" style="font-weight:bold;color:#c32929;">Add Event</a></li>
                    <li class="divider"></li>
                    <li><a href="ViewEvent.jsp" style="font-weight:bold;color:#c32929;">View Event</a></li>                    
                  </ul>
                </li>
                  <li class="dropdown">
                  <a href="" class="dropdown-toggle" data-toggle="dropdown">Exam Management<b class="caret"></b></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="" data-toggle="modal" data-target="#AddExamModal" style="font-weight:bold;color:#c32929;">Add Exam</a></li>
                    <li class="divider"></li>
                    <li><a href="ViewExam.jsp" style="font-weight:bold;color:#c32929;">View Exam</a></li>                    
                  </ul>
                </li>
                <li class="dropdown">
                  <a href="" class="dropdown-toggle" data-toggle="dropdown">Course Management<b class="caret"></b></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="" data-toggle="modal" data-target="#AddCourseModal" style="font-weight:bold;color:#c32929;">Add Course</a></li>
                    <li class="divider"></li>
                    <li><a href="ViewCourse.jsp" style="font-weight:bold;color:#c32929;">View Course</a></li>                    
                  </ul>
                </li>               
                
                <li class="dropdown">
                  <a href="" class="dropdown-toggle" data-toggle="dropdown">Manage Access & Users<b class="caret"></b></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="UserApproval.jsp" style="font-weight:bold;color:#c32929;">User Approval</a></li>
                    <li class="divider"></li>
                    <li><a href="ManageInstituteAccessibility.jsp" style="font-weight:bold;color:#c32929;">Manage Access</a></li>                    
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
        <!-- ViewFeedBackModel -->
        <div class="modal info" id="ViewFeedBackModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                  <div class="modal-content">                      
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                          <h4 class="modal-title">View FeedBack / Queries</h4>
                      </div> 
                      <div class="modal-body active">  
                          <form class="form-horizontal" id="AddInstitutFeedBackForm" action="AddInstitutFeedBack" method="post">
                             <div class="form-group">
                                <%
                                    Feedback fb1=new Feedback();
                                    fb1.getAllFeedBack(institute_id);
                                %>
                               <table class="table table-bordered">
                                 <thead>
                                        <tr class="active">
                                            <th>Posted By</th>                              
                                            <th>Category</th>
                                            <th>Subject</th>
                                            <th>PostDate</th>
                                            <th></th>                              
                                        </tr>
                                </thead>
                                <tbody>
                                    <% if(fb1.count>0){ %>
                                    <tr class="success">
                                         <td><%=fb1.getUser_name()%></td>
                                         <td><%=fb1.getCategory()%></td>
                                         <td><%=fb1.getSubject()%></td>
                                         <td><%=fb1.getPost_date()%></td>
                                         <td><a href="ViewInstituteFeedBackDetails.jsp?Id=<%=fb1.getId()%>">View Details</a></td>                                         
                                    </tr>
                                    <% }else{ %>
                                    <tr class="success"><td colspan="5"><center>No more details found..</center></td></tr>
                                    <% } %>
                                </tbody>
                              </table>
                             </div>                                                          
                          </form>
                      </div> 
                  </div>                  
              </div>
         </div>
                                <!-- AddPhotoesModal -->
         <div class="modal info" id="AddPhotoesModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                  <div class="modal-content">                      
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                          <h4 class="modal-title">Add Event Pics</h4>
                      </div> 
                      <div class="modal-body active">  
                          <form class="form-horizontal" id="AddPhotoesModalForm" action="AddEventImagesServlet" method="post">
                               <%
                                    ei.getEventIdList(institute_id);
                                %>                               
                             <div class="form-group">                               
                                <label for="name" class="col-sm-4 control-label">Select Event:</label>
                                <div class="col-sm-6">
                                    <select name="eventId">
                                        <%
                                            for(int i=0;i<ei.eventid_list.size();i++){
                                        %>
                                        <option value="<%= ei.eventid_list.get(i)%>"><%= ei.eventname_list.get(i) %></option>
                                        <% } %>
                                    </select>
                                </div>
                             </div>
                             <div class="form-group">
                                <label for="name" class="col-sm-4 control-label">Title:</label>
                                <div class="col-sm-6">
                                    <input type="text" name="title" placeholder="Add Title" required autofocus/>
                                </div>
                             </div>
                             <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-8">
                                  <button type="submit" class="btn btn-info"  >Add Image</button>
                                  <button type="reset" class="btn btn-info">Cancel</button>
                                </div>
                            </div>       
                          </form>
                      </div> 
                  </div>                  
              </div>
         </div>
                                    <!-- AddMp3Model -->
        <div class="modal info" id="AddMp3Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                  <div class="modal-content">                      
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                          <h4 class="modal-title">Add Event Pics</h4>
                      </div> 
                      <div class="modal-body active">  
                          <form class="form-horizontal" id="AddMp3Modal" action="AddMp3ModelServlet" method="post">
                                      
                             <div class="form-group">
                                <label for="name" class="col-sm-4 control-label">Title:</label>
                                <div class="col-sm-6">
                                    <input type="text" name="title" placeholder="Song Title" required autofocus/>
                                </div>
                             </div>
                             <div class="form-group">
                                <label for="name" class="col-sm-4 control-label">Song By:</label>
                                <div class="col-sm-6">
                                    <input type="text" name="songby" placeholder="Song By" required autofocus/>
                                </div>
                             </div>
                             <div class="form-group">
                                <label for="name" class="col-sm-4 control-label">Singer:</label>
                                <div class="col-sm-6">
                                    <input type="text" name="singer" placeholder="Singer" required autofocus/>
                                </div>
                             </div>
                             <div class="form-group">
                                <label for="name" class="col-sm-4 control-label">Album:</label>
                                <div class="col-sm-6">
                                    <input type="text" name="album" placeholder="album" required autofocus/>
                                </div>
                             </div>
                             <div class="form-group">
                                <label for="name" class="col-sm-4 control-label">Song Type</label>
                                <div class="col-sm-6">
                                    <select name="songtype">
                                        <option value="classical_vocal">Classical-Vocal</option>
                                        <option value="classical_instrumental">Classical-Instrumental</option>
                                        <option value="semi-classical">Semi-Classical</option>
                                        <option value="light-music">Light-Music</option>
                                        <option value="fusion">fusion</option>
                                        <option value="rock">rock</option>
                                        <option value="light-music">Other</option>
                                    </select>
                                </div>
                             </div>
                             <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-8">
                                  <button type="submit" class="btn btn-info">Add Mp3</button>
                                  <button type="reset" class="btn btn-info">Cancel</button>
                                </div>
                            </div>       
                          </form>
                      </div> 
                  </div>                  
              </div>
         </div>
            <!-- AddFeedBackModel -->
          <div class="modal info" id="AddFeedBackModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                  <div class="modal-content">                      
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                          <h4 class="modal-title">Add FeedBack / Queries</h4>
                      </div> 
                      <div class="modal-body active">  
                          <form class="form-horizontal" id="AddInstitutFeedBackForm" action="AddInstitutFeedBack" method="post">
                             <div class="form-group">
                                <label for="name" class="col-sm-4 control-label">Choose Category:</label>
                                <div class="col-sm-8">
                                    <select name="category"><option value="Login">Login</option><option value="Registration">Registration</option><option value="Blog/Posts">Blog/Posts</option><option value="events">events</option><option value="Exam">Exam</option>
                                        <option value="Course">Course</option><option value="Result">Result</option><option value="FeedBack">FeedBack</option><option value="Other">Other</option></select>

                                </div>
                             </div>
                             <div class="form-group">
                                <label for="name" class="col-sm-4 control-label">Subject:</label>
                                <div class="col-sm-8">
                                    <input type="text" name="subject" placeholder="Add Subject" required autofocus/>
                                </div>
                             </div>
                             <div class="form-group">
                                <label for="name" class="col-sm-4 control-label">Description:</label>
                                <div class="col-sm-8">
                                    <textarea name="discription" placeholder="Add Details" required autofocus ></textarea>
                                </div>
                             </div>
                             <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-8">
                                    <button type="submit" class="btn btn-info">Submit</button>
                                    <button type="reset" class="btn btn-info">Cancel</button>
                                </div>
                             </div>                              
                          </form>
                      </div> 
                  </div>                  
              </div>
         </div>
    
        <!-- /#BirthdayWishModel-->
        <div class="modal info" id="AddBirthdayWishModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                  <div class="modal-content">                      
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                          <h4 class="modal-title">Add BirthdayWishes</h4>
                      </div> 
                      <div class="modal-body active">                         
                              <%                              
                                for (int i = 0; i < bdc.bdayIdList.size(); i++) {
                                    String birthdaywishdate=bdc.getBirthDayWishDate(bdc.bdayIdList.get(i), institute_id, "Institute");
                                    GetCurrentDateTime gcdt=new GetCurrentDateTime();
                                    if(!(birthdaywishdate.equals(gcdt.getCurDate()))) {
                              %>
                              <form action="BirthdayWishServlet" method="get">                               
                                   <%=k.getKalakarName(bdc.bdayIdList.get(i))%><br>
                                   <input type="text" name="comment" id="comment" placeholder="Add Comment"/></br>
                                   <input type="submit" name="submit"><br>
                                   <br><input type="text" name="bdayid" value="<%=bdc.bdayIdList.get(i)%>"/>&nbsp;<input type="text" name="postedType" value="Institute"/>&nbsp;<input type="text" name="postedBy" value="<%=institute_id%>"/></br>                                   
                              </form>
                              <% }else{%>
                              You have already posted on <%=k.getKalakarName(bdc.bdayIdList.get(i))%>'s wall<br>  
                                   <% }
                                }%>
                              <hr>
                      </div>
                  </div>
              </div>
         </div>
         <div class="modal info" id="ViewNotificationModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                  <div class="modal-content">
                      
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                          <h4 class="modal-title">Notifications</h4>
                      </div> <form class="form-inline" id="addEventForm">
                          
                      <div class="modal-body active">
                         
                              <%for (int i = 0; i < bdc.bdayIdList.size(); i++) {%>
                              <i><a href="" data-toggle="modal" data-target="#AddBirthdayWishModel"><%=k.getKalakarName(bdc.bdayIdList.get(i))%></a></i>,
                              <%}if(bdc.bdayIdList.size()>0){%> 
                              have birthday today,click to wish 
                              <%}%>
                              <hr>
                              <%for (int i = 0; i < bdc.eventIdList.size(); i++) {
                              ei.getEventInfo(bdc.eventIdList.get(i)); %>
                              <%=ei.getEvent_name()%> arranged @Location: <%=ei.getLocation()%>, starting from <%=ei.getStartDate()%>,<a href="ViewEvent.jsp">Click</a> to see details<br><hr>
                            <% }
                            int updateStatus= bdc.updateLast_Seen(institute_id, "Institute");
                          %>
                      </div>                      
                      </form>                           
                  </div>
              </div>
         </div>
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
                                <input type="text" class="form-control" id="time" name="eventTime"  placeholder="(e..g 1:00PM-3:00PM)" required autofocus>
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
                          <h4 class="modal-title">Add Exam</h4>
                      </div>

                      <div class="modal-body">
                          <!-- The form is placed inside the body of modal -->
                          <form class="form-horizontal" id="addEventForm" action="AddExamServlet" method="post" >
                          <div class="form-group">
                            <label for="name" class="col-sm-4 control-label">Select Course:</label>
                            <div class="col-sm-8">
                                <select name="course_id"><% for(int i=0;i<coursename_list.size();i++){ %><option value="<%=coursename_list.get(i)%>"><%=coursename_list.get(i)%></option><%}%></select>
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
                                <input type="text" class="form-control" id="time" name="examTime"  placeholder="(e..g 1:00PM-3:00PM)" required autofocus>
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
                                <select name="exam_id"><% for(int i=0;i<examid_list.size();i++){ %>
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
                                   count=0;
                                   GetCurrentDateTime gcdt=new GetCurrentDateTime();
                                   String cur_date=gcdt.getCurDate();
                                    Class.forName("com.mysql.jdbc.Driver");
                                   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/beatheaven", "root", "");              
                                    Statement stmt = conn.createStatement();            
                                    String sql="select k.* from kalakar k,kalakar_institute_relation kir where  kir.InstituteId="+institute_id+" and kir.Status=0 and k.Id=kir.KalakarId";
                                    ResultSet rs = stmt.executeQuery(sql); 
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
                                            <td><a href="InstituteUserReqApproval?KalakarId=<%=kalakar_id%>&Status=approve&InstituteId=<%=institute_id%>&Email=<%=email%>">Approve</a></td>
                                            <td><a href="InstituteUserReqApproval?KalakarId=<%=kalakar_id%>&Status=delete&InstituteId=<%=institute_id%>&Email=<%=email%>">Delete</a></td>
                                       </tr>
                                    <% }
                                    conn.close(); 
                                    if(count==0){%>
                                    <tr class="success"><td colspan="12"><center><b>No more approvals pending</b></center></td></tr>
                              <%} } catch(Exception ex){}
                         %>  
                      </tbody>
                    </table>
                 </form>                      
            </div>
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