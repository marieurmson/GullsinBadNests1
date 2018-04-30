package ui;

import datalayer.PostDao;
import datalayer.UniqueIdDao;
import datalayer.UserDao;
import models.PostModel;
import models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Logger;

public class ViewAPostServlet extends javax.servlet.http.HttpServlet {
    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * The post method is called by the browser when the user presses the button
     *
     * @param request The request has info on filled in fields and button presses.
     * @param response We use this to give the browser a response.
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logRequestParameters(request);  // Just to help with debugging.

        // Get data from the request. This may or may not be there
        // depending on what the user did.
        UserModel user = loadUserFromRequest(request);
        String commentText = request.getParameter("commentText");
        String buttonValue = request.getParameter("submitButton");

        // We stored the id of the story being displayed in the session in ViewStoriesServlet.
        Integer postId = (Integer) request.getSession().getAttribute("clicked postId");

        int postBeingDisplayedId = 0;
        if (postId != null) {
            postBeingDisplayedId = postId.intValue();
        }

        // Maybe the user submitted a comment.
        if (buttonValue != null && buttonValue.equals("Submit")){
            if(!commentText.equals("")) {
                addPostComment(user, postBeingDisplayedId, commentText, postBeingDisplayedId);
            }
        }

        // Show the page
        showViewPostPage(request, response, user, postBeingDisplayedId);
    }

    private void showViewPostPage(HttpServletRequest request, HttpServletResponse response, UserModel user, int postId) throws ServletException, IOException {
        PostModel post = PostDao.getPost(postId);

        // Load any data we need on the page into the request.
        request.setAttribute("user", user);
        request.setAttribute("post", post);


        loadCommentsOnPostIntoRequest(request, postId);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/viewapost.jsp");
        dispatcher.forward(request, response);
    }

    private void loadCommentsOnPostIntoRequest(HttpServletRequest request, int postId) {
        ArrayList<PostModel> postsList = PostDao.getPostsThatAreComments(postId);

        PostModel[] posts = postsList.toArray(new PostModel[postsList.size()]);
        request.setAttribute("postcomments", posts);
    }

    /**
     * Grab the username from the request and create a user model.
     */
    private UserModel loadUserFromRequest(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        UserModel user = UserDao.getUser(username);

        // If there is no user for some weird reason, just use anonymous.
        if (user == null) {
            user = new UserModel();
            user.setUsername("anonymous");
        }

        return user;
    }

    /**
     * The get method is called if the user directly invokes the URI.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Before we go the page to display the stories, we need to get the stories.
        // And then shove the stories in to the request.

        UserModel user = loadUserFromRequest(request);
        String postIdAsString =request.getParameter("postId");
        int postBeingDisplayedId = 0;
        if (postIdAsString != null) {
            postBeingDisplayedId = Integer.parseInt(postIdAsString);
        }

        showViewPostPage(request, response, user, postBeingDisplayedId);
    }

    /**
     * Retrieve all the stories and put them in the request.
     * We can then use then in the JSP file.
     *
     * @param request
     */
    private void loadPostsIntoRequest(HttpServletRequest request) {
        ArrayList<PostModel> postsList = PostDao.getPosts();

        // We're going to convert the array list to an array because it works better in the JSP.
        PostModel[] posts = postsList.toArray(new PostModel[postsList.size()]);
        request.setAttribute("posts", posts);
    }

    /**
     * Save a story.
     */
    private void addPostComment(UserModel user, int postBeingCommentedOnId, String comment, int indexNum) {
        if (comment != null && comment.length() > 0 && user != null) {
            PostDao.savePost(UniqueIdDao.getID(), comment, user.getUsername(), postBeingCommentedOnId, indexNum);
        }
    }

    /**
     * This method is useful in debugging what you got back in the
     * response from the user.
     *
     * @param request
     */
    private void logRequestParameters(HttpServletRequest request) {
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            logger.info("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
        }

    }

    private String getButtonNameGivenValue(HttpServletRequest request, String buttonValue) {
        Enumeration<String> params = request.getParameterNames();

        while(params.hasMoreElements()) {
            String paramName = params.nextElement();
            String paramValue = request.getParameter(paramName);
            if (paramValue.equals(buttonValue)) {
                return paramName;
            }
        }

        return null;
    }

}