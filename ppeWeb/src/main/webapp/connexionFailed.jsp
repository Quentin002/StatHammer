<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>StatHammer : Connexion échouée</title>
		<link rel="stylesheet" href="css/main.css">
	</head>
	<body>
	    <form class="login-form" enctype="application/x-www-form-urlencoded" action="ConnexionController" method=POST>
			<table>
				<tr>
				    <td>Login</td>
				    <td><input type=text size=20 name=login></td>
				</tr>
				<tr>
					<td>Mot de passe</td>
					<td>
						<div class="password-wrapper">
					    	<input type="password" size="20" name="mdp" id="password">
					    	<i class="fa-solid fa-eye" id="togglePassword"></i>
						</div>
					</td>
				</tr>
				<tr>
				    <td colspan="2"height='100'><center><input type=submit value="Connexion"></center></td>
				</tr>
				<tr>
					<td colspan="2" height='10'><a href='CreationCompteView'><center>Vous ne possédez pas de compte ,créez-en un !</center></a></td>
				</tr>			    
			</table>
		</form>
	    <p class="quote"><b class="error">Échec de la connexion. Veuillez vérifier votre identifiant et votre mot de passe.</b></p>
	    <script src="js/togglePassword.js"></script>
	</body>
</html>
