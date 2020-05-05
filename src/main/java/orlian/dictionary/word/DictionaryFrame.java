package orlian.dictionary.word;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.sound.sampled.*;
import javax.swing.*;

public class DictionaryFrame extends JFrame {
    public DictionaryFrame() {
        //<editor-fold desc="Building the Frame">
        JFrame f = new JFrame("DictionaryFrame");
        //buttons
        JButton lookup = new JButton("Lookup");
        JButton listen = new JButton("Listen");
        lookup.setBounds(30, 100, 110, 30);
        listen.setBounds(190, 100, 110, 30);
        //word label
        JLabel label = new JLabel();
        label.setText("Enter Word :");
        label.setBounds(10, 10, 100, 100);
        //empty areas which will show event after button clicked
        JLabel wordType = new JLabel();
        wordType.setBounds(10, 120, 200, 100);
        JTextArea def = new JTextArea();
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


        //-----still need to handle exceptions, only allow user to enter a single word, tell user if word not found-----//


        //action listeners
        lookup.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    List<WordObject> info = callAll(textfield);
                    def.setText(info.get(0).shortdef.get(0));
                    wordType.setText(info.get(0).fl);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        listen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    List<WordObject> info = callAll(textfield);
                    String string = ("https://media.merriam-webster.com/soundc11/" +
                            textfield.getText().toLowerCase().charAt(0) + "/" + info.get(0).hwi.prs.get(0).sound.audio + ".wav");
                    URL url = new URL(string);
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    public List<WordObject> callAll(JTextField textField) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dictionaryapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DictionaryService service = retrofit.create(DictionaryService.class);
        List<WordObject> info = service.getWord(textField.getText()).execute().body();
        return info;
    }




    public static void main(String[] args) {
        new DictionaryFrame();
    }
}
