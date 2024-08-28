package com.ericsson.graduate.group2.CIstabilityproject.services;

import com.ericsson.graduate.group2.CIstabilityproject.entities.T1Entity;
import com.ericsson.graduate.group2.CIstabilityproject.repositories.JobNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobNameService implements IJobNameService{

    @Autowired
    private JobNameRepository repository;

    @Override
    public List<T1Entity> findAll() {
        return (List<T1Entity>) repository.findAll();
    }

    public int getId(String jobName) {
        Iterable<T1Entity> myList = repository.findAll();
        for(T1Entity x: myList) {
            if (x.getJob_name().equalsIgnoreCase(jobName)) {
                return x.getId();
            }
        }
        return -1;
    }

}
