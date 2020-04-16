/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.DTO;

/**
 *
 * @author hungn
 */
public class BillDTO {

    private int billID;
    private int hotelID;
    private String username;
    private String hotelName;
    private String room;
    private String categoryName;
    private int amount;
    private int price;
    private String datebooking;
    private float totalprice;

    public BillDTO() {
    }

    public BillDTO(int billID, String hotelName, String room, String categoryName, float totalprice) {
        this.billID = billID;
        this.hotelName = hotelName;
        this.room = room;
        this.categoryName = categoryName;
        this.totalprice = totalprice;
    }
    
    
     

    public BillDTO(int billID, int hotelID, String hotelName, String room, String categoryName, int amount, int price) {
        this.billID = billID;
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.room = room;
        this.categoryName = categoryName;
        this.amount = amount;
        this.price = price;
    }

    public BillDTO(int billID, String hotelName, String room, String categoryName, String datebooking, float totalprice) {
        this.billID = billID;
        this.hotelName = hotelName;
        this.room = room;
        this.categoryName = categoryName;
        this.datebooking = datebooking;
        this.totalprice = totalprice;
    }

    public BillDTO(int billID, int hotelID, String hotelName, String room, String categoryName, int amount, int price, float totalprice) {
        this.billID = billID;
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.room = room;
        this.categoryName = categoryName;
        this.amount = amount;
        this.price = price;
        this.totalprice = totalprice;
    }

    public BillDTO(int billID, String username, String hotelName, String room, String categoryName, String datebooking, float totalprice) {
        this.billID = billID;
        this.username = username;
        this.hotelName = hotelName;
        this.room = room;
        this.categoryName = categoryName;
        this.datebooking = datebooking;
        this.totalprice = totalprice;
    }

    public BillDTO(int billID, int hotelID, String username, String hotelName, String room, String categoryName, int amount, int price, float totalprice) {
        this.billID = billID;
        this.hotelID = hotelID;
        this.username = username;
        this.hotelName = hotelName;
        this.room = room;
        this.categoryName = categoryName;
        this.amount = amount;
        this.price = price;
        this.totalprice = totalprice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getDatebooking() {
        return datebooking;
    }

    public void setDatebooking(String datebooking) {
        this.datebooking = datebooking;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    

}
