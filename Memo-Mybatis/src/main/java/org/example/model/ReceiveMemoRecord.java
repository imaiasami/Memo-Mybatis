package org.example.model;

public class ReceiveMemoRecord extends MemoRecord {
    private long receive_id;
    private boolean is_read;

    public ReceiveMemoRecord() {
    }

    public ReceiveMemoRecord(long memo_id, long sender_id, long receiver_id) {
        super(memo_id, sender_id, receiver_id);
    }

    public long getReceive_id() {
        return receive_id;
    }

    public void setReceive_id(long receive_id) {
        this.receive_id = receive_id;
    }

    public boolean isIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }

    @Override
    public String toString() {
        return "receive_id: " + receive_id
                + super.toString()
                + ", is_read: " + is_read;
    }
}
