package fr.chatnoir.monopoly.cases;

import fr.chatnoir.monopoly.fenetres.FenetrePrincipale;
import fr.chatnoir.monopoly.io.Console;
import fr.chatnoir.monopoly.jeudeplateau.Carte;
import fr.chatnoir.monopoly.jeudeplateau.Case;
import fr.chatnoir.monopoly.jeumonopoly.JoueurMonopoly;
import fr.chatnoir.monopoly.jeumonopoly.PlateauMonopoly;

/**
 * Cr�e l'action d'une case communaut�
*@author HEGEL MOTOKOUA
*/

public class CaseCommunaute extends Case {

	/**
	 * Indique le nom de la case
	 */
	public CaseCommunaute() {
		super("Communaut�", 0);
	}

	@Override
	public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Permet de tirer et afficher une carte communaut�
	 * @see Carte
	 */
	public void fenetreAction(FenetrePrincipale fp) {

Console es = new Console();

		Carte carte = fp.getPartie().getPM().tirerCarteCommunaute();
		es.println(" > " + fp.getPartie().getPM().getJoueurActif().getNom() + " tire la carte "+carte.getNom());
		fp.afficherMessage(fp.getPartie().getPM().getJoueurActif().getNom() + " tire la carte "+carte.getNom());

		carte.actionCarte(fp.getPartie().getPM().getJoueurActif(), fp.getPartie().getPM(), fp);

		if(fp.getPartie().PARTIE_AUTO)
			fp.getPartie().reprendrePartie();
		else
			fp.afficherFenetreCarteCommunaute(carte.getNom(), carte.getDesc());
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
		return "CaseCommunaute [" + super.toString() + "]";
	}

}
