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
    <title>Parque Tierra Media - Crear Atraccion</title>
</head>

<body>
    <header class="container-fluid bg-secondary bg-gradient text-white pt-1">
        <div class="row">
            <div class="col">
                <h3 class="m-1">Crear Atraccion</h3>
            </div>
            <div class="col m-2 text-end">
                <a class="btn btn-light" role="button" href="atracciones">&lt; Atras</a>
            </div>
        </div>
    </header>
    <main class="bg-light">
    	<c:if test="${flashAT != null}">
				<div class="alert alert-danger row text-center">
					<p>
						<c:out value="${flashAT}"/>
					</p>
				</div>
			</c:if>
        <div class="w-75 m-auto pt-3">
            <form id="createAtraccionForm" action="createAtraccion" method="post">
                <div class="row mb-3">
                    <label for="name" class="form-label col-sm-2">Nombre:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" name="name" minlenght="3" required>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="costo" class="col-form-label col-sm-2">Costo:</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="costo" name="costo" min="0">
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="tiempo" class="col-form-label col-sm-2">Tiempo:</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="tiempo" name="tiempo" min="0">
                    </div>
                </div>
                <div class="row mb-3">
                	<label for="cupo" class="col-form-label col-sm-2">Cupo</label>
                	<div class="col-sm-10">
                		<input type="number" class="form-control" id="cupo" name="cupo" min="0">
                	</div>
                </div>
                <button type="submit" class="btn btn-primary">Crear</button>
                <a href="atracciones" class="btn btn-danger">Cancelar</a>
            </form>
        </div>
    </main>
</body>

</html>