package com.venky.venkycubes.model;

import lombok.*;

import java.util.List;

/**
 * Response class to return list of cubes
 */
@Data
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CubeListResponse {
    private List<CubeResponse> cubes;
}
