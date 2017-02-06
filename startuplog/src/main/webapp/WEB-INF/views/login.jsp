<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- let's add tag srping:url -->
<spring:url value="/resources/css/styles.css" var="startupLogCSS" />
<spring:url value="/resources/js/jquery-3.1.1.min.js" var="startupLogJS" />
<link href="${startupLogCSS}" rel="stylesheet" />
<script src="${startupLogJS}"></script>
<title>StartupLog</title>
</head>
<body>
      <div class="container">
          <div id="login-form" class="form">
              <div class="thumbnail">
                  <img src="resources/images/StartupLog_logo.png">
              </div>
              <form action="/login" method="POST">
                  <input class="form_input" placeholder="username" type="text">
                  <input class="form_input" placeholder="password" type="password">
                  <button class="form_button">login</button>
              </form>
          </div>
      </div>
</body>
</html>