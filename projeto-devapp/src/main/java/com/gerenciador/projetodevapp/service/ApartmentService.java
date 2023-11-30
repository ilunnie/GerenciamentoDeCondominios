package com.gerenciador.projetodevapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gerenciador.projetodevapp.model.ApartmentModel;
import com.gerenciador.projetodevapp.model.UserModel;
import com.gerenciador.projetodevapp.repository.ApartmentRepository;

import org.springframework.data.domain.Pageable;


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

    public Optional<ApartmentModel> findById(String identity) {
        return this.apartmentRepository.findById(identity);
    }

    public List<ApartmentModel> findWithOffsetAndLimit(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        Page<ApartmentModel> page = this.apartmentRepository.findAll(pageable);
        return page.getContent();
    }

    public void addResident(ApartmentModel apartment, UserModel resident) {
        if (apartment.getResidents() == null) {
            List<UserModel> list = new ArrayList<>();
            apartment.setResidents(list);
        }
        apartment.getResidents().add(resident);
    }
}
