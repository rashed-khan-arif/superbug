package com.startup.superbug.controller;

import com.startup.superbug.data_access.service.CommonService;
import com.startup.superbug.entity.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/common/")
public class CommonController {
    @Autowired
    CommonService commonService;

    @GetMapping("country")
    public ResponseEntity getCountryList() {
        try {
            List<Country> countries = commonService.getCountryList().get();
            return ResponseEntity.ok(countries);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
