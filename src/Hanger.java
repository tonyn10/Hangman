package src;

// visualization of the "hanged man"
public class Hanger {
    // top of hanger
    private String[][] top;
    private HangerPart[][] hangerParts;
    private String bottom;

    private HangerPart[] orderOfParts;
    private int currentIndex = 0;

    public Hanger() {
        this.initTop();
        this.initHangerParts();

        this.bottom = "___";
    }

    private void initTop() {
        // horizontal dimension is 6 characters across
        top = new String[2][3];
        top[0][0] = " __";  // tiny space at very left
        top[0][1] = "___";
        top[0][2] = "";

        top[1][0] = "|";
        top[1][1] = "     ";    // five spaces
        top[1][2] = "|";
    }

    private void initHangerParts() {
        hangerParts = new HangerPart[5][4];

        /*

        |     O
        |  ---|---
        |    / \
        |

        */

        hangerParts[0][0] = new HangerPart(true, "|");
        hangerParts[0][1] = new HangerPart(true, "     ");
        hangerParts[0][2] = new HangerPart(false, "O");     // *1
        // hangerParts[0][3] is null

        hangerParts[1][0] = new HangerPart(true, "|");
        hangerParts[1][1] = new HangerPart(false, "  ---"); // *3
        hangerParts[1][2] = new HangerPart(false, "     |");// *2
        hangerParts[1][3] = new HangerPart(false, "---");   // *4

        hangerParts[2][0] = new HangerPart(true, "|");
        hangerParts[2][1] = new HangerPart(false, "    /"); // *5
        hangerParts[2][2] = new HangerPart(false, " \\");   // *6
        // hangerParts[2][3] is null

        hangerParts[3][0] = new HangerPart(true, "|");
        // hangerParts[3][1 to 3] is null

        this.orderOfParts = new HangerPart[6];
        this.orderOfParts[0] = hangerParts[0][2];
        this.orderOfParts[1] = hangerParts[1][2];
        this.orderOfParts[2] = hangerParts[1][1];
        this.orderOfParts[3] = hangerParts[1][3];
        this.orderOfParts[4] = hangerParts[2][1];
        this.orderOfParts[5] = hangerParts[2][2];
    }

    private String getTop() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < top.length; i++) {
            for(int j = 0; j < top[i].length; j++) {
                sb.append(top[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String getHanger() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < hangerParts.length; i++) {
            for(int j = 0; j < hangerParts[i].length; j++) {
                if(hangerParts[i][j] != null && hangerParts[i][j].isVisible()) {
                    sb.append(hangerParts[i][j].toString());
                }
            }
            sb.append("\n");
        }
        return sb.toString().trim();    // trim the extra line space
    }

    public void incrementCurrentIndex() {
        this.orderOfParts[this.currentIndex].setVisible();
        if(this.currentIndex == 2) {
            this.orderOfParts[this.currentIndex-1].setString("|");
        }
        this.currentIndex++;
    }

    // get the whole string
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getTop());
        sb.append(this.getHanger());
        sb.append(this.bottom);
        return sb.toString();
    }
}
