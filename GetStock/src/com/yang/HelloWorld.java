/**
 * 
 */
package com.yang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 * @author Administrator
 * 
 */
public class HelloWorld
{

    public static void AnaHtml(HTMLDocument htmlDoc, Element element, Vector<String> data)
    {
        for (int iCnt = 0; iCnt < element.getElementCount(); ++iCnt)
        {
            Element trChild = element.getElement(iCnt);
            AttributeSet trAttr = trChild.getAttributes();
            Object trName = trAttr.getAttribute(StyleConstants.NameAttribute);
            Object trClassAttr = trAttr.getAttribute(HTML.Attribute.CLASS);
            if (trName instanceof HTML.Tag && trName == HTML.Tag.TR && (null != trClassAttr) && trClassAttr.equals(new String("bg")))
            {
//                try
//                {
//                    System.out.println(">>>>>> TABLE >> tr[" + iCnt + "]" + " name: " + trChild.getName() + ", elem: " + trChild.toString() 
//                                    + " doc: " + htmlDoc.getText(trChild.getStartOffset(), trChild.getEndOffset() - trChild.getStartOffset()));
//                    //attributes.getAttributeNames()
//                }
//                catch (BadLocationException e)
//                {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
                
                for (int iTH = 0; iTH < trChild.getElementCount(); ++iTH)
                {
                    Element thChild = trChild.getElement(iTH);
                    AttributeSet thAttr = thChild.getAttributes();
                    Object thName = thAttr.getAttribute(StyleConstants.NameAttribute);
                    Object thClassAttr = trAttr.getAttribute(HTML.Attribute.CLASS);
                    if (thName instanceof HTML.Tag && thName == HTML.Tag.TD)
                    {
                        try
                        {
                            System.out.println(">>>>>> TABLE >> th[" + trChild.getElementCount() + "]" + " name: " + thChild.getName() + ", elem: " + thChild.toString() 
                                            + " doc: " + htmlDoc.getText(thChild.getStartOffset(), thChild.getEndOffset() - thChild.getStartOffset()));
                            data.add(new String(htmlDoc.getText(thChild.getStartOffset(), thChild.getEndOffset() - thChild.getStartOffset())).replaceAll("\\s", ""));
                            //attributes.getAttributeNames()
                        }
                        catch (BadLocationException e)
                        {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
            
        }
    }
    
    public static void PrintHtml(String strCode, String strName, Vector<String> data)
    {
        URL url;
        try
        {
            String strSep = null;
            if (strCode.charAt(0) == '0')
            {
                strSep = new String("sz");
            }
            else
            {
                strSep = new String("sh");
            }
            
            System.out.println("sep " + strSep);
            // http://www.oschina.net/uploads/doc/javase-6-doc-api-zh_CN/javax/swing/text/html/HTMLDocument.html
            // http://demo.q139.cn
             url = new URL(new String("http://f10.eastmoney.com/f10_v2/ProfitForecast.aspx?code=") + strSep + strCode);
            //url = new URL(new String("http://t.qq.com/"));
            System.out.println("sep " + strSep + " url: " + url.toString() + "UserInfo:" + url.getUserInfo());
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ;
        }
        
        URLConnection connection;
        try
        {
        	System.out.println("1connstr: ");
            connection = url.openConnection();
            System.out.println("2connstr: " + connection.getContentType());
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.75 Safari/537.1");
            System.out.println("3connstr: " + connection.getContent().toString());
            connection.connect();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        InputStream is;
        try
        {
            is = connection.getInputStream();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        HTMLEditorKit htmlKit = new HTMLEditorKit();
        HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
        HTMLEditorKit.Parser parser = new ParserDelegator();
        HTMLEditorKit.ParserCallback callback = htmlDoc.getReader(0);
        try
        {
            parser.parse(br, callback, true);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ;
        }
        
        ElementIterator iterator = new ElementIterator(htmlDoc);
        Element element;
        while ((element = iterator.next()) != null)
        {
            System.out.println("name: " + element.getName() + ", elem: " + element.toString());
            AttributeSet attributes = element.getAttributes();
            Object name = attributes.getAttribute(StyleConstants.NameAttribute);
            Object idAttr = attributes.getAttribute(HTML.Attribute.ID);
            if ((name instanceof HTML.Tag) && (name == HTML.Tag.TABLE) 
                            && (null != idAttr) && (idAttr.toString().equalsIgnoreCase(new String("jgyc_table"))))
            {    
                
                //Eattributes.getAttributeNames()
                int attrCnt = attributes.getAttributeCount();
                //System.out.println("attrCnt<" + name.toString() + ">: " + attrCnt);

//                int startOffset = element.getStartOffset();
//                int endOffset = element.getEndOffset();
                
//                try
//                {
//                    System.out.println(">>>>>> TABLE" + " name: " + element.getName() + ", elem: " + element.toString() 
//                                    + " doc: " + htmlDoc.getText(startOffset, endOffset - startOffset));
//                    //attributes.getAttributeNames()
//                }
//                catch (BadLocationException e)
//                {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
                AnaHtml(htmlDoc, element, data);
            }
        } // end of while
    }
    
    public static int readStock(String strFile, Map<String, String> mapStock)
    {
        File file = new File(strFile);
        System.out.println(strFile);
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String strTmp;
            while ((strTmp = reader.readLine()) != null)
            {
                String[] arrString = strTmp.split("\t");
                if (arrString.length >= 2)
                {
                    //String strUTF = new String(arrString[1].getBytes("gb2312"), "utf-8");
                    mapStock.put(arrString[0], arrString[1]);
                    System.out.println("insert: <" + arrString[0] + ", " + arrString[1] + ">");
                }
                
                System.out.println("tmp str: " + strTmp.toString());
            }
            
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if (null != reader)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return -1;
                }
            }
        }
        
        return 0;
    }
    
    /**
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        
        //PrintHtml();
        Map<String, String> mapStock = new HashMap<String, String>();
        readStock(new String("E:\\workspace\\GetStock\\src\\com\\yang\\stock.txt"), mapStock);
        System.out.println("map stock: " + mapStock.toString());
        
        String strOutput = new String("E:\\workspace\\GetStock\\src\\com\\yang\\statis.txt");
        try
        {
            FileWriter fileWriter = new FileWriter(strOutput, true);
            Set<Entry<String, String>> setData = mapStock.entrySet();
            Iterator itSet = setData.iterator();
            while (itSet.hasNext())
            {
                Entry<String, String> item = (Entry<String, String>) itSet.next();
                System.out.println("map item <" + item.getKey() + ", " + item.getValue() + ">");
                Vector<String> vecData = new Vector<String>();
                PrintHtml(item.getKey(), item.getKey(), vecData);
                
                StringBuffer strBuffer = new StringBuffer();
                if (vecData.size() >= 8)
                {
                    System.out.println("vector item <: " + vecData.toString());
                    strBuffer.append(item.getKey() + "\t" + item.getValue() + "\t"
                                    + vecData.elementAt(0) + "\t" + vecData.elementAt(1) + "\t"
                                    + vecData.elementAt(2) + "\t" + vecData.elementAt(3) + "\t"
                                    + vecData.elementAt(4) + "\t" + vecData.elementAt(5) + "\t"
                                    + vecData.elementAt(6) + "\t" + vecData.elementAt(7) + "\t");
                    //strBuffer.append(new String().valueOf(f))
                    //
                    fileWriter.write(strBuffer.toString() + "\n");
                    fileWriter.flush();
                }
                break;
            }
            fileWriter.close();
            
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
