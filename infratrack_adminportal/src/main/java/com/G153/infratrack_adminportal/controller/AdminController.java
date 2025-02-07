package com.G153.infratrack_adminportal.controller;

import com.G153.infratrack_adminportal.dto.AdminLoginRequest;
import com.G153.infratrack_adminportal.entity.Admin;
import com.G153.infratrack_adminportal.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin Management", description = "APIs for admin authentication and registration")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Operation(
            summary = "Login admin user",
            description = "Authenticates an admin user with their credentials and returns a JWT token"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully authenticated",
                    content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid credentials",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content
            )
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Parameter(description = "Admin login credentials", required = true)
            @Valid @RequestBody AdminLoginRequest request
    ) {
        System.out.println(request);
        return adminService.loginAdmin(request);
    }

    @Operation(
            summary = "Register new admin",
            description = "Creates a new admin account in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Admin successfully registered",
                    content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input or admin already exists",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Admin with given credentials already exists",
                    content = @Content
            )
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Parameter(description = "Admin registration details", required = true)
            @Valid @RequestBody Admin admin
    ) {
        return adminService.registerAdmin(admin);
    }
}