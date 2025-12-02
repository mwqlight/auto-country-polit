package com.smartcity.cockpit.repository.urbanplanning;

import com.smartcity.cockpit.entity.urbanplanning.ConstructionProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructionProjectRepository extends JpaRepository<ConstructionProject, Long> {
    List<ConstructionProject> findByProjectType(String projectType);
    List<ConstructionProject> findByStatus(String status);
    List<ConstructionProject> findByManager(String manager);
}