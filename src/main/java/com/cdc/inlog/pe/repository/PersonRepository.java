package com.cdc.inlog.pe.repository;

import com.cdc.inlog.pe.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.person SET active = ?1 WHERE id_person = ?2")
    public int updateActiveOfPersonEntityById(boolean active, Integer id);

    @Query(nativeQuery = true, value = "SELECT active FROM public.person WHERE id_person = ?1")
    public boolean getActiveOfPersonEntityById(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE public.person SET first_name = ?1, last_name = ?2, second_last_name = ?3," +
            " id_nationality = ?4, id_gender = ?5, id_type_document = ?6, doi = ?7, birthday = ?8 WHERE id_person = ?9")
    public int updatePersonEntityByIdPerson(String firstName, String lastName, String secondLastName,
                                            Integer idNationality, Integer idGender, Integer idTypeDocument, String doi,
                                            LocalDate birthday, Integer idPerson);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM public.person WHERE id_type_document = ?1 AND doi = ?2")
    public Integer existsPersonEntityByIdTypeDocumentAndDoi(Integer idTypeDocument, String doi);

}