package com.book.library.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="bookbrow")
@Table(name="bookbrow")
public class BookBrow implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idbook_brow", unique = true, nullable = false)
	private int id;
	
	@Column(name="start_brow", nullable=false)
	private Timestamp startBrow;
	
	@Column(name="end_brow", nullable=false)
	private Timestamp endBrow;
	
	@Column(name="time_create", insertable=false, updatable=false)
	private Timestamp timeCreate;
	
	@Column(name="time_update", insertable=false, updatable=false)
	private Timestamp timeUpdate;
	
	@Column(name="enable", insertable=false, updatable =true)
	private int enable;
	
	@ManyToOne()
	@JoinColumn(name = "idbook", nullable = false)
	private Book book;
	
	@ManyToOne()
	@JoinColumn(name = "iduser", nullable = false)
	private User user;
}
