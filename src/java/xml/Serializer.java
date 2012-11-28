package xml;

import java.io.*;
import javax.xml.bind.*;


public class Serializer {

    public static <T extends xml.Serializable> String serialize(T serializable) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(serializable.getClass());
        StringWriter writer = new StringWriter();
        context.createMarshaller().marshal(serializable, writer);
        return writer.toString();
    }

    public static <T extends xml.Serializable> T deSerialize(String xml, Class<T> target) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(target);
        return (T) context.createUnmarshaller().unmarshal(new StringReader(xml));
    }
}
