package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.TypeContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TypeContractRepository extends JpaRepository<TypeContractEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.type_contract SET active = ?1 WHERE id_type_contract = ?2")
    public int updateActiveOfTypeContractEntityById(boolean active, Integer id);

    @Query(nativeQuery = true, value = "SELECT active FROM public.type_contract WHERE id_type_contract = ?1")
    public boolean getActiveOfTypeContractEntityById(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.type_contract SET typeContract = ?1 WHERE id_type_contract = ?2")
    public int updateTypeContractEntityTypeContractByIdTypeContract(String typeContract, Integer idTypeContract);

}