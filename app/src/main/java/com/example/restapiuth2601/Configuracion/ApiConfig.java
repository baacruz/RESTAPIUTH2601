package com.example.restapiuth2601.Configuracion;

public class ApiConfig {
    // 10.0.2.2 es la IP para que el emulador vea el localhost
    public static final String BASE_URL = "http://10.0.2.2/crud-php/";

    public static final String EndPointPost = BASE_URL + "PostPersons.php";
    public static final String EndPointGet = BASE_URL + "GetPersons.php";
    public static final String EndPointUpdate = BASE_URL + "UpdatePersons.php";
    public static final String EndPointDelete = BASE_URL + "DeletePersons.php";
}
