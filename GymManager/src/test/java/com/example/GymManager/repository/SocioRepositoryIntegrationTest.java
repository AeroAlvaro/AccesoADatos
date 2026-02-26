package com.example.GymManager.repository;

import com.example.GymManager.model.Socio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class SocioRepositoryIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

    @Autowired
    private SocioRepository socioRepository;

    @Test
    void deberiaGuardarYRecuperarSocioDesdePostgres() {
        Socio socio = new Socio();
        socio.setNombre("Test Postgres");
        socio.setEmail("postgres@gym.com");

        Socio guardado = socioRepository.save(socio);
        Socio recuperado = socioRepository.findById(guardado.getId()).orElse(null);

        assertThat(recuperado).isNotNull();
        assertThat(recuperado.getNombre()).isEqualTo("Test Postgres");
    }
}