/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.server;

import company.driver.ReceiveMail;
import company.driver.RegistrationErrors;
import company.driver.UserDAO;
import company.driver.UserDTO;
import company.util.MyUtils;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "RegisterAccountServlet", urlPatterns = {"/RegisterAccountServlet"})
public class RegisterAccountServlet extends HttpServlet {

    private static final String CREATE_ACCOUNT_PAGE = "createAccount.jsp";
    private static final String ACTIVE_PAGE = "activePage.jsp";

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

        String url = CREATE_ACCOUNT_PAGE;

        try {
            String Username = request.getParameter("txtUserName");
            String Password = request.getParameter("txtPassword");
            String RePassword = request.getParameter("txtRePassword");
            String PhoneNumber = request.getParameter("txtPhoneNumber");
            String Name = request.getParameter("txtName");
            String Address = request.getParameter("txtAddress");
            HttpSession session = request.getSession();

            boolean errs = false;
            RegistrationErrors errObj = new RegistrationErrors();

            if (Username.length() < 6 || Username.length() > 30) {
                errs = true;
                errObj.setUsernameErrs("Username must be 6 to 30 character");
            }
            if (Password.length() < 5 || Password.length() > 20) {
                errs = true;
                errObj.setPasswordErrs("Password must be 5 to 20 character");

            } else if (!RePassword.equals(Password)) {
                errs = true;
                errObj.setRePasswordErrs("RePassword must match the Password");
            }

            final String PHONE_REGEX = "[0-9]{9,10}";
            if (!PhoneNumber.matches(PHONE_REGEX)) {
                errs = true;
                errObj.setPhoneNumberErrs("Phone number must be 10 digit");
            }

            if (Name.length() < 2 || Name.length() > 50) {
                errs = true;
                errObj.setNameErrs("Name must be 2 to 50 character");
            }

            if (Address.length() < 2 || Address.length() > 50) {
                errs = true;
                errObj.setAddressErrs("Address must be 2 to 50 character");
            }

            request.setAttribute("ERRORS", errObj);
            url = CREATE_ACCOUNT_PAGE;

            if (errs == false) {
                UserDAO userDao = new company.driver.UserDAO();
                boolean checkDup = userDao.checkDuplicateUserName(Username);
                if (checkDup) {
                    String msg = Username + " Present in DB";
                    request.setAttribute("DUPLICATE", msg);
                } else {
                    UserDTO user = new UserDTO(Username, Password, PhoneNumber, Name, Address, "", "US", "New");
                    boolean result = userDao.createAccount(user);
                    if (result) {
                        session.setAttribute("ACTIVE_ACCOUNT", Username);
                        MyUtils util = new MyUtils();
                        String code = util.randomAlphaNumeric(8);
                        session.setAttribute("CODE", code);
                        ReceiveMail.sendText(code, Username);
                        url = ACTIVE_PAGE;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at RegisterAccountServlet: "+e.toString());

        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
