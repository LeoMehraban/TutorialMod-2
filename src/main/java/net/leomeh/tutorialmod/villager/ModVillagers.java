package net.leomeh.tutorialmod.villager;
//
//import com.google.common.collect.ImmutableSet;
//import net.leomeh.tutorialmod.TutorialMod;
//import net.leomeh.tutorialmod.block.ModBlocks;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.world.entity.ai.village.poi.PoiType;
//import net.minecraft.world.entity.ai.village.poi.PoiTypes;
//import net.minecraft.world.entity.npc.VillagerProfession;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.Set;
//
//public class ModVillagers {
//    public static final DeferredRegister<PoiType> POI_TYPES =
//            DeferredRegister.create(ForgeRegistries.POI_TYPES, TutorialMod.MOD_ID);
//    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
//            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, TutorialMod.MOD_ID);
//    public static final RegistryObject<PoiType> WANDFORGER_POI = POI_TYPES.register("wandforger_poi", () -> new PoiType(ImmutableSet.copyOf(ModBlocks.WANDFORGER_TABLE.get().getStateDefinition().getPossibleStates()), 1, 1));
//
//    public static final RegistryObject<VillagerProfession> WANDFORGER = VILLAGER_PROFESSIONS.register("wandforger", () -> new VillagerProfession("wandforger", x -> x.get() == WANDFORGER_POI.get(), x -> x.get() == WANDFORGER_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));
//
//    public  static void registerPOIs(){
//        try{
//            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, WANDFORGER_POI);
//        }catch (InvocationTargetException | IllegalAccessException exception){
//            exception.printStackTrace();
//        }
//    }
//    public static void register(IEventBus eventBus){
//        POI_TYPES.register(eventBus);
//        VILLAGER_PROFESSIONS.register(eventBus);
//    }
//}
