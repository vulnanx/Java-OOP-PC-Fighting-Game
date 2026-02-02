package application;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuButton extends Button{
	private Image defImg;
	private Image hovImg;
	private ImageView view;
	
	public MenuButton(Image defImg, Image hovImg, int y){
		this.defImg = defImg;
		this.hovImg = hovImg;
		this.view = new ImageView(this.defImg);
		this.view.setFitHeight(60);
		this.view.setPreserveRatio(true);
		this.setTranslateX(340);
		this.setTranslateY(y);
		double imageHeight = this.view.getFitHeight(); // get the height of the image
        this.setPrefHeight(imageHeight);
        this.setMinHeight(imageHeight);
        this.setMaxHeight(imageHeight);
		this.setGraphic(this.view);
		this.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		
		this.setOnMouseExited(e -> {
			this.view.setImage(this.defImg);
			this.view.setScaleX(1);
			this.view.setScaleY(1);
			});
		this.setOnMouseEntered(e -> {
			this.view.setImage(this.hovImg);
			this.view.setScaleX(1.1); // enlarge a little
			this.view.setScaleY(1.1);
			});
	}
	
	public ImageView getView() {
		return this.view;
	}
}
	
