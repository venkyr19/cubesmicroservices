package com.venky.venkycubes.controller;

import com.venky.venkycubes.model.CubeListResponse;
import com.venky.venkycubes.model.CubeRequest;
import com.venky.venkycubes.model.CubeResponse;
import com.venky.venkycubes.service.CubeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CubeControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CubeService cubeService;

    private static final String REQUEST_MAPPING_PATH = "/cube";

    private static final String GET_BY_ID_PATH = "/cube?id=";

    private static final String GET_BY_VOLUME_PATH = "/cube?volume=";

    private static final double VOLUME = 125.0;

    @BeforeEach
    public void setUp() {
        testRestTemplate.delete(REQUEST_MAPPING_PATH);
    }

    @Test
    public void testAddCube() {

        CubeRequest cubeRequest = getCubeRequest();

        ResponseEntity<CubeResponse> response = testRestTemplate.postForEntity(REQUEST_MAPPING_PATH,
                cubeRequest,
                CubeResponse.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals(VOLUME, response.getBody().getVolume());
    }

    @Test
    public void testGetCubeById() {
        // Save a cube
        CubeRequest cubeRequest = getCubeRequest();
        CubeResponse createdCube = cubeService.addCube(cubeRequest);

        // Make get request
        ResponseEntity<CubeResponse> response = testRestTemplate.getForEntity(
                GET_BY_ID_PATH + createdCube.getId(),
                CubeResponse.class);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdCube.getId(), response.getBody().getId());
        assertEquals(createdCube.getVolume(), response.getBody().getVolume());
    }

    @Test
    public void testGetCubeByVolume() {
        // Save a cube
        CubeRequest cubeRequest = getCubeRequest();
        CubeResponse createdCube = cubeService.addCube(cubeRequest);

        // Make get request
        ResponseEntity<CubeListResponse> response = testRestTemplate.getForEntity(
                GET_BY_VOLUME_PATH + createdCube.getVolume(),
                CubeListResponse.class);

        // Assert response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getCubes().size());
        assertEquals(createdCube.getVolume(), response.getBody().getCubes().get(0).getVolume(), 0);
    }

    private CubeRequest getCubeRequest() {
        return CubeRequest.builder()
                .volume(VOLUME)
                .build();
    }
}
