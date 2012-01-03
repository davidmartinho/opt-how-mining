package eu.ist.opthow.mining.recommendation;

import java.util.Set;

import eu.ist.opthow.mining.Tag;

public class Recommendation {

  private Set<Tag> tag;
  
  public Recommendation(Set<Tag> tag) {
    this.tag = tag;
  }
  
  public Set<Tag> getTagSet() {
    return tag;
  }
  
  public String toString() {
    String result = "";
    for(Tag t : tag) {
      result += t.getKeyword()+", ";
    }
    return result;
  }
}
