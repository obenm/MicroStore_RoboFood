<?php
/**
 * Obtiene todas las alumnos de la base de datos
 */

require 'Functions.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    // Manejar petición GET
    $coupons = Functions::getCoupons();

    if ($coupons) {

        //$datos["estado"] = 1;
        $datos["datos"] = $coupons;

        print json_encode($datos);
    } else {
        print json_encode(array(
            "estado" => 2,
            "mensaje" => "Ha ocurrido un error"
        ));
    }
}

