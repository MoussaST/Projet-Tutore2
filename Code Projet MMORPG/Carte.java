
import java.io.*;
import java.util.Scanner;
import java.text.Format;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Carte {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[20m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[22m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	private String tab[][]=new String[60][60]; //un tableau dans un tableau 
	private int abscisse; //initialisation des abscisses
	private int ordonne; //initialisation des ordonnees
	
	public Carte(){
		this.abscisse=22; //une longueur de 22
		this.ordonne=20; //une largeur de 20
		int j=0; 
		int i=0;
		int c=64;
		String s=" ";

		while (i<=22) {
			while (j<=20) {
				this.tab[i][j]=" ";
				j+=1;
			}
			i+=1;
		}

		i = 0;
		while (i<=22) {
			j = 0;
			while (j<=22) {
				if(j==0){
					if(i==0){
						s=s+" ";
						this.tab[i][j]=s;
					}
					else if(i<10){
						s=String.valueOf(i)+"--->";
						this.tab[i][j]=s;
					}
					else{
						s=String.valueOf(i)+"->";
						this.tab[i][j]=s;	
					}
				}
				if(i==0){ // TABLE ASCII ALPHABET
					this.tab[i][j]=s;
					c=c+1;
					s =""+((char)c);
				}
				j+=1;
			}
			i+=1;
		}

		i = 1;
		while (i<=22) {
			j = 1;
			while (j<=20) {
				this.tab[i][j]="--";
				if((i==1)||(i==22)||(j==1)||(j==20)){
					this.tab[i][j]="#";
				}
				j+=1;
			}
			i+=1;
		}

		i=4;
		for(j=9;j<18;j++){
			this.tab[i][j]="#"; // les murs à la quatrième ligne
		}
		i=10;
		for(j=5;j<15;j++){
			this.tab[i][j]="#"; // les murs à dizième ligne
		}
		i=18;
		for(j=11;j<15;j++){
			this.tab[i][j]="#"; // Les murs à la dixhuitième ligne
		}
		j=14;
		for(i=5;i<9;i++){
			this.tab[i][j]="#"; // Les murs à la quatorzième ligne
		}
		j=10;
		for(i=14;i<21;i++){
			this.tab[i][j]="#"; // Les murs à la dixième ligne
		}

		// SANS COULEURS
		this.setTab(2,10, "C"); // on remplace et on met les objets/monstres
		this.setTab(3,13, "L");
		this.setTab(7,9, "C");
		this.setTab(6,5, "a");
		this.setTab(5,17,"L");
		this.setTab(9,6,"L");
		this.setTab(11,10,"P");
		this.setTab(11,15,"L");
		this.setTab(11,4,"C");
		this.setTab(13,7,"L");
		this.setTab(15,4,"P");
		this.setTab(17,4,"C");
		this.setTab(15,9,"C");
		this.setTab(20,15,"P");
		this.setTab(19,16,"C");
		this.setTab(17,15,"L");
		this.setTab(20,7,"L");
		this.setTab(18,8,"a");

	}
	
	public void setTab(int i, int j,String s){
		this.tab[i][j]=s;
	}
	public String getTab(int i,int j ){
		return this.tab[i][j];
	}
	public int getabscisse(){
		return this.abscisse;
	}
	public void setabscisse(int abscisse){
		this.abscisse=abscisse;
	}
	public int getordonne(){
		return this.ordonne;
	}	
	public void setordonne(int ordonne){
		this.ordonne=ordonne;
	}
	
	public String afficherCarte(){ 
		String ch=" ";
		int i = 0;
		while (i<=22) {
			int j = 0;
			while (j<=20) {
				ch=ch+getTab(i,j);
				j+=1;
			}
			ch=ch+"\n";
			i+=1;
		}
		return ch;
	}

	public int rechercheligne(String s){
		for(int i=0;i<22;i++){
			for(int j=0;j<20;j++){
				if(this.getTab(i,j).equals(s)){
					return i;
				}
			}
		}
		return -1;
	}
	public int recherchecolonne(String s){
		for(int i=0;i<22;i++){
			for(int j=0;j<20;j++){
				if(this.getTab(i,j).equals(s)){
					return j;
				}
			}
		}
		return -1;
	}

	public void supprime(String s){
		for(int i=0;i<22;i++){
			for(int j=0;j<20;j++){
				if(this.getTab(i,j).equals(s)){
					this.tab[i][j]="--";
				}
			}
		}
	}

	public void initialise(String s){
		Scanner n = new Scanner(System.in);
		int verif=0;
		while(verif==0){
			System.out.println("Veuillez saisir la LIGNE et ensuite la COLONNE");
			
			System.out.print("ligne : ");
			this.ordonne=n.nextInt();
			System.out.print("colonne : ");
			this.abscisse=n.nextInt()+1;
			if(this.getTab(this.ordonne, this.abscisse)=="--"){
				this.setTab(this.ordonne,this.abscisse,s);
				verif=1;
			}
			else{
				System.out.println("ERREUR: Case occupé");
			}
		}
		supprime(s);
		this.tab[this.ordonne][this.abscisse]=s;
		
	}
	
	public String toString(){
		String ch=" ";
		for(int i=0;i<=22;i++){
			for(int j=0;j<=20;j++){
				ch=ch+getTab(i,j);
			}
			ch=ch+"\n";
		}
		return ch;
	}

	public void enregistrerCarte(Carte c){			
		
		File file = new File("carte.txt");
		 
		try {
		      FileWriter writer = new FileWriter(file);
		      writer.write(String.valueOf(c));
		      writer.close();
		} catch (IOException ex){
			Logger.getLogger(Serializable.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	
	
}
