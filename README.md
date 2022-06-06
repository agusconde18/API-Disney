<div id="top"></div>

<!-- PROJECT HEADER -->
<br />
<div align="center">
  <h1 align="center">Disney API</h1>
    <br />
    <a href="https://bbva-testja.herokuapp.com/">View Demo</a>
    ·
    <a href="https://github.com/agusconde18/API-Disney/tree/main/issues">Report Bug</a>
    ·
    <a href="https://github.com/agusconde18/API-Disney/tree/main/issues">Request Feature</a>
    <br />
</div>



<!-- TABLE OF CONTENTS -->

### Tabla de Contenidos
1. [Sobre El Proyecto](#sobre-el-proyecto-)
1. [Construido con](#construido-con-%EF%B8%8F)
2. [Comenzando](#comenzando-)
3. [Uso](#uso-%EF%B8%8F)
4. [Despliegue](#despliegue-)
5. [Autores](#autores-%EF%B8%8F)
6. [Contribuyendo](#contribuyendo-)

<!-- ABOUT THE PROJECT -->
## Sobre El Proyecto 📖

El proyecto esta creado para satisfacer a medida de las necesidades del cliente. El requerimiento era desarrollar una apliacion que le permita a niños y niñas hispanohablantes explorar el mundo de Disney. Para hacer esto nos solicitaron que mediante la aplicacion se pueda conocer y modificar a personajes y entender en que peliculas participaron.

Objetivo 🎯
* Utilizar Spring Boot.
* Las rutas deberán seguir el patrón REST.
* Utilizar la librería Spring Security.

Utilizamos el patron MVC para diseñar las entidades (modelos) y manejar la interaccion de las mismas con la capa de datos y controladores mediante DTOs (data transfer objects).

Testeo 🧰
* Mediante postman

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- BUILD WITH -->
## Construido con 🛠️

Aplicamos las siguientes tecnologías para construir el proyecto.

* [Maven](https://maven.apache.org/)
* [MySQL](https://www.mysql.com/)
* [Spring](https://spring.io/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [Spring Security](https://spring.io/projects/spring-security)


<p align="right">(<a href="#top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Comenzando 🚀

Para poder levantar y compilar el proyecto localmente debes cumplir los siguientes requisitos y realizar las instalaciones pertinentes.
A continuacion se encuentran los pasos para poder aprovechar las funcionalidades de la API.

### Prerequisitos 📋

* Tener java 11 (como minimo) configurado
  ```html
    <properties>
      <java.version>11</java.version>
    </properties>
  ```
* Tener maven configurado

### Instalacion 🔧

1. Clona el repositorio
   ```sh
   git clone https://github.com/agusconde18/API-Disney.git
   ```
2. Abre el proyecto con [IntelliJ IDEA](https://www.jetbrains.com/idea/)
3. Instala las dependencias de maven
    ```html
    <dependencies>

      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
      </dependency>

      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
      </dependency>

      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
      </dependency>

      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
      </dependency>

      <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
      </dependency>

      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
      </dependency>

      <dependency>
        <groupId>com.sendgrid</groupId>
        <artifactId>sendgrid-java</artifactId>
        <version>4.7.4</version>
      </dependency>

      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
      </dependency>

      <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
      </dependency>
      
      <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-entitymanager</artifactId>
      </dependency>

      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <version>2.5.3</version>
      </dependency>

      <dependency>
        <groupId>org.modelmapper</groupId>
        <artifactId>modelmapper</artifactId>
        <version>2.4.5</version>
      </dependency>

      <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>1.4.2.Final</version>
      </dependency>

      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
		  </dependency>

      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
      </dependency>

    </dependencies>
    ```

4. Agrega las siguientes configuraciones a `application.properties`
   ```javascript
   spring.datasource.url=jdbc:mysql:
   spring.datasource.username=bbvajatest
   spring.datasource.password=bbvajatest
   ```

5. Compila el proyecto (puede demorar un poco la primera vez). Recomendamos usar la herramienta Postman para probar las requests a la API.

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- USAGE EXAMPLES -->
## Uso ⚙️

A continuacion se encuentran ejemplos de los casos de uso de los diferentes endpoints.
<!--Additional screenshots, code examples and demos work well in this space. You may also link to more resources.-->

| Metodos | Recurso | Endpoint | Respuesta | Requerimiento |
|--------|:--------:|----------|-----------|---------------|
| GET    | FILMS | /films | `list of films` | |
| POST   |       | /films | `posted film data` | title, rating, date, coverImage |
| PUT    |       | /films/{id} | `updated film data` | id in parameters |
| DELETE |       | /films/{id} | `deleted film data` | id in parameters |
| GET    | CHARACTERS | /characters | `list of characters` | |
| POST   |            | /characters | `posted character data` | name, age, image, weight, story |
| PUT    |            | /characters/{id} | `updated character data` | id in parameters |
| DELETE |            | /characters/{id} | `deleted character data` | id in parameters |
| GET    | GENRES | /genre | `list of genres` | |
| POST   |        | /genre | `posted genre data` | name |
| PUT    |        | /genre/{id}| `updated genre data` | id in parameters |
| POST   |  USER  | /auth/signup | `created user` | username, email, password, role (admin, user, mod) |
| POST   |  USER  | /auth/signin | `user authentication token` | username, password |

### Authentication Response
```json
{
    "id": 1,
    "username": "julia",
    "email": "julia@admin.com",
    "roles": [
        "ROLE_ADMIN"
    ],
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWxpYSIsImlhdCI6MTY1NDUzMDA3OSwiZXhwIjoxNjU0NjE2NDc5fQ.B2fEH90zsuEk5WVST-H4N3qm0ycrW1bb8CSvkZgFt7rojBV3XRVV9X2Lm6etmcJBNi1YNpCt57GcPsv-B6F25A",
    "tokenType": "Bearer"
}
```

### Film Response in List
```javascript
{
  id: 1,
  title: "La obra maestra de mi vida",
  releaseDate: "1998-07-12T00:00:00.000+00:00",
  coverImage: "posterMaestro.png"
}
```

### Film Response with id
```javascript
{
  id: 1,
  title: "La obra maestra de mi vida",
  rating: 10,
  releaseDate: "1998-07-12T00:00:00.000+00:00",
  coverImage: "posterMaestro.png",
  characters: [
    {
      id: 1,
      name: "Cheesy",
      age: 2,
      image: "cheesy.png",
      weight: 12,
      story: "Perro callejero en busca de un dueño para toda la vida"
    }
  ],
  genre: {
    id: 4,
    name: "infantil"
  }
}
```
### Character Response in List
```javascript
{
  id: 1,
  name: "Cheesy",
  image: "cheesy.png"
}
```

### Character Response with id

```javascript
{
  id: 1,
  name: "Cheesy",
  age: 2,
  image: "cheesy.png",
  weight: 12,
  story: "Perro callejero en busca de un dueño para toda la vida",
  actFilm: [
    {
      id: 1,
      title: "La obra maestra de mi vida",
      rating: 10,
      releaseDate: "1998-07-12T00:00:00.000+00:00",
      coverImage: "posterMaestro.png",
      genre: {
        id: 4,
        name: "infantil"
      }
    }
  ]
}
```
### Genres Response
```javascript
{
  id: 1,
  name: "aventuras"
}
```

### Generic Error Response
```json
{
    "status": "BAD_REQUEST",
    "message": "Existe un problema con los datos enviados",
    "errors": [
        "El campo coverImage no puede ser nulo",
        "El campo date no puede estar vacio",
        "El campo title no puede ser nulo",
        "El campo title no puede estar vacio",
        "El campo date no puede ser nulo",
        "El campo genre no puede ser nulo",
        "El campo coverImage no puede estar vacio"
    ]
}
```
<p align="right">(<a href="#top">back to top</a>)</p>

<!-- DEPLOY -->
## Despliegue 📦

El proyecto fue desplegado a través de [Heroku](https://www.heroku.com/) 

Link del proyecto: [https://bbva-testja.herokuapp.com/](https://bbva-testja.herokuapp.com/)

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- AUTHORS -->
## Autores ✒️

- Agustin Conde - [@agusconde18](https://github.com/agusconde18) - [linkedin](https://www.linkedin.com/in/agustin-adrian-conde/) 
- Julia Scodelari - [@juliascode96](https://github.com/juliascode96) - [linkedin](https://www.linkedin.com/in/julia-scodelari/)
- Brian Ciszewski Alonso - [@BrianCiszewski-bbva](https://github.com/BrianCiszewski-bbva) - [linkedin](https://www.linkedin.com/in/brian-matias-ciszewski-alonso-938a47202/)
- Iara Baya Vargas - [@iarabaya](https://github.com/iarabaya) - [linkedin](https://www.linkedin.com/in/iarabayavargas/)

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTRIBUTING -->
## Contribuyendo 🤝

Cualquier contribucion y mejora al proyecto es **muy apreciado** por nosotros.

Si tienes alguna sugerencia que pueda mejorar este proyecto, por favor crea una rama del repositorio y un pull request. O simplemente abre un issue con la etiqueta "enhancement". Muchas gracias!

1. Haz una rama del Proyecto
2. Crea una rama Feature (`git checkout -b feature/AmazingFeature`)
3. Haz commit de tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Haz push de tu rama (`git push origin feature/AmazingFeature`)
5. Abre una Pull Request

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->


