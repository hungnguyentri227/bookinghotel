/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.controller;

import hung.DAO.HotelDAO;
import hung.DAO.UserDAO;
import hung.DTO.HotelDTO;
import hung.DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author hungn
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String SEARCH = "hotel.jsp";
    private final String HOME = "index.jsp";
    private final String USER = "bookinghistory.jsp";
    private final String ADMIN = "detailsCustomer.jsp";
    private final String Error = "error.html";

    private final Logger log = Logger.getLogger(this.getClass());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HotelDAO dao = new HotelDAO();
        PrintWriter out = response.getWriter();
        String username = request.getParameter("txtusername");
        String password = request.getParameter("txtpassword");
        String url = HOME;

        HttpSession session = request.getSession();

        try {
            if (username.equals("")) {
                request.setAttribute("error", "Username can't be blank");
            } else if (password.equals("")) {
                request.setAttribute("error", "Password can't be blank");
            } else {
                UserDTO login = new UserDAO().checkLogin(username, password);
                if (login.getRole().equals("customer")) {
                    url = SEARCH;
                    ArrayList<HotelDTO> list = dao.showCountry();
                    request.setAttribute("LIST", list);
                    session.setAttribute("USER", login.getUsername());
                }  else if (login.getRole().equals("admin")) {
                    url = ADMIN;
                    session.setAttribute("ADMIN1", login.getUsername());
                } else {
                    url = Error;
                }
            }
        } catch (Exception ex) {
            log.error("Error at LoginServlet_SQL: " + ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
