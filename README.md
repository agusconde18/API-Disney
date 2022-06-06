<div id="top"></div>

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![MIT License][license-shield]][license-url]

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

El proyecto esta creado para satisfacer las necesidades del cliente segun los objetivos del challenge.

Here's why:
* Your time should be focused on creating something amazing. A project that solves a problem and helps others
* You shouldn't be doing the same tasks over and over like creating a README from scratch
* You should implement DRY principles to the rest of your life :smile:


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

* npm
  ```sh
  npm install npm@latest -g
  ```

### Instalacion 🔧

1. Clona el repositorio
   ```sh
   git clone https://github.com/agusconde18/API-Disney.git
   ```
2. Abre el proyecto con [IntelliJ IDEA](https://www.jetbrains.com/idea/)
3. Instala las dependencias de maven
   ```html
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
   ```
4. Agrega las siguientes configuraciones a `application.properties`
   ```
   spring.datasource.url=jdbc:mysql:
   spring.datasource.username=bbvajatest
   spring.datasource.password=bbvajatest
   ```

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- USAGE EXAMPLES -->
## Uso ⚙️

A continuacion se encuentran ejemplos de los casos de uso de los diferentes endpoints.
<!--Additional screenshots, code examples and demos work well in this space. You may also link to more resources.-->

| Metodos | Recurso | Endpoint | Respuesta | 
|--------|:--------:|----------|-----------|
| GET    | FILMS | /films | `list of films` | 
| POST   |       | /films | `posted film data` | 
| PUT    |       | /films/{id} | `updated film data` | 
| DELETE |       | /films/{id} | `deleted film data` | 
| GET    | CHARACTERS | /characters | `list of characters` | 
| POST   |            | /characters | `posted character data` | 
| PUT    |            | /characters/{id} | `updated character data` | 
| DELETE |            | /characters/{id} | `deleted character data` | 
| GET    | GENRES | /genres | `list of genres` | 
| POST   |        | /genres | `posted genre data` | 
| PUT    |        | /genres/{id}| `updated genre data` | 


### Film Response in List
```json
{
  id: 1,
  title: "La obra maestra de mi vida",
  releaseDate: "1998-07-12T00:00:00.000+00:00",
  coverImage: "posterMaestro.png"
}
```

### Film Response with id
```json
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
```json
{
  id: 1,
  name: "Cheesy",
  image: "cheesy.png"
}
```

### Character Response with id

```json
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
```json
{
  id: 1,
  name: "aventuras"
}
```

### Error Response
```json
{
  status: "BAD_REQUEST",
  message: "No se pudo encontrar un personaje con dicho ID",
  errors: [
    ""
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


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt

