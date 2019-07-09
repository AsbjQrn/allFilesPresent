package dk.asbjoern.foto.allfilespresent.services.filemapper;

import java.io.IOException;
import java.util.Map;

public interface FileMapper {

    Map<String, String> map(String[] absolutePaths) throws IOException;

}
