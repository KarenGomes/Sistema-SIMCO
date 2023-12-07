<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Instalar software</title>
<link rel="stylesheet" href="./Style.css">
</head>
<body>
<h1>Instalar Software</h1>

<form action="InstalarSoftwaresServlet" method="post">
	<label for="nome">Nome do software:</label>
    <input type="text" id="nome" name="nome" required><br>
    <label for="id_pc">Id do PC:</label>
    <input type="text" id="id_pc" name="id_pc" required><br>
    <input type="submit" value="Cadastrar" id="cadastrar">
</form>

</body>
</html>