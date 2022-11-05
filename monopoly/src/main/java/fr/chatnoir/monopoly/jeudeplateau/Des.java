package fr.chatnoir.monopoly.jeudeplateau;

import java.util.Random;

/**
 * Cr�e les des utilis�s pour le jeu
*@author HEGEL MOTOKOUA
*/

public class Des {

	private int de1;
	private int de2;
	private Random rand = new Random();

	/**
	 * Renvoie le nombre obtenu par le lanc� de d�
	 * @return nombreLanc�
	 */
	public int getDes() {
		return (this.de1 + this.de2);
	}

	/**
	 * Renvoie le chiffre obtenu par le premier d�
	 * @return de1
	 */
	public int getDe1(){
		return this.de1;
	}

	/**
	 * Renvoie le chiffre obtenu par le deuxi�me d�
	 * @return de2
	 */
	public int getDe2(){
		return this.de2;
	}

	/**
	 * Attribue un nombre al�atoire aux 2 des et les additionne
	 * @return nombredes
	 */
	public int lancerDes() {
		this.de1 = 1+this.rand.nextInt(6);
		this.de2 = 1+this.rand.nextInt(6);

		return getDes();
	}

}