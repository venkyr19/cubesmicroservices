package com.venky.venkycubes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The actual entity persisted in the database
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="cube_table")
public class Cube {

    @Column(name="x")
    private double x;

    @Column(name="y")
    private double y;

    @Column(name="z")
    private double z;

    @Column(name="volume")
    private double volume;

    @Id
    @Column(columnDefinition = "BINARY(16)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="createdTime")
    private LocalDateTime createdTime;
}
