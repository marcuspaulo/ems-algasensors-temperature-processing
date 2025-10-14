package com.algaworks.algasensors.temperature.processing;

import com.algaworks.algasensors.temperature.processing.common.IdGenerator;
import com.algaworks.algasensors.temperature.processing.common.UUIDv7Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

class UUIDv7Test {

    @Test
    void shoudGenerateUUIDv7() {
        UUID uuid1 = IdGenerator.generateTimeBaseUUID();

        OffsetDateTime uuidDateTime = UUIDv7Utils.extractOffsetDateTime(uuid1).truncatedTo(ChronoUnit.MINUTES);
        OffsetDateTime offsetDateTime = OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        Assertions.assertEquals(uuidDateTime, offsetDateTime);
    }
}
