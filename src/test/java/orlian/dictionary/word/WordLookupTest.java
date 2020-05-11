package orlian.dictionary.word;

import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class WordLookupTest {

    @Test
    public void getWord() throws IOException {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dictionaryapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DictionaryService service = retrofit.create(DictionaryService.class);

        //when
        List<WordObject> info= service.getWord("String").execute().body();

        //then
        assertNotNull(info.get(0).meta.id);
        System.out.println(info.get(0).meta.id);
        System.out.println(info.get(0).fl);
        System.out.println(info.get(0).hwi.prs.get(0).mw);
        System.out.println(info.get(0).hwi.hw);
        assertNotNull(info.get(0).hwi.prs.get(0).sound.audio);
        System.out.println(info.get(0).hwi.prs.get(0).sound.audio);
        System.out.println(info.get(0).hwi.prs.get(0).sound.ref);
        System.out.println(info.get(0).hwi.prs.get(0).sound.stat);
        assertNotNull(info.get(0).shortdef.get(0));
        System.out.println(info.get(0).shortdef.get(0));
        System.out.println("https://media.merriam-webster.com/soundc11/" + "W" + "/" + info.get(0).hwi.prs.get(0).sound.audio + ".wav");
    }
}