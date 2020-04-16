/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.user;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author anhndSE130595
 */
public class UserInfo {
    @SerializedName(value = "userId") private String id;
    @SerializedName(value = "id") private String faceID;
    private String name;
    private String username;

    public UserInfo() {
    }

    public String getId() {
        return id;
    }

    public String getFaceID() {
        return faceID;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFaceID(String faceID) {
        this.faceID = faceID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
