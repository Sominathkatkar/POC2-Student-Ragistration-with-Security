package com.neo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neo.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
