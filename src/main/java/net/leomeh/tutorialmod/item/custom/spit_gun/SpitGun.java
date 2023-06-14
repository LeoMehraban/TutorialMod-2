package net.leomeh.tutorialmod.item.custom.spit_gun;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SpitGun extends Item {
    public SpitGun(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        spit(pPlayer);
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    private void spit(Player shooter) {
        if(!shooter.level.isClientSide) {
            GunSpit llamaspit = new GunSpit(shooter.level, shooter);
            llamaspit.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0.0F, 3.0F, 1.0F);
            shooter.level.addFreshEntity(llamaspit);
        }

    }


}
