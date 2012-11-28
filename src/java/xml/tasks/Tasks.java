package xml.tasks;

import java.util.*;
import javax.xml.bind.annotation.*;


@XmlRootElement
public class Tasks extends xml.Serializable {
    
    public Tasks() {
        this("");
    }
    
    public Tasks(String name) {
        this.name = name;
        this.task = new ArrayList<Task>();
    }

    @XmlAttribute
    public String name;

    @XmlElement
    public List<Task> task;


}