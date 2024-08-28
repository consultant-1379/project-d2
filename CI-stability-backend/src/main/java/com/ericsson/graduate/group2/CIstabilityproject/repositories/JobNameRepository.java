package com.ericsson.graduate.group2.CIstabilityproject.repositories;

import com.ericsson.graduate.group2.CIstabilityproject.entities.T1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobNameRepository extends JpaRepository<T1Entity, Integer> {
}

