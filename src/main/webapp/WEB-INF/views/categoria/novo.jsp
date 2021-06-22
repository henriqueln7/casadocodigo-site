<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <title>Cadastro de Categoria</title>
</head>
<body>
    <p>${alertaCategoriaSalva}</p>

    <h3>Cadastro de Categoria</h3>
    <form action="/categorias" method="POST">
        <label for="nome">Nome da Categoria</label>
        <input type="text" name="nome" id="nome" placeholder="Nome da categoria" required />
        <form:errors path="novaCategoriaRequest.nome" cssStyle="color: red" />
        <input type="submit" value="Cadastrar">
    </form>
</body>
</html>
