package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("Chauffage")
public class Heater extends Device {

  String name;
  String puissance;

  public Heater() {
    super();
  }

  public Heater(String prix, String puissance) {
    this.puissance = puissance;
    this.setPrix(prix);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPuissance() {
    return puissance;
  }

  public void setPuissance(String p) {
    this.puissance = p;
  }
}
