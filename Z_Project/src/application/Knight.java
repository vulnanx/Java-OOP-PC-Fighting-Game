package application;

import javafx.scene.image.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;


public class Knight extends Sprite {
    private String name;
    private String character;
    private int hp = 100;
    private int current_hp = 100;
    private int block_hp = 50;
    private int basic_atk = 10;
    public Boolean isBlocking = false;
    private ImageView characterImage;
    private int spriteCounter;
    private int spriteNum;
    private int atkSpeed;
    public boolean hasAttackedRecently;
    
    private Image idle = new Image(getClass().getResource("KNIGHT_IDLE_1.png").toExternalForm());
    private Image right1 = new Image(getClass().getResource("KNIGHT_WALK_1.png").toExternalForm());
    private Image attack1 = new Image(getClass().getResource("KNIGHT_ATTACK_1.png").toExternalForm());
    private Image attack2 = new Image(getClass().getResource("KNIGHT_ATTACK_2.png").toExternalForm());
    private Image attack3 = new Image(getClass().getResource("KNIGHT_ATTACK_3.png").toExternalForm());
    private Image right2 = new Image(getClass().getResource("KNIGHT_WALK_2.png").toExternalForm());
    private Image block1 = new Image(getClass().getResource("KNIGHT_BLOCK_1.png").toExternalForm());

    private Image block2=new Image(getClass().getResource("KNIGHT_BLOCK_2.png").toExternalForm());

    public Knight(String name, int hp, int block_hp, int basic_atk, Boolean isBlocking, double x, double y, double width, double height, Image player_image) {
        super(x, y, player_image, width, height);
        this.name = name;
        this.hp = hp;
        this.current_hp = current_hp;
        this.block_hp = block_hp;
        this.basic_atk = basic_atk;
        this.isBlocking = isBlocking;
        this.spriteCounter=0;
        this.spriteNum=0;

        this.atkSpeed=1500;
        this.hasAttackedRecently=false;
        
        this.characterImage = new ImageView(player_image);
        this.characterImage.setImage(idle);
        this.characterImage.setFitWidth(width);  
        this.characterImage.setFitHeight(height);
        this.characterImage.setX(x);             
        this.characterImage.setY(y);
    }

    public ImageView getCharacterImage() {
        return characterImage;
    }
    
    public int getHP() {
        return this.hp;
    }
    
    public int getCurrentHP() {
        return this.current_hp;
    }
    public void setCurrentHP(int hp) {
        this.current_hp = hp ;
    }

    @Override
    public void setXPos(double x) {
        super.setXPos(x);
        this.characterImage.setX(x);
    }

    @Override
    public void setYPos(double y) {
        super.setYPos(y);
        this.characterImage.setY(y); 
    }

	public void setCharacter(String character) {
		this.character = character;
		
	}

	public String getName() {
		return this.name;
	}

	public String getCharacter() {
		return this.character;
	}
	
	public int getAtkSpeed() {
		return this.atkSpeed;
	}
    
	public void drawWalk() {
		if(!this.hasAttackedRecently) {
			this.spriteCounter++;
			if(spriteCounter>10) {
				if(spriteNum==1) {
					spriteNum=0;

					this.characterImage.setImage(right1);
					
				}
				else if(spriteNum==0) {
					spriteNum=1;

					this.characterImage.setImage(right2);
					
				}
				spriteCounter=0;
			}
		}
		


	}
	public void drawBlock() {
		if(!this.hasAttackedRecently) {
			this.spriteCounter++;
			if(spriteCounter>30) {
				if(spriteNum==1) {
					spriteNum=0;
					this.characterImage.setImage(block1);
					
				}
				else if(spriteNum==0) {
					spriteNum=1;

					this.characterImage.setImage(block2);
					
				}
				spriteCounter=0;
			}
		}
		


	}
	public void drawAttack() {
	    if (this.character.equals("player1")) {
	        characterImage.setImage(attack1);
	    } else if (this.character.equals("player2")) {
	        characterImage.setImage(attack1); 
	    }

	    Timer frame = new Timer();

	    TimerTask frame1 = new TimerTask() {
	        public void run() {
	            characterImage.setImage(attack2);
	        }
	    };
	    TimerTask frame2 = new TimerTask() {
	        public void run() {
	            characterImage.setImage(attack3);
	        }
	    };

	    frame.schedule(frame1, this.getAtkSpeed() / 3);
	    frame.schedule(frame2, this.getAtkSpeed() / 3 + this.getAtkSpeed() / 3);
	}

	public void drawIdle() {
	    this.characterImage.setImage(idle);
	}
    
	
    
}
