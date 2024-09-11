package org.example;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name="Factura")

@Audited

public class Factura  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdFactura;

    @Column(name = "MontoTotal")
    private int total;

    @Column(name = "Fecha")
    private  String fecha;

    @Column(name = "numero")
    private int num;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

  // @OneToMany(cascade = CascadeType.ALL)
    // private List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();


    @OneToMany(mappedBy = "factura",cascade = CascadeType.ALL)
    @Builder.Default
    private List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();

}
