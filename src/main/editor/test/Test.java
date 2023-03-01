package main.editor.test;
import main.editor.TSyntaxHighlighter;
import main.editor.highlighter.util.Keyword;
import main.editor.highlighter.util.KeywordGroup;
import main.editor.highlighter.util.KeywordParser;

import javax.swing.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.io.IOException;


public class Test{
    public static void main() throws IOException {
        JFrame FRAME = new JFrame("test");
        JTextPane PANE = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(PANE);
        //FRAME.add(PANE);
        FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FRAME.getContentPane().add(scrollPane);

        List<Keyword> KEYWORDS = new ArrayList<>();
        KeywordParser.setup("java.keyword");
        //
        KeywordGroup GROUP = new KeywordGroup("java",
                                              1,
                                               null,
                                               Color.green,
                                               true,
                                                false,
                                                false,
                                                  0,KeywordParser.parseToKeyWordList(KeywordParser.getKeyWordFromFileWithPattern("java.keyword","keyword:"))
                                                     );
        KeywordParser.usePattern_for_settingKeyword("java.keyword","keyword:",GROUP,false);
        KeywordParser.updateSettings("java.keyword",false);
        KeywordParser.writePlainTextToPatternedString("java.keyword","abstract assert boolean break byte case catch char class const continue default do double else enum extends final finally float for goto if implements import instanceof int interface long native new package private protected public return short static strictfp super switch synchronized this throw throws transient try void volatile while","keyword:");
        TSyntaxHighlighter SYNTAX_INSTANCE = new TSyntaxHighlighter(PANE);
        List<KeywordGroup> GROUP_LIST = new ArrayList<>();
        GROUP_LIST.add(GROUP);

        SYNTAX_INSTANCE.setKeywordListener(SYNTAX_INSTANCE,PANE);
        SYNTAX_INSTANCE.setGROUP_LIST(GROUP_LIST);
        FRAME.pack();
        SYNTAX_INSTANCE.setupDocument();
        FRAME.setVisible(true);



    }

    public static void main(String[] args) throws IOException {
        main();
    }
}
