package dev.anirban.stridesync.controller;

import dev.anirban.stridesync.constants.UrlConstants;
import dev.anirban.stridesync.dto.common.MeasurementDto;
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
            @RequestBody MeasurementDto measurementDto
    ) {

        MeasurementDto data = service
                .create(userDetails.getUsername(), measurementDto)
                .toMeasurementDto();

        return new ResponseWrapper<>(data);
    }


    @GetMapping(UrlConstants.FIND_MEASUREMENT_QUERY)
    public ResponseWrapper<List<MeasurementDto>> findMeasurement(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "end", required = false) String end
    ) {

        List<Measurement> measurementList;

        if (start != null && end != null)
            measurementList = service.findByMeasuredBy_UsernameAndMeasuredDateBetween(userDetails.getUsername(), start, end);
        else
            measurementList = service.findByMeasuredBy_Username(userDetails.getUsername());

        List<MeasurementDto> data = measurementList
                .stream()
                .map(Measurement::toMeasurementDto)
                .toList();

        return new ResponseWrapper<>(data);
    }

    @PutMapping(UrlConstants.PUT_MEASUREMENT_QUERY)
    public ResponseWrapper<MeasurementDto> update(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody MeasurementDto measurementDto
    ) {
        MeasurementDto data = service.update(userDetails.getUsername(), measurementDto).toMeasurementDto();
        return new ResponseWrapper<>(data);
    }

    @DeleteMapping(UrlConstants.DELETE_MEASUREMENT_QUERY)
    public ResponseWrapper<Object> deleteById(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer id
    ) {
        service.deleteById(id, userDetails.getUsername());
        return new ResponseWrapper<>("Measurement Deleted Successfully");
    }
}