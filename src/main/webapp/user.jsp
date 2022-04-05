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
    <title>Parque Tierra Media - Usuarios</title>
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
                <a class="btn bg-warning bg-gradient" role="button" href="logout">Cerrar Sesion</a>
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
                <h3>Lista de Usuarios</h3>
            </div>
            <div class="row justify-content-center mt-2">
            	<c:if test="${updateUser != null}">
						<div class="row text-center">
							<p class="text-white bg-success bg-gradient">
								<c:out value="${updateUser}"></c:out>
							</p>
						</div>
					</c:if>
				<c:if test="${createdUser != null}">
						<div class="row text-center">
							<p class="text-white bg-success bg-gradient">
								<c:out value="${createdUser}"></c:out>
							</p>
						</div>
					</c:if>
				<c:if test="${baja != null}">
						<div class="row text-center">
							<p class="text-dark bg-warning bg-gradient">
								<c:out value="${baja}"></c:out>
							</p>
						</div>
					</c:if>
                <div class="mb-3">
                     <a class="btn btn-primary" role="button" href="createUser">Nuevo Usuario</a>
                </div>
                <table class="table table-responsive">
                    <thead>
                        <tr>
                        	<th scope="col">Id</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Dinero</th>
                            <th scope="col">Tiempo</th>
                            <th scope="col">Rol</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${userlist}" var="userL">
                            <tr>
                            	<td>
                            		<c:out value="${userL.id}"></c:out>
                            	</td>
                                <td>
                                    <c:out value="${userL.nombre}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${userL.dinero}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${userL.tiempo}"></c:out>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${userL.isAdmin()}">
                                            Admin
                                        </c:when>
                                        <c:otherwise>
                                            Normal
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="editUser?id=${userL.id}" class="btn btn-info rounded"> Editar </a>
                                    <c:choose>
                                    	<c:when test="${userL.estaDeBaja()}">
                                    		<a href="#" class="btn btn-secondary rounded disabled" role="button">Ya en baja</a>
                                    	</c:when>
                                    	<c:otherwise>
                                    		<a href="darBaja?id=${userL.id}" class="btn btn-danger rounded">Dar De Baja </a>
                                    	</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </article>
    </main>
</body>

</html>