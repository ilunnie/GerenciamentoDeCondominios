package com.gerenciador.projetodevapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
