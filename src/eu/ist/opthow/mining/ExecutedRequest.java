package eu.ist.opthow.mining;

import java.util.HashSet;
import java.util.Set;

public class ExecutedRequest extends Resource {

  private ProcessTrace processTrace;
  
  private ExecutedRequest nextRequest;
  
  private Set<DataObject> availableDataObjectSet;
  
  public ExecutedRequest(String subject) {
   Tag tag = TagFactory.getTag(subject);
   annotate(tag);
   availableDataObjectSet = new HashSet<DataObject>();
  }
  
  public String getSubject() {
    return getTag().getKeyword();
  }
  
  public void setProcessTrace(ProcessTrace processTrace) {
    this.processTrace = processTrace;
  }
  
  public ProcessTrace getProcessTrace() {
    return processTrace;
  }
  
  public void addAvailableDataObjectSet(DataObject availableDataObject) {
    availableDataObjectSet.add(availableDataObject);
  }
  
  public Set<DataObject> getAvailableDataObjectSet() {
    return availableDataObjectSet;
  }

  public ExecutedRequest getNextRequest() {
    return nextRequest;
  }

  public void setNextRequest(ExecutedRequest nextRequest) {
    this.nextRequest = nextRequest;
  }

  public boolean hasNextRequest() {
    return nextRequest != null;
  }
  
}
