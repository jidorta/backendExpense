package com.ibandorta.api.entity;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name="tbl_expenses")
public class Expense {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;
	
	@Column(name="expense_name")
	@NotBlank(message ="Expense name must not be null")
	@Size(min= 3, message="Expense name atleast 3 characters")
	private String name;
	
	
	
	private String description;
	
	@Column(name="expense_amount")
	@NotNull(message="Expense amount should not be null")
	private BigDecimal amount;
	
	@NotBlank(message="Category should not be null")
	private String category;
	
	@NotNull(message="Date must not be null")
	private Date date;
	


	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Expense(Long id, String name, String description, BigDecimal amount, String category, Date date
			) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.category = category;
		this.date = date;
		
	}



	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
}
