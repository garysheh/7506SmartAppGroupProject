package hk.hku.cs.hkudirectory;

public class Maplist {
    private String mName;
    private String mRoom;
    private String mclass;

    public Maplist() {

    }



    public Maplist(String id, String s) {
        this.mclass = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmRoom() {
        return mRoom;
    }

    public void setmRoom(String mRoom) {
        this.mRoom = mRoom;
    }

    public String getMclass() {
        return mclass;
    }

    public void setMclass(String mclass) {
        this.mclass = mclass;
    }
}
