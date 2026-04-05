package com.example.eoghanpetitions.repository;

import com.example.eoghanpetitions.model.Petition;
import com.example.eoghanpetitions.model.Signature;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PetitionRepository {

    private List<Petition> petitions = new ArrayList<>();
    private Long nextId = 1L;

    @PostConstruct
    public void init() {

        Petition p1 = new Petition(nextId++, "Better Parking",
                "More parking spaces needed.", "Alice");

        p1.addSignature(new Signature("John", "john@email.com"));

        Petition p2 = new Petition(nextId++, "Longer Library Hours",
                "Keep library open later.", "Bob");

        petitions.add(p1);
        petitions.add(p2);
    }

    public List<Petition> findAll() {
        return petitions;
    }

    public Petition save(Petition petition) {
        petition.setId(nextId++);
        petitions.add(petition);
        return petition;
    }

    public Optional<Petition> findById(Long id) {
        return petitions.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<Petition> searchByTitle(String keyword) {
        return petitions.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }
}