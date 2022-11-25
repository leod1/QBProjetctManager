package fr.leod1.qbprojectmanager.GUIInterface;

public class QBDataPlayer {
    String Path;
    String event;

    public QBDataPlayer(String path, String event) {
        Path = path;
        this.event = event;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void Delete(){
        this.event = null;
        this.Path = null;
    }
}
