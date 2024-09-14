package io.litmuschaos.response;

import java.util.List;

public class ProjectListResponse {
    private List<ProjectResponse> data;

    public ProjectListResponse(List<ProjectResponse> data) {
        this.data = data;
    }

    public List<ProjectResponse> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ListProjectsResponse{" +
                "data=" + data +
                '}';
    }
}