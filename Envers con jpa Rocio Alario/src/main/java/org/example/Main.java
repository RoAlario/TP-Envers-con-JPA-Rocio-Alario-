package org.example;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");

            EntityManager em = emf.createEntityManager();
            System.out.println("en marcha Ro");

            try {

                // Persistir una nueva entidad Person
                em.getTransaction().begin();


                //Creo las personas
                Cliente cliente1 = Cliente.builder()
                        .nombre("Rocio")
                        .apellido("Alario")
                        .dni(44907397)
                        .build();

                Articulo art1 = Articulo.builder()
                        .cantidad(3)
                        .denominacion("Gomitas")
                        .precio(200)
                        .build();

                Domicilio dom1 = Domicilio.builder()
                        .calle("Juan G Molina")
                        .numcalle(1020)
                        .build();

                Categoria lib = Categoria.builder()
                        .denominacionCat("Libreria")
                        .build();

                Factura f1 = Factura.builder()
                        .fecha("3/12/23")
                        .num(19)
                        .total(5000)
                        .build();

                DetalleFactura d1 = DetalleFactura.builder()
                        .subtotal(2000)
                        .cant(5)
                        .build();

                //Hago las relaciones
                cliente1.setDomicilio(dom1);

                dom1.setCliente(cliente1);

                f1.setCliente(cliente1);

                f1.getDetalles().add(d1);

                d1.setFactura(f1);

                art1.getCategorias().add(lib);

                lib.getArticulos().add(art1);

                d1.setArticulo(art1);

                art1.getDetalle().add(d1);


                //Hago las persistencias
                //Clientes
                em.persist(cliente1);

                //Articulos
                em.persist(art1);

                //Domicilio
                em.persist(dom1);

                //Caegoria
                em.persist(lib);

                //Detalle Factura
                em.persist(d1);

                //Factura
                em.persist(f1);

                em.getTransaction().commit();


                //Actualizo una factura
                em.getTransaction().begin();
                f1 = em.find(Factura.class, 1L);
                f1.setNum(65);
                em.getTransaction().commit();
               // System.out.println("Factura actualizada: " + f1);



            /*
            //Actualizo una factura
                em.getTransaction().begin();
               f1 = em.find(Factura.class, 1L);  // Aquí `idFactura` es el identificador de la factura.

                f1.setNum(65);
                em.getTransaction().commit();
               System.out.println("Factura actualizada: " + f1);

            //Elimino una factura
            em.getTransaction().begin();
            em.remove(f1);
            em.getTransaction().commit();

            //System.out.println("Factura eliminada" + f1);


            // Consultar y mostrar la entidad persistida
            //        Persona personaRecuperada = em.find(Persona.class, persona.getId());
            //         System.out.println("Retrieved Persona: " + personaRecuperada.getNombre());

*/
        }catch (Exception e){

            em.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Persona");}

        // Cerrar el EntityManager y el EntityManagerFactory
        em.close();
        emf.close();
    }
}