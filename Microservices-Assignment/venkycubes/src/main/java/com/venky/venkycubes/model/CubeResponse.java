package com.venky.venkycubes.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Response class to return matching cube
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CubeResponse {

    private double x;
    private double y;
    private double z;
    private double volume;
    private UUID id;
    private LocalDateTime createdTime;
}
