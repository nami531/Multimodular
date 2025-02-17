package com.multi.modular.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Module {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // TODO: contemplar que si se está metiendo el csv, se muestre en qué linea del CSV está el error.
    @NotBlank(message = "Debe especificarse un nombre para el módulo.")
    private String name;

    @NotNull
    @Min(value = 0) // TODO: pregunatrle a Marta si quieren que als tematicas puedan tener valor negativo
    private Integer thematic;

    @NotBlank (message = "Debe especificarse un nombre para el módulo.")
    private String specialization;

    // TODO: se puede plantear como un ENUM
    @NotBlank (message = "Debe especificarse un regimen.")
    private String program; 

    @NotBlank (message = "Debe especificarse un ciclo.")
    private String course;

    @NotNull (message = "Debe especificarse la cantidad de horas.")
    private Integer hours;
}

