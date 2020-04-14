package nl.hsleiden.game.engine.utilities;

public class ARGBColor {
    public static final ARGBColor BLACK = new ARGBColor(1.0,0,0,0);
    public static final ARGBColor WHITE = new ARGBColor(1.0,255,255,255);
    public static final ARGBColor RED = new ARGBColor(1.0,255,0,0);
    private double alpha;
    private int red;
    private int green;
    private int blue;

    public ARGBColor(double alpha, int red, int green, int blue) {
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public double getAlpha() {
        return alpha;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}
