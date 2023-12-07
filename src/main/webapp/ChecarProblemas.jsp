<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Escolha de Consulta</title>
<link rel="stylesheet" href="./Style.css">
</head>
<body>
    <form action="ConsultaProblemasServlet" method="post">
        <label for="opcoes">Escolha a opção de consulta:</label>
        <select name="opcaoConsulta" id="opcoes">
        	<option disabled selected value>Selecione uma opção</option>
            <option value="problemasComputador">Problemas relatados de um computador específico</option>
            <option value="problemasLaboratorio">Problemas relatados de um laboratório específico</option>
            <option value="todosProblemas">Todos os problemas relatados</option>
            <option value="problemasHardware">Todos os problemas de hardware</option>
            <option value="problemasSoftware">Todos os problemas de software</option>
            <option value="problemasData">Problemas relatados dentro de uma faixa de datas específica</option>
            <option value="problemasUsuario">Problemas relatados por um usuário específico</option>
        </select><br><br>


        <div id="datas" style="display: none;">
            <label for="dataInicial">Data Inicial:</label>
            <input type="date" id="dataInicial" name="dataInicial" value="2022-12-31" min="2000-12-31" max="2099-12-31"><br><br>
            <label for="dataFinal">Data Final:</label>
            <input type="date" id="dataFinal" name="dataFinal" value="2022-12-31" min="2000-12-31" max="2099-12-31"><br><br>
        </div>

  
        <div id="labEspecifico" style="display: none;">
            <label for="escolherLab">Escolher Laboratório:</label>
            <select name="labEspecifico" id="escolherLab">
             	<option value="" selected disabled hidden>Escolha</option>
                <option value="1">Laboratório 1</option>
                <option value="2">Laboratório 2</option>
                <option value="3">Laboratório 3</option>
                <option value="4">Laboratório 4</option>
                <option value="5">Laboratório 5</option>
            </select><br><br>
        </div>

   
        <div id="usuarioEspecifico" style="display: none;">
            <label for="idUsuario">ID do Usuário:</label>
            <input type="number" id="idUsuario" name="idUsuario"><br><br>
        </div>


        <div id="computadorEspecifico" style="display: none;">
            <label for="idPC">ID do Computador:</label>
            <input type="number" id="idPC" name="idPC"><br><br>
        </div>

        <input type="submit" value="Consultar" id="consultar">
    </form>
    <br>
    <p>Cadastre um problema: </p>
    <form action="CadastroProblema.jsp" method="post">
    	<input type="submit" value="Relatar problema" id="consultar">
    </form>

    <script>
        
        document.getElementById("opcoes").addEventListener("change", function() {
            var datasDiv = document.getElementById("datas");
            var labDiv = document.getElementById("labEspecifico");
            var usuarioDiv = document.getElementById("usuarioEspecifico");
            var computadorDiv = document.getElementById("computadorEspecifico");

            if (this.value === "problemasData") {
                datasDiv.style.display = "block";
                labDiv.style.display = "none";
                usuarioDiv.style.display = "none";
                computadorDiv.style.display = "none";
            } else if (this.value === "problemasLaboratorio") {
                datasDiv.style.display = "none";
                labDiv.style.display = "block";
                usuarioDiv.style.display = "none";
                computadorDiv.style.display = "none";
            } else if (this.value === "problemasUsuario") {
                datasDiv.style.display = "none";
                labDiv.style.display = "none";
                usuarioDiv.style.display = "block";
                computadorDiv.style.display = "none";
            } else if (this.value === "problemasComputador") {
                datasDiv.style.display = "none";
                labDiv.style.display = "none";
                usuarioDiv.style.display = "none";
                computadorDiv.style.display = "block";
            } else {
                datasDiv.style.display = "none";
                labDiv.style.display = "none";
                usuarioDiv.style.display = "none";
                computadorDiv.style.display = "none";
            }
        });
    </script>

</body>
</html>