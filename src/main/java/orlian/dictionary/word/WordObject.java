package orlian.dictionary.word;

import java.util.ArrayList;
import java.util.List;


public class WordObject {

      class Meta {
            String id;
            List<String> stems = new ArrayList<String>();

        }
        String fl;
      class Hwi {
          String hw;
          List<Prs> prs = new ArrayList<Prs>();

          class Prs {
              String mw;
              Sound sound;
              class Sound {
                  String audio;
                  String ref;
                  String stat;
              }
          }

      }
      Meta meta;
      Hwi hwi;
      List<String> shortdef = new ArrayList<String>();
}


