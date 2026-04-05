package com.example.eoghanpetitions.service;

import com.example.eoghanpetitions.model.Petition;
import com.example.eoghanpetitions.model.Signature;
import com.example.eoghanpetitions.repository.PetitionRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetitionService {

    private final PetitionRepository repository;

    public PetitionService(PetitionRepository repository) {
        this.repository = repository;
    }

    public List<Petition> getAll() {
        return repository.findAll();
    }

    public Petition create(Petition petition) {
        return repository.save(petition);
    }

    public Petition getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Petition not found"));
    }

    public List<Petition> search(String keyword) {
        return repository.searchByTitle(keyword);
    }

    public void sign(Long id, Signature signature) {
        Petition petition = getById(id);
        petition.addSignature(signature);
    }
}