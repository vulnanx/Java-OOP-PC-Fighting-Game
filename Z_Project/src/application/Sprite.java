package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
    protected Image img;
    protected ImageView imageView;
    protected double xPos, yPos;
    protected double width;
    protected double height;

    public Sprite(double xPos, double yPos, Image image, double width, double height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.img = image;

        // Initialize ImageView and position it
        this.imageView = new ImageView(this.img);
        this.imageView.setFitWidth(this.width);
        this.imageView.setFitHeight(this.height);
        this.imageView.setX(this.xPos);
        this.imageView.setY(this.yPos);
        
        this.imageView = new ImageView(this.img);
        this.imageView.setFitWidth(this.width);
        this.imageView.setFitHeight(this.height);
        this.imageView.setX(this.xPos);
        this.imageView.setY(this.yPos);
        
        this.imageView = new ImageView(this.img);
        this.imageView.setFitWidth(this.width);
        this.imageView.setFitHeight(this.height);
        this.imageView.setX(this.xPos);
        this.imageView.setY(this.yPos);
        
        this.imageView.setPreserveRatio(true);
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void setXPos(double xPos) {
        this.xPos = xPos;
        this.imageView.setX(xPos);
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
        this.imageView.setY(yPos);
    }
    
    public double getXPos(){
        return this.xPos;
    }

    public double getYPos(){
        return this.yPos;
    }
    
    public double getWidth() {
    	return this.width;
    }
    
    public double getHeight() {
    	return this.height;
    }
}
