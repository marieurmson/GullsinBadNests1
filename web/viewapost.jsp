<%--
  Created by IntelliJ IDEA.
  User: marieurmson
  Date: 4/8/18
  Time: 1:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="models.PostModel" %>
<%@ page import="models.UserModel" %>
<%@ page import="models.DormModel" %>
<%@ page import="models.DormData" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<title>View a Post</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="resources/style.css">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
      integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
      integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
        integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
        crossorigin="anonymous"></script>
<!--my own stylesheet -->
<link rel="stylesheet" href="styles/welcome-stylesheet.css">

</head>
<body>
<!-- Let's start by loading information we expect in the request.
     For any info missing, we'll just fake it.
  -->
<%
    UserModel user = (UserModel) request.getAttribute("user");
    if (user == null) {
        user = new UserModel();
        user.setUsername("anonymous");
    }

    PostModel posts[] = (PostModel[]) request.getAttribute("posts");
    if (posts == null) {
        posts = new PostModel[0];
    }

    PostModel comments[] = (PostModel[]) request.getAttribute("postcomments");
    if (comments == null) {
        comments = new PostModel[0];
    }
%>
<p></p>
<p></p>
<div class="container">

    <form action="viewapost" method="post">

        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <%-- MU changed nav bar --%>
                        <li class="active"><a href="viewPosts">Home</a></li>
                        <%--<li class="inactive"><a href="viewPosts">All Residence Halls</a></li>--%>
                        <div class="dropdown">
                            <button class="dropbtn">All Residence Halls
                                <i class="fa fa-caret-down"></i>
                            </button>
                            <div class="dropdown-content">
                                <%
                                    DormData g = new DormData();
                                    for(int i = 0; i < g.dorms.size(); i++){
                                        String title = g.dorms.get(i).name;%>
                                <a href="reshallpage?id=<%=title%>"><%=title%></a>
                                <%}%>
                            </div>
                        </div>
                        <%-- MU removed third part of nav bar --%>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="welcome"><span class="glyphicon glyphicon-log-out"></span>Log Out <b><%=user.getUsername()%></b></a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!--jumbotron went here -->

        <!-- Display post -->
        <div class="container">
            <div class="post-container">
            <div class="well well-sm">
                <h3><p class="text-primary">Post by <b><%=(String) request.getSession().getAttribute("author of clicked post")%>:</b>
                </h3>
                <div class="pre-scrollable">
                    <div class="post-data">
                    <%=(String) request.getSession().getAttribute("clicked post data")%>
                    </div>
                </div>
            </div>
            </div>

       <!--display comment text box and button -->
            <!-- Input for a new comment -->
            <div class="container">
                <div class="comment-container">
                <div class="row">
                    <div class="well well-sm">
                        <div class="form-group">
                            <div class="form-group">
                                <input type="text" class="form-control" id="commentText" name="commentText"
                                       placeholder="Comment on this post">
                            </div>
                            <!-- Button -->
                            <input type="submit" class="btn btn-info" name="submitButton" value="Submit">
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </div>


        <!-- Comments on a post -->
        <!-- Display a list of comments -->
        <div class="container">
            <div class="comment-container2">
            <div class="row">
                <div class="well well-sm">
                    <%//PostModel[] comments = request.getAttribute("postcomments", posts);%>
                    <h3><p class="text-primary"><%=comments.length%> Comments</h3>
                    <div class="pre-scrollable">
                        <ul class="list-group">
                            <%
                                for (int i = comments.length - 1; i >= 0; i--)
                                {
                            %>
                            <li class="list-group-item">
                                <% if (comments[i].getUsername().equals("anonymous")) { %>

                                <% } else { %>
                                Comment by <b><%=comments[i].getUsername()%>:</b>

                                <% } %>
                                <%=comments[i].getPost()%>
                            </li>
                            <%
                                }
                            %>
                        </ul>
                    </div>
                </div>
            </div>
            </div>
        </div>

        <!-- This is a screet input to the post!  Acts as if the user
             had an input field with the username.
         -->
        <input type="hidden" name="username" value="<%=user.getUsername()%>">

    </form>
</div>
</body>
</html>
