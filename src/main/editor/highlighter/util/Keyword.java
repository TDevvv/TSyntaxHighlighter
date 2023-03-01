package main.editor.highlighter.util;

public class Keyword{
    protected final String KEYWORD;
    protected KeywordGroup KEYWORD_GROUP;
    public String getKEYWORD() {
        return KEYWORD;
    }
    public KeywordGroup getKEYWORD_GROUP() {
        return KEYWORD_GROUP;
    }
    public void setKEYWORD_GROUP(KeywordGroup KEYWORD_GROUP) {
        this.KEYWORD_GROUP = KEYWORD_GROUP;
    }
    public Keyword(String KEYWORD) {
        this.KEYWORD = KEYWORD;
        //this.KEYWORD_GROUP = KEYWORD_GROUP;
    }
    @Override
    public String toString() {
        return "Keyword\n" +
                "{" +
                "KEYWORD=++'" + KEYWORD + '\'' +"\n"+
                " *KEYWORD_GROUP#name="+KEYWORD_GROUP.GROUP_NAME+"\n"+
                " *KEYWORD_GROUP#id="+KEYWORD_GROUP.GROUP_ID+"\n"+
                " *KEYWORD_GROUP#font_size="+KEYWORD_GROUP.FONT_SIZE+"\n"+
                '}';
    }
}
