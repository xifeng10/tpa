<!DOCTYPE html>
<html
        xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Spring Security Example </title>
</head>
<body>

<#if (param.error)??>
用户名或密码错
</#if>
<#if (param.logout)??>
您已注销成功
</#if>

<form action="${login!'login'}" method="post">
    <div><label> 用户名 : <input type="text" name="username" value="user"/> </label></div>
    <div><label> 密 码 : <input type="password" name="password" value="password"/> </label></div>
    <div><input type="submit" value="登录"/></div>
    <input type="hidden" name="${_csrf.parameterName!''}" value="${_csrf.token!''}"/>
</form>
</body>
</html>