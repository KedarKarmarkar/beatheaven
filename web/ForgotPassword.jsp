<%-- 
    Document   : ForgotPassword
    Created on : Jun 4, 2015, 8:54:11 PM
    Author     : GlobeOp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recover Password</title>
    </head>
    <body><br><br><br>
    <center>
         <form id="RecoverPassword" method="post" action="RecoverPassword" class="form-horizontal" ><br>
              <div class="form-group">
                                  
                  <label class="col-xs-8 control-label"><%=request.getParameter("msg")%></label>
                                 
                              </div>  
                              <div class="form-group">
                                  
                                   <label class="col-xs-3 control-label">Enter Registered Email</label>
                                  <div class="col-xs-5">
                                      <input type="email" class="form-control" name="email" required autofocus/>
                                  </div>
                              </div>                              
                              <div class="form-group">
                                  <label class="col-xs-3 control-label">Select Type</label>
                                  <div class="col-xs-5">
                                      <div class="btn-group" data-toggle="buttons">
                                                          <label class="btn btn-primary">
                                                            <input type="radio" name="ut" id="kalakar" value="kalakar"> As a Kalakar
                                                          </label>
                                                          <label class="btn btn-primary">
                                                            <input type="radio" name="ut" id="maifal" value="maifal"> As a Maifal
                                                          </label>
                                      </div>              
                                  </div>
                              </div>
                              <div class="form-group">
                                  <div class="col-xs-6 col-xs-offset-4">
                                      <button type="submit" class="btn btn-info">Send Code</button>
                                  </div>
                              </div>                              
                          </form>
    </center>
    </body>
</html>
