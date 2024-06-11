package ejb;

import jakarta.ejb.Stateful;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import modelo.ProductoItem;

@Stateful
public class ProductoBean implements ProductoBeanLocal {
    
    private Map<String, ProductoItem> carrito;
    private Float totalDinero;
    private int cantidad;
    
    public ProductoBean(){
        carrito = new HashMap();
        this.totalDinero = (float)0;
        this.cantidad = 0;
    }

    public Float getTotalDinero(){return this.totalDinero;}
    private void addDinero(Float dinero){this.totalDinero += dinero;}
    private void remDinero(Float dinero){this.totalDinero -= dinero;}
    
    //aqui seteamos las variables ya con funciones
    @Override
    public void agregar(ProductoItem item) {
        ProductoItem elemento = carrito.get(item.getNombre());
        if (!(elemento == null)){
            if(Objects.equals(elemento.getPrecio(), item.getPrecio())){
                elemento.setCantidad(elemento.getCantidad()+item.getCantidad());
            }
            else{
                item.setNombre(item.getNombre()+((int) (Math.random()*10000)));
                carrito.put(item.getNombre(), item);
                setCantidadCarrito(item.getCantidad());
                addDinero(item.getPrecio()*item.getCantidad());
            }
        }
        else{
            carrito.put(item.getNombre(), item);
            setCantidadCarrito(item.getCantidad());
            addDinero(item.getPrecio()*item.getCantidad());
        }
    }
    
    @Override
    public String agregar(String nombre, Float precio, Integer cantidad){
        ProductoItem ele = new ProductoItem();
        String returnoTexto="";
        
        ele.setCantidad(cantidad);
        ele.setPrecio(precio);
        ele.setNombre(nombre);
        
        if(precio==0 || cantidad==0){
            ele.setNombre("-@!0!@-");
            ele.setCantidad(0);
            ele.setPrecio((float)0);
            returnoTexto = "¡El precio o cantidad no pueden ser cero o vacios!";
        } else {
             if (precio<0 || cantidad<0){
                ele.setNombre("-@!-1!@-");
                ele.setCantidad(0);
                ele.setPrecio((float)0);
                returnoTexto = "¡Precio o cantidad no válidos!";
            } else {
                if("".equals(nombre)){
                    ele.setNombre("Sin_nombre"+((int) (Math.random()*10000)));
                    this.agregar(ele);
                } else {this.agregar(ele);}
             }
        }
        return returnoTexto;
    }

    @Override
    public void eliminarUnProducto(String nombre) {
        ProductoItem elemento = carrito.get(nombre);
        
        if (!(elemento == null)){
            Integer cantidad = elemento.getCantidad();
            remCantidadCarrito(1);
            
            remDinero(elemento.getPrecio());
            if (cantidad == 1){carrito.remove(nombre);}
            else {elemento.setCantidad(cantidad-1);}
        }
    }
    
    @Override
    public void eliminarProducto(String nombre) {
        ProductoItem elemento = carrito.get(nombre);
        
        if (!(elemento == null)){
            remCantidadCarrito(getCantidadCarrito());
            remDinero(elemento.getCantidad()*elemento.getPrecio());
            
            
            carrito.remove(nombre);         
        }
    }

    @Override
    public void vaciar() {carrito.clear();}

    @Override
    public Map<String, ProductoItem> listaProductoMap() {return carrito;}
    
    @Override
    public ArrayList<ProductoItem> listaProducto() {
        ArrayList<ProductoItem> returno = new ArrayList<>();   
        for (String key: carrito.keySet()) {
            returno.add(carrito.get(key));
        }
        
        return returno;
    }

    @Override
    public Float getTotal() {return this.totalDinero;}

    @Override
    public Integer getCantidadCarrito() {return this.cantidad;}
    
    @Override
    public void setCantidadCarrito(Integer i) {cantidad = cantidad + i;}
    
    @Override
    public void remCantidadCarrito(Integer i) {cantidad = cantidad - i;}
    
    @Override
    public ProductoItem getEleMap(String cadena){return carrito.get(cadena);}
    
    @Override
    public ProductoItem lastEleCarrito(){
        ProductoItem returno = new ProductoItem();
        Collection col = carrito.values();
        Iterator i = col.iterator();
        
       while(i.hasNext()){
           returno = (ProductoItem) i.next();
       }
        return returno;
    }
    
    @Override
    public ProductoItem popEleCarrito(){
        ProductoItem elemento = lastEleCarrito();
        carrito.remove(elemento.getNombre());
        return elemento;
    }
    
    @Override
    public ProductoItem firstEleCarrito(){
        ProductoItem returno = new ProductoItem();
        Collection col = carrito.values();
        Iterator i = col.iterator();
        returno = (ProductoItem) i.next();
        return returno;
    }
    
    @Override
    public ProductoItem delFirstEleCarrito(){
        ProductoItem elemento = firstEleCarrito();
        carrito.remove(elemento.getNombre());
        return elemento;
    }
}