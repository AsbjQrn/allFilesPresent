package dk.asbjoern.foto.allfilespresent

import dk.asbjoern.foto.allfilespresent.beans.Image
import dk.asbjoern.foto.allfilespresent.services.filemapper.FileMapper
import dk.asbjoern.foto.allfilespresent.services.filemapper.FileMapperImpl
import dk.asbjoern.foto.allfilespresent.services.linux.CommandExecuter
import dk.asbjoern.foto.allfilespresent.services.linux.LinuxCommandExecuter
import spock.lang.Specification

import java.nio.file.Path
import java.nio.file.Paths

class TestFilemapper extends Specification {


    def "Test that Filemapper delivers all files in libraries"(){

        CommandExecuter linuxCommand = new LinuxCommandExecuter()
        FileMapper fileMapper = new FileMapperImpl(linuxCommand)

        when:
        Path p = Paths.get('src/test/resources/sourceLibs')
        ArrayList<Image> images =  fileMapper.map(p.toAbsolutePath().toString())

        then:
        images.size() == 38
        for (Image image : images){
            assert image.getAbsolutePath().size() > 0
            assert image.getMd5sum().size() > 0
        }
        noExceptionThrown()


    }


}
