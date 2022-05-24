package src;

public class HangerPart {
    private boolean visible;
    private String value;

    public HangerPart(boolean visible, String value) {
        this.visible = visible;
        this.value = value;
    }

    public void setString(String s) {
        this.value = s;
    }

    public void setVisible() {
        this.visible = true;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public String toString() {
        return value;
    }
}
