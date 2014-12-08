package kz.sayan.servlet;

import kz.sayan.config.HibernateUtil;
import kz.sayan.config.MD5;
import kz.sayan.entity.User;
import kz.sayan.service.Service;
import org.hibernate.Session;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Sayan.Zhumashev
 * Date: 7/16/14
 * Time: 5:36 PM
 */
@WebServlet("/sign_up")
public class SignUpServlet extends HttpServlet {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]*@"
                    + "[A-Za-z0-9-]*(\\.[A-Za-z]{2,})$";
    private Service service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = new Service();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=null, email=null, password=null;
        HttpSession h_session = request.getSession();
        int valid = 0;

        if(h_session.getAttribute("current_user")!=null){
            response.sendRedirect("/Authorization");
            return;
        }

        if(request.getParameter("request")==null){
            response.sendRedirect("/Authorization/sign_up.jsp");
            return;
        }

        if(isValid(request.getParameter("su_name"))){
            name = request.getParameter("su_name");
            h_session.setAttribute("n_error", null);
            h_session.setAttribute("su_name", name);
            ++valid;
        }
        else{
            h_session.setAttribute("n_error", "name is incorrect");
        }

        if(isValid(request.getParameter("su_email")) && isEmail(request.getParameter("su_email"))){
            email = request.getParameter("su_email");
            h_session.setAttribute("su_email", email);

            if(service.checkEmail(email)==0){
                h_session.setAttribute("e_error", null);
                ++valid;
            }
            else{
                h_session.setAttribute("e_error", "email already exist");
            }
        }
        else
            h_session.setAttribute("e_error", "email is incorrect");

        if(isValid(request.getParameter("su_password"))){
            password = request.getParameter("su_password");
            h_session.setAttribute("su_password", password);
            h_session.setAttribute("p_error", null);
            ++valid;
        }
        else
            h_session.setAttribute("p_error", "password is incorrect");

        if(valid==3){
            h_session.setAttribute("su_name", null);
            h_session.setAttribute("su_email", null);
            h_session.setAttribute("su_password", null);
            h_session.setAttribute("n_error", null);
            h_session.setAttribute("e_error", null);
            h_session.setAttribute("p_error", null);

            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(MD5.hash(password));
            h_session.setAttribute("current_user", service.saveUser(user));
            response.sendRedirect("/Authorization/success.jsp");
        }
        else{
            response.sendRedirect("/Authorization/sign_up.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private Boolean isValid(String value) {
        if(value==null)
            return false;
        if(value.trim().toString().length()>0)
            return true;
        return false;
    }

    private Boolean isEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }

}
