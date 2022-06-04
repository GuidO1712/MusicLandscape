// **************************************************
//		
//       git.rev = ${gitrev}
//  git.revision = ${gitrevision}
//         stage = ${stage}
//
// ***************************************************
package MusicLandscape.application;

import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import MusicLandscape.container.MyTrackContainer;
import MusicLandscape.entities.Artist;
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
import MusicLandscape.util.io.MyTrackCSVReader;
import MusicLandscape.util.io.MyWriter;
import MusicLandscape.util.matcher.*;

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
		matchers.add(new YearMatcher());
		matchers.add(new WriterMatcher(""));
		matchers.add(new PerfomerMatcher(""));
		matchers.add(new TrackMatcher(""));

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
		},

		new MenuItem("add") {
			void execute() {
				System.out.printf("add:\n");

				MainProvided.this.add();
			}
		// end of MenuItem
		},

		new MenuItem("edit") {
			void execute() {
				System.out.printf("edit:\n");

				MainProvided.this.edit();
			}
			// end of MenuItem
		},

		new MenuItem("reset") {
			void execute() {
				db.reset();
				System.out.println("selection was reseted");
			}
			// end of MenuItem
		},

		new MenuItem("remove selection") {
			void execute() {
				System.out.printf("edit:\n");
				db.remove();
			}
			// end of MenuItem
		},

		new MenuItem("filter") {
			void execute() {
				System.out.printf("filter:\n");

				int i = 0;

				for(MyMatcher<Track> mym : matchers){
					i++;
					System.out.println(i + "\t" + mym.toString());
				}

				Scanner sc = new Scanner(System.in);

				System.out.print("Select filtering: ");
				try{
					int filteringIdx = Integer.parseInt(sc.nextLine());
					if(filteringIdx > 0 && filteringIdx <= i){
						filteringIdx--; // to get the right index for the list
						System.out.print("Enter pattern: ");
						String inputPattern = sc.nextLine();

						matchers.get(filteringIdx).setPattern(inputPattern);

						System.out.println(matchers.get(filteringIdx).toString() + " filter applied (" + db.filter(matchers.get(filteringIdx)) + " records filtered)" );
					}
				} catch (NumberFormatException e){
					System.out.println("Input for the index for filtering was not a number");
				}
			}
			// end of MenuItem
		},

		new MenuItem("select formatting") {
			void execute() {
				System.out.printf("available formats:\n");

				int i = 0;

				for(MyFormatter<Track> myf : formatters){
					i++;
					System.out.println(i + "\t" + myf.toString());
				}

				Scanner sc = new Scanner(System.in);
				System.out.print("Select formatting: ");
				try{
					int selectedIdx = Integer.parseInt(sc.nextLine());
					if(selectedIdx > 0 && selectedIdx <= i){
						theFormat = formatters.get(selectedIdx-1);
						System.out.println(theFormat.toString() + " selected");
					}
				} catch (NumberFormatException e){
					System.out.println("Input for the index for formatters was not a number");
				}
			}
			// end of MenuItem
		},

		new MenuItem("select sorting") {
			void execute() {
				System.out.printf("available sorting styles:\n");
				for(int i = 0, j = 1; i < comparators.size(); i++, j++){
					System.out.println(j + ":\t" + comparators.get(i).toString());
				}

				Scanner sc = new Scanner(System.in);
				System.out.print("Select sorting: ");
				try{
					int sortingIdx = Integer.parseInt(sc.nextLine());
					if(sortingIdx > 0 && sortingIdx <= comparators.size()){
						theComp = comparators.get(sortingIdx-1);
						db.sort(theComp, asc);
						String text = "descending";
						if(asc){
							text = "ascending";
						}
						System.out.println("selection sorted " + theComp.toString() + "(" + text + ")");
					}
				} catch (NumberFormatException e){
					System.out.println("Input for index for sorting was not a number");
				}
			}
			// end of MenuItem
		},

		new MenuItem("reverse sorting order") {
			void execute() {
				asc = !asc;
				String text = "descending";
				if(asc){
					text = "ascending";
				}
				System.out.println(theComp.toString() + " (" + text +").");
			}
			// end of MenuItem
		},

		new MenuItem("save selection") {
			void execute() {
				try {
					Scanner sc = new Scanner(System.in);
					String fileName = sc.nextLine();
					FileWriter file = new FileWriter(fileName);
					CSVTrackFormatter csvTrackFormatter = new CSVTrackFormatter();
					MyWriter myWriter = new MyWriter<>(file, csvTrackFormatter);
					int counter = 0;

					if(db.selection().length > 0) {
						for (Track t : db.selection()) {
							if (myWriter.put(t)) {
								counter++;
							} else {
								System.out.println("Could not write track: " + csvTrackFormatter.format(t));
							}
						}
					}

					myWriter.close();

					System.out.println(counter + " tracks written");

				} catch (IOException e){
					System.out.println("Could not create new file");
				}

			}
			// end of MenuItem
		},

		new MenuItem("load") {
			void execute() {
				int counter = 0;

				Scanner sc = new Scanner(System.in);
				System.out.print("Enter file name: ");
				String fileName = sc.nextLine();
				CSVTrackFormatter csvTrackFormatter = new CSVTrackFormatter();

				try {
					FileReader frLine = new FileReader(fileName);
					BufferedReader readerForLines = new BufferedReader(frLine);

					int lines = 0;

					while(readerForLines.readLine() != null){
						lines++;
					}

					readerForLines.close();
					frLine.close();

					FileReader frData = new FileReader(fileName);
					BufferedReader readerForData = new BufferedReader(frData);
					MyTrackCSVReader myCSV = new MyTrackCSVReader(readerForData);

					Track t;
					for(int i = 0; i < lines; i++){
						if((t = myCSV.get()) != null){
							db.add(t);
							counter++;
							System.out.println(csvTrackFormatter.format(t));
						}
					}

				} catch (FileNotFoundException e) {
					System.out.println("Did not find file");
				} catch (IOException e) {
					System.out.println("Could not read file");
				}

				System.out.println(counter + " tracks imported");

				/*
				//First I wrote my own reader but I realized we already coded MyTrackCSVReader
				Scanner sc = new Scanner(System.in);
				System.out.print("Enter file name: ");

				String fileName = sc.nextLine();
				int counter = 0;

				try{
					File f = new File(fileName);
					Scanner myReader = new Scanner(f);

					while (myReader.hasNextLine()) {
						String data = myReader.nextLine();

						String fields[] = data.split(",");

						if(fields.length > 0) {
							System.out.println(data);
							Track t = new Track();

							//Title,Writer,Performer,duration,year
							String inputTitle = fields[0].trim();
							String inputWriter = fields[1].trim();
							String inputPerformer = fields[2].trim();

							if (!inputTitle.isEmpty()) {
								t.setTitle(inputTitle);
							}

							if (!inputWriter.isEmpty()) {
								t.setWriter(new Artist(inputWriter));
							}

							if (!inputPerformer.isEmpty()) {
								t.setPerformer(new Artist(inputPerformer));
							}

							if (!fields[3].trim().isEmpty()) {
								t.setDuration(Integer.parseInt(fields[3].trim()));
							}

							if (!fields[4].trim().isEmpty()) {
								int inputYear = Integer.parseInt(fields[4].trim());
								t.setYear(inputYear);
							}

							if (db.add(t)) {
								counter++;
							}
						}
					}
					myReader.close();

					System.out.println(counter + " tracks imported");

				} catch (FileNotFoundException e){
					System.out.println("File was not found");
				} catch (NumberFormatException e){
					System.out.println("The format of the CSV file is not valid");
				}*/
			}
			// end of MenuItem
		},


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
			try {
				// get choice
				System.out.print(": ");
				int input = Integer.parseInt(sc.nextLine());
				if (menu.execute(input))
					continue;

				System.out.print("exit? (1=yes)");
				if (Integer.parseInt(sc.nextLine()) == 1)
					break;
			}catch (NumberFormatException e){
				System.out.println("Please input a number from the menu");
			}
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

	public void add(){
		Track t = new Track();

		Scanner sc = new Scanner(System.in);

		System.out.print("Title: ");

		String inputTitle = sc.nextLine();

		if(!inputTitle.trim().isEmpty()){
			t.setTitle(inputTitle);
		}

		System.out.print("Duration: ");

		try{
			int inputDuration = Integer.parseInt(sc.nextLine());
			if(inputDuration >= 0){
				t.setDuration(inputDuration);
			} else {
				System.out.println("Duration was negative");
			}
		} catch (NumberFormatException e){
			System.out.println("Input for duration was not a number");
		}

		System.out.print("Year: ");

		try{
			int inputYear = Integer.parseInt(sc.nextLine());
			if(inputYear >= 1900 && inputYear <= 2999){
				t.setYear(inputYear);
			} else {
				System.out.println("Yaer was outside the valid range (1900 - 2999)");
			}
		} catch (NumberFormatException e){
			System.out.println("Input for year was not a number");
		}

		System.out.print("Writer's name: ");

		String inputWriter = sc.nextLine();
		t.setWriter(new Artist(inputWriter));


		System.out.print("Performer's name: ");

		String inputPerformer = sc.nextLine();
		t.setPerformer(new Artist(inputPerformer));

		db.add(t);

		System.out.println("Track was successfully added");
	}

	public void edit(){
		if (db.size() == 0) {
			System.out.print("no records stored.\n");
			return;
		}
		if (db.selection().length == 0) {
			System.out.print("selection empty.\n");
			return;
		}

		String text = "entry with the index 0";
		if(db.selection().length > 1){
			 text = "an entry in the index range from 0 to " + (db.selection().length-1);
		}
		System.out.println("Select " + text);
		display(db);
		Scanner sc = new Scanner(System.in);

		try{
			int idx = Integer.parseInt(sc.nextLine());
			if(idx >= 0 && idx <= db.selection().length-1){
				Track t = db.selection()[idx];
				t.scan();
			}
		} catch (NumberFormatException e){
			System.out.println("Input for duration was not a number");
		}
	}
}
