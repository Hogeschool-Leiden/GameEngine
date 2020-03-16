package nl.hsleiden.engine;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import nl.hsleiden.behaviour.Behaviour;
import nl.hsleiden.behaviour.BehaviourManager;
import nl.hsleiden.behaviour.CollisionManager;
import nl.hsleiden.behaviour.KeyBehaviourManager;
import nl.hsleiden.behaviour.behaviours.Collidable;
import nl.hsleiden.behaviour.behaviours.KeyBehaviour;
import nl.hsleiden.game.Element;
import nl.hsleiden.game.Game;
import nl.hsleiden.game.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * De engine is het controlle mechanisme van het spel.
 */
public class Engine {
    private static Game game;
    private Renderer renderer;
    private HashMap<Class<? extends Behaviour>, BehaviourManager> behaviors;
    private Stage stage;


    public Engine(Game game) {
        this.game = game;
        this.behaviors = new HashMap<>();
    }

    /**
     * Dit is een methode om het game object overal te kunnen verkrijgen in het spel.
     * Gebruik deze methode voor het toevoegen van elementen aan het spel tijdens het draaien van het spel.
     * @return de game
     * */
    public static Game getGameGlobaly() {
        return game;
    }

    /**
     * Dit is een methode om het spel te starten.
     *
     * @param primaryStage die meegegeven word vanuit bij het opstarten van een javafx applicatie.
     * */
    public void start(Stage primaryStage) {

        this.stage = primaryStage;
        setupInitialBehaviorsAndRenderer();

        renderer.render();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.getActiveLevel().refreshElements();
                Set<Class<? extends Behaviour>> behaviorsKeySet = getBehaviors().keySet();

                for (Class<? extends Behaviour> behavior : behaviorsKeySet) {
                    BehaviourManager behaviorManager = getBehaviors().get(behavior);

                    Level level = getGame().getActiveLevel();
                    ArrayList<Element> elements = level.getBufferedElements();

                    for (Element element : elements) {
                        if (behavior.isInstance(element))
                            behaviorManager.handle(element);
                    }
                }
                renderer.render();

            }
        }.start();
    }

    private void setupInitialBehaviorsAndRenderer() {
        this.renderer = new Renderer(game,stage);
        if(getGame().getActiveLevel().getFocusedElement() != null){
            focusOnElement(getGame().getActiveLevel().getFocusedElement());
        }
        KeyBehaviourManager keyBehaviorManager = new KeyBehaviourManager(stage);
        CollisionManager collisionManager = new CollisionManager(game);
        addBehavior(KeyBehaviour.class, keyBehaviorManager);

        addBehavior(Collidable.class,collisionManager);

    }

    public void resetRenderer(){
        this.renderer.resetRenderer();
    }

    /**
     * Hiermee kan er een gedrag aan de game toegevoegd worden.
     *
     * @param behavior het gedrag dat in de game aanwezig is.
     * @param behaviorManager de manager die het gedrag kan afhandelen.
     * */
    public void addBehavior(Class<? extends Behaviour> behavior, BehaviourManager behaviorManager) {
        this.behaviors.put(behavior, behaviorManager);
    }

    /**
     * Hiermee kan een focus van de camera op een element gezet worden. Bijvoorbeeld de character die je bestuurd.
     *
     * @param element de character waarop je de camera op kan laten focussen.
     * */
    public void focusOnElement(Element element) {
        this.renderer.getCamera().focus(element);
    }

    /**
     * Hiermee kan de instantie van de game opgevraagd worden, maar alleen als je een instantie van de engine hebt.
     *
     * @return de game
     * */
    public Game getGame() {
        return this.game;
    }

    /**
     * Hiermee kan je al het gedrag dat in de game aanwezig is opvragen.
     *
     * @return een HashMap met alle soorten gedrag.
     * Deze bevatten .class objecten geen daadwerkelijke instanties van het gedrag.
     * */
    public HashMap<Class<? extends Behaviour>, BehaviourManager> getBehaviors() {
        return this.behaviors;
    }
}
