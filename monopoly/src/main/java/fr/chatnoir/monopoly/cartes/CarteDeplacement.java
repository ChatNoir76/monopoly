package fr.chatnoir.monopoly.cartes;

import fr.chatnoir.monopoly.fenetres.FenetrePrincipale;
import fr.chatnoir.monopoly.io.Console;
import fr.chatnoir.monopoly.jeudeplateau.Carte;
import fr.chatnoir.monopoly.jeumonopoly.JoueurMonopoly;
import fr.chatnoir.monopoly.jeumonopoly.PlateauMonopoly;

/**
 * Type de {@link Carte} pour les d�placements de joueurs.<br><br>
 * &nbsp; <b>Liste des champs :</b>
 * <ul><li><b>position</b> : int - position � laquelle placer le joueur.</li>
 * <li><b>deplacementRelatif</b> : boolean - true si le d�placement est relatif � la position (ex: avancez de 3 cases) ou absolue (ex: avancez jusqu'� la Gare de Lyon).</li></ul>
 * @see Carte
 */
public class CarteDeplacement extends Carte {
	
	
	private int position;
	private boolean deplacementRelatif;
	
	/**
	 * Unique constructeur de la clase {@link CarteDeplacement}.
	 * @param titre String
	 * @param description String
	 * @param pos int
	 * @param deplacementRelatif boolean
	 */
	public CarteDeplacement(String titre, String description, int pos, boolean deplacementRelatif) {
		super(titre, description);
		this.position = pos;
		this.deplacementRelatif = deplacementRelatif;
	}

	/**
	 * M�thode r�alisant l'action de la carte. 
	 * @param joueur JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fp FenetrePrincipale
	 */
	@SuppressWarnings("static-access")
	@Override
	public void actionCarte(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {
		
		Console es = new Console();
		
		if(deplacementRelatif) //Pour les cartes "Reculez/avancez et X cases"
			plateau.deplacerJoueur(joueur, position);
		else {
			if(getNom().equals("Prison")) {
				if(joueur.getCarteSortiePrison()) {
					es.println(" > " + joueur.getNom() + " utilise sa carte et �vite la prison !");
					if(fp != null)
						fp.afficherMessage(joueur.getNom() + " utilise sa carte et �vite la prison !");
					joueur.setCarteSortiePrison(false);
					plateau.remettreCarteSortiePrisonDansPaquet();
				}
				else {
					joueur.setEstPrison(true);
					plateau.deplacerJoueur(joueur, position-joueur.getPosition());
				}
			}
			else if(position-joueur.getPosition()<0)
				plateau.deplacerJoueur(joueur, position-joueur.getPosition()+40);
			else
				plateau.deplacerJoueur(joueur, position-joueur.getPosition());
		}
		
		if(getNom().equals("Prison")) {
			es.println(" > "+joueur.getNom()+" se retrouve en prison.");
			if(fp != null)
				fp.afficherMessage(joueur.getNom()+" se retrouve en prison.");
		}
		else {
			es.println(" > "+joueur.getNom()+" atterit sur "+plateau.getCaseActive().getNom());
			if(fp != null)
				fp.afficherMessage(joueur.getNom()+" atterit sur "+plateau.getCaseActive().getNom());
			
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
		
	}
	@Override
	public String toString() {
		return "CarteDeplacement [" + super.toString() + "]";
	}

}
