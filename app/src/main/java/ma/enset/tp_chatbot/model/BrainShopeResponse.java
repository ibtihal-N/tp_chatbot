package ma.enset.tp_chatbot.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class BrainShopeResponse implements Serializable {
    @SerializedName("cnt")
    private String cnt;

    public BrainShopeResponse(String cnt) {
        this.cnt = cnt;
    }

    public BrainShopeResponse() {
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }
}
