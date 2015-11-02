package poker.app.view;

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
	private void handleP1SitLeave() {
		
		int iPlayerPosition = 1;
		
		if (bP1Sit == false)
		{
			Player p = new Player(txtP1Name.getText(),iPlayerPosition);
			mainApp.AddPlayerToTable(p);
			lblP1Name.setText(txtP1Name.getText());
			lblP1Name.setVisible(true);
			btnP1SitLeave.setText("Leave");
			txtP1Name.setVisible(false);
			bP1Sit = true;
		}
		else
		{
			mainApp.RemovePlayerFromTable(iPlayerPosition);
			btnP1SitLeave.setText("Sit");
			txtP1Name.setVisible(true);
			lblP1Name.setVisible(false);
			bP1Sit = false;			
		}
		
	}
	
	@FXML
	private void handleP2SitLeave() {
		
		int iPlayerPosition = 2;
		
		if (bP2Sit == false)
		{
			Player p = new Player(txtP2Name.getText(),iPlayerPosition);
			mainApp.AddPlayerToTable(p);
			lblP2Name.setText(txtP2Name.getText());
			lblP2Name.setVisible(true);
			btnP2SitLeave.setText("Leave");
			txtP2Name.setVisible(false);
			bP2Sit = true;
		}
		else
		{
			mainApp.RemovePlayerFromTable(iPlayerPosition);
			btnP2SitLeave.setText("Sit");
			txtP2Name.setVisible(true);
			lblP2Name.setVisible(false);
			bP2Sit = false;			
		}
		
	}
	
	@FXML
	private void handleP3SitLeave() {
		
		int iPlayerPosition = 3;
		
		if (bP3Sit == false)
		{
			Player p = new Player(txtP3Name.getText(),iPlayerPosition);
			mainApp.AddPlayerToTable(p);
			lblP3Name.setText(txtP3Name.getText());
			lblP3Name.setVisible(true);
			btnP3SitLeave.setText("Leave");
			txtP3Name.setVisible(false);
			bP3Sit = true;
		}
		else
		{
			mainApp.RemovePlayerFromTable(iPlayerPosition);
			btnP3SitLeave.setText("Sit");
			txtP3Name.setVisible(true);
			lblP3Name.setVisible(false);
			bP3Sit = false;			
		}
		
	}
	
	@FXML
	private void handleP4SitLeave() {
		
		int iPlayerPosition = 4;
		
		if (bP4Sit == false)
		{
			Player p = new Player(txtP4Name.getText(),iPlayerPosition);
			mainApp.AddPlayerToTable(p);
			lblP4Name.setText(txtP4Name.getText());
			lblP4Name.setVisible(true);
			btnP4SitLeave.setText("Leave");
			txtP4Name.setVisible(false);
			bP4Sit = true;
		}
		else
		{
			mainApp.RemovePlayerFromTable(iPlayerPosition);
			btnP4SitLeave.setText("Sit");
			txtP4Name.setVisible(true);
			lblP4Name.setVisible(false);
			bP4Sit = false;			
		}
		
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
					GamePlayPlayerHand GPPH = gme.FindPlayerGame(gme, p);
					GPPH.addCardToHand(c);
					String strCard = "/res/img/" + c.getCardImg() ;		
					ImageView img = new ImageView(new Image(getClass().getResourceAsStream(
							strCard), 75, 75, true, true));
					h1P1.getChildren().add(img);
					
					
					
					if (iCardDrawn == 5)
					{
						GPPH.getHand().EvalHand();
						System.out.println(GPPH.getHand().getHandStrength());
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









