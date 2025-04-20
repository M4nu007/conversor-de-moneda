package com.manuel.conversordemoneda.modelo;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record RespuestaTipoDeCambio(String result,
                                    @SerializedName("error-type") String errorType,
                                    double conversion_rate) {

}
