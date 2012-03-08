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
                        
                </header>
               
                <div id="loginTable">
                        <h2>Přihlášení</h2>
                        <form id="login" method="post" action="zapis.jsp">
                                <label for="userName">Jméno:</label>
                                <input type="text" id="userName">
                                <br>
                                <label for="password">Heslo:   </label>
                                <input type="password" id="password">
                                <br>
                                <input type="submit" value="Přihlásit">
                        </form>
                </div>
                
        </body>
</html>
