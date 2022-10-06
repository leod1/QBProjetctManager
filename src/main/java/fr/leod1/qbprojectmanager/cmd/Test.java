package fr.leod1.qbprojectmanager.cmd;

import fr.leod1.qbprojectmanager.GUIInterface.PlayerinvManager;
import fr.leod1.qbprojectmanager.QBProjetctManager;
import fr.leod1.qbprojectmanager.directories.directorie.Directories;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import fr.leod1.qbprojectmanager.directories.FilesHandler;

import static fr.leod1.qbprojectmanager.QBProjetctManager.MAINQB;

public class Test implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sdr, Command command, String label, String[] args) {
        if(sdr instanceof Player){
            Player pl = (Player) sdr;
            if (args[0].equalsIgnoreCase("addDir")){
                MAINQB.filesmanager.addDirectory(args[1], args[2],pl.getItemInHand());
            }
            if (args[0].equalsIgnoreCase("addproj")){
                MAINQB.filesmanager.addProject(args[1], args[2]);
            }
            if (args[0].equalsIgnoreCase("checkRoot")){
                for (FilesHandler i: MAINQB.filesmanager.firstdir.getContenante()) {
                    if(i instanceof Directories){
                        pl.sendMessage("§4" +i.getName());
                    } else {
                        pl.sendMessage(i.getName());
                    }
                }
            }
            if (args[0].equalsIgnoreCase("checkIn")){
                Directories file = (Directories) MAINQB.filesmanager.getFile(args[1]);
                for (FilesHandler i: file.getContenante()) {
                    pl.sendMessage("---");
                    pl.sendMessage(i.getName());
                }
            }
            if (args[0].equalsIgnoreCase("inv")){
                MAINQB.linkedPlayerInventory.get(pl).OpenDefaultInf("QBPM",pl);
            }
            if (args[0].equalsIgnoreCase("debugr")){
                pl.sendMessage(MAINQB.filesmanager.firstdir.debugContenantes());
            }
        }
        return true;
    }
}
