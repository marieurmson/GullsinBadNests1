<%@ page import="models.PostModel" %>
<%@ page import="models.UserModel" %>
<%@ page import="models.DormModel" %>
<%@ page import="models.DormData" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<title>Recent Complaints</title>
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

    <form action="viewPosts" method="post">

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
                                <div class="dropdown-content">
                                    <%
                                        DormData g = new DormData();
                                    for(int i = 0; i < g.dorms.size(); i++){
                                        String title = g.dorms.get(i).name;%>
                                    <a href="reshallpage?id=<%=title%>"><%=title%></a>
                                    <%}%>
                                </div>
                            </div>
                        <%-- removed third part of nav bar --%>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="welcome"><span class="glyphicon glyphicon-log-out"></span>Log Out <b><%=user.getUsername()%></b></a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Display the jumbotron -->
        <div class="gulllogo">
            <img src="images/gulllogo.png" alt="Gull Logo">
        </div>
        <div class="jumbotron">
            <%-- changed the jumbotron text --%>
            <h1>Gulls in Bad Nests</h1>
            <p>Complain about your Endicott College dorm</p>


        </div>

        <!-- Display a list of posts -->
        <div class="container">
            <div class="row">
                <div class="well well-sm">
                    <%
                    ArrayList<PostModel> nonComment = new ArrayList();
                    for(int i = posts.length - 1; i >=0; i--){
                        if(posts[i].getCommentOnPostID() == 0){
                            nonComment.add(posts[i]);

                        }
                    }%>
                    <h3><p class="text-primary"><%=nonComment.size()%> Posts</h3>
                    <div class="pre-scrollable">
                        <ul class="list-group">
                            <%
                                for (int i = posts.length - 1; i >= 0; i--) {
                                    if(posts[i].getCommentOnPostID() != 0){
                                        continue;
                                    }
                            %>
                            <li class="list-group-item"><b>[<%=posts[i].getUsername()%>]</b> is talking about <b><%=posts[i].getDormByIndex(posts[i].getDormIndex())%>:</b>  <%=posts[i].getPost()%>
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
