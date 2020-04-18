package classes;

import java.util.Date;

public class Snippet {

    private int id;
    private String name;
    private String programingLanguage;
    private Date dateCreated;
    private String content;
    private String categories;

    public Snippet(){
        this.id = 1001;
        this.name = "\"Hello World\" in Java";
        this.programingLanguage = "Java";
        this.dateCreated = new Date();
        this.content = "public static void main(String args[]){\n\tSystem.out.println(\"Hello World!!!\")";
        this.categories = "";
    }

    public Snippet(int id, String name, String programingLanguage, Date dateCreated, String content, String categories) {
        this.id = id;
        this.name = name;
        this.programingLanguage = programingLanguage;
        this.dateCreated = dateCreated;
        this.content = content;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgramingLanguage() {
        return programingLanguage;
    }

    public void setProgramingLanguage(String programingLanguage) {
        this.programingLanguage = programingLanguage;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

}
