package dk.asbjoern.foto.allfilespresent

import dk.asbjoern.foto.allfilespresent.beans.Image
import dk.asbjoern.foto.allfilespresent.services.filemapper.FileMapper
import dk.asbjoern.foto.allfilespresent.services.filemapper.FileMapperImpl
import dk.asbjoern.foto.allfilespresent.services.linux.CommandExecuter
import dk.asbjoern.foto.allfilespresent.services.linux.LinuxCommandExecuter
import dk.asbjoern.foto.allfilespresent.services.listcomparator.ListComparator
import dk.asbjoern.foto.allfilespresent.services.listcomparator.ListComparatorSimple
import spock.lang.Specification

import java.nio.file.Path
import java.nio.file.Paths

class TestfileComparator extends Specification {


    def "Test that only missing files is contained in returned collection after running listcomparator"() {

        CommandExecuter linuxCommand = new LinuxCommandExecuter()
        FileMapper fileMapper = new FileMapperImpl(linuxCommand)
        ListComparator listComparator = new ListComparatorSimple()
        Path source = Paths.get('src/test/resources/sourceLibs/')
        Path destination = Paths.get('src/test/resources/destinationLib/')
        ArrayList<Image> imagesSource = fileMapper.map(source.toAbsolutePath().toString())
        ArrayList<Image> imagesDestination = fileMapper.map(destination.toAbsolutePath().toString())


        when:
        ArrayList<Image> missingInDestination = listComparator.findDifferences(imagesSource, imagesDestination)

        then:
        missingInDestination.size() == 7


    }


}
