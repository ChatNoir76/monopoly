package fr.chatnoir.monopoly.jeumonopoly;

import java.util.ArrayList;

import fr.chatnoir.monopoly.jeudeplateau.Case;
import fr.chatnoir.monopoly.jeudeplateau.Joueur;
import javafx.application.Platform;
import javafx.scene.paint.Color;

/**
 * D�finit un joueur et toutes ses donn�es dans le jeu du Monopoly
*@author HEGEL MOTOKOUA
*/

public class JoueurMonopoly extends Joueur {

	private int argent = 1000;
	private boolean estBanqueroute = false;
	private boolean estPrison = false;
	private int toursEnPrison = 1;
	private boolean possedeCarteSortiePrison = false;
	private int nombreGaresPossedees = 0;
	private int nombreServicesPossedes = 0;
	private ArrayList<Case> terrains = new ArrayList<Case>();
	private ArrayList<String> couleurs = new ArrayList<String>();

	/* CONSTRUCTEUR */

	public JoueurMonopoly(String nom, int id, int argent) {
		super(nom, id);
		this.argent = argent;
	}


	/* PARTIE PRISON  */

	/**
	 * Renvoie le nombre de tours en prison
	 * @return toursEnPrison
	 */
	public int getToursEnPrison() {
		return toursEnPrison;
	}

	/**
	 * Met � jour le nombre de tour en prison
	 * @param toursEnPrison
	 * Le nouveau nombre de tour en prison
	 */
	public void setToursEnPrison(int toursEnPrison) {
		this.toursEnPrison = toursEnPrison;
	}

	/**
	 * Renvoie si le joueur est en prison ou non
	 * @return estPrison
	 */
	public boolean getEstPrison(){
		return this.estPrison;
	}

	/**
	 * Met � jour si le joueur est en prison ou non
	 * @param prison boolean
	 */
	public void setEstPrison(boolean prison){
		this.estPrison = prison;
	}

	/**
	 * Renvoie si le joueur poss�de la carte Sortie de Prison ou non
	 * @return possedeCarteSortiePrison
	 */
	public boolean getCarteSortiePrison() {
		return possedeCarteSortiePrison;
	}

	/**
	 * Met � jour si le joueur poss�de une carte de sortie de prison
	 * @param b boolean
	 */
	public void setCarteSortiePrison(boolean b) {
		possedeCarteSortiePrison = b;
	}

	/*  PARTIE TERRAINS  */

		// GARES ET SERVICES

	/**
	 * Renvoie le nombre de gares qu'un joueur poss�de
	 * @return nombreGaresPossedees
	 */
	public int getNbGares() {
		return this.nombreGaresPossedees;
	}

	/**
	 * Met � jour le nombre de gares qu'un joueur poss�de
	 * @param nb int
	 */
	public void setNbGares(int nb) {
		this.nombreGaresPossedees = nb;
	}

	/**
	 * Renvoie le nombre de services qu'un joueur poss�de
	 * @return nombreServicesPossedes
	 */
	public int getNbServices() {
		return this.nombreServicesPossedes;
	}

	/**
	 * Met � jour le nombre de services qu'un joueur poss�de
	 * @param nb int
	 */
	public void setNbServices(int nb) {
		this.nombreServicesPossedes = nb;
	}

		// TERRAINS

	/**
	 * Ajoute "terrain" � la liste des terrains
	 * @param terrain Case
	 */
	public void ajouterTerrain(Case terrain) {
		this.terrains.add(terrain);
	}

	/**
	 * Renvoie la liste "�crite" des terrains qu'un joueur poss�de
	 * @return s
	 * @see Case
	 */
	public String getListeStringTerrains() {
		String s = "";
		for(Case t:this.terrains) {
			s+=(t.getNom()+",");
		}
		return s;
	}

	/**
	 * Renvoie une liste de terrain que poss�de un joueur
	 * @return terrains
	 */
	public ArrayList<Case> getListeTerrains(){
		return this.terrains;
	}

	// COULEURS				(A AMELIORER JE PENSE)

	/**
	 * Renvoie une liste correspondant aux couleurs que poss�de un joueur
	 * @return couleurs
	 * @see Case
	 */
	public ArrayList<String> getListeCouleur(){

		couleurs.clear();

		int 	brun = 0,
				turquoise = 0,
				mauve = 0,
				orange = 0,
				rouge = 0,
				jaune = 0,
				vert = 0,
				bleu = 0;

		for(Case t:this.getListeTerrains()) {

			if(t.getCouleur() == "brun")
				brun += 1;
			if(t.getCouleur() == "turquoise")
				turquoise += 1;
			if(t.getCouleur() == "mauve")
				mauve += 1;
			if(t.getCouleur() == "orange")
				orange += 1;
			if(t.getCouleur() == "rouge")
				rouge += 1;
			if(t.getCouleur() == "jaune")
				jaune += 1;
			if(t.getCouleur() == "vert")
				vert += 1;
			if(t.getCouleur() == "bleu")
				bleu += 1;
		}

		if(brun == 2) 		couleurs.add("brun");

		if(turquoise == 3) 	couleurs.add("turquoise");

		if(mauve == 3) 		couleurs.add("mauve");

		if(orange == 3) 	couleurs.add("orange");

		if(rouge == 3) 		couleurs.add("rouge");

		if(jaune == 3) 		couleurs.add("jaune");

		if(vert == 3) 		couleurs.add("vert");

		if(bleu == 2) 		couleurs.add("bleu");

		return this.couleurs;
	}

	/* PARTIE ARGENT */

	/**
	 * Renvoie l'argent d'un joueur
	 * @return argent
	 */
	public int getArgent() {
		return this.argent;
	}

	/**
	 * Met � jour l'argent d'un joueur en lui ajoutant un montant
	 * @param montant int
	 */
	public void ajouterArgent(int montant) {
		this.argent+=montant;
	}

	/**
	 * Met � jour l'argent d'un joueur en lui retirant un montant
	 * @param montant int
	 */
	public void retirerArgent(int montant) {
		this.argent = this.argent - montant;
		if(this.argent <= 0) {
			this.argent = 0;
			this.setEstBanqueroute(true);
		}
	}

	/**
	 * Renvoie si un joueur est en banqueroute ou non
	 * @return estBanqueroute
	 */
	public boolean getEstBanqueroute() {
		return this.estBanqueroute;
	}

	/**
	 * Met � jour si le joueur est banqueroute ou pas
	 * @param banqueroute boolean
	 */
	public void setEstBanqueroute(boolean banqueroute) {
		this.estBanqueroute = banqueroute;
		clearMarqueurs();
		this.terrains.clear();
	}

	/**
	 * Supprime le marqueur de possession d'un terrain
	 * @see Case
	 */
	public void clearMarqueurs() {

		for(Case t:getListeTerrains()){
			t.setProprietaire(null);

			Platform.runLater(new Runnable() {
	            @Override public void run() {

	            	t.getMarqueur().setFill(Color.TRANSPARENT);
	            }
			});
		}
	}

	@Override
	public String toString() {
		return "JoueurMonopoly [" + super.toString() + ", argent=" + argent + ", estBanqueroute=" + estBanqueroute + ", estPrison=" + estPrison
				+ ", toursEnPrison=" + toursEnPrison + ", possedeCarteSortiePrison=" + possedeCarteSortiePrison
				+ ", nombreGaresPossedees=" + nombreGaresPossedees + ", nombreServicesPossedes="
				+ nombreServicesPossedes + ", \nterrains=[" + getListeStringTerrains() + "], \ncouleurs=" + getListeCouleur() + "]";
	}
}
