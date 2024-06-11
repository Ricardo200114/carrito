package ejb;

import jakarta.ejb.Local;
import java.util.ArrayList;
import java.util.Map;
import modelo.ProductoItem;

@Local
public interface ProductoBeanLocal {
    void agregar(ProductoItem item);
    String agregar(String nombre, Float precio, Integer cantidad);
    void eliminarUnProducto(String nombre);
    void eliminarProducto(String nombre);
    void vaciar();
    void setCantidadCarrito(Integer i);
    void remCantidadCarrito(Integer i);
    Map <String, ProductoItem> listaProductoMap();
    ArrayList<ProductoItem> listaProducto();
    Float getTotal();
    Integer getCantidadCarrito();
    ProductoItem getEleMap(String cadena);
    ProductoItem lastEleCarrito();
    ProductoItem popEleCarrito();
    ProductoItem firstEleCarrito();
    ProductoItem delFirstEleCarrito();
}
