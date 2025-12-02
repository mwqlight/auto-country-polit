package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.PublicService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicServiceRepository extends JpaRepository<PublicService, Long> {
    
    List<PublicService> findByServiceType(String serviceType);
    
    List<PublicService> findByStatus(String status);
    
    List<PublicService> findByServiceArea(String serviceArea);
    
    List<PublicService> findByNameContaining(String name);
    
    @Query("SELECT ps FROM PublicService ps WHERE ps.latitude BETWEEN :minLat AND :maxLat AND ps.longitude BETWEEN :minLng AND :maxLng")
    List<PublicService> findByLocationRange(
        @Param("minLat") Double minLat,
        @Param("maxLat") Double maxLat,
        @Param("minLng") Double minLng,
        @Param("maxLng") Double maxLng
    );
}