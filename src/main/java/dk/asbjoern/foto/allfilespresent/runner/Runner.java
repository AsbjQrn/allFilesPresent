package dk.asbjoern.foto.allfilespresent.runner;

import dk.asbjoern.foto.allfilespresent.beans.Image;
import dk.asbjoern.foto.allfilespresent.helpers.Loggable;
import dk.asbjoern.foto.allfilespresent.services.filemapper.FileMapper;
import dk.asbjoern.foto.allfilespresent.services.listcomparator.ListComparator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Runner implements Loggable {

    private FileMapper fileMapper;
    private  ListComparator listComparator;

    @Value("${source}")
    private String[] billedbiblioteker;

    @Value("${destination}")
    String tilBibliotek = "";

    public Runner(FileMapper fileMapper, ListComparator listComparator) {
        this.fileMapper = fileMapper;
        this.listComparator = listComparator;
    }

    public void run() throws IOException {

        long start = System.currentTimeMillis();



        List<Image> source = fileMapper.map(billedbiblioteker);
        System.out.println("Size of source: " + source.size());

        List<Image> destination = fileMapper.map(tilBibliotek);
        System.out.println("Size of destination: " + destination.size());



        Long tidStartMetode2 = System.currentTimeMillis();
        List<Image> differences2 =  listComparator.findDifferencesMetode2(source, destination);
        Long tidSlutMetode2 = System.currentTimeMillis();
        Long tidforbrugMetode2Minutter = (tidSlutMetode2 - tidStartMetode2 / 1000 / 60);
        System.out.println("Tidforbrug i minutter metode2: "  + tidforbrugMetode2Minutter) ;
        System.out.println("Size of differences (metode2): " + differences2.size());

        for (Image image : differences2){
            System.out.println(image.toString() + " findes ikke i destinationsmappe" );
        }


        Long tidStartMetode1 = System.currentTimeMillis();
        List<Image> differences1 =  listComparator.findDifferencesMetode1(source, destination);
        Long tidSlutMetode1 = System.currentTimeMillis();
        Long tidforbrugMetode1Minutter = (tidSlutMetode1 - tidStartMetode1 / 1000 / 60);
        System.out.println("Tidforbrug i minutter metode2: "  + tidforbrugMetode1Minutter) ;


        System.out.println("Size of differences (metode1): " + differences1.size());


        for (Image image : differences1){
            System.out.println(image.toString() + " findes ikke i destinationsmappe" );
        }




        long slut = System.currentTimeMillis();
        Long tid = slut - start;
        System.out.println("Køretid i minutter: " + tid/1000/60);



    }
}
