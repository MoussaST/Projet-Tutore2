import java.io.*;
import java.text.Format;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Personnage extends Carte {
	private int pa;
	private int blessure ;
	private int numJoueur;
	private int experience;

	private Caracteristique caract;
	private Capacite capacite;
	private Objets objet;
	private String mainGauche;
	private String mainDroite;
	
	// CONSTRUCTEUR 
	public Personnage() {
		this.caract=new Caracteristique ();
		this.objet=new Objets();
		this.capacite=new Capacite(this.objet,this.caract);
		this.pa=20;
		this.blessure=6;
		this.numJoueur=1;
		this.experience=0;
		this.mainDroite=" ";
		this.mainGauche=" ";
	}

	public String getMainGauche() {
		return mainGauche;
	}
	public void setMainGauche(String mainGauche) {
		this.mainGauche = mainGauche;
	}
	public String getMainDroite() {
		return mainDroite;
	}
	public void setMainDroite(String mainDroite) {
		this.mainDroite = mainDroite;
	}

	// GETTERS
	public int getExperience() {
		return experience;
	}
	public Caracteristique getCar() {
		return caract;
	}
	public Capacite getCap() {
		return capacite;
	}
	public Objets getOpj() {
		return objet;
	}
	public int getNumJoueur() {
		return numJoueur;
	}
	public int getPa(){
		return this.pa;
	}
	public int getBlessure(){
		return this.blessure;
	}

	// STTERS
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public void setCar(Caracteristique caract) {
		this.caract = caract;
	}
	public void setCap(Capacite capacite) {
		this.capacite = capacite;
	}
	public void setOpj(Objets objet) {
		this.objet = objet;
	}
	public void setNumJoueur(int numJoueur) {
		this.numJoueur = numJoueur;
	}
	public void setPa(int pa){
		this.pa=pa;
	}
	public void setBlessure(int blessure){
		this.blessure=blessure;
	}


	// METHODES
	public void initialisation(){
		this.caract.initialisation();
	}
	public boolean perdu(){
		if(this.getBlessure()<=0){
			return true;
		}
		else {
			return false;
		}
	}

	public void enregistrerPerso(Personnage p){
		File file = new File("personnage.txt");
		try {
		      FileWriter writer = new FileWriter(file);
		      writer.write(String.valueOf(p));
			  writer.close();
			  
		} catch (IOException e){
			System.err.println(e);
		}
	}

	public String toString() {
		String s= this.objet.toString() + "Vos caractéristique : \n" + this.caract.toString() + this.capacite.toString() + "\n";
		s=s+"Votre niveau de blessures est  ";
		if(this.getBlessure()==6){
			s=s+"en forme \n";
		}
		else if(this.getBlessure()==5){
			s=s+"Blessures superficielles \n";
		}
		else if(this.getBlessure()==4){
			s=s+"Légèrement blessé \n";
		}
		if(this.getBlessure()==3){
			s=s+"Blessé \n";
		}
		if(this.getBlessure()==2){
			s=s+"Gravement blessé \n";
		}
		if(this.getBlessure()==1){
			s=s+"Inconscient \n";
		}
		if(this.getBlessure()<=0){
			s=s+"Mort \n";
		}
		s=s+"Vos points d'action :"+this.getPa()+"\n"; 
		return s;
	}
}
