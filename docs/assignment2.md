# Assignment 2

Maximum number of words for this document: 12000

**IMPORTANT**: In this assignment you will model the whole system. Within each of your models, you will have a *prescriptive intent* when representing the elements related to the feature you are implementing in this assignment, whereas the rest of the elements are used with a *descriptive intent*. In all your diagrams it is strongly suggested to used different colors for the prescriptive and descriptive parts of your models (this helps you in better reasoning on the level of detail needed in each part of the models and the instructors in knowing how to assess your models).   

**Format**: establish formatting conventions when describing your models in this document. For example, you style the name of each class in bold, whereas the attributes, operations, and associations as underlined text, objects are in italic, etc.

### Implemented feature

| ID  | Short name  | Description  |
|---|---|---|
| F1  | Tags | Code snippets can be tagged via freely-defined labels called tags  |

### Used modeling tool
Add here the name of the modeling tool you are using for your project.

## Class diagram									
Author(s): `Dimitar Georgiev, Klimis Tsakiridis, Stoyan Tsiniov, Iva Dimitrova`

`Figure representing the UML class diagram`
![Image of Yaktocat](./diagrams/ClassDiagram.png)

###Main.java (introduced v1.0 still alive) 

```java
public static void main (String[] args) {

    Globals.currentState = State.STATE_DASHBOARD;
    Globals.setFrame(new DashboardForm().mainPanel);
}
```
Since we are sure every time the user starts the application he/she will end in the Dashboard screen we set the current state of the application to be the `DASHBOARD_STATE`. After we complete this we call the `setFrame()` method in the `Globals.java` class which will take as parameter a `JPanel` and change the screen of the application depending on the `JPanel` we passed as parameter.


###ColorTheme.java (introduced v1.1 still alive)

This class is used to set the color theme of the application. When the application is started an instance from this class is created which is parsed from the settings.json. This instance is available all the time since we need to se the colors for alll components and we cannot bbe sure at what time the user will request a different component. The class has an empty constructor that will take the default/last set color theme. When the user changes the settings for the color theme the new color theme will be dynamically injected in the instance and change the whole look and feel. We are aware that there are plenty of data fields present in this class but we wanted to make sure that when using a color we use the correct color instead of wondering which color goes where, hence we have fields such as: `snippetScreenSaveButtonHoverBackgroundColor`, `dashboardButtonHoverBackgroundColor` which will explicitly say for which element we use the given color. 

###Settings.java (introduced v1.0 still alive) 

This class will hold/create the settings. The various settings will be hold in this class. The class contains information about the settings for the system such as: defaultProgramingLanguage, categories and colorTheme. The class is instantiated only once in the `Globlas.java` class from where the settings are read in the system. When we parse the settigns.json file an object is created from this type. The when the user changes a setting in the application the current settings object is updated and translated back to `JSON` and saved as such.


###SingleClickCopy.java (introduced v1.0 still alive) 

The class contains s single method `copy(Snippet snippet)` that will copy the content of the snippet passed as a parameter. The contented of the snippet will be set in the “copy buffer” of the system. So basically this copy method will perform `ctrl + c` for us so the user can paste it anywhere he/she wants. We are aware that having a class for one functionality is a bit of overkill but we also consider that maybe by the end of the project we will have to add some extra methods in order to make it flexible.

***NB:** In case we keep it as it is and do not add extra functionality to it we will move it to the SnippetHelper.java class, where we keep all of the snippet manipulations.*

###Snippet.java (introduced v1.0 still alive) 

The snippet class is the class that is used to create snippets. All if its variables are private. The snippet class is used when we parse the snippets.json file. The parser will create an ArrayList of snippets that we will use trough the system. The data fields of this class are:
```
name
programingLanguage
dateCreated
content
categories
dateModified
```

All of its data fields are private. In order to get/set any property of a snippet we use the provided getters and setters. Up until this point we think that this information about the snippets is enough. If by any chance we need an extra field we can easily add it and use it trough the system.


###SnippetHelper.java (introduced v1.0 still alive) 

The Snippet helper class is the class that manipulates the snippets. It instantiates the SnippetParser.java class which provides the data and is using this data according to our needs. We are using only one object (ArryList<Snippet>)containing all snippets and we used it to complete the needed manipulations. Here are the methods used in this class:

`public SnippetHelper()`\
A constructor without any arguments that creates an instange of the SnippetParser class and reads all of the snippets form it.

`public ArrayList<Snippet> getAllSnippets()`\
Returns a list with all of the snippets fetched form the parser. 

`public void updateSnippets(ArrayList<Snippet> updatedSnipptes)`\
This method will take the updatedSnippets list and parse it back to JSON format using the SnippetParser class. Depending on the list we pass this information will be stored in the snippets.json file.

`public void deleteSnippet(Snippet snippetToBeRemoved)`\
Deletes a certain snippet from the list and updating the snippet.json file

`public void editSnippet(Snippet snippetToBeEdited)`\
Updates a snippet with the newly entered information.

`public ArrayList<Snippet> getSnippetsOrderByDateAscending()`\
Returns a list with ordered snippets by dateCreated ascending order.

`public ArrayList<Snippet> getSnippetsOrderByDateDescending()`\
Returns a list with ordered snippets by dateCreated desscending order.

`public ArrayList<Snippet> getSnippetsOrderByName()`\
Retruns a list with ordered snippets by name.

`public ArrayList<Snippet> getSnippetsFilteredByLanguage(String filterLanguage)`\
Returns a list containing snippets only from from a certain programming language.

`public ArrayList<Snippet> getSnippetsFilteredByCategory(ArrayList<String> categories)`\
Returns a list containing snippets only from from a certain category.

`public String toString(Snippet snippet)`\
Custom to string method, mainly for testing purposes to see if the data is passed correctly.


###State.java (introduced v1.0 still alive) 
The sate class is controlling what the current view will be. We have four main states:
```java
public static final String STATE_DASHBOARD      = "STATE_DASHBOARD";
public static final String STATE_SETTINGS       = "STATE_SETTINGS";
public static final String STATE_ADD_SNIPPET    = "STATE_ADD_SNIPPET";
public static final String STATE_ALL_SNIPPETS    = "STATE_ALL_SNIPPETS";
```

This static variables will determine the state of the application and populate the screen accordingly.

`public void changeState(String newState)`\
This method will be used every time we change the state (switch a screen). This method will set the current state depending on what we pass as parameter and will show the appropriate screen depending on this parameter. For example:
The user is on the AllSnippets screen, the current state is  `STATE_ALL_SNIPPETS`, that means that on the screen now we will see all snippets. The user clicks the navigation and he/she goes to Dashboard. When this click occurs we will trigger an event that will change the state to  `STATE_DASHBOARD` and call this method with parameter `STATE_DASHBOARD` which will change the screen to Dashboard.

Doing so we can easily control the screens and changing a screen will not be so difficult.

###SnippetParser.java (introduced v1.0 still alive) 
The snippet parser is the class that will read the snippets.json file and turn the json string into Snippet objects and vice versa. The class contains a constructor and two methods:
```java
public SnippetsParser() {
    this.gsonObject = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    this.filePath = "src/main/java/JSONFiles/snippets.json";
    this.snippetListType = new TypeToken<ArrayList<Snippet>>(){}.getType();
    this.snippetList = new ArrayList<>();
}
```
Here we set the date format we use for the snippets, the path to the `snippets.json` file, the type we will use when we read the `Snippet` objects to `JSON` and we initialize the snippetsList.

`public ArrayList<Snippet> getAllSnippets()`\
In this method we basically read the json file and we create a list with `Snippets` objects from it. This is the only instance of this list that we use all the time. Doing so we make sure that we read the file only once since there is a possibility that this file becomes too big.

`public void updateSnippets(ArrayList<Snippet> updatedSnippets)`\
This method updates/overrides the current file we have depending on the list that we pass. We use the `Type` created in the constructor to point out to the `Gson` library that we want to deserialize the object in a certain format. The method is called every time the user changes/adds/deletes a snippet to reduce the data loss in case of a crash.

###SettingsParser.java (introduced v1.1 still alive) 
The settings parser have the same functionality as the snippet parser but it is parsing the `settings.json` file. The interesting part of the class comes where we parse the `Color` class that is used for setting the color theme. Because of the fact that we use a library to serialize and deserialize the json file this process is handled automatically. The output for the color theme is bit different than expected bit it is working so we are not planning to have different approach to it. The downside of this is that the data stored in the settings.json file is not fully readable for a human (only the color theme). Here a s small example:
```json
"colorTheme": {
  "backgroundColor": {
    "value": -991003,
    "falpha": 0.0
  },
  "headerBackgroundColor": {
    "value": -12049618,
    "falpha": 0.0
  },
  "headerTextColor": {
    "value": -1846312,
    "falpha": 0.0
  },
  "headerButtonBackgroundColor": {
    "value": -12049618,
    "falpha": 0.0
  },
  .
  .
  .
}
```
Strangely enough when we read the `JSON` all of the color are translated properly. 


###Colors.java (introduced v1.0 NOT USED ANYMORE) 
The class was used to suit the need of taking static colors from it, but since we decided to have different color themes as set color palettes it become useless. We will still keep it since maybe a custom color can be introduced. Moreover, another possibility of using the class is to make it as a factory for the color themes. This will reduce the length of the `ColorTheme` class and also will make it easier to create new color themes.


###Globals.java (introduced v1.0 still alive) 
A class consisting only from static methods and variable. This class is crucial for the application. Since in lots of places we use the same code this class will hold this code for us so we can reuse it at anytime. 
```java
public static final String APPLICATION_NAME = "Coniunx";
public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
public static String currentState = "";
public static SnippetHelper snippetHelper = new SnippetHelper();
public static SettingsParser settingsParser = new SettingsParser();
public static JFrame mainFrame = new JFrame(Globals.APPLICATION_NAME);
```

Static variables help us getting the currentState of the application as well as the name of the application. Here we also have an instance of the snippetHelper that will provide the snippet manipulations as well as the `settingParser` that will provide the settings. During the usage of the application this variables will change and we do not need to worry about is since thy are static and every time we need them we will get their current situation.

`public static void setFrame(JPanel panel)`\
Used to set the frame depending on the current state of the application.

`public static ArrayList<String> getAllCategories()`\
We use it in couple of places. This reduces the code length.

`public static ArrayList<String> getAllProgramingLanguages()`\
Used to get all of the supported programming language.

`public static String[] colorThemesNames = {"Light","Dark","Moonlight"};`\
Used to populate the drop-down list in the Settings screen. Depending on the choice we will change the theme accordingly.

When we notice a repetitive code we put it here as static variable/function for ease of use and access. It is possible more methods and/or variables to be moved here.

###GlobalsViews.java (introduced v1.1 still alive) 
All button styling that we used will be created here. Setting colors, icons and text. This class will hold all the repetitive code for the visual parts of the application. Currently most of the methods are in the From classes themselves but we will refactor it and make sure that the repetitive methods will be here. 

The only case where we have to set the listeners of the button we consider to keep it in the Form classes itself since this will give us more flexibility to make the button per form not general.

###AllSnippetCellRenderer.java (introduced v1.0 still alive) 
The class constructs a single list view that will be populated in the list. Especially this case  the class is setting the list to contain the name of the snippet plus the extension of the file, which basically will be the filename.

###FilterListCellRenderer.java (introduced v1.0 still alive) 
The class constructs a single list view that will be populated in the list. Especially this case the class is making a list of checkboxes with the categories or programing language filter. The class is used in both cases. Setting some click listeners will allow us to to select multiple items and read back which item are selected. Using this items as parameters in the SnippetHelper class we can filter the snippet list.

###RecentlyAddedCellRenderer.java (introduced v1.0 still alive) 
The class constructs a single list view that will be populated in the list. Especially this case the class is setting the item to have two text fields, one with the name and the second with the date created. Setting a double click listener to the item we get the option to edit/delete/copy the selected snippet.

The whole application UI will be created using Swing Forms. We will have one main Swing Frame that can be repainted with different Forms. Every application state will have its own form. The following classes will explain how the components in this Forms will be handled in the different states.



###AllSnippetsForm.java (introduced v1.1 still alive) 
This class will paint the all snippets screen. We will have a text area that will handle the content insertion/edition, a filter panel that will contain all the filters and snippets list that will contain the current existing snippets. Furthermore, we will have a dedicated button to add a snippet that basically will empty the text area and provide the possibility to the user to insert a new information about the snippet. All of the different parts mentioned will be placed in wrapper panels for ease of placement on the screen. A navigation will be provided on the left hand side for ease of access to all  of the other screens. Using the Globals.java we can access all of needed information with combination of  the SnippetHelper we can manipulate the snippets. A search box is present here which has a onKeyUp listener that will change the datamodel dynamically in order to see the results immediately in the snippets list located on the same screen. The filters will have the same functionality using again the SnippetHelper class.

`private void createTextArea()`\
Creates a text-area that has a syntax highlighter.

`private void handleSelectSnippet()`\
Updates the visual content part with the selected snippet data.

`private void createAllSnippetsList()`\
Populates the snippets list with the correct list render.

`private void addProgramingLanguages()`\
Populate the programing languages drop down menu

`public void createFilters()`\
Creates the filters chackboxes

`private void changeProgramingLanguage(String programingLanguage)`\
When user choose a snippet from the menu we are changing the selected programming language accordingly.

`private void handleSaveSnippet()`\
If new snippet, we add the new snippet. If existing we update it.

`private void validateData()`\
Validate the data: if the name is empty or already exist, if content is empty give a warning message.

`private void update()`\
This method will update the snippets list view

`private void search()`\
The search method will change the data model of the snippets list


###DashboardForm.java (introduced v1.0 still alive) 
This class will paint the all dashboard screen. This will be the initial state of the application. In this screen we will have a list of the recently added snippets that will instantiates the AllSnippetsListRenderer in order to populate the list. Moreover, we will have the options to Add Snippet, see All Snippets and change Settings. This options will be provided with separated buttons in order to be able to change the state of the application so we can repaint the other screens. 

`private ImageIcon getScaledImageIcons(ImageIcon imageIcon, int width, int height)`\
Get button icons

`private void setupDashboadButton(JButton dashboardButton)`\
Setting the dashboard button

`private void setupOptionsButton(JButton optionButton)`\
When double click a button a list item a dialog box will popup with three buttons. We use this to set their look and feel.

`private void setOptionsDialogSettings(JDialog optionsDialog)`\
Creating the dialog box with the options per item such as edit/delete/copy

`private void handleDeleteButtonPressed(JDialog optionsDialog, int index)`\
Called when delete button is clicked. It will delete the selected snippet.

`private void handleCopyButtonPressed(JDialog optionsDialog, int index)`\
Called copy button is clicked. It will copy the content of the snippet.

`private void handleSettingsButtonClicked()`\
Called when the settings button is clicked. It will go to the settings screen.

`private void handleAllSnippetsButton() `\
Called when the all snippets button is clicked. It will go to all snippets screen.

`private void update()`\
Used to update the Recently added list when an item is deleted.


###SttingsForm.java (introduced v1.0 still alive) 
This class will paint the settings screen. In this screen the user can choose the color theme and the default programming language. What is more, he/she can add the categories here as well in a coma separated list. When pressing the save button the settings will be applied automatically and also will be parsed back to the settigns.json file. This will play the role of “saving” your settings for next time or until they change again.


`private ImageIcon getScaledImageIcons(ImageIcon imageIcon, int width, int height)`\
Get button icons

`private void setupOptionsButton(JButton optionButton)`\
Setting the navigation button

`private void saveSettings()`\
Reads the user settings from the Settings Screen components and updates the current settings.

`private void update()`\
Applies the settings.


## Object diagrams								
Author(s): `Dimitar Georgiev, Klimis Tsakiridis, Stoyan Tsiniov, Iva Dimitrova`

This chapter contains the description of a "snapshot" of the status of your system during its execution. 
This chapter is composed of a UML object diagram of your system, together with a textual description of its key elements.

`Figure representing the UML class diagram`
  
`Textual description`

Maximum number of words for this section: 1000

## State machine diagrams									
Author(s): `Dimitar Georgiev, Klimis Tsakiridis, Stoyan Tsiniov, Iva Dimitrova`

This chapter contains the specification of at least 2 UML state machines of your system, together with a textual description of all their elements. Also, remember that classes the describe only data structures (e.g., Coordinate, Position) do not need to have an associated state machine since they can be seen as simple "data containers" without behaviour (they have only stateless objects).

For each state machine you have to provide:
- the name of the class for which you are representing the internal behavior;
- a figure representing the part of state machine;
- a textual description of all its states, transitions, activities, etc. in a narrative manner (you do not need to structure your description into tables in this case). We expect 3-4 lines of text for describing trivial or very simple state machines (e.g., those with one to three states), whereas you will provide longer descriptions (e.g., ~500 words) when describing more complex state machines.

The goal of your state machine diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

Maximum number of words for this section: 3000

## Sequence diagrams									
Author(s): `Dimitar Georgiev, Klimis Tsakiridis, Stoyan Tsiniov, Iva Dimitrova`

This chapter contains the specification of at least 2 UML sequence diagrams of your system, together with a textual description of all its elements. Here you have to focus on specific situations you want to describe. For example, you can describe the interaction of player when performing a key part of the videogame, during a typical execution scenario, in a special case that may happen (e.g., an error situation), when finalizing a fantasy soccer game, etc.

For each sequence diagram you have to provide:
- a title representing the specific situation you want to describe;
- a figure representing the sequence diagram;
- a textual description of all its elements in a narrative manner (you do not need to structure your description into tables in this case). We expect a detailed description of all the interaction partners, their exchanged messages, and the fragments of interaction where they are involved. For each sequence diagram we expect a description of about 300-500 words.

The goal of your sequence diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

Maximum number of words for this section: 3000

## Implementation									
Author(s): `Dimitar Georgiev, Klimis Tsakiridis, Stoyan Tsiniov, Iva Dimitrova`

In this chapter you will describe the following aspects of your project:
- the strategy that you followed when moving from the UML models to the implementation code;
- the key solutions that you applied when implementing your system (for example, how you implemented the syntax highlighting feature of your code snippet manager, how you manage fantasy soccer matches, etc.);
- the location of the main Java class needed for executing your system in your source code;
- the location of the Jar file for directly executing your system;
- the 30-seconds video showing the execution of your system (you can embed the video directly in your md file on GitHub).

IMPORTANT: remember that your implementation must be consistent with your UML models. Also, your implementation must run without the need from any other external software or tool. Failing to meet this requirement means 0 points for the implementation part of your project.

Maximum number of words for this section: 2000

## References

References, if needed.