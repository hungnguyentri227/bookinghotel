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
public class UserDTO {
    private int userid;
    private String username;
    private String password;
    private String fullname;
    private String role;
    private boolean action;

    public UserDTO() {
    }

    public UserDTO(int userid, String username, String password, String fullname, String role, boolean action) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
        this.action = action;
    }
    
    

    public UserDTO(int userid, String username, String password, String fullname, String role) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    public UserDTO(String username, String password, String fullname, String role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }
    
    
    

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    
    
    
}
