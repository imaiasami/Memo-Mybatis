package org.example.model;

public class MemoRecord {
    private long memo_id;
    private long sender_id;
    private long receiver_id;

    public MemoRecord() {
    }

    public MemoRecord(long memo_id, long sender_id, long receiver_id) {
        this.memo_id = memo_id;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
    }

    public long getMemo_id() {
        return memo_id;
    }

    public void setMemo_id(long memo_id) {
        this.memo_id = memo_id;
    }

    public long getSender_id() {
        return sender_id;
    }

    public void setSender_id(long sender_id) {
        this.sender_id = sender_id;
    }

    public long getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(long receiver_id) {
        this.receiver_id = receiver_id;
    }

    @Override
    public String toString() {
        return ", memo_id: " + memo_id
                + ", sender_id: " + sender_id
                + ", receiver_id: " + receiver_id;
    }
}
