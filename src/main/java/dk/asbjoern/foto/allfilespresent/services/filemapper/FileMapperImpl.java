package dk.asbjoern.foto.allfilespresent.services.filemapper;

import dk.asbjoern.foto.allfilespresent.services.linux.CommandExecuter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileMapperImpl implements FileMapper {

    CommandExecuter commandExecuter;

    public FileMapperImpl(CommandExecuter commandExecuter) {
        this.commandExecuter = commandExecuter;
    }

    @Override
    public Map<String, String> map(String[] absolutePaths) throws IOException {

        Map<String, String> uniqueFiles = new HashMap<>();
        for (String path : absolutePaths) {
            Files.walk(Paths.get(path)).forEach(
                    p ->
                    {
                        String absolutePath = p.toFile().getAbsolutePath();
                        String md5sum = commandExecuter.executeCommand(Arrays.asList("md5sum", p.toFile().getAbsolutePath()));
                        uniqueFiles.put(md5sum, absolutePath);
                    }
            );
        }

        return uniqueFiles;
    }
}
