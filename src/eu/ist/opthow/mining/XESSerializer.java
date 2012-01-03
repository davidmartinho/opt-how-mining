package eu.ist.opthow.mining;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.impl.XAttributeLiteralImpl;
import org.deckfour.xes.model.impl.XAttributeMapImpl;
import org.deckfour.xes.model.impl.XAttributeTimestampImpl;
import org.deckfour.xes.model.impl.XEventImpl;
import org.deckfour.xes.model.impl.XLogImpl;
import org.deckfour.xes.model.impl.XTraceImpl;
import org.deckfour.xes.out.XSerializer;
import org.deckfour.xes.out.XesXmlSerializer;

public class XESSerializer {

  private static class Key {
    static final String PROCESS_ID = "process.id";
    static final String PROCESS_TITLE = "process.title";
    static final String PROCESS_START_DATE = "process.start.date";
    static final String PROCESS_END_DATE = "process.end.date";
    
    static final String REQUEST_ID = "request.id";
    static final String REQUEST_SUBJECT = "request.subject";
    static final String REQUEST_START_DATE = "request.start.date";
    static final String REQUEST_END_DATE = "request.end.date";
    
    static final String DATAOBJECT_ID = "dataobject.id";
    static final String DATAOBJECT_LABEL = "dataobject.label";
    static final String DATAOBJECT_CREATION_DATE = "request.creation.date";
  }
  
  private static final XESSerializer INSTANCE = new XESSerializer();
  private XSerializer xSerializer = new XesXmlSerializer();
  
  public static void serialize(Set<ProcessTrace> processTraceSet, OutputStream outputStream) throws IOException {
    XAttributeMap attributeMap = new XAttributeMapImpl();
    
    XLog log = new XLogImpl(attributeMap);
    for(ProcessTrace processTrace : processTraceSet) {
      XTrace trace = getTraceOfProcess(processTrace);
      log.add(trace);
    }
    log.add(new XTraceImpl(attributeMap));
    XESSerializer.INSTANCE.xSerializer.serialize(log, outputStream);
    
  }
  
  private static XTrace getTraceOfProcess(ProcessTrace processTrace) {
    XAttributeMap attributeMap = new XAttributeMapImpl();
    XTrace trace = new XTraceImpl(attributeMap);
    trace.addAll(getEventSetOfProcess(processTrace));
    trace.add(getProcessTraceStartEvent(processTrace));
    trace.add(getProcessTraceEndEvent(processTrace));
    return trace;
  }


  private static XEvent getProcessTraceEndEvent(ProcessTrace processTrace) {
    XAttributeMap attributeMap = new XAttributeMapImpl();
    attributeMap.put(Key.PROCESS_ID, new XAttributeLiteralImpl(Key.PROCESS_ID, processTrace.toString()));
    attributeMap.put(Key.PROCESS_END_DATE, new XAttributeTimestampImpl(Key.PROCESS_END_DATE, processTrace.getEndDate()));
    XEvent event = new XEventImpl(attributeMap);
    return event;
  }

  private static XEvent getProcessTraceStartEvent(ProcessTrace processTrace) {
    XAttributeMap attributeMap = new XAttributeMapImpl();
    attributeMap.put(Key.PROCESS_ID, new XAttributeLiteralImpl(Key.PROCESS_ID, processTrace.toString()));
    attributeMap.put(Key.PROCESS_START_DATE, new XAttributeTimestampImpl(Key.PROCESS_START_DATE, processTrace.getStartDate()));
    XEvent event = new XEventImpl(attributeMap);
    return event;
  }

  private static Collection<? extends XEvent> getEventSetOfProcess(ProcessTrace processTrace) {
    Set<XEvent> eventSet = new HashSet<XEvent>();
    for(ExecutedRequest executedRequest : processTrace.getExecutedRequestSet()) {
      XAttributeMap attributeMap = new XAttributeMapImpl();
      attributeMap.put("request.name", new XAttributeLiteralImpl("request.name", executedRequest.getSubject()));
      for(DataObject availableDataObject : executedRequest.getAvailableDataObjectSet()) {
        XAttributeMap dataObjectAttributeMap = new XAttributeMapImpl();
        dataObjectAttributeMap.put("data.object.name", new XAttributeLiteralImpl("data.object.name", availableDataObject.getLabel()));
        XEvent event = new XEventImpl(dataObjectAttributeMap);
        eventSet.add(event);
      }
      XEvent event = new XEventImpl(attributeMap);      
      eventSet.add(event);
    }
    return eventSet;
  }
}
