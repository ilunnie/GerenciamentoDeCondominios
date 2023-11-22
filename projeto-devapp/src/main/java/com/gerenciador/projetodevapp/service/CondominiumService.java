package com.gerenciador.projetodevapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.projetodevapp.model.CondominiumModel;
import com.gerenciador.projetodevapp.repository.CondominiumRepository;

@Service
public class CondominiumService {
    @Autowired
    private CondominiumRepository condominiumRepository;

    public CondominiumModel save(CondominiumModel condominiumModel) {
        return this.condominiumRepository.save(condominiumModel);
    }

    public List<CondominiumModel> findAll() {
        return (List<CondominiumModel>) this.condominiumRepository.findAll();
    }
}
