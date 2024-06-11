package control;

import ejb.ProductoBeanLocal;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@WebServlet ("/carrito")

public class ProductoBeanServlet extends HttpServlet {
        
    private static final long serialVersionUID = 1L;
    
    //@EJB
    //private ProductoBeanLocal carrito;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ProductoBeanLocal servProducto = getServProducto(request);
        request.getSession().setAttribute("total", servProducto.getTotal());
        request.getSession().setAttribute("cantidad", servProducto.getCantidadCarrito());            
        request.getSession().setAttribute("carritoBody", servProducto.listaProducto());
    
        response.sendRedirect("carritoJSP.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String nombreProd = request.getParameter("nombreProd");
        Float precioProd;
        Integer cantidadProd;
        String respuesta = "";
        
        try{
            precioProd = Float.valueOf(request.getParameter("precioProd"));
            cantidadProd = Integer.valueOf(request.getParameter("cantidadProd"));
        } 
        catch(NumberFormatException ex){
            precioProd = Float.valueOf(-1);
            cantidadProd = -1;
        }
        
        ProductoBeanLocal servProducto = getServProducto(request);
        respuesta = servProducto.agregar(nombreProd, precioProd, cantidadProd);
        request.getSession().setAttribute("mensaje",respuesta);
        this.doGet(request, response);
    }

    private ProductoBeanLocal getServProducto(HttpServletRequest request){
        HttpSession sesion = request.getSession(true);
        ProductoBeanLocal servProducto = null;
        String jid = "java:global/carritoEJBean/ProductoBean!ejb.ProductoBeanLocal";
        
        
        if (sesion.getAttribute("carrito") == null){
            try{
                InitialContext ict = new InitialContext();
                servProducto = (ProductoBeanLocal) ict.lookup(jid);
                sesion.setAttribute("carrito",servProducto);
            }
            catch(NamingException e){
            }
        }
        else {
            servProducto = (ProductoBeanLocal) sesion.getAttribute("carrito");
        }
        
        return servProducto;
    }
}
