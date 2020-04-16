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
@WebServlet(name = "Hotel_Update_Controller", urlPatterns = {"/Hotel_Update_Controller"})
public class Hotel_Update_Controller extends HttpServlet {

    private final String home = "AdminController?btAction=ViewHotel&pageid=1";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int id = Integer.parseInt(request.getParameter("txtid1"));
        String hotelname = request.getParameter("txthotelname1").trim();
        String address = request.getParameter("txtaddress1").trim();
        String countryname = request.getParameter("txtcountryname1").trim();
        HotelDTO dto = new HotelDTO(id, hotelname, address, countryname);
        String valid = "";
        try {
            if (hotelname.equals("")) {
                request.setAttribute("error1", "hotelName can't be blank");
            } else if (address.equals("")) {
                request.setAttribute("error1", "Address can't be blank");
            } else {
                boolean result = new HotelDAO().Update(dto);
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
