package fr.chatnoir.monopoly.cases;

import fr.chatnoir.monopoly.cartes.CartePayerArgent;
import fr.chatnoir.monopoly.cartes.CarteSortirPrison;
import fr.chatnoir.monopoly.fenetres.FenetrePrincipale;
import fr.chatnoir.monopoly.io.Console;
import fr.chatnoir.monopoly.jeudeplateau.Carte;
import fr.chatnoir.monopoly.jeudeplateau.Case;
import fr.chatnoir.monopoly.jeumonopoly.JoueurMonopoly;
import fr.chatnoir.monopoly.jeumonopoly.PlateauMonopoly;

/**
 * Cr�e l'action d'une case chance
*@author HEGEL MOTOKOUA
*/
public class CaseChance extends Case {

	/**
	 * Indique le nom de la case
	 */
	public CaseChance() {
		super("Chance", 0);
	}

	@Override
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Permet de tirer et afficher une carte chance
	 * @see Carte
	 */
	public void fenetreAction(FenetrePrincipale fp) {

		Console es = new Console();

		Carte carte = fp.getPartie().getPM().tirerCarteChance();
		es.println(" > " + fp.getPartie().getPM().getJoueurActif().getNom() + " tire la carte "+carte.getNom());
		fp.afficherMessage(fp.getPartie().getPM().getJoueurActif().getNom() + " tire la carte "+carte.getNom());

		if(fp.getPartie().PARTIE_AUTO)
			fp.getPartie().reprendrePartie();
		else
			fp.afficherFenetreCarteChance(carte.getNom(), carte.getDesc());

		fp.getPartie().pausePartie();
		while(fp.getPartie().getPausePartie() && !fp.getPartie().PARTIE_AUTO){
			try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} }

		carte.actionCarte(fp.getPartie().getPM().getJoueurActif(), fp.getPartie().getPM(), fp);
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

	@Override
	public String toString() {
		return "CaseChance [" + super.toString() + "]";
	}


	public static void main(String[] args) {

		System.out.println("TEST DE LA CLASSE : CaseChance");
		JoueurMonopoly j = new JoueurMonopoly("Yann", 0, 1000);
		PlateauMonopoly p = new PlateauMonopoly(4);
		CartePayerArgent payer = new CartePayerArgent("Amende", "Amende pour exc�s de vitesse : 15�.", 15);
		System.out.println(payer.toString());
		payer.actionCarte(j, p, null);
		System.out.println(j.toString()); //Le joueur Yann perd 15�
		System.out.println(p.getCase(20).toString());

		CarteSortirPrison prison = new CarteSortirPrison("Sortie", "Vous �tes lib�r� de prison. \n(Cette carte doit �tre conserv�e)");
		System.out.println(prison.toString());
		prison.actionCarte(j, p, null);
		System.out.println(j.toString()); //Le joueur Yann poss�de la carte de sortie de prison
	}
}
