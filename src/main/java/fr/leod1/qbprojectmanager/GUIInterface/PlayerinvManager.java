package fr.leod1.qbprojectmanager.GUIInterface;

import fr.leod1.qbprojectmanager.directories.FilesHandler;
import fr.leod1.qbprojectmanager.directories.directorie.Directories;
import fr.leod1.qbprojectmanager.directories.projects.Projects;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
            invofpl.setItem(i, dir.getView());
            i++;
        }
        //filtre
        ItemStack Filtre = new ItemStack(Material.HOPPER,1);
        ItemMeta mFiltre = Filtre.getItemMeta();
        mFiltre.setDisplayName("§6Filtre");
        Filtre.setItemMeta(mFiltre);
        //filtre
        //Suppr fichier
        ItemStack SupprProjet = new ItemStack(Material.REDSTONE,1);
        ItemMeta mSupprProjet = SupprProjet.getItemMeta();
        mSupprProjet.setDisplayName("§4Suppretion d'un projet");
        SupprProjet.setItemMeta(mSupprProjet);
        //Suppr fichier
        //Suppr dossier
        ItemStack SupprDossier = new ItemStack(Material.REDSTONE_BLOCK,1);
        ItemMeta mSupprDossier = SupprDossier.getItemMeta();
        mSupprDossier.setDisplayName("§4Suppretion d'un dossier");
        SupprDossier.setItemMeta(mSupprDossier);
        //Suppr dossier
        //Crea projet
        /*ItemStack CreaProjet = new ItemStack(Material.LEGACY_PISTON_BASE,1);
        ItemMeta mCreaProjet = CreaProjet.getItemMeta();
        mCreaProjet.setDisplayName("§aCréation d'un projet");
        CreaProjet.setItemMeta(mCreaProjet);*/
        //Crea projet
        //Crea dossier
        ItemStack CreaDossier = new ItemStack(Material.BOOK,1);
        ItemMeta mCreaDossier = CreaDossier.getItemMeta();
        mCreaDossier.setDisplayName("§aCréation d'un dossier");
        CreaDossier.setItemMeta(mCreaDossier);
        //Crea dossier
        //DECORATION
        /*ItemStack deco = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
        ItemMeta mdeco = deco.getItemMeta();
        mdeco.setDisplayName("§8DECO");
        deco.setItemMeta(mdeco);*/
        //DECORATION
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
        mCreaProjet.setDisplayName("§aCréation d'un fichier");
        CreaProjet.setItemMeta(mCreaProjet);
        //PAGE PRECEDENTE
        invofpl.setItem(47,Back);
        invofpl.setItem(45,PP);
        invofpl.setItem(46,PS);
        invofpl.setItem(48,Filtre);
        invofpl.setItem(50,SupprProjet);
        invofpl.setItem(51,SupprDossier);
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
                case "§4Suppretion d'un projet":
                    e.getWhoClicked().sendMessage("C'est bon " + nameItem);
                    break;


                case "§8DECO":
                    e.getWhoClicked().sendMessage("C'est bon " + nameItem);
                    break;


                case "§4Suppretion d'un dossier":
                    MAINQB.filesmanager.getFile(path).remove("xd");
                    e.getWhoClicked().sendMessage(path);
                    e.getWhoClicked().sendMessage("C'est bon " + nameItem);
                    String pathB = String.join("/", Arrays.copyOf(path.split("/"),path.split("/").length-1));
                    OpenDefaultInf(pathB, Bukkit.getPlayer(e.getWhoClicked().getName()));
                    break;


                case "§aCréation d'un projet":
                    e.getWhoClicked().sendMessage("C'est bon " + nameItem);
                    break;


                case "§6Filtre":
                    e.getWhoClicked().sendMessage("C'est bon " + nameItem);
                    break;

                case "§aCréation d'un fichier":
                    if (e.getWhoClicked().getItemInHand().getType()== Material.AIR){
                        e.getWhoClicked().sendMessage("Hand " + nameItem);
                        break;
                    }
                    MAINQB.filesmanager.addProject("TestProj2", path,e.getWhoClicked().getItemInHand());
                    OpenDefaultInf(path, Bukkit.getPlayer(e.getWhoClicked().getName()));
                    e.getWhoClicked().sendMessage("C'est bon " + nameItem);
                    break;

                case "§aCréation d'un dossier":
                    if (e.getWhoClicked().getItemInHand().getType()== Material.AIR){
                        e.getWhoClicked().sendMessage("Hand " + nameItem);
                        break;
                    }
                    MAINQB.filesmanager.addDirectory("TestGraph2", path,e.getWhoClicked().getItemInHand());
                    OpenDefaultInf(path, Bukkit.getPlayer(e.getWhoClicked().getName()));
                    e.getWhoClicked().sendMessage("C'est bon " + nameItem);
                    break;


                case "§6Page suivante":
                    e.getWhoClicked().sendMessage("C'est bon " + nameItem);
                    break;
                case "§6Page precedente":
                    e.getWhoClicked().sendMessage("C'est bon " + nameItem);
                    break;

                case "§4Retour":
                    e.getWhoClicked().sendMessage("C'est bon " + nameItem);
                    String pathU = String.join("/", Arrays.copyOf(path.split("/"),path.split("/").length-1));
                    e.getWhoClicked().sendMessage(pathU);
                    OpenDefaultInf(pathU, Bukkit.getPlayer(e.getWhoClicked().getName()));
                    break;
                default:
                    switch (e.getClick()){
                        case MIDDLE:
                            MAINQB.filesmanager.remove(e.getCurrentItem().getItemMeta().getDisplayName(),path);
                            //Bukkit.getPlayer(e.getWhoClicked().getName()).closeInventory();
                            OpenDefaultInf(path, Bukkit.getPlayer(e.getWhoClicked().getName()));
                            break;
                        default:
                            FilesHandler file = MAINQB.filesmanager.getFile(path +"/"+ e.getCurrentItem().getItemMeta().getDisplayName());
                            if (file instanceof Directories){
                                OpenDefaultInf(path+"/" + e.getCurrentItem().getItemMeta().getDisplayName(), Bukkit.getPlayer(e.getWhoClicked().getName()));
                            } else {
                                e.getWhoClicked().sendMessage("tp" + nameItem);
                                Bukkit.getPlayer(e.getWhoClicked().getName()).teleport(((Projects) file).getLoc()); //.teleport((Projects file).getLoc())
                                break;
                            }

                    }
                    break;
            }
        }
    }

    @EventHandler
    public void onchat(AsyncPlayerChatEvent e){

    }
}
