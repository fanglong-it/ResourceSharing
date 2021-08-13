/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.server;

import company.driver.UserDAO;
import java.io.IOException;
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
@WebServlet(name = "VerifyAccountServlet", urlPatterns = {"/VerifyAccountServlet"})
public class VerifyAccountServlet extends HttpServlet {
private static final String LOGIN_PAGE = "login.jsp";
private static final String REGISTER_PAGE = "createAccount.jsp";
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
        String url = REGISTER_PAGE;
        
        try  {
            HttpSession session = request.getSession();
            String Code  = (String) session.getAttribute("CODE");
            
            String userId = request.getParameter("userId");
            String subCode = request.getParameter("subCode");
            
            String error_message = "";
            if(Code.equals(subCode)){
                UserDAO userDao = new UserDAO();
                userDao.updateStatus(userId);
                session.setAttribute("USERNOTACTIVE", null);
                url = LOGIN_PAGE;
            }else{
                error_message = "The code not match! Check your mail carefully1";
                request.setAttribute("CODE_MESSAGE_ERROR", error_message);
                url = ACTIVE_PAGE;
            }
            
           
        }catch(Exception e){
            log("Error at VerifyAccountServlet:" +e.toString());
        }finally{
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
