package com.example.eoghanpetitions.model;

import java.util.ArrayList;
import java.util.List;

public class Petition {

    private Long id;
    private String title;
    private String description;
    private String creatorName;
    private List<Signature> signatures = new ArrayList<>();

    public Petition() {}

    public Petition(Long id, String title, String description, String creatorName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creatorName = creatorName;
    }

    public void addSignature(Signature signature) {
        this.signatures.add(signature);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public List<Signature> getSignatures() {
        return signatures;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}