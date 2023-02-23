package com.venky.venkycubes.controller;

import com.venky.venkycubes.model.CubeListResponse;
import com.venky.venkycubes.model.CubeRequest;
import com.venky.venkycubes.model.CubeResponse;
import com.venky.venkycubes.service.CubeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller class to handle the API operations
 */
@RestController
@RequestMapping("/cube")
@RequiredArgsConstructor
@Log4j2
@EnableCaching
public class CubeController {

    private final CubeService cubeService;

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CubeResponse> addCube(@RequestBody CubeRequest cubeRequest) {
        log.info("CubeController-addCube :: Called");
        log.info("CubeController-addCube :: cubeRequest: {}", cubeRequest.toString());
        CubeResponse cubeResponse = cubeService.addCube(cubeRequest);
        return new ResponseEntity<>(cubeResponse, HttpStatus.CREATED);
    }

    @GetMapping(params = "id")
    public ResponseEntity<CubeResponse> getCubeById(@RequestParam("id") UUID id) {
        log.info("CubeController-getCubeById :: Called");
        log.info("CubeController-getCubeById :: id: {}", id);
        CubeResponse cubeResponse = cubeService.getCubeById(id);
        return new ResponseEntity<>(cubeResponse, HttpStatus.OK);
    }

    @GetMapping(params = "volume")
    public ResponseEntity<CubeListResponse> getCubeByVolume(@RequestParam("volume") double volume) {
        log.info("CubeController-getCubeByVolume :: Called");
        log.info("CubeController-getCubeByVolume :: volume: {}", volume);
        CubeListResponse cubeListResponse = cubeService.getCubeByVolume(volume);
        return new ResponseEntity<>(cubeListResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> clearCubes() {
        log.info("CubeController-clearCubes :: Called");
        cubeService.clearCubes();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
