/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.DAO;

import hung.DTO.UserDTO;
import hung.dbutils.Dbutils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author hungn
 */
public class CustomerDAO {

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

    public ArrayList<UserDTO> showDetailsbyRole(String role, int start, int end) {
        ArrayList<UserDTO> list = new ArrayList<>();
        try {
            conn = Dbutils.makeConnection();
            String sql = "SELECT userid, username, password, fullname, role FROM tbl_user WHERE role = ? and action=? "
                    + "order by userid offset ? rows fetch next ? row only";
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
    
    public UserDTO showDetailsCustomer(String username) {
        UserDTO dto = null;
        try {
            conn = Dbutils.makeConnection();
            String sql = "Select userid, username, password, fullname, role FROM tbl_user WHERE username=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            
            if (rs.next()) {
                int id1 = rs.getInt("userid");
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                String fullname = rs.getString("fullname");
                String role1 = rs.getString("role");
                dto = new UserDTO(id1, username1, password1, fullname, role1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public boolean updateCustomer(UserDTO dto) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "Update tbl_user SET username=?, password=?, fullname=? WHERE userid=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getUsername());
            stm.setString(2, dto.getPassword());
            stm.setString(3, dto.getFullname());
            stm.setInt(4, dto.getUserid());
            
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
    
    public boolean deleteCustomer(int id) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "Update tbl_user SET isAction=? WHERE userid=?";
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
    
    public boolean changePassword(String newPass, String name) {
        try {
            conn = Dbutils.makeConnection();
            String sql = "Update tbl_user SET password=? WHERE username=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, newPass);
            stm.setString(2, name);
            
            if (stm.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public int Countrow() {
        int count = 0;
        try {
            conn = Dbutils.makeConnection();
            String sql = "Select count(*) from tbl_user";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            rs.next();

            count = rs.getInt(1);
        } catch (Exception e) {
        }
        return count;
    }
    
}
