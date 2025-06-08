package br.com.salvando.repository;

import br.com.salvando.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findBySensorNumeroSensor(Long numeroSensor);

    @Query("SELECT e FROM Evento e WHERE e.dataHora BETWEEN :inicio AND :fim ORDER BY e.dataHora DESC")
    List<Evento> findEventosEntreDatas(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    @Query("SELECT e FROM Evento e WHERE e.sensor.numeroSensor = :numeroSensor AND e.dataHora BETWEEN :inicio AND :fim ORDER BY e.dataHora DESC")
    List<Evento> findEventosBySensorAndPeriodo(
            @Param("numeroSensor") Long numeroSensor,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );

    @Query("SELECT e FROM Evento e ORDER BY e.dataHora DESC LIMIT :limit")
    List<Evento> findRecentEventos(@Param("limit") int limit);

    @Query("SELECT COUNT(e) FROM Evento e WHERE e.sensor.numeroSensor = :numeroSensor")
    Long countEventosBySensor(@Param("numeroSensor") Long numeroSensor);

    @Query(value = "SELECT * FROM evento WHERE TRUNC(data_hora) = TRUNC(SYSDATE) ORDER BY data_hora DESC", nativeQuery = true)
    List<Evento> findEventosHoje();
}