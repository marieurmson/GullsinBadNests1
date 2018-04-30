<%--@ page import="com.endicott.edu.ui.UiMessage" %>
<%@ page import="com.endicott.edu.xxxxmodels.CollegeModel" --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Gulls in Bad Nests</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href = "resources/style.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<!--my own stylesheet -->
<link rel="stylesheet" href="styles/welcome-stylesheet.css">

</head>
<body>
<p></p>
<p></p>
<div class="container">

  <form action="welcome" method="post">
  <div class="jumbotron">
    <%-- changed the title of welcome page --%>
    <h1>Gulls in Bad Nests</h1>
    <p>Complain about your Endicott College dorm</p>
    <div class="form-group">
      <input type="text" name="username" class="form-control" id="username" placeholder="Enter username. ">
    </div>
      <%-- added password field --%>
        <div class="form-group">
          <input type="password" name="password" class="form-control" id="password" placeholder="Enter password. ">
      </div>
    <div class="container">
      <div class="buttons">
      <input type="submit" class="btn btn-info" name="button" value="Log In">
      <input type="submit" class="btn btn-info" name="button" value="Create Account">
      </div>
      <%-- removed anonymous button --%>
    </div>
  </div>

<div class="container">
  <!-- Display a message if defined -->
  <%-- if (msg.getMessage().length() > 0) { %>
  <div class="alert alert-danger">
    <strong>Info</strong> <%=msg.getMessage()%>
  </div>
  <% } --%>

</form>
</div>
</body>
</html>
