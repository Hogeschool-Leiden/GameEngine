package nl.hsleiden.engine;

import nl.hsleiden.game.Element;
import nl.hsleiden.game.Game;
import nl.hsleiden.game.Level;
import nl.hsleiden.game.Tile;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Deze class zorgd voor het laden van de game
 * */
public class TextFileGameLoader implements GameLoader {
    private HashMap<Integer, Class<? extends Tile>> tileMap;

    private HashMap<Integer, Class<? extends Element>> elementMap;

    private HashMap<Integer, String> levelTilesPaths;

    private HashMap<Integer, String> levelElementsPaths;

    private int tileSize;

    public TextFileGameLoader(int tileSize) {
        this.tileSize = tileSize;
        this.levelTilesPaths = new HashMap<>();
        this.levelElementsPaths = new HashMap<>();
    }

    public TextFileGameLoader() {
        this.tileSize = 80;
        this.levelTilesPaths = new HashMap<>();
        this.levelElementsPaths = new HashMap<>();
    }

    /**
     * Deze methode zorgd ervoor dat het spel geladen wordt.
     *
     * @return de game
     * */
    @Override
    public Game load() {
        ArrayList<Level> levels = new ArrayList<>();
        int numberOfLevels = levelTilesPaths.size();

        for (int levelNumber = 1; levelNumber <= numberOfLevels; levelNumber++) {
            InputStream tilesData = getLevelTilesData(levelNumber);
            InputStream elementsData = getLevelElementsData(levelNumber);
            Level level = new Level();
            loadTilesInLevel(tilesData, level);
            loadElementsInLevel(elementsData, level);
            levels.add(level);
        }

        Game game = new Game();
        game.setLevels(levels);

        return game;
    }

    /**
     * Hiermee voeg je een level toe aan het spel.
     * */
    public void addLevel(int level, String levelTilesPath, String levelElementsPath) {
        if (levelExists(level)) return;
        levelTilesPaths.put(level, levelTilesPath);
        levelElementsPaths.put(level, levelElementsPath);
    }

    /**
     * Hier kun je een configuratie voor een tile toevoegen.
     * Deze is dan te herkennen aan een bepaald nummer.
     *
     * @param tileMap een HashMap met een configuratie voor een tile.
     * Bijvoorbeeld: 1,GrassTile
     * */
    public void addTileConfiguration(HashMap<Integer, Class<? extends Tile>> tileMap) {
        this.tileMap = tileMap;
    }

    /**
     * Hier kun je een configuratie voor een element toevoegen.
     * Deze is dan te herkennen aan een bepaald nummer.
     *
     * @param elementMap een HashMap met een configuratie voor een element.
     * Bijvoorbeeld: 1,Character
     * */
    public void addElementsConfiguration(HashMap<Integer, Class<? extends Element>> elementMap) {
        this.elementMap = elementMap;
    }

    private Level loadTilesInLevel(InputStream stream, Level level) {
        Scanner scanner = new Scanner(stream);
        int levelHeight = scanner.nextInt();
        int levelWidth = scanner.nextInt();
        Tile[][] tiles = new Tile[levelHeight][levelWidth];

        for (int y = 0; y < levelHeight; y++) {
            for (int x = 0; x < levelWidth; x++) {
                try {
                    int id = scanner.nextInt();
                    if (tileMap.get(id) == null) continue;
                    Tile tile = tileMap.get(id).newInstance();
                    tile.setX(x*tileSize);
                    tile.setY(y*tileSize);
                    tiles[y][x] = tile;
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        level.setTiles(tiles);

        return level;
    }


    private void loadElementsInLevel(InputStream stream, Level level) {
        ArrayList<Element> elements = new ArrayList<>();
        Scanner scanner = new Scanner(stream);
        int levelHeight = scanner.nextInt();
        int levelWidth = scanner.nextInt();
        for (int y = 0; y < levelHeight; y++) {
            for (int x = 0; x < levelWidth; x++) {
                try {
                    int id = scanner.nextInt();
                    if (elementMap.get(id) == null) continue;
                    Element element = elementMap.get(id).newInstance();
                    element.setX(x*tileSize);
                    element.setY(y*tileSize);
                    elements.add(element);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        level.setElements(elements);
    }

    private InputStream getLevelElementsData(int level) {
        String filePath = levelElementsPaths.get(level);
        return readFile(filePath);
    }

    private InputStream getLevelTilesData(int level) {
        System.out.println(new File(".").getAbsolutePath());
        String filePath = levelTilesPaths.get(level);
        System.out.println(filePath);
        return readFile(filePath);
    }

    private InputStream readFile(String filePath){
        return this.getClass().getResourceAsStream(filePath);
    }


    private boolean levelExists(int level) {
        return levelElementsPaths.get(level) != null && levelTilesPaths.get(level) != null;
    }
}
