package br.com.salvando.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String titulo;
    private String mensagem;
    private int status;
    private LocalDateTime timestamp;
    private Map<String, String> erros;
}
