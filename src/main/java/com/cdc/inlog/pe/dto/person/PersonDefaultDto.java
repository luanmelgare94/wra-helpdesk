package com.cdc.inlog.pe.dto.person;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDefaultDto {

    private Integer codigo;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String nombres;

    private String nacionalidad;

    private String genero;

    private String tipoDocumento;

    private String numeroDocumento;

}