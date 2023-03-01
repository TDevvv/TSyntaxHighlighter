package main.editor.highlighter.util;

import java.util.List;
import java.awt.*;

public class KeywordGroup {
    protected String GROUP_NAME;
    protected Integer GROUP_ID;
    protected Color BACKGROUND_COLOR;
    protected Color FOREGROUND_COLOR;
    protected Boolean IS_BOLD;
    protected Boolean IS_ITALIC;
    protected Boolean IS_UNDERLINE;
    protected Integer FONT_SIZE;
    protected List<Keyword> KEYWORD_LIST;

    public List<Keyword> getKEYWORD_LIST() {return KEYWORD_LIST;}
    public void setKEYWORD_LIST(List<Keyword> KEYWORD_LIST) {this.KEYWORD_LIST = KEYWORD_LIST;}
    public String getGROUP_NAME() {return GROUP_NAME;}
    public void setGROUP_NAME(String GROUP_NAME) {
        this.GROUP_NAME = GROUP_NAME;
    }
    public Integer getGROUP_ID() {
        return GROUP_ID;
    }
    public void setGROUP_ID(Integer GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }
    public Color getBACKGROUND_COLOR() {
        return BACKGROUND_COLOR;
    }
    public void setBACKGROUND_COLOR(Color BACKGROUND_COLOR) {
        this.BACKGROUND_COLOR = BACKGROUND_COLOR;
    }
    public Color getFOREGROUND_COLOR() {
        return FOREGROUND_COLOR;
    }
    public void setFOREGROUND_COLOR(Color FOREGROUND_COLOR) {
        this.FOREGROUND_COLOR = FOREGROUND_COLOR;
    }
    public Boolean getIS_BOLD() {
        return IS_BOLD;
    }
    public void setIS_BOLD(Boolean IS_BOLD) {
        this.IS_BOLD = IS_BOLD;
    }
    public Boolean getIS_ITALIC() {
        return IS_ITALIC;
    }
    public void setIS_ITALIC(Boolean IS_ITALIC) {
        this.IS_ITALIC = IS_ITALIC;
    }
    public Boolean getIS_UNDERLINE() {
        return IS_UNDERLINE;
    }
    public void setIS_UNDERLINE(Boolean IS_UNDERLINE) {
        this.IS_UNDERLINE = IS_UNDERLINE;
    }
    public Integer getFONT_SIZE() {
        return FONT_SIZE;
    }
    public void setFONT_SIZE(Integer FONT_SIZE) {
        this.FONT_SIZE = FONT_SIZE;
    }
    public KeywordGroup(String GROUP_NAME, Integer GROUP_ID, Color BACKGROUND_COLOR, Color FOREGROUND_COLOR, Boolean IS_BOLD, Boolean IS_ITALIC, Boolean IS_UNDERLINE, Integer FONT_SIZE,List<Keyword> KEYWORD_LIST) {
        this.GROUP_NAME = GROUP_NAME;
        this.GROUP_ID = GROUP_ID;
        this.BACKGROUND_COLOR = BACKGROUND_COLOR;
        this.FOREGROUND_COLOR = FOREGROUND_COLOR;
        this.IS_BOLD = IS_BOLD;
        this.IS_ITALIC = IS_ITALIC;
        this.IS_UNDERLINE = IS_UNDERLINE;
        if (FONT_SIZE==0){
            this.FONT_SIZE = 18;
        }else{
            this.FONT_SIZE = FONT_SIZE;
        }
        this.KEYWORD_LIST = KEYWORD_LIST;
    }
    @Override
    public String toString() {
        return "KeywordGroup\n" +
                "{" +
                "GROUP_NAME='" + GROUP_NAME + '\'' +
                ", GROUP_ID=" + GROUP_ID +
                ", BACKGROUND_COLOR=" + BACKGROUND_COLOR +
                ", FOREGROUND_COLOR=" + FOREGROUND_COLOR +
                ", IS_BOLD=" + IS_BOLD +
                ", IS_ITALIC=" + IS_ITALIC +
                ", IS_UNDERLINE=" + IS_UNDERLINE +
                ", FONT_SIZE=" + FONT_SIZE+
                ", KEYWORD_LIST= "+KEYWORD_LIST+
                '}';
    }
    public String getAllKeywords(){
        int i = 0;
        StringBuilder builder = new StringBuilder();
        for (Keyword K :
             KEYWORD_LIST) {
            i++;
            builder.append("#"+i+" "+K.getKEYWORD()+"\n");
        }
        return builder.toString();
    }
    public Integer countKeywords(){
       return KEYWORD_LIST.size();
    }
}
