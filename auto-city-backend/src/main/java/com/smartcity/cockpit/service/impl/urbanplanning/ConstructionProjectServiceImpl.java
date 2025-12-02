package com.smartcity.cockpit.service.impl.urbanplanning;

import com.smartcity.cockpit.entity.urbanplanning.ConstructionProject;
import com.smartcity.cockpit.repository.urbanplanning.ConstructionProjectRepository;
import com.smartcity.cockpit.service.urbanplanning.ConstructionProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConstructionProjectServiceImpl implements ConstructionProjectService {

    @Autowired
    private ConstructionProjectRepository constructionProjectRepository;

    @Override
    public List<ConstructionProject> getAllConstructionProjects() {
        return constructionProjectRepository.findAll();
    }

    @Override
    public ConstructionProject getConstructionProjectById(Long id) {
        Optional<ConstructionProject> project = constructionProjectRepository.findById(id);
        return project.orElse(null);
    }

    @Override
    public ConstructionProject createConstructionProject(ConstructionProject constructionProject) {
        constructionProject.setCreatedTime(LocalDateTime.now());
        constructionProject.setUpdatedTime(LocalDateTime.now());
        return constructionProjectRepository.save(constructionProject);
    }

    @Override
    public ConstructionProject updateConstructionProject(Long id, ConstructionProject constructionProject) {
        Optional<ConstructionProject> existingProject = constructionProjectRepository.findById(id);
        if (existingProject.isPresent()) {
            ConstructionProject project = existingProject.get();
            project.setProjectName(constructionProject.getProjectName());
            project.setProjectDescription(constructionProject.getProjectDescription());
            project.setProjectType(constructionProject.getProjectType());
            project.setLocation(constructionProject.getLocation());
            project.setBudget(constructionProject.getBudget());
            project.setStartDate(constructionProject.getStartDate());
            project.setEndDate(constructionProject.getEndDate());
            project.setProgress(constructionProject.getProgress());
            project.setManager(constructionProject.getManager());
            project.setStatus(constructionProject.getStatus());
            project.setUpdatedTime(LocalDateTime.now());
            return constructionProjectRepository.save(project);
        }
        return null;
    }

    @Override
    public void deleteConstructionProject(Long id) {
        constructionProjectRepository.deleteById(id);
    }

    @Override
    public List<ConstructionProject> getConstructionProjectsByType(String projectType) {
        return constructionProjectRepository.findByProjectType(projectType);
    }

    @Override
    public List<ConstructionProject> getConstructionProjectsByStatus(String status) {
        return constructionProjectRepository.findByStatus(status);
    }
}