<%@ page contentType="text/html; ISO-8859-1;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cadastro de Autor</title>
</head>
<body>
<form action="/autores" method="POST">
    <label>
        Nome:
        <input type="text" name="nome" /> <br />
    </label>
    <label>
        Email:
        <input type="email" name="email" /> <br />
    </label>
    <label>
        Descrição:
        <input type="text" name="descricao" /> <br />
    </label>
    <input type="submit" value="Cadastrar autor">
</form>
</body>
</html>