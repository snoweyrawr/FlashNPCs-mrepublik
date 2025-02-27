package flash.npcmod.init;

import flash.npcmod.Main;
import flash.npcmod.item.BehaviorEditorItem;
import flash.npcmod.item.NpcEditorItem;
import flash.npcmod.item.NpcSaveToolItem;
import flash.npcmod.item.QuestEditorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

  public static final RegistryObject<Item> NPC_EDITOR = ITEMS.register("npc_editor",
          NpcEditorItem::new
  );

  public static final RegistryObject<Item> BEHAVIOR_EDITOR = ITEMS.register("behavior_editor",
          BehaviorEditorItem::new
  );

  public static final RegistryObject<Item> QUEST_EDITOR = ITEMS.register("quest_editor",
          QuestEditorItem::new
  );

  public static final RegistryObject<Item> NPC_SAVE_TOOL = ITEMS.register("npc_save_tool",
          NpcSaveToolItem::new
  );

}
