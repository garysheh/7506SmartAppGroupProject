package hk.hku.cs.hkudirectory;

public class Contact {

    private String cName;
    private String cEmail;
//    private int aIcon;

    public Contact() {
    }

    public Contact(String cName, String cEmail) {
        this.cName = cName;
//        this.cEmail = cEmail;
//        this.aIcon = aIcon;
    }

    public String getcName() {
        return cName;
    }

//    public String getcEmail() {
//        return cEmail;
//    }

//    public int getaIcon() {
//        return aIcon;
//    }

    public void setcName(String cName) {
        this.cName = cName;
    }

//    public void setcEmail(String cEmail) {
//        this.cEmail = cEmail;
//    }
//
////    public void setaIcon(int aIcon) {
////        this.aIcon = aIcon;
//    }
}

