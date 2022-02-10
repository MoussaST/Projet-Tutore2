import java.util.Scanner;

public class Attaquer {
	private int x;
	private int y;

	public Attaquer(){
		this.x=0;
		this.y=0;
	}
	
	// SETTERS
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	// GETTERS
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void attaquer(Carte t, Personnage p1, Personnage p2){
		boolean verif=false;
		String mainAttaque;
		System.out.println("Saisie la case d'attaquement");
		Scanner n = new Scanner(System.in);
		System.out.print("ligne : ");
		this.setX(n.nextInt());
		System.out.print("colonne : ");
		this.setY(n.nextInt()+1);
		
		if(t.getTab(this.getX(),this.getY()).equals(String.valueOf(p2.getNumJoueur()))){
			if(p1.getCap().getAttaque()>p2.getCap().getEsquive()){
				try {
					int i = 0;
					while (i<7) {
						System.out.print(".");
						Thread.sleep(800); //2 secs
						i+=1;
					}
					System.out.println();
					System.out.println("Cible touchée !!");
					Thread.sleep(2000); //2 secs
					if(p1.getCap().getDegats()>=p2.getCap().getDefense()){
						p2.setBlessure(p2.getBlessure()-3);
					}
					else{
						System.out.println(p2.getNumJoueur()+" personnage s'en tire indemne ");
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				
			}		
		}
		else if((t.getTab(this.getX(),this.getY()).equals("C"))||(t.getTab(this.getX(),this.getY()).equals("L"))) { //regarde si il y a des PNJ
			try {
				int i = 0;
				while (i<10) {
					System.out.print(".");
					Thread.sleep(800); //2 secs
					i+=1;
				}
				System.out.println();
				System.out.println(" Cible touchée");
				Thread.sleep(2000); //2 secs
				t.setTab(this.getX(),this.getY(),"--");
				int randomNum = 1 + (int)(Math.random()*2);
				if(randomNum==1){
					t.setTab(this.getX(),this.getY(),"a"); // gagne arme
				}
				else {
					t.setTab(this.getX(),this.getY(),"P"); // gagne potions
				}
			} catch (Exception e) {
				System.out.println(e);
			}	
		}
		else{
			try {
				int i = 0;
				while (i<10) {
					System.out.print(".");
					Thread.sleep(300); //2 secs
					i+=1;
				}
			System.out.println("Attaque non réussi");
			Thread.sleep(1000); //2 secs
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public void action(Carte t,Personnage p1,Personnage p2){
		int ligne=t.rechercheligne(Integer.toString(p1.getNumJoueur()));
		int colonne=t.recherchecolonne(Integer.toString(p1.getNumJoueur()));
		int x = ligne;
		int y = colonne;
		int i=0;
		
		while(i<3){
			x=x+1;
			if((t.getTab(x,y).equals(Integer.toString(p2.getNumJoueur())))){
				p2.getCap().setDegats(0);
			}
			if ((t.getTab(x,y).equals("C"))||(t.getTab(x,y).equals("L"))){
				t.setTab(x,y,"--");
			}
			i=i+1;
		}
		x=ligne;
		i=0;
		while(i<3){
			y=y+1;
			if((t.getTab(x,y).equals(Integer.toString(p2.getNumJoueur())))){
				p2.getCap().setDegats(0);
			}
			if((t.getTab(x,y).equals("C"))||(t.getTab(x,y).equals("L"))){
				t.setTab(x,y,"--");
			}
			i=i+1;
		}
		x=ligne;
		y=colonne;
		i=0;
		while(i<3){
			if((x-1)>=0){
				x=x-1;
			}
			if((t.getTab(x,y).equals(Integer.toString(p2.getNumJoueur())))){
				p2.getCap().setDegats(0);
			}
			if ((t.getTab(x,y).equals("C"))||(t.getTab(x,y).equals("L"))){
				t.setTab(x,y,"--");
			}
			i=i+1;
		}
		x=ligne;
		i=0;
		while(i<3){
			if((y-1)>=0){
				y=y-1;
			}
			if((t.getTab(x,y).equals(Integer.toString(p2.getNumJoueur())))){
				p2.getCap().setDegats(0);
			}
			if ((t.getTab(x,y).equals("C"))||(t.getTab(x,y).equals("L"))){
				t.setTab(x,y,"--");
			}
			i=i+1;
		}
	}
}