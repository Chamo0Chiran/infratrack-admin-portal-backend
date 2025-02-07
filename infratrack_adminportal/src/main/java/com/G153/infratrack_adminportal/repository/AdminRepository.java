package com.G153.infratrack_adminportal.repository;
import com.G153.infratrack_adminportal.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin> findByAdminId(String adminId);
}
