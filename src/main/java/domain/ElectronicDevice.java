package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("Phone")
public class ElectronicDevice extends Device {
   // long id;
    String name;
    String marque;

    

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

//	@Id
//    @GeneratedValue
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}