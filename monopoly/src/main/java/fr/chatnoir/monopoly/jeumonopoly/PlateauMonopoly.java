package fr.chatnoir.monopoly.jeumonopoly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import fr.chatnoir.monopoly.cartes.CarteDeplacement;
import fr.chatnoir.monopoly.cartes.CartePayerArgent;
import fr.chatnoir.monopoly.cartes.CarteRecevoirArgent;
import fr.chatnoir.monopoly.cartes.CarteSortirPrison;
import fr.chatnoir.monopoly.cases.CaseAllerPrison;
import fr.chatnoir.monopoly.cases.CaseChance;
import fr.chatnoir.monopoly.cases.CaseCommunaute;
import fr.chatnoir.monopoly.cases.CaseDepart;
import fr.chatnoir.monopoly.cases.CaseGare;
import fr.chatnoir.monopoly.cases.CaseImpots;
import fr.chatnoir.monopoly.cases.CaseParcGratuit;
import fr.chatnoir.monopoly.cases.CasePrison;
import fr.chatnoir.monopoly.cases.CaseServicePublic;
import fr.chatnoir.monopoly.cases.CaseTerrain;
import fr.chatnoir.monopoly.jeudeplateau.Carte;
import fr.chatnoir.monopoly.jeudeplateau.Case;
import fr.chatnoir.monopoly.jeudeplateau.Joueur;

/**
 * Initialise le plateau du monopoly avec toutes ses cases
*@author HEGEL MOTOKOUA
*/
public class PlateauMonopoly extends fr.chatnoir.monopoly.jeudeplateau.Plateau {

	/**
	 * @see JoueurMonopoly
	 */
	private ArrayList<JoueurMonopoly> joueurs = new ArrayList<JoueurMonopoly>();
	/**
	 * @see Carte
	 */
	private ArrayList<Carte> chance = new ArrayList<Carte>();
	private ArrayList<Carte> communaute = new ArrayList<Carte>();

	/**
	 * Cr�e un plateau avec un nombre de joueur
	 * @param nombreDeJoueurs int
	 * @see fr.chatnoir.monopoly.jeudeplateau.Plateau
	 * @see CaseDepart
	 * @see CaseCommunaute
	 * @see CaseImpots
	 * @see CaseGare
	 * @see CaseChance
	 * @see CasePrison
	 * @see CaseServicePublic
	 * @see CaseParcGratuit
	 * @see CaseAllerPrison
	 * @see CaseTerrain
	 * @see CarteDeplacement
	 * @see CartePayerArgent
	 * @see CarteRecevoirArgent
	 * @see CarteSortirPrison
	 */
	public PlateauMonopoly(int nombreDeJoueurs) {
		super(nombreDeJoueurs, 40);


		/* INITIALISATION DES JOUEURS */
		for(int i = 0; i < this.getNbJoueurs(); i++) {
			this.joueurs.add(new JoueurMonopoly("Joueur"+(i+1), i, 1000));
		}

		/* INITIALISATION DES CASES*/
		setCase(0, new CaseDepart());
		setCase(2, new CaseCommunaute());
		setCase(4, new CaseImpots("Impots sur le revenu", 200));
		setCase(5, new CaseGare("Gare Montparnasse"));
		setCase(7, new CaseChance());
		setCase(10, new CasePrison());
		setCase(12, new CaseServicePublic("Compagnie d'�lectricit�"));
		setCase(15, new CaseGare("Gare de Lyon"));
		setCase(17, new CaseCommunaute());
		setCase(20, new CaseParcGratuit());
		setCase(22, new CaseChance());
		setCase(25, new CaseGare("Gare du Nord"));
		setCase(28, new CaseServicePublic("Compagnie des eaux"));
		setCase(30, new CaseAllerPrison());
		setCase(33, new CaseCommunaute());
		setCase(35, new CaseGare("Gare Saint-Lazare"));
		setCase(36, new CaseChance());
		setCase(38, new CaseImpots("Taxe de Luxe", 100));

		/* INITIALISATION DES TERRAINS */
		setCase(1, new CaseTerrain("Boulevard de Belleville", 60, new ArrayList<Integer>(Arrays.asList(2, 10, 30, 90, 160, 250)), 50, 0, "brun"));
		setCase(3, new CaseTerrain("Rue Lecourbe", 60, new ArrayList<Integer>(Arrays.asList(4, 20, 60, 180, 320, 450)), 50, 0, "brun"));

		setCase(6, new CaseTerrain("Rue de Vaugirard", 100, new ArrayList<Integer>(Arrays.asList(6, 30, 90, 270, 400, 550)), 50, 0, "turquoise"));
		setCase(8, new CaseTerrain("Rue de Courcelles", 100, new ArrayList<Integer>(Arrays.asList(6, 30, 90, 270, 400, 550)), 50, 0, "turquoise"));
		setCase(9, new CaseTerrain("Avenue de la R�publique", 120, new ArrayList<Integer>(Arrays.asList(8, 40, 100, 300, 450, 600)), 50, 0, "turquoise"));

		setCase(11, new CaseTerrain("Boulevard la Villette", 140, new ArrayList<Integer>(Arrays.asList(10, 50, 150, 450, 625, 750)), 100, 0, "mauve"));
		setCase(13, new CaseTerrain("Avenue de Neuilly", 140, new ArrayList<Integer>(Arrays.asList(10, 50, 150, 450, 625, 750)), 100, 0, "mauve"));
		setCase(14, new CaseTerrain("Rue du Paradis", 160, new ArrayList<Integer>(Arrays.asList(12, 60, 180, 500, 700, 900)), 100, 0, "mauve"));

		setCase(16, new CaseTerrain("Avenue Mozart", 180, new ArrayList<Integer>(Arrays.asList(14, 70, 200, 550, 750, 950)), 100, 0, "orange"));
		setCase(18, new CaseTerrain("Boulevard Saint-Victorien", 180, new ArrayList<Integer>(Arrays.asList(14, 70, 200, 550, 750, 950)), 100, 0, "orange"));
		setCase(19, new CaseTerrain("Place Pigalle", 200, new ArrayList<Integer>(Arrays.asList(16, 80, 220, 600, 800, 1000)), 100, 0, "orange"));

		setCase(21, new CaseTerrain("Avenue Matignon", 220, new ArrayList<Integer>(Arrays.asList(18, 90, 250, 700, 875, 1050)), 150, 0, "rouge"));
		setCase(23, new CaseTerrain("Boulevard Malesherbes", 220, new ArrayList<Integer>(Arrays.asList(18, 90, 250, 700, 875, 1050)), 150, 0, "rouge"));
		setCase(24, new CaseTerrain("Avenue Henri-Martin", 240, new ArrayList<Integer>(Arrays.asList(20, 100, 300, 750, 925, 1100)), 150, 0, "rouge"));

		setCase(26, new CaseTerrain("Faubourg Saint-Honor�", 260, new ArrayList<Integer>(Arrays.asList(22, 110, 330, 800, 975, 1150)), 150, 0, "jaune"));
		setCase(27, new CaseTerrain("Place de la Bourse", 260, new ArrayList<Integer>(Arrays.asList(22, 110, 330, 800, 975, 1150)), 150, 0, "jaune"));
		setCase(29, new CaseTerrain("Rue La Fayette", 280, new ArrayList<Integer>(Arrays.asList(24, 120, 360, 850, 1025, 1200)), 150, 0, "jaune"));

		setCase(31, new CaseTerrain("Avenue de Breuteuil", 300, new ArrayList<Integer>(Arrays.asList(26, 130, 390, 900, 1100, 1275)), 200, 0, "vert"));
		setCase(32, new CaseTerrain("Avenue Foch", 300, new ArrayList<Integer>(Arrays.asList(26, 130, 390, 900, 1100, 1275)), 200, 0, "vert"));
		setCase(34, new CaseTerrain("Boulevard des Capucines", 320, new ArrayList<Integer>(Arrays.asList(28, 150, 450, 1000, 1200, 1400)), 200, 0, "vert"));

		setCase(37, new CaseTerrain("Avenue des Champs-�lys�es", 350, new ArrayList<Integer>(Arrays.asList(35, 175, 500, 1100, 1300, 1500)), 200, 0, "bleu"));
		setCase(39, new CaseTerrain("Rue de la Paix", 400, new ArrayList<Integer>(Arrays.asList(50, 200, 600, 1400, 1700, 2000)), 200, 0, "bleu"));


		/* INITIALISATION DES CARTES CHANCES */
		/*chance.add(new CartePayerArgent("Amende", "Amende pour exc�s de vitesse : 15�.", 15));
		chance.add(new CartePayerArgent("Pr�sident du conseil d'administration", "Vous avez �t� �lu pr�sident du conseil d'administration. \nVersez 50� � chaque joueur.", 50));
		chance.add(new CartePayerArgent("Lanuel", "Vous avez manqu� de respect � M. Lanuel. \nVersez 50� de d�dommagement.", 50));

		chance.add(new CarteRecevoirArgent("Versement", "La banque vous verse un dividende de 50�.", 50));
		chance.add(new CarteRecevoirArgent("Gain", "Vos terrains vous rapportent. Touchez 150�.", 150));
		chance.add(new CarteRecevoirArgent("Mots crois�s", "Vous avez gagn� le prix de mots-crois�s ! \nRecevez 100�.", 100));*/

		chance.add(new CarteDeplacement("Case D�part", "Avancez jusqu'� la case d�part. \n(Recevez 200�)", 0, false));
		chance.add(new CarteDeplacement("Rue de la Paix", "Rendez-vous Rue de la Paix.", 39, false));
		chance.add(new CarteDeplacement("Henri-Martin", "Rendez-vous � l'Avenue Henri-Martin. \nSi vous passez par la case d�part, recevez 200�.", 24, false));
		chance.add(new CarteDeplacement("Boulevard de la Villette", "Avancez au Boulevard de la Vilette. \nSi vous passez par la case d�part, recevez 200�.", 11, false));
		chance.add(new CarteDeplacement("Gare de Lyon", "Avancez � la gare de Lyon. \nSi vous passez par la case d�part, recevez 200�.", 15, false));
		chance.add(new CarteDeplacement("Reculez", "Reculez de 3 cases.", -3, true));
		chance.add(new CarteDeplacement("Nv Depart", "Le joueur d�m�nage et prend un \nnouveau d�part au Technopole.", 0, false));

		/*chance.add(new CarteDeplacement("Prison", "Allez en prison. \nAvancez tout droit en prison. \nNe passez pas par la case d�part, ne recevez pas 200�.", 10, false));
		chance.add(new CarteSortirPrison("Sortie", "Vous �tes lib�r� de prison. \n(Cette carte doit �tre conserv�e)"));
		*/
		Collections.shuffle(chance); //M�lange des cartes


		/* INITIALISATION DES CARTES COMMUNAUT�S */
		communaute.add(new CartePayerArgent("Frais de scolarit�", "Payez 150� de frais de scolarit�.", 150));
		communaute.add(new CartePayerArgent("Frais d'hospitalisation", "Payez 100� de frais d'hospitalisation.", 100));
		communaute.add(new CartePayerArgent("M�decin", "Visite chez le m�decin : payez 50�.", 50));

		communaute.add(new CarteRecevoirArgent("Remboursement", "Les imp�ts vous remboursent 20�.", 20));
		communaute.add(new CarteRecevoirArgent("Assurance vie", "Votre assurance vie vous rapporte 100�.", 100));
		communaute.add(new CarteRecevoirArgent("Anniversaire", "C'est votre anniversaire ! \nChaque joueur doit vous donner 10�.", 10));
		communaute.add(new CarteRecevoirArgent("Commission d'expert", "Commission d'expert immobilier. \nRecevez 25�.", 25));
		communaute.add(new CarteRecevoirArgent("Prix de beaut�", "Vous avez gagn� le deuxi�me prix de beaut�. \nRecevez 10�.", 10));
		communaute.add(new CarteRecevoirArgent("Stock", "La vente de votre stock vous rapporte 50�.", 50));
		communaute.add(new CarteRecevoirArgent("H�ritage", "Vous h�ritez de 100�.", 100));
		communaute.add(new CarteRecevoirArgent("Placement", "Votre placement vous rapporte. \nRecevez 100�.", 100));
		communaute.add(new CarteRecevoirArgent("Erreur de la Banque", "Erreur de la Banque en votre faveur. \nRecevez 200�.", 200));

		communaute.add(new CarteDeplacement("Prison", "Allez en prison. \nAvancez tout droit en prison. \nNe passez pas par la case d�part, ne recevez pas 200�.", 10, false));
		communaute.add(new CarteSortirPrison("Sortie", "Vous �tes lib�r� de prison. \n(Cette carte doit �tre conserv�e)"));

		Collections.shuffle(communaute); //M�lange des cartes
	}

	/* PARTIE JOUEUR */

	/**
	 * Permet de d�placer un joueur d'un certain nombre de cases
	 * @param joueur JoueurMonopoly
	 * @param nombreDeCases int
	 * @see Joueur
	 * @see JoueurMonopoly
	 * @see fr.chatnoir.monopoly.jeudeplateau.Plateau
	 */
	public void deplacerJoueur(JoueurMonopoly joueur, int nombreDeCases) {
		int pos;

		if((joueur.getPosition() + nombreDeCases) >= getNbCases()) {
			pos = (joueur.getPosition() + nombreDeCases) % getNbCases();
			System.out.println(" > " + joueur.getNom() + " passe par la case d�part et gagne 200� !");
			joueur.ajouterArgent(200);
		}
		else
			pos = joueur.getPosition() + nombreDeCases;

		if(!joueur.getEstBanqueroute()) {
			joueur.setPosition(pos);
		}
	}

	/**
	 * Renvoie au joueur qui doit jouer son tour
	 * @return joueur
	 */
	public JoueurMonopoly getJoueurActif() {
		return this.joueurs.get(getJoueurActifID());
	}

	/**
	 * Permet de changer de joueur en fonction de l'indice i
	 * @param i int
	 * @return joueur
	 */
	public JoueurMonopoly getJoueur(int i) {
		return this.joueurs.get(i);
	}

	/**
	 * Renvoie le joueur vainqueur
	 * @return joueur
	 */
	@Override
	public Joueur estVainqueur() {
		int res = 0;
		for(int i=0; i<joueurs.size(); i++) {
			if(getJoueur(i).getArgent() > getJoueur(res).getArgent())
				res = i;
		}
		return getJoueur(res);
	}

	/* PARTIE CASE */

	/**
	 * Renvoie la case o� est le joueur actif
	 * @return Case
	 */
	public Case getCaseActive() {
		return getCase(getJoueurActif().getPosition());
	}

	/* PARTIE JEU */

	/**
	 * Met fin � la partie
	 * return boolean
	 */
	@Override
	public boolean finPartie() {
		int nbJoueursEnJeu = 0;
		for(JoueurMonopoly j:joueurs) {
			if(!j.getEstBanqueroute()) nbJoueursEnJeu++;
		}
		return (nbJoueursEnJeu <= 1);
	}

	/*PARTIE CARTE */

	/**
	 * Renvoie la liste des cartes chances
	 * @return c
	 * @see Carte
	 */
	public Carte tirerCarteChance() {
		Carte c = chance.remove(0);
		if(!c.getNom().equals("Sortie"))
			chance.add(c);
		return c;
	}

	/**
	 * Renvoie la liste des cartes communaut�s
	 * @return c
	 * @see Carte
	 */
	public Carte tirerCarteCommunaute() {
		Carte c = communaute.remove(0);
		if(!c.getNom().equals("Sortie"))
			communaute.add(c);
		return c;
	}

	/**
	 * Permet l'ajout de la carte Sortie Prison lorsqu'un joueur qui la poss�de l'utilise
	 * @see Carte
	 */
	public void remettreCarteSortiePrisonDansPaquet() {

		boolean carteDansPaquetChance = false;
		for(Carte c:chance) {
			if(c.getNom().equals("Sortie"))
				carteDansPaquetChance = true;  // TR�S TR�S MOCHE, OUI
		}

		if(carteDansPaquetChance)
			chance.add(new CarteSortirPrison("Sortie", "Vous �tes lib�r� de prison. \n(Cette carte doit �tre conserv�e)"));
		else
			communaute.add(new CarteSortirPrison("Sortie", "Vous �tes lib�r� de prison. \n(Cette carte doit �tre conserv�e)"));
	}


	/* TOSTRING */

	@Override
	/**
	 * fonction obligatoire
	 */
	public String toString() {
		return "PlateauMonopoly [toString()=" + super.getCase(1) + "]";
	}

}
