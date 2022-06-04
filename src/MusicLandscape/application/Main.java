package MusicLandscape.application;

import MusicLandscape.container.MyTrackContainer;
import MusicLandscape.entities.Track;
import MusicLandscape.util.MyFormatter;
import MusicLandscape.util.MyMatcher;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*private boolean asc;
    private List<Comparator<Track>> comparators;
    private MyTrackContainer db;
    private List<MyFormatter<Track>> formatters;
    private static String GOOD_BYE_TEXT = "Thank you for using FinalTrackDataBase";
    private List<MyMatcher<Track>> matchers;
    private Menu menu = new Menu();
    private MyMatcher<Track> placeboMatcher;
    Scanner sc;
    private Comparator<Track> theComp;
    private MyFormatter<Track> theFormat;
    private static String WELCOME_TEXT = "Welcome to the FinalTrackDataBase";

    public static void main(String[] arg){
        new Main().go();
    }

    public void display(MyTrackContainer db){
        if(db.size() == 0){
            System.out.println("No tracks are stored");
        } else if (db.selection().length == 0){
            System.out.println("No tracks are selected");
        } else {
            System.out.println(theFormat.header());
            System.out.println(theFormat.topSeparator());

            for(Track t : db.selection()){
                System.out.println(theFormat.format(t));
            }

            String text = ("\n" + db.selection().length + " out of " + db.size() + " are selected";
            System.out.println(text);
        }
    }

    public void go(){
        System.out.println(WELCOME_TEXT);

        while(true){

        }
    }

    public boolean execute(int input) {
        for (MenuItem m : menu) {
            if (m != null && m.id == input) {
                m.execute();
                return true;
            }
        }
        return false;

    }

    private class Menu{
        private MenuItem[] menu = {
                new MenuItem(0, "Show menu"){
                    @Override
                    void execute() {
                        display();
                    }
                },
                new MenuItem(1, "Display selection"){
                    @Override
                    void execute() {
                        System.out.println("Select");
                    }
                },
                new MenuItem(2, "Edit"){
                    @Override
                    void execute() {
                        display();
                    }
                },
                new MenuItem(3, "Filter"){
                    @Override
                    void execute() {
                        display();
                    }
                },
                new MenuItem(4, "Reset"){
                    @Override
                    void execute() {
                        display();
                    }
                },
                new MenuItem(5, "Remove selection"){
                    @Override
                    void execute() {
                        display();
                    }
                },
                new MenuItem(6, "Add"){
                    @Override
                    void execute() {
                        display();
                    }
                },
                new MenuItem(7, "Save selection"){
                    @Override
                    void execute() {
                        display();
                    }
                },
                new MenuItem(8, "Load"){
                    @Override
                    void execute() {
                        display();
                    }
                },
                new MenuItem(9, "Reverse sorting order"){
                    @Override
                    void execute() {
                        asc = !asc;
                    }
                },
                new MenuItem(10, "Select sorting"){
                    @Override
                    void execute() {
                        display();
                    }
                },
                new MenuItem(11, "Select formatting"){
                    @Override
                    void execute() {
                        display();
                    }
                }
        };

        public void display(){
            for(MenuItem item : menu){
                System.out.println(item.toString());
            }
        }

        //public boolean execute(int input){
        //    return false;
        //}
    }

    private static abstract class MenuItem{
        private int id;
        private String text;

        public MenuItem(int id, String text){
            if(id >= 0 && !text.trim().isEmpty()){
                this.id = id;
                this.text = text;
            }
        }

        abstract void execute();

        public String toString() {
            return id + "\t" + text;
        }
    }*/
}
