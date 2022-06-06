// **************************************************
//		
//       git.rev = ${gitrev}
//  git.revision = ${gitrevision}
//         stage = ${stage}
//
// ***************************************************
package MusicLandscape.application;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import MusicLandscape.container.MyTrackContainer;
import MusicLandscape.entities.Track;
import MusicLandscape.util.MyFormatter;
import MusicLandscape.util.MyMatcher;
import MusicLandscape.util.comparators.DurationComparator;
import MusicLandscape.util.comparators.PerformerComparator;
import MusicLandscape.util.comparators.TitleComparator;
import MusicLandscape.util.comparators.WriterComparator;
import MusicLandscape.util.comparators.YearComparator;
import MusicLandscape.util.formatters.CSVTrackFormatter;
import MusicLandscape.util.formatters.LongTrackFormatter;
import MusicLandscape.util.formatters.ShortTrackFormatter;
import MusicLandscape.util.matcher.DurationMatcher;
import MusicLandscape.util.matcher.TitleMatcher;

/**
 *
 * @author TeM
 * @version ${gitrev}
 * @Stage ${stage}
 *
 */
public class MainProvided {

    private MyTrackContainer db = new MyTrackContainer();
    private List<Comparator<Track>> comparators = new LinkedList<Comparator<Track>>();
    private List<MyFormatter<Track>> formatters = new LinkedList<MyFormatter<Track>>();
    private List<MyMatcher<Track>> matchers = new LinkedList<MyMatcher<Track>>();

    private Comparator<Track> theComp;
    private boolean asc = true;

    private MyFormatter<Track> theFormat;
    private MyMatcher<Track> placeboMatcher = new TitleMatcher("");
    private Menu menu = new Menu();

    {

        comparators.add(theComp = new TitleComparator());
        comparators.add(new DurationComparator());
        comparators.add(new WriterComparator());
        comparators.add(new PerformerComparator());
        comparators.add(new YearComparator());

        matchers.add(placeboMatcher);
        matchers.add(new DurationMatcher());

        formatters.add(theFormat = new LongTrackFormatter());
        formatters.add(new ShortTrackFormatter());
        formatters.add(new CSVTrackFormatter());

    }

    private static final String WELCOME_TEXT = "Welcome to the FinalTrackDataBase";
    private static final String GOOD_BYE_TEXT = "Thank you for using FinalTrackDataBase";

    private static abstract class MenuItem {
        String text;
        static int nextID = 0;
        final int id = nextID++;

        abstract void execute();

        MenuItem(String s) {
            text = s;
        };

        public String toString() {
            return id + "\t" + text;
        }
    }

    private class Menu {

        private MenuItem[] menu = {

                new MenuItem("show menu") {
                    void execute() {
                        display();
                    }
                    // end of MenuItem
                },

                new MenuItem("display selection") {
                    void execute() {
                        System.out.printf("displaying selection:\n");

                        MainProvided.this.display(db);
                    }
                    // end of MenuItem
                }

        };// end of array

        void display() {
            for (MenuItem m : menu) {
                System.out.println(m);
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

    }

    public void go() {

        System.out.println(WELCOME_TEXT);
        Scanner sc = new Scanner(System.in);
        menu.execute(0);
        while (true) {
            // display(db);

            // get choice
            System.out.print(": ");
            int input = Integer.parseInt(sc.nextLine());
            if (menu.execute(input))
                continue;

            System.out.print("exit? (1=yes)");
            if (Integer.parseInt(sc.nextLine()) == 1)
                break;
        }

        System.out.println(GOOD_BYE_TEXT);
        sc.close();
    }


    public static void main(String[] args) {

        new MainProvided().go();

    }

    public void display(MyTrackContainer db) {

        if (db.size() == 0) {
            System.out.print("no records stored.\n");
            return;
        }
        if (db.selection().length == 0) {
            System.out.print("selection empty.\n");
            return;
        }

        System.out.println('\n' + theFormat.header());
        System.out.println(theFormat.topSeparator());
        for (Track tt : db.selection())
            System.out.println(theFormat.format(tt));
        System.out.println();

        System.out.printf("%d out of %d records selected\n", db.selection().length,
                db.size());
    }

}
