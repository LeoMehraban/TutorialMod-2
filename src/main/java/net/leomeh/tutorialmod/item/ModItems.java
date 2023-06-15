package net.leomeh.tutorialmod.item;

import net.leomeh.tutorialmod.TutorialMod;
import net.leomeh.tutorialmod.block.ModBlocks;
import net.leomeh.tutorialmod.entity.ModEntityTypes;
import net.leomeh.tutorialmod.item.custom.*;
import net.leomeh.tutorialmod.item.custom.spit_gun.SpitGun;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS=DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item>   LIVINGINGOT = ITEMS.register("livingingot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item>   LLAMA_LEATHER = ITEMS.register("llama_leather", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item>   DEATHSTEEL_INGOT = ITEMS.register("deathsteel_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item>   BONEBINDING = ITEMS.register("bone_binding", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item>   LIVINGSTONE_PEBBLE = ITEMS.register("livingstone_pebble", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item>   STONEPEBBLE = ITEMS.register("stone_pebble", () -> new StonePebble(ModBlocks.STONECROP.get(), new Item.Properties()));

    public static final RegistryObject<Item>   LIVINGPICKAXE = ITEMS.register("living_pickaxe", () -> new PickaxeItem(ModTiers.LIVING,1,3,new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>   LIVINGSHOVEL = ITEMS.register("living_shovel", () -> new ShovelItem(ModTiers.LIVING,1,3,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item>   LIVINGAXE = ITEMS.register("living_axe", () -> new AxeItem(ModTiers.LIVING,3,3,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item>   LIVINGHOE = ITEMS.register("living_hoe", () -> new HoeItem(ModTiers.LIVING,1,3,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item>   CORE_GEM = ITEMS.register("core_gem", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item>   INERT_CORE_GEM = ITEMS.register("inert_core_gem", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item>   LIVINGSWORD = ITEMS.register("living_sword", () -> new SwordItem(ModTiers.LIVING,2,3,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item>   DOGSUMMONWAND = ITEMS.register("summonwand_dog", () -> new DogSummonWand(new Item.Properties().stacksTo(1).durability(200)));

    public static final RegistryObject<Item>   ALLAYSUMMONWAND = ITEMS.register("summonwand_allay", () -> new AllaySummonWand(new Item.Properties().stacksTo(1).durability(200)));
    public static final RegistryObject<Item>   LIVINGHELMET = ITEMS.register("living_helmet", () -> new LivingArmorItem(ModArmorMaterials.LIVING, ArmorItem.Type.HELMET,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item>   LLAMA_LEATHER_HELMET = ITEMS.register("llama_leather_helmet", () -> new ArmorItem(ModArmorMaterials.LLAMA_LEATHER, ArmorItem.Type.HELMET,new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>   LIVINGCHESTPLATE = ITEMS.register("living_chestplate", () -> new LivingArmorItem(ModArmorMaterials.LIVING, ArmorItem.Type.CHESTPLATE,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item>   LIVINGLEGGINGS = ITEMS.register("living_leggings", () -> new LivingArmorItem(ModArmorMaterials.LIVING, ArmorItem.Type.LEGGINGS,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item>   LIVINGBOOTS = ITEMS.register("living_boots", () -> new LivingArmorItem(ModArmorMaterials.LIVING, ArmorItem.Type.BOOTS,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item>   DEATHSTEELPICKAXE = ITEMS.register("deathsteel_pickaxe", () -> new PickaxeItem(ModTiers.DEATHSTEEL,1,3,new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>   DEATHSTEELSHOVEL = ITEMS.register("deathsteel_shovel", () -> new ShovelItem(ModTiers.DEATHSTEEL,1,3,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item>   DEATHSTEELAXE = ITEMS.register("deathsteel_axe", () -> new AxeItem(ModTiers.DEATHSTEEL,3,3,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item>   DEATHSTEELHOE = ITEMS.register("deathsteel_hoe", () -> new HoeItem(ModTiers.DEATHSTEEL,1,3,new Item.Properties().stacksTo(1)));


    public static final RegistryObject<Item>   DEATHSTEEL_SWORD = ITEMS.register("deathsteel_sword", () -> new DeathSteelSword(ModTiers.DEATHSTEEL,2,3,new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>   CATSUMMONWAND = ITEMS.register("summonwand_cat", () -> new CatSummonWand(new Item.Properties().stacksTo(1).durability(200)));

    public static final RegistryObject<Item>   DEATHSTEELHELMET = ITEMS.register("deathsteel_helmet", () -> new ArmorItem(ModArmorMaterials.DEATHSTEEL, ArmorItem.Type.HELMET,new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>   DEATHSTEELCHESTPLATE = ITEMS.register("deathsteel_chestplate", () -> new ArmorItem(ModArmorMaterials.DEATHSTEEL, ArmorItem.Type.CHESTPLATE,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item>   DEATHSTEELLEGGINGS = ITEMS.register("deathsteel_leggings", () -> new ArmorItem(ModArmorMaterials.DEATHSTEEL, ArmorItem.Type.LEGGINGS,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item>   DEATHSTEELBOOTS = ITEMS.register("deathsteel_boots", () -> new ArmorItem(ModArmorMaterials.DEATHSTEEL, ArmorItem.Type.BOOTS,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> CHEESE_BALL = ITEMS.register("cheese_ball" , () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationMod(3.1f)
            .build())));

    public static final RegistryObject<Item>   SPIT_GUN = ITEMS.register("spit_gun", () -> new SpitGun(new Item.Properties().stacksTo(1).durability(200)));


    public static final RegistryObject<Item> SPAWN_EGG_LLAMAMAN = ITEMS.register("llamaman_spawn_egg", () -> new ForgeSpawnEggItem(() -> ModEntityTypes.CHOMPER.get(),0xffffff , 0xffffe6, new Item.Properties()
    ));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }




}
