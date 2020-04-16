/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.controller;

import hung.DTO.BillDTO;
import hung.DTO.ProductCart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
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
@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

    private final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = ERROR;
        try {
            String action = request.getParameter("btAction");
            if (action.equals("Add To Cart")) {
                HttpSession session = request.getSession(true);
                ProductCart shop = (ProductCart) session.getAttribute("SHOP");
                if (shop == null) {
                    shop = new ProductCart();
                }

                Random random = new Random();
                int billid = random.nextInt();
                int hotelid = Integer.parseInt(request.getParameter("txthotelid").trim());
                int roomid = Integer.parseInt(request.getParameter("txtroomid"));
                String username = request.getParameter("txtusername");
                String hotelname = request.getParameter("txthotelname");
                String roomname = request.getParameter("txtroomname");
                String category_name = request.getParameter("txtcategoryname");
                int amount = Integer.parseInt(request.getParameter("txtcount"));
                int price = Integer.parseInt(request.getParameter("txtprice"));
                float total = Float.parseFloat(request.getParameter("txttotal"));

                BillDTO dto = new BillDTO(billid, hotelid, username, hotelname, roomname, category_name, amount, price, total);

                shop.addtoCart(dto);
                session.setAttribute("SHOP", shop);
                url = "ClickHotelServlet?txthotelid=" + hotelid + "&pageid_room=1";
            } else if (action.equals("View Cart")) {
                url = "cart.jsp";
            } else if (action.equals("AddMore")) {
                String hotelid = request.getParameter("txthotelid").trim();
                url = "ClickHotelServlet?txthotelid=" + hotelid + "&pageid_room=1";

            } else if (action.equals("Delete")) {
                HttpSession session = request.getSession();
                if (session != null) {
                    ProductCart shop1 = (ProductCart) session.getAttribute("SHOP");
                    if (shop1 != null) {
                        int removeID = Integer.parseInt(request.getParameter("txtbillid"));
                        shop1.deleteCart(removeID);
                        session.setAttribute("SHOP", shop1);
                    }
                }
                String urlWriting = "CartController?btAction=View Cart";
                url = urlWriting;
            }
        } catch (Exception e) {
            log("Error at CartController: " + e.toString());

        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
