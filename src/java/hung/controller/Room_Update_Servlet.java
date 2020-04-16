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
@WebServlet(name = "Room_Update_Servlet", urlPatterns = {"/Room_Update_Servlet"})
public class Room_Update_Servlet extends HttpServlet {

    private final String success = "AdminController?btAction=ViewRoom&pageid=1";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int id = Integer.parseInt(request.getParameter("txtid"));
        String roomname = request.getParameter("txtroomname1");
        String floor = request.getParameter("txtfloor1");
        String price = request.getParameter("txtprice1");
        RoomDAO dao = new RoomDAO();
        try {
            if (price.equals("")) {
                request.setAttribute("error1", "Price can't be blank");
            } else if (!price.matches("[0-9]{1,1000}")) {
                request.setAttribute("error1", "Price must be Integer");
            } else {
                RoomDTO dto = new RoomDTO(id, roomname, floor, Integer.parseInt(price));
                dao.Update(dto);
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
