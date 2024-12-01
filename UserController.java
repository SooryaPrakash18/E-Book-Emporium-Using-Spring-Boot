package backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import backend.dao.UserRepository;
import backend.entity.user;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Controller
public class UserController {
	@Autowired
	private UserRepository repo;
	
	@GetMapping("/adduser")
	public String adduser(Model model) {
		model.addAttribute("user", new user());
		return "login";
	}
	
	@PostMapping("/useradded")
    public String processRegistration(user user) {
        repo.save(user);
        return "login";
    }
	
	@GetMapping("/logout")
	public String logout(HttpServletResponse response, HttpServletRequest request) {
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if ("username".equals(cookie.getName()) || "password".equals(cookie.getName())) {
	                cookie.setValue(null);
	                cookie.setMaxAge(0);
	                response.addCookie(cookie);
	            }
	        }
	    }
	    return "redirect:/loginPage";
	}

	
	@PostMapping("/authenticate")
    public String login(@RequestParam String username, 
                        @RequestParam String password,
                        HttpServletResponse response,
                        Model model) {
        user users = repo.findByUsername(username);
        if (users != null && users.getPassword().equals(password)) {
        	Cookie usernameCookie = new Cookie("username", username);
            Cookie passwordCookie = new Cookie("password", password);
            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);
        	if(users.getRole().equals("customer")) {
        		return "redirect:/";
        	}else if(users.getRole().equals("seller")) {
        		return "redirect:/AddProduct";
        	}
        }
        model.addAttribute("error", "Invalid username or password");
        return "403";
    }
	
	
}


