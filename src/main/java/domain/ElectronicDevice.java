package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("Phone")
public class ElectronicDevice extends Device {
    String name;
    String marque;


  public ElectronicDevice(String prix, String name, String marque) {
    this.setPrix(prix);
    this.name = name;
    this.marque = marque;
  }

  public ElectronicDevice(Long idDev, String name) {
  		super(idDev, name);
  	}

  public ElectronicDevice() {
  	super();
  }
    public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
