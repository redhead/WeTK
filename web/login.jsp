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
                                <label for="userName">Jméno:</label>
                                <input type="text" id="j_username">
                                <br>
                                <label for="password">Heslo:   </label>
                                <input type="password" id="j_password">
                                <br>
                                <input type="submit" value="Přihlásit">
                        </form>
                </div>

        </body>
</html>
