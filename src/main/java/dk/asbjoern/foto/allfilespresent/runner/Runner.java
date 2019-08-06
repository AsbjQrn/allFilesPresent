package dk.asbjoern.foto.allfilespresent.runner;

import dk.asbjoern.foto.allfilespresent.beans.Image;
import dk.asbjoern.foto.allfilespresent.helpers.Loggable;
import dk.asbjoern.foto.allfilespresent.services.filemapper.FileMapper;
import dk.asbjoern.foto.allfilespresent.services.listcomparator.ListComparator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
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

        String[] tilBib = {tilBibliotek};

        List<Image> source = fileMapper.map(billedbiblioteker);
        System.out.println("Size of source: " + source.size());

        List<Image> destination = fileMapper.map(tilBib);
        System.out.println("Size of destination: " + destination.size());

        List<Image> differences =  listComparator.findDifferences(source, destination);
        System.out.println("Size of differences: " + differences.size());


        for (Image image : differences){
            System.out.println(image.toString() + " findes ikke i destinationsmappe" );
        }

        long slut = System.currentTimeMillis();
        Long tid = slut - start;
        System.out.println("Køretid i minutter: " + tid/1000/60);



    }
}
