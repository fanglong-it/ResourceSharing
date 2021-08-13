/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.server;

import company.connectRecaptcha.VerifyUtils;
import company.driver.ItemDAO;
import company.driver.ItemDTO;
import company.driver.RequestDAO;
import company.driver.RequestDTO;
import company.driver.UserDAO;
import company.driver.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String MANAGE_PAGE = "managerPage.jsp";
    private static final String USER_PAGE = "employeePage.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = LOGIN_PAGE;
        try {

            HttpSession session = request.getSession();
            UserDAO userDao = new UserDAO();
            ItemDAO itemDao = new ItemDAO();
            RequestDAO requestDao = new RequestDAO();
            String userId = request.getParameter("Username");
            String password = request.getParameter("Password");
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            String errorMessage = "";
            List<ItemDTO> listItem = null;
            List<RequestDTO> listRequest = null;
            
            UserDTO user = userDao.checkLogin(userId, password);
            if (user == null) {
                errorMessage = "Invalid Username or password!";
            } else if (!user.getStatus().equals("Active")) {
                errorMessage = "The user haven't been Authentication yet";
            } else {
                if (VerifyUtils.verify(gRecaptchaResponse)) {
                    if (user.getRole().equals("AD")) {
                        listRequest = requestDao.listFirstRequest();
                        session.setAttribute("LIST_REQUEST", listRequest);
                        url = MANAGE_PAGE;
                    } else {
                        listItem = itemDao.listFirstItem();
                        session.setAttribute("FIRSTLOAD", listItem);
                        url = USER_PAGE;
                    }
                } else {
                    errorMessage = "Check againt your Re-Captcha!";
                }
            }
            session.setAttribute("USERNOTACTIVE", errorMessage);
            session.setAttribute("USER", user);
        } catch (Exception e) {
            log("ERROR at LOGINSERVLET:" + e.toString());
        } finally {
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
