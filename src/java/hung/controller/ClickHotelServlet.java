/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.controller;

import hung.DAO.HotelDAO;
import hung.DAO.RoomDAO;
import hung.DTO.HotelDTO;
import hung.DTO.RoomDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "ClickHotelServlet", urlPatterns = {"/ClickHotelServlet"})
public class ClickHotelServlet extends HttpServlet {

    private final String SUCCESS = "room.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RoomDAO roomdao = new RoomDAO();
        //-------Xu ly phan trang-------------
        String pageidstr = request.getParameter("pageid_room");
        //ep kieu
        int pageid = Integer.parseInt(pageidstr);
        int count = 2;
        //neu pageid = 1 => k0 phan trang
        //neu pageid != 1 => phan trang
        if (pageid == 1) {

        } else {
            pageid = pageid - 1;
            pageid = pageid * count + 1;
        }
        int sumrow = roomdao.Countrow();
        //CT: max pageid = (max row / count) + 1
        int maxpageid = (sumrow / count) + 1;
        //----------------------------
        int hotelid = Integer.parseInt(request.getParameter("txthotelid"));

        try {
            ArrayList<RoomDTO> result = roomdao.showDetailsRoomByHotelID(hotelid, pageid - 1, count);
            HotelDTO hoteldto = new HotelDAO().showHotelNameByID(hotelid);
            if (request != null) {
                request.setAttribute("ROOM", result);
                request.setAttribute("maxpageid_room", maxpageid);
                request.setAttribute("numberpage_room", Integer.parseInt(pageidstr));
                HttpSession session = request.getSession();
                session.setAttribute("HOTELID", hotelid);
                request.setAttribute("HOTEL", hoteldto);
                
            }
            RequestDispatcher rd = request.getRequestDispatcher(SUCCESS);
            rd.forward(request, response);
        } catch (Exception e) {
        } finally {
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
