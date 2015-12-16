<!DOCTYPE html>
<!-- saved from url=(0047)http://getbootstrap.com/examples/justified-nav/ -->
<html lang="en">
   <head><meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img1/favicon.ico">
    <title>BeatHeaven_Home</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/carousel-vertical.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/justified-nav.css" rel="stylesheet">
    <script src="js/ie-emulation-modes-warning.js"></script> 
  </head>

  <body style="background-color:#64250E">

    <div class="container" >
        <div class="temp_header" style="background: url(img1/header_back.png) repeat-x;">
            <div id="title" style="padding-top:30px; padding-left:70px;font-size:35px;color:#2b1e1e;">BEAT
            <span style="font-weight:bold;color:#c32929;">HEAVEN</span></div>
              <div id="slogan" style="padding-top:10px;padding-left:100px;font-size:15px;color:#2b1e1e;">Live The Music</div> 
        </div>            
        <div class="masthead">
          <nav>
            <ul class="nav nav-justified" >
              <li class="active"><a href="index.jsp">Home</a></li>  
              <li><a href="AboutUs.jsp">Contact</a></li>
              <li><a href="" data-toggle="modal" data-target="#loginModal">Login</a></li>
            <li class="dropdown">
                  <a href="" class="dropdown-toggle" data-toggle="dropdown">Register<b class="caret"></b></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="" data-toggle="modal" data-target="#RegisterKalakarModal">As a Kalakar</a></li>
                    <li class="divider"></li>
                    <li><a href="" data-toggle="modal" data-target="#RegisterMaifalModal">As a Maifal</a></li>
                  </ul>
          </li>
            </ul>
          </nav>
        </div>
            <div class="modal fade" id="RegisterKalakarModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                  <div class="modal-content">
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                          <h4 class="modal-title">Register As A Kalakar</h4>
                      </div>
                      <div class="modal-body">
                          <!-- The form is placed inside the body of modal -->
                          <form class="form-horizontal" id="kregform" action="Kalakar_Registration" method="post"><br>
                          <div class="form-group">
                            <label for="name" class="col-sm-4 control-label">Enter Name:</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="kname" name="kname" placeholder="Enter Name" required autofocus>
                            </div>
                          </div>
                          <div class="form-group">
                            <label for="address" class="col-sm-4 control-label">Enter Address:</label>
                            <div class="col-sm-6">
                              <textarea  id="address" name="address" placeholder="Enter Address" required autofocus></textarea>
                            </div>
                          </div>
                          <div class="form-group">
                            <label for="city" class="col-sm-4 control-label">Enter City:</label>
                            <div class="col-sm-6">
                              <input type="text" class="form-control" id="city" name="city" placeholder="Enter City" required autofocus>
                            </div>
                          </div>
                          <div class="form-group input-group">
                            <label for="dob" class="col-sm-4 control-label">Enter DOB:</label>
                            <div class="col-sm-6">
                              <input type="text" class="form-control hasDatePicker" id="datepicker" name="dob" placeholder="YYYY/MM/DD" required autofocus>
                              <span class="input-group-addon">
                                  <i class="glyphicon glyphicon-calendar">:</i>
                              </span>
                            </div>
                          </div>
                          <div class="form-group">
                            <label for="email" class="col-sm-4 control-label">Enter Email:</label>
                            <div class="col-sm-6">
                              <input type="email" class="form-control" id="email" name="email" placeholder="Enter Email" required autofocus>
                            </div>
                          </div>
                           <div class="form-group">
                            <label for="contact" class="col-sm-4 control-label">Enter Contact:</label>
                            <div class="col-sm-6">
                              <input type="text" size="10" class="form-control" id="contact" name="contact" placeholder="Enter Contact" required autofocus>
                            </div>
                          </div>
                           <div class="form-group">
                            <label for="interest" class="col-sm-4 control-label">Enter Interests:</label>
                            <div class="col-sm-6">
                              <input type="text" class="form-control" id="interest" name="interest" placeholder="Enter Interest" required autofocus>
                            </div>
                          </div>
                           <div class="form-group">
                            <label for="instruments" class="col-sm-4 control-label">Playing Instruments:</label>
                            <div class="col-sm-6">
                              <input type="text" class="form-control" id="instruments" name="instruments" placeholder="Enter Playing Instruments(if any)" required autofocus>
                            </div>
                          </div>
                          <div class="form-group">
                            <label for="occupation" class="col-sm-4 control-label">Enter Occupation:</label>
                            <div class="col-sm-6">
                              <input type="text" class="form-control" id="occupation" name="occupation" placeholder="Enter Your Occupation" required autofocus>
                            </div>
                          </div>
                          <div class="form-group">
                            <label for="gender" class="col-sm-4 control-label">Select Gender:</label>
                            <div class="col-sm-6">
                              <div class="btn-group" data-toggle="buttons">
                               <label class="btn btn-primary">
                                   <input type="radio" name="gender" value="male"> Male
                               </label>
                               <label class="btn btn-primary">
                                   <input type="radio" name="gender" value="female"> Female
                               </label>
                           </div>
                            </div>
                          </div>
                               <div class="form-group col-sm-12"><center>
                                    <textarea  name="terms" class="form-control"  disabled style="margin-left: 10px">Terms & Conditions:Posting of any copyrighted prevented or illegal information through this site/application is strictly prohibited.If posted then it will be fully that users/institutes responsibility who is going to post it,site admin will not be responsible for such cases.</textarea></center>
                            </div>  
                              <div class="form-group col-sm-12"><center>
                                      <input type="checkbox" required autofocus name="chkTerms">&nbsp;&nbsp;I have read all terms & condition and I accept it</center>
                            </div>
                              <hr class="style-two">
                          <div class="form-group">
                            <div class="col-sm-offset-4 col-sm-5">
                              <button type="submit" class="btn btn-info">Submit</button>
                              <button type="reset" class="btn btn-info">Cancel</button>
                            </div>
                          </div>
                        </form>
                      </div>
                  </div>
              </div>
            </div>
            <div class="modal fade" id="RegisterMaifalModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                  <div class="modal-content">
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                          <h4 class="modal-title">Register As A Maifal</h4>
                      </div>
                      <div class="modal-body">
                          <!-- The form is placed inside the body of modal -->
                          <form class="form-horizontal" id="mregform" action="Maifal_Registration" method="post"><br>
                          <div class="form-group">
                            <label for="name" class="col-sm-4 control-label">Enter Name:</label>
                            <div class="col-sm-6">
                              <input type="text" class="form-control" id="name" name="mname" placeholder="Enter Name" required autofocus>
                            </div>
                          </div>
                          <div class="form-group">
                            <label for="address" class="col-sm-4 control-label">Enter Address:</label>
                            <div class="col-sm-6">
                              <textarea  name="address" placeholder="Enter Address"  required autofocus></textarea>
                            </div>
                          </div>
                          <div class="form-group">
                            <label for="city" class="col-sm-4 control-label">Enter City:</label>
                            <div class="col-sm-6">
                              <input type="text" class="form-control" id="city" name="city" placeholder="Enter City" required autofocus>
                            </div>
                          </div>
                          <div class="form-group">
                            <label for="dob" class="col-sm-4 control-label">Enter Estd. Date:</label>
                            <div class="col-sm-6">
                              <input type="text" class="form-control" id="dob" name="edate" placeholder="YYYY/MM/DD" required autofocus>
                            </div>
                          </div>
                           <div class="form-group">
                            <label for="interest" class="col-sm-4 control-label">Enter Founder Name:</label>
                            <div class="col-sm-6">
                              <input type="text" class="form-control" id="founder" name="founder" placeholder="Enter Founder Name" required autofocus>
                            </div>
                          </div>
                           <div class="form-group">
                            <label for="instruments" class="col-sm-4 control-label">Enter Activities:</label>
                            <div class="col-sm-6">
                              <input type="text" class="form-control" id="instruments" name="activities" placeholder="Enter Activities" required autofocus>
                            </div>
                          </div>
                          <div class="form-group">
                            <label for="email" class="col-sm-4 control-label">Enter Email:</label>
                            <div class="col-sm-6">
                              <input type="email" class="form-control" id="email" name="email" placeholder="Enter Email" required autofocus>
                            </div>
                          </div>
                           <div class="form-group">
                            <label for="contact" class="col-sm-4 control-label">Enter Contact:</label>
                            <div class="col-sm-6">
                              <input type="text" size="10" class="form-control" id="contact" name="contact" placeholder="Enter Contact" required autofocus>
                            </div>
                            </div>
                          <div class="form-group">
                            <label for="occupation" class="col-sm-4 control-label">Enter RegId:</label>
                            <div class="col-sm-6">
                              <input type="text" class="form-control" id="regId" name="regId" placeholder="Enter RegId" required autofocus>
                            </div>
                          </div>
                            <div class="form-group col-sm-12"><center>
                                    <textarea  name="terms" class="form-control"  disabled style="margin-left: 10px">Terms & Conditions:Posting of any copyrighted prevented or illegal information through this site/application is strictly prohibited.If posted then it will be fully that users/institutes responsibility who is going to post it,site admin will not be responsible for such cases.<br>In case of institute registration the access will provided only after full payment of registration fees.For payment mode & details please mail to beatHeaven_admin@gmail.com.</textarea></center>
                            </div>  
                              <div class="form-group col-sm-12"><center>
                                      <input type="checkbox" required autofocus name="chkTerms">&nbsp;&nbsp;I have read all terms & condition and I accept it</center>
                            </div>
                           
                          <hr class="style-two">
                          <div class="form-group">
                            <div class="col-sm-offset-4 col-sm-6">
                              <button type="submit" class="btn btn-info">Submit</button>
                              <button type="reset" class="btn btn-info">Cancel</button>
                            </div>
                          </div>
                        </form>
                      </div>
                  </div>
              </div>
            </div>
            <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                  <div class="modal-content">
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                          <h4 class="modal-title">Login</h4>
                      </div>

                      <div class="modal-body">
                          <!-- The form is placed inside the body of modal -->
                          <form id="loginForm" method="post" action="LoginServlet" class="form-horizontal" ><br>
                              <div class="form-group">
                                   <label class="col-xs-3 control-label">Username</label>
                                  <div class="col-xs-5">
                                      <input type="text" class="form-control" name="un" required autofocus/>
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label class="col-xs-3 control-label">Password</label>
                                  <div class="col-xs-5">
                                      <input type="password" class="form-control" name="ps" required autofocus/>
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label class="col-xs-3 control-label">Select Type</label>
                                  <div class="col-xs-5">
                                      <div class="btn-group" data-toggle="buttons">
                                                          <label class="btn btn-primary">
                                                            <input type="radio" name="ut"  value="kalakar"> As a Kalakar
                                                          </label>
                                                          <label class="btn btn-primary">
                                                            <input type="radio" name="ut"  value="maifal"> As a Maifal
                                                          </label>
                                      </div>              
                                  </div>
                              </div>
                              <div class="form-group">
                                  <div class="col-xs-6 col-xs-offset-4">
                                      <button type="submit" class="btn btn-info">Login</button>
                                  </div>
                              </div>
                               <div class="form-group">
                                  <div class="col-xs-6 col-xs-offset-4">
                                      <a href="ForgotPassword.jsp?msg=none">Forgot Password?</a>
                                  </div>
                              </div>
                          </form>
                      </div>
                  </div>
              </div>
            </div>
            <div class="modal fade" id="welcomeModal" tabindex="-1" role="dialog" aria-labelledby="Welcome back" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <h4>Welcome back, <span class="username"></span></h4>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
      <!-- Example row of columns -->
        <div class="row">
        <div class="col-md-12">
                    <div id="carousel-example-generic-1" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic-1" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic-1" data-slide-to="1" ></li>
                            <li data-target="#carousel-example-generic-1" data-slide-to="2"></li>
                            <li data-target="#carousel-example-generic-1" data-slide-to="3"></li>
                            <li data-target="#carousel-example-generic-1" data-slide-to="4"></li>
                            <li data-target="#carousel-example-generic-1" data-slide-to="5"></li>
                            <li data-target="#carousel-example-generic-1" data-slide-to="6"></li>
                            <li data-target="#carousel-example-generic-1" data-slide-to="7"></li>
                            <li data-target="#carousel-example-generic-1" data-slide-to="8"></li>
                            <li data-target="#carousel-example-generic-1" data-slide-to="9"></li>
                        </ol>
                        <center>
                            <!-- Wrapper for slides -->
                            <div class="carousel-inner">
                                <div class="item ">
                                    <a href="#whoswho.jsp">  <img src="img1/img1.jpg" alt="Company"></a>
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="img1/img2.jpg" alt="Company">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="img1/img3.jpg" alt="Company">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                <div class="item active">
                                    <img src="img1/img4.jpg" alt="Company">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="img1/img5.jpg" alt="Company">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="img1/img6.png" alt="Company">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="img1/img7.jpg" alt="Company">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="img1/img8.jpg" alt="Company">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="img1/img9.jpg" alt="Company">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="img1/img10.jpg" alt="Company">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                            </div>
                        </center>
                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic-1" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic-1" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right"></span>
                        </a> 
                    </div>
                </div>
        </div>
      
        <div class="row" >
            <div class="col-lg-12">
            <div class="col-lg-4 panel panel-primary carousel slide" data-ride="carousel"  id="carousel-example-generic-1">
                
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic-1" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic-1" data-slide-to="1" ></li>
                            <li data-target="#carousel-example-generic-1" data-slide-to="2"></li>
                            <li data-target="#carousel-example-generic-1" data-slide-to="3"></li>
                        </ol>
                        <center>
                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" >
                                <div class="item ">
                                    <img src="img1/img4.jpg" alt="Company" style="height: 250px">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="img1/img2.jpg" alt="Company" style="height: 250px">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="img1/img3.jpg" alt="Company" style="height: 250px">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                <div class="item active">
                                    <img src="img1/img9.jpg" alt="Company" style="height: 250px">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                
                            </div> 
                        </center>
                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic-1" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic-1" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right"></span>
                        </a> 
                    
            </div>
            <div class="col-lg-4 panel panel-primary" style="height: 250px">                
                <center>
                    <h3>About Us</h3>
                                      
                    <p> The application is developed specifically for all musician and related people & institutes. The purpose is to keep all such artist and musical institutes closer, share their ideas, and keep them updated regarding musical events and manage the various musical activities & working of musical institutes.</p></center>
                  
                
            </div>
            
                <div class="col-lg-4 panel panel-primary carousel slide" data-ride="carousel" style="height: 250px" id="carousel-example-generic-1">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic-1" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic-1" data-slide-to="1" ></li>
                            <li data-target="#carousel-example-generic-1" data-slide-to="2"></li>
                            <li data-target="#carousel-example-generic-1" data-slide-to="3"></li>
                        </ol>
                        <center>
                            <!-- Wrapper for slides -->
                            <div class="carousel-inner">
                                <div class="item ">
                                    <img src="img1/img5.jpg" alt="Company" style="height: 250px">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="img1/img8.jpg" alt="Company" style="height: 250px">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="img1/img7.jpg" alt="Company" style="height: 250px">
                                    <div class="carousel-caption">
                                    </div>
                                </div>
                                <div class="item active">
                                    <img src="img1/img10.jpg" alt="Company" style="height: 250px">
                                    <div class="carousel-caption">
                                    </div>
                                </div>                                
                            </div>
                        </center>
                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic-1" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic-1" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right"></span>
                        </a> 
                    </div>
                  
          </div>
          <div class="col-lg-12 text-center"> 
            
              <p><font color="Black"><b>DevelopedBy:&nbsp;</b></font><font color="white"><B>©NK Developers</b></font></p>
            
          </div>
      <!-- Site footer -->
      
        </div>
    </div> <!-- /container -->
    <script src="js/ie10-viewport-bug-workaround.js">
    </script>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/framework/formValidation.min.js"></script>
    <script src="js/framework/bootstrap.min.js"></script>
    <script src="js/framework/app.js"></script>
   <script src="js/framework/share.min.js"></script>
  

    <script>
    $(document).ready(function() {
    $('#loginForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: ':disabled',
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: 'The username is required'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required'
                    }
                }
            },
            usertype: {
                validators: {
                    notEmpty: {
                        message: 'The type is required'
                    }
                }
            }
        }
    });
});
</script>
<script>
$(document).ready(function() {
    $('#loginForm').on('success.form.fv', function(e) {
        // Prevent form submission
        e.preventDefault();

        var validator = $(e.target).data('formValidation');
        $('#loginModal')
            .one('hidden.bs.modal', function() {
                $('#welcomeModal')
                    .find('.username')
                        .html(validator.getFieldElements('username').val()).end()
                    .modal('show');
            document.getElementById("loginForm").submit();
            })
            .modal('hide');
    });

    $('#loginModal').on('shown.bs.modal', function() {
        $('#loginForm').formValidation('resetForm', true)
                       .find('[name="username"]').focus();
    });
});
</script>
<script>
$(document).ready(function() {
$('#kregform').formvalidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: ':disabled',
        fields: {
            kname:      {
                                validators: {
                                    notEmpty: { message: 'The name is required' },
                                    stringLength: { min: 6,max: 30,message: 'The username must be more than 6 and less than 30 characters long' },
                                    regexp: {regexp: /^[a-zA-Z]+$/,message: 'The username can only consist of alphabetical, number, dot and underscore'}
                                }
                        },
            address:    {
                            validators: {
                                notEmpty: {message: 'The address is required'},
                                regexp: {regexp: /^[a-zA-Z]+$/,message: 'The username can only consist of alphabetical'}
                            }
                        },
            city:       {
                            validators: {
                                notEmpty: {message: 'The city is required'},
                                regexp: {regexp: /^[a-zA-Z]+$/,message: 'The username can only consist of alphabetical'}
                            }
                        },
            dob:        {
                            validators: {
                                date: {format: 'YYYY/MM/DD',message: 'The birthday is not valid'}
                            }
                        },
            email:      {
                            validators: {
                                emailAddress: {message: 'The input is not a valid email address'}
                            }
                        },
            contact:    {
                            validators: {
                                integer: {message: 'The value is not an integer'},
                                notEmpty: {message: 'The city is required'}
                            }
                        },
            interest:    {
                            validators: {
                                string:  {message: 'The value is not an string'},
                                notEmpty: {message: 'The city is required'}
                            }
                        },
            instruments:{
                            validators: {
                                string:  {message: 'The value is not an string'},
                                notEmpty: {message: 'The city is required'}
                            }
                        },
            occupation: {
                            validators: {
                                string:  {message: 'The value is not an string'},
                                notEmpty: {message: 'The city is required'}
                            }
                        },
            
            gender:     {
                            validators: { 
                                notEmpty: {message: 'The gender is required'}
                            }
                        }
                }

 
    });  
}); 
</script>


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  
</body>

</html>