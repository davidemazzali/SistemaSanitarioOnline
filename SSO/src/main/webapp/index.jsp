<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Servizio Sanitario</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>

<body>

    <nav class="navbar-expand-md sticky-top "
        style="background-color: #51b5e0;font-family: 'Open Sans', sans-serif;padding: 11px;border-style: groove; border-width: 1px; border-color:lightgray">
        <div class="container-fluid"><img src="../assets/img/logoebbasta.png"
                style="height: 42px;padding: 0px;margin: 0px;">
            <a class="navbar-brand" href="index.jsp"
                style="padding: 3px;font-family: default;color: rgb(255,255,255);">
                Ministero della salute
            </a>
        </div>
    </nav>

    <div style="position: relative; text-align: center;width: 100%;">
        <img style="width:100%;"
            src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.expatica.com%2Ffr%2Fwp-content%2Fuploads%2Fsites%2F5%2F2014%2F05%2Fshutterstock_588010928-1200x675.jpg&f=1&nofb=1">
        <div style="position: absolute; top: 20%; left: 50%;color: white;width: 50%;">
            <h3 class="scaled"
                style="text-shadow: 1px 1px black;font-family:Verdana, Geneva, Tahoma, sans-serif; font-size: 4vw; ">
                <fmt message>Sistema Sanitario <br> per il Cittadino</fmt>
            </h3>

            <p style="text-shadow: 1px 1px black;font-family:Verdana, Geneva, Tahoma, sans-serif;font-size: 2vw;">
                <fmt message>Benvenuto sul sistema universale <br> per il servizio Sanitario</fmt>
            </p>
            <div> <a href="LoginServlet"><button type="button" class="btn btn-primary"
                                              style="box-shadow: 1px 1px black;font-size: 20;font-size:2vw ; width: 30%;">Login</button></a>
            </div>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row" style="background-color:#51b5e0;color: white;">
            <div class="col-md-12" style="text-align: center;">
                <h2 style=" padding-top:3% ; text-shadow: 1px 1px black">
                    Da qui
                    potrai usufruire di tutti i servizi
                    disponibili a tutti gli utenti.</h2>
            </div>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row" style="background-color:#51b5e0;color: white;padding-bottom: 5%;">
            <div class="col-md-6" style="text-align: center;">

                <ul style="list-style-type:none;text-align: center;">
                    <li>
                        <div style="background-color: transparent;">
                            <h3 class="card" style=" text-shadow: 1px 1px black; background-color: 
                            transparent ">
                                <fmt message>Per chi</fmt>
                            </h3>
                        </div>
                    </li>
                    <li>
                        <fmt message>
                            <h5>Medici</h5>
                        </fmt>
                    </li>
                    <li>
                        <fmt message>
                            <h5>Pazienti</h5>
                        </fmt>
                    </li>
                    <li>
                        <fmt message>
                            <h5>
                                Farmacie
                            </h5>
                        </fmt>
                    </li>
                    <li>
                        <fmt message>
                            <h5>Servizio Sanitario Nazionale/Provinciale</h5>
                        </fmt>
                    </li>
                </ul>
            </div>
            <div class="col-md-6" style="text-align: center;">
                <ul style="list-style-type:none;text-align: center">
                    <li>
                        <div style="background-color: transparent">
                            <h3 class="card" style="text-shadow: 1px 1px black; background-color: transparent; ">
                                <fmt message>Cosa si puo fare</fmt>
                            </h3>
                        </div>
                    <li>

                    <li>
                        <fmt message>
                            <h5>Prenotare Appuntamenti</h5>
                        </fmt>
                    </li>
                    <li>
                        <fmt message>
                            <h5>Richiedere farmaci</h5>
                        </fmt>
                    </li>
                    <li>
                        <fmt message>
                            <h5>Ananmnesi</h5>
                        </fmt>

                    </li>
                    <li>
                        <fmt message>
                            <h5>Resoconto economico</h5>
                        </fmt>

                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div
        style="background-color: #51b5e0;font-family: 'Open Sans', sans-serif; border-style: groove; border-width: 1px;border-color:lightgray;bottom: 0;width: 100%;">
        <p style="color: rgb(255,255,255); font-size: inherit;padding: 5px;">
            via Sommarive, 5 - 38123 Trento (Povo)
            Tel. +39 1234 567890
            CF e P.IVA 12345678901
            Numero verde 800 12345
        </p>

    </div>

</body>

</html>