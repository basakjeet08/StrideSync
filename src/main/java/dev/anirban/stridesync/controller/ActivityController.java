package dev.anirban.stridesync.controller;


import dev.anirban.stridesync.constants.UrlConstants;
import dev.anirban.stridesync.dto.common.ActivityDto;
import dev.anirban.stridesync.dto.response.ResponseWrapper;
import dev.anirban.stridesync.entity.Activity;
import dev.anirban.stridesync.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService service;

    @PostMapping(UrlConstants.CREATE_ACTIVITY)
    public ResponseWrapper<ActivityDto> create(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ActivityDto activityDto
    ) {

        ActivityDto data = service
                .create(userDetails.getUsername(), activityDto)
                .toActivityDto();

        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_ACTIVITY_QUERY)
    public ResponseWrapper<List<ActivityDto>> findActivity(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "end", required = false) String end
    ) {

        List<Activity> activityList;

        if (start != null && end != null)
            activityList = service.findByCompletedBy_UsernameAndDateBetween(userDetails.getUsername(), start, end);
        else
            activityList = service.findByCompletedBy_Username(userDetails.getUsername());

        List<ActivityDto> data = activityList
                .stream()
                .map(Activity::toActivityDto)
                .toList();

        return new ResponseWrapper<>(data);
    }

    @PutMapping(UrlConstants.PUT_ACTIVITY_QUERY)
    public ResponseWrapper<ActivityDto> update(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ActivityDto activityDto
    ) {
        ActivityDto data = service
                .update(userDetails.getUsername(), activityDto)
                .toActivityDto();

        return new ResponseWrapper<>(data);
    }

    @DeleteMapping(UrlConstants.DELETE_ACTIVITY_QUERY)
    public ResponseWrapper<Object> deleteById(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer id
    ) {
        service.deleteById(id, userDetails.getUsername());
        return new ResponseWrapper<>("Activity Entry Deleted Successfully");
    }
}