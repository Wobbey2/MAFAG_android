package uqac.dim.mafag;

import java.io.Serializable;

public class ImageInfo implements Serializable {
    private String chosen_text;
    private String chosen_url;

    public ImageInfo(String chosen_text, String chosen_url) {
        this.chosen_text = chosen_text;
        this.chosen_url = chosen_url;
    }

    public ImageInfo() {
    }

    public String getChosen_url() {
        return chosen_url;
    }

    public void setChosen_url(String chosen_url) {
        this.chosen_url = chosen_url;
    }

    public String getChosen_text() {
        return chosen_text;
    }

    public void setChosen_text(String chosen_text) {
        this.chosen_text = chosen_text;
    }
}
