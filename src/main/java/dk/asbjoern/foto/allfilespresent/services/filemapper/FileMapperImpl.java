package dk.asbjoern.foto.allfilespresent.services.filemapper;

import dk.asbjoern.foto.allfilespresent.beans.Image;
import dk.asbjoern.foto.allfilespresent.services.linux.CommandExecuter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileMapperImpl implements FileMapper {

    CommandExecuter commandExecuter;

    public FileMapperImpl(CommandExecuter commandExecuter) {
        this.commandExecuter = commandExecuter;
    }

    @Override
    public List<Image> map(String[] absolutePaths) throws IOException {

        ArrayList<Image> listOfImages = new ArrayList<>();
        for (String path : absolutePaths) {
            Files.walk(Paths.get(path)).filter(path1 -> Files.isRegularFile(path1)).forEach(
                    p ->
                    {
                        String absolutePath = p.toFile().getAbsolutePath();
                        String md5sum = commandExecuter.executeCommand(Arrays.asList("md5sum", p.toFile().getAbsolutePath()));

                        Image image = new Image(md5sum, absolutePath);

                        if(md5sum == null || md5sum.length() < 2 || absolutePath == null || absolutePath.length() < 2){
                            System.out.println("ERROR: " + image.toString());
                        }
                        listOfImages.add(image);
                    }
            );
        }

        return listOfImages;
    }
}
