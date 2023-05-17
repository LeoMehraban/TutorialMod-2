package net.leomeh.tutorialmod.item.custom.spit_gun;

import net.leomeh.tutorialmod.entity.LlamamanSpit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SpitGun extends Item {
    public SpitGun(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    private void spit(BlockPos pTarget, Player shooter) {
        GunSpit llamaspit = new GunSpit(shooter.level, shooter);
        double d0 = pTarget.getX() - shooter.getX();
        double d1 = pTarget.getY() - llamaspit.getY();
        double d2 = pTarget.getZ() - shooter.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2) * (double) 0.2F;
        llamaspit.shoot(d0, d1 + d3, d2, 1.5F, 10.0F);

        shooter.level.addFreshEntity(llamaspit);

    }
}
