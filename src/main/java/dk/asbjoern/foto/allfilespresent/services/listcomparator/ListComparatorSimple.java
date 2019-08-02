package dk.asbjoern.foto.allfilespresent.services.listcomparator;


import dk.asbjoern.foto.allfilespresent.beans.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListComparatorSimple implements ListComparator {

  public List<Image> findDifferences(List<Image> source, List<Image> destination ){
      source.removeAll(destination);
      return source;
  }


}
