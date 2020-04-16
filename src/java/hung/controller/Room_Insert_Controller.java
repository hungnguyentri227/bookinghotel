/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.controller;

import hung.DAO.RoomDAO;
import hung.DTO.RoomDTO;
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
@WebServlet(name = "Room_Insert_Controller", urlPatterns = {"/Room_Insert_Controller"})
public class Room_Insert_Controller extends HttpServlet {

    private final String success = "AdminController?btAction=ViewRoom&pageid=1";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String roomname = request.getParameter("txtroomname");
        String floor = request.getParameter("txtfloor");
        String price = request.getParameter("txtprice");
        int hotelid = Integer.parseInt(request.getParameter("dropdownHotel"));
        int category_id = Integer.parseInt(request.getParameter("dropdownCategory"));

        RoomDAO dao = new RoomDAO();

        try {
            if (roomname.equals("")) {
                request.setAttribute("error", "Roomname can't be blank");
            } else if (floor.equals("")) {
                request.setAttribute("error", "Floor can't be blank");
            } else if (price.equals("")) {
                request.setAttribute("error", "Price can't be blank");
            } else if (floor.equals("0")) {
                request.setAttribute("error", "Floor must be > 0");
            } else if (!price.matches("[0-9]{1,1000}")) {
                request.setAttribute("error", "Price must be Integer");
            } else if (dao.checkDuplicate(roomname)) {
                request.setAttribute("error", "Roomname Duplicate.");
            } else {
                RoomDTO dto = new RoomDTO(0, roomname, floor, Integer.parseInt(price), hotelid, category_id, true);
                boolean result = dao.Insert(dto);
                if (result) {
                    request.setAttribute("error", "Insert Successfully");
                } else {
                    request.setAttribute("error", "Not Successfully");
                }
            }
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(success).forward(request, response);
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
