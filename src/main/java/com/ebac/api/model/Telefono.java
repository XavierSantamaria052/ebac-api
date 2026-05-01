package com.ebac.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Telefono {
    private int idTelefono;
    private String tipoTelefono;
    private int lada;
    private String numero;
}
