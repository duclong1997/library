package com.book.library.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;

@Data
@Entity(name ="book")
@Table(name ="book")
public class Book implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idbook", unique = true, nullable = false)
	private int id;
	
	@Column(name="name_book", nullable=false)
	private String nameBook;
	
	@Column(name="time_create", insertable=false, updatable=false)
	private Timestamp timeCreate;
	
	@Column(name="time_update", insertable=false, updatable=false)
	private Timestamp timeUpdate;
	
	@Column(name="enable", insertable=false, updatable =true)
	private int enable;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "idcategory", nullable = false)
	private Category category;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy = "book")
	private List<BookBrow> listBookBrow = new ArrayList<>();
}
