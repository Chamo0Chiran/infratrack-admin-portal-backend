package com.G153.infratrack_adminportal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admins")  // MongoDB Collection
public class Admin {
    @Id
    private String id;
    private String adminNo;
    private String password;

    public Admin() {}

    public Admin(String adminNo, String password) {
        this.adminNo = adminNo;
        this.password = password;
    }

    public String getAdminNo() { return adminNo; }
    public void setAdminNo(String adminNo) { this.adminNo = adminNo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
