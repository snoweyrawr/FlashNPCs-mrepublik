package flash.npcmod.init;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import flash.npcmod.Main;
import flash.npcmod.commands.*;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

import java.util.function.Supplier;

import static net.minecraft.commands.Commands.literal;

public class CommandInit {

  public static BehaviorsCommand BEHAVIORS_COMMAND;
  public static DialoguesCommand DIALOGUES_COMMAND;
  public static DebugCommand DEBUG_COMMAND;
  public static FunctionsCommand FUNCTIONS_COMMAND;
  public static QuestsCommand QUESTS_COMMAND;
  public static ResetNPCCommand RESET_NPC_COMMAND;
  public static SetNPCDataCommand SET_NPC_DATA_COMMAND;
  public static SummonCommand SUMMON_COMMAND;

  public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher, Commands.CommandSelection env) {
    BEHAVIORS_COMMAND = registerCommand(BehaviorsCommand::new, dispatcher, env);
    DEBUG_COMMAND = registerCommand(DebugCommand::new, dispatcher, env);
    DIALOGUES_COMMAND = registerCommand(DialoguesCommand::new, dispatcher, env);
    FUNCTIONS_COMMAND = registerCommand(FunctionsCommand::new, dispatcher, env);
    QUESTS_COMMAND = registerCommand(QuestsCommand::new, dispatcher, env);
    RESET_NPC_COMMAND = registerCommand(ResetNPCCommand::new, dispatcher, env);
    SET_NPC_DATA_COMMAND = registerCommand(SetNPCDataCommand::new, dispatcher, env);
    SUMMON_COMMAND = registerCommand(SummonCommand::new, dispatcher, env);
  }

  public static <T extends Command> T registerCommand(Supplier<T> supplier, CommandDispatcher<CommandSourceStack> dispatcher, Commands.CommandSelection env) {
    T command = supplier.get();

    if (!command.isDedicatedServerOnly() || env == Commands.CommandSelection.DEDICATED || env == Commands.CommandSelection.ALL) {
      LiteralArgumentBuilder<CommandSourceStack> builder = literal(command.getName());
      builder.requires((sender) -> sender.hasPermission(command.getRequiredPermissionLevel()));
      command.build(builder);
      dispatcher.register(literal(Main.MODID).then(builder));
    }

    return command;
  }
}
