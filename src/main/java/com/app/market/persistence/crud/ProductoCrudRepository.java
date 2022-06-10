package com.app.market.persistence.crud;

import com.app.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//CrudRepository recibe la entidad(tabla) y tipo de dato de la PK.
public interface ProductoCrudRepository extends CrudRepository <Producto, Integer>{
    //@Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    // Es mejor práctica utilizar los query methods porque nos ofrecen mucha + flexibilidad a la hora de implementar nuestro código.
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
