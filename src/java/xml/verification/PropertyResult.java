package xml.verification;

import javax.xml.bind.annotation.*;

/**
 * Java Class representation of the XML <element propertyResult>.
 */
@XmlRootElement(name = "propertyResult")
public class PropertyResult extends xml.Serializable {

    @XmlElement(name = "propertyType")
    public String type;
    @XmlElement(name = "isValid")
    public boolean isValid;
    @XmlElement(name = "message")
    public String message;
}
