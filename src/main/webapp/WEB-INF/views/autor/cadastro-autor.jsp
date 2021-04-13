<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<section class="container">
    <form action="/autores" method="POST" class="form-autor">
        <div class="inline-field">
            <div class="field">
                <label for="nome"> Nome: </label>
                <input type="text" name="nome" id="nome" required placeholder="Nome"/>
                <form:errors path="novoAutorForm.nome" cssClass="form-erro"/>
            </div>

            <div class="field">
                <label for="email"> Email: </label>
                <input type="email" name="email" id="email" required placeholder="Email"/>
                <form:errors path="novoAutorForm.email" cssClass="form-erro"/>
            </div>
        </div>

        <div class="field">
            <label for="descricao">Descrição: </label>
            <textarea name="descricao"
                      id="descricao"
                      required
                      maxlength="400"
                      placeholder="Insira uma descrição sobre você aqui. Descreva mais sobre sua jornada na área de desenvolvimento e o que você achar interessante :)"
                      cols="30"
                      rows="10"></textarea>
            <form:errors path="novoAutorForm.descricao" cssClass="form-erro"/>
        </div>
        <div class="input-field">
            <input type="submit" value="Cadastrar autor">
        </div>
    </form>
</section>
</body>
</html>