package com.ebookfrenzy.tablayoutdemo;

/**
 * Created by hn012 on 2017-04-13.
 */
import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable iconDrawable ;
    private Drawable lineDrawavble;
    private String titleStr ;
    private String descStr ;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setline(Drawable line) { lineDrawavble = line;}
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public Drawable getline() { return this.lineDrawavble ;}
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
}

