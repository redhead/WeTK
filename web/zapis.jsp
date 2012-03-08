<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>eTK - Elektronická třídní kniha</title>
		<link href="css/style.css" type="text/css" rel="stylesheet">
	</head>

	<body>

		<header>
			<h1>
				<span class="abbr"><span>e</span>TK</span>
				<span class="hidden">-</span>
				<span class="long">elektronická třídní kniha</span>
			</h1>
			<nav>
				<ul>
					<li><a href="#">Zápis hodiny</a></li>
					<li><a href="#">Můj rozvrh</a></li>
					<li><a href="#">Administrace třídy</a></li>
				</ul>
			</nav>
		</header>

		<section id="page">
			<h2>Zápis hodiny</h2>

			<form class="find-entry-form">
				<label for="classSelect">Třída: </label>
				<select id="classSelect">
					<option>3.C</option>
					<option>4.C</option>
				</select>

				<label for="dateInput">Datum:</label>
				<select id="dateInput">
					<option>dnes</option>
				</select>

				<label for="hourInput">Hodina:</label>
				<input id="hourInput" type="number" value="1">

				<input type="submit" value="Hledat">
			</form>

			<div id="prevLesson"><a href=""></a></div>
			<div id="nextLesson"><a href=""></a></div>

			<form>
				<table class="lessonEntry">
					<tr>
						<th><label>Téma:</label></th>
						<td><input type="text" class="topic-input"></td>
					</tr>
					<tr>
						<th><label>Předmět:</label></th>
						<td class="compound">
							<select>
								<option>AJ - Anglický jazyk</option>
							</select>
							<label>Číslo hodiny:</label>
							<input type="number" value="1">

							<span class="checkbox"><input type="checkbox"><label>Suplováno</label></span>
						</td>
					</tr>
				</table>
			</form>
		</section>
	</body>
</html>
