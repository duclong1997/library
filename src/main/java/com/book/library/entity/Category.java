package com.book.library.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity(name="category")
@Table(name="category")
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="idcategory")
	private int idCategory;
	
	@Column(name="name_category", nullable=false)
	private String nameCategory;
	
	@Column(name="time_create", insertable=false, updatable=false)
	private Timestamp timeCreate;
	
	@Column(name="time_update", insertable=false, updatable =false)
	private Timestamp timeUpdate;
	
	@Column(name="enable", insertable=false, updatable=true)
	private int enable;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy = "category")
	private List<Book> listBook= new ArrayList<>();
	
}
