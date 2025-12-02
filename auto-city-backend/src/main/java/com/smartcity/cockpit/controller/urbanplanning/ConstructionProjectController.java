package com.smartcity.cockpit.controller.urbanplanning;

import com.smartcity.cockpit.entity.urbanplanning.ConstructionProject;
import com.smartcity.cockpit.service.urbanplanning.ConstructionProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/urban-planning/construction-projects")
@CrossOrigin(origins = "*")
public class ConstructionProjectController {

    @Autowired
private ConstructionProjectService constructionProjectService;

    @GetMapping
    public ResponseEntity<List<ConstructionProject>> getAllConstructionProjects() {
        List<ConstructionProject> projects = constructionProjectService.getAllConstructionProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConstructionProject> getConstructionProjectById(@PathVariable Long id) {
        ConstructionProject project = constructionProjectService.getConstructionProjectById(id);
        if (project != null) {
            return ResponseEntity.ok(project);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ConstructionProject> createConstructionProject(@RequestBody ConstructionProject constructionProject) {
        ConstructionProject createdProject = constructionProjectService.createConstructionProject(constructionProject);
        return ResponseEntity.status(201).body(createdProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConstructionProject> updateConstructionProject(@PathVariable Long id, @RequestBody ConstructionProject constructionProject) {
        ConstructionProject updatedProject = constructionProjectService.updateConstructionProject(id, constructionProject);
        if (updatedProject != null) {
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConstructionProject(@PathVariable Long id) {
        constructionProjectService.deleteConstructionProject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{projectType}")
    public ResponseEntity<List<ConstructionProject>> getConstructionProjectsByType(@PathVariable String projectType) {
        List<ConstructionProject> projects = constructionProjectService.getConstructionProjectsByType(projectType);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ConstructionProject>> getConstructionProjectsByStatus(@PathVariable String status) {
        List<ConstructionProject> projects = constructionProjectService.getConstructionProjectsByStatus(status);
        return ResponseEntity.ok(projects);
    }
}