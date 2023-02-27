package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.CHARACTER_SPACE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ZERO;
import static com.cdc.inlog.pe.util.Constants.NUMBER_ONE;
import static com.cdc.inlog.pe.util.Constants.NUMBER_TWO;
import static com.cdc.inlog.pe.util.Constants.WORD_PERSON;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = WORD_PERSON)
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPerson;

    @Size(min = 2, max = 60, message = "El nombre como minimo debe contar con 2 caracteres y como maximo 60 caracteres")
    @Column(length = 60, nullable = false)
    private String firstName;

    @Size(min = 2, max = 60, message = "El primer apellido como minimo debe contar con 2 caracteres y como maximo 60 caracteres")
    @Column(length = 60, nullable = false)
    private String lastName;

    @Size(min = 2, max = 60, message = "El segundo apellido como minimo debe contar con 2 caracteres y como maximo 60 caracteres")
    @Column(length = 60, nullable = false)
    private String secondLastName;

    @ManyToOne
    @JoinColumn(name = "id_nationality", nullable = false, foreignKey = @ForeignKey(name = "FK_person_nationality"))
    private NationalityEntity nationalityEntity;

    @ManyToOne
    @JoinColumn(name = "id_gender", nullable = false, foreignKey = @ForeignKey(name = "FK_person_gender"))
    private GenderEntity genderEntity;

    @ManyToOne
    @JoinColumn(name = "id_type_document", nullable = false, foreignKey = @ForeignKey(name = "FK_person_type_document"))
    private TypeDocumentEntity typeDocumentEntity;

    @Size(min = 6, max = 20, message = "El documento como minimo debe contar con 6 caracteres y como maximo 20 caracteres")
    @Column(length = 20, nullable = false)
    private String doi;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(length = 50, nullable = false)
    private String userRegistration;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private LocalDateTime dateRegister;

    @OneToMany(mappedBy = "personEntity", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<EmailEntity> emailEntityList;

    @OneToMany(mappedBy = "personEntity", cascade = { CascadeType.ALL },orphanRemoval = true)
    private List<PhoneEntity> phoneEntityList;

    public String getFullName() {
        char[] characters = this.firstName.toLowerCase().toCharArray();
        for (int i = NUMBER_ZERO; i < this.firstName.length() - NUMBER_TWO; i++) {
            if (i == NUMBER_ZERO) {
                characters[i] = Character.toUpperCase(characters[i]);
            }
            if (characters[i] == ' ' || characters[i] == '.' || characters[i] == ',') {
                characters[i + NUMBER_ONE] = Character.toUpperCase(characters[i + NUMBER_ONE]);
            }
        }
        String newName = new String(characters);
        return this.secondLastName == null || this.secondLastName.isEmpty() ?
                newName.concat(CHARACTER_SPACE).concat(this.lastName) :
                newName.concat(CHARACTER_SPACE).concat(this.lastName)
                        .concat(CHARACTER_SPACE).concat(this.secondLastName);
    }

}