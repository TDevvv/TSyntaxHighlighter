package main.editor.file_system;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ThunderFileWriter {
    protected File FILE;
    protected FileWriter WRITER;
    protected BufferedWriter BF_WRITER;
    protected boolean append=true;

    public static ThunderFileWriter create(String FILE_NAME){
        ThunderFileWriter WRITER_INSTANCE = new ThunderFileWriter();
        try {
            WRITER_INSTANCE.FILE = new File(FILE_NAME);
            if (!WRITER_INSTANCE.FILE.exists()){
                WRITER_INSTANCE.FILE.createNewFile();
            }
            WRITER_INSTANCE.WRITER = new FileWriter(WRITER_INSTANCE.FILE,WRITER_INSTANCE.append);
            WRITER_INSTANCE.BF_WRITER = new BufferedWriter(WRITER_INSTANCE.WRITER);
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        WRITER_INSTANCE.BF_WRITER.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }));
        }
        catch (IOException e){
        }
        return WRITER_INSTANCE;
    }
    public void write(String STRING){
        try {
            BF_WRITER.write(STRING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeInt(int INTEGER){
        try {
            BF_WRITER.write(String.valueOf(INTEGER));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeWithList(List<String> LIST){
        for (String s:
             LIST) {
            write(s);
        }
    }
    public void writePatternedText(String PATTERN,String text){
        write(PATTERN+" ");
        write(text);
    }
    public void writePatternedTextSpaced(String PATTERN,String text){
        List<String> LIST = Arrays.asList(text.split(" "));
        for (String s:
                LIST) {
            write("\n"+PATTERN+" ");
            write(s);
        }
    }
    public void writePatternedTextWithList(String PATTERN,List<String> texts){
        for (String s:
             texts) {
           writePatternedText(PATTERN,s);
           write(" ");
           write("\n");
        }
    }
    public void updateSettings(String FILE_NAME,boolean append){
        this.append = append;
        try {
            WRITER = new FileWriter(FILE_NAME,this.append);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
