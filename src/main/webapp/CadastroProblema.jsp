<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Problema</title>
<link rel="stylesheet" href="./Style.css">
</head>
<body>
 <h1>Cadastro de problema</h1>
 
 <form action="CadastroProblemaServlet" method="post" id="form-problema">
 		
        <label for="descr">Descrição:</label>
        <input type="text" id="descr" name="descr" required><br><br>

        <label for="tipo">Tipo:</label>
        <select id="tipo" name="tipo" required>
    		<option value="software">Software</option>
    		<option value="hardware">Hardware</option>
  		</select><br>
  		<label for="date">Data:</label>
        <input type="date" id="date" name="date" required><br>
        <label for="id_pc">Id do computador:</label>
        <input type="text" id="id_pc" name="id_pc" required><br>
        <label for="id_user">Id do usuário:</label>
        <input type="text" id="id_user" name="id_user" required><br>
         <label for="labs">Qual laboratório?</label>
        <select name="labs" id="labs" required>
                <option value="1">Laboratório 1</option>
                <option value="2">Laboratório 2</option>
                <option value="3">Laboratório 3</option>
                <option value="4">Laboratório 4</option>
                <option value="5">Laboratório 5</option>
       </select><br><br>

        <input type="submit" value="Cadastrar" id="cadastrar">
    </form> 

</body>
</html>