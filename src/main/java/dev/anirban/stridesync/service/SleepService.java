package dev.anirban.stridesync.service;

import dev.anirban.stridesync.dto.common.SleepDto;
import dev.anirban.stridesync.entity.Sleep;
import dev.anirban.stridesync.entity.User;
import dev.anirban.stridesync.exception.SleepNotFound;
import dev.anirban.stridesync.exception.UserNotAuthenticated;
import dev.anirban.stridesync.repo.SleepRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SleepService {

    private final SleepRepo sleepRepo;
    private final UserService userService;

    public Sleep create(String username, SleepDto sleepDto) {

        // Check user
        User savedUser = userService.findByUsername(username);

        Sleep newSleep = Sleep
                .builder()
                .wentToBed(sleepDto.getWentToBed())
                .wokeUp(sleepDto.getWokeUp())
                .date(sleepDto.getDate())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        savedUser.addSleepHistory(newSleep);

        return sleepRepo.save(newSleep);
    }

    public Sleep findById(Integer id) {
        return sleepRepo
                .findById(id)
                .orElseThrow(() -> new SleepNotFound(id));
    }

    public List<Sleep> findBySleptBy_Username(String username) {
        return sleepRepo.findBySleptBy_Username(username);
    }

    public List<Sleep> findBySleptBy_UsernameAndDateBetween(
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
        return sleepRepo.findBySleptBy_UsernameAndDateBetween(username, startTime, endTime);
    }


    public Sleep update(String username, SleepDto sleepDto) {

        // Fetch Sleep
        Sleep savedSleep = findById(sleepDto.getId());

        // Verify that the username is valid
        if (!savedSleep.getSleptBy().getUsername().equals(username))
            throw new UserNotAuthenticated();

        // Update
        if (sleepDto.getWentToBed() != null)
            savedSleep.setWentToBed(sleepDto.getWentToBed());
        if (sleepDto.getWokeUp() != null)
            savedSleep.setWokeUp(sleepDto.getWokeUp());
        if (sleepDto.getDate() != null)
            savedSleep.setDate(sleepDto.getDate());

        savedSleep.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return sleepRepo.save(savedSleep);
    }

    public void deleteById(Integer id, String username) {
        Sleep savedSleep = findById(id);

        if (!savedSleep.getSleptBy().getUsername().equals(username))
            throw new UserNotAuthenticated();

        sleepRepo.deleteById(id);
    }
}