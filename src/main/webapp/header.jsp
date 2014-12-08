<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Authorization</title>
    <link rel="stylesheet" type="text/css" href="/Authorization/css/style.css">

</head>
<body>
<div class="main">
    <div class="header">
        <span class="title">Authorization</span>
        <% if(session.getAttribute("current_user")==null) {%>
            <span class="sign"><a class="in" href="/Authorization/sign_in">sign in</a> / <a class="up" href="/Authorization/sign_up">sign up</a></span>
        <%} else {%>
            <span class="sign"><a class="up" href="/Authorization/sign_out">sign out</a></span>
        <%}%>
    </div>
    <hr>