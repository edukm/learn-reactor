package br.com.thinkdevelop.learnreactor.domain;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Document(collection = "accounts")
public class Account {

    @Id
    private String id;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy mm:ss")
    private Date creationDate;

    private Double amount;

    private Currency currency;

    public Account() {
    }

    public Account(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", amount=" + amount +
                ", currency=" + currency +
                '}';
    }
    
    public static void main(String[] args) throws JsonProcessingException {
    	
    	Account a = new Account();
    	a.setAmount(12.50);
    	a.setCreationDate(new Date());
    	a.setCurrency(Currency.BRL);
    	a.setId("1");
		System.out.println(new ObjectMapper().writeValueAsString(a));
	}
}

