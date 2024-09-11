package org.example;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import org.hibernate.envers.Audited;


@Builder
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="articulo")

@Audited


public class Articulo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdArticulo;

    @Column(name = "Cantidad")
    private int cantidad;

    @Column(name = "Descripcion")
    private String denominacion;

    @Column(name = "Precio")
    private int precio;


    @OneToMany(mappedBy = "articulo", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<DetalleFactura> detalle = new ArrayList<DetalleFactura>();


    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
        name= "Articulo_Categoria",
        joinColumns = @JoinColumn(name="articulo_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    @Builder.Default
    private  List<Categoria> categorias = new ArrayList<Categoria>();
}
