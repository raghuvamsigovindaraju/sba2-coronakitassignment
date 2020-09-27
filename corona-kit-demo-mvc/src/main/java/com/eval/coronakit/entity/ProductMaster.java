package com.eval.coronakit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="prodmaster")
public class ProductMaster {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="prodname")
	@NotNull(message = "product name is requried")
	@Size(min=2,max=25,message = "product name is requried to be of 2 to 25 chars in length")
	private String productName;
	
	@Column(name="cost")
	@NotNull(message = "cost is requried")
	@Min(value=10,message = "cost should be minimum 10 rupees")
	private Integer cost;
	
	@Column(name="prodesc")
	@NotNull(message = "product desc is requried")
	@Size(min=5,message = "product desc is requried to be of minimum 5 chars in length")
	private String productDescription;
	
	public ProductMaster() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductMaster(int id, String productName, Integer cost, String productDescription) {
		super();
		this.id = id;
		this.productName = productName;
		this.cost = cost;
		this.productDescription = productDescription;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Override
	public String toString() {
		return "ProductMaster [id=" + id + ", productName=" + productName + ", cost=" + cost + ", productDescription="
				+ productDescription + "]";
	}

}

	


