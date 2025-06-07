package br.com.salvando.salvando.repository;

import br.com.salvando.salvando.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.*;
import org.springframework.data.domain.*;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    Page<Evento> findByLatitudeAndLongitude(Double lat, Double lng, Pageable pageable);
}