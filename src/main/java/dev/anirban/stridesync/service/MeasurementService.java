package dev.anirban.stridesync.service;


import dev.anirban.stridesync.dto.request.CreateMeasurementDto;
import dev.anirban.stridesync.entity.Measurement;
import dev.anirban.stridesync.entity.User;
import dev.anirban.stridesync.repo.MeasurementRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepo measurementRepo;
    private final UserService userService;

    public Measurement create(String username, CreateMeasurementDto measurementDto) {

        // Check user
        User savedUser = userService.findByUsername(username);

        Measurement newMeasurement = Measurement
                .builder()
                .height(measurementDto.getHeight())
                .weight(measurementDto.getWeight())
                .measuredDate(measurementDto.getMeasuredDate())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        savedUser.addMeasureHistory(newMeasurement);

        return measurementRepo.save(newMeasurement);
    }

    public List<Measurement> findByMeasuredBy_Username(String username) {
        return measurementRepo.findByMeasuredBy_Username(username);
    }
}