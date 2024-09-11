package org.example;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import org.hibernate.envers.Audited;


@Data
@Builder
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name="cliente")

@Audited

public class Cliente implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdCliente;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellido")
    private String apellido;

    @Column(name = "DNI", unique = true)
    private int dni;

    //cualquier cambio que realizo e cliente se ve realizado en domicilio (si elimino un cliente se elimina su domicilio)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Fk_domicilio")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "cliente")
    @Builder.Default
    private List<Factura> factura = new ArrayList<Factura>();

}





