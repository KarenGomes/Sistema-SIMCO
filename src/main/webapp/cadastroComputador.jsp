<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Usuário</title>
    <link rel="stylesheet" href="./Style.css">
</head>
<body>
    <h1>Cadastro de Computador</h1>
    <form action="CadastroComputadorServlet" method="post">
        <label for="descr">Descrição do computador:</label>
        <input type="text" id="descr" name="descr" required><br><br>

        <label for="idlabs">Qual laboratorio?</label>
        <select id="idlabs" name="idlabs" required>
    		<option value="1">Laboratório 1</option>
    		<option value="2">Laboratório 2</option>
    		<option value="3">Laboratório 3</option>
    		<option value="4">Laboratório 4</option>
    		<option value="5">Laboratório 5</option>
  		</select>

        <input type="submit" value="Cadastrar" id="cadastrar">
    </form>
</body>
</html>
