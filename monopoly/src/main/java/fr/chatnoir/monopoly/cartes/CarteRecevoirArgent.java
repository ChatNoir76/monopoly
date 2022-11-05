package fr.chatnoir.monopoly.cartes;

import fr.chatnoir.monopoly.fenetres.FenetrePrincipale;
import fr.chatnoir.monopoly.io.Console;
import fr.chatnoir.monopoly.jeudeplateau.Carte;
import fr.chatnoir.monopoly.jeumonopoly.JoueurMonopoly;
import fr.chatnoir.monopoly.jeumonopoly.PlateauMonopoly;

/**
 * Type de {@link Carte} pour les r�ceptions de payements des joueurs.<br><br>
 * &nbsp; <b>Liste des champs :</b>
 * <ul><li><b>montant</b> : int - montant � ajouter au joueur.</li></ul>
 * @see Carte
 */

public class CarteRecevoirArgent extends Carte {

	private int montant;

	/**
	 * Unique constructeur de la clase {@link CarteRecevoirArgent}.
	 * @param titre String
	 * @param description String
	 * @param montant int
	 *
	 */

	public CarteRecevoirArgent(String titre, String description, int montant) {
		super(titre, description);
		this.montant = montant;
	}

	/**
	 * M�thode r�alisant l'action de la carte.
	 * @param joueur JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fp FenetrePrincipale
	 */

	@Override
	public void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {

		Console es = new Console();

		if(getNom().equals("Anniversaire")) {
			for(int i=0; i<plateau.getNbJoueurs(); i++) {
				if(plateau.getJoueur(i) != joueur && !plateau.getJoueur(i).getEstBanqueroute()) {
					plateau.getJoueur(i).retirerArgent(10);
					joueur.ajouterArgent(10);
				}
			}
			es.println(" > "+joueur.getNom()+" re�oit 10� de chaque joueur.");
			if(fp != null)
				fp.afficherMessage(joueur.getNom()+" re�oit 10� de chaque joueur.");
		}

		else {
			joueur.ajouterArgent(montant);
			es.println(" > "+joueur.getNom()+" re�oit "+montant+"� de la Banque");
		}
	}

	@Override
	public String toString()
	{
		return "CarteRecevoirArgent [" + super.toString() + " montant= " + montant + "]";
	}

}
