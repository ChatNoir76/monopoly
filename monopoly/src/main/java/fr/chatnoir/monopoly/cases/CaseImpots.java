package fr.chatnoir.monopoly.cases;

import fr.chatnoir.monopoly.fenetres.FenetrePrincipale;
import fr.chatnoir.monopoly.io.Console;
import fr.chatnoir.monopoly.jeudeplateau.Case;
import fr.chatnoir.monopoly.jeumonopoly.JoueurMonopoly;
import fr.chatnoir.monopoly.jeumonopoly.PlateauMonopoly;

/**
 * Cr�e l'action d'une case imp�t
*@author HEGEL MOTOKOUA
*/
public class CaseImpots extends Case {

	/**
	 * Indique le nom ainsi que le prix � payer d'une case Imp�t
	 * @param nom String
	 * @param valeur int
	 */
	public CaseImpots(String nom, int valeur) {
		super(nom, valeur);
	}

	/**
	 * M�thode retirant de l'argent � un joueur et l'ajoutant au Parc Gratuit
	 * @see CaseParcGratuit
	 * @see fr.chatnoir.monopoly.jeudeplateau.Joueur
	 * @see Case
	 */
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {

		Console es = new Console();

		es.println(" > " + joueur.getNom() + " d�pose " + this.getPrix() + "� au Parc Gratuit.");
		if(fp != null)
			fp.afficherMessage(joueur.getNom() + " d�pose " + this.getPrix() + "� au Parc Gratuit.");

		joueur.retirerArgent(this.getPrix());

		int nouveauMontantParcGratuit = plateau.getCase(20).getPrix() + this.getPrix();
		plateau.getCase(20).setPrix(nouveauMontantParcGratuit);
	}

	public static void main(String[] args){

		System.out.println("TEST DE LA CLASSE : CaseImpots");
		JoueurMonopoly j = new JoueurMonopoly("Yann", 0, 1000);
		PlateauMonopoly p = new PlateauMonopoly(4);

		CaseImpots c = (CaseImpots) p.getCase(4);
		j.setPosition(4);
		c.actionCase(j, p, null);
		System.out.println(c.toString());
		System.out.println(p.getCase(20).toString());
		System.out.println(j.toString());
	}

	@Override
	/**
	 * Reprend le cours de la partie
	 */
	public void fenetreAction(FenetrePrincipale fp) {
		fp.getPartie().reprendrePartie();
	}

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
		return false;
	}

	@Override
	public boolean getPeutMettreMaison() {
		return false;
	}

	@Override
	public void setProprietaire(JoueurMonopoly j) {}

	@Override
	public void setReponseQuestion(boolean b) {}

}
