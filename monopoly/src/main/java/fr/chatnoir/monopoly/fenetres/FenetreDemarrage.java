package fr.chatnoir.monopoly.fenetres;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Fen�tre � afficher au d�marrage d'une nouvelle {@link fr.chatnoir.monopoly.jeumonopoly.Partie}, permettant de s�lectionner le nombre de joueur.
 * @see FenetrePrincipale
 */
public class FenetreDemarrage {

	private FenetrePrincipale fp;
	private Stage stage;
	private VBox root;
	private Label l_NbJoueurs;private Label l_NbJoueurs2;
	private ArrayList<TextField> listeJoueurs = new ArrayList<TextField>();
	private Button b_Valider;
	private int choix = 0;

	/**
	 * Unique constructeur de la classe {@link FenetreDemarrage}, prenant en param�tre la {@link FenetrePrincipale} fp.
	 * @param fp FenetrePrincipale
	 * @see FenetrePrincipale
	 */
	public FenetreDemarrage(FenetrePrincipale fp) {

		this.fp = fp;

		this.stage = new Stage();

		this.stage.setTitle("UPM-Monopoly");
		this.stage.initOwner(fp.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);

		//Scene scene = new Scene(AnchorPaneRoot, 884.0,495.0);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


		root = new VBox();

		initRoot();
	    Scene scene = new Scene(root, 884.0,495.0/*400,190*/);


		stage.setScene(scene);

		stage.setOnHiding(new EvtQuitter());
	}

	/**
	 * Initialise la VBox root de la FenetreDemarrage avec une {@link ListView} de nombres de joueurs et un bouton de validation.
	 */
	private void initRoot() {
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(5);
       // root.resize(884, 495);

		l_NbJoueurs = new Label("Noms des joueurs (2 minimum) :");
		l_NbJoueurs2 = new Label("                                                        Coder par Hegel Motokoua");


		VBox vBox1 =new VBox();
		vBox1.setPrefHeight(884.0);
		vBox1.setPrefWidth(495.);
		vBox1.setStyle("-fx-background-color: #005395");
		vBox1.setBlendMode(BlendMode.SRC_OVER);
		vBox1.setDepthTest(DepthTest.INHERIT);
		vBox1.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		vBox1.setCenterShape(true);
		l_NbJoueurs2.setCenterShape(true);

		ImageView imageView1=new ImageView();
		imageView1.setFitHeight(500.0);
		imageView1.setFitWidth(500.0);
		imageView1.setPickOnBounds(true);
		imageView1.setPreserveRatio(true);
		
		imageView1.setImage(new Image(getClass().getResourceAsStream("/images/ml3.png")));

		VBox.setMargin((imageView1), new Insets(10,0,0,170));

		vBox1.getChildren().add(imageView1);



		vBox1.getChildren().add(l_NbJoueurs2);



		root.getChildren().add(vBox1);



		root.getChildren().add(l_NbJoueurs);
		for(int i=0; i<4; i++) {
			listeJoueurs.add(new TextField(i<2?"Joueur"+(i+1):""));
			listeJoueurs.get(i).setPromptText("Nom du joueur "+(i+1));
			root.getChildren().add(listeJoueurs.get(i));
		}
		b_Valider = new Button("Valider");
		b_Valider.setOnAction(new EvtValider());
		b_Valider.setDefaultButton(true);
		b_Valider.setOnAction(new EvtValider());

		root.getChildren().add(b_Valider);



		VBox vBox2 =new VBox();
		vBox2.setPrefHeight(884.0);
		vBox2.setPrefWidth(495.);
		vBox2.setStyle("-fx-background-color: #005395");
		vBox2.setBlendMode(BlendMode.SRC_OVER);
		vBox2.setDepthTest(DepthTest.INHERIT);
		vBox2.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		vBox2.setCenterShape(true);
		l_NbJoueurs2.setCenterShape(true);

		ImageView imageView2=new ImageView();
		imageView2.setFitHeight(300.0);
		imageView2.setFitWidth(300.0);
		imageView2.setPickOnBounds(true);
		imageView2.setPreserveRatio(true);

		imageView2.setImage(new Image(getClass().getResourceAsStream("/images/LOGO_UPM_UNIVERSITE_PRIVEE_DE_MARRAKECH.jpg")));

		VBox.setMargin((imageView2), new Insets(10,0,0,270));

		vBox2.getChildren().add(imageView2);

		root.getChildren().add(vBox2);
	}

	/**
	 * Renvoie la Stage de la fen�tre de d�marrage.
	 * @return stage Stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * �v�nement qui r�cup�re dans la {@link ListView} le nombre de joueurs d�sir� et lance une partie avec ce nombre.
	 * @see FenetrePrincipale
	 */
	private class EvtValider implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			ArrayList<String> champs = new ArrayList<String>();
			for(int i=0; i<4; i++) {
				if(listeJoueurs.get(i).getText() != null && !listeJoueurs.get(i).getText().isEmpty())
					champs.add(listeJoueurs.get(i).getText());
			}
			if(champs.size()>=2) {
				choix = 1;
				fp.setPartie(champs.size(), champs);
				fp.getStage().show();
				stage.close();
			}
			event.consume();
		}
	}

	/**
	 * �v�nement qui ferme la fen�tre de d�marrage et arr�te le programme si l'on a pas cliqu� sur le bouton Valider.
	 */
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			if(choix == 0)
				System.exit(0);
			event.consume();
		}
	}
}
