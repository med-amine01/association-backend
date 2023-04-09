package de.tekup.associationspringboot.controller;

import de.tekup.associationspringboot.entity.Project;
import de.tekup.associationspringboot.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@AllArgsConstructor

public class ProjectController {

    private ProjectService projectService;

    //GENERATE FAKE DATA BY HITTING THIS ENDPOINT
    @GetMapping("/generate")
    public Iterable<Project> generateData(){
        return this.projectService.generateData();
    }
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id") Long id){
        return projectService.getProject(id);
    }
    @GetMapping("/getall")
    public List<Project> getAll(){
        return projectService.getAllProjects();
    }

    @PostMapping("/add")
    public Project createProject(@RequestBody Project Project){
        return projectService.addProject(Project);
    }

    @PatchMapping("/update")
    public Project updateProject(@RequestBody Project Project){
        return projectService.updateProject(Project);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
    }

}
