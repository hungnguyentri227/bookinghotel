/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.controller;

import hung.DAO.UserDAO;
import hung.DTO.BillDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hungn
 */
@WebServlet(name = "BookingHistoryServlet", urlPatterns = {"/BookingHistoryServlet"})
public class BookingHistoryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        UserDAO dao = new UserDAO();
        String datebook = request.getParameter("txtdate");
        //----------------Phan Trang--------------------
        String pageidstr = request.getParameter("pageid");

        //ep kieu
        int pageid = Integer.parseInt(pageidstr);
        int count = 5;
        //neu pageid = 1 => k0 phan trang
        //neu pageid != 1 => phan trang
        if (pageid == 1) {
        } else {
            pageid = pageid - 1;
            pageid = pageid * count + 1;
        }

        int sumrow = dao.Countrow();
        //CT: max pageid = (max row / count) + 1

        int maxpageid = (sumrow / count) + 1;
        //--------------------------------------------
        try {
            ArrayList<BillDTO> result = dao.showHistoryBooking(datebook, pageid - 1, count);
            if (result != null) {
                request.setAttribute("HISTORY", result);
                request.setAttribute("maxpageid", maxpageid);
                request.setAttribute("numberpage", Integer.parseInt(pageidstr));
                request.setAttribute("DATE", datebook);

            }
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher("bookinghistory.jsp").forward(request, response);
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
