package fr.chatnoir.monopoly.jeumonopoly;

import java.util.ArrayList;

import fr.chatnoir.monopoly.fenetres.FenetrePrincipale;
import fr.chatnoir.monopoly.io.Console;
import fr.chatnoir.monopoly.jeudeplateau.Case;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * Lance la partie
*@author HEGEL MOTOKOUA
*/

public class Partie {

	/**
	 * @see PlateauMonopoly
	 */
	private PlateauMonopoly pm;

	/**
	 * @see FenetrePrincipale
	 */
	private FenetrePrincipale fp;

	private boolean pausePartie = false;
	public final static long VITESSE_PARTIE = 1000;
	public final static boolean PARTIE_AUTO = false;

	/* CONSTRUCTEUR PARTIE */

	/**
	 * Cr�e une partie en fonction du nombre de joueurs
	 * @param nombreDeJoueurs int
	 * @param fp FenetrePrincipale
	 */
	public Partie(int nombreDeJoueurs, ArrayList<String> nomsDesJoueurs, FenetrePrincipale fp) {
		this.pm = new PlateauMonopoly(nombreDeJoueurs);
		this.fp = fp;

		for(int i=0; i<nombreDeJoueurs; i++) {
			pm.getJoueur(i).setNom(nomsDesJoueurs.get(i));
		}
	}

	/**
	 * D�marre la partie
	 * @see JoueurMonopoly
	 * @see Case
	 */
	public void demarrerLaPartie() {

		final Service<Void> partieService = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {
                    	Console es = new Console();
                    	es.println("La partie d�marre!");

                		JoueurMonopoly joueur;
                		int lance;
                		Case caze;

                		while(!pm.finPartie() && pm.getNbTours() <= 100) {

                			joueur = pm.getJoueurActif();

                			if(pm.getJoueurActifID() == 0)
                				es.println("=== DEBUT DU TOUR " + pm.getNbTours() + " ===");

                			es.println("C'est au tour de " + joueur.getNom() + " (poss�de " + joueur.getArgent() + "�)");
                			fp.afficherMessage("C'est au tour de " + joueur.getNom() + " (poss�de " + joueur.getArgent() + "�)");

                			if(!joueur.getEstBanqueroute()) {
                				Thread.sleep(VITESSE_PARTIE);

                				lance = pm.des.lancerDes();

                				if(!joueur.getEstPrison()) {

                    				fp.afficherDes(pm);
                					es.println("" + joueur.getNom() + " lance les d�s... [" + pm.des.getDe1() + "][" + pm.des.getDe2() + "]... et obtient un " + lance + " !");
                					pm.deplacerJoueur(joueur, lance);
                					fp.deplacerPion(joueur);

                					caze = pm.getCase(joueur.getPosition());
                					es.println("" + joueur.getNom() + " avance de " + lance + " cases et atterit sur " + caze.getNom());
                				}
                				else {
                					es.println("Le joueur est en prison.");

                					caze = pm.getCase(joueur.getPosition());
                				}
            					Thread.sleep(VITESSE_PARTIE);

                				pausePartie = true;
            					caze.fenetreAction(fp);

                    			while(pausePartie && !PARTIE_AUTO){ Thread.sleep(200); }

                				caze.actionCase(joueur, pm, fp);

                				es.println("" + joueur.getNom() + " poss�de � la fin de son tour " + joueur.getArgent() + "�");
                				System.out.println("et les terrains suivants :\n" + joueur.getListeStringTerrains());
                			}
                			else {
                				es.println("" + pm.getJoueurActif().getNom() + " est en banqueroute, il ne joue pas.");
                			}

                			Thread.sleep(400);
                			fp.deplacerPion(joueur);
                			fp.refreshLabels(pm);

                			pausePartie = !joueur.getEstBanqueroute();
                			while(pausePartie && !PARTIE_AUTO){ Thread.sleep(200); }

                			es.println("");
                			fp.effacerDes();
                			pm.setJoueurSuivant();

                		}

                		es.println("=== Fin de la partie ===");
                		es.println(" Le vainqueur est " + pm.estVainqueur().getNom() + " !");

                		fp.afficherVainqueur(pm);

                		return null;
                    }
                };
            }
        };
        partieService.start();
	}

	/**
	 * Renvoie le plateau du Monopoly
	 * @return pm
	 */
	public PlateauMonopoly getPM() {
		return this.pm;
	}

	/**
	 * Permet de reprendre le cours de la partie
	 */
	public void reprendrePartie() {
		this.pausePartie = false;
	}
	public void pausePartie(){
		this.pausePartie = true;
	}
	public boolean getPausePartie(){
		return this.pausePartie;
	}

	/**
	 * Il est l� car il le doit c'est tout
	 */
	@Override
	public String toString() {
		return "Partie [plateauM=" + pm.toString() + "]";
	}

}
