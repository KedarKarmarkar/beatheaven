<%-- 
    Document   : User_Profile
    Created on : Apr 27, 2015, 11:28:10 PM
    Author     : OM DATTA
--%>

<%@page import="java.io.File"%>
<%@page import="BeatHeaven_Classes.ProfilePicDetails"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
        <title>PersonalBlogImagesAttach</title>
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
            hr.style-two {
    border: 0;
    height: 1px;
    background-image: -webkit-linear-gradient(left, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));
    background-image:    -moz-linear-gradient(left, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));
    background-image:     -ms-linear-gradient(left, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));
    background-image:      -o-linear-gradient(left, rgba(0,0,0,0), rgba(0,0,0,0.75), rgba(0,0,0,0));

}
            
        </style>
        <script >
            $(document).ready(function() {
            $("#txtaimagepath").hide();
            }
            $("").click(function () {
            $("p").hide();
            });
                    $("#show").click(function () {
            $("p").show();
            });</script>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>
        <%! HttpSession session;%>
        <%       // Set refresh, autoload time as 5 seconds
            response.setIntHeader("Refresh", 180);
            // Get current time
            Calendar calendar = new GregorianCalendar();
            String am_pm;
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            if (calendar.get(Calendar.AM_PM) == 0) {
                am_pm = "AM";
            } else {
                am_pm = "PM";
            }
            String CT = hour + ":" + minute + ":" + second + " " + am_pm;

            //session = request.getSession();
            int institute_id = 1;
            int kalakar_id = 3, notificationCount = 0;
            String msg = "welcome", kalakar_name = "Admin";
            ArrayList<String> coursename_list = new ArrayList<String>();
            EventInfo ei = new EventInfo();
            Kalakar k = new Kalakar();           
            try {
                session = request.getSession();
                if (session == null || session.isNew()) { %>
        <div class="alert alert-warning" role="alert">You are not logged in...</div>
        <%   } else {
                Enumeration names = session.getAttributeNames();
                while (names.hasMoreElements()) {
                    String name = (String) names.nextElement();
                    Object value = session.getAttribute(name);
                    //institute_id = Integer.parseInt((session.getAttribute("Institute_Id").toString()));
                    kalakar_id = Integer.parseInt((session.getAttribute("Kalakar_Id").toString()));
                    msg = session.getAttribute("Message").toString();
                }
                //Kalakar k=new Kalakar();
                // kalakar_name=k.getKalakarName(institute_id);
            }
        } catch (Exception e) {%>
        <div class="alert alert-warning" role="alert">Error while session loading..<%=e.getMessage()%></div>         
        <% }
          k.getkalakarDetails(kalakar_id);
            Course c = new Course();
            coursename_list = c.getCourseName(institute_id);
            ArrayList<Integer> examid_list = new ArrayList<Integer>();
            Exam e = new Exam();
            examid_list = e.getExamId(institute_id);
            ArrayList<Integer> olfriend_list = new ArrayList<Integer>();
            OnlineUsers ou = new OnlineUsers();
            olfriend_list = ou.getOnlineInstituteUsers(institute_id);
            BirthDayCalculator bdc = new BirthDayCalculator();
            String isStatus = bdc.insertIntoLastSeenStatus(kalakar_id, "Kalakar");
            notificationCount = bdc.getKalakarNotificationCount(kalakar_id);
            ProfilePicDetails ppd=new ProfilePicDetails();
            int count=ppd.getProfilePicDetails(kalakar_id, "Kalakar");
            //String dir=institute_id+"_Institute";
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
                        <a class="navbar-brand" href="Kalakar_Profile.jsp" ><b>BEAT</b><span style="font-weight:bold;color:#c32929;">HEAVEN</span> </a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="Kalakar_Profile.jsp">Kalakar Profile</a></li>
                            <li><a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-plus"></i> Post</a></li>
                            <li><a href="" data-toggle="modal" data-target="#ViewNotificationModel"><span class="badge">Notifications<span class="badge badge-info"><%=notificationCount%></span></span></a></li>
                            <li><a href="#menu-toggle" class="btn btn-default btn-sm" id="menu-toggle">Toggle Menu</a></li>
                            <li><a href="#"><span class="badge"><%=CT%></span></a></li>

                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><p class="navbar-text">Logged in as <a href="Edit_Kalakar_Profile.jsp"><%=k.kalakar_name%></a></p></li>
                            <li id="topbar-chat" class="hidden-xs"><a href="javascript:void(0)" data-step="4" data-intro="&lt;b&gt;Form chat&lt;/b&gt; keep you connecting with other coworker" data-position="left" class="btn-chat">Chat<i class="fa fa-comments"></i><span class="badge badge-info"><%=olfriend_list.size()%></span></a></li>
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
                                <form class="form-horizontal" action="AddPersonalBlogServlet" method="post">
                                    <div class="form-group">
                                        <label for="txtPostTitle" class="col-sm-3 control-label">Title</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="txtPostTitle" id="txtPostTitle" placeholder="Post Title">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="txtSubject" class="col-sm-3 control-label">Subject</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="txtSubject" id="txtSubject" placeholder="Post Subject">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="txtRefrences" class="col-sm-3 control-label">References</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="txtRefrences" id="txtRefrences" placeholder="For eg. friends,family,newspaper,TV channel">
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
                                                        <input type="radio" name="txtPrivacy" id="option2" value="Public" >Public 
                                                    </label>
                                                    <label class="btn btn-primary">
                                                        <input type="radio" name="txtPrivacy" id="option3"  value="Institute">Institute 
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="txtaPostBody" class="col-sm-3 control-label">What's in Your Mind ?</label>
                                        <div class="col-sm-9">
                                            <textarea class="form-control input-lg" name="txtaPostBody" id="txtaPostBody" autofocus="" placeholder="What do you want to share?"></textarea>
                                        </div>
                                    </div>
                                    <hr class="style-two">
                                    <div><center>
                                    <button type="submit" class="btn btn-primary btn-sm" name="post" value="Post" >Post</button>
                                    <button type="submit" class="btn btn-primary btn-sm" name="post" value="AttachPic" >Add Image</button>
                                        </center>
                                <!--    <ul class="pull-left list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li><li><a href=""><i class="glyphicon glyphicon-camera"></i></a></li><li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul> -->
                                </div>
                                </form>
                            </div>
                            <div class="modal-footer">

                            </div>
                        </div>
                    </div>
                </div>
                <div id="sidebar-wrapper">
                    <ul class="sidebar-nav">
                        <li class="sidebar-brand">   </li>           
                <li >               
                    <figure>
                        <center>
                            <img src="<%=profilepic_path%>" alt="" class="img-circle img-responsive" style="width:150px;height:125px"> 
                        </center>
                    </figure>                    
                </li>               
                <li><hr></li>
                        <li class="dropdown">
                  <a href="" class="dropdown-toggle" data-toggle="dropdown">Registered Institutes<b class="caret"></b></a>
                  <ul class="dropdown-menu" role="menu">
                      
                    <li><a href="ViewInstituteProfileByUser?Id=1"  style="font-weight:bold;color:#c32929;">Vishwa Music Acadamy</a></li>
                    
                    
                  </ul>
                </li>
                    </ul>
                </div>

                <!-- /#sidebar-wrapper -->
                <div class="modal info" id="ViewNotificationModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title">Notifications</h4>
                            </div> 
                            <form class="form-inline" id="addEventForm">                          
                                <div class="modal-body active">                         
                                    <%for (int i = 0; i < bdc.bdayIdList.size(); i++) {%>
                                    <i><a href="" data-toggle="modal" data-target="#AddBirthdayWishModel"><%=k.getKalakarName(bdc.bdayIdList.get(i))%></a></i>,
                                        <%}
                                  if (bdc.bdayIdList.size() > 0) {%> 
                                    have birthday today,click to wish 
                                    <%}%>
                                    <hr>
                                    <%for (int i = 0; i < bdc.eventIdList.size(); i++) {
                                      ei.getEventInfo(bdc.eventIdList.get(i));%>
                                    <%=ei.getEvent_name()%> arranged @Location: <%=ei.getLocation()%>, starting from <%=ei.getStartDate()%>,<a href="ViewEvent.jsp">Click</a> to see details<br><hr>
                                    <% }
                                        int updateStatus = bdc.updateLast_Seen(institute_id, "Institute");
                                    %>
                                </div>                      
                            </form>                           
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
                    <form  action="UploadPersonalBlogImage" method="post" enctype="multipart/form-data" >
                        <div class="form-group">
                            <center>
                                <br><br><br><br>
                                <table style="border-style: inherit"> 
                                    <tr><td colspan="3"><center><b><u>Choose Image to Upload</u></b></center><br></td></tr>
                                    <tr><td><b>Choose File :</b></td><td> <input type="file" name="fname" multiple="multiple"/></td><td><input type="image" src="img1/MainUpload.png"/> </td></tr>                                    
                                </table>
                            </center>
                        </div>                          
                    </form>                    
                </div>
                        
                    </div>
                </div>
            </div>
            <!-- /#page-content-wrapper -->

        </div>
        <!-- /#wrapper -->


        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery-1.10.2.min.js"></script>
        <script src="js/jquery-ui.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <script>
                    $("#menu-toggle").click(function (e) {
            e.preventDefault();
                    $("#wrapper").toggleClass("toggled");
            });</script>
        <script>
                    // BEGIN FORM CHAT
                    $('.btn-chat').click(function () {
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
                    $('.chat-box-close').click(function () {
            $('#chat-box').hide();
                    $('#chat-form .chat-group a').removeClass('active');
            });
                    $('.chat-form-close').click(function () {
            $('#chat-form').toggle('slide', {
            direction: 'right'
            }, 500);
                    $('#chat-box').hide();
            });
                    $('#chat-form .chat-group a').unbind('*').click(function () {
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
                    $('.chat-textarea input').on("keypress", function (e) {

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
                    $me.find('li').each(function (i, value) {
            height += parseInt($(this).height());
            });
                    height += '';
                    //alert(height);
                    $me.scrollTop(height); // add more 400px for #chat-box position     

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
                    setTimeout(function () {
                    $me.append($res);
                            $me.scrollTop(height + 100); // add more 500px for #chat-box position             
                    }, 1000);
            }
            }
            });
                    // END FORM CHAT

        </script>




    </body></html>