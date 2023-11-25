package fr.leod1.qbprojectmanager.GUIInterface;

import fr.leod1.qbprojectmanager.directories.FilesHandler;
import fr.leod1.qbprojectmanager.directories.directorie.Directories;
import fr.leod1.qbprojectmanager.directories.projects.Projects;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

import static fr.leod1.qbprojectmanager.QBProjetctManager.MAINQB;

public class PlayerinvManager implements Listener {
    public Inventory invofpl;
    public Player pl;

    public PlayerinvManager(Player pl) {
        this.pl = pl;
    }

    public PlayerinvManager() {
    }

    public void OpenDefaultInf(String path, Player pl){
        Directories dirs = (Directories) MAINQB.filesmanager.getFile(path);
        invofpl = Bukkit.createInventory(null, 54, "§cPath: §1"+path);
        int i = 0;
        for (FilesHandler dir: dirs.getContenante()) {
            ItemStack xdx = dir.getView().clone();
            ItemMeta xd = xdx.getItemMeta();
            if (dir instanceof Directories){
                xd.setDisplayName("§8[§7Dossier§8] §f" + dir.getName());
                xd.setLore(Arrays.asList(" ", "§8[§7GAUCHE§8] §fRentré dans le dossier", "§8[§7MOLETTE§8] §fSupprimer le dossier (récursive)"));
            } else {
                xd.setDisplayName("§8[§7Projet§8] §f" + dir.getName());
                xd.setLore(Arrays.asList(" ","§8[§7GAUCHE§8] §fTéléporter au projet", "§8[§7MOLETTE§8] §fSupprimer le fichier"));

            }
            xdx.setItemMeta(xd);
            invofpl.setItem(i, xdx);
            i++;
        }
        //filtre
        ItemStack Filtre = new ItemStack(Material.HOPPER,1);
        ItemMeta mFiltre = Filtre.getItemMeta();
        mFiltre.setDisplayName("§6Filtre");
        Filtre.setItemMeta(mFiltre);
        //filtre
        //Crea dossier
        ItemStack CreaDossier = new ItemStack(Material.BOOK,1);
        ItemMeta mCreaDossier = CreaDossier.getItemMeta();
        mCreaDossier.setDisplayName("§aCréation d'un dossier");
        CreaDossier.setItemMeta(mCreaDossier);
        //Crea dossier
        //RETOUR
        ItemStack Back = new ItemStack(Material.BARRIER,1);
        ItemMeta mb = Back.getItemMeta();
        mb.setDisplayName("§4Retour");
        Back.setItemMeta(mb);
        //RETOUR
        //PAGE SUIVANTE
        ItemStack PS = new ItemStack(Material.ARROW,1);
        ItemMeta mPS = PS.getItemMeta();
        mPS.setDisplayName("§6Page suivante");
        PS.setItemMeta(mPS);
        //PAGE SUIVANTE
        //PAGE PRECEDENTE
        ItemStack PP = new ItemStack(Material.ARROW,1);
        ItemMeta mPP = PP.getItemMeta();
        mPP.setDisplayName("§6Page precedente");
        PP.setItemMeta(mPP);

        //ADD PROJ
        ItemStack CreaProjet = new ItemStack(Material.PAPER,1);
        ItemMeta mCreaProjet = CreaProjet.getItemMeta();
        mCreaProjet.setDisplayName("§aCréation d'un projet");
        CreaProjet.setItemMeta(mCreaProjet);
        //PAGE PRECEDENTE
        invofpl.setItem(47,Back);
        invofpl.setItem(45,PP);
        invofpl.setItem(46,PS);
        invofpl.setItem(48,Filtre);
        invofpl.setItem(52,CreaProjet);
        invofpl.setItem(53,CreaDossier);
        /*for (int j = 36; j < 45; j++) {
            invofpl.setItem(j,deco);
        }*/

        pl.openInventory(invofpl);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null && e.getView().getTitle().contains("§cPath: §1") && e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
            e.setCancelled(true);
            String nameItem = e.getCurrentItem().getItemMeta().getDisplayName();
            String path = e.getView().getTitle().replace("§cPath: §1", "");
            //Tools
            switch (nameItem){

                case "§8DECO":
                    e.getWhoClicked().sendMessage("C'est bon " + nameItem);
                    break;

                case "§6Filtre":
                    break;
                case "§aCréation d'un projet":
                    if (e.getWhoClicked().getItemInHand().getType()== Material.AIR){
                        e.getWhoClicked().sendMessage("§8[§7QBL§8] §fIl faut avoir un item dans la main ! (Ce sera l'item dans le menu)");
                        break;
                    }
                    MAINQB.linkedPlayerData.put((Player) e.getWhoClicked(),new QBDataPlayer(path, "nameFil", e.getWhoClicked().getItemInHand()));
                    e.getWhoClicked().sendMessage("§8[§7QBL§8] §fÉcrit le nom du projet dans le chat !");
                    e.getWhoClicked().closeInventory();
                    break;

                case "§aCréation d'un dossier":
                    if (e.getWhoClicked().getItemInHand().getType()== Material.AIR){
                        e.getWhoClicked().sendMessage("§8[§7QBL§8] §fIl faut avoir un item dans la main ! (Ce sera l'item dans le menu)");
                        break;
                    }
                    if (e.getWhoClicked().hasPermission("qbl.create")){
                        MAINQB.linkedPlayerData.put((Player) e.getWhoClicked(),new QBDataPlayer(path, "nameDir", e.getWhoClicked().getItemInHand()));
                        e.getWhoClicked().sendMessage("§8[§7QBL§8] §fÉcrit le nom du dossier dans le chat !");
                        e.getWhoClicked().closeInventory();
                        break;
                    }
                    break;


                case "§6Page suivante":
                    break;
                case "§6Page precedente":
                    break;


                case "§4Retour":
                    if (!path.equalsIgnoreCase("QBPM")){
                        String pathU = String.join("/", Arrays.copyOf(path.split("/"),path.split("/").length-1));
                        OpenDefaultInf(pathU, Bukkit.getPlayer(e.getWhoClicked().getName()));
                    }
                    break;
                default:
                    switch (e.getClick()){
                        case MIDDLE:
                            if (e.getWhoClicked().hasPermission("qbl.delete")){
                                MAINQB.filesmanager.remove(e.getCurrentItem().getItemMeta().getDisplayName().replace("§8[§7Dossier§8] §f", "").replace("§8[§7Projet§8] §f",""),path);
                                OpenDefaultInf(path, Bukkit.getPlayer(e.getWhoClicked().getName()));
                                e.getWhoClicked().sendMessage("§8[§7QBL§8] §fProjet/Dossier §7"+ nameItem.replace("§8[§7Dossier§8] §f", "").replace("§8[§7Projet§8] §f","") + "§f bien supprimé");
                                break;
                            }
                            break;
                        default:
                            if (MAINQB.filesmanager.pathExist(path + "/" + nameItem.replace("§8[§7Dossier§8] §f", "").replace("§8[§7Projet§8] §f", ""))) {
                                FilesHandler file = MAINQB.filesmanager.getFile(path + "/" + nameItem.replace("§8[§7Dossier§8] §f", "").replace("§8[§7Projet§8] §f", ""));
                                if (file instanceof Directories) {
                                    OpenDefaultInf(path + "/" + nameItem.replace("§8[§7Dossier§8] §f", "").replace("§8[§7Projet§8] §f", ""), Bukkit.getPlayer(e.getWhoClicked().getName()));
                                } else {
                                    e.getWhoClicked().sendMessage("§8[§7QBL§8] Téléportation au projet §7" + nameItem.replace("§8[§7Dossier§8] §f", "").replace("§8[§7Projet§8] §f", ""));
                                    Bukkit.getPlayer(e.getWhoClicked().getName()).teleport(((Projects) file).getLoc()); //.teleport((Projects file).getLoc())
                                    break;
                                }
                            }
                            break;

                    }
                    break;
            }
        }
    }
}
