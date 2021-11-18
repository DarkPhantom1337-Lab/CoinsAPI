package ua.darkphantom1337.coinsapi;

import ua.darkphantom1337.coinsapi.entitys.DarkPlayer;

public class CoinsAPI {

    private static Main coinsAPIPlugin;

    public CoinsAPI(Main coinsAPI_plugin){
        coinsAPIPlugin = coinsAPI_plugin;
    }

    /**
     * @return CoinsAPI plugin
     * by DarkPhantom1337
     */
    public static Main getCoinsAPIPlugin() {
        return coinsAPIPlugin;
    }

    /**
     * @param playerName - player original name.
     * @return all functional class for player.
     * by DarkPhantom1337
     */
    public static DarkPlayer getCoinsAPIPlayer(String playerName) {
        return new DarkPlayer(playerName);
    }

}
