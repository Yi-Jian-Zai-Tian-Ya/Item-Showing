package is.client;

import is.ItemShowing;
import is.PacketHandler;
import is.proxy.ClientProxy;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = "is", value = Side.CLIENT)
public class ClientEventHandler
{
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event)
    {
        if (event.side == Side.CLIENT && event.phase == TickEvent.Phase.START)
        {
            if (ClientProxy.KEY_ITEM_SHOWING.isPressed())
            {
                PacketHandler.INSTANCE.sendToServer(new ItemShowing());
            }
        }
    }
}