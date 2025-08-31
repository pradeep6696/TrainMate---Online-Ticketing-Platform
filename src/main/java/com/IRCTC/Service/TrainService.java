package com.IRCTC.Service;

import com.IRCTC.Model.Train;
import com.IRCTC.Repository.TrainRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainService {

    private final TrainRepository trainRepository;

    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    // Add train
    public Train addTrain(Train train) {
        return trainRepository.save(train);
    }

    // Get all trains
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    // Get train by ID
    public Optional<Train> getTrainById(Long id) {
        return trainRepository.findById(id);
    }

    // Delete train
    public void deleteTrain(Long id) {
        trainRepository.deleteById(id);
    }

    // Search trains by source and destination
    public List<Train> searchTrains(String source, String destination) {
        return trainRepository.findBySourceAndDestination(source, destination);
    }
}
