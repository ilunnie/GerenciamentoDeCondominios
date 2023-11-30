package com.gerenciador.projetodevapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.projetodevapp.model.ApartmentModel;
import com.gerenciador.projetodevapp.model.BlockModel;
import com.gerenciador.projetodevapp.repository.BlockRepository;

@Service
public class BlockService {
    @Autowired
    private BlockRepository blockRepository;

    public BlockModel save(BlockModel blockModel) {
        return this.blockRepository.save(blockModel);
    }

    public List<BlockModel> findAll() {
        return (List<BlockModel>) this.blockRepository.findAll();
    }

    public Optional<BlockModel> findById(String identity) {
        return this.blockRepository.findById(identity);
    }

    public void addApartment(BlockModel block, ApartmentModel apartment) {
        if(block.getApartments() == null) {
            List<ApartmentModel> list = new ArrayList<ApartmentModel>();
            block.setApartments(list);
        }
        block.getApartments().add(apartment);
    }

    public BlockModel findByApartment(ApartmentModel apartment) {
        List<BlockModel> blocks = this.blockRepository.findAll();
        for(BlockModel block : blocks) {
            if (block.getApartments().contains(apartment)) {
                return block;
            }
        }

        return null;
    }
}
