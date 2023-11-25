package fr.leod1.qbprojectmanager.cmd;

import fr.leod1.qbprojectmanager.directories.FilesHandler;
import fr.leod1.qbprojectmanager.directories.directorie.Directories;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static fr.leod1.qbprojectmanager.QBProjetctManager.MAINQB;

public class Qbl implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sdr, Command command, String label, String[] args) {
        if(sdr instanceof Player){
            Player pl = (Player) sdr;
            if (!Arrays.stream(args).toList().isEmpty()  && args[0].equalsIgnoreCase("save")){
                MAINQB.filesmanager.doHardSave();
                pl.sendMessage("HardSave done !");
            } else {
                MAINQB.linkedPlayerInventory.get(pl).OpenDefaultInf("QBPM",pl);
            }
        }
        return true;
    }
}
