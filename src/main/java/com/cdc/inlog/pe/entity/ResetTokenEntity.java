package com.cdc.inlog.pe.entity;

import static com.cdc.inlog.pe.util.Constants.WORD_RESET_TOKEN;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = WORD_RESET_TOKEN)
public class ResetTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne(targetEntity = UsernameEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_username")
    private UsernameEntity user;

    @Column(nullable = false)
    private LocalDateTime expiration;

    public void updateExpiration(int minutes) {
        LocalDateTime today = LocalDateTime.now();
        this.expiration = today.plusMinutes(minutes);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiration);
    }

}