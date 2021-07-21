<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>API Agenda</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
</head>
<body>
<h1>API Agenda</h1>
<h3>Bienvenido! Con esta API podrás acceder a una base de datos de una agenda de personas.</h3>
<div class="container">
    <p>GET: /personas -----> Devuelve una lista de personas</p>
    <p>GET: /persona/{dni}  -----> Devuelve una persona dado el dni</p>
    <p>POST: /persona  -----> Añade una persona</p>
    <p>PUT: /persona/{dni} -----> Modifica una persona</p>
    <p>DELETE: /persona/{dni}  -----> Elimina una persona dado el dni</p>
</div>
</body>
</html>