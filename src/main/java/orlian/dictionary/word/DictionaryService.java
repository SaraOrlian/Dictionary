package orlian.dictionary.word;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface DictionaryService {

    @GET ("/api/v3/references/collegiate/json/{word}?key=cd40d554-a52f-4e49-8a3d-3751dbd50f5b")
    //Call<List<WordObject>> getWord();
    Call<List<WordObject>> getWord(@Path("word") String word);
}
