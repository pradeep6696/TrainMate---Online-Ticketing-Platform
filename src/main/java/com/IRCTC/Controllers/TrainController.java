package com.IRCTC.Controllers;

import com.IRCTC.Model.Train;
import com.IRCTC.Service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class    TrainController {

    @Autowired
    private TrainService trainService;

    // ✅ Admin adds train
    @PostMapping
    public ResponseEntity<Train> addTrain(@RequestBody Train train) {
        Train savedTrain = trainService.addTrain(train);
        return ResponseEntity.ok(savedTrain);
    }

    // ✅ Get all trains (everyone can see)
    @GetMapping
    public List<Train> getAllTrains() {
        return trainService.getAllTrains();
    }

    // ✅ Get train by ID
    @GetMapping("/{id}")
    public ResponseEntity<Train> getTrainById(@PathVariable Long id) {
        return trainService.getTrainById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Search trains by source and destination
    @GetMapping("/search")
    public List<Train> searchTrains(@RequestParam String source, @RequestParam String destination) {
        return trainService.searchTrains(source, destination);
    }

    // ✅ Admin deletes train
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrain(@PathVariable Long id) {
        trainService.deleteTrain(id);
        return ResponseEntity.ok("Train deleted successfully!");
    }
}
