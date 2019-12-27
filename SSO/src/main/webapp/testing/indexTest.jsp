<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>funziona?</title>
  </head>
  <body>
  <p>Ciao, ${name}</p>
  <form action="LoginServlet" method="post">
    Email: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" value="Log in bro">
  </form>
  </body>
</html>
