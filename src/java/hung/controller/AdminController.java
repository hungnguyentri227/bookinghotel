/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.controller;

import hung.DAO.CustomerDAO;
import hung.DAO.HotelDAO;
import hung.DAO.RoomDAO;
import hung.DAO.UserDAO;
import hung.DTO.HotelDTO;
import hung.DTO.RoomDTO;
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

/**
 *
 * @author hungn
 */
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {

    private final String customer = "detailsCustomer.jsp";
    private final String user = "detailsUser.jsp";
    private final String error = "error.jsp";
    private final String hotel = "detailsHotel.jsp";
    private final String room = "detailsRoom.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        CustomerDAO dao = new CustomerDAO();
        UserDAO userdao = new UserDAO();
        HotelDAO hoteldao = new HotelDAO();
        RoomDAO roomdao = new RoomDAO();
        String button = request.getParameter("btAction");
        String url = error;

        //----------------Phan Trang--------------------
        String pageidstr = request.getParameter("pageid");

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

        //--------------------------------------------
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("ADMIN1") != null) {
                if (button.equals("Search")) {
                    int sumrow = dao.Countrow();
                    //CT: max pageid = (max row / count) + 1

                    int maxpageid = (sumrow / count) + 1;
                    url = customer;
                    ArrayList<UserDTO> list = dao.showDetailsbyRole("customer", pageid - 1, count);
                    if (list != null) {
                        request.setAttribute("ADMIN", list);
                        request.setAttribute("maxpageid", maxpageid);
                        request.setAttribute("numberpage", Integer.parseInt(pageidstr));
                    }
                } else if (button.equals("ViewUser")) {
                    int sumrow = dao.Countrow();
                    //CT: max pageid = (max row / count) + 1

                    int maxpageid = (sumrow / count) + 1;
                    url = user;
                    ArrayList<UserDTO> list = userdao.showDetailsbyRole("user", pageid - 1, count);
                    request.setAttribute("ADMIN2", list);
                    request.setAttribute("maxpageid", maxpageid);
                    request.setAttribute("numberpage", Integer.parseInt(pageidstr));
                } else if (button.equals("ViewHotel")) {
                    int sumrow = hoteldao.Countrow();
                    int maxpageid = (sumrow / count) + 1;
                    url = hotel;
                    ArrayList<HotelDTO> list = hoteldao.showCountry();
                    request.setAttribute("LIST", list);
                    ArrayList<HotelDTO> listtable = hoteldao.showDetailsHotel(pageid - 1, count);
                    request.setAttribute("LISTTABLE", listtable);
                    request.setAttribute("maxpageid", maxpageid);
                    request.setAttribute("numberpage", Integer.parseInt(pageidstr));
                } else if (button.equals("ViewRoom")) {
                    int sumrow = roomdao.Countrow();
                    int maxpageid = (sumrow / count) + 1;
                    url = room;
                    ArrayList<HotelDTO> list = roomdao.showHotel();
                    request.setAttribute("LIST2", list);
                    ArrayList<RoomDTO> list2 = roomdao.showCategory();
                    request.setAttribute("LIST3", list2);
                    ArrayList<RoomDTO> listtable = roomdao.showDetailsRoom(pageid - 1, count);
                    request.setAttribute("LISTTABLE2", listtable);
                    request.setAttribute("maxpageid", maxpageid);
                    request.setAttribute("numberpage", Integer.parseInt(pageidstr));

                }
            } else {
                response.sendRedirect("index.jsp");
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
