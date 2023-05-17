package net.leomeh.tutorialmod.block;

import net.leomeh.tutorialmod.TutorialMod;
import net.leomeh.tutorialmod.block.custom.*;
//import net.leomeh.tutorialmod.block.custom.WandForgerBlock;
import net.leomeh.tutorialmod.block.entity.WandForger;
import net.leomeh.tutorialmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    public static  final RegistryObject<Block> LIVINGMETAL_BLOCK = registerBlock("livingmetal_block", () -> new LivingMetal(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(6f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_MISC);
    public static  final RegistryObject<Block> LIVINGSTONE = registerBlock("livingstone", () -> new LivingStone(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_MISC);

    public static  final RegistryObject<Block> CORE_GEM_BLOCK = registerBlock("core_gem_block", () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(6f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_MISC);

    public static  final RegistryObject<Block> CORE_GEM_ORE = registerBlock("core_gem_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_MISC);

    public static  final RegistryObject<Block> CORE_GEM_DEEPSLATE_ORE = registerBlock("deepslate_core_gem_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_MISC);

    public static  final RegistryObject<Block> WANDFORGER_TABLE = registerBlock("wand_forger_table", () -> new WandForgerBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f)), CreativeModeTab.TAB_MISC);

    public static  final RegistryObject<Block> DEATHSTEEL_BLOCK = registerBlock("deathsteel_block", () -> new DeathSteel(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(4f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_MISC);
//
//    public static  final RegistryObject<Block> MANAWOOD_LOG = registerBlock("manawood_log", () -> new ModFlammibleRotatedPillerBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(6f)), CreativeModeTab.TAB_MISC);
//
//    public static  final RegistryObject<Block> MANAWOOD_WOOD = registerBlock("manawood_wood", () -> new ModFlammibleRotatedPillerBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), CreativeModeTab.TAB_MISC);
//
//    public static  final RegistryObject<Block> MANAWOOD_LEAVES = registerBlock("manawood_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
//        @Override
//        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
//            return true;
//        }
//
//        @Override
//        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
//            return 30;
//        }
//
//        @Override
//        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
//            return 60;
//        }
//    }, CreativeModeTab.TAB_BUILDING_BLOCKS);
//
//    public static  final RegistryObject<Block> MANAWOOD_PLANKS = registerBlock("manawood_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS){
//        @Override
//        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
//            return true;
//        }
//
//        @Override
//        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
//            return 5;
//        }
//
//        @Override
//        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
//            return 20;
//        }
//    });

    //public static  final RegistryObject<Block> MANAWOOD_SAPLING = registerBlock("manawood_sapling", () -> new SaplingBlock( ,BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), CreativeModeTab.TAB_MISC);


    public static  final RegistryObject<Block> STONECROP = registerBlock("stone_crop", () -> new StoneCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)), CreativeModeTab.TAB_FOOD);
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
