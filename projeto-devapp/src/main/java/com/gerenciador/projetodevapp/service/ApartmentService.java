package com.gerenciador.projetodevapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.projetodevapp.model.ApartmentModel;
import com.gerenciador.projetodevapp.repository.ApartmentRepository;

@Service
public class ApartmentService {
    @Autowired
    private ApartmentRepository apartmentRepository;

    public ApartmentModel save(ApartmentModel apartmentModel) {
        return this.apartmentRepository.save(apartmentModel);
    }

    public List<ApartmentModel> findAll() {
        return (List<ApartmentModel>) this.apartmentRepository.findAll();
    }
}
