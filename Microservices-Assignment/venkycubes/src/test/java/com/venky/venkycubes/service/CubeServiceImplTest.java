package com.venky.venkycubes.service;

import com.venky.venkycubes.entity.Cube;
import com.venky.venkycubes.model.CubeRequest;
import com.venky.venkycubes.model.CubeResponse;
import com.venky.venkycubes.repository.CubeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CubeServiceImplTest {

    @Mock
    private CubeRepository cubeRepository;

    @InjectMocks
    private CubeServiceImpl cubeService;

    @Test
    public void testAddCube() {
        // Create cube request
        CubeRequest cubeRequest = CubeRequest.builder()
                .volume(125.0)
                .build();

        // create a Cube object with expected output values
        Cube cube = Cube.builder()
                .x(5.0)
                .y(5.0)
                .z(5.0)
                .volume(125.0)
                .id(UUID.randomUUID())
                .createdTime(LocalDateTime.now())
                .build();

        // mock the save method of CubeRepository to return the Cube object
        when(cubeRepository.save(any(Cube.class))).thenReturn(cube);

        // invoke the addCube method of CubeService
        CubeResponse cubeResponse = cubeService.addCube(cubeRequest);

        // verify that the CubeResponse object is not null and contains the expected values
        assertNotNull(cubeResponse);
        assertEquals(cube.getId(), cubeResponse.getId());
        assertEquals(cube.getX(), cubeResponse.getX(), 0.0);
        assertEquals(cube.getY(), cubeResponse.getY(), 0.0);
        assertEquals(cube.getZ(), cubeResponse.getZ(), 0.0);
        assertEquals(cube.getVolume(), cubeResponse.getVolume(), 0.0);
        assertEquals(cube.getCreatedTime(), cubeResponse.getCreatedTime());
    }
}
