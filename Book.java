package backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "bookname", nullable = false, unique = true, length = 300)
    private String bookname;

    @Column(name = "ImageURL", nullable = true, length = 500)
    private String ImageURL;

    @Column(name = "bookprice", nullable = false)
    private int bookprice;

    @Column(name = "description", nullable = false, length = 2000)
    private String description;

    @Column(name = "cart", nullable = false)
    private int cart = 0;

    @Column(name = "solded", nullable = false)
    private int solded = 0;

    // Getters and Setters
    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public int getBookprice() {
        return bookprice;
    }

    public void setBookprice(int bookprice) {
        this.bookprice = bookprice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCart() {
        return cart;
    }

    public void setCart(int cart) {
        this.cart = cart;
    }

    public int getSolded() {
        return solded;
    }

    public void setSolded(int solded) {
        this.solded = solded;
    }
}
