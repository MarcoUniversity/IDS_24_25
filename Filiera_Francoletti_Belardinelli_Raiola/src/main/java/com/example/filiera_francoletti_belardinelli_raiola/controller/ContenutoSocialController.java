package com.example.filiera_francoletti_belardinelli_raiola.controller;

import com.example.filiera_francoletti_belardinelli_raiola.model.Social.ContenutoSocial;
import com.example.filiera_francoletti_belardinelli_raiola.repository.SocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/social")
public class ContenutoSocialController {

    private final SocialRepository socialRepository;

    @Autowired
    public ContenutoSocialController(SocialRepository socialRepository) {
        this.socialRepository = socialRepository;
    }

    @PostMapping
    public ResponseEntity<ContenutoSocial> createSocialContent(@RequestBody ContenutoSocial socialContent) {
        ContenutoSocial saved = socialRepository.save(socialContent);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContenutoSocial> getSocialContent(@PathVariable Long id) {
        Optional<ContenutoSocial> opt = socialRepository.findById(id);
        return opt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContenutoSocial> updateSocialContent(@PathVariable Long id, @RequestBody ContenutoSocial socialContentData) {
        Optional<ContenutoSocial> opt = socialRepository.findById(id);
        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ContenutoSocial cs = opt.get();
        cs.setDescription(socialContentData.getDescription());
        cs.setProduct(socialContentData.getProduct());
        cs.setSeller(socialContentData.getSeller());
        ContenutoSocial updated = socialRepository.save(cs);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocialContent(@PathVariable Long id) {
        socialRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ContenutoSocial>> getAllSocialContent() {
        List<ContenutoSocial> list = socialRepository.findAll();
        return ResponseEntity.ok(list);
    }
}

