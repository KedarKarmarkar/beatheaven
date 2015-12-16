
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="BeatHeaven_Classes.*"%>  
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Email Validator</title>
    <meta name="keywords" content="music note, free template, website template, CSS, XHTML" />
    <meta name="description" content="Music Note - free CSS template provided by templatemo.com" />
    <link rel="icon" href="img1/favicon.ico">
    <title>BeatHeaven</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/carousel-vertical.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/justified-nav.css" rel="stylesheet">
    <script src="js/ie-emulation-modes-warning.js"></script> 
    <script>
       function showTable() {
                document.getElementById('table1').style.visibility = "visible";
            }
           
            function hideTable() {
                document.getElementById('table1').style.visibility = "hidden";
                document.getElementById('table2').style.visibility = "hidden";
            }
            
            function resetTables(){
                document.getElementById('table1').style.visibility = "hidden";
                    document.getElementById('table2').style.visibility = "visible";
            }
    </script>
</head>
<body onload="hideTable();">
    
    <% String button="";
        int count=0;
        count=Integer.parseInt(request.getParameter("count"));
        String random_code="##";  
        String error_msg="",msg_login="";
        String email_id="";
         Kalakar k=new Kalakar();
        if(count!=0) {
        button=request.getParameter("submit");
        if(button.equalsIgnoreCase("Verify")) {         
                    
            if(count<3) {                
                
                random_code=k.getRandomCode(request.getParameter("email")); 
                if( random_code.equals(request.getParameter("code")))  { error_msg="code matched,enter login details" ;
                error_msg= error_msg+" <input type='button' name='proceed' value='Continue' onClick='resetTables();'/> ";
//if code matches enable table2 & diable table1 
                email_id=request.getParameter("email");
                }else{
                    error_msg="Invalid code..enter again";
                    error_msg= error_msg+" <input type='button' name='proceed' value='Continue' onClick='showTable();'/> ";
                }
                count++;
            }else{
                int i= k.deleteKalakar(request.getParameter("email"));                
                error_msg="Error:"+i+" ==>Maximum no of retirals..Invalid code..Click <a href='index.jsp'><b><font color='red'>Home</font></b></a> to return"; 
               
            }
        }else if(button.equalsIgnoreCase("Submit")) {
               //code to enter value of login into db
            String user_name=request.getParameter("user_name");
            String password=request.getParameter("password");
            String cpassword=request.getParameter("confirm_password");
            String rc=request.getParameter("rc");
            String enterrc=request.getParameter("enterrc");
            int m=k.getUserNameAvailability(user_name);
            if(m==0) {
                if(password.equalsIgnoreCase(cpassword) && rc.equals(enterrc)){                   
                     email_id=request.getParameter("emailid");
                     int i= k.updateKalakarInfo(email_id,user_name , password);
                     if(i==1){
                         msg_login="Registration process completed..<a href='index.jsp'>Go Home</a>";
                         msg_login+="<br> You will Receive Email Notification for Approval of your request shortly..Thanks!!";
                         
                     }else{
                          msg_login="Error while adding login details:"+i;
                          msg_login +=" Click to <input type='button' name='continue' value='Enter Details' onClick='resetTables();'/> ";
                     }
                }else{
                    msg_login="password does not matched,try again";
                    msg_login +=" Click to <input type='button' name='continue' value='Enter Details' onClick='resetTables();'/> ";
                    email_id=request.getParameter("emailid");
                }
            }else{
                msg_login="user name already exists,try with diff name";
                    msg_login +=" Click to <input type='button' name='continue' value='Enter Details' onClick='resetTables();'/> ";
                    email_id=request.getParameter("emailid");
            }
        }
       }
       else{
            count++;
            error_msg="Click to <input type='button' name='continue' value='Enter Code' onClick='showTable();'/>";
      }
     %>
<div id="templatemo_container_wrapper">
    <div id="templatemo_container">
        <div class="temp_header" style="background: url(img1/header_back.png) repeat-x;">
            <div id="title" style="padding-top:30px; padding-left:70px;font-size:35px;color:#2b1e1e;">BEAT
            <span style="font-weight:bold;color:#c32929;">HEAVEN</span></div>
              <div id="slogan" style="padding-top:10px;padding-left:100px;font-size:15px;color:#2b1e1e;">Live The Music</div> 
        </div>        
        <div id="templatemo_content">
            <center>
                <h3>Welcome to Kalakar Registration</h3>
                <center>
                    <div>
                        <form class="form-vertical" role="form" name="codeverifierform" action="EmailValidator.jsp" method="post"><br>
                                <label><%=error_msg%></label><br>
                            <table  id="table1" >
                               
                                <tr><td>Enter Email:</td><td><input type="email" class="form-control" name="email" 
                                                                    placeholder="Enter Name"></td></tr>
                                <tr><td>Enter Code:</td><td><input type="text"  class="form-control" name="code"
                                                                      placeholder="Enter Code"></td></tr>
                                <tr><br><td><button class="btn btn-sm btn-primary btn-block" type="submit" name="submit" value="Verify">Verify</button></td>
                                    <td><button class="btn btn-sm btn-primary btn-block" type="clear">clear</button></td></tr>
                            </table>
                                <input type="hidden" name="count" value="<%=count%>"/>
                                <br><br><hr> 
                        </form>
                        <form class="form-vertical" role="form" name="logindetailsform" action="EmailValidator.jsp" method="post">
                            <label><%=msg_login %></label><br><br>
                            <table id="table2">
                                 <%  RandomNoGenerator rng=new RandomNoGenerator();
                                     long rc=rng.getRandomCode();  
                                %>
                                <tr><td></td><td><input type="hidden" class="form-control" name="rc" value="<%=rc%>"/></td></tr>
                                <tr><td></td><td><input type="text" class="form-control" name="rc1" value="<%=rc%>" style="background-color: pink;font-style: oblique;font-size: large;text-align: center; text-decoration: line-through" disabled/></td></tr>
                                <tr><td>Enter code shown above:</td><td ><input type="text" class="form-control" name="enterrc" required autofocus/></td></tr>
                                <tr><td>Enter UserName:</td><td><input type="text" class="form-control" id="user_name" name="user_name"
                                                                    placeholder="Enter Name" ></td></tr>
                                <tr><td>Enter Password:</td><td><input type="password"  class="form-control" id="password" name="password"
                                                                   placeholder="Enter Password" ></td></tr>
                                <tr><td>Confirm Password:</td><td><input type="password"  class="form-control" id="confirm_password" name="confirm_password"
                                                                       placeholder="Confirm Password" ></td></tr>
                                <tr><td><button class="btn btn-sm btn-primary btn-block" type="submit" name="submit" value="Submit" onClick="resetTables();">Submit</button></td>
                                    <td><button class="btn btn-sm btn-primary btn-block" type="clear">clear</button></td></tr>
                            </table>
                                    <input type="hidden" name="count" value="<%=count%>"/><br>
                                     <input type="hidden" name="emailid" value="<%=email_id%>"/>                                
                        </form>
                    </div>
                </center>
            </div>
        
        
    </div>
</div>
</body>
</html>