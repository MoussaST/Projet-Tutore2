import java.util.Scanner;

public class Caracteristique {
	private int force;
	private int adresse;
	private int endurance;
	
	public Caracteristique() {
		this.force = 0;
		this.adresse = 0;
		this.endurance = 0;
	}
	
	public Caracteristique(int force, int adresse, int endurance) {
		this.force = force;
		this.adresse = adresse;
		this.endurance = endurance;
	}

	public int getForce() {
		return force;
	}
	public int getAdresse() {
		return adresse;
	}
	public int getEndurance() {
		return endurance;
	}

	public void setForce(int force) {
		this.force = force;
	}
	public void setAdresse(int adresse) {
		this.adresse = adresse;
	}
	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}
	
	public String convertion(int n){
		String ch="";
		if(((int)n/3)!=0){
			ch=((int)n/3)+"D";
		}
		if((n-(3*((int)n/3)))!=0){
			ch=ch+" +"+(n-(3*((int)n/3)));
		}
		return ch;
	}
	
	public void initialisation(){
		Scanner n = new Scanner(System.in);
		System.out.println("Personnalisez votre personnage ! 18 degrés à répartir entre les trois caractéristiques. ");
		boolean verif=false;
		while(verif==false){
			System.out.println("Veuillez saisir le degré de force qui doit être compris entre 0 et 18");
			this.setForce(n.nextInt());
			verif=true;
			if((this.getForce()<0)||(this.getForce()>18)){
				System.out.println("degré de force invalide ! ");
				verif=false;
			}
		}
		verif=false;
		while(verif==false){
			System.out.println("Saisissez le degré d'adresse qui doit être entre [0.."+(18-this.force)+"]");
			this.setAdresse(n.nextInt());
			verif=true;
			if((this.getAdresse()<0)||(this.getAdresse()>(18-this.force))){
				System.out.println("degré d'adresse invalide! ");
				verif=false;
			}
		}	
		System.out.println("Votre endurance sera donc de : "+(18-(this.force+this.adresse)));
		this.setEndurance(18-(this.force+this.adresse));
		try {
			System.out.println();
			Thread.sleep(2000); //2 secs
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public String toString() {
		String s;
		s=" -Force : "+convertion(this.force) ;
		s=s+"\n -Adresse : "+convertion(this.adresse);
		s=s+"\n -Endurance : "+convertion(this.endurance)+"\n";
		return s ;
	}
	
		

}