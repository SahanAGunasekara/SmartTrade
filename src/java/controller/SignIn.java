package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hibernate.HibernateUtil;
import hibernate.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

@WebServlet(name = "SignIn", urlPatterns = {"/SignIn"})
public class SignIn extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        JsonObject signIn = gson.fromJson(request.getReader(), JsonObject.class);

        JsonObject responseObject = new JsonObject();
        responseObject.addProperty("status", false);

        String email = signIn.get("email").getAsString();
        String password = signIn.get("password").getAsString();

        if (email.isEmpty()) {
            responseObject.addProperty("message", "Email can not be empty");
        } else if (!Util.isEmailValid(email)) {
            responseObject.addProperty("message", "Please enter valid email");
        } else if (password.isEmpty()) {
            responseObject.addProperty("message", "password can not be empty");
        } else {

            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session s = sf.openSession();

            Criteria criteria = s.createCriteria(User.class);

            Criterion crt1 = Restrictions.eq("email", email);
            Criterion crt2 = Restrictions.eq("password", password);

            criteria.add(crt1);
            criteria.add(crt2);

            if (criteria.list().isEmpty()) {
                responseObject.addProperty("message", "Invalid Credentials");
            } else {

                User u = (User) criteria.list().get(0);
                responseObject.addProperty("status", true);
                HttpSession ses = request.getSession();

                if (!u.getVerification().equals("Verified")) {

                    ses.setAttribute("email", email);

                    responseObject.addProperty("message", "1");
                } else {
                    ses.setAttribute("user", u);

                    responseObject.addProperty("message", "2");
                }
            }
        }
        
        String responseText = gson.toJson(responseObject);
        response.setContentType("application/json");
        response.getWriter().write(responseText);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        JsonObject responseObject = new JsonObject();
        responseObject.addProperty("status", false);
        
        if (request.getSession().getAttribute("user")!=null) {
            responseObject.addProperty("message", "1");
        }else{
            responseObject.addProperty("message", "2");
        }
        
        Gson gson = new Gson();
        String responseText = gson.toJson(responseObject);
        response.setContentType("application/json");
        response.getWriter().write(responseText);
        
    }
    
    

}
