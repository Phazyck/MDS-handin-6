package xml.tasks;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Cal extends xml.Serializable {    

    @XmlElement
    public Tasks tasks;
}