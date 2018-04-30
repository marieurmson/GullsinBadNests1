package ui;

import datalayer.UserDao;
import models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

public class WelcomeServlet extends javax.servlet.http.HttpServlet {
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
        UserModel user = null;

        // Load data from the request
        String buttonValue = request.getParameter("button");
        String username=request.getParameter("username");
        String enteredPassword=request.getParameter("password");
        String validUsername;
        String validPassword;
        String through = "no";


        // Create an account
        if (buttonValue != null && buttonValue.equals("Create Account") && username != null){

            //if they enter nothing, do nothing
            if(username.equals("") || enteredPassword.equals("")){
                RequestDispatcher dispatcher=request.getRequestDispatcher("/welcome.jsp");
                dispatcher.forward(request, response);
                return;
            }
            //if they enter an empty username, create a new user
            try {
                user = UserDao.getUser(username);
                validUsername = user.getUsername();
                validPassword = user.getPassword();
            }
            catch(Exception e){
                user = new UserModel();
                user.setUsername(username);
                //added password attaching code
                user.setPassword(enteredPassword);
                UserDao.saveUser(user);
                through = "yes";
            }
            //if they enter an already taken username, they stay where they are
            if(through.equals("no")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.jsp");
                dispatcher.forward(request, response);
                return;
            }

        }

        // Or log in
        else if (buttonValue != null && buttonValue.equals("Log In")){

            //try to find the user by username, if the user is null catch the exception
            try {
                user = UserDao.getUser(username);
                validUsername = user.getUsername();
                validPassword = user.getPassword();
            }
            catch(Exception e){
                RequestDispatcher dispatcher=request.getRequestDispatcher("/welcome.jsp");
                dispatcher.forward(request, response);
                return;
            }



            // first validate that entered values are good.
            if (user == null || user.equals("")) {
                // We don't know who this is.
                // We're going to stay on this page.
                RequestDispatcher dispatcher=request.getRequestDispatcher("/welcome.jsp");
                dispatcher.forward(request, response);
                return;
            }
            if(enteredPassword.equals(null) || enteredPassword.equals("")){
                RequestDispatcher dispatcher=request.getRequestDispatcher("/welcome.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // at this point, we know we have values entered and the user value is on file.
            // continue with the password test.
            // incorrect password was entered. we stay on this page.
            if(!(validPassword.equals(enteredPassword))){
                RequestDispatcher dispatcher=request.getRequestDispatcher("/welcome.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }

        request.getSession().setAttribute("username", user.getUsername());

        // Load any data we need on the page into the request.
        request.setAttribute("user", user);

        // Show the stories page
        RequestDispatcher dispatcher=request.getRequestDispatcher("/viewPosts");
        dispatcher.forward(request, response);
    }

    /**
     * The get method is invoked when the user goes to the page by browser URI.
     */
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.jsp");
        dispatcher.forward(request, response);
    }

}
