package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Course {

    public void run(final Document xml) {
        final Element root = xml.getDocumentElement();
        System.out.println("--- ROOT: " + root.getTagName());
        this.run(root, root.getChildNodes());
    }

    private void run(Element higher, NodeList inferior) {
        if (inferior == null) {
            return;
        }

        for (int i = 0; i < inferior.getLength(); i++) {
            Node node = inferior.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element child = (Element) node;
                System.out.println("--- Tag: " + higher.getTagName() + " -> " + child.getTagName());
                this.run(child, child.getChildNodes());
            }
        }
    }

}
