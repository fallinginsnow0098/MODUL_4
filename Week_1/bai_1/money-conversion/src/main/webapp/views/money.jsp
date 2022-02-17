<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2/17/2022
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Transform money</title>
  </head>
  <body>
  <form action="/transform" method="post">
    <h1>Chuyển đổi tiền tệ</h1>
    <label for="usd">
      USD:
    </label>
    <input type="text" name="usd" id="usd"/>
    <br>
    <label for="rate">
      Rate:
    </label>
    <input type="text" name="rate" id="rate" value="23000"/>
    <button type="submit">Chuyển đổi</button>
  </form>
  <h3>Result:
  ${result}
  </h3>
  </body>
</html>
