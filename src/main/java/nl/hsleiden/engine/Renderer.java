package nl.hsleiden.engine;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import nl.hsleiden.game.Element;
import nl.hsleiden.game.Game;
import nl.hsleiden.game.Tile;

/**
 * Deze class zorgd voor het renderen van het spel.
 * */
public class Renderer {
    private Game game;
    private Camera camera;
    private Stage stage;

    public Renderer(Game game, Stage stage) {
        this.game = game;
        this.stage = stage;
        this.camera = new Camera(game);
        initialSetup();
    }

    private void initialSetup(){
        Scene scene = new Scene(new Group(),1024,768);
        this.stage.setScene(scene);
        this.stage.getScene().setCamera(camera);
        this.stage.show();
    }

    public void resetRenderer(){
        this.stage.getScene().setRoot(new Group());
    }

    /**
     * Deze methode zet het renderen in werking.
     * */
    public void render(){
        camera.calculatePosition();
        removeOldElements();
        renderTiles();
        renderElements();

    }

    private void removeOldElements() {
        for(Node node:getRootGroup().getChildren()){
            if(node instanceof Element && !this.game.getActiveLevel().getBufferedElements().contains(node)){
                resetRenderer();
            }
        }


    }


    private void renderTiles() {
        Rectangle rectangle = new Rectangle(1024+80,768+80);
        rectangle.setX(camera.getX()-80);
        rectangle.setY(camera.getY()-80);
        for(int i = 0; i<this.game.getActiveLevel().getTiles().length; i++){
            for(int j = 0; j<this.game.getActiveLevel().getTiles()[i].length; j++){
                Tile tile = this.game.getActiveLevel().getTiles()[i][j];
                if(tile.intersects(rectangle.getLayoutBounds()) &&!getRootGroup().getChildren().contains(tile) ){
                    getRootGroup().getChildren().add(tile);
                }
                else if(!tile.intersects(rectangle.getLayoutBounds()) && getRootGroup().getChildren().contains(tile) ){
                    getRootGroup().getChildren().remove(tile);
                }
            }
        }
    }

    private void renderElements() {

        Rectangle rectangle = new Rectangle(1024+80,768+80);
        rectangle.setX(camera.getX()-80);
        rectangle.setY(camera.getY()-80);

        for(Element element : this.game.getActiveLevel().getBufferedElements()){
            if(element.intersects(rectangle.getLayoutBounds())){
                if(getRootGroup().getChildren().contains(element)){
                    getRootGroup().getChildren().remove(element);
                }
                getRootGroup().getChildren().add(element);
            }
            else if(!element.intersects(rectangle.getLayoutBounds()) && getRootGroup().getChildren().contains(element) ){
                getRootGroup().getChildren().remove(element);
            }
        }

    }

    /**
     * @return de camera.
     * */
    public Camera getCamera() {
        return camera;
    }

    private Group getRootGroup(){
        return (Group) this.stage.getScene().getRoot();
    }
}
