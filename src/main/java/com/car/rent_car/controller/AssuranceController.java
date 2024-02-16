package com.car.rent_car.controller;
import com.car.rent_car.models.Assurance;
import com.car.rent_car.models.Voiture;
import com.car.rent_car.requests.AssuranceRequest;
import com.car.rent_car.requests.VoitureRequest;
import com.car.rent_car.services.AssuranceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assurances")
public class AssuranceController {

    private final AssuranceService assuranceService;

    @Autowired
    public AssuranceController(AssuranceService assuranceService) {
        this.assuranceService = assuranceService;
    }

    @GetMapping
    public ResponseEntity<List<Assurance>> getAllAssurances() {
        List<Assurance> assurances = assuranceService.getAllAssurances();
        return new ResponseEntity<>(assurances, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assurance> getAssuranceById(@PathVariable Long id) {
        Assurance assurance = assuranceService.getAssuranceById(id);
        if (assurance != null) {
            return new ResponseEntity<>(assurance, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   /* @PostMapping
    public ResponseEntity<Assurance> createAssurance(@RequestBody AssuranceRequest assuranceRequest) {
        Assurance newAssurance = new Assurance();
        BeanUtils.copyProperties(assuranceRequest, newAssurance);
        Assurance createdAssurance = assuranceService.saveAssurance(newAssurance); // <- Changed here
        return new ResponseEntity<>(createdAssurance, HttpStatus.CREATED);
    }*/
   @PostMapping
   public ResponseEntity<Assurance> createAssurance(@RequestBody AssuranceRequest assuranceRequest) {
       Assurance createdAssurance = assuranceService.saveAssurance(assuranceRequest);
       return new ResponseEntity<>(createdAssurance, HttpStatus.CREATED);
   }




    @PutMapping("/{id}")
    public ResponseEntity<Assurance> updateAssurance(@PathVariable Long id, @RequestBody Assurance newAssurance) {
        Assurance updatedAssurance = assuranceService.updateAssurance(id, newAssurance);
        if (updatedAssurance != null) {
            return new ResponseEntity<>(updatedAssurance, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssurance(@PathVariable Long id) {
        assuranceService.deleteAssurance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
