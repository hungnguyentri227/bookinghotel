/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.DTO;

import hung.DAO.RoomDAO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hungn
 */
public class ProductCart extends HashMap {

    private Map<Integer, BillDTO> cart;

    public ProductCart() {
    }

    public Map<Integer, BillDTO> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, BillDTO> cart) {
        this.cart = cart;
    }

    public void addtoCart(BillDTO dto) {
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }
        int key = dto.getBillID();
        if (this.cart.containsKey(key)) {
        }
        this.cart.put(key, dto);
    }

    public void deleteCart(int id) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

    public void updateCart(BillDTO dto) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(dto.getBillID())) {
            this.cart.replace(dto.getBillID(), dto);
        }
    }

}
