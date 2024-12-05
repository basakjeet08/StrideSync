package dev.anirban.stridesync.controller;

import dev.anirban.stridesync.constants.UrlConstants;
import dev.anirban.stridesync.dto.request.CreateMeasurementDto;
import dev.anirban.stridesync.dto.response.MeasurementDto;
import dev.anirban.stridesync.dto.response.ResponseWrapper;
import dev.anirban.stridesync.entity.Measurement;
import dev.anirban.stridesync.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService service;

    @PostMapping(UrlConstants.CREATE_MEASUREMENT)
    public ResponseWrapper<MeasurementDto> create(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody CreateMeasurementDto measurementDto
    ) {

        MeasurementDto data = service
                .create(userDetails.getUsername(), measurementDto)
                .toMeasurementDto();

        return new ResponseWrapper<>(data);
    }


    @GetMapping(UrlConstants.FIND_MEASUREMENT_QUERY)
    public ResponseWrapper<List<MeasurementDto>> findMeasurement(
            @RequestParam(name = "measuredBy", required = false) String username
    ) {

        List<MeasurementDto> data = service
                .findByMeasuredBy_Username(username)
                .stream()
                .map(Measurement::toMeasurementDto)
                .toList();

        return new ResponseWrapper<>(data);
    }
}