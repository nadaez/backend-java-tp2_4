package domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DEVICE_TYPE")
public class Device implements Serializable {
  Long idDev;
  String prix;


  public Device(Long idDev, String prix) {
    super();
    this.idDev = idDev;
    this.prix = prix;
  }

  public Device() {
    super();
  }


  public String getPrix() {
    return prix;
  }

  public void setPrix(String prix) {
    this.prix = prix;
  }

  @Id
  @GeneratedValue
  public Long getIdDev() {
    return idDev;
  }

  public void setIdDev(Long idElecDev) {
    this.idDev = idElecDev;
  }
}

