package com.ericsson.graduate.group2.CIstabilityproject.services;

import com.ericsson.graduate.group2.CIstabilityproject.entities.BuildEntity;

import java.util.List;

public interface IBuildService {
    List<BuildEntity> findAll();
}
