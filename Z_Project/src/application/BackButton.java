package application;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BackButton extends Button{
	private ImageView view;
	private Image defImg;
	private Image hovImg;
	
	public BackButton(Image defImg, Image hovImg, int x, int y, int h){
		this.defImg = defImg;
		this.hovImg = hovImg;
		this.view = new ImageView(this.defImg);
		this.view.setFitHeight(h);
		this.view.setPreserveRatio(true);
		this.setTranslateX(x);
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
