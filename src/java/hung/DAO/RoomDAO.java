/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.DAO;

import hung.DTO.HotelDTO;
import hung.DTO.RoomDTO;
import hung.dbutils.Dbutils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author hungn
 */
public class RoomDAO {
    
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
    
    public ArrayList<RoomDTO> showDetailsByRoom() {
        ArrayList<RoomDTO> listDTO = new ArrayList<>();
        
        try {
            conn = Dbutils.makeConnection();
            String sql = "SELECT room_id, room_name, floor, price, isAction FROM tbl_room";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("room_id");
                String roomname = rs.getString("room_name");
                String floor = rs.getString("floor");
                int price = rs.getInt("price");
                boolean isaction = rs.getBoolean("isAction");
                listDTO.add(new RoomDTO(id, roomname, floor, price, isaction));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listDTO;
    }
    
    public int Countrow() {
        int count = 0;
        try {
            conn = Dbutils.makeConnection();
            String sql = "Select count(*) from tbl_room";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            
            rs.next();
            
            count = rs.getInt(1);
        } catch (Exception e) {
        }
        return count;
    }
    
    public ArrayList<RoomDTO> showDetailsRoomByHotelID(int hotelid, int start, int end) {
        ArrayList<RoomDTO> listDTO = new ArrayList<>();
        
        try {
            conn = Dbutils.makeConnection();
            String sql = "select room_id, room_name, floor, price , tbl_category.categoryname "
                    + "from tbl_room, tbl_category where tbl_room.category_id = tbl_category.categoryid and hotel_id= ? and isAction= ? "
                    + "order by room_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, hotelid);
            stm.setBoolean(2, true);
            stm.setInt(3, start);
            stm.setInt(4, end);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("room_id");
                String roomname = rs.getString("room_name");
                String floor = rs.getString("floor");
                int price = rs.getInt("price");
                String category_name = rs.getString("categoryname");
                listDTO.add(new RoomDTO(id, roomname, floor, price, category_name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return listDTO;
    }
    
    public boolean updateIsActon(String room_name) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "Update tbl_room SET isAction=? WHERE room_name=?";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, false);
            stm.setString(2, room_name);
            
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
    
    public ArrayList<HotelDTO> showHotel() {
        ArrayList<HotelDTO> list = new ArrayList<>();
        try {
            conn = Dbutils.makeConnection();
            String sql = "SELECT hotel_id, hotel_name, action FROM tbl_hotel WHERE action=?";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, true);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                int hotelid = rs.getInt("hotel_id");
                String hotelname = rs.getString("hotel_name");
                boolean action = rs.getBoolean("action");
                list.add(new HotelDTO(hotelid, hotelname, action));
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public ArrayList<RoomDTO> showCategory() {
        ArrayList<RoomDTO> list = new ArrayList<>();
        try {
            conn = Dbutils.makeConnection();
            String sql = "SELECT categoryid, categoryname FROM tbl_category WHERE action=?";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, true);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                int categoryid = rs.getInt("categoryid");
                String categoryname = rs.getString("categoryname");
                list.add(new RoomDTO(categoryname, categoryid));
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public boolean Insert(RoomDTO dto) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "Insert tbl_room(room_name, floor, price, hotel_id, category_id, isAction) values(?,?,?,?,?,?)";
            
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getRoomname());
            stm.setString(2, dto.getFloor());
            stm.setInt(3, dto.getPrice());
            stm.setInt(4, dto.getHotel_id());
            stm.setInt(5, dto.getCategoryID());
            stm.setBoolean(6, true);
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
    
    public boolean Delete(int id) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "Update tbl_room SET isAction=? WHERE room_id=?";
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
    
    public boolean Update(RoomDTO dto) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "Update tbl_room SET price=? WHERE room_id=?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, dto.getPrice());
            stm.setInt(2, dto.getRoomid());
            
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
    
    public ArrayList<RoomDTO> showDetailsRoom(int start, int end) {
        ArrayList<RoomDTO> list = new ArrayList<>();
        try {
            conn = Dbutils.makeConnection();
            String sql = "select room_id, room_name, floor, price, tbl_hotel.hotel_name, tbl_category.categoryname "
                    + "from tbl_room, tbl_hotel, tbl_category "
                    + "where tbl_room.category_id = tbl_category.categoryid "
                    + "and tbl_room.hotel_id = tbl_hotel.hotel_id and isAction=? "
                    + "order by room_id offset ? rows fetch next ? row only";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, true);
            stm.setInt(2, start);
            stm.setInt(3, end);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("room_id");
                String roomname = rs.getString("room_name");
                String floor = rs.getString("floor");
                int price = rs.getInt("price");
                String hotel_name = rs.getString("hotel_name");
                String category_name = rs.getString("categoryname");
                
                list.add(new RoomDTO(id, roomname, floor, price, hotel_name, category_name));
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public boolean checkDuplicate(String roomname) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "SELECT room_name FROM tbl_room";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (roomname.equals(rs.getString("room_name"))) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }
}
