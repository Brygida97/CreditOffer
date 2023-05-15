package pl.coderslab.creditofferfinal.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "type")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeOfLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_Type")
    private String name_Type;

//----
}

