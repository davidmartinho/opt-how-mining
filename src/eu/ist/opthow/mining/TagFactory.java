package eu.ist.opthow.mining;

import java.util.HashSet;
import java.util.Set;

public class TagFactory {

  private static TagFactory INSTANCE = new TagFactory();
  
  private Set<Tag> existingTagSet;
  
  private TagFactory() {
    this.existingTagSet = new HashSet<Tag>();
  }
  
  public static Tag getTag(String keyword) {
    for(Tag existingTag : INSTANCE.existingTagSet) {
      if(existingTag.getKeyword().equals(keyword)) {
        return existingTag;
      }
    }
    Tag newTag = new Tag(keyword);
    INSTANCE.existingTagSet.add(newTag);
    return newTag;
  }
}
