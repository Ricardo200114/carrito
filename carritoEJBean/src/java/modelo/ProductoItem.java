package modelo;

public class ProductoItem {
    private String nombre;
    private Float precio;
    private Integer cantidad;
    private String idCiente;
    
    public void ProductoItem(){}
    public void ProductoItem(String nombre, Float precio, Integer cantidad){
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    
    public String getNombre(){return this.nombre;}
    public Float getPrecio(){return this.precio;}
    public Integer getCantidad(){return this.cantidad;}
    public String getCliente(){return this.idCiente;}
    
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setPrecio(Float precio){this.precio = precio;}
    public void setCantidad(Integer cantidad){this.cantidad = cantidad;}
    public void setIdCliente(String idCliente){this.idCiente = idCliente;}
    
    @Override
    public String toString(){return getNombre() + " - " + getCantidad() + " - " + getPrecio();}
}
