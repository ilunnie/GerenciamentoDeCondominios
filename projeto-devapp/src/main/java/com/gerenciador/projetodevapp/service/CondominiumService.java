package com.gerenciador.projetodevapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.projetodevapp.model.BlockModel;
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

    public Optional<CondominiumModel> findById(String identity) {
        return this.condominiumRepository.findById(identity);
    }

    public void addBlock(CondominiumModel condominium, BlockModel block) {
        if(condominium.getBlocks() == null) {
            List<BlockModel> list = new ArrayList<BlockModel>();
            condominium.setBlocks(list);
        }
        condominium.getBlocks().add(block);
    }

    public CondominiumModel findByBlock(BlockModel block) {
        List<CondominiumModel> condominiums = this.condominiumRepository.findAll();
        for(CondominiumModel condominium : condominiums) {
            if (condominium.getBlocks().contains(block)) {
                return condominium;
            }
        }

        return null;
    }
}
