
using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.IO;
using System.Windows.Forms;
using System.Xml;



namespace crosschecker
{
    public partial class Form1 : Form
    {
        checker ch;
        Hashtable sr;
        public Form1()
        {
            this.InitializeComponent();
        }

        private void loadComponentToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (this.openFileDialog1.ShowDialog() != DialogResult.OK)
                return;
            this.ch = new checker();
            this.eraseWidgets();
            this.showWidgets();
            this.setComponentName(this.openFileDialog1.FileName);
            this.showPorts();
            this.showDataElements();
        }

        private void eraseWidgets()
        {
            this.listBox_port_component.Items.Clear();
            this.listBox_port_prPorts.Items.Clear();
            this.listBox_port_commonPorts.Items.Clear();
            this.listBox_signal_srPorts.Items.Clear();
            this.listBox_signal_dataElement.Items.Clear();
            this.label1.Text = "Total: ";
        }

        private void setComponentName(string str)
        {
            string str1 = str.Substring(str.LastIndexOf("\\") + 1);
            this.label2.Text = str1.Substring(0, str1.Length - 15);
        }

        private void showPorts()
        {
            /*
            foreach (object obj in this.ch.p_port())
                this.listBox_port_prPorts.Items.Add(obj);
            foreach (object obj in this.ch.r_port())
                this.listBox_port_commonPorts.Items.Add(obj);
            this.sr = this.ch.sr_port();
            foreach (string key in (IEnumerable)this.sr.Keys)
                this.listBox_signal_srPorts.Items.Add((object)key);
             */
        }

        private void showDataElements()
        {
            foreach (string str in (IEnumerable)this.sr.Values)
            {
                foreach (string file in Directory.GetFiles("C:\\Bdc2015ContiBody", str + ".ecuextract", SearchOption.AllDirectories))
                    this.showData(file);
            }
        }

        private void showData(string iFileName)
        {
            XmlDocument xmlDocument = new XmlDocument();
            xmlDocument.Load(iFileName);
            foreach (XmlNode xmlNode in xmlDocument.GetElementsByTagName("DATA-ELEMENT-PROTOTYPE"))
            {
                if (xmlNode.NodeType == XmlNodeType.Element)
                    this.listBox_signal_dataElement.Items.Add((object)((XmlElement)xmlNode).GetElementsByTagName("SHORT-NAME").Item(0).InnerText);
            }
        }

        private void showWidgets()
        {
            this.label1.Visible = true;
            this.label2.Visible = true;
            this.groupBox_port_prPorts.Visible = true;
            this.groupBox2_port_commonPorts.Visible = true;
            this.groupBox_port_shared_component.Visible = true;
            this.groupBox_port_component.Visible = true;
            this.groupBox_signal_srPorts.Visible = true;
            this.groupBox_signal_dataElement.Visible = true;
            this.groupBox_signal_component.Visible = true;
            this.groupBox_signal_signal.Visible = true;
        }

        private void loadDirectoryToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.eraseWidgets();
            this.showWidgets();
            string direcory = loadDirectory();
            if (direcory != null)
            this.loadAllData(direcory);
        }

        private void loadAllData(string directory)
        {
            int lIndexOfSlash = 0, lIndexOfdot = 0;
            string sub = "";
            ch = new checker();
            List<string> r_ports = new List<string>();
            List<string> p_ports = new List<string>();
            List<string> comps = ch.getComponents(directory);
            TextWriter tw = new StreamWriter("data.xml");
            sr = new Hashtable();

            tw.WriteLine("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            tw.WriteLine("<root>");
            foreach (string comp in comps)
            {
                lIndexOfSlash = comp.LastIndexOf("\\");
                lIndexOfdot = comp.LastIndexOf(".");
                sub = comp.Substring(lIndexOfSlash + 1, lIndexOfdot - lIndexOfSlash - 1);

                tw.WriteLine("<component name = \"" + sub + "\">");

                p_ports = ch.p_port(comp);
                tw.WriteLine("<pr_ports>");
                foreach (string p_port in p_ports)
                {
                    tw.WriteLine("<name>" + p_port + "</name>");

                }
                r_ports = ch.r_port(comp);
                foreach (string r_port in r_ports)
                {
                    tw.WriteLine("<name>" + r_port + "</name>");
                }
                tw.WriteLine("</pr_ports>");

                tw.WriteLine("<sr_ports>");
                sr = ch.sr_port(comp);
                foreach (string sr_port in sr.Keys)
                {
                    tw.WriteLine("<name>" + sr_port + "</name>");
                }
                tw.WriteLine("</sr_ports>");


                tw.WriteLine("<data_elements>");
                foreach (string dataE in sr.Values)
                {
                   
                    tw.WriteLine("<name>" + dataE + "</name>");
                }
                tw.WriteLine("</data_elements>");


                this.listBox_port_component.Items.Add(sub);
                this.listBox_signal_component.Items.Add(sub);
                tw.WriteLine("</component>");
            }
            tw.WriteLine("</root>");
            tw.Close();
            this.label1.Text = "Total: " + comps.Count;
            label5.Text = "Total: " + comps.Count;
        }

        private string loadDirectory()
        {
            FolderBrowserDialog folderBrowserDialog = new FolderBrowserDialog();
            folderBrowserDialog.ShowDialog();
            if (!string.IsNullOrWhiteSpace(folderBrowserDialog.SelectedPath))
                return folderBrowserDialog.SelectedPath;
            int num = (int)MessageBox.Show("Not a valid directory");
            return (string)null;
        }

        private void showComponents(string directoryPath)
        {
            if (directoryPath == null)
                return;
            List<string> components = new checker().getComponents(directoryPath);
            foreach (string str in components)
            {
                this.listBox_port_component.Items.Add((object)str);
                this.listBox_signal_component.Items.Add((object)str);
            }
            this.label1.Text = "Total: " + (object)components.Count;
        }

        private void label1_Click(object sender, EventArgs e)
        {
        }

        private void listBox_port_component_SelectedValueChanged(object sender, EventArgs e)
        {
            System.Windows.Forms.ListBox listbox = (System.Windows.Forms.ListBox)sender;
            listBox_port_prPorts.Items.Clear();
            listBox_port_shared_component.Items.Clear();
            listBox_port_commonPorts.Items.Clear();


            XmlDocument xmlDoc = new XmlDocument(); // Create an XML document object
            xmlDoc.Load("data.xml");

            // Get elements
            XmlNodeList nodeList = xmlDoc.GetElementsByTagName("component");
            //p_ports = new List<string>();

            foreach (XmlNode n in nodeList)
            {
                if (n.NodeType == XmlNodeType.Element)
                {
                    XmlElement element = (XmlElement)n;
                    if (element.GetAttribute("name").Equals(listbox.SelectedItem.ToString()))
                    {
                        //  MessageBox.Show(element.GetAttribute("name").ToString());
                        XmlElement child = (XmlElement)element.GetElementsByTagName("pr_ports").Item(0);
                        XmlNodeList childList = child.GetElementsByTagName("name");
                        foreach (XmlNode node in childList)
                        {
                            element = (XmlElement)node;
                            listBox_port_prPorts.Items.Add(element.InnerText);
                            //MessageBox.Show(element.ParentNode.ParentNode.Attributes[0].Value);
                            XmlNodeList nodeList1 = xmlDoc.GetElementsByTagName("name");

                            foreach (XmlNode snode in nodeList1)
                            {
                                if (n.NodeType == XmlNodeType.Element)
                                {
                                    XmlElement selement = (XmlElement)snode;
                                    if (selement.InnerText == element.InnerText)
                                    {
                                        if (selement.ParentNode.ParentNode.Attributes[0].Value != element.ParentNode.ParentNode.Attributes[0].Value)
                                        {
                                            if (!listBox_port_shared_component.Items.Contains(selement.ParentNode.ParentNode.Attributes[0].Value))
                                            {
                                                if (!listBox_port_commonPorts.Items.Contains(selement.InnerText))
                                                    listBox_port_commonPorts.Items.Add(selement.InnerText);

                                                listBox_port_shared_component.Items.Add(selement.ParentNode.ParentNode.Attributes[0].Value);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        label2.Text = "Total: " + childList.Count.ToString();

                    }

                }

            }


            label3.Text = "Total: " + listBox_port_commonPorts.Items.Count;
            label4.Text = "Total: " + listBox_port_shared_component.Items.Count;



        }

        private void listBox_signal_component_SelectedValueChanged(object sender, EventArgs e)
        {

            System.Windows.Forms.ListBox listbox = (System.Windows.Forms.ListBox)sender;
            listBox_signal_srPorts.Items.Clear();
            listBox_signal_dataElement.Items.Clear();
            listBox_signal_signal.Items.Clear();


            XmlDocument xmlDoc = new XmlDocument(); // Create an XML document object
            xmlDoc.Load("data.xml");

            // Get elements
            XmlNodeList nodeList = xmlDoc.GetElementsByTagName("component");

            foreach (XmlNode n in nodeList)
            {
                if (n.NodeType == XmlNodeType.Element)
                {
                    XmlElement element = (XmlElement)n;
                    if (element.GetAttribute("name").Equals(listbox.SelectedItem.ToString()))
                    {
                        //  MessageBox.Show(element.GetAttribute("name").ToString());
                        XmlElement child = (XmlElement)element.GetElementsByTagName("sr_ports").Item(0);
                        XmlNodeList childList = child.GetElementsByTagName("name");
                        foreach (XmlNode node in childList)
                        {
                            XmlElement sr_element = (XmlElement)node;
                            listBox_signal_srPorts.Items.Add(sr_element.InnerText);
                        }
                        label6.Text = "Total: " + childList.Count.ToString();

                        XmlElement child1 = (XmlElement)element.GetElementsByTagName("data_elements").Item(0);
                        if (child != null)
                        {
                            //MessageBox.Show("Hello");
                            XmlNodeList childList1 = child1.GetElementsByTagName("name");
                            foreach (XmlNode node in childList1)
                            {
                                XmlElement dt_element = (XmlElement)node;
                                string[] file = Directory.GetFiles("C:\\Bdc2015ContiBody", dt_element.InnerText + ".ecuextract", SearchOption.AllDirectories);
                                
                                if(file.Length > 0)
                                this.showData(file[0]);
                            }
                            label7.Text = "Total: " + listBox_signal_dataElement.Items.Count;
                        }
                    }

                }
            }
        }

        private void groupBox_port_component_Enter(object sender, EventArgs e)
        {

        }



    }
}