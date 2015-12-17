package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import movieDatabase.Actor;
import movieDatabase.Database;
import movieDatabase.Director;
import movieDatabase.Movie;
import movieDatabase.Review;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class Search implements ActionListener {

	private int lock=0;
	private int lock2=0;
	Database database = new Database("localhost","3306","root","rootroot");
	private JFrame frame;
	private JTextField actorFname;
	private JTextField actorLname;
	JComboBox actorGender;
	private JTextField actorSearch;
	private JButton actorInsert;
	private JButton actorClear;
	private JButton actorDelete;
	private JButton actorUpdate;
	private JList actorList;
	private DefaultListModel actorListModel;
	ArrayList<Actor> actors;
	Actor selectedActor;
	int selectedActorIndex;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JLabel actorHint;
	private JLabel actorHint1;

	private JTextField directorFname;
	private JTextField directorLname;
	JComboBox directorGender;
	private JTextField directorSearch;
	private JButton directorInsert;
	private JButton directorClear;
	private JButton directorDelete;
	private JButton directorUpdate;
	private JList directorList;
	private DefaultListModel directorListModel;
	ArrayList<Director> directors;
	Director selectedDirector;
	int selectedDirectorIndex;
	private JPanel panel_2;
	private JScrollPane scrollPane2;
	private JLabel directorHint;
	private JLabel directorHint1;
	
	private JPanel movieTab;
	private JPanel loginTab;
	private JLabel movieTitleLbl;
	private JLabel movieRatingLbl;
	private JLabel movieYearLbl;
	private JLabel movieDurationLbl;
	private JLabel movieGenreLbl;
	private JLabel movieDirectorLbl;
	private JTextField movieTitle;
	private JTextField movieRating;
	private JTextField movieYear;
	private JTextField movieDuration;
	private JTextField movieGenre;
	private JComboBox movieDirectors;
	private JTextField movieSearch;
	private JButton movieSearchBtn;
	private JButton movieInsert;
	private JButton movieClear;
	private JButton movieDelete;
	private JButton movieUpdate;
	private JButton backBtn;
	private JButton reviewClear;
	private JButton addReview;
	private JButton deleteReview;
	private JList movieList;
	private DefaultListModel movieListModel;
	private DefaultListModel reviewListModel;
	private DefaultListModel actorChoiceListModel;
	private DefaultListModel movieActorsListModel;
	private ArrayList<Movie> movies;
	private ArrayList<Director> directorChoices;
	private ArrayList<Actor> actorChoices;
	private ArrayList<Actor> movieActorChoices;
	private ArrayList<Actor> movieActors;
	private int arraySize;
	private String[] directorNames;
	private Movie selectedMovie;
	private Review selectedReview;
	private Actor selectedActorChoice;
	private Actor selectedMovieActor;
	private int selectedMovieIndex;
	private int selectedReviewIndex;
	private int selectedActorChoiceIndex;
	private int selectedMovieActorIndex;
	private JScrollPane scrollPane3;
	private JScrollPane scrollPane4;
	private JLabel movieHint;
	private JLabel movieHint1;
	private JList movieActorList;
	private JLabel movieActorLbl;
	private JList actorChoiceList;
	private JList reviewList;
	private JButton addActor;
	private JButton removeActor;
	private JButton reviewBtn;
	private JLabel fArrow1;
	private JLabel fArrow2;
	private JLabel fArrow3;
	private JLabel bArrow1;
	private JLabel bArrow2;
	private JLabel bArrow3;
	private JButton editActor;
	private JButton doneBtn;
	private JTextField reviewTitle;
	private JLabel reviewTitleLbl;
	private JLabel reviewAuthorLbl;
	private JLabel textAreaLbl;
	private JTextField reviewAuthor;
	private JTextArea textArea;
	private JScrollPane scrollPane_1;
	private ArrayList<Review> reviews;	
	private JLabel label;
	private JLabel lblNewLabel;
	private JLabel lblUsername;
	private JLabel label_1;
	private JTextArea textArea_1;
	private JTextArea textArea_2;
	private JButton btnLogin;
	private JLabel lblNewLabel_1;
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search window = new Search();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Search() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		database.connect();
		
	    ArrayList<Actor> test = database.getActors(10);
		
		for (Actor director: test) {
			System.out.println(director.getFirstName() + " " + director.getLastName());
		}
		movies = database.getMovies();
		actors = database.getActors();
		actorChoices = database.getActors();
		directors = database.getDirectors();
		movieActors = new ArrayList<Actor>();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1323, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1323, 672);
		frame.getContentPane().add(tabbedPane);
		
		//Movies
		movieListModel = new DefaultListModel();
		for(int i=0; i < movies.size(); i++)
		{
			movieListModel.addElement(movies.get(i).getTitle() + "  (" + Integer.toString(movies.get(i).getYear()) + ")");
		}
		if(database.getDirectors().size() == 0)
		{
			directorChoices = null;
			arraySize=1;
			directorNames = new String[arraySize];
			directorNames[0]="";
		}
		else
		{
			directorChoices = database.getDirectors();
			arraySize = directorChoices.size()+1;
			directorNames = new String[arraySize];
			directorNames[0]="";
			for(int i=1; i<arraySize; i++)
	    	{
				directorNames[i] = (directorChoices.get(i-1).getFirstName() + " " + directorChoices.get(i-1).getLastName());
	    	}
		}
		movieActorsListModel = new DefaultListModel();
		actorChoiceListModel = new DefaultListModel();
		for(int i=0; i < actorChoices.size(); i++)
		{
			actorChoiceListModel.addElement(actorChoices.get(i).getFirstName() + " " + actorChoices.get(i).getLastName());
		}
		
		reviewListModel = new DefaultListModel();
		actorListModel = new DefaultListModel();
		for(int i=0; i < actors.size(); i++)
		{
			actorListModel.addElement(actors.get(i).getFirstName() + " " + actors.get(i).getLastName());
		}
				
				movieTab = new JPanel();
				tabbedPane.addTab("Movies", null, movieTab, null);
				movieTab.setLayout(null);
				scrollPane3 = new JScrollPane();
				scrollPane3.setBounds(12, 94, 290, 488);
				movieTab.add(scrollPane3);
				
				movieList = new JList();
				scrollPane3.setViewportView(movieList);
				movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				movieList.setToolTipText("");
				movieList.setModel(movieListModel);
				
				movieHint = new JLabel("Hint: Clear page before");
				movieHint.setForeground(Color.RED);
				movieHint.setBounds(352, 375, 172, 25);
				movieTab.add(movieHint);
				movieHint1 = new JLabel("adding a new movie");
				movieHint1.setForeground(Color.RED);
				movieHint1.setBounds(392, 394, 152, 25);
				movieTab.add(movieHint1);
				movieHint.setVisible(false);
				movieHint1.setVisible(false);
				
				movieTitleLbl = new JLabel("Title:");
				movieTitleLbl.setBounds(474, 130, 70, 15);
				movieTab.add(movieTitleLbl);
				
				movieRatingLbl = new JLabel("Rating:");
				movieRatingLbl.setBounds(474, 155, 60, 15);
				movieTab.add(movieRatingLbl);
				
				movieYearLbl = new JLabel("Year:");
				movieYearLbl.setBounds(474, 182, 70, 15);
				movieTab.add(movieYearLbl);
				
				movieDurationLbl = new JLabel("Duration:");
				movieDurationLbl.setBounds(474, 209, 70, 15);
				movieTab.add(movieDurationLbl);
				
				movieGenreLbl = new JLabel("Genre:");
				movieGenreLbl.setBounds(474, 236, 70, 15);
				movieTab.add(movieGenreLbl);
				
				movieInsert = new JButton("Insert");
				movieInsert.setBounds(676, 608, 88, 25);
				movieInsert.addActionListener(this);
				movieInsert.setActionCommand("insertMovie");
				movieTab.add(movieInsert);
				
				movieDelete = new JButton("Delete");
				movieDelete.setBounds(676, 608, 88, 25);
				movieDelete.addActionListener(this);
				movieDelete.setActionCommand("deleteMovie");
				movieTab.add(movieDelete);
				
				movieClear = new JButton("Clear");
				movieClear.setBounds(576, 608, 88, 25);
				movieClear.addActionListener(this);
				movieClear.setActionCommand("clearMovie");
				movieTab.add(movieClear);
				
				movieDuration = new JTextField();
				movieDuration.setBounds(562, 207, 88, 19);
				movieTab.add(movieDuration);
				movieDuration.setColumns(10);
				
				movieSearch = new JTextField();
				movieSearch.setColumns(10);
				movieSearch.setBounds(12, 45, 290, 19);
				movieSearch.addActionListener(this);
				movieSearch.setActionCommand("searchMovie");
				movieTab.add(movieSearch);
				
				movieYear = new JTextField();
				movieYear.setColumns(10);
				movieYear.setBounds(562, 180, 60, 19);
				movieTab.add(movieYear);
				
				movieGenre = new JTextField();
				movieGenre.setColumns(10);
				movieGenre.setBounds(562, 234, 165, 19);
				movieTab.add(movieGenre);
				
				movieRating = new JTextField();
				movieRating.setColumns(10);
				movieRating.setBounds(562, 153, 88, 19);
				movieTab.add(movieRating);
				
				movieTitle = new JTextField();
				movieTitle.setColumns(10);
				movieTitle.setBounds(562, 128, 230, 19);
				movieTab.add(movieTitle);
				
				movieSearchBtn = new JButton("Search");
				movieSearchBtn.setBounds(314, 42, 88, 25);
				movieTab.add(movieSearchBtn);
				
				movieDirectors = new JComboBox();
				movieDirectors.setBounds(562, 258, 247, 24);
				
				movieDirectors.setModel(new DefaultComboBoxModel(directorNames));
				movieTab.add(movieDirectors);
				
				movieDirectorLbl = new JLabel("Director:");
				movieDirectorLbl.setBounds(474, 263, 70, 15);
				movieTab.add(movieDirectorLbl);
				
				movieUpdate = new JButton("Update");
				movieUpdate.setBounds(776, 608, 88, 25);
				movieUpdate.addActionListener(this);
				movieUpdate.setActionCommand("updateMovie");
				movieTab.add(movieUpdate);
				
				addActor = new JButton("Add Actor");
				addActor.addActionListener(this);
				addActor.setActionCommand("addActor");
				addActor.setBounds(776, 343, 142, 25);
				movieTab.add(addActor);
				
				removeActor = new JButton("Remove Actor");
				removeActor.addActionListener(this);
				removeActor.setActionCommand("removeActor");
				removeActor.setBounds(776, 343, 142, 25);
				movieTab.add(removeActor);
				
				movieActorList = new JList();
				movieActorList.setToolTipText("");
				movieActorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				movieActorList.setBounds(562, 294, 202, 298);
				movieTab.add(movieActorList);
				
				movieActorLbl = new JLabel("Actors:");
				movieActorLbl.setBounds(474, 290, 70, 15);
				movieTab.add(movieActorLbl);
				
				actorChoiceList = new JList();
				actorChoiceList.setToolTipText("");
				actorChoiceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				actorChoiceList.setBounds(930, 294, 202, 298);
				actorChoiceList.setModel(actorChoiceListModel);
				movieTab.add(actorChoiceList);
				
				fArrow1 = new JLabel("------------------------>");
				fArrow1.setVisible(false);
				fArrow1.setBounds(786, 380, 132, 15);
				
				fArrow2 = new JLabel("------------------------>");
				fArrow2.setVisible(false);
				fArrow2.setBounds(782, 473, 136, 15);
				
				fArrow3 = new JLabel("------------------------>");
				fArrow3.setVisible(false);
				fArrow3.setBounds(782, 567, 136, 15);
				
				bArrow1 = new JLabel("<------------------------");
				bArrow1.setVisible(false);
				bArrow1.setBounds(786, 380, 132, 15);
				
				bArrow2 = new JLabel("<------------------------");
				bArrow2.setVisible(false);
				bArrow2.setBounds(782, 473, 136, 15);
				
				bArrow3 = new JLabel("<------------------------");
				bArrow3.setVisible(false);
				bArrow3.setBounds(782, 567, 130, 15);
				
				movieTab.add(fArrow1);
				movieTab.add(fArrow2);
				movieTab.add(fArrow3);
				movieTab.add(bArrow1);
				movieTab.add(bArrow2);
				movieTab.add(bArrow3);
				
				editActor = new JButton("Edit Actor");
				editActor.addActionListener(this);
				editActor.setActionCommand("editActor");
				editActor.setBounds(776, 306, 142, 25);
				movieTab.add(editActor);
				
				doneBtn = new JButton("Done");
				doneBtn.addActionListener(this);
				doneBtn.setActionCommand("done");
				doneBtn.setBounds(776, 306, 142, 25);
				movieTab.add(doneBtn);
				
				movieDelete.setEnabled(false);
				movieDelete.setVisible(false);
				movieUpdate.setEnabled(false);
				movieUpdate.setVisible(false);
				addActor.setEnabled(false);
				addActor.setVisible(false);
				removeActor.setEnabled(false);
				removeActor.setVisible(false);
				doneBtn.setEnabled(false);
				doneBtn.setVisible(false);
				movieActorList.setEnabled(false);
				actorChoiceList.setEnabled(false);
				actorChoiceList.setVisible(false);
				
				//Reviews
				scrollPane4 = new JScrollPane();
				scrollPane4.setBounds(12, 94, 290, 488);
				movieTab.add(scrollPane4);
				reviewList = new JList();
				scrollPane4.setViewportView(reviewList);
				reviewList.setBounds(12, 91, 290, 491);
				reviewList.setEnabled(false);
				reviewList.setVisible(false);
				scrollPane4.setEnabled(false);
				scrollPane4.setVisible(false);
				reviewList.setModel(reviewListModel);
				
				reviewBtn = new JButton("Check Reviews");
				reviewBtn.setBackground(Color.ORANGE);
				reviewBtn.setForeground(Color.BLACK);
				reviewBtn.setEnabled(false);
				reviewBtn.setVisible(false);
				reviewBtn.addActionListener(this);
				reviewBtn.setActionCommand("gotoReviews");
				reviewBtn.setBounds(990, 125, 142, 25);
				movieTab.add(reviewBtn);
				
				reviewTitle = new JTextField("");
				reviewTitle.setBounds(474, 130, 434, 15);
				reviewTitle.setEnabled(false);
				reviewTitle.setVisible(false);
				reviewTitle.setDisabledTextColor(Color.black);
				movieTab.add(reviewTitle);
				
				reviewAuthor = new JTextField("");
				reviewAuthor.setBounds(474, 157, 254, 15);
				reviewAuthor.setEnabled(false);
				reviewAuthor.setVisible(false);
				reviewAuthor.setDisabledTextColor(Color.black);
				movieTab.add(reviewAuthor);
				
				textArea = new JTextArea();
				textArea.setBounds(474, 182, 658, 414);
				textArea.setEnabled(false);
				textArea.setVisible(false);
				textArea.setDisabledTextColor(Color.black);
				movieTab.add(textArea);
				
				backBtn = new JButton("Back");
				backBtn.addActionListener(this);
				backBtn.setBounds(474, 608, 117, 25);
				backBtn.setEnabled(false);
				backBtn.setVisible(false);
				movieTab.add(backBtn);
				
				reviewClear = new JButton("Clear");
				reviewClear.addActionListener(this);
				reviewClear.setActionCommand("clearReview");
				reviewClear.setBounds(610, 608, 117, 25);
				reviewClear.setEnabled(false);
				reviewClear.setVisible(false);
				movieTab.add(reviewClear);
				
				addReview = new JButton("Add Review");
				addReview.addActionListener(this);
				addReview.setActionCommand("addReview");
				addReview.setBounds(747, 608, 150, 25);
				addReview.setEnabled(false);
				addReview.setVisible(false);
				movieTab.add(addReview);
				
				deleteReview = new JButton("Delete Review");
				deleteReview.addActionListener(this);
				deleteReview.setActionCommand("deleteReview");
				deleteReview.setBounds(747, 608, 150, 25);
				deleteReview.setEnabled(false);
				deleteReview.setVisible(false);
				movieTab.add(deleteReview);
				
				reviewTitleLbl = new JLabel("Title:");
				reviewTitleLbl.setBounds(362, 130, 60, 15);
				reviewTitleLbl.setEnabled(false);
				reviewTitleLbl.setVisible(false);
				movieTab.add(reviewTitleLbl);
				
				reviewAuthorLbl = new JLabel("Author:");
				reviewAuthorLbl.setBounds(362, 155, 60, 15);
				reviewAuthorLbl.setEnabled(false);
				reviewAuthorLbl.setVisible(false);
				movieTab.add(reviewAuthorLbl);
				
				textAreaLbl = new JLabel("Body");
				textAreaLbl.setEnabled(false);
				textAreaLbl.setVisible(false);
				textAreaLbl.setBounds(362, 182, 94, 15);
				movieTab.add(textAreaLbl);
				
				movieList.addListSelectionListener(new ListSelectionListener() {
					 
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && lock == 0) {
                	
                	selectedMovieIndex = movieList.getSelectedIndex();
                	selectedMovie = movies.get(selectedMovieIndex);
                	movieActors = database.getActors(selectedMovie.getId());
                	
                	movieTitle.setText(selectedMovie.getTitle());
                	movieRating.setText(selectedMovie.getParentalRating());
                	movieYear.setText(Integer.toString(selectedMovie.getYear()));
                	movieDuration.setText(Integer.toString(selectedMovie.getDuration()));
                	movieGenre.setText(selectedMovie.getGenre());
                	for(int i=0; i<directorChoices.size(); i++)
                	{
                		if(directorChoices.get(i).getId() == selectedMovie.getDirectorId())
                		{
                			movieDirectors.setSelectedIndex(i+1);
                			break;
                		}
                	}             
                	
                	movieActors=database.getActors(selectedMovie.getId());
                	movieActorsListModel = new DefaultListModel();
                	for(int i=0; i<movieActors.size();i++)
                		movieActorsListModel.addElement(movieActors.get(i).getFirstName() + " " + movieActors.get(i).getLastName());
                	movieActorList.setModel(movieActorsListModel);
                	movieHint.setVisible(true);
                	movieHint1.setVisible(true);
                	movieInsert.setEnabled(false);
                	movieInsert.setVisible(false);
                	actorChoiceList.setEnabled(false);
                	actorChoiceList.setVisible(false);
                	addActor.setEnabled(false);
                	addActor.setVisible(false);
                	removeActor.setEnabled(false);
                	removeActor.setVisible(false);
                	doneBtn.setEnabled(false);
                	doneBtn.setVisible(false);
                	movieActorList.setEnabled(false);
                	fArrow1.setEnabled(false);
                    fArrow1.setVisible(false);
                    fArrow2.setEnabled(false);
                    fArrow2.setVisible(false);
                    fArrow3.setEnabled(false);
                    fArrow3.setVisible(false);
                    bArrow1.setEnabled(false);
                    bArrow1.setVisible(false);
                    bArrow2.setEnabled(false);
                    bArrow2.setVisible(false);
                    bArrow3.setEnabled(false);
                    bArrow3.setVisible(false);

                    reviewBtn.setEnabled(true);
            		reviewBtn.setVisible(true);
                	movieDelete.setEnabled(true);
                	movieDelete.setVisible(true);
                	movieUpdate.setEnabled(true);
                	movieUpdate.setVisible(true);
                	editActor.setEnabled(true);
                	editActor.setVisible(true);
                }
            }
				});
				
				actorChoiceList.addListSelectionListener(new ListSelectionListener() {
					 
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && lock == 0) {
                	
                	actorChoices = database.getActors();
                	selectedActorChoiceIndex = actorChoiceList.getSelectedIndex();
                	selectedActorChoice = actorChoices.get(selectedActorChoiceIndex);
                }
                bArrow1.setEnabled(true);
                bArrow1.setVisible(true);
                bArrow2.setEnabled(true);
                bArrow2.setVisible(true);
                bArrow3.setEnabled(true);
                bArrow3.setVisible(true);
                addActor.setEnabled(true);
                addActor.setVisible(true);
                removeActor.setEnabled(false);
                removeActor.setVisible(false);
                fArrow1.setEnabled(false);
                fArrow1.setVisible(false);
                fArrow2.setEnabled(false);
                fArrow2.setVisible(false);
                fArrow3.setEnabled(false);
                fArrow3.setVisible(false);
            }
				});
				
				movieActorList.addListSelectionListener(new ListSelectionListener() {
					 
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && lock == 0) {
                	selectedMovieActorIndex = movieActorList.getSelectedIndex();
                	selectedMovieActor = movieActors.get(selectedMovieActorIndex);
                }
                addActor.setEnabled(false);
                addActor.setVisible(false);
                if(!editActor.isVisible())
                {
                	removeActor.setEnabled(true);
                	removeActor.setVisible(true);
                	fArrow1.setEnabled(true);
                    fArrow1.setVisible(true);
                    fArrow2.setEnabled(true);
                    fArrow2.setVisible(true);
                    fArrow3.setEnabled(true);
                    fArrow3.setVisible(true);
                    bArrow1.setEnabled(false);
                    bArrow1.setVisible(false);
                    bArrow2.setEnabled(false);
                    bArrow2.setVisible(false);
                    bArrow3.setEnabled(false);
                    bArrow3.setVisible(false);
                }
            }
				});
				
				reviewList.addListSelectionListener(new ListSelectionListener() {
					 
		            public void valueChanged(ListSelectionEvent event) {
		                if (!event.getValueIsAdjusting() && lock2==0) {
		                	
		                	selectedReviewIndex = reviewList.getSelectedIndex();
		                	selectedReview = reviews.get(selectedReviewIndex);
		                	
		                	reviewTitle.setText(selectedReview.getTitle());
		                	reviewAuthor.setText(selectedReview.getAuthor());
		                	textArea.setText(selectedReview.getReview());
		                
		                	addReview.setEnabled(false);
		                	addReview.setVisible(false);
		                	reviewTitle.setEnabled(false);
		                	reviewAuthor.setEnabled(false);
		                	textArea.setEnabled(false);
		               
		                	deleteReview.setEnabled(true);
		                	deleteReview.setVisible(true);
		                }
		            }
						});
				

				
				
				
				
				
				//Actors
				JPanel actorTab = new JPanel();
				actorTab.setForeground(Color.RED);
				tabbedPane.addTab("Actors", null, actorTab, null);
				actorTab.setLayout(null);
				
				JButton actorSearchBtn = new JButton("Search");
				actorSearchBtn.setBounds(321, 28, 94, 25);
				actorTab.add(actorSearchBtn);
				
				actorInsert = new JButton("Insert");
				actorInsert.setBounds(568, 343, 82, 25);
				actorInsert.setActionCommand("insertActor");
				actorInsert.addActionListener(this);
				actorInsert.setToolTipText("Click this button to "
                + "insert a new actor.");
				actorTab.add(actorInsert);
				
				actorDelete = new JButton("Delete");
				actorDelete.addActionListener(this);
				actorDelete.setToolTipText("Click this button to delete a actor.");
				actorDelete.setActionCommand("deleteActor");
				actorDelete.setBounds(568, 343, 82, 25);
				actorTab.add(actorDelete);
				
				actorClear = new JButton("Clear");
				actorClear.setBounds(468, 343, 88, 25);
				actorClear.setActionCommand("clearActor");
				actorClear.addActionListener(this);
				actorClear.setToolTipText("Click this button to "
                + "clear this page.");
				actorTab.add(actorClear);
				
				actorUpdate = new JButton("Update");
				actorUpdate.addActionListener(this);
				actorUpdate.setToolTipText("Click this button to update a actor.");
				actorUpdate.setActionCommand("updateActor");
				actorUpdate.setBounds(662, 343, 94, 25);
				actorTab.add(actorUpdate);
				
				actorDelete.setEnabled(false);
				actorDelete.setVisible(false);
				actorUpdate.setEnabled(false);
				actorUpdate.setVisible(false);
				
				
				JLabel actorFnameLbl = new JLabel("First Name:");
				actorFnameLbl.setBounds(474, 130, 95, 15);
				actorTab.add(actorFnameLbl);
				
				JLabel actorLnameLbl = new JLabel("Last Name:");
				actorLnameLbl.setBounds(474, 157, 95, 15);
				actorTab.add(actorLnameLbl);
				
				JLabel actorGenderLbl = new JLabel("Gender:");
				actorGenderLbl.setBounds(474, 184, 95, 15);
				actorTab.add(actorGenderLbl);
				
				actorFname = new JTextField();
				actorFname.setBounds(574, 128, 182, 19);
				actorTab.add(actorFname);
				actorFname.setColumns(10);
				
				actorLname = new JTextField();
				actorLname.setColumns(10);
				actorLname.setBounds(574, 155, 182, 19);
				actorTab.add(actorLname);
				
	    actorGender = new JComboBox();
	    actorGender.setModel(new DefaultComboBoxModel(new String[] {" ", "Male", "Female"}));
	    actorGender.setBounds(574, 179, 86, 24);
	    actorTab.add(actorGender);	
	    
	    
	    actorSearch = new JTextField();
	    actorSearch.setColumns(10);
	    actorSearch.setBounds(12, 30, 297, 22);
	    actorTab.add(actorSearch);
	    
	    actorHint = new JLabel("Hint: Clear page before");
	    actorHint.setForeground(Color.RED);
	    actorHint.setBounds(364, 231, 172, 25);
	    actorTab.add(actorHint);
	    actorHint1 = new JLabel("adding a new actor");
	    actorHint1.setForeground(Color.RED);
	    actorHint1.setBounds(404, 250, 152, 25);
	    actorTab.add(actorHint1);
	    
	    scrollPane_1 = new JScrollPane();
	    scrollPane_1.setBounds(12, 65, 297, 303);
	    actorTab.add(scrollPane_1);
	    
	    actorList = new JList();
	    scrollPane_1.setViewportView(actorList);
	    actorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    actorList.setToolTipText("");
	    actorList.setModel(actorListModel);
	    
	    actorList.addListSelectionListener(new ListSelectionListener() {
	    	 
	            public void valueChanged(ListSelectionEvent event) {
	                if (!event.getValueIsAdjusting() && lock == 0) {
	                	
	                	int index;
	                	String gender;
	                	actors = database.getActors();
	                	selectedActorIndex = actorList.getSelectedIndex();
	                	selectedActor = actors.get(selectedActorIndex);
	                	
	                	actorFname.setText(selectedActor.getFirstName());
	                	actorLname.setText(selectedActor.getLastName());
	                	if(selectedActor.getGender().equals("M"))
	                	{
	                		index=1;
	                		gender = "M";
	                	}
	                	else if(selectedActor.getGender().equals("F"))
	                	{
	                		index=2;
	                		gender = "F";
	                	}
	                	else
	                	{
	                		index=0;
	                		gender="?";
	                	}
	                	actorGender.setSelectedIndex(index);
	                	
	                	
	                	actorHint.setVisible(true);
	                	actorHint1.setVisible(true);
	                	actorInsert.setEnabled(false);
	                	actorInsert.setVisible(false);
	             
	                	
	                	actorDelete.setEnabled(true);
	            		actorDelete.setVisible(true);
	            		actorUpdate.setEnabled(true);
	            		actorUpdate.setVisible(true);
	            	}
	            }
	        });
	    actorHint.setVisible(false);
	    actorHint1.setVisible(false);
		
		
		
		
				//Director
				JPanel directorTab = new JPanel();
				directorTab.setForeground(Color.RED);
				tabbedPane.addTab("Directors", null, directorTab, null);
				directorTab.setLayout(null);
				
				JButton directorSearchBtn = new JButton("Search");
				directorSearchBtn.setBounds(242, 10, 94, 25);
				directorTab.add(directorSearchBtn);
				
				directorInsert = new JButton("Insert");
				directorInsert.setBounds(242, 209, 82, 25);
				directorInsert.setActionCommand("insertDirector");
				directorInsert.addActionListener(this);
				directorInsert.setToolTipText("Click this button to "
		                + "insert a new director.");
				directorTab.add(directorInsert);
				
				directorDelete = new JButton("Delete");
				directorDelete.addActionListener(this);
				directorDelete.setToolTipText("Click this button to delete a director.");
				directorDelete.setActionCommand("deleteDirector");
				directorDelete.setBounds(242, 209, 82, 25);
				directorTab.add(directorDelete);
				
				directorClear = new JButton("Clear");
				directorClear.setBounds(154, 209, 82, 25);
				directorClear.setActionCommand("clearDirector");
				directorClear.addActionListener(this);
				directorClear.setToolTipText("Click this button to "
		                + "clear this page.");
				directorTab.add(directorClear);
				
				directorUpdate = new JButton("Update");
				directorUpdate.addActionListener(this);
				directorUpdate.setToolTipText("Click this button to update a director.");
				directorUpdate.setActionCommand("updateDirector");
				directorUpdate.setBounds(328, 209, 94, 25);
				directorTab.add(directorUpdate);
				
				directorDelete.setEnabled(false);
				directorDelete.setVisible(false);
				directorUpdate.setEnabled(false);
				directorUpdate.setVisible(false);
				
				
				JLabel directorFnameLbl = new JLabel("First Name:");
				directorFnameLbl.setBounds(154, 68, 95, 15);
				directorTab.add(directorFnameLbl);
				
				JLabel directorLnameLbl = new JLabel("Last Name:");
				directorLnameLbl.setBounds(154, 95, 95, 15);
				directorTab.add(directorLnameLbl);
				
				JLabel directorGenderLbl = new JLabel("Gender:");
				directorGenderLbl.setBounds(154, 122, 95, 15);
				directorTab.add(directorGenderLbl);
				
				directorFname = new JTextField();
				directorFname.setBounds(242, 66, 142, 19);
				directorTab.add(directorFname);
				directorFname.setColumns(10);
				
				directorLname = new JTextField();
				directorLname.setColumns(10);
				directorLname.setBounds(242, 95, 142, 19);
				directorTab.add(directorLname);
				
			    directorGender = new JComboBox();
				directorGender.setModel(new DefaultComboBoxModel(new String[] {" ", "Male", "Female"}));
				directorGender.setBounds(245, 121, 86, 24);
				directorTab.add(directorGender);	
				
				
				directorSearch = new JTextField();
				directorSearch.setColumns(10);
				directorSearch.setBounds(12, 12, 212, 22);
				directorTab.add(directorSearch);
				directorListModel = new DefaultListModel();
				for(int i=0; i < directors.size(); i++)
				{
					directorListModel.addElement(directors.get(i).getFirstName() + " " + directors.get(i).getLastName());
				}
				
				panel_1 = new JPanel();
				panel_1.setBounds(12, 66, 130, 167);
				directorTab.add(panel_1);
				panel_1.setLayout(null);
				scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 0, 130, 167);
				panel_1.add(scrollPane);
				
				directorList = new JList();
				scrollPane.setViewportView(directorList);
				directorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				directorList.setToolTipText("");
				directorList.setModel(directorListModel);
				
				directorHint = new JLabel("Hint: Clear page before");
				directorHint.setForeground(Color.RED);
				directorHint.setBounds(154, 149, 230, 25);
				directorTab.add(directorHint);
				directorHint1 = new JLabel("adding a new director");
				directorHint1.setForeground(Color.RED);
				directorHint1.setBounds(192, 172, 230, 25);
				directorTab.add(directorHint1);
				directorHint.setVisible(false);
				directorHint1.setVisible(false);
				
				directorList.addListSelectionListener(new ListSelectionListener() {
					 
			            public void valueChanged(ListSelectionEvent event) {
			                if (!event.getValueIsAdjusting() && lock == 0) {
			                	
			                	int index;
			                	String gender;
			                	directors = database.getDirectors();
			                	selectedDirectorIndex = directorList.getSelectedIndex();
			                	selectedDirector = directors.get(selectedDirectorIndex);
			                	
			                	directorFname.setText(selectedDirector.getFirstName());
			                	directorLname.setText(selectedDirector.getLastName());
			                	if(selectedDirector.getGender().equals("M"))
			                	{
			                		index=1;
			                		gender = "M";
			                	}
			                	else if(selectedDirector.getGender().equals("F"))
			                	{
			                		index=2;
			                		gender = "F";
			                	}
			                	else
			                	{
			                		index=0;
			                		gender="?";
			                	}
			                	directorGender.setSelectedIndex(index);
			                	
			                	
			                	directorHint.setVisible(true);
			                	directorHint1.setVisible(true);
			                	directorInsert.setEnabled(false);
			                	directorInsert.setVisible(false);
			                	
			                	directorDelete.setEnabled(true);
			            		directorDelete.setVisible(true);
			            		directorUpdate.setEnabled(true);
			            		directorUpdate.setVisible(true);
			                }
			            }
			        });
	}
	
	public void actionPerformed(ActionEvent e) {
		
		//Movie functions
				if("insertMovie".equals(e.getActionCommand())) {
					lock=1;
					Movie newMovie;
					int directorId=0;
					for(int i=0; i<directorChoices.size(); i++)
                	{
                		if((directorChoices.get(i).getFirstName() + " " + directorChoices.get(i).getLastName()).equals(movieDirectors.getSelectedItem()))
                		{
                			directorId = directorChoices.get(i).getId();
                			System.out.println(directorId);
                			break;
                		}
                	} 	
					newMovie = new Movie(movieTitle.getText(), movieRating.getText(), Integer.parseInt(movieYear.getText()), Integer.parseInt(movieDuration.getText()), movieGenre.getText(), directorId);
					int movieId = database.insertMovie(newMovie);
					database.updateMovieActors(movieActors, movieId);
					
					//refresh list
					movies = database.getMovies();
					movieListModel = new DefaultListModel();
					for(int i=0; i < movies.size(); i++)
					{
						movieListModel.addElement(movies.get(i).getTitle() + "  (" + Integer.toString(movies.get(i).getYear()) + ")");
					}
					if(selectedMovie == null)
					{
						selectedMovie = newMovie;
					}
					movieList.setModel(movieListModel);
					movieTitle.setText("");
					movieRating.setText("");
					movieYear.setText("");
					movieDuration.setText("");
					movieGenre.setText("");
					movieDirectors.setSelectedIndex(0);
					resetMovies();
					lock=0;	
				}
				
				if("deleteMovie".equals(e.getActionCommand())) {
					lock=1;
					//delete movie
					database.deleteMovie(selectedMovie);
					
					//refresh list
					movies = database.getMovies();
					movieListModel = new DefaultListModel();
					for(int i=0; i < movies.size(); i++)
					{
						movieListModel.addElement(movies.get(i).getTitle() + "  (" + Integer.toString(movies.get(i).getYear()) + ")");
					}
					if(selectedMovieIndex != 0)
					{
					if(selectedMovieIndex < movies.size()-1)	selectedMovieIndex++;
					else selectedMovieIndex = movies.size()-1;
					movieList.setSelectedIndex(selectedMovieIndex);
					selectedMovie = movies.get(selectedMovieIndex);
					}
					else 
					{
						selectedMovieIndex=0;
						movieList.setSelectedIndex(0);
						selectedMovie = null;
					}
					movieList.setModel(movieListModel);
					movieTitle.setText("");
					movieRating.setText("");
					movieYear.setText("");
					movieDuration.setText("");
					movieGenre.setText("");
					movieDirectors.setSelectedIndex(0);
					resetMovies();
					lock=0;	
				}
				//update movie
				if("updateMovie".equals(e.getActionCommand())) 
				{
					lock=1;
					int directorId=0;
					for(int i=0; i<directorChoices.size(); i++)
                	{
                		if((directorChoices.get(i).getFirstName() + " " + directorChoices.get(i).getLastName()).equals(movieDirectors.getSelectedItem()))
                		{
                			directorId = directorChoices.get(i).getId();
                			System.out.println(directorId);
                			break;
                		}
                	} 	
					System.out.println("START");
					for(int i = 0; i< movieActors.size(); i++)
						System.out.println(movieActors.get(i).getId());
					selectedMovie.setTitle(movieTitle.getText());
					selectedMovie.setParentalRating(movieRating.getText());
					selectedMovie.setYear(Integer.parseInt(movieYear.getText()));
					selectedMovie.setDuration(Integer.parseInt(movieDuration.getText()));
					selectedMovie.setGenre(movieGenre.getText());
					selectedMovie.setDirectorId(directorId);
					database.updateMovie(selectedMovie);
					database.updateMovieActors(movieActors, selectedMovie.getId());
					
					//refresh list
					movies = database.getMovies();
					movieListModel = new DefaultListModel();
					for(int i=0; i < movies.size(); i++)
					{
						movieListModel.addElement(movies.get(i).getTitle() + "  (" + Integer.toString(movies.get(i).getYear()) + ")");
					}
					if(selectedMovieIndex != 0)
					{
					if(selectedMovieIndex < movies.size()-1)	selectedMovieIndex++;
					else selectedMovieIndex = movies.size()-1;
					movieList.setSelectedIndex(selectedMovieIndex);
					selectedMovie = movies.get(selectedMovieIndex);
					}
					else 
					{
						selectedMovieIndex=0;
						movieList.setSelectedIndex(0);
						selectedMovie = null;
					}
					movieList.setModel(movieListModel);
					movieTitle.setText("");
					movieRating.setText("");
					movieYear.setText("");
					movieDuration.setText("");
					movieGenre.setText("");
					movieDirectors.setSelectedIndex(0);
					resetMovies();
					lock=0;	
				}
				//search movie
				if("searchMovie".equals(e.getActionCommand())) {
					lock=1;
					movies = database.getMovies(movieSearch.getText());
					movieListModel = new DefaultListModel();
					for(int i=0; i < movies.size(); i++)
					{
						movieListModel.addElement(movies.get(i).getTitle() + "  (" + Integer.toString(movies.get(i).getYear()) + ")");
					}
					
					movieList.setModel(movieListModel);
					movieTitle.setText("");
					movieRating.setText("");
					movieYear.setText("");
					movieDuration.setText("");
					movieGenre.setText("");
					movieDirectors.setSelectedIndex(0);
					lock=0;
				}
				//clear movie
				if("clearMovie".equals(e.getActionCommand())) {
					lock=1;
					movieTitle.setText("");
					movieRating.setText("");
					movieYear.setText("");
					movieDuration.setText("");
					movieGenre.setText("");
					movieDirectors.setSelectedIndex(0);
					movieHint.setVisible(false);
					movieHint1.setVisible(false);
		        	movieInsert.setEnabled(true);
		        	movieInsert.setVisible(true);
		        	movieDelete.setEnabled(false);
		        	movieDelete.setVisible(false);
		        	movieUpdate.setEnabled(false);
		        	movieUpdate.setVisible(false);
		        	
                	actorChoiceList.setEnabled(false);
                	actorChoiceList.setVisible(false);
                	addActor.setEnabled(false);
                	addActor.setVisible(false);
                	removeActor.setEnabled(false);
                	removeActor.setVisible(false);
                	doneBtn.setEnabled(false);
                	doneBtn.setVisible(false);
                	editActor.setEnabled(true);
                	editActor.setVisible(true);
                	movieActors = new ArrayList<Actor>();
                	movieActorsListModel = new DefaultListModel();
                	movieActorList.setModel(movieActorsListModel);
                	resetMovies();
                	lock=0;
				}
			//edit movie actor
			if("editActor".equals(e.getActionCommand())) {
				lock=1;
				editActor.setEnabled(false);
				editActor.setVisible(false);
				doneBtn.setEnabled(true);
				doneBtn.setVisible(true);
				actorChoiceList.setEnabled(true);
				actorChoiceList.setVisible(true);
				movieActorList.setEnabled(true);
				lock=0;
			}
			//add movie actor
			if("addActor".equals(e.getActionCommand())) {
				lock=1;
					for(int i=0; i<actorChoices.size(); i++)
					{
						if((actorChoices.get(i).getFirstName() + " " + actorChoices.get(i).getLastName()).equals(actorChoiceList.getSelectedValue()))
						{
							movieActors.add(actorChoices.get(i));
							movieActorsListModel.addElement(actorChoices.get(i).getFirstName() + " " + actorChoices.get(i).getLastName());
							break;
						}
					}
					movieActorList.setModel(movieActorsListModel);
					lock=0;
			}
			//remove movie actor
			if("removeActor".equals(e.getActionCommand())) {
				lock=1;
				for(int i=0; i<actorChoices.size(); i++)
				{	
					if((actorChoices.get(i).getFirstName() + " " + actorChoices.get(i).getLastName()).equals(movieActorList.getSelectedValue()))
					{
						movieActorsListModel.removeElement(actorChoices.get(i).getFirstName() + " " + actorChoices.get(i).getLastName());
						movieActors.remove(selectedMovieActorIndex);
						//movieActors.remove(actorChoices.get(i).getFirstName() + " " + actorChoices.get(i).getLastName());
						break;
					}
				}
				movieActorList.setModel(movieActorsListModel);		
				lock=0;
			}
			if("done".equals(e.getActionCommand())) {
				addActor.setEnabled(false);
				addActor.setVisible(false);
				removeActor.setEnabled(false);
				removeActor.setVisible(false);
				doneBtn.setEnabled(false);
				doneBtn.setVisible(false);
				actorChoiceList.setEnabled(false);
				actorChoiceList.setVisible(false);
				editActor.setEnabled(true);
				editActor.setVisible(true);
				movieActorList.setEnabled(false);
				fArrow1.setEnabled(false);
                fArrow1.setVisible(false);
                fArrow2.setEnabled(false);
                fArrow2.setVisible(false);
                fArrow3.setEnabled(false);
                fArrow3.setVisible(false);
                bArrow1.setEnabled(false);
                bArrow1.setVisible(false);
                bArrow2.setEnabled(false);
                bArrow2.setVisible(false);
                bArrow3.setEnabled(false);
                bArrow3.setVisible(false);
			}
			if("gotoReviews".equals(e.getActionCommand())) {
				lock=1;
				movieTitle.setEnabled(false);
				movieTitle.setVisible(false);
				movieTitleLbl.setEnabled(false);
				movieTitleLbl.setVisible(false);
				movieRating.setEnabled(false);
				movieRating.setVisible(false);
				movieRatingLbl.setEnabled(false);
				movieRatingLbl.setVisible(false);
				movieYear.setEnabled(false);
				movieYear.setVisible(false);
				movieYearLbl.setEnabled(false);
				movieYearLbl.setVisible(false);
				movieDuration.setEnabled(false);
				movieDuration.setVisible(false);
				movieDurationLbl.setEnabled(false);
				movieDurationLbl.setVisible(false);
				movieDuration.setEnabled(false);
				movieDuration.setVisible(false);
				movieDurationLbl.setEnabled(false);
				movieDurationLbl.setVisible(false);
				movieGenre.setEnabled(false);
				movieGenre.setVisible(false);
				movieGenreLbl.setEnabled(false);
				movieGenreLbl.setVisible(false);
				movieDirectors.setEnabled(false);
				movieDirectors.setVisible(false);
				movieDirectorLbl.setEnabled(false);
				movieDirectorLbl.setVisible(false);
				addActor.setEnabled(false);
				addActor.setVisible(false);
				removeActor.setEnabled(false);
				removeActor.setVisible(false);
				doneBtn.setEnabled(false);
				doneBtn.setVisible(false);
				movieInsert.setEnabled(false);
				movieInsert.setVisible(false);
				movieDelete.setEnabled(false);
				movieDelete.setVisible(false);
				movieClear.setEnabled(false);
				movieClear.setVisible(false);
				movieUpdate.setEnabled(false);
				movieUpdate.setVisible(false);
				actorChoiceList.setEnabled(false);
				actorChoiceList.setVisible(false);
				editActor.setEnabled(false);
				editActor.setVisible(false);
				movieActors=new ArrayList<Actor>();
				movieActorsListModel = new DefaultListModel();
				movieActorList.setModel(movieActorsListModel);
				movieActorList.setEnabled(false);
				movieActorList.setVisible(false);
				fArrow1.setEnabled(false);
		        fArrow1.setVisible(false);
		        fArrow2.setEnabled(false);
		        fArrow2.setVisible(false);
		        fArrow3.setEnabled(false);
		        fArrow3.setVisible(false);
		        bArrow1.setEnabled(false);
		        bArrow1.setVisible(false);
		        bArrow2.setEnabled(false);
		        bArrow2.setVisible(false);
		        bArrow3.setEnabled(false);
		        bArrow3.setVisible(false);
		        movieSearch.setEnabled(false);
		        movieSearch.setVisible(false);
		        movieSearchBtn.setEnabled(false);
		        movieSearchBtn.setVisible(false);
		        movieActorLbl.setEnabled(false);
		        movieActorLbl.setVisible(false);
		        movieHint.setEnabled(false);
		        movieHint.setVisible(false);
		        movieHint1.setEnabled(false);
		        movieHint1.setVisible(false);
				movieList.setEnabled(false);
				movieList.setVisible(false);
				scrollPane3.setEnabled(false);
				scrollPane3.setVisible(false);
				reviewBtn.setEnabled(false);
				reviewBtn.setVisible(false);
				movieTitle.setText("");
				movieRating.setText("");
				movieYear.setText("");
				movieDuration.setText("");
				movieGenre.setText("");
				movieDirectors.setSelectedIndex(0);
				
				//New scene
				reviewList.setEnabled(true);
				reviewList.setVisible(true);
				scrollPane4.setEnabled(true);
				scrollPane4.setVisible(true);
				reviewTitle.setEnabled(true);
				reviewTitle.setVisible(true);
				reviewTitleLbl.setEnabled(true);
				reviewTitleLbl.setVisible(true);
				reviewAuthor.setEnabled(true);
				reviewAuthor.setVisible(true);
				reviewAuthorLbl.setEnabled(true);
				reviewAuthorLbl.setVisible(true);
				textArea.setEnabled(true);
				textArea.setVisible(true);
				textAreaLbl.setEnabled(true);
				textAreaLbl.setVisible(true);
				backBtn.setEnabled(true);
				backBtn.setVisible(true);
				addReview.setEnabled(true);
				addReview.setVisible(true);
				reviewClear.setEnabled(true);
				reviewClear.setVisible(true);
				deleteReview.setEnabled(true);
				deleteReview.setVisible(true);
				reviews = database.getReviews(selectedMovie.getId());
				reviewListModel = new DefaultListModel();
				
				for(int i=0; i < reviews.size(); i++)
				{
					reviewListModel.addElement(reviews.get(i).getTitle());
				}
				reviewList.setModel(reviewListModel);
				reviewTitle.setText("");
				reviewAuthor.setText("");
				textArea.setText("");
			}
			
		//add review
		if("addReview".equals(e.getActionCommand())) {
			lock2=1;
			Calendar calendar = Calendar.getInstance();
			Date now = calendar.getTime();
			Timestamp currentTimestamp = new Timestamp(now.getTime());
			Review newReview = new Review(selectedMovie.getId(), reviewAuthor.getText(), reviewTitle.getText(), textArea.getText(),currentTimestamp);
			database.insertReview(newReview);
			//refresh list
			reviews = database.getReviews(selectedMovie.getId());
			reviewListModel = new DefaultListModel();
			
			for(int i=0; i < reviews.size(); i++)
			{
				reviewListModel.addElement(reviews.get(i).getTitle());
			}
			if(selectedActor == null)
			{
				selectedReview = newReview;
			}
			reviewList.setModel(reviewListModel);
			reviewTitle.setText("");
			reviewAuthor.setText("");
			textArea.setText("");
			lock2=0;
			
		}
	//delete review
	if("deleteReview".equals(e.getActionCommand())) {
		lock2=1;
		database.deleteReview(selectedReview);
		
		//refresh list
		reviews = database.getReviews(selectedMovie.getId());
		reviewListModel = new DefaultListModel();
		
		for(int i=0; i < reviews.size(); i++)
		{
			reviewListModel.addElement(reviews.get(i).getTitle());
		}
		if(selectedReviewIndex != 0)
		{
		if(selectedReviewIndex < reviews.size()-1)	selectedReviewIndex++;
		else selectedReviewIndex = reviews.size()-1;
		reviewList.setSelectedIndex(selectedReviewIndex);
		selectedReview = reviews.get(selectedReviewIndex);
		}
		else 
		{
			selectedReviewIndex=0;
			reviewList.setSelectedIndex(0);
			selectedReview = null;
		}
		reviewList.setModel(reviewListModel);
		reviewTitle.setEnabled(true);
		reviewAuthor.setEnabled(true);
		textArea.setEnabled(true);
		reviewTitle.setText("");
		reviewAuthor.setText("");
		textArea.setText("");
		reviewTitle.setEnabled(false);
		reviewAuthor.setEnabled(false);
		textArea.setEnabled(false);
		lock2=0;
	}
	if("clearReview".equals(e.getActionCommand())) {
		lock2=1;		
		reviewTitle.setText("");
		reviewAuthor.setText("");
		textArea.setText("");
    	addReview.setEnabled(true);
    	addReview.setVisible(true);
    	deleteReview.setEnabled(false);
    	deleteReview.setVisible(false);
    	
    	reviewTitle.setEnabled(true);
    	reviewAuthor.setEnabled(true);
    	textArea.setEnabled(true);
    	lock2=0;
		
	}
		
		
		//Actor functions
		if("insertActor".equals(e.getActionCommand())) {
			lock=1;
			Actor newActor;
			String gender;
			//add new actor
			if(actorGender.getSelectedItem() == "Male")
				gender="M";
			else
				gender="F";
			newActor = new Actor(actorFname.getText(), actorLname.getText(), gender);
			database.insertActor(newActor);
			
			//refresh list
			actors = database.getActors();
			actorListModel = new DefaultListModel();
			for(int i=0; i < actors.size(); i++)
			{
				actorListModel.addElement(actors.get(i).getFirstName() + " " + actors.get(i).getLastName());
			}
			if(selectedActor == null)
			{
				selectedActor = newActor;
			}
			actorList.setModel(actorListModel);
			actorFname.setText("");
			actorLname.setText("");
			actorGender.setSelectedIndex(0);
			lock=0;	
		}
		
		if("deleteActor".equals(e.getActionCommand())) {
			lock=1;
			//delete actor
			database.deleteActor(selectedActor);
			
			//refresh list
			actors = database.getActors();
			actorListModel = new DefaultListModel();
			for(int i=0; i < actors.size(); i++)
			{
				actorListModel.addElement(actors.get(i).getFirstName() + " " + actors.get(i).getLastName());
			}
			if(selectedActorIndex != 0)
			{
			if(selectedActorIndex < actors.size()-1)	selectedActorIndex++;
			else selectedActorIndex = actors.size()-1;
			actorList.setSelectedIndex(selectedActorIndex);
			selectedActor = actors.get(selectedActorIndex);
			}
			else 
			{
				selectedActorIndex=0;
				actorList.setSelectedIndex(0);
				selectedActor = null;
			}
			actorList.setModel(actorListModel);
			actorFname.setText("");
			actorLname.setText("");
			actorGender.setSelectedIndex(0);
			lock=0;
		}
		
		if("updateActor".equals(e.getActionCommand())) 
		{
			lock=1;
			
			//update actor
			String gender1;
			if(actorGender.getSelectedIndex() == 1)
				gender1 = "M";
			else if(actorGender.getSelectedIndex() == 2)
				gender1 = "F";
			else
				gender1 = "?";
			selectedActor.setFirstName(actorFname.getText());
			selectedActor.setLastName(actorLname.getText());
			selectedActor.setGender(gender1);
			database.updateActor(selectedActor);
			
			//refresh list
			actors = database.getActors();
			actorListModel = new DefaultListModel();
			for(int i=0; i < actors.size(); i++)
			{
				actorListModel.addElement(actors.get(i).getFirstName() + " " + actors.get(i).getLastName());
			}
			actorList.setModel(actorListModel);
			actorFname.setText("");
			actorLname.setText("");
			actorGender.setSelectedIndex(0);
			lock=0;
		}
		
		if("clearActor".equals(e.getActionCommand())) {
			actorFname.setText("");
			actorLname.setText("");
			actorGender.setSelectedIndex(0);
			actorHint.setVisible(false);
        	actorHint1.setVisible(false);
        	actorInsert.setEnabled(true);
        	actorInsert.setVisible(true);
        	actorDelete.setEnabled(false);
    		actorDelete.setVisible(false);
    		actorUpdate.setEnabled(false);
    		actorUpdate.setVisible(false);
			
		}
		
				//Director functions
				if("insertDirector".equals(e.getActionCommand())) {
					lock=1;
					Director newDirector;
					String gender;
					//add new director
					if(directorGender.getSelectedItem() == "Male")
						gender="M";
					else
						gender="F";
					newDirector = new Director(directorFname.getText(), directorLname.getText(), gender);
					database.insertDirector(newDirector);
					
					//refresh list
					directors = database.getDirectors();
					directorListModel = new DefaultListModel();
					for(int i=0; i < directors.size(); i++)
					{
						directorListModel.addElement(directors.get(i).getFirstName() + " " + directors.get(i).getLastName());
					}
					if(selectedDirector == null)
					{
						selectedDirector = newDirector;
					}
					directorList.setModel(directorListModel);
					directorFname.setText("");
					directorLname.setText("");
					directorGender.setSelectedIndex(0);
					lock=0;	
				}
				
				if("deleteDirector".equals(e.getActionCommand())) {
					lock=1;
					//delete director
					database.deleteDirector(selectedDirector);
					
					//refresh list
					directors = database.getDirectors();
					directorListModel = new DefaultListModel();
					for(int i=0; i < directors.size(); i++)
					{
						directorListModel.addElement(directors.get(i).getFirstName() + " " + directors.get(i).getLastName());
					}
					if(selectedDirectorIndex != 0)
					{
					if(selectedDirectorIndex < directors.size()-1)	selectedDirectorIndex++;
					else selectedDirectorIndex = directors.size()-1;
					directorList.setSelectedIndex(selectedDirectorIndex);
					selectedDirector = directors.get(selectedDirectorIndex);
					}
					else 
					{
						selectedDirectorIndex=0;
						directorList.setSelectedIndex(0);
						selectedDirector = null;
					}
					directorList.setModel(directorListModel);
					directorFname.setText("");
					directorLname.setText("");
					directorGender.setSelectedIndex(0);
					lock=0;
				}
				
				if("updateDirector".equals(e.getActionCommand())) 
				{
					lock=1;
					
					//update director
					String gender;
					if(directorGender.getSelectedIndex() == 1)
						gender = "M";
					else if(directorGender.getSelectedIndex() == 2)
						gender = "F";
					else
						gender = "?";
					selectedDirector.setFirstName(directorFname.getText());
					selectedDirector.setLastName(directorLname.getText());
					selectedDirector.setGender(gender);
					database.updateDirector(selectedDirector);
					
					//refresh list
					directors = database.getDirectors();
					directorListModel = new DefaultListModel();
					for(int i=0; i < directors.size(); i++)
					{
						directorListModel.addElement(directors.get(i).getFirstName() + " " + directors.get(i).getLastName());
					}
					directorList.setModel(directorListModel);
					directorFname.setText("");
					directorLname.setText("");
					directorGender.setSelectedIndex(0);
					lock=0;
				}
				
				if("clearDirector".equals(e.getActionCommand())) {
					directorFname.setText("");
					directorLname.setText("");
					directorGender.setSelectedIndex(0);
					directorHint.setVisible(false);
		        	directorHint1.setVisible(false);
		        	directorInsert.setEnabled(true);
		        	directorInsert.setVisible(true);
		        	directorDelete.setEnabled(false);
		    		directorDelete.setVisible(false);
		    		directorUpdate.setEnabled(false);
		    		directorUpdate.setVisible(false);
				}
		}
	
	public void resetMovies()
	{
		addActor.setEnabled(false);
		addActor.setVisible(false);
		removeActor.setEnabled(false);
		removeActor.setVisible(false);
		doneBtn.setEnabled(false);
		doneBtn.setVisible(false);
		actorChoiceList.setEnabled(false);
		actorChoiceList.setVisible(false);
		editActor.setEnabled(true);
		editActor.setVisible(true);
		movieActors=new ArrayList<Actor>();
		movieActorsListModel = new DefaultListModel();
		movieActorList.setModel(movieActorsListModel);
		movieActorList.setEnabled(false);
		fArrow1.setEnabled(false);
        fArrow1.setVisible(false);
        fArrow2.setEnabled(false);
        fArrow2.setVisible(false);
        fArrow3.setEnabled(false);
        fArrow3.setVisible(false);
        bArrow1.setEnabled(false);
        bArrow1.setVisible(false);
        bArrow2.setEnabled(false);
        bArrow2.setVisible(false);
        bArrow3.setEnabled(false);
        bArrow3.setVisible(false);
		movies = database.getMovies(movieSearch.getText());
		movieListModel = new DefaultListModel();
		for(int i=0; i < movies.size(); i++)
		{
			movieListModel.addElement(movies.get(i).getTitle() + "  (" + Integer.toString(movies.get(i).getYear()) + ")");
		}
		if(selectedMovieIndex != 0)
		{
		if(selectedMovieIndex < movies.size()-1)	selectedMovieIndex++;
		else selectedMovieIndex = movies.size()-1;
		movieList.setSelectedIndex(selectedMovieIndex);
		selectedMovie = movies.get(selectedMovieIndex);
		}
		else 
		{
			selectedMovieIndex=0;
			movieList.setSelectedIndex(0);
			selectedMovie = null;
		}
		movieList.setModel(movieListModel);
		movieList.removeSelectionInterval(0,1);
		movieTitle.setText("");
		movieRating.setText("");
		movieYear.setText("");
		movieDuration.setText("");
		movieGenre.setText("");
		movieDirectors.setSelectedIndex(0);
	}
}
