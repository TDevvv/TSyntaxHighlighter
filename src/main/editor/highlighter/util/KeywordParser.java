package main.editor.highlighter.util;

import main.editor.file_system.ThunderFileReader;
import main.editor.file_system.ThunderFileWriter;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeywordParser {
    protected   static ThunderFileReader READER;

    protected  static ThunderFileWriter WRITER;
    public static void setup(String PATH){
        READER = new ThunderFileReader(new File(PATH));
        WRITER= ThunderFileWriter.create(PATH);
    }
    public static List<String> getKeyWordFromFileWithPattern(String PATH,String PATTERN){
        return READER.readPatternTextWithList(PATTERN);
    }
    public static List<Keyword> parseToKeyWordList(List<String> KEYWORDS_String){
       List<Keyword> KEYWORDS_Keyword = new ArrayList<>();
        for (String S:
            KEYWORDS_String ) {
            KEYWORDS_Keyword.add(new Keyword(S));
        }
        //System.out.println(KEYWORDS_Keyword.toString());

        return KEYWORDS_Keyword;
    }
    public static void setToKeywordGroups(KeywordGroup GROUP){
        for (Keyword KEYWORD:
             GROUP.getKEYWORD_LIST()) {
            KEYWORD.setKEYWORD_GROUP(GROUP);
        }
    }
    public static void usePattern_for_settingKeyword(String PATH,String PATTERN,KeywordGroup GROUP,boolean show_preview){
        KeywordParser.setup(PATH);
        List<String> STR =  KeywordParser.getKeyWordFromFileWithPattern(PATH,PATTERN);
        GROUP.setKEYWORD_LIST(KeywordParser.parseToKeyWordList(STR));
        //System.out.println(GROUP.toString());
        KeywordParser.setToKeywordGroups(GROUP);
        if (show_preview) System.out.println("2# "+GROUP.toString());
    }
    public static void writePlainTextToPatternedString(String PATH,String STR,String PATTERN){
        KeywordParser.setup(PATH);
        WRITER.writePatternedTextSpaced(PATTERN,STR);
    }
    public static void updateSettings(String PATH,boolean append){
        WRITER.updateSettings(PATH,append);
    }
}
