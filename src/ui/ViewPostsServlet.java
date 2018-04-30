package ui;

import datalayer.PostDao;
import datalayer.UniqueIdDao;
import datalayer.UserDao;
import models.PostModel;
import models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Logger;

public class ViewPostsServlet extends javax.servlet.http.HttpServlet {
    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * The post method is called by the browser when the user presses the button
     *
     * @param request The request has info on filled in fields and button presses.
     * @param response We use this to give the browser a response.
     * @throws javax.servlet.ServletException
     * @throws IOException
     */
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        logRequestParameters(request);  // Just to help with debugging.

        // Get data from the request
        UserModel user = loadUserFromRequest(request);
        String postText=request.getParameter("postText");
        String buttonValue = request.getParameter("submitButton");
        String dormID = request.getParameter("id");
        //make a new string with the id

        // Load any data we need on the page into the request.
        request.setAttribute("user", user);
        // set a new attribute of the dorm name
        request.setAttribute("dormID", dormID);
        loadPostsIntoRequest(request);

        // Show the page
        RequestDispatcher dispatcher=request.getRequestDispatcher("/viewposts.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Grab the username from the request and create a user model.
     */
    private UserModel loadUserFromRequest(HttpServletRequest request) {
        String username= (String) request.getSession().getAttribute("username");
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
     * @throws javax.servlet.ServletException
     * @throws IOException
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        // Before we go the page to display the stories, we need to get the stories.
        // And then shove the stories in to the request.
        String username = (String) request.getSession().getAttribute("username");
        String dormID = request.getParameter("id");

        UserModel user = loadUserFromRequest(request);
        request.setAttribute("user", user);

        request.setAttribute("dormID", dormID);
        loadPostsIntoRequest(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewposts.jsp");
        dispatcher.forward(request, response);


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
     * This method is useful in debugging what you got back in the
     * response from the user.
     *
     * @param request
     */
    private void logRequestParameters(javax.servlet.http.HttpServletRequest request) {
        Enumeration<String> params = request.getParameterNames();
        while(params.hasMoreElements()){
            String paramName = params.nextElement();
            logger.info("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
        }
    }

}
