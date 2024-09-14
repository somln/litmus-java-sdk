package io.litmuschaos.response;

import java.util.List;

public class ListProjectsResponse {

    private List<ProjectResponse> projects;
    private int totalNumberOfProjects;

    public ListProjectsResponse(List<ProjectResponse> projects, int totalNumberOfProjects) {
        this.projects = projects;
        this.totalNumberOfProjects = totalNumberOfProjects;
    }

    public List<ProjectResponse> getProjects() {
        return projects;
    }

    public int getTotalNumberOfProjects() {
        return totalNumberOfProjects;
    }

    @Override
    public String toString() {
        return "ListProjectsResponse{" +
                "projects=" + projects +
                ", totalNumberOfProjects=" + totalNumberOfProjects +
                '}';
    }
}