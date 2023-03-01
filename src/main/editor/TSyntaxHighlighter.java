package main.editor;

import main.editor.highlighter.util.Keyword;
import main.editor.highlighter.util.KeywordGroup;
import main.editor.highlighter.util.KeywordParser;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import javax.swing.text.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TSyntaxHighlighter
{
    protected final JTextPane textPane;

    public JTextPane getTextPane() {
        return textPane;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    private JScrollPane scrollPane;

   // private final String keywords = "abstract assert boolean break byte case catch char class const continue default do double else enum extends final finally float for goto if implements import instanceof int interface long native new package private protected public return short static strictfp super switch synchronized this throw throws transient try void volatile while";

    private final StyledDocument doc;
    protected List<KeywordGroup> GROUP_LIST = new ArrayList<>();
    protected List<KeywordGroup> GROUP_STARTER_LIST = new ArrayList<>();
    protected KeywordGroup GROUP;
    protected KeywordGroup STARTER_GROUP;

    public List<KeywordGroup> getGROUP_STARTER_LIST() {
        return GROUP_STARTER_LIST;
    }

    public void setGROUP_STARTER_LIST(List<KeywordGroup> GROUP_STARTER_LIST) {
        this.GROUP_STARTER_LIST = GROUP_STARTER_LIST;
    }

    public void setSTARTER_GROUP(KeywordGroup STARTER_GROUP) {
        this.STARTER_GROUP = STARTER_GROUP;
    }

    public KeywordGroup getSTARTER_GROUP() {
        return STARTER_GROUP;
    }

    public void setGROUP(KeywordGroup GROUP) {
        this.GROUP = GROUP;
    }

    public KeywordGroup getGROUP() {
        return GROUP;
    }

    public void setKeywordListener(TSyntaxHighlighter INSTANCE, JTextPane PANE){
        PANE.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    INSTANCE.UpdateStyleAll();
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void setGROUP_LIST(List<KeywordGroup> GROUP_LIST) {
        this.GROUP_LIST = GROUP_LIST;
    }

    public List<KeywordGroup> getGROUP_LIST() {
        return GROUP_LIST;
    }

    public TSyntaxHighlighter(JTextPane pane)
    {
        textPane = pane;

        textPane.setBackground(new Color(25,25,25,255));

        doc = textPane.getStyledDocument();




    }
    public void setupDocument(){
        setupDocument(doc);
    }

    public void UpdateStyleAll() throws BadLocationException
    {
        //ArrayList<EditorWord> words = new ArrayList<>();

        for(int i = 0; i < textPane.getDocument().getDefaultRootElement().getElementCount(); i++)
        {
            UpdateStyleLine(i);
        }
        //StyleAll(words);
    }

    public void UpdateStyleLine(int t) throws BadLocationException {
        int startT = textPane.getDocument().getDefaultRootElement().getElement(t).getStartOffset();
        int endT = textPane.getDocument().getDefaultRootElement().getElement(t).getEndOffset();
        if (t > 0) startT--;

        String txt = textPane.getText(startT, (endT - 1) - startT);

        //\/\/.+|\"[a-zA-Z ]+\"+|[a-z0-9A-Z_+\-><\(\)]+
        //comment | string | anything else
        Pattern p = Pattern.compile("//.+|\"[0-9a-zA-Z _\\-?.,#\\\\=%$']+\"+|[a-z0-9A-Z_+\\-><():.*]+");
        Matcher match = p.matcher(txt);

        //System.out.println("Working line "+t);
        //System.out.println("Matching string "+txt);

        boolean any = false;

        while (match.find()) {
            //System.out.println("Found "+match.group(0));
            StyleWord(startT + match.start(), startT + match.end());
            any = true;
        }

        if (!any) {
            StyleWord(startT, endT - 1);
        }
    }
    private void StyleWord(int start, int end)
    {
        Runnable doHighlight = () ->
        {
            String wrd = null;
            try
            {
                wrd = textPane.getText(start, end-start);
                wrd = wrd.trim();
                wrd = wrd.replace('\n','\0');
                //System.out.println("Word is "+wrd);
            } catch (BadLocationException e)
            {
                System.out.println("Bad location at "+start+" - "+end);
            }

            if (wrd==null||wrd.length()==0)return;

            if (wrd.startsWith("\""))
            {
                doc.setCharacterAttributes(start, wrd.length(), doc.getStyle("string"), true);
            }
            else
            {
                for (KeywordGroup group:
                     GROUP_LIST) {
                    for (Keyword s:
                            group.getKEYWORD_LIST()) {
                        //System.out.println(s.getKEYWORD());
                        if (wrd.equals(s.getKEYWORD())){
                            doc.setCharacterAttributes(start,wrd.length(),doc.getStyle(s.getKEYWORD_GROUP().getGROUP_NAME()),true);
                            return;
                        }else{
                            doc.setCharacterAttributes(start, wrd.length(), doc.getStyle("regular"), true);
                        }
                    }

                }


                    for (KeywordGroup group:
                         GROUP_STARTER_LIST) {
                        for (Keyword s:
                                group.getKEYWORD_LIST()) {
                            if (wrd.startsWith(s.getKEYWORD())){
                                doc.setCharacterAttributes(start,wrd.length(),doc.getStyle(s.getKEYWORD_GROUP().getGROUP_NAME()),true);
                                return;
                            }else{
                                doc.setCharacterAttributes(start, wrd.length(), doc.getStyle("regular"), true);
                            }
                        }
                    }



                /*
                if (wrd.startsWith("//"))
                {
                    doc.setCharacterAttributes(start, wrd.length(), doc.getStyle("comment"), true);
                } else if (Arrays.asList(keywords.split(" ")).contains(wrd))
                {
                    doc.setCharacterAttributes(start, wrd.length(), doc.getStyle("keyword"), true);
                }
                else
                {
                    doc.setCharacterAttributes(start, wrd.length(), doc.getStyle("regular"), true);
                }
                 */
            }

            doc.setLogicalStyle(textPane.getText().length()-1,doc.getStyle("regular"));
        };
        SwingUtilities.invokeLater(doHighlight);
    }

    private void setupDocument(StyledDocument doc)
    {
        Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

        Style s = doc.addStyle("regular",def);
        //StyleConstants.setFontFamily(s,"Monospace");
        StyleConstants.setForeground(s, Color.WHITE);
        StyleConstants.setFontSize(s, 18);
        for (KeywordGroup GROUP:
             GROUP_LIST) {
            s= doc.addStyle(GROUP.getGROUP_NAME(),s);
            StyleConstants.setForeground(s,GROUP.getFOREGROUND_COLOR());
            if (GROUP.getBACKGROUND_COLOR()==null){

            }else{
                StyleConstants.setBackground(s,GROUP.getBACKGROUND_COLOR());
            }

            StyleConstants.setItalic(s,GROUP.getIS_ITALIC());
            StyleConstants.setFontSize(s,GROUP.getFONT_SIZE());
            StyleConstants.setUnderline(s,GROUP.getIS_UNDERLINE());
        }

        for (KeywordGroup STARTER_GROUP:
             GROUP_STARTER_LIST) {
            s= doc.addStyle(STARTER_GROUP.getGROUP_NAME(),s);
            StyleConstants.setForeground(s,STARTER_GROUP.getFOREGROUND_COLOR());
            StyleConstants.setBackground(s,STARTER_GROUP.getBACKGROUND_COLOR());
            StyleConstants.setItalic(s,STARTER_GROUP.getIS_ITALIC());
            StyleConstants.setFontSize(s,STARTER_GROUP.getFONT_SIZE());
            StyleConstants.setUnderline(s,STARTER_GROUP.getIS_UNDERLINE());
        }




        s = doc.addStyle("keyword",s);
        StyleConstants.setForeground(s,Color.ORANGE);
        StyleConstants.setBold(s,true);

        s = doc.addStyle("comment",s);
        StyleConstants.setForeground(s,new Color(7, 128, 22));
        StyleConstants.setBold(s,false);

        s = doc.addStyle("string",s);
        StyleConstants.setForeground(s,new Color(6, 122, 178));
        StyleConstants.setBold(s,false);

        doc.setLogicalStyle(textPane.getText().length()-1,doc.getStyle("regular"));
    }
}