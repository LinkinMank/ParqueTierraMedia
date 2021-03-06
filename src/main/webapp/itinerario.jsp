<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- bootstrap js with popper-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"
        defer></script>
    <title>Parque Tierra Media - Itinerario</title>
</head>

<body>
    <header class="container-fluid bg-secondary bg-gradient text-white pt-1">
        <div class="row justify-content-between">
            <div class="col-3">
                <h3>
                    Usuario: <c:out value="${user.nombre}"></c:out>
                </h3>
                <h5>
                    Dinero disponible: <c:out value="${user.dinero}"></c:out>
                </h5>
                <h5>
                    Tiempo disponible: <c:out value="${user.tiempo}"></c:out>
                </h5>
            </div>
            <div class="col-2 mt-2">
                <a class="btn bg-warning bg-gradient" role="button" href="logout">Cerrar
                    Sesion</a>
            </div>
        </div>
        <nav class="bg-transparent text-white container-fluid mt-3">
            <div class="row d-flex justify-content-between text-center">
                <div class="col-2">
                    <a class="nav-link text-white btn btn-dark" role="button" href="inicio">Inicio</a>
                </div>
                <c:if test="${user.isAdmin()}">
                    <div class="col-2">
                        <a class="nav-link text-white btn btn-dark" role="button" href="user">Usuarios</a>
                    </div>
                </c:if>
                <div class="col-2">
                    <a class="nav-link text-white btn btn-dark" role="button" href="atracciones">Atracciones</a>
                </div>
                <div class="col-2">
                    <a class="nav-link text-white btn btn-dark" role="button" href="promociones">Promociones</a>
                </div>
                <div class="col-2">
                    <a class="nav-link text-white btn btn-dark" role="button" href="itinerario">Itinerario</a>
                </div>
            </div>
        </nav>
    </header>
    <main class="container-fluid">
        <article class="container mt-4 text-center">
            <div class="row">
                <h3>Lista de Tus Compras</h3>
            </div>
            <div class="row justify-content-center mt-2">
                <table class="table table-responsive">
                        <thead>
                            <tr>
                                <th scope="col">Nombre</th>
                                <th scope="col">Costo</th>
                                <th scope="col">Duracion</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:choose>
                        		<c:when test="${itinerario.isNull()}">
                        			<tr>
                        				<td colspan="3">No Ha Realizado Compras</td>
                        			</tr>
                        		</c:when>
                        		<c:otherwise>
                        			<c:forEach items="${itinerario.getListaCompras()}" var="item">
                        				<tr>
                        					<td><c:out value="${item.nombre}"></c:out></td>
                        					<td><c:out value="${item.costo}"></c:out></td>
                        					<td><c:out value="${item.tiempo}"></c:out>Hs</td>
                        				</tr>
                        			</c:forEach>
                        		</c:otherwise>
                        	</c:choose>
                        </tbody>
                </table>
            </div>
        </article>
    </main>
</body>

</html>