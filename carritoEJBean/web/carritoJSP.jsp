<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito de compras</title>
        <script>
            function reestablecer (){
                let nombre = document.getElementById("nombreProd");
                let precio = document.getElementById("precioProd");
                let cantidad = document.getElementById("cantidadProd");
                
                nombre.value = "";
                precio.value = "";
                cantidad.value = "";
            }
        </script>
    </head>
    <body>
        <div class = "container">
            <h1 class="mt-5">Carrito de compras</h1>
            <h3>Ingreso de productos</h3>
            <br>
            
            <form action="carrito" method="post" class="container border rounded">
                <div class="m-5">
                    <div class="form-group m-3">
                        <label for="nombreProd">Nombre del producto:</label>
                        <input type="text" name="nombreProd" class="form-control" id="nombreProd" placeholder="Producto">
                    </div>
                    <div class="form-group m-3">
                        <label for="precioProd">Precio del producto:</label>
                        <div class="input-group">
                            <div class="input-group-text">
                                <span class="input-group-prepend" id="elDolar">$</span>
                            </div>
                            <input type="text" name="precioProd" class="form-control" id="precioProd" placeholder="Precio" aria-describedby="elDolar">
                        </div>
                    </div>
                    
                    <div class="form-group m-3">
                        <label for="cantidadProd">Cantidad</label>
                        <input type="text" name="cantidadProd" class="form-control" id="cantidadProd" placeholder="Cantidad">
                    </div>
                    <br>
                    <button type="submit" class="btn btn-primary">Agregar</button>
                    <p class="btn btn-outline-primary m-2" onclick="reestablecer()">Reestablecer campos</p>
                    <p id="msj" class="mt-3 text-danger font-weight-bold">${mensaje}</p>
                </div>
            </form>
        </div>
        <br><hr><br>
        
        <div class="container">
            <div class="container border rounded">
                <div class="m-5">
                    <h4 class="mt-2">Carrito de compra</h4>
                    <table class = "p-6 table table-striped table-bordered">
                        <thead class="thead-primary text-center">
                            <tr>
                                <th class="col-6">Nombre del producto</th>
                                <th class="col-2">Precio</th>
                                <th class="col-2">Unidades</th>
                                <th class="col-2">Sub-Total</th>
                            </tr>
                        </thead>
                        <tbody id="carrito" class="text-center">
                            <c:forEach items="${carritoBody}" var="campo">
                                <tr>
                                    <td><c:out value="${campo.nombre}"></c:out></td>
                                    <td>
                                        $
                                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${campo.precio}" var="precioFormateado" />
                                        <c:out value="${precioFormateado}"></c:out>
                                    </td>
                                    <td><c:out value="${campo.cantidad}"></c:out></td>
                                    <td>
                                        $
                                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${campo.precio * campo.cantidad}" var="subTotalFormateado" />
                                        <c:out value="${subTotalFormateado}"></c:out>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                        <tfoot>
                            <tr class="table-dark">
                                <th colspan="2" class = "text-end">Total</th>
                                <td class="fw-bold text-center" id="totalUnidades">${cantidad}</td>
                                <td class="fw-bold text-center" id="totalDinero">
                                    
                                    <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${total}" var="totalFormateado" />
                                    <c:if test="${total!=0 || total!=''}">$</c:if>
                                    <c:out value="${totalFormateado}"></c:out>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
