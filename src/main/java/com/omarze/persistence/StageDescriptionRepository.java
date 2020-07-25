package com.omarze.persistence;


import com.omarze.entities.StageDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * created by julian
 */
@Repository
@RepositoryRestResource(path = StageDescriptionRepository.PATH)
public interface StageDescriptionRepository extends JpaRepository<StageDescription, Long>  {


    String PATH = "stage_description";


}

