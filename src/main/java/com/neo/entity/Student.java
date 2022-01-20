package com.neo.entity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sid;
	
	@Column(name="FIRST_NAME", nullable=false)
	private String sName;
	
	
	  @Column(name="LAST_NAME", nullable=false) 
	  private String lName;
	 
	
	@Column(name="EMAIL", nullable=false)
	private String email;
	
	@Column(name="MOB_NO", nullable=false)
	private long mob;
	
	
	  @OneToMany(targetEntity = Project.class, cascade = CascadeType.ALL)
	   @JoinColumn(name = "sp_fk", referencedColumnName = "sid")
	  private List<Project> projects;
	 
}
