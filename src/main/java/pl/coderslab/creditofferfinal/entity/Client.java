package pl.coderslab.creditofferfinal.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "client")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String imie;

    @NotBlank
    private String nazwisko;

    @NotBlank
    @Email
    private String email;
}
