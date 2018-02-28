# backend-java-tp2_4

L’objectif de ce projet est de construire une application type réseau social permettant de comparer sa consommation électrique avec ses amis et ses voisins.(#version Api)

## Getting Started

#### Design
Please refer to the [design](docs/design.md) for more information.

### Prerequisites

```
 Java  -
 Maven -
 Apache tomcat -
 Hibernate -
 Javax servlet -
 jersey -  
 Postman -

```

### Installing

1: Configuration server 


 dans le ficher persistence.xml (src/java/resources/META-INF) 
utiliser les informations de votre server 'nom' 'password' et nom de la BD  'jpaservelet'

            <property name="hibernate.connection.password" value="yourPassword"/>
            <property name="hibernate.connection.url" value="jdbc:mysql://localhost/YourBDName"/>
            <property name="hibernate.connection.username" value="Name"/>

2: Demarer votre server (Wamp)

3: lancer l'application JpaTest(Java)
```
il créaion des tables dans la BD
```

4: Lancer l'application MyServlet (Maven)
```
GLOA : tomcat7:run
```
5: Faite vos requetes (postman) ou utiliser le navigateur web pour le visionage des données



## Authors

* **Nabe** - *work* - [PurpleBlue](https://github.com/diarranabe)
* **Nada** - *work* - [PurpleOrange](https://github.com/nadaez)
* **Maud** - *work* - [PurpleBlack](https://github.com/maudmcok)

See also the list of [contributors](https://github.com/diarranabe/backend-java-tp2_4/contributors) who participated in this project.

## License

This project is licensed under the GNU License.
