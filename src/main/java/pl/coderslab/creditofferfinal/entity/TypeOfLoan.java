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

    // metody equals i hashCode
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TypKredytu that = (TypKredytu) o;
//        return Objects.equals(nazwaTypu, that.nazwaTypu);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(nazwaTypu);
}

