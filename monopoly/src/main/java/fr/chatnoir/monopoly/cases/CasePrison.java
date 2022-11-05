package fr.chatnoir.monopoly.cases;

import java.util.Random;

import fr.chatnoir.monopoly.fenetres.FenetrePrincipale;
import fr.chatnoir.monopoly.io.Console;
import fr.chatnoir.monopoly.jeudeplateau.Case;
import fr.chatnoir.monopoly.jeumonopoly.JoueurMonopoly;
import fr.chatnoir.monopoly.jeumonopoly.PlateauMonopoly;

/**
 * Cr�e l'action de la case Prison
*@author HEGEL MOTOKOUA
*/
public class CasePrison extends Case {

	private boolean reponseQuestion = false;

	/**
	 * Indique le nom de la case
	 */
	public CasePrison() {
		super("Simple Visite", 0);
	}

	/**
	 * M�thode g�rant tous les cas d'un joueur en prison : <br>
	 * <ul>
	 * <li>Si un joueur est rest� 3 tours en prison, il doit payer 50�</li>
	 * <li>Si un joueur fait un double au lance de d�s, il peut sortir</li>
	 * <li>Si un joueur poss�de une carte Sortie de Prison et qu'il l'utilise, il se lib�re</li>
	 * </ul>
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {

		Console es = new Console();


		int lance = plateau.des.lancerDes();
		int d1 = plateau.des.getDe1();
		int d2 = plateau.des.getDe2();

		if(joueur.getEstPrison() == true){

			if(fp != null) fp.afficherDes(plateau);

			es.println("Voulez vous payer 50� pour sortir de prison ? ");

			if(getReponseQuestion()){
				es.println("OUI : " + joueur.getNom() + " d�cide de payer 50� pour sortir de prison.");
				joueur.retirerArgent(50);
				reponseQuestion = false;
				joueur.setEstPrison(false);
				joueur.setToursEnPrison(1);
				plateau.deplacerJoueur(joueur, lance);
				es.println("" + joueur.getNom() + " lance les d�s... [" + d1 + "][" + d2 + "]... et obtient un " + lance + " !");
				es.println("" + joueur.getNom() + " avance de " + lance + " cases et atterit sur " + plateau.getCaseActive().getNom());
				if(fp!= null) actionSortiePrison(plateau, joueur, fp);
			}
			else{
				if(joueur.getToursEnPrison() > 2) {
					es.println("NON : " + joueur.getNom() + " est a son 3e tour en prison, il sort et paye 50�.");
					joueur.retirerArgent(50);
					joueur.setEstPrison(false);
					joueur.setToursEnPrison(1);
					plateau.deplacerJoueur(joueur, lance);
					es.println("" + joueur.getNom() + " lance les d�s... [" + d1 + "][" + d2 + "]... et obtient un " + lance + " !");
					es.println("" + joueur.getNom() + " avance de " + lance + " cases et atterit sur " + plateau.getCaseActive().getNom());
					if(fp!=null) actionSortiePrison(plateau, joueur, fp);
				}
				else{
					es.println("NON : " + joueur.getNom() + " (tour " + joueur.getToursEnPrison() + ") d�cide de ne pas payer et lance ses d�s...");
					if(d1 == d2){
						es.println("  [" + d1 + "][" + d2 + "] Gagn�! " + joueur.getNom() + " sort de prison sans payer!");
						joueur.setEstPrison(false);
						joueur.setToursEnPrison(1);
						plateau.deplacerJoueur(joueur, lance);
						es.println("" + joueur.getNom() + " avance de " + lance + " cases et atterit sur " + plateau.getCaseActive().getNom());
						if(fp!=null) actionSortiePrison(plateau, joueur, fp);
					}
					else{
						es.println("  [" + d1 + "][" + d2 + "] Perdu!");
						joueur.setToursEnPrison(joueur.getToursEnPrison() + 1);
					}
				}
			}
		}
		else{
			es.println(" > Le joueur observe les criminels...");
			if(fp != null) fp.afficherMessage("Le joueur observe les criminels...");
		}

	}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Reprend le cours de la partie
	 */
	public void fenetreAction(FenetrePrincipale fp) {

		if(fp.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				reponseQuestion = true;
			fp.getPartie().reprendrePartie();
		}
		else if(fp.getPartie().getPM().getJoueurActif().getEstPrison())
			fp.afficherFenetrePrison();
		else
			fp.getPartie().reprendrePartie();
	}

	@SuppressWarnings("static-access")
	public void actionSortiePrison(PlateauMonopoly plateau, JoueurMonopoly joueur, FenetrePrincipale fp){

		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		plateau.getCase(joueur.getPosition()).fenetreAction(fp);
		fp.deplacerPion(joueur);
		fp.getPartie().pausePartie();
		while(fp.getPartie().getPausePartie() && !fp.getPartie().PARTIE_AUTO){ try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} }

		plateau.getCase(joueur.getPosition()).actionCase(joueur, plateau, fp);
	}

	public static void main(String[] args){

		System.out.println("TEST DE LA CLASSE : CasePrison \n");
		JoueurMonopoly j = new JoueurMonopoly("Yann", 0, 1000);
		PlateauMonopoly p = new PlateauMonopoly(4);

		CasePrison c = (CasePrison) p.getCase(10);

		j.setPosition(10);
		j.setEstPrison(true);
		System.out.println("Joueur en prison mais ne veut pas sortir : " + j.toString()+"\n");
		System.out.println(c.toString()+"\n");
		c.actionCase(j, p, null);

		j.setEstPrison(true);
		System.out.println("\nJoueur en prison veut sortir : " + j.toString()+"\n");
		c.setReponseQuestion(true);
		System.out.println(c.toString()+"\n");
		c.actionCase(j, p, null);

		System.out.println("\nJoueur en simple visite : " + j.toString()+"\n");
		System.out.println(c.toString()+"\n");
		c.actionCase(j, p, null);

		System.out.println("\nJoueur apr�s la prison : " + j.toString());
	}

	/* ===========================
	   M�thodes abstraites de Case
	   =========================== */

	@Override
	public JoueurMonopoly getProprietaire() {
		return null;
	}

	@Override
	public String getCouleur() {
		return null;
	}

	@Override
	public int getLoyer() {
		return 0;
	}

	@Override
	public int getPrixMaison() {
		return 0;
	}

	@Override
	public int getNbMaison() {
		return 0;
	}

	@Override
	public boolean getReponseQuestion() {
		return reponseQuestion;
	}

	@Override
	public boolean getPeutMettreMaison() {
		return false;
	}

	@Override
	public void setProprietaire(JoueurMonopoly j) {}

	@Override
	public void setReponseQuestion(boolean b) {
		this.reponseQuestion = b;
	}

	@Override
	public String toString() {
		return "CasePrison [ " + super.toString() + ", reponseQuestion=" + reponseQuestion + "]";
	}

}