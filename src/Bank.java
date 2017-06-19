import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Bank extends Application {
	
	// Declare BorderPane for menu bar
	private BorderPane bankPane;
	// Declared menu bar
	private MenuBar bankMenuBar;
	// Declare menus in menu bar
	private Menu menuFile, menuOther, menuAbout;
	// Declare menu items
	private MenuItem miLogOut, miClose, miPrintReceipt, miContactInfo;
	private Scene LoginScreen;
	private Screen screen;

	public Bank() {
		
	}

	public static void main(String[] args) {
		// Start the GUI of the ATM
		Bank.launch(args);
	}

	@Override
	public void start(Stage window) {

		// Declare labels
		Label loginLabel = new Label("Login");
		Label acctNum = new Label("Account Card Number");
		Label acctPin = new Label("Account Pin Number");

		// Declare text boxes
		Text acctNumInput = new Text();
		Text acctPinInput = new Text();

		// Declare buttons
		Button login = new Button("Login");
		Button signup = new Button("Sign up");
		
		// Disable ability to resize
		window.setResizable(false);
//		// disable window buttons
//		window.initStyle(StageStyle.UNDECORATED);		

		// Determines what scene is displayed first
		window.setScene(LoginScreen);
		// Setting title
		window.setTitle("Eagle Bank");
		// Enabling program to run on any IDE
		window.show();

	}

}