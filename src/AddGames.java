import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


public class AddGames extends JFrame {

    private static final long serialVersionUID = 1L;

    public static List<String> divisions = Arrays.asList("Div 1", "Div 2", "Div 3", "Colts", "U18", "U16", "U14.5", "U13", "U11.5");
    List<String> locations = Arrays.asList("CC Upper 1", "Darfield 1", "Darfield 2", "Rolleston 1a", "Rolleston 1b", "Rolleston 2", "Rolleston 3", "Rolleston 4", "Rolleston 2a", "Rolleston 2b", "Rolleston 5b", "Rolleston 5a", "Kaiapoi", "Ohoka", "Hampstead 1", "Ashburton 1", "Prebbleton 1", "Oxford 2", "Glenmark", "Southbridge");
    List<String> teamsDiv1 = Arrays.asList("Hornby", "Waihora", "Lincoln", "Raikaia", "Methven", "Southbridge", "Burn/Duns/Irwell", "Glenmark", "Darfield",
            "Ashley", "Prebbleton", "Celtic", "Saracens", "Oxford", "Ohoka", "Kaiapoi", "West Melton", "Southern", "Hampstead", "Rolleston");
    List<String> teamsDiv2 = Arrays.asList("Springston", "West Melton", "Diamond Harbour", "Leeston", "Darfield", "Selwyn", "Banks Peninsula",
            "Southbridge", "Hornby", "Kirwee", "Rolleston", "Lincoln", "Prebbleton", "Burn/Duns/Irwell");
    List<String> teamsDiv3 = Arrays.asList("Hornby", "Waihora", "Kirwee", "Springston", "Burn/Duns/Irwell", "Lincoln");
    List<String> teamsColts = Arrays.asList("Banks Peninsula", "Waihora", "Prebbleton", "Celtic", "Rolleston", "Lincoln", "West Melton", "Darfield",
            "Springston", "Kirwee");
    List<String> teamsU18 = Arrays.asList("Malvern Combined", "Waihora", "Springston/Southbridge", "Meth/Allen/Rak", "Tinwald/Celtic");
    List<String> teamsU16 = Arrays.asList("Ashley", "Oxford", "Waihora", "Woodend/Ohoka", "Rolleston", "Prebbleton", "West Melton", "Celtic",
            "Malvern Combined", "Lincoln", "Kaiapoi", "Harlequins");
    List<String> teamsU145 = Arrays.asList("Rolleston", "Prebbleton", "Malvern Combined", "West Melton", "Waihora", "Lincoln", "Duns/Southbr/Leest/Irwell");
    List<String> teamsU13 = Arrays.asList("Rolleston Black", "Rolleston Gold", "West Melton", "Lincoln", "Waihora", "Duns/Irwell/Leeston",
            "Prebbleton White", "Springston/Southbridge", "Prebbleton Blue", "Darfield");
    List<String> teamsU115 = Arrays.asList("Prebbleton Black", "Rolleston Black", "Rolleston Gold", "Lincoln", "Southbridge", "Waihora White",
            "Duns/Irwell/Sprinst", "Selwyn/Sheffield", "West Melton", "Prebbleton Blue", "Prebbleton White", "Waihora Black", "Banks Peninsula",
            "Leeston", "Darfield/Kirwee");

    public AddGames() {
        // Sort all the collections alphabetically
        Collections.sort(teamsDiv1);
        Collections.sort(teamsDiv2);
        Collections.sort(teamsDiv3);
        Collections.sort(teamsColts);
        Collections.sort(teamsU18);
        Collections.sort(teamsU16);
        Collections.sort(teamsU145);
        Collections.sort(teamsU13);
        Collections.sort(teamsU115);
        Collections.sort(locations);

        Label lblDiv = new Label("Division");
        Label lblHome = new Label("Home Team");
        Label lblAway = new Label("Away Team");
        Label lblLoc = new Label("Location");
        Label lblTime = new Label("Start Time");
        Label lblDate = new Label("Date");
        Label lblRef = new Label("Referee");
        Label lblAssRef1 = new Label("Assistant Ref 1");
        Label lblAssRef2 = new Label("Assistant Ref 2");
        Label lblToBeAdded = new Label("To be Added");

        final JComboBox<String> cbxDiv = new JComboBox<String>();
        final JComboBox<String> cbxHome = new JComboBox<String>();
        final JComboBox<String> cbxAway = new JComboBox<String>();
        final JComboBox<String> cbxLoc = new JComboBox<String>();
        final JComboBox<String> cbxRef = new JComboBox<String>();
        final JComboBox<String> cbxAssRef1 = new JComboBox<String>();
        final JComboBox<String> cbxAssRef2 = new JComboBox<String>();

        cbxDiv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbxHome.removeAllItems();
                cbxAway.removeAllItems();
                switch (cbxDiv.getSelectedIndex()) {
                    case 0:
                        for (String s : teamsDiv1) {
                            cbxHome.addItem(s);
                            cbxAway.addItem(s);
                        }
                        break;
                    case 1:
                        for (String s : teamsDiv2) {
                            cbxHome.addItem(s);
                            cbxAway.addItem(s);
                        }
                        break;
                    case 2:
                        for (String s : teamsDiv3) {
                            cbxHome.addItem(s);
                            cbxAway.addItem(s);
                        }
                        break;
                    case 3:
                        for (String s : teamsColts) {
                            cbxHome.addItem(s);
                            cbxAway.addItem(s);
                        }
                        break;
                    case 4:
                        for (String s : teamsU18) {
                            cbxHome.addItem(s);
                            cbxAway.addItem(s);
                        }
                        break;
                    case 5:
                        for (String s : teamsU16) {
                            cbxHome.addItem(s);
                            cbxAway.addItem(s);
                        }
                        break;
                    case 6:
                        for (String s : teamsU145) {
                            cbxHome.addItem(s);
                            cbxAway.addItem(s);
                        }
                        break;
                    case 7:
                        for (String s : teamsU13) {
                            cbxHome.addItem(s);
                            cbxAway.addItem(s);
                        }
                        break;
                    case 8:
                        for (String s : teamsU115) {
                            cbxHome.addItem(s);
                            cbxAway.addItem(s);
                        }
                        break;
                }
            }
        });

        for (String s : divisions) {
            cbxDiv.addItem(s);
        }
        for (String s : teamsDiv1) {
            cbxHome.addItem(s);
            cbxAway.addItem(s);
        }
        for (String s : locations) {
            cbxLoc.addItem(s);
        }

        UtilDateModel model = new UtilDateModel();
        model.setDate(2015, 2, 2);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        final JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        //TODO: Add ability to choose time

        JButton btnCreate = new JButton("Create Game");
        btnCreate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String home = (String) cbxHome.getSelectedItem();
                String away = (String) cbxAway.getSelectedItem();
                String loc = (String) cbxLoc.getSelectedItem();
                String time = "12pm"; // TODO: Change
                String ref = (String) cbxRef.getSelectedItem();
                String assref1 = (String) cbxAssRef1.getSelectedItem();
                String assref2 = (String) cbxAssRef2.getSelectedItem();

                int div = cbxDiv.getSelectedIndex();
                int homeID = cbxHome.getSelectedIndex();
                int awayID = cbxAway.getSelectedIndex();

                Date selectedDate = (Date) datePicker.getModel().getValue();
                Calendar cal = Calendar.getInstance();
                cal.setTime(selectedDate);
                String date = cal.get(Calendar.YEAR) + pad(cal.get(Calendar.MONTH) + 1) + pad(cal.get(Calendar.DAY_OF_MONTH));

                String gameID = createGameID(div, homeID, awayID, date);

                try {
                    upload(gameID, home, away, loc, time, ref, assref1, assref2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        JPanel pnl = (JPanel) getContentPane();
        GroupLayout layout = new GroupLayout(pnl);
        pnl.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDiv)
                                .addComponent(cbxDiv))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblHome)
                                .addComponent(cbxHome))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAway)
                                .addComponent(cbxAway))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblLoc)
                                .addComponent(cbxLoc))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTime)
                                .addComponent(lblToBeAdded))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDate)
                                .addComponent(datePicker))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblRef)
                                .addComponent(cbxRef))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAssRef1)
                                .addComponent(cbxAssRef1))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAssRef2)
                                .addComponent(cbxAssRef2))
                        .addComponent(btnCreate)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblDiv)
                                .addComponent(cbxDiv))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblHome)
                                .addComponent(cbxHome))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblAway)
                                .addComponent(cbxAway))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblLoc)
                                .addComponent(cbxLoc))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblTime)
                                .addComponent(lblToBeAdded))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblDate)
                                .addComponent(datePicker))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblRef)
                                .addComponent(cbxRef))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblAssRef1)
                                .addComponent(cbxAssRef1))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblAssRef2)
                                .addComponent(cbxAssRef2))
                        .addComponent(btnCreate)
        );

        setTitle("Create Game");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();
    }

    // Uses Apaches HTTPComponents Library to upload data to php script
    private void upload(String gameID, String home, String away, String loc,
                        String time, String ref, String assref1, String assref2) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.possumpam.com/rugby-scoring-app-scripts/create_game.php");

        // Add all game details to List<NameValuePair> and add to HttpPost
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("gameID", gameID));
        nameValuePairs.add(new BasicNameValuePair("homeTeam", home));
        nameValuePairs.add(new BasicNameValuePair("awayTeam", away));
        nameValuePairs.add(new BasicNameValuePair("location", loc));
        nameValuePairs.add(new BasicNameValuePair("time", time));
        nameValuePairs.add(new BasicNameValuePair("ref", ref));
        nameValuePairs.add(new BasicNameValuePair("assRef1", assref1));
        nameValuePairs.add(new BasicNameValuePair("assRef2", assref2));
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        CloseableHttpResponse response2;
        response2 = httpclient.execute(httpPost);

        try {
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
        }
    }

    // Pads single digit ints with a leading zero to keep 2 character length
    private String pad(int c) {
        return c >= 10 ? String.valueOf(c) : "0" + String.valueOf(c);
    }

    // Create gameID from date, teams and division
    private String createGameID(int div, int home, int away, String date) {
        String gameID = "";

        // Add date to gameID
        gameID += date;

        // Add teamIDs to gameID. If teamID less than 10,
        // add a 0 to preserve gameID length
        gameID += pad(home);
        gameID += pad(away);

        // Add divisionID to gameID.
        gameID += pad(div);

        System.out.println(gameID);
        return gameID;
    }

    public class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "EEEE d MMMM yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                AddGames md = new AddGames();
                md.setVisible(true);
            }
        });
    }

}
