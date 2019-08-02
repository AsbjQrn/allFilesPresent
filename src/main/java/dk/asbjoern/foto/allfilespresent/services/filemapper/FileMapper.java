package dk.asbjoern.foto.allfilespresent.services.filemapper;

import dk.asbjoern.foto.allfilespresent.beans.Image;

import java.io.IOException;
import java.util.List;

public interface FileMapper {

    List<Image> map(String[] absolutePaths) throws IOException;

}
