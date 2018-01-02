package Message;

import javax.xml.XMLConstants;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class XMLParser {
	private final SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
}
