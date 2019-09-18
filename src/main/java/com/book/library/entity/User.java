package com.book.library.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import lombok.NoArgsConstructor;

@Data
@Entity(name="user")
@Table(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="iduser")
	private int idUser;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="username", nullable=false)
	private String username;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="role", nullable=false)
	private String role;
	
	@Column(name="gender", nullable=false)
	private boolean gender;
	
	@Column(name="dob", nullable=false)
	private Date dob;
	
	@Column(name="time_create", insertable=false, updatable=false)
	private Timestamp timeCreate;
	
	@Column(name="time_update", insertable=false, updatable =false)
	private Timestamp timeUpdate;
	
	@Column(name="enable", insertable=false, updatable=true)
	private int enable;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy = "user")
	private List<BookBrow> listBookBrow= new ArrayList<>();
}
