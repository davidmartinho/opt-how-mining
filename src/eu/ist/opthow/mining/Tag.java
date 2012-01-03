package eu.ist.opthow.mining;

import java.util.HashSet;
import java.util.Set;

public class Tag {
  
  private String keyword;
  private Set<Resource> annotatedResourceSet;
  
  public Tag(String keyword) {
    this.keyword = keyword;
    this.annotatedResourceSet = new HashSet<Resource>();
  }
  
  public String getKeyword() {
    return keyword;
  }
  
  public void annotateResource(Resource resource) {
    annotatedResourceSet.add(resource);
  }
}
