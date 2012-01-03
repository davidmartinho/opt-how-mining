package eu.ist.opthow.mining;

import java.util.HashSet;
import java.util.Set;

public class DataObject extends Resource {

  private Set<ExecutedRequest> inputRequestSet;
  
  public DataObject(String label) {
    Tag tag = TagFactory.getTag(label);
    annotate(tag);
    inputRequestSet = new HashSet<ExecutedRequest>();
  }
  
  public String getLabel() {
    return getTag().getKeyword();
  }

  public void addRequest(ExecutedRequest request) {
    inputRequestSet.add(request);
  }
}
