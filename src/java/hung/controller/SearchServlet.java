/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.controller;

import hung.DAO.DateObj;
import hung.DAO.HotelDAO;
import hung.DAO.RoomDAO;
import hung.DTO.HotelDTO;
import hung.dbutils.Validate;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final String RESULT = "hotel.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        RoomDAO dao = new RoomDAO();
        Validate valid = new Validate();
        HotelDAO hoteldao = new HotelDAO();
        //----------------------------------

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

        int sumrow = hoteldao.Countrow();
        //CT: max pageid = (max row / count) + 1

        int maxpageid = (sumrow / count) + 1;
        //---------------------------------

        String country_name = request.getParameter("txtcountry");
        String datebegin = request.getParameter("txtdatebegin");
        String dateend = request.getParameter("txtdateend");
        String hotelid = request.getParameter("txthotelid");
        //count 2 date:

        try {
            if (datebegin.equals("")) {
                request.setAttribute("error", "DateBegin can't be blank");

            } else if (dateend.equals("")) {
                request.setAttribute("error", "DateEnd can't be blank");

            } else if (country_name.equals("")) {
                request.setAttribute("error", "Countryname can't be blank");

            } else {
                if (!valid.check2Date(datebegin, dateend)) {
                    request.setAttribute("error", "valid false");
                } else {
                    long days = valid.countDate(datebegin, dateend);
                    DateObj date = new DateObj(datebegin, dateend, days);
                    HttpSession session = request.getSession();
                    session.setAttribute("DATE", date);
                    session.setAttribute("hotelid", hotelid);
                    ArrayList<HotelDTO> result = hoteldao.showDetailsByCountryID(country_name, pageid - 1, count);
                    if (result != null) {
                        request.setAttribute("SEARCHHOTEL", result);
                        request.setAttribute("maxpageid", maxpageid);
                        request.setAttribute("numberpage", Integer.parseInt(pageidstr));
                        request.setAttribute("COUNTRYNAME", country_name);
                        request.setAttribute("DATEBEGIN", datebegin);
                        request.setAttribute("DATEEND", dateend);
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(RESULT).forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
