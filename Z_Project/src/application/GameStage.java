package application;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;



public class GameStage {
	
    
    private Scene scene;
    private Stage stage;
    private Group root;
    private static Player player2; 
    private static Player player1; 
    private Rectangle player1_atk;
	private Rectangle player2_atk;
	private GameTimer gametimer;
	private Rectangle hp_bar1;
	private Rectangle hp_bar2;
	private static final int WINDOW_HEIGHT = Menu.WINDOW_HEIGHT;
    private static final int WINDOW_WIDTH = Menu.WINDOW_WIDTH;
	private int currentPlayer = 1;
	private int gameTimeSecs = 60;
	private Timeline gameTimer;
	private Label timerLabel;


    public GameStage() {
        this.root = new Group();
        this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        Image char1 = new Image(getClass().getResource("char1.png").toExternalForm());  
        Image char2 = new Image(getClass().getResource("char1.png").toExternalForm()); 
        Image backgroundImage = new Image(getClass().getResource("bgScene.png").toExternalForm());
        ImageView background = new ImageView(backgroundImage);
	        this.root = new Group(background); 
	        background.setFitWidth(WINDOW_WIDTH);
	        background.setFitHeight(WINDOW_HEIGHT);
	        
        this.player2 = new Player("player2", 150, 50, 20, 1200, false,0 ,0, 100, 150, char1,true/*,"KNIGHT_ATTACK_1.png","KNIGHT_ATTACK_2.png","KNIGHT_ATTACK_3.png","KNIGHT_WALK_1.png","KNIGHT_WALK_2.png","KNIGHT_BLOCK_1.png","KNIGHT_BLOCK_2.png","KNIGHT_IDLE_1.png"*/);
        this.player1 = new Player("player1", 100, 50, 10, 750, false,0 ,0, 100, 150, char2,false/*,"THIEF_ATTACK_1.png","THIEF_ATTACK_2.png","THIEF_ATTACK_3.png","THIEF_WALK_1.png","THIEF_WALK_2.png","THIEF_WALK_1.png","THIEF_WALK_2.png","THIEF_WALK_1.png"*/);
        player2.getCharacterImage().setScaleX(-1);
        this.player1_atk = new Rectangle(0, 20);
        this.player1_atk.setFill(Color.BLUE);
        this.player2_atk = new Rectangle(0, 20);
        this.player2_atk.setFill(Color.RED);
        
        this.hp_bar1 = new Rectangle(200,20);
        this.hp_bar1.setFill(Color.RED);
        
        this.hp_bar2 = new Rectangle(200,20);
        this.hp_bar2.setFill(Color.RED);
        
        this.root.getChildren().add(player1.getCharacterImage());
        this.root.getChildren().add(player2.getCharacterImage());
        this.root.getChildren().addAll(player1_atk, player2_atk, hp_bar1, hp_bar2);
        this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        this.timerLabel = new Label();
        this.timerLabel.setText("Time Left: " + gameTimeSecs + "s");
        this.timerLabel.setStyle("-fx-font-size: 24; -fx-text-fill: white;");
        this.timerLabel.setTranslateX(WINDOW_WIDTH - 150); // Adjust position
        this.timerLabel.setTranslateY(20);
        this.root.getChildren().add(timerLabel);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setResizable(false);
        charSelectScene(stage, player1, player2);        
    }
    
    private void charSelectScene(Stage stage, Player p1, Player p2) {
		Group root = new Group();
		HBox vbox = new HBox();
			vbox.setPadding(new Insets(250, 20, 20, 20));
		    vbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root, Menu.WINDOW_WIDTH, Menu.WINDOW_HEIGHT);
		
		Image player1charsel = new Image(getClass().getResource("/player1charsel.png").toExternalForm());
		Image player2charsel = new Image(getClass().getResource("/player2charsel.png").toExternalForm());
		ImageView viewbg = new ImageView(player1charsel);
			viewbg.setFitHeight(WINDOW_HEIGHT);
			viewbg.setFitWidth(WINDOW_WIDTH);
			viewbg.setPreserveRatio(false);
			
		
		Image char1 = new Image("/char1.png");
		Image char2 = new Image("/char2.png");
		Image char3 = new Image("/char3.png");
		Image char4 = new Image("/char4.png");
		ImageView char1view = new ImageView(char1);
			char1view.setFitWidth(200);
			char1view.setFitHeight(200);
		ImageView char2view = new ImageView(char2);
			char2view.setFitWidth(200);
			char2view.setFitHeight(200);
		ImageView char3view = new ImageView(char3);
			char3view.setFitWidth(200);
			char3view.setFitHeight(200);
		ImageView char4view = new ImageView(char4);
			char4view.setFitWidth(200);
			char4view.setFitHeight(200);
		
			
		RadioButton btn1 = new RadioButton();
			btn1.setGraphic(char1view);
		RadioButton btn2 = new RadioButton();
			btn2.setGraphic(char2view);
		RadioButton btn3 = new RadioButton();
			btn3.setGraphic(char3view);
		RadioButton btn4 = new RadioButton();
			btn4.setGraphic(char4view);
			
		ToggleGroup group = new ToggleGroup();
		btn1.setToggleGroup(group);
		btn2.setToggleGroup(group);
		btn3.setToggleGroup(group);
		btn4.setToggleGroup(group);
		
		Image pickBtn = new Image("/pickBtn.png");
		Image pickBtnHov = new Image("/pickBtnHov.png");
		BackButton confirm = new BackButton(pickBtn,pickBtnHov,350,450,120);
		
		vbox.getChildren().addAll(btn1,btn2,btn3,btn4);
		root.getChildren().addAll(viewbg,confirm,vbox);
		
		// set logic for character selection
		confirm.setOnAction(event -> {
            RadioButton selectedButton = (RadioButton) group.getSelectedToggle();
            
            if (selectedButton != null) {
				if (currentPlayer == 1) {
					if (selectedButton == btn1) {
		            	 player1.setCharacter("char1");
		            } else if (selectedButton == btn2) {
		            	player1.setCharacter("char2");
		            } else if (selectedButton == btn3) {
		            	player1.setCharacter("char3");
		            } else {
		            	player1.setCharacter("char4");
		            }
					System.out.println("PLAYER 1 chose: " + player1.getCharacter());
                    currentPlayer = 2;
                    viewbg.setImage(player2charsel);
                    group.selectToggle(null); 
                } else if (currentPlayer == 2) {
                	if (selectedButton == btn1) {
                   	 player2.setCharacter("char1");
                   } else if (selectedButton == btn2) {
                   	player2.setCharacter("char2");
                   } else if (selectedButton == btn3) {
                   	player2.setCharacter("char3");
                   } else {
                   	player2.setCharacter("char4");
                   }
					System.out.println("PLAYER 2 chose: " + player2.getCharacter());
                    showGameScene(this.stage, this.scene, this.root);
                }
            } else {
                System.out.println("Please select a character!");
            }
        });
		
        stage.setScene(scene);
		stage.show();
	}

	private void showGameScene(Stage stage, Scene scene, Group root) {
		// Create a new scene for the game
		this.gametimer = new GameTimer(this.stage, this.scene, this.player2, this.player1, this.player1_atk, this.player2_atk, this.hp_bar1, this.hp_bar2);
        this.gametimer.start();
        startTimer(); // Start the countdown timer
        this.stage.setScene(scene);
		
	}
	public static void showGameOverScene (Stage stage, Player winner) {
		StackPane root = new StackPane();
		Scene scene  = new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT);
		
		Image defImg = new Image("/backRed.png");
		Image hovImg = new Image("/backRedHover.png");
		BackButton back = new BackButton(defImg,hovImg,0,220,50);
		back.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				stage.setScene(Menu.getScene());
			}
		});
		
		if (winner == player1) {
			Image bgimg = new Image(GameStage.class.getResource("/player1wins.gif").toExternalForm());
			ImageView view = new ImageView(bgimg);
			root.getChildren().add(view);
		} else {
			Image bgimg = new Image(GameStage.class.getResource("/player2wins.gif").toExternalForm());
			ImageView view = new ImageView(bgimg);
			root.getChildren().add(view);
		}
		
		root.getChildren().add(back);
		stage.setScene(scene);
	    stage.show();
	}
	
	private void endGame() {
	    Player winner;
	    if (player1.getCurrentHP() > player2.getCurrentHP()) {
	        winner = player1;
	    } else if (player2.getCurrentHP() > player1.getCurrentHP()) {
	        winner = player2;
	    } else {
	        System.out.println("It's a draw!");
	        return;
	    }

	    System.out.println("Game Over! Winner: " + winner.getName());
	    showGameOverScene(stage, winner);
	}
	
	private void startTimer() {
	    gameTimer = new Timeline(new javafx.animation.KeyFrame(
	        javafx.util.Duration.seconds(1), 
	        event -> {
	            timerLabel.setText("Time Left: " + gameTimeSecs + "s");
	            timerLabel.setTranslateX(370); 
	            timerLabel.setTranslateY(110);
	            gameTimeSecs--;

	            // Check if the timer has ended
	            if (gameTimeSecs <= 0) {
	                gameTimer.stop();
	                endGame();
	            }
	        }
	    ));
	    gameTimer.setCycleCount(gameTimeSecs); // Runs for the specified number of seconds
	    gameTimer.play();
	}
	
}
