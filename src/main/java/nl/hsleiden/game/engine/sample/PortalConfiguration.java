package nl.hsleiden.game.engine.sample;

import nl.hsleiden.game.engine.abstractions.Configuration;
import nl.hsleiden.game.engine.physics.Dimension;

public class PortalConfiguration implements Configuration<PortalGravity> {
    @Override
    public Dimension getScreenSize() {
        return new Dimension(1920, 1080);
    }

    @Override
    public String getTitle() {
        return "Sample | Portal";
    }

    @Override
    public Class<PortalGravity> getGameType() {
        return PortalGravity.class;
    }
}
