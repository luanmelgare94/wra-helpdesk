package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.TypeDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TypeDocumentRepository extends JpaRepository<TypeDocumentEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.type_document SET active = ?1 WHERE id_type_document = ?2")
    public int updateActiveOfTypeDocumentEntityById(boolean active, Integer id);

    @Query(nativeQuery = true, value = "SELECT active FROM public.type_document WHERE id_type_document = ?1")
    public boolean getActiveOfTypeDocumentEntityById(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.type_document SET abbreviation = ?1, typeDocument = ?2 WHERE id_type_document = ?3")
    public int updateTypeDocumentEntityAbbreviationAndTypeDocumentByIdTypeDocument(String abbreviation,
                                                                                   String typeDocument,
                                                                                   Integer idTypeDocument);

}