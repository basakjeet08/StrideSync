package dev.anirban.stridesync.controller;


import dev.anirban.stridesync.constants.UrlConstants;
import dev.anirban.stridesync.dto.common.WaterDto;
import dev.anirban.stridesync.dto.response.ResponseWrapper;
import dev.anirban.stridesync.entity.Water;
import dev.anirban.stridesync.service.WaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WaterController {

    private final WaterService service;

    @PostMapping(UrlConstants.CREATE_WATER)
    public ResponseWrapper<WaterDto> create(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody WaterDto waterDto
    ) {

        WaterDto data = service
                .create(userDetails.getUsername(), waterDto)
                .toWaterDto();

        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_WATER_QUERY)
    public ResponseWrapper<List<WaterDto>> findWater(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "end", required = false) String end
    ) {

        List<Water> WaterList;

        if (start != null && end != null)
            WaterList = service.findByDrankBy_UsernameAndDrankAtBetween(userDetails.getUsername(), start, end);
        else
            WaterList = service.findByDrankBy_Username(userDetails.getUsername());

        List<WaterDto> data = WaterList
                .stream()
                .map(Water::toWaterDto)
                .toList();

        return new ResponseWrapper<>(data);
    }

    @PutMapping(UrlConstants.PUT_WATER_QUERY)
    public ResponseWrapper<WaterDto> update(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody WaterDto waterDto
    ) {
        WaterDto data = service.update(userDetails.getUsername(), waterDto).toWaterDto();
        return new ResponseWrapper<>(data);
    }

    @DeleteMapping(UrlConstants.DELETE_WATER_QUERY)
    public ResponseWrapper<Object> deleteById(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer id
    ) {
        service.deleteById(id, userDetails.getUsername());
        return new ResponseWrapper<>("Water Deleted Successfully");
    }
}