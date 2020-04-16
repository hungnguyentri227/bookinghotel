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
public class HotelDTO {
    private int hotelid;
    private String hotelname;
    private String address;
    private String country_name;
    private int country_id;
    private boolean action;
    
    public HotelDTO() {
    }

    public HotelDTO(int hotelid, String hotelname, String address, String country_name) {
        this.hotelid = hotelid;
        this.hotelname = hotelname;
        this.address = address;
        this.country_name = country_name;
    }

    public HotelDTO(int hotelid, String hotelname, String address, int country_id) {
        this.hotelid = hotelid;
        this.hotelname = hotelname;
        this.address = address;
        this.country_id = country_id;
    }

    public HotelDTO(String country_name, int country_id) {
        this.country_name = country_name;
        this.country_id = country_id;
    }

    public HotelDTO(int hotelid, String hotelname, boolean action) {
        this.hotelid = hotelid;
        this.hotelname = hotelname;
        this.action = action;
    }


    public HotelDTO( String hotelname) {
        this.hotelname = hotelname;
    }

    public HotelDTO(int hotelid, String hotelname) {
        this.hotelid = hotelid;
        this.hotelname = hotelname;
    }

    
    public int getHotelid() {
        return hotelid;
    }

    public void setHotelid(int hotelid) {
        this.hotelid = hotelid;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }
    
    
    
}
