package is;

import is.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(
        modid = IS.MODID,
        name = IS.NAME,
        version = IS.VERSION)
public class IS
{
    public static final String MODID = "is";
    public static final String NAME = "Item Showing";
    public static final String VERSION = "1.4.2";

    @SidedProxy(clientSide = "is.proxy.ClientProxy", serverSide = "is.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(value=IS.MODID)
    public static IS instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);

        proxy.registerEventHandlers();
        PacketHandler.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
        proxy.init();
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}