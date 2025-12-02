package com.smartcity.cockpit.service.urbanplanning;

import com.smartcity.cockpit.entity.urbanplanning.ConstructionProject;

import java.util.List;

public interface ConstructionProjectService {
    List<ConstructionProject> getAllConstructionProjects();
    ConstructionProject getConstructionProjectById(Long id);
    ConstructionProject createConstructionProject(ConstructionProject constructionProject);
    ConstructionProject updateConstructionProject(Long id, ConstructionProject constructionProject);
    void deleteConstructionProject(Long id);
    List<ConstructionProject> getConstructionProjectsByType(String projectType);
    List<ConstructionProject> getConstructionProjectsByStatus(String status);
}