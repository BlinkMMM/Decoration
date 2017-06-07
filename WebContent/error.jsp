<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.io.*"%>
<%
    response.setStatus(HttpServletResponse.SC_OK);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
    <head>
        <title>错误页面</title>
        <script type="text/javascript" src="./javascript/jquery-1.5.1.min.js"></script>
        <script>
            function showErrorMessage(){
                $("#errorMessageDiv").toggle();
            }
            $(document).ready(showErrorMessage);
        </script>
    </head>
    <body>
        <table width="100%">
            <tr>
                <td style="border-bottom:dotted 1px Gray;" colspan="2" >
                    <img src="images/error_title_icon.gif" id="img1" />&nbsp;&nbsp;错误提示                               
                </td><td></td>
            </tr>
            <tr>
                <td style="width: 130px" >
                    <img src="images/sorry.gif" id="error_img" />
                </td>
                <td>尊敬的用户：<br />系统出现了异常，请重试。
                    <br />如果问题重复出现，请向系统管理员反馈。<br /><br />
                    <a id="showErrorMessageButton" href="javascript:showErrorMessage();">详细错误信息</a>
                </td>
            </tr>
        </table>
        <div id="errorMessageDiv" >
            <pre>
                
            </pre>
        </div>
    </body>
</html>