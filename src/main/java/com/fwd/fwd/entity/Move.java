package com.fwd.fwd.entity;

import com.fwd.fwd.domain.Mark;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "moves")
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID moveId;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game gameId;

    @Enumerated(EnumType.STRING)
    private Mark player;

    private int row;

    private int col;
}
