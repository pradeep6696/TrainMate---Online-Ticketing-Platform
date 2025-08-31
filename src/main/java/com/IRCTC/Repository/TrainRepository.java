package com.IRCTC.Repository;

import com.IRCTC.Model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long> {

    // ✅ Search trains between source and destination
    List<Train> findBySourceAndDestination(String source, String destination);

    // ✅ Search trains by name (case insensitive)
    List<Train> findByTrainNameContainingIgnoreCase(String trainName);
}
