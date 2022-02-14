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
    <title>Parque Tierra Media - Login</title>
</head>

<body>
    <div class="container-fluid bg-warning py-4">
        <h1 class="text-center">Bienvenido al Parque Tierra Media</h1>
    </div>
    <div class="w-50 m-auto pt-3">
            <form id="loginForm" action="login" method="post">
                <div class="mb-3">
                    <label for="name" class="form-label">Nombre usuario:</label>
                    <input type="text" class="form-control" id="name" name="username" required>
                </div>
                <c:if test="${flash != null}">
					<div class="alert alert-danger">
						<p>
							<c:out value="${flash}" />
						</p>
					</div>
				</c:if>
                <button type="submit" class="btn btn-primary">Ingresar</button>
    </div>
</body>

</html>