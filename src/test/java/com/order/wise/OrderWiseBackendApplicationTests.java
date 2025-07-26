package com.order.wise;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

class OrderWiseBackendApplicationMainMethodTest {

	@Test
	@DisplayName("Should call SpringApplication.run when main method is executed")
	void main_ShouldCallSpringApplicationRun() {

		try (MockedStatic<SpringApplication> mockedSpringApplication = mockStatic(SpringApplication.class)) {

			String[] args = new String[]{"arg1", "arg2"};
			OrderWiseBackendApplication.main(args);

			mockedSpringApplication.verify(() -> SpringApplication.run(eq(OrderWiseBackendApplication.class), eq(args)));
		}
	}
}