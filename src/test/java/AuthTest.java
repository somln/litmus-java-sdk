import io.litmuschaos.LitmusClient;
import java.io.IOException;

public class AuthTest {

    private static final String hostUrl = "http://localhost:3000";
    private static final String username = "admin";
    private static final String password = "Litmus1234!";

    public static void main(String[] args) throws IOException {

        var authClient = new LitmusClient(hostUrl, username, password);

        System.out.println("### capabilities test");
        var capabilities = authClient.capabilities(hostUrl);
        System.out.println(capabilities);

        System.out.println("### createProject test");
        var project = authClient.createProject(
                hostUrl,
                "TEST_Project_9");
        System.out.println(project);

        var auth = authClient.authenticate(hostUrl, username, password);
        System.out.println("### refresh token test");
        System.out.println("access Token :: " + auth.getAccessToken());

        System.out.println("### getListProjects test");
        var listProjects = authClient.getListProjects(hostUrl);
        System.out.println(listProjects);

        System.out.println("### updateProjectName test");
        var projectNameResponse = authClient.updateProjectName(hostUrl,
                "db8d1fc2-c8f4-413f-a833-56c1978be3c3", "newProjectName");
        System.out.println(projectNameResponse);

        System.out.println("### getProject test");
        var getProjectResponse = authClient.getProject(hostUrl,
                "db8d1fc2-c8f4-413f-a833-56c1978be3c3");
        System.out.println(getProjectResponse);

//        System.out.println("### getOwnerProject test");
//        var ownerProjects = authClient.getOwnerProject(hostUrl);
//        System.out.println(ownerProjects);

        System.out.println("### leaveProject test");
        var LeaveProjectResponse = authClient.leaveProject(hostUrl,
                "db8d1fc2-c8f4-413f-a833-56c1978be3c3", "79c3506c-273e-4018-a062-ff2cc4fbb248");
        System.out.println(LeaveProjectResponse);

        System.out.println("### getProjectRole test");
        var projectRole = authClient.getProjectRole(hostUrl, "db8d1fc2-c8f4-413f-a833-56c1978be3c3");
        System.out.println(projectRole);

        System.out.println("### getUserWithProject test");
        var UserWithProjectResponse = authClient.getUserWithProject(hostUrl);
        System.out.println(UserWithProjectResponse);


    }
}
