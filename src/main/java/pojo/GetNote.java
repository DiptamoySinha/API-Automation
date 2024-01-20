package pojo;

import java.util.List;

public class GetNote {
     private String success;
     private String status;
     private String message;
    private Note data;


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Note getData() {
        return data;
    }

    public void setData(Note data) {
        this.data = data;
    }


}
