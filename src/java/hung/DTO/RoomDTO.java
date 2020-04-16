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
public class RoomDTO {
    private int roomid;
    private String roomname;
    private String floor;
    private int price;
    private int hotel_id;
    private String hotel_name;
    private String categoryName;
    private int categoryID;
    private boolean isAction;

    public RoomDTO() {
    }

    public RoomDTO(int roomid, String roomname, String floor, int price) {
        this.roomid = roomid;
        this.roomname = roomname;
        this.floor = floor;
        this.price = price;
    }
    
    

    public RoomDTO(int roomid, String roomname, String floor, int price, boolean isAction) {
        this.roomid = roomid;
        this.roomname = roomname;
        this.floor = floor;
        this.price = price;
        this.isAction = isAction;
    }

    public RoomDTO(int roomid, String roomname, String floor, int price, String categoryName) {
        this.roomid = roomid;
        this.roomname = roomname;
        this.floor = floor;
        this.price = price;
        this.categoryName = categoryName;
    }

    public RoomDTO(String categoryName, int categoryID) {
        this.categoryName = categoryName;
        this.categoryID = categoryID;
    }

    public RoomDTO(int roomid, String roomname, String floor, int price, int hotel_id, int categoryID, boolean isAction) {
        this.roomid = roomid;
        this.roomname = roomname;
        this.floor = floor;
        this.price = price;
        this.hotel_id = hotel_id;
        this.categoryID = categoryID;
        this.isAction = isAction;
    }

    public RoomDTO(int roomid, String roomname, String floor, int price, String hotel_name, String categoryName) {
        this.roomid = roomid;
        this.roomname = roomname;
        this.floor = floor;
        this.price = price;
        this.hotel_name = hotel_name;
        this.categoryName = categoryName;
    }
    
    

    
    
    

    
    
    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    

    public boolean isIsAction() {
        return isAction;
    }

    public void setIsAction(boolean isAction) {
        this.isAction = isAction;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }
    
    
    
}
