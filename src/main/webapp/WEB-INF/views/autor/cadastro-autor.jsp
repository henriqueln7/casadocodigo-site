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
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: auto;
        }

        .form-autor {
        }

        .form-autor .field {
            display: flex;
            flex-direction: column;
        }

        .form-autor .field label {
            font-size: 18px;
            margin: 0.3rem;
        }

        .form-autor .inline-field {
            display: flex;
            justify-content: space-between;
        }

        .form-autor .field input {
            padding: 0.2rem;
        }


        .form-autor .input-field {
            display: flex;
            justify-content: center;
        }
        .form-autor .input-field input[type="submit"] {
            border-radius: 8px;
            text-align: center;
            padding: 0.6rem;
            justify-content: center;
            margin-top: 1rem;
            font-size: 18px;
            cursor: pointer;
        }

        .form-erro {
            color: #ff0000;
            font-size: 16px;
        }

        .form-erro::first-letter {
            text-transform: capitalize;
        }
    </style>
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