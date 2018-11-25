package com.example.chrom.jsontest;

public class Message {

    private long id,time;
            private String text,image;

    Message(long id, long time, String text, String image){
        this.id=id;
        this.time = time;
        this.text = text;
        this.image=image;
    }
    Message(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id=id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setImage(String image) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setText(String image) {
        this.image = image;
    }

    @Override
    public  String toString(){
        return id + "\n" + time+"\n"+ text;
    }
}
