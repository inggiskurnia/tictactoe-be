package com.fwd.fwd.common.enums;

import lombok.Getter;

@Getter
public enum Mark {
    X("X"),
    O("O"),
    EMPTY(" ");

    private final String value;

    Mark(String value){
        this.value = value;
    }
}
