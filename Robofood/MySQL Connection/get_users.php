<?php
/**
 * Obtiene todas las alumnos de la base de datos
 */

require 'Functions.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    // Manejar petición GET
    $users = Functions::getUsers();

    if ($users) {

        //$datos["estado"] = 1;
        $datos["datos"] = $users;

        print json_encode($datos);
    } else {
        print json_encode(array(
            "estado" => 2,
            "mensaje" => "Ha ocurrido un error"
        ));
    }
}

