/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.controller;

import hung.DAO.HotelDAO;
import hung.DTO.HotelDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hungn
 */
@WebServlet(name = "Hotel_Insert_Controller1", urlPatterns = {"/Hotel_Insert_Controller1"})
public class Hotel_Insert_Controller1 extends HttpServlet {

    private final String home = "AdminController?btAction=ViewHotel&pageid=1";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HotelDAO dao = new HotelDAO();
        PrintWriter out = response.getWriter();
        String hotelName = request.getParameter("txthotelname");
        String address = request.getParameter("txtaddress");
        int countryid = Integer.parseInt(request.getParameter("dropdownCountry"));

        try {
            if (hotelName.equals("")) {
                request.setAttribute("error", "Hotel Name can't be blank");
            } else if (address.equals("")) {
                request.setAttribute("error", "Address can't be blank");
            } else if (dao.checkDuplicate(hotelName)) {
                request.setAttribute("error", "HotelName Duplicate");
            } else {
                HotelDTO dto = new HotelDTO(0, hotelName, address, countryid);
                if (dao.Insert(dto)) {
                    request.setAttribute("error", "Insert Successfully");
                } else {
                    request.setAttribute("error", "Not successfully.");
                }
            }
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(home).forward(request, response);
            out.close();
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
