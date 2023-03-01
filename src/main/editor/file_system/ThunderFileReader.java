package main.editor.file_system;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThunderFileReader
{
    protected File FILE;
    protected FileReader READER;
    protected BufferedReader BF_READER;
    protected Scanner SCANNER;
    public static ThunderFileReader create(String PATH) throws FileNotFoundException {
        ThunderFileReader tf_reader = new ThunderFileReader();

        tf_reader.FILE = new File(PATH);
        tf_reader.READER = new FileReader(tf_reader.FILE);
        tf_reader.BF_READER = new BufferedReader(tf_reader.READER);
        tf_reader.SCANNER = new Scanner(tf_reader.FILE);

        return tf_reader;
    }
    public int readInt(){
        int goreturn =0;
       if (SCANNER.hasNextInt()){
           goreturn = SCANNER.nextInt();
       }else{

       }
       return goreturn;
    }
    public List<Integer> readAllIntsWithList(){
        List<Integer> integerList = new ArrayList<>();;
        for (int i = 0; i < FILE.length(); i++) {
          integerList.add(readInt());
        }
        return integerList;
    }
    public String readLine(){
        try {
            if (BF_READER.ready()){
                return "\n"+BF_READER.readLine();
            }else{
                return "";
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String readWord(){
        if (SCANNER.hasNext()){
            return SCANNER.next();
        }else{
            return"";
        }
    }
    public String readAllFile(){
        String read_ed_file="";
        StringBuilder read_file_builder = new StringBuilder();
        for (int i = 0; i < FILE.length(); i++) {
            read_file_builder.append(readLine());
        }
        read_ed_file = read_file_builder.toString();
        return read_ed_file;
    }
    public List<String> readAllFileWithList(){
        List<String> read_ed_file_list = new ArrayList<>();
        for (int i = 0; i < FILE.length(); i++) {
            read_ed_file_list.add(readLine());
        }
        return read_ed_file_list;
    }
    public String readWords(){
        String read_ed_word="";
        StringBuilder read_file_builder =new StringBuilder();
        for (int i = 0; i < FILE.length(); i++) {
            read_file_builder.append(readWord()+" ");
        }
        return read_ed_word=read_file_builder.toString();
    }
    public List<String> readWordWithList(){
        List<String> read_ed_word=new ArrayList<>();
        test:
        for (int i = 0; i < FILE.length(); i++) {
            read_ed_word.add(readWord());
        }
        return  read_ed_word;
    }
    public String readPatternText(String PATTERN){
        String patterned ="";
        StringBuilder builder = new StringBuilder();
        boolean founded= false;
        while (SCANNER.hasNext()){
            if (readWord().equals(PATTERN)&&!founded){
               // builder.append(PATTERN);
                founded = true;
            } else if (founded) {
                while (SCANNER.hasNext()){
                    String read = readWord();
                    if (read.equals(PATTERN)){

                    }else{
                        builder.append(readWord()+" ");
                    }

                }
            }
        }
        return builder.toString();


    }
    public List<String> readPatternTextWithList(String PATTERN){
        List<String> LIST = new ArrayList<>();
        for (int i = 0; i < FILE.length(); i++) {
            if (readWord().equals(PATTERN)){
                LIST.add(readWord());
            }else{
            }
        }
        return LIST;
    }
    public List<Integer> readPatternIntegerWithList(String PATTERN){
        List<Integer> LIST = new ArrayList<>();
        for (int i = 0; i < FILE.length(); i++) {
            if (readWord().equals(PATTERN)){
                LIST.add(readInt());
            }else{

            }
        }
        return LIST;
    }
    public static final String findInFile(final Path file, final String pattern,
                                          final int flags)
            throws IOException
    {
        final StringBuilder sb = new StringBuilder();
        final Pattern p = Pattern.compile(pattern, flags);

        String line;
        Matcher m;

        try (
                final BufferedReader br = Files.newBufferedReader(file);
        ) {
            while ((line = br.readLine()) != null) {
                m = p.matcher(line);
                while (m.find())
                    sb.append(m.group());
            }
        }

        return sb.toString();
    }
    public int countLines(){
        int count = 0;
        do {
            if (SCANNER.nextLine()!=null){
                count++;
            }
        }while (SCANNER.hasNextLine());
        return count;
    }
    public int countWords(){
        int count = 0;
        do {
            if (readWord()!=null){
                count++;
            }
        }while (SCANNER.hasNext());
        return count;
    }

    @Override
    public String toString() {
        return "ThunderFileReader{" +
                "FILE=" + FILE +
                ", READER=" + READER +
                ", BF_READER=" + BF_READER +
                ", SCANNER=" + SCANNER +
                '}';
    }
    public ThunderFileReader(File FILE) {
        try {
            this.FILE = FILE;
            this.READER = new FileReader(this.FILE);
            this.BF_READER = new BufferedReader(this.READER);
            this.SCANNER = new Scanner(this.FILE);
        }
        catch (FileNotFoundException ignored){

        }
    }
    public void updateVariables(String PATH){
        this.FILE = new File(PATH);
    }
    public ThunderFileReader(){

    }
}
