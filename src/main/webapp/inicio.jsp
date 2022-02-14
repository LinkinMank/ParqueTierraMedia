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
	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"
		defer></script>
	<title>Parque Tierra Media - Inicio</title>
</head>

<body>
	<header class="container-fluid bg-secondary bg-gradient text-white pt-1">
		<div class="row justify-content-between">
			<div class="col-3">
				<h3>
					Usuario:
				</h3>
				<h5>
					Dinero disponible:
				</h5>
				<h5>
					Tiempo disponible:
				</h5>
			</div>
			<div class="col-2 mt-2">
				<a class="btn bg-warning bg-gradient" role="button" href="#">Cerrar
					Sesion</a>
			</div>
		</div>
		<nav class="bg-transparent text-white container-fluid mt-3">
			<div class="row d-flex justify-content-between text-center">
				<div class="col-2">
					<a class="nav-link text-white btn btn-dark" role="button" href="#">Inicio</a>
				</div>
				<div class="col-2">
					<a class="nav-link text-white btn btn-dark" role="button" href="#">Atracciones</a>
				</div>
				<div class="col-2">
					<a class="nav-link text-white btn btn-dark" role="button" href="#">Promociones</a>
				</div>
				<div class="col-2">
					<a class="nav-link text-white btn btn-dark" role="button" href="#">Itinerario</a>
				</div>
			</div>
		</nav>
	</header>
	<main class="container-fluid">
		<article class="container mt-4 text-center">
			<div class="row">
				<h3>Lista de Productos Disponibles</h3>
			</div>
			<div class="row justify-content-center">
				<ul class="list-group col-12 col-sm-6">
				</ul>
			</div>
		</article>
	</main>
</body>

</html>