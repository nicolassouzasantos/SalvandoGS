package br.com.salvando.service;

import br.com.salvando.dto.SensorDTO;
import br.com.salvando.entity.Sensor;
import br.com.salvando.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public List<SensorDTO> findAll() {
        return sensorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<SensorDTO> findById(Long numeroSensor) {
        return sensorRepository.findById(numeroSensor)
                .map(this::convertToDTO);
    }

    public List<SensorDTO> findSensorsInArea(BigDecimal latMin, BigDecimal latMax,
                                             BigDecimal lonMin, BigDecimal lonMax) {
        return sensorRepository.findSensorsInArea(latMin, latMax, lonMin, lonMax).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SensorDTO save(SensorDTO sensorDTO) {
        if (sensorRepository.existsById(sensorDTO.getNumeroSensor())) {
            throw new IllegalArgumentException("Sensor com este número já existe");
        }

        Sensor sensor = convertToEntity(sensorDTO);
        Sensor savedSensor = sensorRepository.save(sensor);
        return convertToDTO(savedSensor);
    }

    public SensorDTO update(Long numeroSensor, SensorDTO sensorDTO) {
        Sensor sensor = sensorRepository.findById(numeroSensor)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));

        sensor.setLatitude(sensorDTO.getLatitude());
        sensor.setLongitude(sensorDTO.getLongitude());

        Sensor updatedSensor = sensorRepository.save(sensor);
        return convertToDTO(updatedSensor);
    }

    public void deleteById(Long numeroSensor) {
        if (!sensorRepository.existsById(numeroSensor)) {
            throw new RuntimeException("Sensor não encontrado");
        }

        // Verificar se há eventos associados
        Long eventosCount = sensorRepository.countEventosBySensor(numeroSensor);
        if (eventosCount > 0) {
            throw new IllegalStateException("Não é possível excluir sensor com eventos associados");
        }

        sensorRepository.deleteById(numeroSensor);
    }

    private Sensor convertToEntity(SensorDTO dto) {
        Sensor sensor = new Sensor();
        sensor.setNumeroSensor(dto.getNumeroSensor());
        sensor.setLatitude(dto.getLatitude());
        sensor.setLongitude(dto.getLongitude());
        return sensor;
    }

    private SensorDTO convertToDTO(Sensor sensor) {
        return new SensorDTO(
                sensor.getNumeroSensor(),
                sensor.getLatitude(),
                sensor.getLongitude()
        );
    }
}