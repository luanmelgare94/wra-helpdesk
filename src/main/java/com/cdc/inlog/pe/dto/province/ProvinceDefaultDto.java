package com.cdc.inlog.pe.dto.province;

import com.cdc.inlog.pe.dto.department.DepartmentDefaultDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProvinceDefaultDto {

    private DepartmentDefaultDto departamento;

    private String codigoProvincia;

    private String nombre;

}