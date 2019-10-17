package com.startup.superbug.controller;

import com.startup.superbug.data_access.service.ComplainService;
import com.startup.superbug.entity.Complain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class ComplainController {

    @Autowired
    ComplainService complainService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    @GetMapping("complains")
    public ResponseEntity getComplains() {
        try {
            List<Complain> complains = complainService.getAll().get();
            if (complains.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(complains);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("complains/{complainId}")
    public ResponseEntity get(@PathVariable int complainId) {
        try {
            if (complainId == 0) {
                return ResponseEntity.badRequest().body("Complain ID is required");
            }

            Optional<Complain> complain = complainService.get(complainId).get();
            if (!complain.isPresent()) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(complain);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("complains-by-user")
    public ResponseEntity getByUser(@RequestParam("userId") int userId) {
        try {
            if (userId == 0) {
                return ResponseEntity.badRequest().body("User ID is required!");
            }
            List<Complain> complains = complainService.getAllByUserId(userId).get();
            if (!complains.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(complains);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("complains")
    public ResponseEntity post(@RequestBody Complain complain) {
        try {
            String msg = getMessage(complain);
            if (!msg.isEmpty()) {
                return ResponseEntity.badRequest().body(msg);
            }
            Complain newComplain = complainService.save(complain).get();
            if (newComplain == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(newComplain);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    private String getMessage(Complain complain) {
        if (complain.getUserId() == 0) {
            return "User ID is required!";
        }
        if (complain.getAddress().isEmpty()) {
            return "Address is required!";
        }
        if (complain.getProviderName().isEmpty()) {
            return "Provider Name is required!";
        }
        if (complain.getDetails().isEmpty()) {
            return "Details is required!";
        }
        if (complain.getFarmacyName().isEmpty()) {
            return "Farmacy is required!";
        }
        return "";
    }
}
