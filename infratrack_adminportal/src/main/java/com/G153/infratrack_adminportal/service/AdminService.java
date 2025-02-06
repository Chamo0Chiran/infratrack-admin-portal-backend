package com.G153.infratrack_adminportal.service;
import com.G153.infratrack_adminportal.dto.AdminLoginRequest;
import com.G153.infratrack_adminportal.entity.Admin;
import com.G153.infratrack_adminportal.repository.AdminRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, JWTService jwtService, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> loginAdmin(AdminLoginRequest request) {
        Optional<Admin> adminOptional = adminRepository.findByAdminNo(request.getAdminNo());

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            if (passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
                return ResponseEntity.ok(jwtService.generateToken(request.getAdminNo()));
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        }
        return ResponseEntity.status(404).body("Admin not found");
    }

    public ResponseEntity<String> registerAdmin(Admin admin) {
        if (adminRepository.findByAdminNo(admin.getAdminNo()).isPresent()) {
            return ResponseEntity.badRequest().body("Admin already exists");
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));  // Hash password
        adminRepository.save(admin);
        return ResponseEntity.ok("Admin registered successfully");
    }
}
