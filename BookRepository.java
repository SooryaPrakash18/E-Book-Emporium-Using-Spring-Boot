package backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.entity.Book;

public interface BookRepository extends JpaRepository<Book, String> {
	Book findByBookname(String bookname);
	List<Book> findByCart(int cart);

}
