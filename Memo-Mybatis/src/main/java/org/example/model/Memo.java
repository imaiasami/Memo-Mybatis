package org.example.model;

public class Memo {
    private long memo_id;
    private String title;
    private String contents;

    public Memo() {}

    public Memo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public long getMemo_id() {
        return memo_id;
    }

    public void setMemo_id(long memo_id) {
        this.memo_id = memo_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "memo_id=" + memo_id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
