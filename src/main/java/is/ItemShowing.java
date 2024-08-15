package is;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.HashMap;
import java.util.Map;

public class ItemShowing implements IMessage, IMessageHandler<ItemShowing, IMessage>
{
    private static final long COOLDOWN_TIME = 3000;
    private static Map<String, Long> lastTriggerTime = new HashMap<>();

    public ItemShowing() { }

    @Override
    public void toBytes(ByteBuf buffer) { }

    @Override
    public void fromBytes(ByteBuf buffer) { }

    @Override
    public IMessage onMessage(ItemShowing message, MessageContext ctx)
    {
        IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
        mainThread.addScheduledTask(new Runnable()
        {
            public void run()
            {
                EntityPlayerMP player = ctx.getServerHandler().player;
                if (shouldTrigger(player)) sendMessage(player);
            }
        });
        return null;
    }

    private boolean shouldTrigger(EntityPlayerMP player)
    {
        if (player == null) return false;
        String playerName = player.getName();
        long currentTime = System.currentTimeMillis();
        if (!lastTriggerTime.containsKey(playerName) || currentTime - lastTriggerTime.get(playerName) >= COOLDOWN_TIME)
        {
            lastTriggerTime.put(playerName, currentTime);
            return true;
        }
        return false;
    }

    public void sendMessage(EntityPlayerMP player)
    {
        ItemStack item = player.getHeldItemMainhand();
        if (item.isEmpty()) return;
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        if (server == null) return;

        ITextComponent itemComponent = new TextComponentString(item.getDisplayName());
        ITextComponent hoverText = new TextComponentString(item.serializeNBT().toString());
        itemComponent.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, hoverText));

        server.getPlayerList().sendMessage(new TextComponentTranslation("message.show", player.getName(), itemComponent));
    }
}