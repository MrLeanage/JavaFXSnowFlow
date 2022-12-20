import javafx.animation.Animation;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.time.LocalDate;
import java.util.Random;

public class SnowFallingEffect {
    /**
     * Declaring Variables
     */

    //Change this variable to change snow falling frequency
    private static final Integer snowFrequency = 100;
    private static Random random = null;
    private static AnchorPane rootPane = null;
    private static Integer windowHeight = 0;
    private static Integer windowWidth = 0;
    private static Animation seasonalAnimation = null;

    /**
     * private static method to get Christmas Snowfall Decorations
     */
    private static void getChristmasDecorations(){
        Light.Distant light = new Light.Distant();
        light.setAzimuth(5);
        light.setElevation(60);
        light.setColor(Color.rgb(255,255,255));

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);

        Label[] labelArray = new Label[snowFrequency];
        String snowArrayList[] = {"❄", "❅"};

        Random random = new Random();
        for (int i = 0; i < snowFrequency; i++) {
            labelArray[i] = new Label(snowArrayList[random.nextInt(snowArrayList.length)]);
            labelArray[i].setStyle("-fx-font: 15 arial; -fx-text-fill: #d9d8d8; ");
            labelArray[i].setEffect(lighting);
            rootPane.getChildren().add(labelArray[i]);
            snowRaining(labelArray[i]);
        }
    }

    /**
     * Private static method to make the snowfall animation
     * @param label which need to animate with text inside
     */
    private static void snowRaining(Label label) {
        label.setLayoutX(random.nextInt(windowWidth));//Window width

        int time = 10 + random.nextInt(15);
        seasonalAnimation = TranslateTransitionBuilder.create()
                .node(label)
                .fromY(-200)
                .toY(windowHeight+200) //Window height
                .toX(random.nextDouble() * label.getLayoutX())
                .duration(Duration.seconds(time))
                .onFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        snowRaining(label);
                    }
                }).build();
        seasonalAnimation.play();
    }

    /**
     * Getting current screen bounds (height and Width)
     * @return Rectangle2D with height and Width
     */
    private static Rectangle2D getScreenBounds(){
        return Screen.getPrimary().getVisualBounds();
    }

    /**
     * Public method to get seasonal Decorations
     * @param rootPane as the main Anchorpane in the GUI Application
     */
    public static void getSeasonalDecorations(AnchorPane rootPane){

        SnowFallingEffect.rootPane = rootPane;
        SnowFallingEffect.windowHeight = (int) Math.round(rootPane.getPrefHeight());
        SnowFallingEffect.windowWidth = (int) Math.round(rootPane.getPrefWidth());
        random = new Random();

        if(windowHeight == 0 || windowWidth == 0){
            SnowFallingEffect.windowHeight = (int) Math.round(getScreenBounds().getHeight());
            SnowFallingEffect.windowWidth = (int) Math.round(getScreenBounds().getWidth());
        }

        if(LocalDate.now().getMonth().toString().equals("DECEMBER")){
            getChristmasDecorations();
        }
    }

    /**
     * public method to stop current playing snow animation
     */
    public static void stopSeasonalAnimation(){
        if(seasonalAnimation != null)
            seasonalAnimation.stop();
    }
}
