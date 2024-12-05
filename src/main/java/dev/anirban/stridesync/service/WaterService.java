package dev.anirban.stridesync.service;

import dev.anirban.stridesync.dto.common.WaterDto;
import dev.anirban.stridesync.entity.User;
import dev.anirban.stridesync.entity.Water;
import dev.anirban.stridesync.exception.UserNotAuthenticated;
import dev.anirban.stridesync.exception.WaterNotFound;
import dev.anirban.stridesync.repo.WaterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WaterService {

    private final WaterRepo waterRepo;
    private final UserService userService;

    public Water create(String username, WaterDto waterDto) {

        // Check user
        User savedUser = userService.findByUsername(username);

        Water newWater = Water
                .builder()
                .volume(waterDto.getVolume())
                .drankAt(waterDto.getDrankAt())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        savedUser.addWaterHistory(newWater);

        return waterRepo.save(newWater);
    }


    public Water findById(Integer id) {
        return waterRepo
                .findById(id)
                .orElseThrow(() -> new WaterNotFound(id));
    }

    public List<Water> findByDrankBy_Username(String username) {
        return waterRepo.findByDrankBy_Username(username);
    }

    public List<Water> findByDrankBy_UsernameAndDrankAtBetween(
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
        return waterRepo.findByDrankBy_UsernameAndDrankAtBetween(username, startTime, endTime);
    }

    public Water update(String username, WaterDto waterDto) {

        // Fetch Water
        Water savedWater = findById(waterDto.getId());

        // Verify that the username is valid
        if (!savedWater.getDrankBy().getUsername().equals(username))
            throw new UserNotAuthenticated();

        // Update
        if (waterDto.getVolume() != null)
            savedWater.setVolume(waterDto.getVolume());
        if (waterDto.getDrankAt() != null)
            savedWater.setDrankAt(waterDto.getDrankAt());

        savedWater.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return waterRepo.save(savedWater);
    }

    public void deleteById(Integer id, String username) {
        Water savedWater = findById(id);

        if (!savedWater.getDrankBy().getUsername().equals(username))
            throw new UserNotAuthenticated();

        waterRepo.deleteById(id);
    }
}