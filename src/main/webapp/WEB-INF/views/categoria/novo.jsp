<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Categoria</title>
</head>
<body>
    <h3>Cadastro de Categoria</h3>
    <form action="/categorias" method="POST">
        <label for="nome">Nome da Categoria</label>
        <input type="text" name="nome" id="nome" placeholder="Nome da categoria" required />
        <input type="submit" value="Cadastrar">
    </form>
</body>
</html>
