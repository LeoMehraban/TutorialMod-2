package net.leomeh.tutorialmod.entity;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.leomeh.tutorialmod.entity.util.TradingMob;
import net.leomeh.tutorialmod.item.ModArmorMaterials;
import net.leomeh.tutorialmod.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableRangedAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.CustomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;


public class LlamamanEntity extends PathfinderMob implements RangedAttackMob, IAnimatable, SmartBrainOwner<LlamamanEntity>, TradingMob {


    public List<LivingEntity> getNearbyLivingEntities(ServerLevel pLevel, LivingEntity pEntity){
        AABB aabb = pEntity.getBoundingBox().inflate((double)this.radiusXZ(), (double)this.radiusY(), (double)this.radiusXZ());
        List<LivingEntity> list = pLevel.getEntitiesOfClass(LivingEntity.class, aabb, (p_26717_) -> {
            return p_26717_ != pEntity && p_26717_.isAlive();
        });
        list.sort(Comparator.comparingDouble(pEntity::distanceToSqr));
        return  list;
    }

    protected int radiusXZ() {
        return 16;
    }

    protected int radiusY() {
        return 16;
    }

    public static Vec3 getRandomNearbyPos(LlamamanEntity pLlamaman) {
        Vec3 vec3 = LandRandomPos.getPos(pLlamaman, 4, 2);
        return vec3 == null ? pLlamaman.position() : vec3;
    }

    public static void throwItemsTowardRandomPos(LlamamanEntity pLlamaman, ItemStack pStacks) {
        throwItemsTowardPos(pLlamaman, pStacks, getRandomNearbyPos(pLlamaman));
    }

    public static void throwItem(LlamamanEntity pLlamaman, ItemStack pStack) {
        Optional<Player> optional = pLlamaman.getBrain().getMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER);
        if (optional.isPresent()) {
            throwItemsTowardPlayer(pLlamaman, optional.get(), pStack);
        } else {
            throwItemsTowardRandomPos(pLlamaman, pStack);
        }

    }

    public static void throwItemsTowardPlayer(LlamamanEntity pLlamaman, Player pPlayer, ItemStack pStack) {
        throwItemsTowardPos(pLlamaman, pStack, pPlayer.position());
    }

    public static void throwItemsTowardPos(LlamamanEntity pLlamaman,  ItemStack pStack, Vec3 pPos) {

                BehaviorUtils.throwItem(pLlamaman, pStack, pPos.add(0.0D, 1.0D, 0.0D));



    }

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);


    public LlamamanEntity(EntityType<LlamamanEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0f)
                .add(Attributes.ATTACK_SPEED, 0.4f)
                .add(Attributes.MOVEMENT_SPEED, 0.2f).build();
    }



    private boolean didSpit = false;
    public   boolean isPassive = false;

    private Player tradingPlayer;

//    @Override
//    protected void registerGoals() {
//        this.goalSelector.addGoal(1, new FloatGoal(this));
//        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 1D, 5f));
//        this.goalSelector.addGoal(3, new RangedAttackGoal(this, 1.25D, 40, 10.0F));
//        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
//        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
//        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
//        //this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, true));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, false));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
//        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Creeper.class, true));
//    }


    private void spit(LivingEntity pTarget) {
        LlamamanSpit llamaspit = new LlamamanSpit(this.level, this);
        double d0 = pTarget.getX() - this.getX();
        double d1 = pTarget.getY(0.3333333333333333D) - llamaspit.getY();
        double d2 = pTarget.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2) * (double) 0.2F;
        llamaspit.shoot(d0, d1 + d3, d2, 1.5F, 10.0F);
        if (!this.isSilent()) {
            this.level.playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundEvents.LLAMA_SPIT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
        }

        this.level.addFreshEntity(llamaspit);
        this.didSpit = true;
    }


    @Override
    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {
        this.spit(pTarget);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("Walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("Idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public List<ExtendedSensor<LlamamanEntity>> getSensors() {
        return ObjectArrayList.of(
                new NearbyLivingEntitySensor().setPredicate((target, entity) ->
                                target instanceof Player ||
                                        target instanceof IronGolem ),

                new HurtBySensor<>()                // This tracks the last damage source and attacker
        );
    }


    public Brain<?> getBrain() {
        return this.brain;
    }



    @Override
    public BrainActivityGroup<LlamamanEntity> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new LookAtTarget<>(),
                new MoveToWalkTarget<>()
        );
    }

    @Override
    public BrainActivityGroup<LlamamanEntity> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<LlamamanEntity>(
                        new TargetOrRetaliate<>(),
                        new SetPlayerLookTarget<>(),
                        new SetRandomLookTarget<>()
                ),
                new OneRandomBehaviour<>(
                        new SetRandomWalkTarget<>(),
                        new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60)))
                );
    }

    @Override
    public BrainActivityGroup<LlamamanEntity> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>().invalidateIf(this::targetCheck),
                new SetWalkTargetToAttackTarget<>(),
                new AnimatableRangedAttack<>(20));
    }

    boolean targetCheck(LivingEntity entity, LivingEntity target){
        return ((target instanceof Player pl
                && (pl.getAbilities().invulnerable
                || targetHasLlamaLeather(pl)))
                || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2));
    }

     public static boolean targetHasLlamaLeather(Player player){
        AtomicBoolean hasLL = new AtomicBoolean(false);
        player.getArmorSlots().forEach(itemStack -> {
                  if(itemStack.getItem() instanceof  ArmorItem armorItem){
                      hasLL.set(armorItem.getMaterial() == ModArmorMaterials.LLAMA_LEATHER);
                  }
        });
        return  hasLL.get();
    }



    @Override
    protected Brain.Provider<?> brainProvider() {
        return new SmartBrainProvider<>(this);
    }

    @Override
    protected void customServerAiStep() {
        tickBrain(this);
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return tradingPlayer;
    }

    @Override
    public void setTradingPlayer(@Nullable Player player) {
        tradingPlayer = player;
    }

    @Override
    public Item getTradingItem() {
        return Items.COPPER_INGOT;
    }


    @Override
    public ResourceLocation getTradeLootTable() {
        return null;
    }
}


