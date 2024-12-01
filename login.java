package backend.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import backend.dao.BookRepository;
import backend.entity.Book;

@Controller
public class login {
	
	@Autowired
    BookRepository repo;
	
	
	@GetMapping("/")
	public String home(Model model) {
		List<Book> books = repo.findAll();
        model.addAttribute("books", books);
		return "index";
	}
	
	@GetMapping("/cart")
	public String cart(){
		return "addcart";
	}
		@GetMapping("/view")
		public String view() {
			return "viewread";
		}
		
		@GetMapping("/Book")
		public String Book() {
			return "redirect:/allproducts";
		}
		@GetMapping("/About")
		public String About() {
			return "aboutus";
		}
		@GetMapping("/Ebook")
		public String Ebook() {
			return "ebook";
		}
		@GetMapping("/Read")
		public String Read() {
			return "readmore";
		}
		@GetMapping("/Contact")
		public String Contact() {
			return "contact";
		}
		@GetMapping("/Buynow")
		public String Buynow() {
			return "payment";
			
		}
		@GetMapping("/Signup")
		public String Signup() {
			return "login";
		}
		@GetMapping("/Forget")
		public String Forget() {
			return "forget";
		}
		
		}
