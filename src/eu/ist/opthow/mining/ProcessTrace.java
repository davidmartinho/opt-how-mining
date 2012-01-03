package eu.ist.opthow.mining;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ProcessTrace extends Resource {
  
  private Date startDate = new Date();
  private Date endDate = new Date(startDate.getTime()+32234L);
  
  private Set<ExecutedRequest> executedRequestSet;
  
  public ProcessTrace(String title) {
    Tag titleTag = TagFactory.getTag(title);
    annotate(titleTag);
    executedRequestSet = new HashSet<ExecutedRequest>();
    ExecutedRequest firstRequest = new ExecutedRequest(title);
    executedRequestSet.add(firstRequest);
    firstRequest.setProcessTrace(this);
  }
  
  public String getTitle() {
    return getTag().getKeyword();
  }
  
  public void addExecutedRequest(ExecutedRequest request) {
    if(executedRequestSet.size()==1) {
      executedRequestSet.iterator().next().setNextRequest(request);
    }
    executedRequestSet.add(request);
    request.setProcessTrace(this);
  }

  public Set<ExecutedRequest> getExecutedRequestSet() {
    return executedRequestSet;
  }
  
  public Date getStartDate() {
    return startDate;
  }
  
  public Date getEndDate() {
    return endDate;
  }

  public double match(ProcessTrace process) {
    if(process.getTag().equals(getTag())) {
      return 1.0;
    } else {
      return 0.0;
    }
  }
  
}
