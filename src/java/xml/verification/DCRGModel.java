package xml.verification;

import java.util.*;
import javax.xml.bind.annotation.*;

/**
 * Java Class representation of the XML element <dcrgModel>. Also applicaple to
 * <dcrgProperty> and <dcrgComposite>.
 */
@XmlRootElement(name = "dcrgModel")
public class DCRGModel extends xml.Serializable {

    @XmlAttribute(name = "name")
    public String name;
    @XmlElement(name = "propertyResult")
    public List<PropertyResult> results;
    @XmlElement(name = "verificationStatistics")
    public VerificationStatistics statistic;
    @XmlElement(name = "statespaceInDotFormat")
    public String statespace;
}
