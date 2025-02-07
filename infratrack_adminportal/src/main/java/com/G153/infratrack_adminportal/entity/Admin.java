package com.G153.infratrack_adminportal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admins")  // MongoDB Collection
public class Admin {
    @Id

    private String adminId;
    private String password;

    public Admin() {}

    public Admin(String adminNo, String password) {
        this.adminId = adminNo;
        this.password = password;
    }

    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
