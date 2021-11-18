package ua.darkphantom1337.coinsapi.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import ua.darkphantom1337.coinsapi.Main;
import ua.darkphantom1337.coinsapi.entitys.DarkPlayer;

public class CoinsPlaceholder extends PlaceholderExpansion {

    private Main plugin;

    public CoinsPlaceholder(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier(){
        return "coins";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }
    
    @Override
    public String onPlaceholderRequest(Player player, String identifier){
    	if (identifier.equals("current") && player != null && player.getName() != null && !player.getName().equals("")){
    		return ""+Main.getInstance().getFormattedBalance(new DarkPlayer(player.getName()).getBalance());
    	}
    	return null;
    }
   
}

