/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.controller;

import hung.DAO.RoomDAO;
import hung.DAO.UserDAO;
import hung.DTO.BillDTO_FB;
import hung.DTO.BillDTO;
import hung.DTO.ProductCart;
import hung.dbutils.Validate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hungn
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/BookingServlet"})
public class BookingServlet extends HttpServlet {

    private final String SUCCESS = "hotel.jsp";
    private final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Validate date = new Validate();
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        ProductCart cart = (ProductCart) session.getAttribute("SHOP");
        for (BillDTO list : cart.getCart().values()) {
            int billID = list.getBillID();
            String username = request.getParameter("txtusername");
            String facebookID = request.getParameter("txtfaceID");
            String hotelName = list.getHotelName();
            String roomName = list.getRoom();
            String categoryName = list.getCategoryName();
            Float totalPrice = list.getTotalprice();
            String currentDate = date.dateNow();
            BillDTO dto1 = new BillDTO(billID, username, hotelName, roomName, categoryName, currentDate, totalPrice);
            BillDTO_FB dto_fb = new BillDTO_FB(billID, facebookID, hotelName, roomName, categoryName, currentDate, totalPrice);
            // billDTO dto = new billDTO(billID, hotelName, roomName, categoryName, currentDate, totalPrice);
            cart.put(billID, dto1);

            UserDAO dao = new UserDAO();
            String url = ERROR;

            try {
                if (session.getAttribute("USER") != null) {
                    boolean result = dao.booking(dto1, cart);
                    boolean updateAction = new RoomDAO().updateIsActon(roomName);
                    if (result && updateAction) {
                        url = SUCCESS;
                        response.sendRedirect(url);
                    }
                } else if (session.getAttribute("USERFACEBOOKINFO") != null) {
                    boolean result = dao.booking_fb(dto_fb, cart);
                    boolean updateAction = new RoomDAO().updateIsActon(roomName);
                    if (result && updateAction) {
                        url = SUCCESS;
                        response.sendRedirect(url);
                    }
                }
                else {
                    response.sendRedirect("index.jsp");
                }
            } catch (Exception e) {
                log("Error Booking Servlet " + e.toString());
            } finally {
                out.close();
            }
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
