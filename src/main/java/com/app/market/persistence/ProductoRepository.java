package com.app.market.persistence;

import com.app.market.domain.Product;
import com.app.market.domain.repository.ProductRepository;
import com.app.market.persistence.crud.ProductoCrudRepository;
import com.app.market.persistence.entity.Producto;
import com.app.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Nuestro producto repository está enfocado al dominio en vez de una tabla puntual.
//Evitamos que nuestro proyecto se acople a una base de datos puntual.

//Le indicamos a Spring que esta clase se encarga de interactuar con nuestra bd.
@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    //crear un método que recupere una (todos) lista de productos de nuestra base de datos.
    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
                return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScaseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true );
        //recibe y retorna
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));

    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    public List<Producto> getByCategoria(int idCategoria){
        return  productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    //eliminar un producto
    @Override
    public void delete(int productoId){
        productoCrudRepository.deleteById(productoId);
    }


}


