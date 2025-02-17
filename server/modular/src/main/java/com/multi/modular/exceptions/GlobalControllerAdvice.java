
package com.multi.modular.exceptions; 

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {
    // @ExceptionHandler(EmployeeNotFoundException.class)
    // public ResponseEntity<?> handleEmpleadoNotFound(EmployeeNotFoundException ex, WebRequest request) {
    //     ExceptionBody body = new ExceptionBody(LocalDateTime.now(), HttpStatus.NOT_FOUND, ex.getMessage(),((ServletWebRequest) request).getRequest().getRequestURI());
    //     return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
    // }

    // Añadimos el resto de excepciones que consideremos necesarias

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIOException(IOException ex, WebRequest request) {
        ExceptionBody body = new ExceptionBody(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST, 
                "Error de lectura del archivo: " + ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @Override
    // Proporciona una respuesta personalizada cuando se capturan excepciones manejadas dentro de la propia estructura de Spring.
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, // (*)
            HttpStatusCode status, WebRequest request) {
        ExceptionBody myBody = new ExceptionBody(LocalDateTime.now(),
                status, ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURI());
        return ResponseEntity.status(status).headers(headers).body(myBody);
    }
}

@AllArgsConstructor
@Getter
class ExceptionBody {
    private LocalDateTime timestamp;
    private HttpStatusCode status;
    private String message;
    private String path;
}

// HttpStatus.OK (200) → Petición correcta (por defecto en GET)
// HttpStatus.CREATED (201) → Recurso creado (por ejemplo, en POST)
// HttpStatus.NO_CONTENT (204) → Operación exitosa, pero sin contenido en la respuesta (por ejemplo, en DELETE)

// HttpStatus.BAD_REQUEST (400) → Solicitud incorrecta, por datos inválidos.
// HttpStatus.UNAUTHORIZED (401) → No autenticado, falta token o credenciales.
// HttpStatus.FORBIDDEN (403) → No autorizado, el usuario no tiene permisos.
// HttpStatus.NOT_FOUND (404) → No encontrado, recurso no existente.
// HttpStatus.CONFLICT (409) → Conflicto en la solicitud, por ejemplo, un registro duplicado.

// HttpStatus.INTERNAL_SERVER_ERROR (500) → Error interno del servidor, fallo en el código.
// HttpStatus.NOT_IMPLEMENTED (501) → Función no implementada.
// HttpStatus.BAD_GATEWAY (502) → Error de comunicación con otro servidor.
// HttpStatus.SERVICE_UNAVAILABLE (503) → El servicio está temporalmente inactivo.