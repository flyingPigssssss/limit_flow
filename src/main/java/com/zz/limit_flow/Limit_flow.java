package com.zz.limit_flow;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.data.property.block.MatterProperty;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.world.LocatableBlock;
import java.util.Optional;

@Plugin(
        id = "limit_flow",
        name = "Limit_flow",
        description = "限制流水_邮箱:1403676227@qq.com",
        authors = {
                "flyingPigs"
        }
)
public class Limit_flow {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("===============================");
        logger.info("=======限制水流动插件启动======");
        logger.info("===============================");
    }

    @Listener
    public void stopLiquidFlow(ChangeBlockEvent.Place event, @Root LocatableBlock block) {
        for (Transaction<BlockSnapshot> trans : event.getTransactions()) {
            Optional<MatterProperty> matter = trans.getFinal().getState().getProperty(MatterProperty.class);
            if (matter.isPresent() && matter.get().getValue() == MatterProperty.Matter.LIQUID) {
                event.setCancelled(true);
            }
        }
    }
}
