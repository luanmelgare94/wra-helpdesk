package com.cdc.inlog.pe.dto.parameter;

import com.cdc.inlog.pe.dto.gender.GenderDefaultDto;
import com.cdc.inlog.pe.dto.nationality.NationalityDefaultDto;
import com.cdc.inlog.pe.dto.operator.OperatorDefaultDto;
import com.cdc.inlog.pe.dto.typecontract.TypeContractDefaultDto;
import com.cdc.inlog.pe.dto.typedocument.TypeDocumentDefaultDto;
import com.cdc.inlog.pe.dto.typeemail.TypeEmailDefaultDto;
import com.cdc.inlog.pe.dto.typephone.TypePhoneDefaultDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ParameterRegistrationDto {

    private List<GenderDefaultDto> generos;

    private List<NationalityDefaultDto> paises;

    private List<OperatorDefaultDto> operadoresTelefonicos;

    private List<TypeContractDefaultDto> tiposContrato;

    private List<TypeDocumentDefaultDto> tiposDocumento;

    private List<TypeEmailDefaultDto> tiposCorreo;

    private List<TypePhoneDefaultDto> tiposTelefono;

}