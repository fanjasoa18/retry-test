package com.example.prog4.repository.cnaps;

import com.example.prog4.repository.cnaps.entity.CnapsPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CnapsPositionRepository extends JpaRepository<CnapsPosition, String> {
    Optional<CnapsPosition> findPositionByNameEquals(String name);
}
