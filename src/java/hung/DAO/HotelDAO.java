/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.DAO;

import hung.DTO.HotelDTO;
import hung.dbutils.Dbutils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author hungn
 */
public class HotelDAO {

    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
        }
    }

    public ArrayList<HotelDTO> showDetailsByCountryID(String country_name, int start, int end) {
        ArrayList<HotelDTO> list = new ArrayList<>();
        try {
            conn = Dbutils.makeConnection();
            String sql = "select hotel_id, hotel_name, address, tbl_country.countryname from tbl_hotel, tbl_country"
                    + " where tbl_hotel.countryid = tbl_country.countryid and tbl_country.countryname = ? "
                    + "order by hotel_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            stm = conn.prepareStatement(sql);
            stm.setString(1, country_name);
            stm.setInt(2, start);
            stm.setInt(3, end);
            rs = stm.executeQuery();

            while (rs.next()) {
                int hotelid = rs.getInt("hotel_id");
                String hotelname = rs.getString("hotel_name");
                String address = rs.getString("address");
                String country_name1 = rs.getString("countryname");

                list.add(new HotelDTO(hotelid, hotelname, address, country_name1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public int Countrow() {
        int count = 0;
        try {
            conn = Dbutils.makeConnection();
            String sql = "Select count(*) from tbl_hotel";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            rs.next();

            count = rs.getInt(1);
        } catch (Exception e) {
        }
        return count;
    }

    public HotelDTO showHotelNameByID(int id) {
        HotelDTO dto = null;
        try {
            conn = Dbutils.makeConnection();
            String sql = "select hotel_id, hotel_name from tbl_hotel where hotel_id = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            if (rs.next()) {
                int hotel_id = rs.getInt("hotel_id");
                String hotelname = rs.getString("hotel_name");
                dto = new HotelDTO(hotel_id, hotelname);
            }

        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean Insert(HotelDTO dto) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "Insert tbl_hotel(hotel_name, address, countryid, action) values(?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getHotelname());
            stm.setString(2, dto.getAddress());
            stm.setInt(3, dto.getCountry_id());
            stm.setBoolean(4, true);
            
            if (stm.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public ArrayList<HotelDTO> showCountry() {
        ArrayList<HotelDTO> list = new ArrayList<>();
        try {
            conn = Dbutils.makeConnection();
            String sql = "SELECT countryid, countryname FROM tbl_country";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                int countryid = rs.getInt("countryid");
                String countryname = rs.getString("countryname");
                list.add(new HotelDTO(countryname, countryid));
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public ArrayList<HotelDTO> showDetailsHotel(int start, int end) {
        ArrayList<HotelDTO> list = new ArrayList<>();
        try {
            conn = Dbutils.makeConnection();
            String sql = "select hotel_id, hotel_name, address, tbl_country.countryname from tbl_country, tbl_hotel  where tbl_country.countryid = tbl_hotel.countryid and action=? "
                    + "order by hotel_id offset ? rows fetch next ? row only";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, true);
            stm.setInt(2, start);
            stm.setInt(3, end);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                int hotelid = rs.getInt("hotel_id");
                String hotelname = rs.getString("hotel_name");
                String address = rs.getString("address");
                String country_name1 = rs.getString("countryname");
                
                list.add(new HotelDTO(hotelid, hotelname, address, country_name1));
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public boolean Delete(int id) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "Update tbl_hotel SET action=? WHERE hotel_id=?";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, false);
            stm.setInt(2, id);
        
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public boolean Update(HotelDTO dto) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "Update tbl_hotel SET hotel_name=?, address=? WHERE hotel_id=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getHotelname());
            stm.setString(2, dto.getAddress());
            stm.setInt(3, dto.getHotelid());
        
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }
    
      public boolean checkDuplicate(String hotelname) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "SELECT hotel_name FROM tbl_hotel";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (hotelname.equals(rs.getString("hotel_name"))) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }
}
