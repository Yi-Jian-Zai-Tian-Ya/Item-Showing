package is;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(IS.MODID.toLowerCase());

    public static void init()
    {
        INSTANCE.registerMessage(ItemShowing.class, ItemShowing.class, 0, Side.SERVER);
    }
}