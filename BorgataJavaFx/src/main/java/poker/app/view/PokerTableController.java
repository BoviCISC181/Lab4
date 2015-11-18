package poker.app.view;

import java.util.ArrayList;

import enums.eGame;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import poker.app.MainApp;
import pokerBase.Card;
import pokerBase.Deck;
import pokerBase.GamePlay;
import pokerBase.GamePlayPlayerHand;
import pokerBase.Hand;
import pokerBase.Player;
import pokerBase.Rule;

public class PokerTableController {

	boolean bP1Sit = false;
	boolean bP2Sit = false;
	boolean bP3Sit = false;
	boolean bP4Sit = false;
	
	// Reference to the main application.
	private MainApp mainApp;
	private GamePlay gme = null;
	private int iCardDrawn = 0;
	private int playersSeated = 0;
	
	private GamePlayPlayerHand GPPH1 = new GamePlayPlayerHand();
	private GamePlayPlayerHand GPPH2 = new GamePlayPlayerHand();
	private GamePlayPlayerHand GPPH3 = new GamePlayPlayerHand();
	private GamePlayPlayerHand GPPH4 = new GamePlayPlayerHand();
	
	@FXML
	public HBox h1P1;
	
	@FXML
	public HBox h1P2;
	
	@FXML
	public HBox h1P3;
	
	@FXML
	public HBox h1P4;

	@FXML
	public TextField txtP1Name;
	
	@FXML
	public TextField txtP2Name;
	
	@FXML
	public TextField txtP3Name;
	
	@FXML
	public TextField txtP4Name;
	
	@FXML
	public Label lblP1Name;
	
	@FXML
	public Label lblP2Name;
	
	@FXML
	public Label lblP3Name;
	
	@FXML
	public Label lblP4Name;

	@FXML
	public ToggleButton btnP1SitLeave;
	
	@FXML
	public ToggleButton btnP2SitLeave;
	
	@FXML
	public ToggleButton btnP3SitLeave;
	
	@FXML
	public ToggleButton btnP4SitLeave;
	
	@FXML
	public Button btnDraw;
	
	@FXML
	public Button btnPlay;

	public PokerTableController() {
	}
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}
	
	@FXML
	private boolean handleSitLeave(Label lblName, ToggleButton butnSitLeave, TextField txtName, int position, boolean Sit){
		
		if (Sit == false){
			Player p = new Player(txtName.getText(),position);
			mainApp.AddPlayerToTable(p);
			lblName.setText(txtName.getText());
			lblName.setVisible(true);
			butnSitLeave.setText("Leave");
			txtName.setVisible(false);
			//playersSeated++;
			return true;
		}
		else{
			mainApp.RemovePlayerFromTable(position);
			butnSitLeave.setText("Sit");
			txtName.setVisible(true);
			lblName.setVisible(false);
			//playersSeated--;
			return false;		
		}
		
	}
	
	@FXML
	private void handleP1SitLeave() {
		int iPlayerPosition = 1;
		bP1Sit = handleSitLeave(lblP1Name, btnP1SitLeave, txtP1Name, iPlayerPosition, bP1Sit);
	}
	
	@FXML
	private void handleP2SitLeave() {
		int iPlayerPosition = 2;
		bP2Sit = handleSitLeave(lblP2Name, btnP2SitLeave, txtP2Name, iPlayerPosition, bP2Sit);
	}
	
	@FXML
	private void handleP3SitLeave() {
		int iPlayerPosition = 3;
		bP3Sit = handleSitLeave(lblP3Name, btnP3SitLeave, txtP3Name, iPlayerPosition, bP3Sit);
	}
	
	@FXML
	private void handleP4SitLeave() {
		int iPlayerPosition = 4;
		bP4Sit = handleSitLeave(lblP4Name, btnP4SitLeave, txtP4Name, iPlayerPosition, bP4Sit);
	}
	
	@FXML
	private void handleDraw()
	{
		iCardDrawn++;
		
		//	Draw a card for each player seated		
		for (Player p: mainApp.GetSeatedPlayers())
		{
				Card c = gme.getGameDeck().drawFromDeck();
				
				if (p.getiPlayerPosition() == 1)				
				{
					GamePlayPlayerHand GPPH1 = gme.FindPlayerGame(gme, p);
					GPPH1.addCardToHand(c);
					String strCard = "/res/img/" + c.getCardImg() ;		
					ImageView img = new ImageView(new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));
					h1P1.getChildren().add(img);
					
					
					
					if (iCardDrawn == 5)
					{
						GPPH1.getHand().EvalHand();
						System.out.println(GPPH1.getHand().getHandStrength());
					}
				}
				
				if (p.getiPlayerPosition() == 2)				
				{
					GamePlayPlayerHand GPPH2 = gme.FindPlayerGame(gme, p);
					GPPH2.addCardToHand(c);
					String strCard = "/res/img/" + c.getCardImg() ;		
					ImageView img = new ImageView(new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));
					h1P2.getChildren().add(img);
					
					
					
					if (iCardDrawn == 5)
					{
						GPPH2.getHand().EvalHand();
						System.out.println(GPPH2.getHand().getHandStrength());
					}
				}
				
				if (p.getiPlayerPosition() == 3)				
				{
					GamePlayPlayerHand GPPH3 = gme.FindPlayerGame(gme, p);
					GPPH3.addCardToHand(c);
					String strCard = "/res/img/" + c.getCardImg() ;		
					ImageView img = new ImageView(new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));
					h1P3.getChildren().add(img);
					
					
					
					if (iCardDrawn == 5)
					{
						GPPH3.getHand().EvalHand();
						System.out.println(GPPH3.getHand().getHandStrength());
					}
				}
				
				if (p.getiPlayerPosition() == 4)				
				{
					GamePlayPlayerHand GPPH4 = gme.FindPlayerGame(gme, p);
					GPPH4.addCardToHand(c);
					String strCard = "/res/img/" + c.getCardImg() ;		
					ImageView img = new ImageView(new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));
					h1P4.getChildren().add(img);
					
					
					
					if (iCardDrawn == 5)
					{
						GPPH4.getHand().EvalHand();
						System.out.println(GPPH4.getHand().getHandStrength());
					}
				}
		}
		
		if (iCardDrawn == 5)
		{
			btnDraw.setVisible(false);

		}
		
		
	}
	
	@FXML
	private void handlePlay()
	{
		//	Clear all players hands
		h1P1.getChildren().clear();
		h1P2.getChildren().clear();
		h1P3.getChildren().clear();
		h1P4.getChildren().clear();
		
		//	Get the Rule, start the Game
		Rule rle = new Rule(eGame.FiveStud);		
		gme = new GamePlay(rle);
		
		//	Add the seated players to the game		
		for (Player p: mainApp.GetSeatedPlayers())
		{
			gme.addPlayerToGame(p);
			GamePlayPlayerHand GPPH = new GamePlayPlayerHand();
			GPPH.setGame(gme);
			GPPH.setPlayer(p);
			GPPH.setHand(new Hand());
			gme.addGamePlayPlayerHand(GPPH);			
		}
		
		//	Add a deck to the game
		gme.setGameDeck(new Deck());
		
		btnDraw.setVisible(true);
		iCardDrawn = 0;
	}
}









