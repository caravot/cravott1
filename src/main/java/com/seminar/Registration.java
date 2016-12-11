package com.seminar;

import com.user.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by carrie on 12/6/16.
 */
public class Registration extends HttpServlet {
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // get path that we came from
        String pathInfo = request.getServletPath();

        // common return values
        String message = "";
        String url = "/modules/ten/index.jsp";

        // verify that the com.user has filled in all form values
        if (pathInfo != null && pathInfo.contains("add")) {
            // get parameters from the request
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String empStatus = request.getParameter("empStatus");
            String[] courses = request.getParameterValues("courses");
            String[] fees = request.getParameterValues("fees");

            if (name == null || email == null || empStatus == null || courses == null || fees == null ) {
                message = "You must fill out all sections of the form.";

                request.setAttribute("message", message);
            } else {
                // set com.user variables
                User user = new User(name, email);

                // set redirect url
                url = "/modules/ten/result.jsp";

                // store the com.user object in the session
                request.getSession().setAttribute("com/user", user);
            }
        } else if (pathInfo != null && pathInfo.contains("confirm")) {
            User user = (User) request.getSession().getAttribute("com/user");

            // get parameters from the request
            String cardType = request.getParameter("card-type");
            String cardNumber = request.getParameter("card-number");
            String cardDate = request.getParameter("card-type");

            if (cardType == null || cardNumber.length() == 0 || cardDate.length() == 0) {
                message = "You must fill out all sections of the form.";

                request.setAttribute("message", message);

                // set redirect url
                url = "/modules/ten/payment.jsp";
            } else {
                String to = user.getEmail();
                String from = "carrie.peary@gmail.com";
                String subject = "Registration Information for JHU 1st Annual Software Development Seminar";
                String body = "Thanks for your registration to the JHU 1st Annual Software Development Seminar.\n\n"
                        + "You registered with the following details: \n\n"
                        + " - Name: " + user.getName() + "\n"
                        + "If you have any questions please contact us at sw-dev-com.seminar@jhu.edu.";

                System.out.println(
                        "Unable to send email. \n"
                                + "Here is the email you tried to send: \n"
                                + "=====================================\n"
                                + "TO: " + to + "\n"
                                + "FROM: " + from + "\n"
                                + "SUBJECT: " + subject + "\n\n"
                                + body + "\n\n");

                url = "/modules/ten/index.jsp";
            }
        }

        // forward request and response to the view
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    // forward all get actions to the post method
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
