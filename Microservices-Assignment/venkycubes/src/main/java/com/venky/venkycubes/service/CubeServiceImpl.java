package com.venky.venkycubes.service;

import com.venky.venkycubes.entity.Cube;
import com.venky.venkycubes.exception.CubeCustomException;
import com.venky.venkycubes.model.CubeListResponse;
import com.venky.venkycubes.model.CubeRequest;
import com.venky.venkycubes.model.CubeResponse;
import com.venky.venkycubes.repository.CubeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Service class implementation for cube operations
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class CubeServiceImpl implements CubeService {

    private final CubeRepository cubeRepository;

    @Override
    public CubeResponse addCube(CubeRequest cubeRequest) {
        log.info("CubeServiceImpl-addCube :: Called");
        double volume = cubeRequest.getVolume();
        double dimension = Math.cbrt(volume);

        Cube cube = Cube.builder()
                .x(dimension)
                .y(dimension)
                .z(dimension)
                .volume(volume)
                .createdTime(LocalDateTime.now())
                .build();
        log.info("CubeServiceImpl-addCube :: cube: {}", cube.toString());
        cube = cubeRepository.save(cube);
        CubeResponse cubeResponse = new CubeResponse();
        BeanUtils.copyProperties(cube, cubeResponse);
        log.info("CubeServiceImpl-addCube :: cubeResponse: {}", cubeResponse);
        return cubeResponse;
    }

    @Override
    @Cacheable(value = "cubes", key = "#id")
    public CubeResponse getCubeById(UUID id) {
        log.info("CubeServiceImpl-getCubeById :: Called");
        Cube cube = cubeRepository.findById(id)
                .orElseThrow( () -> new CubeCustomException("CUBE_NOT_FOUND", "Cube with given id: " + id + " not found"));
        CubeResponse cubeResponse = new CubeResponse();
        BeanUtils.copyProperties(cube, cubeResponse);
        log.info("CubeServiceImpl-getCubeById :: cubeResponse: {}", cubeResponse);
        return cubeResponse;
    }

    @Override
    @Cacheable(value = "cubes", key = "#volume")
    public CubeListResponse getCubeByVolume(double volume) {
        log.info("CubeServiceImpl-getCubeByVolume :: Called");
        List<Cube> cubes = cubeRepository.findByVolume(volume)
                .orElseThrow( () -> new CubeCustomException("CUBE_NOT_FOUND", "Cube with given volume: " + volume + " not found"));
        log.info("CubeServiceImpl-getCubeByVolume :: cubes: {}", cubes);
        CubeListResponse cubeListResponse = new CubeListResponse();
        List<CubeResponse> cubeResponses = new ArrayList<>();
        for (Cube cube : cubes) {
            CubeResponse cubeResponse = new CubeResponse();
            BeanUtils.copyProperties(cube, cubeResponse);
            cubeResponses.add(cubeResponse);
        }
        cubeListResponse.setCubes(cubeResponses);
        log.info("CubeServiceImpl-getCubeByVolume :: cubeResponse: {}", cubeListResponse);
        return cubeListResponse;
    }

    @Override
    public void clearCubes() {
        log.info("CubeServiceImpl-clearCubes :: Called");
        cubeRepository.deleteAll();
        log.info("CubeServiceImpl-deleteAll :: Deleted");
    }
}
