package edu.fra.uas.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

public class LoginController {
    private Map<String, String> users;

    public LoginController() {
        users = new HashMap<>();
        users.put("admin", "password");
        users.put("user1", "pass1");
        users.put("user2", "pass2");
    }

    public boolean checkCredentials(String username, String password) {
        if (users.containsKey(username)) {
            return users.get(username).equals(password);
        }
        return false;
    }

    public boolean login(String username, String password) {
        if (checkCredentials(username, password)) {
            // Code to log user in
            return true;
        }
        return false;
    }
}





	
	

