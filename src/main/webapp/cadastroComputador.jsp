<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Usu�rio</title>
    <link rel="stylesheet" href="./Style.css">
</head>
<body>
    <h1>Cadastro de Computador</h1>
    <form action="CadastroComputadorServlet" method="post">
        <label for="descr">Descri��o do computador:</label>
        <input type="text" id="descr" name="descr" required><br><br>

        <label for="idlabs">Qual laboratorio?</label>
        <select id="idlabs" name="idlabs" required>
    		<option value="1">Laborat�rio 1</option>
    		<option value="2">Laborat�rio 2</option>
    		<option value="3">Laborat�rio 3</option>
    		<option value="4">Laborat�rio 4</option>
    		<option value="5">Laborat�rio 5</option>
  		</select>

        <input type="submit" value="Cadastrar" id="cadastrar">
    </form>
</body>
</html>
