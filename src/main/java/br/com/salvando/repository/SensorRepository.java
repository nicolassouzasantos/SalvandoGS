package br.com.salvando.repository;

import br.com.salvando.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    @Query("SELECT s FROM Sensor s WHERE s.latitude BETWEEN :latMin AND :latMax AND s.longitude BETWEEN :lonMin AND :lonMax")
    List<Sensor> findSensorsInArea(
            @Param("latMin") BigDecimal latMin,
            @Param("latMax") BigDecimal latMax,
            @Param("lonMin") BigDecimal lonMin,
            @Param("lonMax") BigDecimal lonMax
    );

    @Query("SELECT s FROM Sensor s WHERE s.latitude = :latitude AND s.longitude = :longitude")
    List<Sensor> findByCoordinates(@Param("latitude") BigDecimal latitude, @Param("longitude") BigDecimal longitude);

    @Query("SELECT COUNT(e) FROM Evento e WHERE e.sensor.numeroSensor = :numeroSensor")
    Long countEventosBySensor(@Param("numeroSensor") Long numeroSensor);
}