package fr.chatnoir.monopoly.jeudeplateau;

import fr.chatnoir.monopoly.fenetres.FenetrePrincipale;
import fr.chatnoir.monopoly.jeumonopoly.JoueurMonopoly;
import fr.chatnoir.monopoly.jeumonopoly.PlateauMonopoly;

/**
 * D�finit les cartes du Monopoly
*@author HEGEL MOTOKOUA
*/

public abstract class Carte {

	private String titre;
	private String description;

	/**
	 * D�finit une carte avec un titre et une description
	 * @param titre String
	 * @param description String
	 */
	public Carte(String titre, String description) {
		this.titre = titre;
		this.description = description;
	}

	/**
	 * Renvoie le titre de la carte
	 * @return titre
	 */
	public String getNom() {
		return this.titre;
	}

	/**
	 * Renvoie la description de la carte
	 * @return description
	 */
	public String getDesc() {
		return this.description;
	}

	/**
	 * Action de la carte en fonction du joueur, sur le plateau
	 * @param joueur JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fp FenetrePrincipale
	 * @see fr.chatnoir.monopoly.cartes.CarteDeplacement
	 * @see fr.chatnoir.monopoly.cartes.CartePayerArgent
	 * @see fr.chatnoir.monopoly.cartes.CarteRecevoirArgent
	 * @see fr.chatnoir.monopoly.cartes.CarteSortirPrison
	 */
	public abstract void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp);

	@Override
	public String toString() {
		return "Carte [titre=" + titre + ", description=" + description + "]";
	}
}
