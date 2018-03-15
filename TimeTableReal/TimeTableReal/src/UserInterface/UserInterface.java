package UserInterface;

import Filtering.ContainsWord;
import com.sun.tools.javac.comp.Flow;
import logic.EventHandler;
import logic.Lecture;
import logic.Event;
import logic.WeekDays;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserInterface implements Runnable {

    public JFrame frame;
    public EventHandler handler;
    public JPanel mainPanel;
    private CardLayout cardLayout;

// this is just a test comment
// another test
    public UserInterface(EventHandler handler) {

        this.handler = handler;
        this.cardLayout = new CardLayout();


    }

    public void run() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setLocationRelativeTo(null);
        frame.setTitle("TimeTable App");
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {

        container.setLayout(cardLayout);
        container.add(new MainPanel().createMainPanel(), "MAIN_PANEL");
        container.add(new addEventPanel().createAddEventPanel(), "ADD_PANEL");
        container.add(new filterPanel().createFilterPanel(), "FILTER_PANEL");
        container.add(new removePanel().createRemovePanel(), "REMOVAL_PANEL");

    }


    class MainPanel {

        private ArrayList<WeekdayPanel> weekdayPanels;
        private ArrayList<WeekDays> weekdayList;
        private JPanel weekPanel;

        private JPanel createMainPanel() {
            mainPanel = new JPanel(new GridLayout(2, 1));
            mainPanel.add(createWeekPanel());
            mainPanel.add(createButtonPanel());

            return mainPanel;
        }

        private JPanel createWeekPanel() {

            weekPanel = new JPanel();

            this.weekdayPanels = new ArrayList<>();
            this.weekdayList = new ArrayList<>();
            weekdayList.add(WeekDays.MON);
            weekdayList.add(WeekDays.TUE);
            weekdayList.add(WeekDays.WED);
            weekdayList.add(WeekDays.THU);
            weekdayList.add(WeekDays.FRI);
            weekdayList.add(WeekDays.SAT);
            weekdayList.add(WeekDays.SUN);

            WeekdayPanel mondayPanel = new WeekdayPanel("Monday", WeekDays.MON);
            WeekdayPanel tuesdayPanel = new WeekdayPanel("Tuesday", WeekDays.TUE);
            WeekdayPanel wednesdayPanel = new WeekdayPanel("Wednesday", WeekDays.WED);
            WeekdayPanel thursdayPanel = new WeekdayPanel("Thursday", WeekDays.THU);
            WeekdayPanel fridayPanel = new WeekdayPanel("Friday", WeekDays.FRI);
            WeekdayPanel saturdayPanel = new WeekdayPanel("Saturday", WeekDays.SAT);
            WeekdayPanel sundayPanel = new WeekdayPanel("Sunday", WeekDays.SUN);
            weekPanel.setLayout(new GridLayout(1, 7));
            weekdayPanels.add(mondayPanel);
            weekdayPanels.add(tuesdayPanel);
            weekdayPanels.add(wednesdayPanel);
            weekdayPanels.add(thursdayPanel);
            weekdayPanels.add(fridayPanel);
            weekdayPanels.add(saturdayPanel);
            weekdayPanels.add(sundayPanel);
            for (WeekdayPanel panel : weekdayPanels) {
                weekPanel.add(panel.createDayPanel());
            }

            return weekPanel;

        }


        private JPanel createButtonPanel() {

            JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
            buttonPanel.setSize(new Dimension(600, 75));
            buttonPanel.setMinimumSize(new Dimension(600, 75));
            buttonPanel.setMaximumSize(new Dimension(600, 75));

            JButton addButton = new JButton("Add");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(frame.getContentPane(), "ADD_PANEL");
                }
            });

            buttonPanel.add(addButton);

            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(frame.getContentPane(), "REMOVAL_PANEL");
                }
            });

            buttonPanel.add(removeButton);

            JButton filterButton = new JButton("Filter");
            filterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(frame.getContentPane(), "FILTER_PANEL");
                }
            });

            buttonPanel.add(filterButton);

            return buttonPanel;
        }

        private void updateUI() {

            for (int i = 0; i < weekdayPanels.size(); i++) {
                WeekdayPanel panel = weekdayPanels.get(i);
                ArrayList<Event> eventList = (ArrayList) handler.showEventsOnDay(weekdayList.get(i));
                for (int j = 0; j < eventList.size(); j++) {
                    panel.addElementToList(eventList.get(i).toString());

                }
            }
            JPanel weekPanel = new JPanel();
            for (WeekdayPanel panel : weekdayPanels) {
                weekPanel.add(panel.createDayPanel());
            }

            mainPanel.remove(weekPanel);
            mainPanel.add(this.weekPanel);
        }


    }


    class WeekdayPanel extends JPanel{

        private String name;
        private WeekDays day;
        private DefaultListModel<String> list;
        private JList list1;

        public WeekdayPanel(String name, WeekDays day) {

            this.name = name;
            this.day = day;
            this.list = new DefaultListModel<>();

        }

        public JPanel createDayPanel() {
            this.add(new JLabel(name));


            ArrayList<Event> eventList = (ArrayList) handler.showEventsOnDay(day);
            for (int i = 0; i < eventList.size(); i++) {
                list.addElement(eventList.get(i).toString());
            }

            list1 = new JList(list);

            this.add(list1);

            return this;
        }

        public void addElementToList(String element) {
            this.list.addElement(element);
            this.list1.setModel(list);
        }

        public void removeElementFromList(String element) {
            this.list.removeElement(element);
            this.list1.setModel(list);
        }

    }


    class addEventPanel {

        JPanel addEventPanel;


        public JPanel createAddEventPanel() {

            addEventPanel = new JPanel(new GridLayout(8, 2));
            createComponents(addEventPanel);

            return addEventPanel;
        }

        private void createComponents(JPanel addEventPanel) {


            addEventPanel.add(new JLabel("Name: "));
            JTextField nameField = new JTextField();
            addEventPanel.add(nameField);

            addEventPanel.add(new JLabel("Start time:"));
            JTextField startField = new JTextField();
            addEventPanel.add(startField);

            addEventPanel.add(new JLabel("End time:"));
            JTextField endField = new JTextField();
            addEventPanel.add(endField);

            addEventPanel.add(new JLabel("Day:"));
            JTextField dayField = new JTextField();
            addEventPanel.add(dayField);

            addEventPanel.add(new JLabel("Teacher:"));
            JTextField teacherField = new JTextField();
            addEventPanel.add(teacherField);

            addEventPanel.add(new JLabel("Location:"));
            JTextField locationField = new JTextField();
            addEventPanel.add(locationField);

            JLabel errorLabel = new JLabel();
            addEventPanel.add(errorLabel);

            JButton addNewEventButton = new JButton("Add new event");
            addNewEventButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Lecture l = new Lecture(
                                nameField.getText().toString(),
                                Integer.parseInt(startField.getText().toString()),
                                Integer.parseInt(endField.getText().toString()),
                                dayField.getText().toString(),
                                teacherField.getText().toString(),
                                locationField.getText().toString()
                        );
                        handler.add(l);
                        mainPanel.updateUI();
                    } catch (Exception e1) {
                        errorLabel.setText("Error");
                    }
                    errorLabel.setText("Succesfully added");
                    nameField.setText("");
                    startField.setText("");
                    endField.setText("");
                    dayField.setText("");
                    teacherField.setText("");
                    locationField.setText("");
                }
            });

            addEventPanel.add(addNewEventButton);

            addEventPanel.add(new JPanel(new GridLayout(1,1)).add(new JButton(new AbstractAction("Back") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(frame.getContentPane(), "MAIN_PANEL");
                }
            })));

        }


        private void createComboBox() {

            JComboBox<String> c = new JComboBox<>();
            for (int i = 0; i < handler.getEventTypes().size(); i++) {
                c.addItem(handler.getEventTypes().get(i));
            }
        }

    }


    class filterPanel {
        JPanel filterPanel;

        private JPanel createFilterPanel() {

            filterPanel = new JPanel(new GridLayout(2,2));
            filterPanel.add(new JLabel("Enter string to search for:"));
            JTextField stringField = new JTextField();

            filterPanel.add(stringField);

            ArrayList events = (ArrayList) handler.eventsWhichComplyWith(new ContainsWord(stringField.getText().toString()));

            filterPanel.add(searchResultsPanel(events));

            return filterPanel;
        }

        private JPanel searchResultsPanel(ArrayList<Event> events) {

            JPanel searchResultsPanel = new JPanel(new GridLayout(2,1));

            String s = "";

            for (Event event : events) {
                s += event.toString() + "\n";
            }

            searchResultsPanel.add(new JLabel("<html>" + s + "</html>"));

            searchResultsPanel.add(new JButton(new AbstractAction("Back") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(frame.getContentPane(), "MAIN_PANEL");
                }
            }));

            return searchResultsPanel;
        }
    }


    class removePanel {

        private JPanel removePanel;

        private JPanel createRemovePanel() {
            removePanel = new JPanel(new BorderLayout());
            removePanel.add(new JLabel("Type name of the lesson you want to remove:"));
            JTextField removalField = new JTextField();
            removePanel.add(removalField);


            JLabel errorLabel = new JLabel("Test");

            JPanel buttonPanel = new JPanel(new GridLayout(1,2));

            buttonPanel.add(new JButton(new AbstractAction("Back") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(frame.getContentPane(), "MAIN_PANEL");
                }
            }));

            buttonPanel.add(new JButton(new AbstractAction("Remove") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<Event> removalList = (ArrayList<Event>) handler.eventsWhichComplyWith(new ContainsWord(removalField.getText().toString()));
                    for (Event event : removalList) {
                        handler.remove(event);
                    }
                    errorLabel.setText("<html>" + removalList.size() + " events were succesfully removed</html>");
                }
            }));

            removePanel.add(buttonPanel);

            removePanel.add(errorLabel);

            return removePanel;
        }
    }

}