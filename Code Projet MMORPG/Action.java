import java.util.Scanner;

public class Action extends Carte{
	private int action;
	
	public Action (){
		this.action=0;
	}
	
	// GETTERS
	public int getAction(){
		return this.action;
	}

	// SETTERS 
	public void setAction(int action){
		this.action=action;
	}
	
	public int choisir(Personnage p) {
		Scanner saisie = new Scanner(System.in);
		boolean verif=false;
		String 	s="Vous pouvez : \n"
			+"1 - vous déplacer (cout 2PA) \n"
			+"2 - attaquer (cout 3PA) \n"
			+"3 - utiliser un objet (cout 3PA) \n"
			+"4 - ramasser un objet (cout 2PA) \n"
			+"5 - finir et garder les PA restants \n"
			+"6 - enregistrer mon personnage \n"
			+"Votre choix : ";
		while(!(verif)){
			System.out.print(s);
			this.setAction(saisie.nextInt());	
			if((this.getAction()<1)||(this.getAction()>6)){ //tant que la saisie n'est pas correct on reessaie
				System.out.println("Aïe votre choix est incorrect !");
				System.out.print(s);
			}
			else{
				if (this.getAction()==6) {
					p.enregistrerPerso(p);
				}
				else if(((this.getAction()==1)&&(p.getPa()>=2))||(((this.getAction()==2)&&(p.getPa()>=3)))||((this.getAction()==3)&&(p.getPa()>=3))||((this.getAction()==4)&&(p.getPa()>=2))||(this.getAction()==5)){
					if((this.getAction()==1)||(this.getAction()==4)){
						p.setPa(p.getPa()-2);
					}
					else if((this.getAction()==2)||(this.getAction()==3)){
						p.setPa(p.getPa()-3);
					}
					verif=true;
				}
				else{
					System.out.println("Aïe ! Vous n'avez pas assez de PA pour faire cette action ! ");
				}
				
			}
		}
		return this.action;
	}

}
