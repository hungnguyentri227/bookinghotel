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
public class BillDTO_FB {

    private int billID;
    private String faceID;
    private String hotelName;
    private String room;
    private String categoryName;
    private String datebooking;
    private float totalprice;

    public BillDTO_FB(int billID, String faceID, String hotelName, String room, String categoryName, String datebooking, float totalprice) {
        this.billID = billID;
        this.faceID = faceID;
        this.hotelName = hotelName;
        this.room = room;
        this.categoryName = categoryName;
        this.datebooking = datebooking;
        this.totalprice = totalprice;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getFaceID() {
        return faceID;
    }

    public void setFaceID(String faceID) {
        this.faceID = faceID;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
    
    
}
