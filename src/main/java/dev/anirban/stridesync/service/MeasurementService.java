package dev.anirban.stridesync.service;


import dev.anirban.stridesync.dto.response.MeasurementDto;
import dev.anirban.stridesync.entity.Measurement;
import dev.anirban.stridesync.entity.User;
import dev.anirban.stridesync.exception.MeasurementNotFound;
import dev.anirban.stridesync.exception.UserNotAuthenticated;
import dev.anirban.stridesync.repo.MeasurementRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepo measurementRepo;
    private final UserService userService;

    public Measurement create(String username, MeasurementDto measurementDto) {

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

    public Measurement findById(Integer id) {
        return measurementRepo
                .findById(id)
                .orElseThrow(() -> new MeasurementNotFound(id));
    }

    public List<Measurement> findByMeasuredBy_Username(String username) {
        return measurementRepo.findByMeasuredBy_Username(username);
    }

    public List<Measurement> findByMeasuredBy_UsernameAndMeasuredDateBetween(
            String username,
            String start,
            String end
    ) {

        // Convert String to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startLocalDate = LocalDate.parse(start, formatter);
        LocalDate endLocalDate = LocalDate.parse(end, formatter);

        Timestamp startTime = Timestamp.valueOf(startLocalDate.atStartOfDay());
        Timestamp endTime = Timestamp.valueOf(endLocalDate.atTime(23, 59, 59));
        return measurementRepo.findByMeasuredBy_UsernameAndMeasuredDateBetween(username, startTime, endTime);
    }

    public Measurement update(String username, MeasurementDto measurementDto) {

        // Fetch Measurement
        Measurement savedMeasurement = findById(measurementDto.getId());

        // Verify that the username is valid
        if (!savedMeasurement.getMeasuredBy().getUsername().equals(username))
            throw new UserNotAuthenticated();

        // Update
        if (measurementDto.getHeight() != null)
            savedMeasurement.setHeight(measurementDto.getHeight());
        if (measurementDto.getWeight() != null)
            savedMeasurement.setWeight(measurementDto.getWeight());
        if (measurementDto.getMeasuredDate() != null)
            savedMeasurement.setMeasuredDate(measurementDto.getMeasuredDate());

        savedMeasurement.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return measurementRepo.save(savedMeasurement);
    }

    public void deleteById(Integer id, String username) {
        Measurement savedMeasurement = findById(id);

        if (!savedMeasurement.getMeasuredBy().getUsername().equals(username))
            throw new UserNotAuthenticated();

        measurementRepo.deleteById(id);
    }
}