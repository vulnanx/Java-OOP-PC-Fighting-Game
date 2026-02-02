package application;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;
import java.util.HashSet;
import java.util.Set;

public class GameTimer extends AnimationTimer{
	
	private Stage stage;
	private Scene scene;
	private Player player2;
	private Player player1;
	private Player winner;
	private Rectangle player1_atk;
	private Rectangle player2_atk;
	private Rectangle hp_bar1;
	private Rectangle hp_bar2;
	private double windowWidth = Menu.WINDOW_WIDTH;
	private double windowHeight = Menu.WINDOW_HEIGHT;
	private int counter = 0;
	private Boolean block = false;
	private int current_hp1;
	private int hp1;
	private int current_hp2;
	private int hp2;
	
	
	
	
	//pwede paltan yung values dito para sa baba
	
	private final Boolean IS_VISIBLE = false;
	private final int INITIAL_Y_POSITION = 400;
	private final int INITIAL_X_POSITION1 = 100;
	private final int INITIAL_X_POSITION2 = 600;
	private final double MOVESPEED = 5.0;
	private final double BLOCKSPEED = 1.0;
	private final int ATTACK_WIDTH = 120;

	
	private Set<KeyCode> activeKeys = new HashSet<>();

	public GameTimer(Stage stage, Scene scene, Player player2, Player player1, Rectangle player1_atk, Rectangle player2_atk, Rectangle hp_bar1, Rectangle hp_bar2) {
		this.stage = stage;
		this.scene = scene;
		this.player2 = player2;
		this.player1 = player1;
		
		this.player1_atk = player1_atk;
		this.player2_atk = player2_atk;
		this.hp1 = player1.getHP();
		this.hp2 = player2.getHP();
		this.current_hp1 = player1.getHP();
		this.current_hp2 = player2.getHP();
		this.hp_bar1 = hp_bar1;
		this.hp_bar2 = hp_bar2;
		this.setInitialPositions();
		this.handleKeyPressEvent();
	}

	private void setInitialPositions() {
		
		player1.setXPos(INITIAL_X_POSITION1); 
		player1.setYPos(INITIAL_Y_POSITION);
		player2.setXPos(INITIAL_X_POSITION2); 
		player2.setYPos(INITIAL_Y_POSITION);
		player1_atk.setX(player1.getXPos());
		player1_atk.setY(player1.getYPos());
		player2_atk.setX(player2.getXPos());
		player2_atk.setY(player2.getYPos());
		
		hp_bar1.setX(INITIAL_X_POSITION1 - 20);
		hp_bar1.setY(70);
		
		hp_bar2.setX(INITIAL_X_POSITION2 + 20);
		hp_bar2.setY(70);
		
		player1_atk.setVisible(IS_VISIBLE);
		player2_atk.setVisible(IS_VISIBLE);	
	}

	private void handleKeyPressEvent() {
	    scene.setOnKeyPressed(e -> {
	    	counter = counter + 1;
	        activeKeys.add(e.getCode());
	    });
	    scene.setOnKeyReleased(e -> {
	        activeKeys.remove(e.getCode());
	        counter = 0;
	        block = false;
	        if (e.getCode() == KeyCode.Q) {
	            player1_atk.setWidth(80); 
	        }
	        if (e.getCode() == KeyCode.U) {
	            player2_atk.setWidth(80);
	        }
	    });
	}

	private void movePlayer(Player player, Rectangle rectangle, KeyCode up, KeyCode down, KeyCode left, KeyCode right, Boolean isPlayer1) {
		double newX = player.getXPos(); 
		double newY = player.getYPos(); 
		double moveAmount = MOVESPEED; 
		double rectWidth = player.getWidth(); 
		double rectHeight = player.getHeight(); 
		int status = 0;
		if(isPlayer1 == false && player2.getXPos() + 100 <= player1.getXPos() || player2.getXPos() - 100 <= player1.getXPos() && activeKeys.contains(left) ) {
			if(isPlayer1 == true && activeKeys.contains(left)) {
				status = 0;
			}
			else {
				status = 1;
			}
		}
		else if(isPlayer1 == true && player1.getXPos() + 100 >= player2.getXPos() || player1.getXPos() - 100 >= player2.getXPos() && activeKeys.contains(right) ) {
			if(isPlayer1 == false && activeKeys.contains(right)) {
				status = 0;
			}
			else {
				status = 1;
			}
		}
		if(status == 0){
			if (isPlayer1 == true && activeKeys.contains(left) && newX - moveAmount >= -100) {
				if(!player1.hasAttackedRecently) {
					player1.drawBlock();
					moveAmount = BLOCKSPEED;
					newX -= moveAmount;
					block = true;
					
				}
			}
			if (isPlayer1 == true && activeKeys.contains(right) && newX + moveAmount + rectWidth <= windowWidth + 100) {
				if(!player1.hasAttackedRecently) {
					player1.drawWalk();
					moveAmount = MOVESPEED;
					newX += moveAmount;
					block = false;
				}
			}
			if (isPlayer1 == false && activeKeys.contains(left) && newX - moveAmount >= -100) {
				if(!player2.hasAttackedRecently) {
				player2.drawWalk();
				moveAmount = MOVESPEED;
				newX -= moveAmount;
				block = false;
				}
			}
			if (isPlayer1 == false && activeKeys.contains(right) && newX + moveAmount + rectWidth <= windowWidth + 100) {
				if(!player2.hasAttackedRecently) {
				player2.drawBlock();
				moveAmount = BLOCKSPEED;
				newX += moveAmount;
				block = true;
				
				}
			}
		}
		player.setXPos(newX);
		player.setYPos(newY);
		rectangle.setX(newX);
		rectangle.setY(newY);
	}
	
	private void basicAttack(Rectangle rectangle, KeyCode attack, Boolean isPlayer1) {
		if(isPlayer1 == true && activeKeys.contains(attack) && !player1.hasAttackedRecently) {
			player1.drawAttack();
			player1.hasAttackedRecently=true;

			Timer timer1 = new Timer();
			
			TimerTask restoreGrace1 = new TimerTask() {
				public void run() {
					player1.hasAttackedRecently=false;
				}
			};

			timer1.schedule(restoreGrace1,player1.getAtkSpeed());
			
			
			rectangle.setWidth(ATTACK_WIDTH);
			if(rectangle.getWidth() + rectangle.getX() >= player2.getXPos() && counter == 1 && block == false) {
				hp_bar2.setWidth(player2.getCurrentHP()*2);
				System.out.println("Attack hit on player 2");
				player2.setCurrentHP(player2.getCurrentHP() - 5);
				counter = counter + 1;
				System.out.println("player 2 hp:" +  player2.getCurrentHP());
			} else if(rectangle.getWidth() + rectangle.getX() >= player2.getXPos() && counter == 1 && block == true) {
				System.out.println("Attack blocked");
				counter = counter + 1;
			}
			
			
			
		}
		if(isPlayer1 == false && activeKeys.contains(attack) && !player2.hasAttackedRecently) {
			player2.drawAttack();
			player2.hasAttackedRecently = true;

			Timer timer = new Timer();
			
			TimerTask restoreGrace = new TimerTask() {
				public void run() {
					player2.hasAttackedRecently = false;
				}
			};

			timer.schedule(restoreGrace,player2.getAtkSpeed());
			
			rectangle.setX(rectangle.getX() - 40);
			rectangle.setWidth(ATTACK_WIDTH);
			if(rectangle.getX() <= (player1.getXPos() + 100) && counter == 1 && block == false) {
				hp_bar1.setWidth(player1.getCurrentHP()*2);
				System.out.println("Attack hit on player 1");
				player1.setCurrentHP(player1.getCurrentHP() - 10);
				counter = counter + 1;
				System.out.println("player 1 hp:" +  player1.getCurrentHP());
			}
			else if(rectangle.getX() <= (player1.getXPos() + 100) && counter == 1 && block == true) {
				System.out.println("Attack blocked");
				counter = counter + 1;
			}
		}
		if(player1.getCurrentHP() <= 0) {
			System.out.println("Player 1 is dead");
			this.stop();
			Platform.runLater(() -> {
	            this.winner = player2;
	            GameStage.showGameOverScene(this.stage, this.winner);
	        });
		}
		if(player2.getCurrentHP() <= 0) {
			System.out.println("Player 2 is dead");
			this.stop();
			Platform.runLater(() -> {
	            this.winner = player1;
	            GameStage.showGameOverScene(this.stage, this.winner);
	        });
		}
	}
		
	@Override
	public void handle(long now) {
	    // Update players' positions
	    movePlayer(player1, player1_atk, KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D, true);
	    movePlayer(player2, player2_atk, KeyCode.I, KeyCode.K, KeyCode.J, KeyCode.L, false);
	   
	    basicAttack(player1_atk, KeyCode.Q, true);
	    basicAttack(player2_atk, KeyCode.U, false);
	    
	}

}
