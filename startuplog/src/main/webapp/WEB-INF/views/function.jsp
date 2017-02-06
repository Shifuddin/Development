<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<spring:url value="/resources/css/styles.css" var="startupLogCSS" />
<spring:url value="/resources/js/jquery-3.1.1.min.js" var="startupLogJS" />
<link href="${startupLogCSS}" rel="stylesheet" />
<script src="${startupLogJS}"></script>
<title>StartupLog</title>
</head>
<body>
<h2>Your Stage: ${stage} </h2>
<h3>Funtion will be loaded here </h3>
</body>
</html>