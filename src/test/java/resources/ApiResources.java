package resources;

public enum ApiResources {
    registerAPI("users/register"),
    logInAPI("users/login"),
    userProfileAPI("/users/profile"),
    forgotPasswordAPI("/users/forgot-password"),
    changePasswordAPI("/users/change-password"),
    logoutAPI("/users/logout"),
    delteAccoutAPI("/users/delete-account"),

    // note api
    noteAPI("/notes");

    private String url;
    public String getURl()
    {
        return this.url;
    }
    ApiResources(String s) {
        this.url = s;
    }
}
