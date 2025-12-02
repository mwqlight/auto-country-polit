package com.smartcity.cockpit.repository;

import com.smartcity.cockpit.entity.CityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityModelRepository extends JpaRepository<CityModel, Long> {
}