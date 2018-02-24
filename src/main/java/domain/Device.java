package domain;
	
	import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
	import javax.persistence.Inheritance;
	import javax.persistence.InheritanceType;
	import javax.persistence.JoinColumn;
	import javax.persistence.ManyToOne;
	@Entity 
	@Inheritance(strategy=InheritanceType.SINGLE_TABLE) 
	@DiscriminatorColumn(name="DEVICE_TYPE")
	public class Device implements Serializable {
	
		public Device(Long idDev, String prix) {
			super();
			this.idDev = idDev;
			Prix = prix;
		}
	
		public Device() {
			super();
		}
	
		Long idDev;
		String Prix;
	
	
		public String getPrix() {
			return Prix;
		}
	
		public void setPrix(String prix) {
			Prix = prix;
		}
	
		@Id
		@GeneratedValue
		public Long getIdDev() {
			return idDev;
		}
	//
	//public Person getPerson() {
//			return person;
//		}
	
//		public void setPerson(Person person) {
//			this.person = person;
//		}
	//
		public void setIdDev(Long idElecDev) {
			this.idDev = idElecDev;
		}
	
		//to one person 1 phone est pourseulement 1  personne
//		@ManyToOne
//		Person person;
	
	}

