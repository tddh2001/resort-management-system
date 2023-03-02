package com.manager.service.impl;

import com.manager.model.Gender;
import com.manager.repository.GenderRepository;
import com.manager.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl implements GenderService {
    @Autowired
    GenderRepository genderRepository;
    @Override
    public Iterable<Gender> findAll() {
        return genderRepository.findAll();
    }
}
