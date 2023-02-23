package com.venky.venkycubes.service;

import com.venky.venkycubes.model.CubeListResponse;
import com.venky.venkycubes.model.CubeRequest;
import com.venky.venkycubes.model.CubeResponse;

import java.util.UUID;

/**
 * Service interface containing cube operations
 */
public interface CubeService {

    CubeResponse addCube(CubeRequest cubeRequest);

    CubeResponse getCubeById(UUID id);

    CubeListResponse getCubeByVolume(double volume);

    void clearCubes();
}
