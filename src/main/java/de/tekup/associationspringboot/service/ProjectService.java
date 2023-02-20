package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Project;
import de.tekup.associationspringboot.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ProjectService {
    private ProjectRepository projectRepository;

    public Project getProject(Long id){
        return projectRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("No Project with ID : " + id));
    }

    public List<Project> getAllProjects(){
        List<Project> Projects = new ArrayList<>();
        projectRepository.findAll().forEach(Projects::add);
        return Projects;
    }

    public Project addProject(Project Project){
        return projectRepository.save(Project);
    }

    public Project updateProject(Project Project){
        if(!projectRepository.existsById(Project.getId())){
            throw new NoSuchElementException("No Project with ID : " + Project.getId());
        }
        return projectRepository.save(Project);
    }

        public void deleteProject(Long id){
        if(!projectRepository.existsById(id)){
            throw new NoSuchElementException("No Project with ID : " + id);
        }
        projectRepository.deleteById(id);
    }
}
