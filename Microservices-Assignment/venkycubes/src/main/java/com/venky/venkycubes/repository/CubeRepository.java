package com.venky.venkycubes.repository;

import com.venky.venkycubes.entity.Cube;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository class for Cube
 */
public interface CubeRepository extends JpaRepository<Cube, UUID> {

    Optional<List<Cube>> findByVolume(double volume);
}
