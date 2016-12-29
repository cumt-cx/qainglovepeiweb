<%--
  Created by IntelliJ IDEA.
  User: cumt_
  Date: 2016/12/17
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>恋皮网</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="/resource/script/demo.js" type="text/javascript"></script>
    <script src="/resource/script/json2.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/resource/css/style.css" media="screen" type="text/css"/>
    <link rel="icon" href="/resource/images/favicon.ico" mce_href="/resource/images/favicon.ico" type="image/x-icon">
</head>
<%--<body>
<div><input type = "text" id ="userName"/></div>
<div><input type = "text" id ="passWord"/></div>
<div><input type = "button" value="登入" onclick="verify();"/></div>
<div id="result"></div>
</body>--%>
<body>
<form id="login" action="/login/auth" method="post">
    <h1>Log In</h1>
    <fieldset id="inputs">
        <input id="userName" type="text" placeholder="Username" autofocus required>
        <input id="passWord" type="password" placeholder="Password" required>
    </fieldset>
    <fieldset id="actions">
        <a href='#' class='className' onclick="verify();">登 入</a>
        <%--
                <input type="button" id="submit" value="Log in" />
        --%>
    </fieldset>
</form>
<%--
<div><a href='#' class='className' onclick="getUser();">user1信息</a></div>
--%>
<div id="result"></div>
</body>
</html>
