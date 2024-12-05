package dev.anirban.stridesync.controller;


import dev.anirban.stridesync.constants.UrlConstants;
import dev.anirban.stridesync.dto.common.SleepDto;
import dev.anirban.stridesync.dto.response.ResponseWrapper;
import dev.anirban.stridesync.entity.Sleep;
import dev.anirban.stridesync.service.SleepService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SleepController {

    private final SleepService service;

    @PostMapping(UrlConstants.CREATE_SLEEP)
    public ResponseWrapper<SleepDto> create(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody SleepDto sleepDto
    ) {

        SleepDto data = service
                .create(userDetails.getUsername(), sleepDto)
                .toSleepDto();

        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_SLEEP_QUERY)
    public ResponseWrapper<List<SleepDto>> findSleep(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "end", required = false) String end
    ) {

        List<Sleep> sleepList;

        if (start != null && end != null)
            sleepList = service.findBySleptBy_UsernameAndDateBetween(userDetails.getUsername(), start, end);
        else
            sleepList = service.findBySleptBy_Username(userDetails.getUsername());

        List<SleepDto> data = sleepList
                .stream()
                .map(Sleep::toSleepDto)
                .toList();

        return new ResponseWrapper<>(data);
    }

    @PutMapping(UrlConstants.PUT_SLEEP_QUERY)
    public ResponseWrapper<SleepDto> update(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody SleepDto sleepDto
    ) {
        SleepDto data = service.update(userDetails.getUsername(), sleepDto).toSleepDto();
        return new ResponseWrapper<>(data);
    }

    @DeleteMapping(UrlConstants.DELETE_SLEEP_QUERY)
    public ResponseWrapper<Object> deleteById(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer id
    ) {
        service.deleteById(id, userDetails.getUsername());
        return new ResponseWrapper<>("Sleep Deleted Successfully");
    }
}