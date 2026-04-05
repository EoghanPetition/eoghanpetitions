package com.example.eoghanpetitions.controller;

import com.example.eoghanpetitions.model.Petition;
import com.example.eoghanpetitions.model.Signature;
import com.example.eoghanpetitions.service.PetitionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PetitionController {

    private final PetitionService petitionService;

    public PetitionController(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/petitions";
    }

    @GetMapping("/petitions")
    public String viewAllPetitions(Model model) {
        model.addAttribute("petitions", petitionService.getAll());
        return "view-petitions";
    }

    @GetMapping("/petitions/create")
    public String showCreateForm(Model model) {
        model.addAttribute("petition", new Petition());
        return "create-petition";
    }

    @PostMapping("/petitions/create")
    public String createPetition(@ModelAttribute Petition petition) {
        petitionService.create(petition);
        return "redirect:/petitions";
    }

    @GetMapping("/petitions/search")
    public String showSearchPage() {
        return "search-petition";
    }

    @GetMapping("/petitions/results")
    public String searchResults(@RequestParam String keyword, Model model) {
        model.addAttribute("petitions", petitionService.search(keyword));
        model.addAttribute("keyword", keyword);
        return "search-results";
    }

    @GetMapping("/petitions/{id}")
    public String viewPetition(@PathVariable Long id, Model model) {
        model.addAttribute("petition", petitionService.getById(id));
        model.addAttribute("signature", new Signature());
        return "petition-detail";
    }

    @PostMapping("/petitions/{id}/sign")
    public String signPetition(@PathVariable Long id, @ModelAttribute Signature signature) {
        petitionService.sign(id, signature);
        return "redirect:/petitions/" + id;
    }
}