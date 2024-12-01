package backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import backend.dao.BookRepository;
import backend.dao.UserRepository;
import backend.entity.Book;
import backend.entity.user;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BookController {

    @Autowired
    BookRepository repo;
    
    @Autowired
    UserRepository repo1;

    @GetMapping("/AddProduct")
    public String AddProduct(Model model) {
        model.addAttribute("book", new Book());
        return "AddProduct";
    }

    @PostMapping("/bookadded")
    public String bookadded(@ModelAttribute Book book) {
        repo.save(book);
        return "redirect:/allproducts";
    }

    @GetMapping("/allproducts")
    public String viewAllProducts(Model model) {
        List<Book> books = repo.findAll();
        model.addAttribute("books", books);
        return "book";
    }

    @PostMapping("/addcart1")
    public String addCart(@RequestParam String productid, Model model) {
        Book product = repo.findByBookname(productid);
        if (product != null) {
            product.setCart(1);
            repo.save(product);
        }
        return "redirect:/addcart";
    }
    @PostMapping("/deletecart")
    public String deletecart(@RequestParam String productid, Model model) {
        Book product = repo.findByBookname(productid);
        System.out.print(productid);
        if (product != null) {
            product.setSolded(0);
            repo.save(product);
            }
        
        return "redirect:/addcart";
        
    }
    @GetMapping("/addcart")
    public String addcart(Model model) {
    	List<Book> allProducts = repo.findByCart(1);
        model.addAttribute("allProducts", allProducts);
        return "addcart";

    }
   @PostMapping("/forget")
   public String forget(@RequestParam String password, @RequestParam String username) {
	   user forget=repo1.findByUsername(username);
	   System.out.println(username);
	   if(forget !=null) {
		   forget.setPassword(password);
		   repo1.save(forget);
		   
	   }
	   return  "redirect:/adduser";
	   
   }
   @GetMapping("/search")
   public String search(@RequestParam String name, Model model) {
       List<Book> products = repo.findAll();
       List<Book> result = products.stream()
           .filter(product -> Arrays.stream(product.getBookname().split(" "))
               .anyMatch(word -> word.equalsIgnoreCase(name)))
           .collect(Collectors.toList());
       model.addAttribute("books", result);

       return "search";
   }
   
   @PostMapping("/updatesolded")
   public String updatesolded() {
	   List<Book> book = repo.findByCart(1);
	   for(Book book1:book) {
		   book1.setSolded(book1.getSolded()+1);
		   repo.save(book1);
	   }
	   return "payment";
   }
   
   @GetMapping("/admin")
   public String admin(Model model) {
	   List<Book> book = repo.findAll();
	   model.addAttribute("book", book);
	   List<user> users = repo1.findAll();
	   model.addAttribute("user", users);
	   return "admin";
   }
   
   @PostMapping("/deletebook")
   public String deletebook(@RequestParam("bookname") String bookname) {
	   repo.deleteById(bookname);
	   return "redirect:/admin";
   }
   
   @PostMapping("/deleteuser")
   public String deleteuser(@RequestParam("username") String username) {
	   repo1.deleteById(username);
	   return "redirect:/admin";
   }
}
