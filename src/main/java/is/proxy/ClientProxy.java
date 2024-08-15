package is.proxy;

import is.client.ClientEventHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy
{
    public static final KeyBinding KEY_ITEM_SHOWING = new KeyBinding("keybind.item_showing", KeyConflictContext.IN_GAME, Keyboard.KEY_N, "key.categories.gameplay");

    @Override
    public void registerEventHandlers()
    {
        super.registerEventHandlers();

        ClientRegistry.registerKeyBinding(KEY_ITEM_SHOWING);

        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return super.getClientGuiElement(ID, player, world, x, y, z);
    }

    @Override
    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().world;
    }

    @Override
    public void init()
    {
        super.init();
    }

    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
    }

    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }
}