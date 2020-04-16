/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.controller;

import hung.DAO.CustomerDAO;
import hung.DTO.UserDTO;
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
@WebServlet(name = "Customer_ChangePass_Controller", urlPatterns = {"/Customer_ChangePass_Controller"})
public class Customer_ChangePass_Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String password = request.getParameter("txtpassword");
        String newpassword = request.getParameter("txtnewpass");
        String confirmpass = request.getParameter("txtconfirm");
        CustomerDAO dao = new CustomerDAO();
        String username = request.getParameter("txtusername");
        UserDTO dto = dao.showDetailsCustomer(username);
        String url = "Customer_changepassJSP?txtusername=" + username;
        try {
            if (session.getAttribute("USER") != null) {
                if (password.equals("")) {
                    request.setAttribute("error", "Current Pass can't be blank!");
                } else if (newpassword.equals("")) {
                    request.setAttribute("error", "New Pass can't be blank!");
                } else if (confirmpass.equals("")) {
                    request.setAttribute("error", "Confirm pass can't be blank!");
                } else if (!password.equals(dto.getPassword())) {
                    request.setAttribute("error", "Current Password is not correct!!");
                } else if (!newpassword.equals(confirmpass)) {
                    request.setAttribute("error", "New Password and Confirm Password are different!");
                } else {
                    if (dao.changePassword(newpassword, username)) {
                        request.setAttribute("error", "Your Password has been changed");
                    } else {
                          request.setAttribute("error", "Change Password failed.");
                    }
                }
            }
        } catch (Exception e) {
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
