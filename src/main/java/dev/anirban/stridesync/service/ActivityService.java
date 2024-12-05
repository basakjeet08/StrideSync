package dev.anirban.stridesync.service;


import dev.anirban.stridesync.dto.common.ActivityDto;
import dev.anirban.stridesync.entity.Activity;
import dev.anirban.stridesync.entity.User;
import dev.anirban.stridesync.exception.ActivityNotFound;
import dev.anirban.stridesync.exception.UserNotAuthenticated;
import dev.anirban.stridesync.repo.ActivityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepo activityRepo;
    private final UserService userService;

    public Activity create(String username, ActivityDto activityDto) {

        // Check user
        User savedUser = userService.findByUsername(username);

        Activity newActivity = Activity
                .builder()
                .title(activityDto.getTitle())
                .type(activityDto.getType())
                .startTime(activityDto.getStartTime())
                .endTime(activityDto.getEndTime())
                .intensity(activityDto.getIntensity())
                .note(activityDto.getNote())
                .date(activityDto.getDate())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        savedUser.addActivityHistory(newActivity);

        return activityRepo.save(newActivity);
    }

    public Activity findById(Integer id) {
        return activityRepo
                .findById(id)
                .orElseThrow(() -> new ActivityNotFound(id));
    }

    public List<Activity> findByCompletedBy_Username(String username) {
        return activityRepo.findByCompletedBy_Username(username);
    }

    public List<Activity> findByCompletedBy_UsernameAndDateBetween(
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
        return activityRepo.findByCompletedBy_UsernameAndDateBetween(username, startTime, endTime);
    }

    public Activity update(String username, ActivityDto activityDto) {

        // Fetch Activity
        Activity savedActivity = findById(activityDto.getId());

        // Verify that the username is valid
        if (!savedActivity.getCompletedBy().getUsername().equals(username))
            throw new UserNotAuthenticated();

        // Update
        if (activityDto.getTitle() != null)
            savedActivity.setTitle(activityDto.getTitle());

        if (activityDto.getType() != null)
            savedActivity.setType(activityDto.getType());

        if (activityDto.getStartTime() != null)
            savedActivity.setStartTime(activityDto.getStartTime());

        if (activityDto.getEndTime() != null)
            savedActivity.setEndTime(activityDto.getEndTime());

        if (activityDto.getIntensity() != null)
            savedActivity.setIntensity(activityDto.getIntensity());

        if (activityDto.getNote() != null)
            savedActivity.setNote(activityDto.getNote());

        if (activityDto.getDate() != null)
            savedActivity.setDate(activityDto.getDate());


        savedActivity.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return activityRepo.save(savedActivity);
    }

    public void deleteById(Integer id, String username) {
        Activity savedActivity = findById(id);

        if (!savedActivity.getCompletedBy().getUsername().equals(username))
            throw new UserNotAuthenticated();

        activityRepo.deleteById(id);
    }
}
