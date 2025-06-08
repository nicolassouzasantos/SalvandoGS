package br.com.salvando.service;

import br.com.salvando.dto.EventoCreateDTO;
import br.com.salvando.dto.EventoResponseDTO;
import br.com.salvando.dto.SensorDTO;
import br.com.salvando.entity.Evento;
import br.com.salvando.entity.Sensor;
import br.com.salvando.repository.EventoRepository;
import br.com.salvando.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private SensorRepository sensorRepository;

    public List<EventoResponseDTO> findAll() {
        return eventoRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<EventoResponseDTO> findById(Long id) {
        return eventoRepository.findById(id)
                .map(this::convertToResponseDTO);
    }

    public List<EventoResponseDTO> findBySensor(Long numeroSensor) {
        return eventoRepository.findBySensorNumeroSensor(numeroSensor).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<EventoResponseDTO> findEventosEntreDatas(LocalDateTime inicio, LocalDateTime fim) {
        return eventoRepository.findEventosEntreDatas(inicio, fim).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<EventoResponseDTO> findRecentEventos(int limit) {
        return eventoRepository.findRecentEventos(limit).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<EventoResponseDTO> findEventosHoje() {
        return eventoRepository.findEventosHoje().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public EventoResponseDTO save(EventoCreateDTO eventoCreateDTO) {
        Sensor sensor = sensorRepository.findById(eventoCreateDTO.getIdSensor())
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));

        Evento evento = new Evento();
        evento.setDataHora(eventoCreateDTO.getDataHora() != null ?
                eventoCreateDTO.getDataHora() : LocalDateTime.now());
        evento.setLatitude(eventoCreateDTO.getLatitude() != null ?
                eventoCreateDTO.getLatitude() : sensor.getLatitude());
        evento.setLongitude(eventoCreateDTO.getLongitude() != null ?
                eventoCreateDTO.getLongitude() : sensor.getLongitude());
        evento.setSensor(sensor);

        Evento savedEvento = eventoRepository.save(evento);
        return convertToResponseDTO(savedEvento);
    }

    public void deleteById(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("Evento não encontrado");
        }
        eventoRepository.deleteById(id);
    }

    private EventoResponseDTO convertToResponseDTO(Evento evento) {
        SensorDTO sensorDTO = new SensorDTO(
                evento.getSensor().getNumeroSensor(),
                evento.getSensor().getLatitude(),
                evento.getSensor().getLongitude()
        );

        return new EventoResponseDTO(
                evento.getIdEvento(),
                evento.getDataHora(),
                evento.getLatitude(),
                evento.getLongitude(),
                evento.getSensor().getNumeroSensor(),
                sensorDTO
        );
    }
}