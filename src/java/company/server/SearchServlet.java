/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.server;

import company.driver.ItemDAO;
import company.driver.ItemDTO;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String EMPLOYEE_PAGE = "employeePage.jsp";
    private final String MANAGER_PAGE = "managerPage.jsp";

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
            String SearchValue = request.getParameter("txtSearchByName");
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            ItemDAO itemDao = new ItemDAO();
            List<ItemDTO> list = null;

            if (SearchValue.isEmpty() && !user.getRole().equals("AD")) {
                list = itemDao.listAllItem();
                url = EMPLOYEE_PAGE;
            } else if (!SearchValue.isEmpty() && !user.getRole().equals("AD")) {
                list = itemDao.searchItem(SearchValue);
                url = EMPLOYEE_PAGE;
            } else if (SearchValue.isEmpty() && user.getRole().equals("AD")) {
                list = itemDao.listAllItem();
                url = MANAGER_PAGE;
            } else if (!SearchValue.isEmpty() && user.getRole().equals("AD")) {
                list = itemDao.searchItem(url);
                url = MANAGER_PAGE;
            }

            session.setAttribute("FIRSTLOAD", list);

        } catch (Exception e) {
            log("Error at SearchServlet:"+e.toString());
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
