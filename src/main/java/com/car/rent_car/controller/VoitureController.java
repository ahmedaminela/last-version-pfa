package com.car.rent_car.controller;

import com.car.rent_car.models.Voiture;
import com.car.rent_car.requests.VoitureRequest;
import com.car.rent_car.services.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

    private final VoitureService voitureService;

    @Value("${upload.dir}")
    private String uploadDir;

    @Autowired
    public VoitureController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }

    @GetMapping
    public ResponseEntity<List<Voiture>> getAllVoitures() {
        List<Voiture> voitures = voitureService.getAllVoitures();
        return new ResponseEntity<>(voitures, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voiture> getVoitureById(@PathVariable Long id) {
        Optional<Voiture> voiture = Optional.ofNullable(voitureService.getVoitureById(id));
        return voiture.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Voiture> createVoiture(@RequestPart("voiture") VoitureRequest voitureRequest,
                                                 @RequestPart("img") MultipartFile file) {
        String fileName = storeFile(file);
        if (!fileName.isEmpty()) {
            voitureRequest.setImg(fileName); // Assuming VoitureRequest has a field to set the image file name
        }
        Voiture createdVoiture = voitureService.saveVoiture(voitureRequest);
        return new ResponseEntity<>(createdVoiture, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voiture> updateVoiture(@PathVariable Long id,
                                                 @RequestPart("voiture") VoitureRequest voitureRequest,
                                                 @RequestPart("img") MultipartFile file) {
        String fileName = storeFile(file);
        if (!fileName.isEmpty()) {
            voitureRequest.setImg(fileName); // Set the new image file name if file is not empty
        }
        Voiture updatedVoiture = voitureService.updateVoiture(id, voitureRequest);
        return new ResponseEntity<>(updatedVoiture, HttpStatus.OK);
    }

    @GetMapping(value = "/getImages/{img}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageWithMediaType(@PathVariable String img) {
        try {
            Path path = Paths.get(uploadDir + img);
            byte[] image = Files.readAllBytes(path);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(image);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoiture(@PathVariable Long id) {
        voitureService.deleteVoiture(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private String storeFile(MultipartFile file) {
        if (file.isEmpty()) {
            return "";
        }
        try {
            // Extract the original file name and extension
            String originalFileName = file.getOriginalFilename();
            String fileExtension = Objects.requireNonNull(originalFileName).substring(originalFileName.lastIndexOf("."));

            // Generate a unique file name using a timestamp
            String newFileName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + fileExtension;

            // Define the target location to store the file
            Path targetLocation = Paths.get(uploadDir + File.separator + newFileName);

            // Copy the file to the target location (Replacing existing file with the same name)
            Files.copy(file.getInputStream(), targetLocation);

            return newFileName; // Return the new unique file name for further processing
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
