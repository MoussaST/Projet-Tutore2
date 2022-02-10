import java.awt.*;
import java.nio.*;
import java.util.*;
import java.util.logging.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {
	private JTextField txtListe;

	private JButton btnSauver;
	private JButton btnCharger;
	private JButton btnValider;

	static final int ZONE_AUTRE = 3;
	static final int CODE_VALIDER = 1;
	static final int CODE_CHARGER = 2;
	static final int CODE_SAUVER = 3;

	public Main(String str) {
		super("Projet MMORPG");
		this.initComposants();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.centrer(0.80);
		this.setVisible(true);
		this.initEcouteurs();	
	}

	public void initComposants() {
		JPanel panPrincipal = new JPanel();
		panPrincipal.setLayout(new BorderLayout());
		this.add(panPrincipal);
		
		panPrincipal.add(boutonCharger_Sauver(), BorderLayout.PAGE_START);
		panPrincipal.add(boutonValider(), BorderLayout.PAGE_END);
		panPrincipal.add(buildPanelMeteo(), BorderLayout.CENTER);
	}

	public void initEcouteurs() {
		this.btnValider.addActionListener(new EcouteurBoutons(ZONE_AUTRE, CODE_VALIDER));
		this.btnCharger.addActionListener(new EcouteurBoutons(ZONE_AUTRE, CODE_CHARGER));
		this.btnSauver.addActionListener(new EcouteurBoutons(ZONE_AUTRE, CODE_SAUVER));
	}

	public JPanel boutonCharger_Sauver() {
		JPanel pan = new JPanel();

		btnCharger = new JButton("Charger");
		btnCharger.setBackground(Color.YELLOW);
		btnCharger.setForeground(java.awt.Color.red);
		pan.add(btnCharger);

		btnSauver = new JButton("Sauvegarder");
		btnSauver.setBackground(Color.YELLOW);
		btnSauver.setForeground(java.awt.Color.red);
		pan.add(btnSauver);

		pan.setBorder(BorderFactory.createEtchedBorder());
		return pan;
	}
	
	public JPanel boutonValider() {
		JPanel pan = new JPanel();

		btnValider = new JButton("Valider");
		btnValider.setBackground(Color.RED);
		btnValider.setForeground(java.awt.Color.white);
		pan.add(btnValider);

		pan.setBorder(BorderFactory.createEtchedBorder());
		return pan;
	}
 
	public JPanel buildPanelMeteo() {
        JPanel pan = new JPanel();

        pan.setLayout(new BorderLayout());
        txtListe = new JTextField();
        txtListe.setPreferredSize( new Dimension( 200, 420 ) );
        pan.setLayout(new GridBagLayout());
		pan.add(txtListe);

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("carte.txt")))) {
            txtListe.read(reader, "File");
        } catch (IOException exp) {
            exp.printStackTrace();
        }

        pan.setBorder(BorderFactory.createEtchedBorder());
        return pan;
	}
	
	public void valider() {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("carte.txt")))) {
            txtListe.read(reader, "File");
        } catch (IOException exp) {
            exp.printStackTrace();
		}
	}

	public void charger() {
		JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
		jfc.setDialogTitle("Definir un fichier");
		
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.showSaveDialog(this);
		
		// Obtenir le fichier..
		String fichier = jfc.getSelectedFile().getName();

		try (BufferedReader reader = new BufferedReader(new FileReader(new File(fichier)))) {
            txtListe.read(reader, "File");
        } catch (IOException exp) {
            exp.printStackTrace();
		}
	}

	public boolean sauver(){
		// Initialiser un JFileChooser...
		Carte t = new Carte();
		JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
		jfc.setDialogTitle("Definir un fichier");
		
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.showSaveDialog(this);
		
		// Obtenir le fichier..
		String fichier = jfc.getSelectedFile().getName();
		if(fichier.trim().equals(""))
			// Non du fichier incorrect
			return false;
		try {
			FileWriter writer = new FileWriter(fichier);
			writer.write(String.valueOf(t.toString()));
			writer.close();
		} catch (IOException ex){
			Logger.getLogger(Serializable.class.getName()).log(Level.SEVERE, null, ex);
		}
		return true;
	}

	public static void main(String[] args) {
		new Main("carte.txt");
		
		Scanner saisie = new Scanner(System.in);
		Carte t= new Carte();
		Action choix = new Action();
		Attaquer attaque = new Attaquer();
		Personnage p1 = new Personnage();
		Personnage p2 = new Personnage();
		p1.setNumJoueur(1);
		p2.setNumJoueur(2);
		
		int choisir=1;
		Personnage joueur ;
		Personnage joueuradv;
		int compteur=1;

		t.setTab(4, 5, String.valueOf(p1.getNumJoueur()));
		t.setTab(15, 16, "2");

		System.out.println("------------- Joueur n°1 -------------");
		System.out.println("Personnalise ton personnage ! ");
		p1.initialisation();
		System.out.println(p1.toString());
		System.out.println("------------- Joueur n°2 -------------");
		System.out.println("Personnalise ton personnage ! ");
		p2.initialisation();
		System.out.println(p2.toString());
		
		
		while((p1.perdu()==false)&&(p2.perdu()==false)&&(choisir!=5)){	
			if(compteur%2!=0){
				joueur=p1;
				joueuradv=p2;
			}
			else{
				joueur=p2;
				joueuradv=p1;
			}
			
			System.out.println("##################################################################");
			System.out.println("------------- C'est au tour du Joueur n° " + joueur.getNumJoueur() + "-------------");
			t.enregistrerCarte(t);
			System.out.println();
			System.out.println();
			System.out.println(joueur.toString());
			
			if(joueur.getBlessure()<2){
				System.out.println(" Votre personnage ne peux plus jouer, il est inconscient, il a besoin d'une potion de soin ");
				int nj=0;
				boolean verifnj=false;
				while((nj<4)&&(verifnj==false)){
					if((joueur.getOpj().getactionObject(nj).equals("S"))&&(joueur.getOpj().getNomObject(nj).equals("potion de soin"))){
						System.out.println("Voulez vous utiliser votre potion de soin maintenant y/n");
						String choixnj=saisie.nextLine();
						if(choixnj.equals("y")){
							if((joueur.getBlessure()+3)>6){
								joueur.setBlessure(6);
							}
							else {
								joueur.setBlessure(joueur.getBlessure()+3);
							}
						}
						else{
							System.out.println("Votre personnage est en danger");
						}
					}
					nj=nj+1;
				}
			}
			else{
				choisir=choix.choisir(joueur);
				if(choisir==1){
					t.initialise(String.valueOf(joueur.getNumJoueur()));
				}
				else if(choisir==2){
					attaque.attaquer(t,joueur,joueuradv);
					joueur.setPa(joueur.getPa()+1);
				}
				else if(choisir==3){
					int ch=joueur.getOpj().choisirObjet(joueur.getOpj());
					if((ch==1)||(ch==2)){
						if(joueur.getOpj().getactionObject(ch-1).equals("D")){
							joueur.getOpj().tabObjet[2][ch-1]="E";
							if(ch==1){
								String reponse;
								String main ="Vous voulez l'utiliser dans dans quelle main";
								if(joueur.getMainDroite().equals(" ")){
									main=main+"\n G : main Gauche";
								}
								if(joueur.getMainGauche().equals(" ")){
									main=main+"\n D : main Droite";
								}
								System.out.println(main);
								reponse=saisie.nextLine();
								if(reponse.equals("D")){
									joueur.setMainDroite(joueur.getOpj().getNomObject(ch-1));
								}
								else if(reponse.equals("G")){
									joueur.setMainGauche(joueur.getOpj().getNomObject(ch-1));
								}			
							}
							else{
								joueur.getCap().setDefense(joueur.getCap().getDefense()+joueur.getOpj().getValeurObject(ch-1));
							}
						}
						else{
							joueur.getOpj().tabObjet[2][ch-1]="D";	
						}
					}
					else if((ch==3)||(ch==4)){
						if(joueur.getOpj().getactionObject(ch-1).equals("S")){
							joueur.getOpj().tabObjet[2][ch-1]="A";						
							if(ch==4){
								if((joueur.getBlessure()+3)>6){
									joueur.setBlessure(6);
								}
								else {
									joueur.setBlessure(joueur.getBlessure()+3);
								}
							}
							else{
								attaque.action(t, joueur, joueuradv);
							}
						}
						else{
							joueur.getOpj().tabObjet[2][ch-1]="S";		
						}
					}
				}
				else if(choisir==4){
					joueur.getOpj().ajouterObjets(t);
				}
				else if(choisir==5){
					File file = new File("projet.txt");
					 
					 try {
					       FileWriter writer = new FileWriter(file);
					       writer.write(""+joueur);
					       writer.close();
					} catch (IOException ex){
						Logger.getLogger(Serializable.class.getName()).log(Level.SEVERE, null, ex);
					}
					System.out.println("******************* A LA PROCHAINE LOOSER ! *******************");
				}
			}
			compteur=compteur+1;
		}
		
		if(p1.perdu()==true){
			System.out.println("******************* Félicitations *******************");
			System.out.println("******************* le deuxième joueur a gagné *******************");
			p1.setExperience(p1.getExperience()+2);
		}
		if(p2.perdu()==true){
			System.out.println("******************* Félicitations *******************");
			System.out.println("******************* le premier joueur a gagné *******************");
			p2.setExperience(p2.getExperience()+2);
		}
	}

	class EcouteurBoutons implements ActionListener {

		private int zone;
		private int code;
		

		public EcouteurBoutons(int z, int c) {
			this.zone = z;
			this.code = c;
		}

		public void actionPerformed(ActionEvent e) {
			
			switch (zone) {
			case ZONE_AUTRE:
				switch (code) {
				case CODE_CHARGER:
					charger();
					break;
				case CODE_SAUVER:
					sauver();
					break;
				case CODE_VALIDER:
					txtListe.setText(null); // avant d'appliquer je "nettoie"
					valider();
					break;
				default:
					break;
				}
			default:
				break;
			}
		}
	}
    
    public void centrer(double d) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int largeur = (int) (d * dim.width);
		int longueur = (int) (d * dim.height);
		this.setBounds((dim.width - largeur) / 2, (dim.height - longueur) / 2, largeur, longueur);
	}
}