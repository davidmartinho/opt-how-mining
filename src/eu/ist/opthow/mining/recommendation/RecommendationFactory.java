package eu.ist.opthow.mining.recommendation;

import java.util.HashSet;
import java.util.Set;

import eu.ist.opthow.mining.DataObject;
import eu.ist.opthow.mining.ProcessTrace;
import eu.ist.opthow.mining.ExecutedRequest;
import eu.ist.opthow.mining.Tag;

public class RecommendationFactory {

  private static double PROCESS_MATCH_THRESHOLD = 0.9;
  private static double REQUEST_MATCH_THRESHOLD = 0.9;

  private static RecommendationFactory INSTANCE = new RecommendationFactory();

  private Set<ProcessTrace> existingProcessTraceSet;

  private RecommendationFactory() {
    existingProcessTraceSet = new HashSet<ProcessTrace>();
  }

  public static RecommendationFactory getInstance() {
    return INSTANCE;
  }

  public void addProcessTrace(ProcessTrace processTrace) {
    existingProcessTraceSet.add(processTrace);
  }

  public Recommendation getRecommendationForRequest(ExecutedRequest currentRequest) {
    Set<Tag> recommendedRequestTagSet = new HashSet<Tag>();
    for(ProcessTrace existingProcessTrace : existingProcessTraceSet) {
      if(processTraceMatch(existingProcessTrace, currentRequest.getProcessTrace()) > PROCESS_MATCH_THRESHOLD) {
        for(ExecutedRequest executedRequest : existingProcessTrace.getExecutedRequestSet()) {
          if(executedRequestMatch(executedRequest, currentRequest) > REQUEST_MATCH_THRESHOLD) {
            if(executedRequest.hasNextRequest()) {
              recommendedRequestTagSet.add(executedRequest.getNextRequest().getTag());
            }
          }
        }
      }
    }
    return new Recommendation(recommendedRequestTagSet);
  }
  
  public double processTraceMatch(ProcessTrace processTraceA, ProcessTrace processTraceB) {
    if(processTraceA.hasSameTag(processTraceB)) {
      return 1.0;
    } else {
      return 0.0;
    }
  }
  
  public double executedRequestMatch(ExecutedRequest executedRequestA, ExecutedRequest executedRequestB) {
    if(executedRequestA.hasSameTag(executedRequestB)) {
      int numDataObjectUnionSet = executedRequestA.getAvailableDataObjectSet().size()+executedRequestB.getAvailableDataObjectSet().size();
      int numDataObjectIntersectionSet = 0;
      for(DataObject dataObjectFromExecutedRequestA : executedRequestA.getAvailableDataObjectSet()) {
        for(DataObject dataObjectFromExecutedRequestB : executedRequestB.getAvailableDataObjectSet()) {
          if(dataObjectFromExecutedRequestA.hasSameTag(dataObjectFromExecutedRequestB)) {
            numDataObjectIntersectionSet++;
            numDataObjectUnionSet--;
            break;
          }
        }
      }
      if(numDataObjectUnionSet == 0) {
          return 1.0;
      } else {
        return numDataObjectIntersectionSet / numDataObjectUnionSet;
      }
    } else {
      return 0.0;
    }
  }
  
}
