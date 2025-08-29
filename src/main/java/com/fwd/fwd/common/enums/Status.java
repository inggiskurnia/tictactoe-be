package com.fwd.fwd.common.enums;

import lombok.Getter;

@Getter
public enum Status {
    IN_PROGRESS("IN_PROGRESS"),
    X_WON("X_WON"),
    O_WON("O_WON"),
    DRAW("DRAW");

    private final String value;

    Status(String value){
        this.value = value;
    }
}
