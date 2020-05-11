package orlian.dictionary.word;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Request data and populate the Frame
 */

public class DictionaryController {

    private DictionaryService service;

    public DictionaryController(DictionaryService service) {
        this.service = service;
    }



    public void requestListen(String word) {
        service.getWord(word).enqueue(new Callback<List<WordObject>>() {
            @Override
            public void onResponse(Call<List<WordObject>> call, Response<List<WordObject>> response) {
                String string = ("https://media.merriam-webster.com/soundc11/" +
                        word.toLowerCase().charAt(0) + "/" + response.body().get(0).hwi.prs.get(0).sound.audio + ".wav");

                URL url = null;
                try {
                    url = new URL(string);
                } catch (
                        MalformedURLException e) {
                    e.printStackTrace();
                }
                AudioInputStream audioIn = null;
                try {
                    audioIn = AudioSystem.getAudioInputStream(url);
                } catch (
                        UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
                Clip clip = null;
                try {
                    clip = AudioSystem.getClip();
                } catch (
                        LineUnavailableException e) {
                    e.printStackTrace();
                }
                try {
                    clip.open(audioIn);
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clip.start();
            }


            @Override
            public void onFailure(Call<List<WordObject>> call, Throwable t) {

            }
        });
    }
        public void requestLookup (String word){
            service.getWord(word).enqueue(new Callback<List<WordObject>>() {


                @Override
                public void onResponse(Call<List<WordObject>> call, Response<List<WordObject>> response) {
                    DictionaryFrame.def.setText(response.body().get(0).shortdef.get(0));
                    DictionaryFrame.wordType.setText(response.body().get(0).fl);

                }


                @Override
                public void onFailure(Call<List<WordObject>> call, Throwable t) {

                }
            });
        }
    }
