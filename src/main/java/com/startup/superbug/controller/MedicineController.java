package com.startup.superbug.controller;

import com.startup.superbug.data_access.service.MedicineGroupService;
import com.startup.superbug.data_access.service.MedicineService;
import com.startup.superbug.entity.Medicine;
import com.startup.superbug.entity.MedicineGroup;
import com.startup.superbug.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class MedicineController {

    @Autowired
    MedicineService medicineService;

    @Autowired
    MedicineGroupService medicineGroupService;

    @GetMapping("medicines")
    public ResponseEntity getMedicineList() {
        try {
            return ResponseEntity.ok(medicineService.findAll().get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("medicines")
    public ResponseEntity postMedicine(@RequestBody Medicine medicine) {
        try {
            return ResponseEntity.ok(medicineService.save(medicine).get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("medicines/{medicineId}")
    public ResponseEntity getMedicine(@PathVariable int medicineId) {
        try {
            Optional<Medicine> medicine = medicineService.findById(medicineId).get();
            if (medicine.isPresent())
                return ResponseEntity.ok(medicine.get());
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("medicine-groups")
    public ResponseEntity getMedicineGroupList() {
        try {
            List<MedicineGroup> user = medicineGroupService.findAll().get();
            if (!user.isEmpty()) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("medicine-groups/{groupId}")
    public ResponseEntity getMedicineGroup(@PathVariable int groupId) {
        try {
            Optional<MedicineGroup> medicineGroup = medicineGroupService.findById(groupId).get();
            if (medicineGroup.isPresent()) {
                return ResponseEntity.ok(medicineGroup.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("medicine-groups")
    public ResponseEntity postMedicineGroup(@RequestBody MedicineGroup medicineGroup) {
        try {
            MedicineGroup newGroup = medicineGroupService.save(medicineGroup).get();
            if (newGroup != null) {
                return ResponseEntity.ok(newGroup);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("medicine-groups/{groupId}/medicines")
    public ResponseEntity getMedicineByGroup(@PathVariable int groupId) {
        try {
            List<Medicine> medicines = medicineGroupService.findMedicineByGroupId(groupId).get();
            if (!medicines.isEmpty()) {
                return ResponseEntity.ok(medicines);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
