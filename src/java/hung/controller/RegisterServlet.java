/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.controller;

import hung.DAO.UserDAO;
import hung.DTO.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author hungn
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private final String SUCCESS = "index.jsp";
    private final String ERROR = "register.jsp";
    private final Logger log = Logger.getLogger(this.getClass());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("txtusername");
        String password = request.getParameter("txtpassword");
        String confirm = request.getParameter("txtconfirm");
        String fullname = request.getParameter("txtfullname");
        UserDAO dao = new UserDAO();
        String url = ERROR;
        try {
            if (username.equals("")) {
                request.setAttribute("error", "Username can't be blank");
            } else if (password.equals("")) {
                request.setAttribute("error", "Password can't be blank");

            } else if (confirm.equals("")) {
                request.setAttribute("error", "Confirm can't be blank");

            } else if (fullname.equals("")) {
                request.setAttribute("error", "Fullname can't be blank");

            } else if (!password.equals(confirm.trim())) {
                request.setAttribute("error", "Password and Confirm are different");

            } else if (dao.checkDuplicate(username)) {
                request.setAttribute("error", "Duplicate username");

            } else {
                UserDTO dto = new UserDTO(username, password, fullname, "customer");
                boolean result = new UserDAO().Insert(dto);
                if (result) {
                    url = SUCCESS;
                }
            }
        } catch (SQLException e) {
            log.error("Error RegisterServlet: " + e.getMessage());

        } catch (NamingException e) {
            log.error("Error RegisterServlet: " + e.getMessage());
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
