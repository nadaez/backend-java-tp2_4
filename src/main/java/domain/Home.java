package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Home {

  Long id;
  String taille;
  String adresse;
  int nb_pieces;


  public Home() {

  }

  public Home(String taille, String adresse, int nb_pieces) {
    this.taille = taille;
    this.adresse = adresse;
    this.nb_pieces = nb_pieces;
  }

  public int getNb_pieces() {
    return nb_pieces;
  }

  public void setNb_pieces(int nb_pieces) {
    this.nb_pieces = nb_pieces;
  }

  public String getAdresse() {
    return adresse;
  }

  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }

  List<Heater> heaters = new ArrayList<Heater>();

  @Id
  @GeneratedValue
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTaille() {
    return taille;
  }

  public void setTaille(String taille) {
    this.taille = taille;
  }

  @OneToMany(targetEntity = Heater.class)
  @JoinColumn(name = "id_home", referencedColumnName = "id")
  public List<Heater> getHeaters() {
    return heaters;
  }

  public void setHeaters(List<Heater> heaters) {
    this.heaters = heaters;
  }

  public void addHeater(Heater heater) {
    heaters.add(heater);
  }
}
