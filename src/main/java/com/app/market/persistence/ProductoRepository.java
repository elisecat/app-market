package com.app.market.persistence;

import com.app.market.persistence.crud.ProductoCrudRepository;
import com.app.market.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Le indicamos a Spring que esta clase se encarga de interactuar con nuestra bd.
@Repository
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    //crear un método que recupere una (todos) lista de productos de nuestra base de datos.
    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria){
        return  productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }
    public Optional<List<Producto>> getEscasos(int cantidad, boolean estado){
        return  productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }
    //obtener un producto dado su Id
    public Optional<Producto> getProduto(int idProduto){
        return productoCrudRepository.findById(idProduto);
    }
    //guardar un producto
    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }

    //eliminar un producto
    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}


