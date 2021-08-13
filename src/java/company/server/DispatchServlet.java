/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String SEARCH_SERVLET = "SearchServlet";
    private static final String LOGIN_SERVLET = "LoginServlet";
    private static final String LOGOUT_SERVLET = "LogoutServlet";
    private static final String REGISTER_ACCOUNT_SERVLET = "RegisterAccountServlet";
    private static final String VERIFY_SERVLET = "VerifyAccountServlet";
    private static final String REGISTER_ACCOUNT_PAGE = "createAccount.jsp";
    private static final String SEARCH_REQUEST_SERVLET = "SearchRequestServlet";
    private static final String REQUEST_HISTORY_SERVLET = "RequestHistoryServlet";
    private static final String BOOKING_SERVLET = "BookingServlet";
    private static final String LIST_ALL_ITEM_SERVLET = "ListAllItemServlet";
    private static final String ACCEPT_REQUEST_SERVLET = "AcceptServlet";
    private static final String DELETE_REQUEST_SERVLET = "DeleteServlet";
    private static final String RETURN_REQUEST_SERVLET = "ReturnServlet";
    
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
            String button = request.getParameter("btnAction");

            if (button == null) {
                url = LOGIN_PAGE;
            } else if (button.equals("Login")) {
                url = LOGIN_SERVLET;
            } else if (button.equals("Logout")) {
                url = LOGOUT_SERVLET;
            } else if (button.equals("Search")) {
                url = SEARCH_SERVLET;
            } else if (button.equals("Register")) {
                url = REGISTER_ACCOUNT_SERVLET;
            } else if (button.equals("submitCode")) {
                url = VERIFY_SERVLET;
            } else if (button.equals("RegisterPage")) {
                url = REGISTER_ACCOUNT_PAGE;
            } else if (button.equals("SearchRequest")) {
                url = SEARCH_REQUEST_SERVLET;
            } else if (button.equals("Request History")) {
                url = REQUEST_HISTORY_SERVLET;
            }else if (button.equals("Booking")){
                url = BOOKING_SERVLET;
            }else if (button.equals("List All Item")){
                url = LIST_ALL_ITEM_SERVLET;
            }else if (button.equals("Accept")){
                url = ACCEPT_REQUEST_SERVLET;
            }else if (button.equals("Delete")){
                url = DELETE_REQUEST_SERVLET;
            }else if (button.equals("Return")){
                url = RETURN_REQUEST_SERVLET;
            }

        } catch (Exception e) {
            log("Error at DispatchServlet:"+e.toString());

        } finally {

            request.getRequestDispatcher(url).forward(request, response);
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
