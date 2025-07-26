package com.order.wise.infrastructure.configuration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.TimeZone;

import static org.mockito.Mockito.mockStatic;

class TimeZoneConfigUnitTest {

    @Test
    @DisplayName("Should set default time zone to America/Sao_Paulo on initialization")
    void init_ShouldSetDefaultTimeZone() {

        try (MockedStatic<TimeZone> mockedTimeZone = mockStatic(TimeZone.class)) {

            TimeZoneConfig timeZoneConfig = new TimeZoneConfig();
            timeZoneConfig.init();
            mockedTimeZone.verify(() -> TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo")));

        }
    }
}