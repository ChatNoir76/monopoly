package fr.chatnoir.monopoly.cases;

import fr.chatnoir.monopoly.fenetres.FenetrePrincipale;
import fr.chatnoir.monopoly.io.Console;
import fr.chatnoir.monopoly.jeudeplateau.Case;
import fr.chatnoir.monopoly.jeumonopoly.JoueurMonopoly;
import fr.chatnoir.monopoly.jeumonopoly.PlateauMonopoly;

/**
 * Cr�e l'action pour aller en prison
*@author HEGEL MOTOKOUA
*@see Case
*/
public class CaseAllerPrison extends Case {

	private boolean reponseQuestion = false;

	/**
	 * Indique le nom de la case
	 */
	public CaseAllerPrison() {
		super("Aller en prison", 0);
	}

	/**
	 * M�thode permettant de g�rer l'action quand le joueur tombe sur la case AllerPrison
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {

		Console es = new Console();

		if(joueur.getCarteSortiePrison()) {
			es.println(" > " + joueur.getNom() + " utilise sa carte et �vite la prison !");
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " utilise sa carte et �vite la prison !");
			joueur.setCarteSortiePrison(false);
			plateau.remettreCarteSortiePrisonDansPaquet();
		}
		else {
			joueur.setEstPrison(true);
			joueur.setPosition(10);
			es.println(" > " + joueur.getNom() + " est envoy� en prison!");
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " est envoy� en prison!");
		}
	}

	@Override
	/**
	 * M�thode permettant de reprendre la partie
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		fp.getPartie().reprendrePartie();
	}

	public static void main(String[] args){

		System.out.println("TEST DE LA CLASSE : CaseAllerPrison \n");
		JoueurMonopoly j = new JoueurMonopoly("Yann", 0, 1000);
		PlateauMonopoly p = new PlateauMonopoly(4);

		CaseAllerPrison c = (CaseAllerPrison) p.getCase(30);
		j.setPosition(30);
		System.out.println("\nLe joueur est sur la case "+ c.toString()+"\n");
		c.actionCase(j, p, null);
		System.out.println("\nMaintenant, le joueur est sur la case "+ p.getCase(j.getPosition()).toString()+" et est donc en prison\n");
		System.out.println(j.toString());
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
		return "CaseAllerPrison [" + super.toString() + "]";
	}

}
