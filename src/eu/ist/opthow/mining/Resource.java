package eu.ist.opthow.mining;

public abstract class Resource {

  private Tag tag;
  
  public Tag getTag() {
    return tag;
  }
  
  public void annotate(Tag tag) {
    this.tag = tag;
    tag.annotateResource(this);
  }
  
  public boolean hasSameTag(Resource resource) {
    return tag.equals(resource.getTag());
  }

}
