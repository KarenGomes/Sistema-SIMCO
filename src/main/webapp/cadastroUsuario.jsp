<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Usu�rio</title>
    <link rel="stylesheet" href="./Style.css">
</head>
<body>
    <h1>Cadastro de Usu�rio</h1>
    <form action="CadastroUsuarioServlet" method="post">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <!-- Adicione outros campos, se necess�rio -->

        <input type="submit" value="Cadastrar" id="cadastrar">
    </form>
</body>
</html>
