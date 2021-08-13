/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.server;

import company.driver.ItemDAO;
import company.driver.RequestDAO;
import company.driver.RequestDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cunpl
 */
@WebServlet(name = "AcceptServlet", urlPatterns = {"/AcceptServlet"})
public class AcceptServlet extends HttpServlet {

    private static final String MANAGER_PAGE = "managerPage.jsp";

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
        String url = MANAGER_PAGE;
        try {
            String UserId = request.getParameter("txtUserId");
            int ItemId = Integer.parseInt(request.getParameter("txtItemId"));
            int newQuantity = Integer.parseInt(request.getParameter("txtQuantity"));
            String message_error = "";
            ItemDAO itemDao = new ItemDAO();
            RequestDAO requestDao = new RequestDAO();
            int oldQuantity = itemDao.getOldQuantity(ItemId);
            
            if (newQuantity >= oldQuantity) {
                message_error = "you can't Accept this item because Quantity isn't enough!";
                request.setAttribute("ACCEPT_ERROR", message_error);
            } else {
                if (!requestDao.checkExistAcceptStatus(UserId, ItemId)) {
                    message_error = "Accept Success!";
                    int totalQuantity = oldQuantity - newQuantity;
                    itemDao.updateItemQuantity(ItemId, totalQuantity);
                    RequestDTO requestDto = new RequestDTO(UserId, ItemId, "", "", "", "Accept");
                    requestDao.updateStatusAndDateAccept(requestDto);
                    url = "DispatchServlet?btnAction=SearchRequest&txtSearchByName=";
                    request.setAttribute("ACCEPT_ERROR", message_error);
                } else {
                    int acceptRequestQuantity = requestDao.getAcceptRequestQuantity(UserId, ItemId);
                    int totalAcceptQuantity = newQuantity + acceptRequestQuantity;
                    int updateItemQuantity = oldQuantity - newQuantity;
                    itemDao.updateItemQuantity(ItemId, updateItemQuantity);
                    requestDao.deleteNewRequest(UserId, ItemId);
                    requestDao.updateAcceptedQuantity(UserId, ItemId, totalAcceptQuantity);
                    url = "DispatchServlet?btnAction=SearchRequest&txtSearchByName=";
                    request.setAttribute("ACCEPT_ERROR", message_error);
                }

                //ACCEPT -> then Overide the Quantity With Status is Accept
                //If(request exist) -> update databse by Delete that request -> update the old with quantity
                //else Write it into database
            }

        } catch (Exception e) {
            log("Error at AcceptServlet: " + e.toString());
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
