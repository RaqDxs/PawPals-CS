package com.mascotas.dieta.petdiet.util;

import java.util.UUID;

public class IdGenerator {

    public static Long generarId() {
        return Math.abs(UUID.randomUUID().getMostSignificantBits());
    }
}
