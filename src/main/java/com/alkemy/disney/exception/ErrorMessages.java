package com.alkemy.disney.exception;

public class ErrorMessages {
        public static final String SERVICE_ERROR= "Existe un error interno en el servicio";
        public static final String DATABASE_ERROR = "parametro %s incorrecto";
        public static final String NOT_NULL = "El campo %s no puede ser nulo";
        public static final String NOT_EMPTY = "El campo %s no puede estar vacio";
        public static final String RESPONSE_ERROR = "parametro %s incorrecto";
        public static final String FEMALE_VALUE = "parametro %s incorrecto";
        public static final String MIN_VALUE_ERROR = "El campo %s no puede ser menor a 0";

        public static final String MAX_VALUE_ERROR = "El campo %s no puede ser mayor a ";

        public static final String NOT_FOUND_ERROR = "No se pudo encontrar el recurso solicitado";

        public static final String ERROR_VALIDATION = "Existe un problema con los datos enviados";

        public static final String ERROR_DATE = "La fecha ingresada no se ajusta al formato yyyy-MM-dd";
        public static final String FILM_NOT_FOUND = "No se pudo encontrar una pelicula con dicho ID";
        public static final String CHARACTER_NOT_FOUND = "No se pudo encontrar un personaje con dicho ID";
        public static final String GENRE_NOT_FOUND = "No se pudo encontrar un genero con dicho ID";
        public static final String SERIE_NOT_FOUND = "No se pudo encontrar una serie con dicho ID";
        public static final String BAD_REQUEST = "La informacion JSON no fue enviada de forma adecuada";

        public static final String INTERNAL_SERVER_ERROR = "Existe un problema interno con su peticion, intente mas tarde";

        public static final String INTERNAL_DB_ERROR = "Existe un problema con los datos, intente nuevamente.";
}
