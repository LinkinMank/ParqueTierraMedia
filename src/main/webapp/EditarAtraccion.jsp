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
    <title>Parque Tierra Media - Editar Atraccion</title>
</head>

<body>
    <header class="container-fluid bg-secondary bg-gradient text-white pt-1">
        <div class="row">
            <div class="col">
                <h3 class="m-1">Editar Atraccion</h3>
            </div>
            <div class="col m-2 text-end">
                <a class="btn btn-light" role="button" href="atracciones">&lt; Atras</a>
            </div>
        </div>
    </header>
    <main class="bg-light">
        <div class="w-75 m-auto pt-3">
            <form id="editAtraccionForm" action="editAtraccion" method="post">
                <input type="hidden" name="atracId" value="${editAtrac.id}">
                <div class="row mb-2">
                    <label for="name" class="col-form-label col-sm-2">Nombre Actual:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" name="name" value="${editAtrac.nombre}" disabled>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="newName" class="form-label col-sm-2">Nuevo Nombre:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="newName" name="newname" value="${editAtrac.nombre}">
                    </div>
                </div>
                <div class="row mb-2">
                    <label for="costo" class="col-form-label col-sm-2">Costo Actual:</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="costo" name="costo" value="${editAtrac.costo}" disabled>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="newCosto" class="col-form-label col-sm-2">Nuevo Costo:</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="newCosto" name="newcosto" min="0" value="${editAtrac.costo}">
                    </div>
                </div>
                <div class="row mb-2">
                    <label for="tiempo" class="col-form-label col-sm-2">Tiempo Actual:</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="tiempo" name="tiempo" value="${editAtrac.tiempo}" disabled>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="newTiempo" class="col-form-label col-sm-2">Nuevo Tiempo:</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="newTiempo" name="newtiempo" min="0" value="${editAtrac.tiempo}">
                    </div>
                </div>
                <div class="row mb-2">
                	<label for="cupo" class="col-form-label col-sm-2">Cupo Actual:</label>
                	<div class="col-sm-10">
                		<input type="number" class="form-control" id="cupo" name="cupo" value="${editAtrac.cupo}" disabled>
                	</div>
                </div>
                <div class="row mb-3">
                	<label for="newCupo" class="col-form-label col-sm-2">Nuevo Cupo</label>
                	<div class="col-sm-10">
                		<input type="number" class="form-control" id="newCupo" name="newcupo" min="0" value="${editAtrac.cupo}">
                	</div>
                </div>
                <button type="submit" class="btn btn-primary">Actualizar</button>
                <a href="atracciones" class="btn btn-danger">Cancelar</a>
            </form>
        </div>
    </main>
</body>

</html>