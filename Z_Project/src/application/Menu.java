package application;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Menu {

	private static Scene scene;
	private Group root;
	private StackPane stackpane;
	private Stage stage;
	final static int WINDOW_HEIGHT=600;
	final static int WINDOW_WIDTH=900;
	
	public Menu(Stage stage) {
		this.stage=stage;
		this.root = new Group();
		this.stackpane = new StackPane();
		scene = new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT);
		stage.setTitle("Krazy Puligists");
		stage.setResizable(false);
	}

	public void setupStage() {
		// 4 main menu buttons

	      Image play = new Image("/play1.png");
	      Image playHov = new Image("/playHover.png");
	      MenuButton game = new MenuButton(play,playHov, 250);
	      Image abt = new Image("/abt1.png");
	      Image abtHov = new Image("/abtHover.png");
	      MenuButton viewControls = new MenuButton(abt, abtHov,335);
	      Image cred = new Image("/creds1.png");
	      Image credHov = new Image("/credsHover.png");
	      MenuButton credits = new MenuButton(cred, credHov, 420);
	      Image exit = new Image("/exit1.png");
	      Image exitHov = new Image("/exitHover.png");
	      MenuButton quit = new MenuButton(exit,exitHov,505);
	      
	    game.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				GameStage gamestage = new GameStage();
	            gamestage.setStage(stage);
			}

		});
	      
		viewControls.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				viewControls.setUnderline(true);
				showControls(stage);
			}

		});
		credits.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				credits.setUnderline(true);
				showCredits(stage);
			}
		});
		quit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				quit.setUnderline(true);
				showQuitScene(stage);
			}
		});
		
		// bg image
		Image bgimg = new Image(getClass().getResource("/menubg1.gif").toExternalForm());
		ImageView viewbg = new ImageView(bgimg);
		viewbg.setFitHeight(WINDOW_HEIGHT);
		viewbg.setFitWidth(WINDOW_WIDTH);
		viewbg.setPreserveRatio(false);
		
		// stackpane layout
		this.stackpane.setAlignment(Pos.CENTER);
		
		// attaching nodes to root
		this.stackpane.getChildren().addAll(game,viewControls,credits,quit);
		this.root.getChildren().addAll(viewbg, stackpane);
		stage.setScene(scene);
		stage.show();
	}
	
	public void startStage() {
		this.stage.setScene(scene);
		this.stage.show();
	}
	
	public static Scene getScene() {
		return scene;
	}
	
	private void showControls(Stage stage) {
		StackPane root = new StackPane();
		Scene scene  = new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT);
		
		Image defImg = new Image("/back.png");
		Image hovImg = new Image("/backHover.png");
		BackButton back = new BackButton(defImg,hovImg,0,240,50);
		back.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				stage.setScene(getScene());
			}
		});
		
		Image bgimg = new Image(getClass().getResource("/instrucBG.gif").toExternalForm());
		ImageView view = new ImageView(bgimg);
		
		root.getChildren().add(view);
		root.getChildren().add(back);
		stage.setScene(scene);
	}
	
	private void showCredits(Stage stage) {
		StackPane root = new StackPane();
		Scene scene  = new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT);
		
		Image defImg = new Image("/back.png");
		Image hovImg = new Image("/backHover.png");
		BackButton back = new BackButton(defImg,hovImg,250,235,50);
		back.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				stage.setScene(getScene());
			}
		});
		
		Image bgimg = new Image(getClass().getResource("/devBG.gif").toExternalForm());
		ImageView view = new ImageView(bgimg);
		
		root.getChildren().add(view);
		root.getChildren().add(back);
		stage.setScene(scene);
	}
	
	public void showInformation(Stage stage) {
		StackPane root = new StackPane();
		Scene scene  = new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT);
		
		Image defImg = new Image("/back.png");
		Image hovImg = new Image("/backHover.png");
		BackButton back = new BackButton(defImg,hovImg,250,235,50);
		
		back.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				stage.setScene(getScene());
			}
		});
		
		Image bgimg = new Image(getClass().getResource("/devBG.gif").toExternalForm());
		ImageView view = new ImageView(bgimg);
		
		root.getChildren().add(view);
		root.getChildren().add(back);
		stage.setScene(scene);
	}
	
	
	
	private void showQuitScene(Stage stage) {
		StackPane root = new StackPane();
		Scene scene  = new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT);
		
		// pause for 3 seconds before exiting
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> System.exit(0));
        pause.play();
        
        Image bgimg = new Image(getClass().getResource("/exitBG.gif").toExternalForm());
		ImageView view = new ImageView(bgimg);
		
		root.getChildren().add(view);
		stage.setScene(scene);
	}
}

	