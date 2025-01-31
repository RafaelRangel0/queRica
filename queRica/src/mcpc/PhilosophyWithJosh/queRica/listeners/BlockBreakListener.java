package mcpc.PhilosophyWithJosh.queRica.listeners;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import mcpc.PhilosophyWithJosh.queRica.Main;
import mcpc.PhilosophyWithJosh.queRica.commands.ActivatePluginCommand;

public class BlockBreakListener implements Listener
{
	private Main plugin;
	ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
	public BlockBreakListener(Main plugin)
	{
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void playerBrokeBlock(BlockBreakEvent bbe)
	{
		
		if(ActivatePluginCommand.commandIsActive())
			drops.clear();
			bbe.setCancelled(true);
			bbe.getPlayer().sendMessage("Plugin Works");
		
		
			Block brokenBlock = bbe.getBlock();
			Collection<ItemStack> itemList = brokenBlock.getDrops();
			Location blockLocation = brokenBlock.getLocation();
			
			
			for(ItemStack item: itemList) 
			{
				int randomNumber = (int) (Math.random() * 25);
				Material dropped = item.getType();
				
				drops.add(new ItemStack (dropped,randomNumber));
				//ItemStack desiredDrops = new ItemStack(dropped,randomNumber);
			}
			
			brokenBlock.setType(Material.AIR);
			
			for (int i = 0; i < drops.size(); i++) 
			{
				brokenBlock.getWorld().dropItemNaturally(blockLocation, drops.get(i));
			}
	}
}
