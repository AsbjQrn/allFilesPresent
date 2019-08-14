package dk.asbjoern.foto.allfilespresent.services.listcomparator;


import dk.asbjoern.foto.allfilespresent.beans.Image;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ListComparatorSimple implements ListComparator {


    private List<Image> sourceSorted;
    private List<Image> destinationSorted;
    private int sourceCounter = 0;
    private int destinationCounter = 0;
    private int comparisonresult;


    public List<Image> findDifferencesMetode1(List<Image> source, List<Image> destination) {
        source.removeAll(destination);
        return source;
    }


    private int compare() {

        return sourceSorted.get(sourceCounter).getMd5sum().compareTo(destinationSorted.get(destinationCounter).getMd5sum());
    }

    public List<Image>  findDifferencesMetode2(List<Image> source, List<Image> destination) {


        Collections.sort(source);
        Collections.sort(destination);

        List<Image> differences = new ArrayList<>();

        this.sourceSorted = source;
        this.destinationSorted = destination;

        boolean read = true;
        while (read) {

            this.comparisonresult = compare();

            if (comparisonresult == 0) {
                sourceCounter++;
            }
            if (comparisonresult > 0) {
                destinationCounter++;
            }
            if (comparisonresult < 0) {
                differences.add(this.sourceSorted.get(sourceCounter));
                sourceCounter++;
            }

            if (sourceCounter  <= sourceSorted.size() - 1 && destinationCounter <=  destinationSorted.size() - 1) {

                continue;

            } else {

                System.out.println("Sammenligning i findDifferencesMetode2 stoppes");
                System.out.println("sourceSorted.size() - 1 <= sourceCounter && destinationSorted.size() - 1 <= destinationCounter: " + sourceSorted.size() + ", " + sourceCounter + ", " + destinationSorted.size() + ", " + destinationCounter);
                read = false;
            }
        }

        return differences;
    }
}