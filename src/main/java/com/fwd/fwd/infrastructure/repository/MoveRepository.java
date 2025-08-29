package com.fwd.fwd.infrastructure.repository;

import com.fwd.fwd.entity.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MoveRepository extends JpaRepository<Move, UUID> {
}
