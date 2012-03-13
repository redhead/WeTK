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
                                        <li><a href="zapis.jsp">Zápis hodiny</a></li>
                                        <li><a href="login.jsp">Odhlášení</a></li>
                                </ul>
                        </nav>
                </header>

                <div id="loginTable">
                        <h2>Přihlášení</h2>
                        <form id="login" method="post" action="j_security_check">
                                <table>
                                        <tr><td>Jméno: </td><td><input type="text" id="j_username"></td></tr>
                                        <tr><td>Heslo: </td><td><input type="password" id="j_password"></tr>
                                        <tr><td><input type="submit" value="Přihlásit" id="loginButton"></td><td></td></tr>
                                </table>
                        </form>
                </div>

        </body>
</html>
