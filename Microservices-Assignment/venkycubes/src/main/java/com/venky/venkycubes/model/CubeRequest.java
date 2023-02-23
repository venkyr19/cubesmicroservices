package com.venky.venkycubes.model;

import lombok.Builder;
import lombok.Data;

/**
 * Request class to create cube with given volume
 */
@Data
@Builder
public class CubeRequest {
    private Double volume;
    private String customName;
}
