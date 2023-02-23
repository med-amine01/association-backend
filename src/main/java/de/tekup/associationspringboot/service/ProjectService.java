package de.tekup.associationspringboot.service;

import com.github.javafaker.Faker;
import de.tekup.associationspringboot.entity.Patient;
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

    //GENERATING FAKE DATA FOR PATIENT
    public Iterable<Project> generateData(){
        Faker faker = new Faker();
        List<Project> projectList = new ArrayList<>();
        for(int i=0; i<15; i++){
            Project p = new Project();
            p.setProjectName(faker.company().name());
            p.setEstimatedBudget(faker.number().randomDouble(2,999,9999));
            p.setTotalAmountSpent(faker.number().randomDouble(2,999,9999));
            p.setProjectLeader(faker.name().fullName());
            p.setProjectDescription(faker.howIMetYourMother().catchPhrase());
            p.setDuration(String.valueOf(faker.number().randomDigit()));
            if(p.getTotalAmountSpent() >= p.getEstimatedBudget()){
                p.setProjectStatus("Success");
            }
            else{
                p.setProjectStatus("On Hold");
            }
            projectList.add(p);
        }
        return projectRepository.saveAll(projectList);
    }

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
