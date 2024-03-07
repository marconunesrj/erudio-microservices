package br.com.erudio.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookDTO implements Serializable{
        
    private static final long serialVersionUID = 1L;
    private Long id;
    private String author;
    private Date launchDate;
    private Double price;
    private String title;
    private String currency;
    private String environment;
    
    public BookDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, currency, environment, id, launchDate, price, title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BookDTO other = (BookDTO) obj;
        return Objects.equals(author, other.author) && Objects.equals(currency, other.currency)
                && Objects.equals(environment, other.environment) && Objects.equals(id, other.id)
                && Objects.equals(launchDate, other.launchDate) && Objects.equals(price, other.price)
                && Objects.equals(title, other.title);
    }

    
}
