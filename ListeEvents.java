package tp1;

import java.util.Vector;

public class ListeEvents {
 static Vector<Event> events;
 
 public ListeEvents () {
	 events =new Vector<>();
 }
 public void addEvent(Event newEvent ) {
	 int insertIndex =0;
	 while(insertIndex< events.size()) {
		Event e= events.elementAt(insertIndex);
		if(e.getEventT()> newEvent.getEventT()) {
			break;
		}
		else
			insertIndex++;
	 }
	  events.insertElementAt(newEvent, insertIndex);
 }
public void removeE(int removeIndex) {
	// TODO Auto-generated method stub
	events.remove(removeIndex);
}



public boolean isEmpty() {
	return events.isEmpty();
	 
}
public Vector<Event> getListe() {
	// TODO Auto-generated method stub
	return events ;
}
}
