package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("Chauffage")
public class Heater extends Device {

    public Heater() {
		super();
	}

//	Long id;
    String name;
String puissance;

//    @Id
//    @GeneratedValue
//    public Long getId() {
//        return id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

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
