<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Laboratorios</title>
<link rel="stylesheet" href="./Style.css">
</head>
<body>

<form method="post" action="ConsultaLaboratoriosServlet">

	<label for="opcao">Informações do laboratório:</label>
    <select id="opcao" name="opcao" required>
    	<option value="TodosHardwares">Todos os hardwares</option>
    	<option value="TodosSoftwares">Todos os Softwares</option>
    	<option value="EspecificoHardware">Todos os hardwares de um laboratório específico</option>
        <option value="EspecificoSoftware">Todos os softwares de um laboratório específico</option>
	</select><br>
	
	
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
 	
 	<input type="submit" value="Consultar" id="consultar">

</form>
<script>

document.getElementById("opcao").addEventListener("change", function() {
    const labDiv = document.getElementById("labEspecifico");
    const selectedOption = this.value;

    if (selectedOption === "EspecificoHardware" || selectedOption === "EspecificoSoftware") {
        labDiv.style.display = "block";
    } else {
        labDiv.style.display = "none";
    }
});

</script>
</body>
</html>