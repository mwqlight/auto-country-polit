package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.SecurityCamera;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface SecurityCameraRepository extends JpaRepository<SecurityCamera, Long> {
    
    Optional<SecurityCamera> findByCameraId(String cameraId);
    
    List<SecurityCamera> findByLocationContaining(String location);
    
    List<SecurityCamera> findByStatus(String status);
    
    List<SecurityCamera> findByIpAddress(String ipAddress);
}