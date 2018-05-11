package nomins.mongo02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class WritingFineDustService {

    @Autowired
    private FineDustRepository fineDustRepository;


    public void writeAll() {
        for(String city : FineDustArea.getAreaCityNames()) {
            String xmlString = getFineDustXml(city);
            fineDustRepository.save(parseXml(xmlString));
        }
    }

    private String getFineDustXml(String city) {
        BufferedReader br = null;
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        String result = "";
        try{
            String urlStr = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?sidoName=" + URLEncoder.encode(city, "UTF-8") +"&searchCondition=HOUR&pageNo=1&numOfRows=1&ServiceKey=y7t3WEIUpppa7zTFRdYGQ4kqA8NlXc8ltzZJPg4TKlXa74Rz%2F6J%2FjumeDu17vwQAwYczhd9gwYoy1Q6Fy65wXg%3D%3D&ver=1.3";
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //응답 읽기
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String line;

            while( (line = br.readLine()) != null ) {
                result = result + line.trim();
            }
        }catch(Exception e) {
            result = null;
        }
        return result;
    }

    private FineDust parseXml(String xmlString) {
        FineDust result = null;

        BufferedReader br = null;
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;

        try{
            InputSource is = new InputSource(new StringReader(xmlString));
            builder = factory.newDocumentBuilder();
            doc = builder.parse(is);
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();

            XPathExpression expr = xpath.compile("//items/item");

            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);


            NodeList row = nodeList.item(0).getChildNodes();
            result = new FineDust();
            for( int j = 0; j< row.getLength(); j++ ) {
                Node node = row.item(j);
                //TODO JAVA8 이용해서 변경 해볼것

                if(node.getNodeName().equalsIgnoreCase("dataTime"))
                    result.setDate(node.getTextContent());
                if(node.getNodeName().equalsIgnoreCase("cityName"))
                    result.setCityName(node.getTextContent());
                if(node.getNodeName().equalsIgnoreCase("so2Value"))
                    result.setSo2( NumberUtil.parseDouble(node.getTextContent()));
                if(node.getNodeName().equalsIgnoreCase("coValue"))
                    result.setCo(NumberUtil.parseDouble(node.getTextContent()));
                if(node.getNodeName().equalsIgnoreCase("o3Value"))
                    result.setO3(NumberUtil.parseDouble(node.getTextContent()));
                if(node.getNodeName().equalsIgnoreCase("no2Value"))
                    result.setNo2(NumberUtil.parseDouble(node.getTextContent()));
                if(node.getNodeName().equalsIgnoreCase("pm10Value"))
                    result.setPm10(NumberUtil.parseInt(node.getTextContent()));
                if(node.getNodeName().equalsIgnoreCase("pm25Value"))
                    result.setPm25(NumberUtil.parseInt(node.getTextContent()));
            }
        }catch(Exception e) {

        }
        return result;
    }
}
