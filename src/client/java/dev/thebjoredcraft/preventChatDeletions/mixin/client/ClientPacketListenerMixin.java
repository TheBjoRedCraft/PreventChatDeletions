package dev.thebjoredcraft.preventChatDeletions.mixin.client;

import com.mojang.logging.LogUtils;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundDeleteChatPacket;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
  @Unique
  private static final Logger LOGGER = LogUtils.getLogger();

  @Inject(method = "handleDeleteChat", at = @At("HEAD"), cancellable = true)
  private void handleChatDeletion(ClientboundDeleteChatPacket packet, CallbackInfo callbackInfo) {
    callbackInfo.cancel();
    LOGGER.info("Prevent chat deletion packet from being processed: {}", packet.messageSignature().fullSignature());
  }
}
