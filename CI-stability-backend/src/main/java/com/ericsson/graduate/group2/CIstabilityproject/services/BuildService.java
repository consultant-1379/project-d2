package com.ericsson.graduate.group2.CIstabilityproject.services;

import com.ericsson.graduate.group2.CIstabilityproject.entities.BuildEntity;
import com.ericsson.graduate.group2.CIstabilityproject.repositories.BuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildService implements IBuildService{

    @Autowired
    private BuildRepository repository;

    @Override
    public List<BuildEntity> findAll() {
        return (List<BuildEntity>) repository.findAll();
    }
}
