package fr.chatnoir.monopoly.jeudeplateau;

import java.util.ArrayList;

/**
 * D�finit le plateau du jeu
*@author HEGEL MOTOKOUA
*/

public abstract class Plateau {

	private int joueurActifID = 0;
	private int nombreDeJoueurs = 2;
	private int nombreDeCases = 20;
	private int nombreDeTours = 1;

	/**
	 * @see Case
	 */
	private ArrayList<Case> cases = new ArrayList<Case>();
	/**
	 * @see Des
	 */
	public Des des = new Des();

	/**
	 * Cr�e un plateau en fonction du nombre de joueur et nombre de cases
	 * @param nombreDeJoueurs int
	 * @param nbCases int
	 */
	public Plateau(int nombreDeJoueurs, int nbCases) {
		this.nombreDeJoueurs = nombreDeJoueurs;
		this.nombreDeCases = nbCases;
		for(int i=0; i<nombreDeCases; i++) {
			cases.add(null);
		}
	}

	/* PARTIE CASE */

	/**
	 * Renvoie une case en fonction d'un index i
	 * @param i int
	 * @return case
	 */
	public Case getCase(int i) {
		return this.cases.get(i);
	}

	/**
	 * D�finit une case en fonction
	 * @param i int
	 * @param caze Case
	 */
	public void setCase(int i, Case caze) {
		this.cases.set(i, caze);
		cases.get(i).setId(i);
	}

	/**
	 * Renvoie le nombre de cases
	 * @return nombreDeCases
	 */
	public int getNbCases() {
		return this.nombreDeCases;
	}

	/* PARTIE JOUEUR */

	/**
	 * renvoie le nombre de joueurs
	 * @return nombreDeJoueurs
	 */
	public int getNbJoueurs() {
		return this.nombreDeJoueurs;
	}

	/**
	 * Renvoie le joueur actif
	 * @return joueurActif
	 */
	public int getJoueurActifID() {
		return this.joueurActifID;
	}

	/**
	 * S�lectionne le joueur suivant
	 */
	public void setJoueurSuivant() {
		this.joueurActifID++;
		if(this.joueurActifID >= this.nombreDeJoueurs) {
			this.joueurActifID = 0;
			this.nombreDeTours++;
		}
	}

	/* PARTIE PLATEAU */

	/**
	 * Renvoie le nombre de tours
	 * @return nombreDeTours
	 */
	public int getNbTours() {
		return this.nombreDeTours;
	}

	/* PARTIE ABSTRAITE */

	/**
	 * M�thode abstraite renvoyant vrai si la partie est finie
	 * @return boolean
	 */
	public abstract boolean finPartie();
	/**
	 * M�thode abstraite renvoyant le gagnant du jeu
	 * @return joueur
	 */
	public abstract Joueur estVainqueur();

}
