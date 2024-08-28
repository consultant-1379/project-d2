package com.ericsson.graduate.group2.CIstabilityproject.repositories;

import com.ericsson.graduate.group2.CIstabilityproject.entities.BuildEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BuildRepository extends JpaRepository<BuildEntity, Integer> {
}
