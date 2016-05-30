using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.IO;

namespace crosschecker
{
    class checker
    {
        private List<string> p_ports;
        private List<string> r_ports;
        private List<string> sr_ports;
        private List<string> pr_ports;
        private Hashtable ht;
        
        public checker()
        {
           
        }

        public List<string> p_port(string file)
        {
            XmlDocument xmlDoc = new XmlDocument(); // Create an XML document object
            xmlDoc.Load(file);

            // Get elements
            XmlNodeList nodeList = xmlDoc.GetElementsByTagName("P-PORT-PROTOTYPE");
            p_ports = new List<string>();
            
            foreach(XmlNode n in nodeList)
            {
                if (n.NodeType == XmlNodeType.Element)
                {
                    XmlElement element = (XmlElement)n;
                    p_ports.Add(element.GetElementsByTagName("SHORT-NAME").Item(0).InnerText);
                }
                
            }
            return p_ports;
        }

        public List<string> r_port(string file)
        {
            XmlDocument xmlDoc = new XmlDocument(); // Create an XML document object
            xmlDoc.Load(file);

            // Get elements
            XmlNodeList nodeList = xmlDoc.GetElementsByTagName("R-PORT-PROTOTYPE");
            r_ports = new List<string>();

            foreach (XmlNode n in nodeList)
            {
                if (n.NodeType == XmlNodeType.Element)
                {
                    XmlElement element = (XmlElement)n;
                    r_ports.Add(element.GetElementsByTagName("SHORT-NAME").Item(0).InnerText);
                }

            }
            return r_ports;
        }

        public List<string> p_r_ports(string componentName)
        {

            XmlDocument xmlDoc = new XmlDocument(); // Create an XML document object
            xmlDoc.Load("data.xml");
            pr_ports = new List<string>();

            // Get elements
            XmlNodeList nodeList = xmlDoc.GetElementsByTagName("component");
            //p_ports = new List<string>();

            foreach (XmlNode n in nodeList)
            {
                if (n.NodeType == XmlNodeType.Element)
                {
                    XmlElement element = (XmlElement)n;
                    if (element.GetAttribute("name").Equals(componentName))
                    {
                        //  MessageBox.Show(element.GetAttribute("name").ToString());
                        XmlElement child = (XmlElement)element.GetElementsByTagName("pr_ports").Item(0);
                        XmlNodeList childList = child.GetElementsByTagName("name");
                        foreach (XmlNode node in childList)
                        {
                            element = (XmlElement)node;
                            //listBox_port_prPorts.Items.Add(element.InnerText);
                            pr_ports.Add(element.InnerText);
                        }
                        return pr_ports;
                       
                    }

                }
                return pr_ports;

            }
            return pr_ports;
        }


        public Hashtable sr_port(string file)
        {
            XmlDocument xmlDoc = new XmlDocument(); // Create an XML document object
            xmlDoc.Load(file);

            // Get elements
            XmlNodeList nodelist = xmlDoc.GetElementsByTagName("R-PORT-PROTOTYPE");
            sr_ports = new List<string>();
            ht = new Hashtable();
            foreach (XmlNode n in nodelist)
            {
                if (n.NodeType == XmlNodeType.Element)
                {
                    XmlElement element = (XmlElement)n;
                    XmlElement child = (XmlElement)element.GetElementsByTagName("REQUIRED-INTERFACE-TREF").Item(0);

                    if (child.GetAttribute("DEST").Equals("SENDER-RECEIVER-INTERFACE"))
                    {
                        string str = element.GetElementsByTagName("REQUIRED-INTERFACE-TREF").Item(0).InnerText;
                        string interfaces = str.Substring(str.LastIndexOf("/") + 1);
                        try
                        {
                            ht.Add(element.GetElementsByTagName("SHORT-NAME").Item(0).InnerText, interfaces);
                        }
                        catch (Exception e)
                        { }
                    }
                    
                }

            }
            return ht;
        }
        public List<String> getComponents(string directoryPath)
        {
            string[] files = Directory.GetFiles(directoryPath, "*.ecuextract", SearchOption.AllDirectories);

            List<String> l = new List<string>();
            int lIndexOfSlash = 0;
            foreach (string s in files)
            {
                lIndexOfSlash = s.LastIndexOf("\\");

                if (char.IsUpper(s[ lIndexOfSlash+ 1]) && s[lIndexOfSlash+1] != 'I')
                {
                    
                    l.Add(s);
                }
            }
            
            return l;
        }
    }
    
}
