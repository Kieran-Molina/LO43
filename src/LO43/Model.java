package LO43;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Model {

    public static final int VITESSE_DEPLACEMENT = 12, HAUTEUR_SAUT = 20;

    public boolean du, dr, dl, gu, gl, gr;

    public ArrayList<Element> elements;

    private final static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder builder = null;

    private File f;
    private Document doc;

    public Model(){
        du = false; dr = false; dl = false; gu=false; gl=false; gr = false;

        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


        try {
            f = new File("sauvegardes.xml");
            doc = builder.parse(f);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
            exit(0);
        }

        //lireSauvegarde("default");
    }

    public void deplacementElement(){
        for (Element element : elements){
            if(element.isControlable()){
                if((element.isControlGauche() && gl) || (element.isControlDroite() && dl)) {
                    if(element.getDx() > -VITESSE_DEPLACEMENT)
                    element.setDx(element.getDx()-4);
                }
                if((element.isControlGauche() && gr) || (element.isControlDroite() && dr)){
                    if(element.getDx() < VITESSE_DEPLACEMENT)
                        element.setDx(element.getDx()+4);
                }

                if (((element.isControlGauche() && gu)|| (element.isControlDroite() && du)) && element.isLand()){
                    element.setDy(-HAUTEUR_SAUT);
                    element.setLand(false);
                }
            }
        }
    }

    public void sauvegarder(String nomSauvegarde){
        //suppression ancienne sauvegarde
        if(saveAlreadyExists(nomSauvegarde)){
            doc.getDocumentElement().removeChild(doc.getElementsByTagName(nomSauvegarde).item(0));
        }

        //creation savegarde
        org.w3c.dom.Element save = doc.createElement(nomSauvegarde);
        //ajout
        for (Element e: elements){
            save.appendChild(e.getXML(doc));
        }
        doc.getDocumentElement().appendChild(save);
        doc.getDocumentElement().normalize();
        saveFile();
    }

    public ArrayList<String> getSauvegardes(){
        NodeList list = doc.getDocumentElement().getChildNodes();
        ArrayList<String> sv = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            if (!list.item(i).getNodeName().startsWith("#"))
            sv.add(list.item(i).getNodeName());
        }
        return sv;
    }

    public void lireSauvegarde(String nomSauvegarde){
        if (nomSauvegarde == null) return;
        elements.clear();

        NodeList save = doc.getDocumentElement().getElementsByTagName(nomSauvegarde).item(0).getChildNodes();

        for (int i = 0; i < save.getLength(); i++) {
            switch (save.item(i).getNodeName()){
                case "Element":
                    elements.add(new Element((org.w3c.dom.Element) save.item(i)));
                    break;
                case "ElementControlable":
                    elements.add(new ElementControlable((org.w3c.dom.Element) save.item(i)));
                    break;
                case "ElementFixe":
                    elements.add(new ElementFixe((org.w3c.dom.Element) save.item(i)));
                    break;
                case "ZoneActivable":
                    elements.add(new ZoneActivable((org.w3c.dom.Element) save.item(i)));
                    break;
                case "#text" :
                    break;
                default:
                    System.out.println("type non reconnu : "+save.item(i).getNodeName());
                    break;
            }
        }
    }

    public void saveFile(){
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(f);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done");
    }


    public boolean saveAlreadyExists(String nomSauvegarde) {
        for (String s : getSauvegardes()){
            if (s.equals(nomSauvegarde)) return true;
        }
        return false;
    }
}
