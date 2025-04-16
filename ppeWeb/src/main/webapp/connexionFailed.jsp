<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>StatHammer : Connexion échouée</title>
	<link rel="stylesheet" href="styles.css">
</head>
<body>

    <div class="error">Échec de la connexion. Veuillez vérifier votre identifiant et votre mot de passe.</div>

    <form class="login-form" action="ConnexionController" method="post" enctype="application/x-www-form-urlencoded">
        <table>
            <tr>
                <td>Login</td>
                <td><input type="text" name="login" size="20" /></td>
            </tr>
            <tr>
                <td>Mot de passe</td>
                <td><input type="password" name="mdp" size="20" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <center><input type="submit" value="Connexion" /></center>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
