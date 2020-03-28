package dataParsing;

import classes.Snippet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import globals.Globals;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SnippetsParser {

//    private Gson gsonObject;
//    Type snippetListType;
//    ArrayList<Snippet> snippetList;
//
//    private ClassLoader classloader;
//    private InputStream snippetsIS;
//
////    private static String OS = System.getProperty("os.name").toLowerCase();
//
//    public SnippetsParser() {
//
//        classloader = Thread.currentThread().getContextClassLoader();
//        snippetsIS = classloader.getResourceAsStream("json_files/snippets.json");
//
//        this.gsonObject = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
//        this.snippetListType = new TypeToken<ArrayList<Snippet>>(){}.getType();
//        this.snippetList = new ArrayList<>();
//    }
//
//    public ArrayList<Snippet> getAllSnippets(){
//
//        this.snippetList = this.gsonObject.fromJson(new InputStreamReader(snippetsIS), this.snippetListType);
//
//        return this.snippetList;
//    }
//
//    public void updateSnippets(ArrayList<Snippet> updatedSnippets){
//
//        try {
////
////            String path = "";
////            if (isWindows()) {
////                System.out.println("This is Windows");
////                path
////            } else if (isMac()) {
////                System.out.println("This is Mac");
////            } else if (isUnix()) {
////                System.out.println("This is Unix or Linux");
////            }else {
////                System.out.println("Your OS is not support!!");
////            }
//
//            String jsonString = this.gsonObject.toJson(updatedSnippets, this.snippetListType);
//            String path = classloader.getResource("json_files/snippets.json").getPath().replace("%20", "\\ ");
//            System.out.println(path);
//            PrintWriter writer = new PrintWriter(new File(path));
//            writer.write(jsonString);
//            writer.close();
//
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//    }
//
////    public static boolean isWindows() {
////
////        return (OS.contains("win"));
////
////    }
////
////    public static boolean isMac() {
////
////        return (OS.contains("mac"));
////
////    }
////
////    public static boolean isUnix() {
////
////        return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0 );
////
////    }
////
////    public static boolean isSolaris() {
////
////        return (OS.contains("sunos"));
////
////    }

    private Gson gsonObject;
    private String filePath;
    Type snippetListType;
    ArrayList<Snippet> snippetList;

    public SnippetsParser() {
        this.gsonObject = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
        this.filePath = Globals.folderPath + "snippets.json";
        this.snippetListType = new TypeToken<ArrayList<Snippet>>(){}.getType();
        this.snippetList = new ArrayList<>();
    }

    public ArrayList<Snippet> getAllSnippets(){

        try {
            this.snippetList = this.gsonObject.fromJson(new FileReader(this.filePath), this.snippetListType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return this.snippetList;
    }

    public void updateSnippets(ArrayList<Snippet> updatedSnippets){

        try {

            String jsonString = this.gsonObject.toJson(updatedSnippets, this.snippetListType);

            FileWriter fw = new FileWriter(this.filePath);
            fw.write(jsonString);
            fw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
