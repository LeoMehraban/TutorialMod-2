package net.leomeh.tutorialmod.entity.ai;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.item.ItemEntity;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.util.BrainUtils;
import org.lwjgl.system.MemoryUtil;

import java.util.List;
import java.util.Map;



public class GoToItem extends ExtendedBehaviour {

    /**
     * Override this for custom behaviour implementations. This is a safe endpoint
     * for behaviours so that all required auto-handling is safely contained without
     * super calls.<br>
     * This is called when the behaviour is to start. Set up any instance variables
     * needed or perform the required actions.<br>
     * By this stage any memory requirements set in
     * {@link ExtendedBehaviour#getMemoryRequirements()} are true, so any memories
     * paired with {@link MemoryStatus#VALUE_PRESENT} are safe to retrieve.
     *
     * @param entity The entity being handled (I.E. the owner of the brain)
     */
    @Override
    protected void start(LivingEntity entity) {
        ItemEntity itemEntity = BrainUtils.getMemory(entity, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM);
        if(itemEntity != null && !BrainUtils.hasMemory(entity, MemoryModuleType.WALK_TARGET)) {
            WalkTarget walkTarget = new WalkTarget(new BlockPos(new Vec3i((int) itemEntity.getPosition(1f).x,(int) itemEntity.getPosition(1f).y,(int) itemEntity.getPosition(1f).z)), 1, 1);
            BrainUtils.setMemory(entity, MemoryModuleType.WALK_TARGET, walkTarget);
        }
    }



    /**
     * The list of memory requirements this task has prior to starting. This
     * outlines the approximate state the brain should be in, in order to allow this
     * behaviour to run. <br>
     * Bonus points if it's a statically-initialised list.
     *
     * @return The {@link List} of {@link MemoryModuleType Memories} and their
     * associated required {@link MemoryStatus status}
     */
    @Override
    protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
        return List.of(Pair.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), Pair.of(MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryStatus.VALUE_PRESENT));
    }
}
