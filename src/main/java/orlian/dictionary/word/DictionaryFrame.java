package orlian.dictionary.word;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;

public class DictionaryFrame extends JFrame {

    public static JButton lookup;
    public static JButton listen;
    public static JLabel wordType;
    public static JTextArea def;

    public DictionaryFrame() {
        //<editor-fold desc="Building the Frame">
        JFrame f = new JFrame("DictionaryFrame");
        //buttons
        lookup = new JButton("Lookup");
        listen = new JButton("Listen");
        lookup.setBounds(30, 100, 110, 30);
        listen.setBounds(190, 100, 110, 30);
        //word label
        JLabel label = new JLabel();
        label.setText("Enter Word :");
        label.setBounds(10, 10, 100, 100);
        //empty areas which will show event after button clicked
        wordType = new JLabel();
        wordType.setBounds(10, 120, 200, 100);
        def = new JTextArea();
        def.setBounds(10, 190, 320, 100);
        def.setWrapStyleWord(true);
        def.setLineWrap(true);
        def.setOpaque(false);
        def.setEditable(false);
        def.setFocusable(false);
        def.setBackground(UIManager.getColor("Label.background"));
        def.setFont(UIManager.getFont("Label.font"));
        def.setBorder(UIManager.getBorder("Label.border"));
        //textfield to enter word
        JTextField textfield = new JTextField();
        textfield.setBounds(110, 50, 130, 30);
        //add to frame
        f.add(wordType);
        f.add(def);
        f.add(textfield);
        f.add(label);
        f.add(lookup);
        f.add(listen);
        f.setSize(390, 500);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //</editor-fold>





        //action listeners

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dictionaryapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       DictionaryService service = retrofit.create(DictionaryService.class);

        DictionaryController controller = new DictionaryController(service);

        listen.addActionListener(actionEvent -> {
            controller.requestListen(textfield.getText());
        });
        lookup.addActionListener(actionEvent -> {
            controller.requestLookup(textfield.getText());
        });

//-----still need to handle exceptions, only allow user to enter a single word, tell user if word not found-----//

    /*public boolean check(JTextField textField, JTextArea def) {
        if(!textField.getText().matches("^[a-zA-Z]+$")) {
            def.setText("Entry invalid");
            return false;
        }
        return true;
    }*/


    }

    public static void main(String[] args) {
        new DictionaryFrame();
    }
}
