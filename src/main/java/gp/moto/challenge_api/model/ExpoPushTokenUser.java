package gp.moto.challenge_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_gpMottu_token_push")
public class ExpoPushTokenUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_token_push")
    private Long id;

    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario userId;
}
