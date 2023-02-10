package edu.fra.uas.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fra.uas.resource.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
