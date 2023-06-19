package com.tekcapsule.tekbyte.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
public enum PrizingModel {
    FREE("Free"),
    PAID("Paid");
    @Getter
    private String value;
}