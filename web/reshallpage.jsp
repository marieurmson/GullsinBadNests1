<%--
  Created by IntelliJ IDEA.
  User: marieurmson
  Date: 3/4/18
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="models.PostModel" %>
<%@ page import="models.UserModel" %>
<%@ page import="models.DormModel" %>
<%@ page import="models.DormData" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="static jdk.nashorn.internal.objects.NativeFunction.function" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<title><%=request.getAttribute("dormID")%></title>
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
%>
<p></p>
<p></p>
<div class="container">


    <form action="reshallpage?id=<%=request.getAttribute("dormID")%>" method="post">

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
                        <%-- changed nav bar --%>
                        <li class="active"><a href="viewPosts">Home</a></li>
                        <div class="dropdown">
                            <button class="dropbtn">All Residence Halls
                                <i class="fa fa-caret-down"></i>
                            </button>
                            <%String title;%>
                            <div class="dropdown-content">
                                <%
                                    DormData g = new DormData();
                                    for(int i = 0; i < g.dorms.size(); i++){
                                        title = g.dorms.get(i).name;%>
                                <a href="reshallpage?id=<%=title%>"><%=title%></a>
                                <%}%>

                            </div>
                        </div>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="welcome"><span class="glyphicon glyphicon-log-out"></span>Log Out <b><%=user.getUsername()%></b></a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Display the jumbotron -->
        <div class="reshallpic">
            <img src="images/<%=request.getAttribute("dormID")%>.jpg" alt="The Hall" height="180" width="240">
        </div>
        <div class="jumbotron">
            <h1><%=request.getAttribute("dormID")%></h1>


        </div>
        <!-- Input for a new post -->
        <div class="container">
            <div class="row">
                <div class="well well-sm">
                    <div class="form-group">
                        <div class="form-group">
                            <input type="text" class="form-control" id="postText" name="postText"
                                   placeholder="What's your complaint?">
                        </div>
                        <!-- Button -->
                        <input type="submit" class="btn btn-info" name="submitButton" value="Submit">
                    </div>
                </div>
            </div>
        </div>
        <%-- end of input for a new post --%>

        <!-- Display a list of posts -->
        <div class="container">
            <div class="row">
                <div class="well well-sm">
                    <% DormModel currentDorm = new DormModel((String)request.getAttribute("dormID"));
                        currentDorm.allPosts = (ArrayList)request.getAttribute("specific dorm post array");
                    %>
                            <h3><p class="text-primary"><%=currentDorm.allPosts.size()%> Posts</h3>
                    <div class="pre-scrollable">
                        <ul class="list-group">
                            <%
                                for (int i = currentDorm.allPosts.size() - 1 ; i >= 0; i--) {
                                    if(currentDorm.allPosts.get(i).getCommentOnPostID() != 0){
                                        continue;
                                    }

                            %>
                            <li class="list-group-item"><b>[<%=currentDorm.allPosts.get(i).getUsername()%>]:</b>  <%=currentDorm.allPosts.get(i).getPost()%>
                                <input type="submit" class="btn btn-info" name="<%=currentDorm.allPosts.get(i).getPostId()%>" value="View">
                            </li>
                            <%
                                    }
                            %>
                        </ul>
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
