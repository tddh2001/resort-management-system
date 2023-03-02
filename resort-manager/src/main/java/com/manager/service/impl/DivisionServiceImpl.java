package com.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manager.model.Division;
import com.manager.repository.DivisionRepository;
import com.manager.service.DivisionService;

import java.util.List;

@Service
public class DivisionServiceImpl  implements DivisionService {
    @Autowired
    DivisionRepository divisionRepository;
    @Override
    public List<Division> findAll() {
        return divisionRepository.findAll();
    }
}
