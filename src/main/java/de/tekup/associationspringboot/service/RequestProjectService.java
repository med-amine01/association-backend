package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Project;
import de.tekup.associationspringboot.entity.Request;
import de.tekup.associationspringboot.entity.RequestProject;
import de.tekup.associationspringboot.entity.RequestProjectId;
import de.tekup.associationspringboot.repository.RequestProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RequestProjectService {
    private RequestProjectRepository requestProjectRepository;
    private ProjectService projectService;

    public void addRequestToProject(Request request, Project project, double amount) {
        requestProjectRepository.save(new RequestProject(
                new RequestProjectId(request.getId(), project.getId()),
                project,
                request,
                amount
        ));
    }

    public void splitAmountOnSelectedProjects(Request request){
        if(request.getRequestedAmount() > 0){
            double eachAmount = request.getRequestedAmount() / request.getProjects().size();

            request.getProjects().forEach(project -> {
                addRequestToProject(request,project,Math.round(eachAmount));
                updateAmount(project.getId(), Math.round(eachAmount));
            });
        }
    }

    private void updateAmount(Long projectId, double amount){
        Project p = projectService.getProject(projectId);

        /**
         *  lina aana montant estimé exemple 1000 dt
         *  w amount spent (eli tissraf aal projet)
         *  donc lezm nzidou fili tissraf puisque funder 7at fih flouss
         */

        double val = Math.round(p.getTotalAmountSpent() + amount);
        if(val <= 0){
            p.setTotalAmountSpent(0);
        }
        else{
            p.setTotalAmountSpent(val);
        }
        projectService.updateProject(p);
    }
}
