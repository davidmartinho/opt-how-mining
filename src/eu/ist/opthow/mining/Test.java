package eu.ist.opthow.mining;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import eu.ist.opthow.mining.recommendation.RecommendationFactory;

public class Test {

  
  
  
  public static void main(String[] args) throws IOException {
    
    ProcessTrace pt1 = new ProcessTrace("Gerir Tese Mestrado");
    ProcessTrace pt2 = new ProcessTrace("Gerir Tese Mestrado");
    ProcessTrace pt3 = new ProcessTrace("Gerir Tese Mestrado");
    ProcessTrace pt4 = new ProcessTrace("Gerir Tese Mestrado");
    ProcessTrace pt5 = new ProcessTrace("Gerir Tese Doutoramento");

    ExecutedRequest pt1r1 = new ExecutedRequest("Propor Título de Tese");
    ExecutedRequest pt2r1 = new ExecutedRequest("Propor Título de Tese");
    ExecutedRequest pt3r1 = new ExecutedRequest("Propor Título de Tese");
    ExecutedRequest pt4r1 = new ExecutedRequest("Propor Título de Tese");
    ExecutedRequest pt5r1 = new ExecutedRequest("Propor Título de Tese");

    ExecutedRequest pt1r2 = new ExecutedRequest("Entregar Tese");
    ExecutedRequest pt2r2 = new ExecutedRequest("Entregar Tese");
    ExecutedRequest pt3r2 = new ExecutedRequest("Entregar Tese");
    ExecutedRequest pt4r2 = new ExecutedRequest("Entregar Tese");
    ExecutedRequest pt5r2 = new ExecutedRequest("Marcar Reunião de CAT");
    
    DataObject pt1r1d1 = new DataObject("Título Tese");
    DataObject pt2r1d1 = new DataObject("Título Tese");
    DataObject pt3r1d1 = new DataObject("Título Tese");
    DataObject pt4r1d1 = new DataObject("Título Tese");
    DataObject pt5r1d1 = new DataObject("Título Tese");
    
    DataObject pt1r2d1 = new DataObject("Ficheiro da Tese");
    DataObject pt2r2d1 = new DataObject("Ficheiro da Tese");
    DataObject pt3r2d1 = new DataObject("Ficheiro da Tese");
    DataObject pt4r2d1 = new DataObject("Ficheiro da Tese");
    DataObject pt5r2d1 = new DataObject("Data Reunião");

    pt1r1.addAvailableDataObjectSet(pt1r1d1);
        
    pt2r1.addAvailableDataObjectSet(pt2r1d1);
    
    pt3r1.addAvailableDataObjectSet(pt3r1d1);
    
    pt4r1.addAvailableDataObjectSet(pt4r1d1);
    
    pt5r1.addAvailableDataObjectSet(pt5r1d1);
    
    pt1.addExecutedRequest(pt1r1);
    pt1.addExecutedRequest(pt1r2);
    pt1r1.setNextRequest(pt1r2);
    
    pt2.addExecutedRequest(pt2r1);
    pt2.addExecutedRequest(pt2r2);
    pt2r1.setNextRequest(pt2r2);
    
    pt3.addExecutedRequest(pt3r1);
    pt3.addExecutedRequest(pt3r2);
    pt3r1.setNextRequest(pt3r2);
    
    pt4.addExecutedRequest(pt4r1);
    pt4.addExecutedRequest(pt4r2);
    pt4r1.setNextRequest(pt4r2);
    
    pt5.addExecutedRequest(pt5r1);
    pt5.addExecutedRequest(pt5r2);
    pt5r1.setNextRequest(pt5r2);
    
    RecommendationFactory rf = RecommendationFactory.getInstance();
    rf.addProcessTrace(pt1);
    rf.addProcessTrace(pt2);
    rf.addProcessTrace(pt3);
    rf.addProcessTrace(pt4);
    rf.addProcessTrace(pt5);

    ProcessTrace partialProcessTrace = new ProcessTrace("Gerir Tese Mestrado");
    /*ExecutedRequest er = partialProcessTrace.getExecutedRequestSet().iterator().next();
    */
    ExecutedRequest er = new ExecutedRequest("Propor Título de Tese");
    er.addAvailableDataObjectSet(new DataObject("Título Tese"));
    partialProcessTrace.addExecutedRequest(er);
    
    //System.out.println(rf.getRecommendationForRequest(er));
    
    Set<ProcessTrace> processTraceSet = new HashSet<ProcessTrace>();
    processTraceSet.add(pt1);
    processTraceSet.add(pt2);
    processTraceSet.add(pt3);
    processTraceSet.add(pt4);
    processTraceSet.add(pt5);
    
    XESSerializer.serialize(processTraceSet, System.out);
    
    
  }
  
}
