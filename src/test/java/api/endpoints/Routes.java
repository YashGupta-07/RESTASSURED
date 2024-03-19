package api.endpoints;

public final class Routes {
	public static String base_url = "https://reqres.in/api";
    
	// User module
	
	 public static String post_url = base_url + "/users";
	 public static String get_url = base_url + "/users/{username}";
	 public static String update_url = base_url + "/users/{username}";
	 public static String delete_url = base_url + "/users/{username}";
}
