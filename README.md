# backend-java-tp2_4

One Paragraph of project description goes here

## Getting Started


### Prerequisites

What things you need to install the software and how to install them

```
Base de donnees ()Java  -  Maven

```

### Installing

1: Configuration server 
ficher persistence.xml (src/java/resources/META-INF) 
utiliser les informations de votre server 'nom' 'password' et nom de la BD  'jpaservelet'

            <property name="hibernate.connection.password" value="yourPassword"/>
            <property name="hibernate.connection.url" value="jdbc:mysql://localhost/YourBDName"/>
            <property name="hibernate.connection.username" value="Name"/>

Demarer votre server (Wamp)

2: lancer l'application JpaTest(Java)
```
il créaion des tables dans la BD
```

3: Lancer l'application MyServlet (Maven)
```
GLOA : tomcat7:run
```
4: Faite vos requetes (postman) ou utiliser le navigateur web pour le visionage des données



## Authors

* **Nabe** - *work* - [PurpleBlue](https://github.com/diarranabe)
* **Nada** - *work* - [PurpleOrange](https://github.com/nadaez)
* **Maud** - *work* - [PurpleBlack](https://github.com/maudmcok)

See also the list of [contributors](https://github.com/diarranabe/backend-java-tp2_4/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
