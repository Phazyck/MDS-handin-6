package xml.tasks;

import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class Task extends xml.Serializable {

    public Task() {
        this("", "", "", "", false);
    }

    public Task(String id, String name, String date, String status, boolean required) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.status = status;
        this.required = required;
    }
    @XmlAttribute
    public String id;
    @XmlAttribute
    public String name;
    @XmlAttribute
    public String date;
    @XmlAttribute
    public String status;
    @XmlAttribute
    public boolean required;
    @XmlElement
    public String description;
    @XmlElement
    public String attendants;
    @XmlElement
    public String conditions;
    @XmlElement
    public String responses;

    public boolean executed() {
        return status.equalsIgnoreCase("executed");
    }

    public List<String> attendantList() {
        return stringAsList(attendants);
    }

    public List<String> conditionList() {
        return stringAsList(conditions);
    }

    public List<String> responseList() {
        return stringAsList(responses);
    }

    private List<String> stringAsList(String string) {
        if (string.trim().isEmpty()) {
            return new ArrayList<String>();
        } else {
            return Arrays.asList(string.split(", "));
        }
    }
}