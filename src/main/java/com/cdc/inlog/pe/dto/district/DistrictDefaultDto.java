package com.cdc.inlog.pe.dto.district;

import com.cdc.inlog.pe.dto.province.ProvinceDefaultDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DistrictDefaultDto {

    private ProvinceDefaultDto provincia;

    private String codigoDistrito;

    private String nombre;

}