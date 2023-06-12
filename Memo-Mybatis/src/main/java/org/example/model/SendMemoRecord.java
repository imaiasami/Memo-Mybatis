package org.example.model;

public class SendMemoRecord extends MemoRecord {
    private long send_id;
    private String send_time;

    public SendMemoRecord() {
    }

    public SendMemoRecord(long memo_id, long sender_id, long receiver_id) {
        super(memo_id, sender_id, receiver_id);
    }

    public long getSend_id() {
        return send_id;
    }

    public void setSend_id(long send_id) {
        this.send_id = send_id;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    @Override
    public String toString() {
        return "send_id: " + send_id
                + super.toString()
                + ", send_time: " + send_time;
    }
}
