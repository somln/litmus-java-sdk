package io.litmuschaos;

import io.litmuschaos.http.LitmusHttpClient;
import io.litmuschaos.request.LeaveProjectRequest;
import io.litmuschaos.request.ProjectNameRequest;
import io.litmuschaos.request.LoginRequest;
import io.litmuschaos.response.CommonResponse;
import io.litmuschaos.response.ListProjectsResponse;
import io.litmuschaos.response.LoginResponse;
import io.litmuschaos.response.ProjectListResponse;
import io.litmuschaos.response.ProjectMemberResponse;
import io.litmuschaos.response.ProjectResponse;

import io.litmuschaos.response.ProjectRoleResponse;
import io.litmuschaos.response.ProjectsStatusResponse;
import io.litmuschaos.response.UserWithProjectResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Response;


public class LitmusClient implements AutoCloseable {

    private String token;

    private final LitmusHttpClient httpClient = new LitmusHttpClient();

    public LitmusClient(String host, String username, String password) throws IOException {
        LoginResponse credential = this.authenticate(host, username, password);
        this.token = credential.getAccessToken();
    }

    @Override
    public void close() throws Exception {
        // TODO
    }

    // TODO - @Suyeon Jung : host, port config to LitmusAuthConfig class
    public LoginResponse authenticate(String host, String username, String password) throws IOException {
        LoginRequest request = new LoginRequest(username, password);
        LoginResponse response = httpClient.post(host + "/login", request, LoginResponse.class);
        this.token = response.getAccessToken();
        return response;
    }

    public ProjectResponse createProject(String host, String projectName) throws IOException {
        Map<String, String> request = new HashMap<>();
        request.put("projectName", projectName);
        return httpClient.post(host + "/create_project", token,  request, ProjectResponse.class);
    }

    // TODO - define Response dto
    public String capabilities(String host) throws IOException {
        return httpClient.get(host + "/capabilities", String.class);
    }

    public ListProjectsResponse getListProjects(String host) throws IOException {
         return httpClient.get(host + "/list_projects", token, ListProjectsResponse.class);
    }

    public CommonResponse updateProjectName(String host, String projectID, String projectName ) throws IOException {
        ProjectNameRequest request = new ProjectNameRequest(projectID, projectName);
        return httpClient.post(host + "/update_project_name", token, request, CommonResponse.class);
    }

    public ProjectResponse getProject(String host, String projectId) throws IOException {
        return httpClient.get(host + "/get_project/" + projectId, token, ProjectResponse.class);
    }

//    public ProjectListResponse getOwnerProject(String host) throws IOException {
//        return httpClient.get(host + "/get_owner_projects", token, ProjectListResponse.class);
//    }

    public CommonResponse leaveProject(String host, String projectID, String userID) throws IOException {
        LeaveProjectRequest request = new LeaveProjectRequest(projectID, userID);
        return httpClient.post(host+"/leave_project", token, request, CommonResponse.class);
    }

    public ProjectRoleResponse getProjectRole(String host, String projectId) throws IOException {
        return httpClient.get(host+"/get_project_role/"+ projectId, token, ProjectRoleResponse.class);
    }

    public UserWithProjectResponse getUserWithProject(String host) throws IOException {
        return httpClient.get(host + "/get_user_with_project/admin", token, UserWithProjectResponse.class);
    }

//    public List<ProjectsStatusResponse> getProjectsStats(String host) throws IOException {
//        return httpClient.get(host + "/get_projects_stats" , token, ProjectsStatusResponse.class);
//    }

//    public List<ProjectMemberResponse> getProjectMembers(String host, String projectID, String status) throws IOException {
//        return httpClient.get(host + "/get_project_members/"+projectID+"/"+status , token, ProjectMemberResponse.class);
//    }

 }