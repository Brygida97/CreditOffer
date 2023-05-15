package pl.coderslab.creditofferfinal.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "banks")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
