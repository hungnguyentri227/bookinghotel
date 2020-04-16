/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.DAO;

import hung.DTO.BillDTO_FB;
import hung.DTO.BillDTO;
import hung.DTO.UserDTO;
import hung.dbutils.Dbutils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.naming.NamingException;
import org.apache.log4j.Logger;

/**
 *
 * @author hungn
 */
public class UserDAO {

    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    private final Logger log = Logger.getLogger(this.getClass());

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

    public int Countrow() {
        int count = 0;
        try {
            conn = Dbutils.makeConnection();
            String sql = "Select count(*) from tbl_booking";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            rs.next();

            count = rs.getInt(1);
        } catch (Exception e) {
        }
        return count;
    }

    public UserDTO checkLogin(String username, String password) {
        UserDTO list = null;
        try {
            conn = Dbutils.makeConnection();
            if (conn != null) {
                String sql = "SELECT userid, username, password, fullname, role, action"
                        + " FROM tbl_user WHERE username=? and password=? and action=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setBoolean(3, true);

                rs = stm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("userid");
                    String username1 = rs.getString("username");
                    String password1 = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String role = rs.getString("role");
                    boolean action = rs.getBoolean("action");
                    list = new UserDTO(id, username1, password1, fullname, role, action);
                }
            }
        } catch (Exception e) {
            
        } 
        finally {
            closeConnection();
        }
        return list;
    }

    public boolean booking(BillDTO dto, Map<Integer, BillDTO> list) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "INSERT tbl_booking(bookid, username, hotelname, rooname, categoryname, totalprice, datebooking) VALUES(?,?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, dto.getBillID());
            stm.setString(2, dto.getUsername());
            stm.setString(3, dto.getHotelName());
            stm.setString(4, dto.getRoom());
            stm.setString(5, dto.getCategoryName());
            stm.setFloat(6, dto.getTotalprice());
            stm.setString(7, dto.getDatebooking());

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
    
    public boolean booking_fb(BillDTO_FB dto, Map<Integer, BillDTO_FB> list) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "INSERT tbl_booking(bookid, username, hotelname, rooname, categoryname, totalprice, datebooking) VALUES(?,?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, dto.getBillID());
            stm.setString(2, dto.getFaceID());
            stm.setString(3, dto.getHotelName());
            stm.setString(4, dto.getRoom());
            stm.setString(5, dto.getCategoryName());
            stm.setFloat(6, dto.getTotalprice());
            stm.setString(7, dto.getDatebooking());

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

    public boolean Insert(UserDTO dto) throws NamingException, SQLException{
        try {
            conn = Dbutils.makeConnection();
            String sql = "Insert tbl_user(username, password, fullname, role, action) VALUES(?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getUsername());
            stm.setString(2, dto.getPassword());
            stm.setString(3, dto.getFullname());
            stm.setString(4, dto.getRole());
            stm.setBoolean(5, true);

            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } 
        finally {
            closeConnection();
        }
        return false;
    }

    public ArrayList<BillDTO> showHistoryBooking(String datebook, int start, int end) {
        ArrayList<BillDTO> list = new ArrayList<>();
        try {
            conn = Dbutils.makeConnection();
            String sql = "SELECT bookid, username,hotelname, rooname, categoryname, totalprice "
                    + "FROM tbl_booking WHERE datebooking= ? "
                    + "order by bookid offset ? rows fetch next ? row only";
            stm = conn.prepareStatement(sql);
            stm.setString(1, datebook);
            stm.setInt(2, start);
            stm.setInt(3, end);
            rs = stm.executeQuery();

            while (rs.next()) {
                int bookid = rs.getInt("bookid");
                String username = rs.getString("username");
                String hotelname = rs.getString("hotelname");
                String roomname = rs.getString("rooname");
                String categoryname = rs.getString("categoryname");
                Float totalprice = rs.getFloat("totalprice");
                list.add(new BillDTO(bookid, username, hotelname, roomname, categoryname, datebook, totalprice));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<UserDTO> showDetailsbyRole(String role, int start, int end) {
        ArrayList<UserDTO> list = new ArrayList<>();
        try {
            conn = Dbutils.makeConnection();
            String sql = "SELECT userid, username, password, fullname, role FROM tbl_user WHERE role =? and action=?"
                    + " order by userid offset ? rows fetch next ? row only";
            stm = conn.prepareStatement(sql);
            stm.setString(1, role);
            stm.setBoolean(2, true);
            stm.setInt(3, start);
            stm.setInt(4, end);
            rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userid");
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                String fullname = rs.getString("fullname");
                String role1 = rs.getString("role");
                list.add(new UserDTO(id, username1, password1, fullname, role1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean Delele(int id) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "Update tbl_user SET action=? WHERE userid=?";
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
    
    public boolean checkDuplicate(String username) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "SELECT username FROM tbl_user";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (username.equals(rs.getString("username"))) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }
}
